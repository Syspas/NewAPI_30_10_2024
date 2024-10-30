package org.JiraApiClient.BasicTemplate;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Класс для извлечения данных из Jira.
 */
public class JiraDataFetcher {

    private final String jiraUrl;
    private final String jiraUsername;
    private final String jiraApiToken;

    /**
     * Конструктор инициализирует значения параметров из конфигурации.
     */
    public JiraDataFetcher(ConfigLoader configLoader) {
        this.jiraUrl = configLoader.getProperty("jiraUrl");
        this.jiraUsername = configLoader.getProperty("jiraUsername");
        this.jiraApiToken = configLoader.getProperty("jiraApiToken");
    }

    /**
     * Извлекает данные задачи по ее ключу и сохраняет их в файл.
     *
     * @param issueKey Ключ задачи в Jira
     */
    public void fetchIssueData(String issueKey) {
        try (JiraRestClient client = new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(new URI(jiraUrl), jiraUsername, jiraApiToken)) {

            Issue issue = client.getIssueClient().getIssue(issueKey).claim();
            String issueData = "Задача: " + issue.getSummary();

            saveToFile(issueKey + ".txt", issueData);
            fetchIssueXml(issueKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Извлекает XML-данные задачи по ее ключу и сохраняет их в файл.
     *
     * @param issueKey Ключ задачи в Jira
     */
    private void fetchIssueXml(String issueKey) {
        String xmlUrl = jiraUrl + "/si/jira.issueviews:issue-xml/" + issueKey + "/" + issueKey + ".xml";

        try {
            // Создание URI и преобразование его в URL
            URI uri = new URI(xmlUrl);
            URL url = uri.toURL(); // Используем toURL для создания объекта URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " +
                    java.util.Base64.getEncoder().encodeToString((jiraUsername + ":" + jiraApiToken).getBytes()));

            // Проверка кода ответа
            if (connection.getResponseCode() == 200) {
                StringBuilder xmlData = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        xmlData.append(inputLine);
                    }
                }

                // Сохранение XML данных в файл
                String fileName = issueKey + "_details.xml";
                saveToFile(fileName, xmlData.toString());

                // Вывод сообщения об успешном сохранении
                System.out.println("XML успешно получен и сохранен по пути: " + fileName);
            } else {
                System.out.println("Ошибка при получении XML данных: " + connection.getResponseCode());
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохраняет строку в файл.
     *
     * @param filename Имя файла
     * @param data Данные для записи
     */
    private void saveToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
