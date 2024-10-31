package org.JiraApiClient.step3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssue {
    private String key;
    private JiraFields fields; // Измените здесь на JiraFields

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public JiraFields getFields() { // Измените здесь на JiraFields
        return fields;
    }

    public void setFields(JiraFields fields) { // Измените здесь на JiraFields
        this.fields = fields;
    }
}
