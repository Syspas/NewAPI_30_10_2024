package org.JiraApiClient.step3;



public class Main {
    public static void main(String[] args) {
        String jiraUrl = "https://cyberportalinfo.atlassian.net"; // URL вашего Jira
        String jiraUsername = "ваш_логин"; // Замените на ваш логин
        String jiraApiToken = "ваш_токен"; // Замените на ваш API токен
        String issueKey = "KAN-1"; // Замените на нужный ключ задачи

        JsonDownloader jsonDownloader = new JsonDownloader(jiraUrl, jiraUsername, jiraApiToken);
        jsonDownloader.fetchIssueJson(issueKey);
    }
}
