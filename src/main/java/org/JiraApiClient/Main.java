package org.JiraApiClient;



/**
 * Главный класс для запуска программы.
 */
public class Main {
    public static void main(String[] args) {


         String issueKey = "KAN-1"; // Задайте ключ задачи здесь

        // Создаем конфигурацию и клиент
        ConfigLoader configLoader = new ConfigLoader();
        JiraDataFetcher jiraDataFetcher = new JiraDataFetcher(configLoader);

        // Запрашиваем данные задачи
        jiraDataFetcher.fetchIssueData(issueKey);
    }
}
