package org.example.task;


import org.example.manager.ManagerImpl;
import org.example.status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Epic extends AbstractTask {
    private final List<Integer> idSubTasks = new ArrayList<>();

    public void addSubTaskId(Integer idSubTask) {
        this.idSubTasks.add(idSubTask);
    }

    public List<Integer> getIdSubTasks() {
        return idSubTasks;
    }

    @Override
    public void setStatus(Status status) {
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
