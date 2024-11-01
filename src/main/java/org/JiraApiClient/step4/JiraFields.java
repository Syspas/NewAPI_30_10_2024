package org.JiraApiClient.step4;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Класс для представления полей задачи Jira.
 * Используется для десериализации JSON-ответа с данными о задаче.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraFields {

    /** Краткое описание задачи. */
    private String summary;

    /** Описание задачи. */
    private String description;

    /** Дата изменения категории статуса задачи. */
    private String statuscategorychangedate;

    // Добавьте другие необходимые поля

    /**
     * Возвращает краткое описание задачи.
     *
     * @return краткое описание задачи
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Устанавливает краткое описание задачи.
     *
     * @param summary краткое описание задачи
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Возвращает описание задачи.
     *
     * @return описание задачи
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание задачи.
     *
     * @param description описание задачи
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает дату изменения категории статуса задачи.
     *
     * @return дата изменения категории статуса задачи
     */
    public String getStatuscategorychangedate() {
        return statuscategorychangedate;
    }

    /**
     * Устанавливает дату изменения категории статуса задачи.
     *
     * @param statuscategorychangedate дата изменения категории статуса задачи
     */
    public void setStatuscategorychangedate(String statuscategorychangedate) {
        this.statuscategorychangedate = statuscategorychangedate;
    }
}
