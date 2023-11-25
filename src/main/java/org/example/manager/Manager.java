package org.example.manager;

import org.example.dto.*;
import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;

public interface Manager {
    int add(TaskDto taskDto);

    int add(EpicDto epicDto);

    int add(SubTaskDto subTaskDto);

    boolean removeTaskById(int id);

    boolean removeEpicById(int id);

    boolean removeSubTaskById(int id);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubTaskById(int id);

    void update(TaskDto taskDto);

    void update(EpicDto epicDto);

    void update(SubTaskDto subTaskDto);
    Status getEpicStatusById(int id);
}
