package org.JiraApiClient.step5;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;

public class JsonParser {
    private ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    public Task parseJson(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), Task.class);
    }
}
