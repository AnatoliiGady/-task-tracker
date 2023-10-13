package org.example.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends AbstractTask {
    private final List<Integer> idSubTasks = new ArrayList<>();

    public void addSubTaskId(Integer idSubTask) {
        this.idSubTasks.add(idSubTask);
    }

    public List<Integer> getIdSubTasks() {
        return idSubTasks;
    }

    @Override
    public String toString() {
        return "Epic{"
                + "id=" + getId()
                + ", title='" + getTitle() + '\''
                + ", description='" + getDescription() + '\''
                + ", status=" + getStatus()
                + "subTasks=" + idSubTasks
                + '}';
    }
}
