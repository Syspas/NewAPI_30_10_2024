package org.JiraApiClient.step7;


import org.JiraApiClient.step7.date.Task;

public class Main {
    public static void main(String[] args) {
        // Ключ задачи
        String issueKey = "KAN-3"; // Замените на актуальный ключ задачи

        // Создаем экземпляр JsonDownloader
        JsonDownloader jsonDownloader = new JsonDownloader();

        // Получаем объект Task по ключу задачи
        Task task = jsonDownloader.fetchIssueJson(issueKey);

        if (task != null) {
            // Вывод информации о задаче
            System.out.println("Ключ задачи: " + task.getKey());
            System.out.println("Название: " + task.getFields().getSummary());
            System.out.println("Описание: " + task.getFields().getDescription());
            System.out.println("Статус: " + task.getFields().getStatus().getName());
            System.out.println("Исполнитель: " + (task.getFields().getAssignee() != null ? task.getFields().getAssignee().getDisplayName() : "Не назначен"));
            System.out.println("Создатель задачи: " + (task.getFields().getCreator() != null ? task.getFields().getCreator().getDisplayName() : "Не указан"));
            System.out.println("Дата создания: " + task.getFields().getCreated());
            System.out.println("Дата обновления: " + task.getFields().getUpdated());
        } else {
            System.err.println("Не удалось получить данные о задаче с ключом: " + issueKey);
        }
    }
}
