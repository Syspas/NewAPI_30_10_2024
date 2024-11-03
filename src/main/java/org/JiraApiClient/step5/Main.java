package org.JiraApiClient.step5;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Создаём экземпляр JsonParser
        JsonParser jsonParser = new JsonParser();

        try {
            // Укажите путь к вашему JSON-файлу
            String filePath = "src/main/resources/KAN-3.json"; // Замените на актуальный путь к файлу
            Task task = jsonParser.parseJson(filePath); // Вызов метода parseJson

            // Вывод информации о задаче
            System.out.println("Ключ задачи: " + task.getKey());
            System.out.println("Название: " + task.getFields().getSummary()); // Изменено на getFields()
            System.out.println("Описание: " + task.getFields().getDescription());
            System.out.println("Статус: " + task.getFields().getStatus().getName());
            System.out.println("Исполнитель: " + (task.getFields().getAssignee() != null ? task.getFields().getAssignee().getDisplayName() : "Не назначен"));
            System.out.println("Создатель задачи: " + (task.getFields().getCreator() != null ? task.getFields().getCreator().getDisplayName() : "Не указан"));
            System.out.println("Дата создания: " + task.getFields().getCreated());
            System.out.println("Дата обновления: " + task.getFields().getUpdated());
        } catch (IOException e) {
            System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
        }
    }
}
