package org.JiraApiClient.step4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс для сохранения текстовых данных в файл.
 */
public class FileSaver {

    /**
     * Сохраняет переданные данные в указанный файл.
     *
     * @param fileName имя файла, в который будут сохранены данные
     * @param data     текстовые данные для записи в файл
     */
    public static void saveToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }
}
