package org.JiraApiClient.step4;


/**
 * Главный класс приложения для загрузки данных задачи из Jira.
 * Загружает конфигурацию из файла и использует её для выполнения запроса к API Jira.
 */
public class Main {
    /**
     * Основной метод программы, который создает объект JsonDownloader
     * и загружает JSON-данные задачи из Jira с указанным ключом.
     *
     * @param args аргументы командной строки (не используются в данной программе)
     */
    public static void main(String[] args) {
        String configFilePath = "config.properties"; // Имя файла конфигурации
        String issueKey = "KAN-1"; // Замените на нужный ключ задачи

        try {
            // Создание экземпляра JsonDownloader с указанным файлом конфигурации
            JsonDownloader jsonDownloader = new JsonDownloader(configFilePath);

            // Запрос JSON-данных задачи с указанным ключом
            jsonDownloader.fetchIssueJson(issueKey);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка конфигурации: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
