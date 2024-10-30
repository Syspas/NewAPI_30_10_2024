package org.JiraApiClient.step1;


import org.JiraApiClient.BasicTemplate.ConfigLoader;
import org.JiraApiClient.BasicTemplate.JiraDataFetcher;

/**
 * Главный класс для запуска программы.
 */
public class Main {
    public static void main(String[] args) {


         String issueKey = "KAN-1"; // Задайте ключ задачи здесь

        // Создаем конфигурацию и клиент
        ConfigLoader configLoader = new ConfigLoader();
        org.JiraApiClient.BasicTemplate.JiraDataFetcher jiraDataFetcher = new JiraDataFetcher(configLoader);

        // Запрашиваем данные задачи
        jiraDataFetcher.fetchIssueData(issueKey);
    }
}
