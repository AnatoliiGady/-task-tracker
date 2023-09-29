package org.example;

import java.util.ArrayList;
import java.util.List;

public class Manager<T> {
    private List<AbstractTask> tasks = new ArrayList<>();
    private int identifier = 1;

    public void add(AbstractTask task) {
        tasks.add(task);
    }

    public void remove(AbstractTask task) {
        tasks.clear();
    }

    public int creationNum() {
        return identifier++;
    }

    public int getIdentifier() {
        return identifier;
    }

    public List<AbstractTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Manager{"
                + "tasks=" + tasks
                + '}';
    }
}
