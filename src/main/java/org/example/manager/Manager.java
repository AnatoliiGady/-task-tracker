package org.example.manager;

import org.example.taskDao.TaskDao;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;

import java.util.HashMap;
import java.util.Map;

public class Manager implements TaskDao {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();


    @Override
    public int add(Task task) {
        tasks.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int add(Epic epic) {
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    @Override
    public int add(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        return subTask.getId();
    }

    @Override
    public boolean removeTaskById(Task id) {
        boolean rsl = false;
        for (Map.Entry<Integer, Task> task : tasks.entrySet()) {
            if (task.getKey() == id.getId()) {
                tasks.remove(id.getId());
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public boolean removeEpicById(Epic id) {
        boolean rsl = false;
        for (Map.Entry<Integer, Epic> epic : epics.entrySet()) {
            if (epic.getKey() == id.getId()) {
                epics.remove(id.getId());
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public boolean removeSubTaskById(SubTask id) {
        boolean rsl = false;
        for (Map.Entry<Integer, SubTask> subTask : subTasks.entrySet()) {
            if (subTask.getKey() == id.getId()) {
                subTasks.remove(id.getId());
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Task getTaskById(int id) {
        for (Map.Entry<Integer, Task> task : tasks.entrySet()) {
            if (task.getKey() == id) {
                return tasks.get(id);
            }
        }
        return null;
    }

    @Override
    public Epic getEpicById(int id) {
        for (Map.Entry<Integer, Epic> epic : epics.entrySet()) {
            if (epic.getKey() == id) {
                return epics.get(id);
            }
        }
        return null;
    }

    @Override
    public SubTask getSubTaskById(int id) {
        for (Map.Entry<Integer, SubTask> subTask : subTasks.entrySet()) {
            if (subTask.getKey() == id) {
                return subTasks.get(id);
            }
        }
        return null;
    }

    @Override
    public void update(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void update(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    @Override
    public void update(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
    }

    @Override
    public String toString() {
        return "Manager{"
                + "tasks=" + tasks
                + ", epics=" + epics
                + ", subTasks=" + subTasks
                + '}';
    }
}
