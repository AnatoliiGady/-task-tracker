package org.example.manager;

import org.example.InMemoryTaskDao;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;
import org.example.taskDao.TaskDao;

public class Manager {

    private final TaskDao taskDao = new InMemoryTaskDao();

    public int add(Task task) {
        int id = taskDao.add(task);
        return id;
    }

    public int add(Epic epic) {
        int id = taskDao.add(epic);
        return id;
    }

    public int add(SubTask subTask) {
        int id = taskDao.add(subTask);
        return id;
    }

    public boolean removeTaskById(int id) {
        taskDao.removeTaskById(id);
        return true;
    }

    public boolean removeEpicById(int id) {
        taskDao.removeEpicById(id);
        return true;
    }

    public boolean removeSubTask(int id) {
        taskDao.removeSubTaskById(id);
        return true;
    }
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }
    public Epic getEpicById(int id) {
        return taskDao.getEpicById(id);
    }
    public SubTask getSubTaskById(int id) {
        return taskDao.getSubTaskById(id);
    }
    public void update(Task task) {
        taskDao.update(task);
    }
    public void update(Epic epic) {
        taskDao.update(epic);
    }
    public void update(SubTask subTask) {
        taskDao.update(subTask);
    }
}
