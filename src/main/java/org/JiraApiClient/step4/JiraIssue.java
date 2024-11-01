package org.JiraApiClient.step4;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Класс для представления задачи в Jira.
 * Используется для десериализации JSON-ответа с данными о задаче.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssue {

    /** Уникальный ключ задачи в Jira (например, "PROJ-123"). */
    private String key;

    /** Поля задачи, содержащие подробную информацию. */
    private JiraFields fields;

    /**
     * Возвращает уникальный ключ задачи.
     *
     * @return ключ задачи в Jira
     */
    public String getKey() {
        return key;
    }

    /**
     * Устанавливает уникальный ключ задачи.
     *
     * @param key ключ задачи в Jira
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Возвращает объект {@link JiraFields}, содержащий поля задачи.
     *
     * @return поля задачи
     */
    public JiraFields getFields() {
        return fields;
    }

    /**
     * Устанавливает объект {@link JiraFields}, содержащий поля задачи.
     *
     * @param fields поля задачи
     */
    public void setFields(JiraFields fields) {
        this.fields = fields;
    }
}
