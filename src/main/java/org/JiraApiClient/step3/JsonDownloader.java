package org.JiraApiClient.step3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;

public class JsonDownloader {

    private final String jiraUrl;
    private final String jiraUsername;
    private final String jiraApiToken;

    public JsonDownloader(String jiraUrl, String jiraUsername, String jiraApiToken) {
        this.jiraUrl = jiraUrl;
        this.jiraUsername = jiraUsername;
        this.jiraApiToken = jiraApiToken;
    }

    public void fetchIssueJson(String issueKey) {
        String jsonUrl = jiraUrl + "/rest/api/2/issue/" + issueKey;
        HttpURLConnection connection = null;

        try {
            URI uri = new URI(jsonUrl);
            URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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

                ObjectMapper objectMapper = new ObjectMapper();
                JiraIssue issue = objectMapper.readValue(jsonData.toString(), JiraIssue.class);
                System.out.println("Задача: " + issue.getKey());
                System.out.println("Заголовок: " + issue.getFields().getSummary());
                System.out.println("Описание: " + issue.getFields().getDescription());

                String fileName = issueKey + "_details.json";
                FileSaver.saveToFile(fileName, jsonData.toString());
                System.out.println("JSON успешно получен и сохранен по пути: " + fileName);
            } else {
                StringBuilder errorResponse = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        errorResponse.append(inputLine);
                    }
                }
                System.out.println("Ошибка при получении JSON данных: Код ответа " + responseCode);
                System.out.println("Ответ от сервера: " + errorResponse.toString());
            }

        } catch (IOException | URISyntaxException e) {
            System.err.println("Ошибка при подключении к Jira: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
