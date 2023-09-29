package org.example;

public interface TaskDao {
    int add(Task task);

    int add(Epic epic);

    int add(SubTask subTask);

    boolean removeTaskById(Task id);

    boolean removeEpicById(Epic id);

    boolean removeSubTaskById(SubTask id);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubTaskById(int id);

    int update(Task task);

    int update(Epic epic);

    int update(SubTask subTask);
}
