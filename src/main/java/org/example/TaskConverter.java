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
    public static Epic convertToEpic(EpicDto epicDto) {
        return new Epic() {{
            setId(epicDto.getId());
            setDescription(epicDto.getDescription());
            setTitle(epicDto.getTitle());
            setStatus(epicDto.getStatus());
        }};
    }

    public static SubTask convertToSubTask(SubTaskDto subTaskDto) {
        return new SubTask() {{
            setId(subTaskDto.getId());
            setDescription(subTaskDto.getDescription());
            setTitle(subTaskDto.getTitle());
            setStatus(subTaskDto.getStatus());
            setEpicId(subTaskDto.getEpicId());
        }};
    }
}
