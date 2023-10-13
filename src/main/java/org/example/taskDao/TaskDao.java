package org.example.taskDao;

import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;

public interface TaskDao {
    int add(Task task);

    int add(Epic epic);

    int add(SubTask subTask);

    boolean removeTaskById(int id);

    boolean removeEpicById(int id);

    boolean removeSubTaskById(int id);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubTaskById(int id);

    void update(Task task);

    void update(Epic epic);

    void update(SubTask subTask);
}
