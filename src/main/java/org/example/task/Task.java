package org.example.task;

public class Task extends AbstractTask {

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
