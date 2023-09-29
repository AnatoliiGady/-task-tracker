package org.example.task;

public class SubTask extends AbstractTask {

    private final Integer epicId;

    public SubTask(int id, Integer epicId) {
        super(id);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask{"
                + "id=" + getId()
                + ", title='" + getTitle() + '\''
                + ", description='" + getDescription() + '\''
                + ", status=" + getStatus()
                + "epic=" + epicId
                + '}';
    }
}
