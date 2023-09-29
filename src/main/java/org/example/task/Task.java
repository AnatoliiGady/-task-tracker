package org.example.task;

public class Task extends AbstractTask {

    public Task(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Task{"
                + "id=" + getId()
                + ", title='" + getTitle() + '\''
                + ", description='" + getDescription() + '\''
                + ", status=" + getStatus()
                + '}';
    }
}
