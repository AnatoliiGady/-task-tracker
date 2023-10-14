package org.example.manager;

import org.example.InMemoryTaskDao;
import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;
import org.example.taskDao.TaskDao;

import java.util.ArrayList;
import java.util.List;

public class ManagerImpl implements Manager {

    private final TaskDao taskDao = new InMemoryTaskDao();

    @Override
    public int add(Task task) {
        task.setStatus(Status.NEW);
        int id = taskDao.add(task);
        return id;
    }

    @Override
    public int add(Epic epic) {
        int id = taskDao.add(epic);
        return id;
    }

    @Override
    public int add(SubTask subTask) {
        subTask.setStatus(Status.NEW);
        int id = taskDao.add(subTask);
        return id;
    }

    @Override
    public boolean removeTaskById(int id) {
        taskDao.removeTaskById(id);
        return true;
    }

    @Override
    public boolean removeEpicById(int id) {
        taskDao.removeEpicById(id);
        return true;
    }

    @Override
    public boolean removeSubTaskById(int id) {
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
    public void update(Task task) {
        taskDao.update(task);
    }

    @Override
    public void update(Epic epic) {
        taskDao.update(epic);
    }

    @Override
    public void update(SubTask subTask) {
        taskDao.update(subTask);
    }

    @Override
    public Status getEpicStatusById(int id) {
        Epic epicById = taskDao.getEpicById(id);
        List<Integer> idSubTasks = epicById.getIdSubTasks();
        List<SubTask> subTasks = new ArrayList<>();
        for (Integer idSubTask : idSubTasks) {
            subTasks.add(getSubTaskById(idSubTask));
        }
        int countInProgress = 0;
        int countNew = 0;
        int countDone = 0;
        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() == Status.IN_PROGRESS) {
                countInProgress++;
            }
            if (subTask.getStatus() == Status.DONE) {
                countDone++;
            }
            if (subTask.getStatus() == Status.NEW) {
                countNew++;
            }
        }
        if (countInProgress > 0) {
            return Status.IN_PROGRESS;
        }
        if (countDone == idSubTasks.size()) {
            return Status.DONE;
        } else {
            return Status.NEW;
        }
    }
}
