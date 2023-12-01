package org.example;

import org.example.dto.EpicDto;
import org.example.dto.SubTaskDto;
import org.example.dto.TaskDto;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;

public class TaskConverter {
    public static Task convertToTask(TaskDto taskDto) {
        return new Task() {{
            setId(taskDto.getId());
            setDescription(taskDto.getDescription());
            setTitle(taskDto.getTitle());
            setStatus(taskDto.getStatus());
        }};
    }
    public static Epic convertToEpic(EpicDto epic) {
        return new Epic() {{
            setId(epic.getId());
            setDescription(epic.getDescription());
            setTitle(epic.getTitle());
            setStatus(epic.getStatus());
        }};
    }

    public static SubTask convertToSubTask(SubTaskDto subTask) {
        return new SubTask() {{
            setId(subTask.getId());
            setDescription(subTask.getDescription());
            setTitle(subTask.getTitle());
            setStatus(subTask.getStatus());
            setEpicId(subTask.getEpicId());
        }};
    }
}
