package org.example;

import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;
import org.example.taskDao.TaskDao;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskDao implements TaskDao {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();
    private int idGeneratorTask = 0;
    private int idGeneratorEpic = 0;
    private int idGeneratorSubTask = 0;

    @Override
    public int add(Task task) {
        tasks.put(idGeneratorTask, task);
        task.setId(idGeneratorTask);
        idGeneratorTask++;
        return task.getId();
    }

    @Override
    public int add(Epic epic) {
        epics.put(idGeneratorEpic, epic);
        epic.setId(idGeneratorEpic);
        idGeneratorEpic++;
        return epic.getId();
    }

    @Override
    public int add(SubTask subTask) {
        subTasks.put(idGeneratorSubTask++, subTask);
        subTask.setId(idGeneratorSubTask);
        idGeneratorSubTask++;
        return subTask.getId();
    }

    @Override
    public boolean removeTaskById(int id) {
        tasks.remove(id);
        return true;
    }

    @Override
    public boolean removeEpicById(int id) {
        epics.remove(id);
        return true;
    }

    @Override
    public boolean removeSubTaskById(int id) {
        subTasks.remove(id);
        return true;
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    @Override
    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
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

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public Map<Integer, Epic> getEpics() {
        return epics;
    }

    public Map<Integer, SubTask> getSubTasks() {
        return subTasks;
    }

    public int getIdGeneratorTask() {
        return idGeneratorTask;
    }

    public void setIdGeneratorTask(int idGeneratorTask) {
        this.idGeneratorTask = idGeneratorTask;
    }

    public int getIdGeneratorEpic() {
        return idGeneratorEpic;
    }

    public void setIdGeneratorEpic(int idGeneratorEpic) {
        this.idGeneratorEpic = idGeneratorEpic;
    }

    public int getIdGeneratorSubTask() {
        return idGeneratorSubTask;
    }

    public void setIdGeneratorSubTask(int idGeneratorSubTask) {
        this.idGeneratorSubTask = idGeneratorSubTask;
    }

    @Override
    public String toString() {
        return "InMemoryTaskDao{"
                + "tasks=" + tasks
                + ", epics=" + epics
                + ", subTasks=" + subTasks
                + '}';
    }
}
