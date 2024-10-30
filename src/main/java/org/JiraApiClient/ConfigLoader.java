package org.JiraApiClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для загрузки конфигурации из файла config.properties.
 */
public class ConfigLoader {
    private final Properties properties = new Properties();

    /**
     * Конструктор загружает конфигурацию из файла.
     */
    public ConfigLoader() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Файл конфигурации 'config.properties' не найден");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке файла конфигурации", e);
        }
    }

    /**
     * Получает значение из конфигурации по ключу.
     *
     * @param key Ключ параметра
     * @return Значение параметра
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
