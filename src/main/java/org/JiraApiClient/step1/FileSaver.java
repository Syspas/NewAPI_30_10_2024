package org.JiraApiClient.step1;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс для сохранения данных в файл.
 */
public class FileSaver {

    /**
     * Сохраняет строку в файл.
     *
     * @param filename Имя файла
     * @param data Данные для записи
     */
    public static void saveToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
