package org.example.dto;

import org.example.status.Status;

public class SubTaskDto {
    private Integer id;
    private String title;
    private String description;
    private Status status;
    private Integer epicId;

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskDto{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", status=" + status
                + '}';
    }
}
