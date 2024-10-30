package org.JiraApiClient.step1;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.JiraApiClient.BasicTemplate.ConfigLoader;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Класс для извлечения данных задачи из Jira.
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
     * Извлекает данные задачи по ее ключу и сохраняет их.
     *
     * @param issueKey Ключ задачи в Jira
     */
    public void fetchIssueData(String issueKey) {
        try (JiraRestClient client = new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(new URI(jiraUrl), jiraUsername, jiraApiToken)) {

            Issue issue = client.getIssueClient().getIssue(issueKey).claim();
            String issueData = "Задача: " + issue.getSummary();

            FileSaver.saveToFile(issueKey + ".txt", issueData);
            new XmlDownloader(jiraUrl, jiraUsername, jiraApiToken).fetchIssueXml(issueKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
