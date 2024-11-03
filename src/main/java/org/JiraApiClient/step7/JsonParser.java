package org.JiraApiClient.step7;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.JiraApiClient.step7.date.Task;

import java.io.IOException;

/**
 * Класс для парсинга JSON-данных в объект Task.
 */
public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    public Task parseJsonFromUrl(String json) throws IOException {
        return objectMapper.readValue(json, Task.class);
    }
}
