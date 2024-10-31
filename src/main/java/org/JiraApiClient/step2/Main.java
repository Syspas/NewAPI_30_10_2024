package org.JiraApiClient.step2;

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

            // Создаем JsonDownloader с параметрами подключения
            JsonDownloader jsonDownloader = new JsonDownloader(
                    configLoader.getProperty("jiraUrl"),
                    configLoader.getProperty("jiraUsername"),
                    configLoader.getProperty("jiraApiToken")
            );

            // Задаем ключ задачи через переменную
            String issueKey = "KAN-1"; // Пример: установить ключ задачи Jira напрямую

            // Сохраняем задачу в формате JSON
            jsonDownloader.fetchIssueJson(issueKey);
            System.out.println("Данные задачи успешно сохранены в формате JSON.");

        } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
