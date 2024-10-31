package org.JiraApiClient.step1;

import org.JiraApiClient.BasicTemplate.ConfigLoader;
import org.JiraApiClient.BasicTemplate.JiraDataFetcher;

/**
 * Главный класс для запуска приложения, извлекающего данные из Jira.
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Инициализируем ConfigLoader для загрузки конфигурации
            ConfigLoader configLoader = new ConfigLoader("config.properties");

            // Инициализируем JiraDataFetcher с загруженными параметрами
            JiraDataFetcher jiraDataFetcher = new JiraDataFetcher(configLoader);

            // Задаем ключ задачи через переменную
            String issueKey = "KAN-1"; // Пример: установить ключ задачи Jira напрямую

            // Сохраняем задачу как текст
            jiraDataFetcher.fetchIssueData(issueKey);
            System.out.println("Данные задачи успешно сохранены как текст.");

            // Сохраняем задачу в формате XML
            XmlDownloader.fetchIssueXml(issueKey);
            System.out.println("Данные задачи успешно сохранены в формате XML.");

            // Сохраняем задачу в формате JSON
            JsonDownloader.fetchIssueJson(issueKey);
            System.out.println("Данные задачи успешно сохранены в формате JSON.");

        } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
