package org.example.manager;

import org.example.*;
import org.example.dto.*;
import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;
import org.example.taskDao.TaskDao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerImpl implements Manager {

    private final TaskDao taskDao = new InMemoryTaskDao();

    @Override
    public int add(TaskDto taskDto) {
        Task task = TaskConverter.convertToTask(taskDto);
        task.setStatus(Status.NEW);
        int id = taskDao.add(task);
        return id;
    }

    @Override
    public int add(EpicDto epicDto) {
        Epic epic = TaskConverter.convertToEpic(epicDto);
        int id = taskDao.add(epic);
        return id;
    }

    @Override
    public int add(SubTaskDto subTaskDto) {
        SubTask subTask = TaskConverter.convertToSubTask(subTaskDto);
        subTask.setStatus(Status.NEW);
        int id = taskDao.add(subTask);
        Epic epicById = getEpicById(subTask.getEpicId());
        epicById.addSubTaskId(id);
        return id;
    }

    @Override
    public boolean removeTaskById(int id) {
        taskDao.removeTaskById(id);
        return true;
    }

    @Override
    public boolean removeEpicById(int id) {
        Epic epic = getEpicById(id);
        List<Integer> idSubtasks = epic.getIdSubTasks();
        for (Integer idSubTask : idSubtasks) {
            taskDao.removeSubTaskById(idSubTask);
        }
        taskDao.removeEpicById(id);
        return true;
    }

    @Override
    public boolean removeSubTaskById(int id) {
        SubTask subTask = getSubTaskById(id);
        Epic epic = getEpicById(subTask.getEpicId());
        List<Integer> idSubTasks = epic.getIdSubTasks();
        idSubTasks.remove(Integer.valueOf(id));
        taskDao.removeSubTaskById(id);
        return true;
    }

    @Override
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Override
    public Epic getEpicById(int id) {
        return taskDao.getEpicById(id);
    }

    @Override
    public SubTask getSubTaskById(int id) {
        return taskDao.getSubTaskById(id);
    }

    @Override
    public void update(TaskDto taskDto) {
        Task task = TaskConverter.convertToTask(taskDto);
        taskDao.update(task);
    }

    @Override
    public void update(EpicDto epicDto) {
        Epic epic = TaskConverter.convertToEpic(epicDto);
        taskDao.update(epic);
    }

    @Override
    public void update(SubTaskDto subTaskDto) {
        SubTask subTask = TaskConverter.convertToSubTask(subTaskDto);
        taskDao.update(subTask);
    }

    public Status getEpicStatusById(int id) {
        Map<Status, Long> res = getEpicById(id).getIdSubTasks().stream()
                .map(taskDao::getSubTaskById)
                .map(SubTask::getStatus)
                .collect(Collectors.groupingBy(status -> status, Collectors.counting()));

        int countOfStatuses = res.values().stream().mapToInt(Long::intValue).sum();

        if (res.getOrDefault(Status.IN_PROGRESS, 0L) > 0) {
            return Status.IN_PROGRESS;
        } else if (res.getOrDefault(Status.DONE, 0L) == countOfStatuses) {
            return Status.DONE;
        } else {
            return Status.NEW;
        }
    }
}
