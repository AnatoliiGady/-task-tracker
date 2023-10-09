package org.example.manager;

import org.example.InMemoryTaskDao;
import org.example.taskDao.TaskDao;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;

import java.util.Map;

public class Manager implements TaskDao {

    private final InMemoryTaskDao memoryTaskDao = new InMemoryTaskDao();

    @Override
    public int add(Task task) {
        memoryTaskDao.getTasks().put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int add(Epic epic) {
        memoryTaskDao.getEpics().put(epic.getId(), epic);
        return epic.getId();
    }

    @Override
    public int add(SubTask subTask) {
        memoryTaskDao.getSubTasks().put(subTask.getId(), subTask);
        return subTask.getId();
    }

    @Override
    public boolean removeTaskById(Task id) {
        boolean rsl = false;
        for (Map.Entry<Integer, Task> task : memoryTaskDao.getTasks().entrySet()) {
            if (task.getKey() == id.getId()) {
                memoryTaskDao.getTasks().remove(id.getId());
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public boolean removeEpicById(Epic id) {
        boolean rsl = false;
        for (Map.Entry<Integer, Epic> epic : memoryTaskDao.getEpics().entrySet()) {
            if (epic.getKey() == id.getId()) {
                memoryTaskDao.getEpics().remove(id.getId());
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public boolean removeSubTaskById(SubTask id) {
        boolean rsl = false;
        for (Map.Entry<Integer, SubTask> subTask : memoryTaskDao.getSubTasks().entrySet()) {
            if (subTask.getKey() == id.getId()) {
                memoryTaskDao.getSubTasks().remove(id.getId());
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Task getTaskById(int id) {
        for (Map.Entry<Integer, Task> task : memoryTaskDao.getTasks().entrySet()) {
            if (task.getKey() == id) {
                return memoryTaskDao.getTasks().get(id);
            }
        }
        return null;
    }

    @Override
    public Epic getEpicById(int id) {
        for (Map.Entry<Integer, Epic> epic : memoryTaskDao.getEpics().entrySet()) {
            if (epic.getKey() == id) {
                return memoryTaskDao.getEpics().get(id);
            }
        }
        return null;
    }

    @Override
    public SubTask getSubTaskById(int id) {
        for (Map.Entry<Integer, SubTask> subTask : memoryTaskDao.getSubTasks().entrySet()) {
            if (subTask.getKey() == id) {
                return memoryTaskDao.getSubTasks().get(id);
            }
        }
        return null;
    }

    @Override
    public void update(Task task) {
        memoryTaskDao.getTasks().put(task.getId(), task);
    }

    @Override
    public void update(Epic epic) {
        memoryTaskDao.getEpics().put(epic.getId(), epic);
    }

    @Override
    public void update(SubTask subTask) {
        memoryTaskDao.getSubTasks().put(subTask.getId(), subTask);
    }

    @Override
    public String toString() {
        return "Manager{"
                + "memoryTaskDao=" + memoryTaskDao
                + '}';
    }
}
