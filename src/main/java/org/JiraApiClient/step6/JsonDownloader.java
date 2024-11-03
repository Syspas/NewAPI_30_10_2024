package org.JiraApiClient.step6;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;

/**
 * Класс для загрузки JSON-данных задачи из Jira.
 * Загружает конфигурацию из файла и использует её для выполнения запроса к API Jira.
 */
public class JsonDownloader {

    /** URL Jira. */
    private final String jiraUrl;

    /** Имя пользователя Jira. */
    private final String jiraUsername;

    /** Токен API Jira. */
    private final String jiraApiToken;

    /** Объект для парсинга JSON. */
    private final JsonParser jsonParser;

    /**
     * Создает экземпляр JsonDownloader с указанным путем к файлу конфигурации.
     * Путь к файлу конфигурации захардкоден в коде.
     *
     * @throws RuntimeException если файл конфигурации не найден или параметры отсутствуют
     */
    public JsonDownloader() {
        String configFilePath = "config.properties"; // Захардкодьте путь к файлу конфигурации
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                throw new IOException("Файл конфигурации не найден: " + configFilePath);
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке конфигурации: " + e.getMessage());
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }

        this.jiraUrl = properties.getProperty("jira.url");
        this.jiraUsername = properties.getProperty("jira.username");
        this.jiraApiToken = properties.getProperty("jira.api.token");

        // Проверка наличия параметров конфигурации
        if (jiraUrl == null || jiraUsername == null || jiraApiToken == null) {
            throw new IllegalArgumentException("Параметры конфигурации не найдены. Проверьте файл конфигурации.");
        }

        // Проверка корректности URL
        if (!jiraUrl.startsWith("http")) {
            throw new IllegalArgumentException("Некорректный URL: " + jiraUrl);
        }

        // Инициализация JsonParser
        this.jsonParser = new JsonParser();

        // Вывод загруженных параметров для отладки
        System.out.println("Загруженный URL: " + this.jiraUrl);
        System.out.println("Загруженное имя пользователя: " + this.jiraUsername);
    }

    /**
     * Выполняет запрос к API Jira для получения JSON-данных о задаче с заданным ключом.
     *
     * @param issueKey ключ задачи в Jira
     * @return объект Task, представляющий задачу Jira
     */
    public Task fetchIssueJson(String issueKey) {
        String jsonUrl = jiraUrl + "/rest/api/2/issue/" + issueKey;
        HttpURLConnection connection = null;

        try {
            URI uri = new URI(jsonUrl);
            URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Установка заголовков аутентификации
            String auth = Base64.getEncoder().encodeToString((jiraUsername + ":" + jiraApiToken).getBytes());
            connection.setRequestProperty("Authorization", "Basic " + auth);
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            System.out.println("Запрашиваемый URL: " + jsonUrl);
            System.out.println("Код ответа: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder jsonData = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        jsonData.append(inputLine);
                    }
                }

                // Логируем полученные данные
                System.out.println("Ответ от Jira: " + jsonData.toString());

                // Используем JsonParser для парсинга JSON в объект Task
                return jsonParser.parseJsonFromUrl(jsonData.toString());

            } else {
                throw new IOException("Ошибка при получении JSON данных: Код ответа " + responseCode);
            }

        } catch (IOException | URISyntaxException e) {
            System.err.println("Ошибка при подключении к Jira: " + e.getMessage());
            e.printStackTrace();
            return null; // Возвращаем null в случае ошибки
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
