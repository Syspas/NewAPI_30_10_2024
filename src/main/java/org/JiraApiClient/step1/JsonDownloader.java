package org.JiraApiClient.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;

/**
 * Класс для загрузки JSON-данных задачи из Jira.
 */
public class JsonDownloader {

     static String jiraUrl ;
     static String jiraUsername;
     static String jiraApiToken;

    /**
     * Конструктор инициализирует параметры для подключения.
     */
    public JsonDownloader(String jiraUrl, String jiraUsername, String jiraApiToken) {
        this.jiraUrl = jiraUrl;
        this.jiraUsername = jiraUsername;
        this.jiraApiToken = jiraApiToken;
    }

    /**
     * Извлекает JSON-данные задачи по ее ключу и сохраняет их в файл.
     *
     * @param issueKey Ключ задачи в Jira
     */
    public static void fetchIssueJson(String issueKey) {
        String jsonUrl = jiraUrl + "/rest/api/2/issue/" + issueKey;

        try {
            URI uri = new URI(jsonUrl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " +
                    Base64.getEncoder().encodeToString((jiraUsername + ":" + jiraApiToken).getBytes()));
            connection.setRequestProperty("Accept", "application/json"); // Устанавливаем заголовок Accept для JSON

            if (connection.getResponseCode() == 200) {
                StringBuilder jsonData = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        jsonData.append(inputLine);
                    }
                }

                String fileName = issueKey + "_details.json";
                FileSaver.saveToFile(fileName, jsonData.toString());

                System.out.println("JSON успешно получен и сохранен по пути: " + fileName);
            } else {
                System.out.println("Ошибка при получении JSON данных: " + connection.getResponseCode());
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
