package org.JiraApiClient.step5;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
    private String summary;
    private String description;
    private Status status;
    private User assignee;
    private User creator;
    private String created;
    private String updated;

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("assignee")
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("creator")
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("updated")
    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
