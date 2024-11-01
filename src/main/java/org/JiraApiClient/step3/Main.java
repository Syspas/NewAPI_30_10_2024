package org.JiraApiClient.step3;


public class Main {
    public static void main(String[] args) {
        String configFilePath = "config.properties"; // Имя файла конфигурации
        String issueKey = "KAN-1"; // Замените на нужный ключ задачи

        try {
            JsonDownloader jsonDownloader = new JsonDownloader(configFilePath);
            jsonDownloader.fetchIssueJson(issueKey);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка конфигурации: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}