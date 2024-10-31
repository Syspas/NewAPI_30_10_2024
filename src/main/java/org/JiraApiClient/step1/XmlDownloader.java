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
 * Класс для загрузки XML-данных задачи из Jira.
 */
public class XmlDownloader {
    static String jiraUrl ;
    static String jiraUsername;
    static String jiraApiToken;


    /**
     * Конструктор инициализирует параметры для подключения.
     */
    public XmlDownloader(String jiraUrl, String jiraUsername, String jiraApiToken) {
        this.jiraUrl = jiraUrl;
        this.jiraUsername = jiraUsername;
        this.jiraApiToken = jiraApiToken;
    }

    /**
     * Извлекает XML-данные задачи по ее ключу и сохраняет их в файл.
     *
     * @param issueKey Ключ задачи в Jira
     */
    public static void fetchIssueXml(String issueKey) {
        String xmlUrl = jiraUrl + "/si/jira.issueviews:issue-xml/" + issueKey + "/" + issueKey + ".xml";

        try {
            URI uri = new URI(xmlUrl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " +
                    Base64.getEncoder().encodeToString((jiraUsername + ":" + jiraApiToken).getBytes()));

            if (connection.getResponseCode() == 200) {
                StringBuilder xmlData = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        xmlData.append(inputLine);
                    }
                }

                String fileName = issueKey + "_details.xml";
                FileSaver.saveToFile(fileName, xmlData.toString());

                System.out.println("XML успешно получен и сохранен по пути: " + fileName);
            } else {
                System.out.println("Ошибка при получении XML данных: " + connection.getResponseCode());
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
