package org.example.manager;

import org.example.UpdateTaskDto;
import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class ManagerTest {
    private final Manager manager;

    private Epic epic = new Epic();
    private Task task = new Task();
    private Task task1 = new Task();
    private SubTask subTask = new SubTask();
    private SubTask subTask1 = new SubTask();

    public ManagerTest(Manager manager) {
        this.manager = manager;
    }

    @Test
    void addTwoTaskThenIdFirstTask0AndIdSecondTask1() {
        manager.add(task);
        manager.add(task1);
        Assertions.assertEquals(0, manager.getTaskById(task.getId()).getId());
        Assertions.assertEquals(1, manager.getTaskById(task1.getId()).getId());
    }

    @Test
    void addEpicThenIdEpic0() {
        manager.add(epic);
        Assertions.assertEquals(0, manager.getEpicById(epic.getId()).getId());
    }

    @Test
    void addTwoSubTaskThenIdFirstSubTask0AndIdSecondSubTask1() {
        manager.add(epic);
        subTask.setEpicId(manager.getEpicById(epic.getId()).getId());
        subTask1.setEpicId(manager.getEpicById(epic.getId()).getId());
        manager.add(subTask);
        manager.add(subTask1);
        Assertions.assertEquals(0, manager.getSubTaskById(subTask.getId()).getId());
        Assertions.assertEquals(1, manager.getSubTaskById(subTask1.getId()).getId());
    }

    @Test
    void statusTaskNew() {
        manager.add(task);
        Assertions.assertEquals(Status.NEW, manager.getTaskById(task.getId()).getStatus());
    }

    @Test
    void statusTaskNewReplaceDone() {
        manager.add(task);
        manager.getTaskById(task.getId()).setStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE, manager.getTaskById(task.getId()).getStatus());
    }

    @Test
    void statusSubTaskNew() {
        manager.add(epic);
        subTask.setEpicId(manager.getEpicById(epic.getId()).getId());
        manager.add(subTask);
        Assertions.assertEquals(Status.NEW, manager.getSubTaskById(subTask.getId()).getStatus());
    }

    @Test
    void statusSubTaskNewReplaceInProgress() {
        manager.add(epic);
        subTask.setEpicId(manager.getEpicById(epic.getId()).getId());
        manager.add(subTask);
        manager.getSubTaskById(subTask.getId()).setStatus(Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, manager.getSubTaskById(subTask.getId()).getStatus());
    }

    @Test
    void statusEpicNewWhenSubTaskNEW() {
        manager.add(epic);
        subTask.setEpicId(manager.getEpicById(epic.getId()).getId());
        manager.add(subTask);
        Assertions.assertEquals(Status.NEW, manager.getEpicStatusById(epic.getId()));
    }

    @Test
    void statusEpicInProgressWhenSubTaskOneOfTheTaskInProgress() {
        manager.add(epic);
        subTask.setEpicId(manager.getEpicById(epic.getId()).getId());
        subTask1.setEpicId(manager.getEpicById(epic.getId()).getId());
        manager.add(subTask);
        manager.add(subTask1);
        manager.getSubTaskById(subTask.getId()).setStatus(Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, manager.getEpicStatusById(epic.getId()));
    }

    @Test
    void statusEpicDoneWhenAllSubTaskDone() {
        manager.add(epic);
        subTask.setEpicId(manager.getEpicById(epic.getId()).getId());
        subTask1.setEpicId(manager.getEpicById(epic.getId()).getId());
        manager.add(subTask);
        manager.add(subTask1);
        manager.getSubTaskById(subTask.getId()).setStatus(Status.DONE);
        manager.getSubTaskById(subTask1.getId()).setStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE, manager.getEpicStatusById(epic.getId()));
    }

    @Test
    void removeTaskByIdTrue() {
        manager.add(task);
        manager.removeTaskById(task.getId());
        Assertions.assertNull(manager.getTaskById(task.getId()));
    }

    @Test
    void removeEpicByIdTrue() {
        manager.add(epic);
        manager.removeEpicById(epic.getId());
        Assertions.assertNull(manager.getEpicById(epic.getId()));
    }

    @Test
    void removeSubTaskByIdTrue() {
        manager.add(epic);
        subTask.setEpicId(manager.getEpicById(epic.getId()).getId());
        subTask1.setEpicId(manager.getEpicById(epic.getId()).getId());
        manager.add(subTask);
        manager.add(subTask1);
        Assertions.assertEquals(2, manager.getEpicById(epic.getId()).getIdSubTasks().size());
        manager.removeSubTaskById(subTask.getId());
        Assertions.assertEquals(1, manager.getEpicById(epic.getId()).getIdSubTasks().size());
    }

    @Test
    void updateTaskDescriptionAndTitle() {
        manager.add(task);
        Assertions.assertNull(manager.getTaskById(task.getId()).getDescription());
        Assertions.assertNull(manager.getTaskById(task.getId()).getTitle());
        UpdateTaskDto updateTaskDto = new UpdateTaskDto();
        updateTaskDto.setDescription("Стать программистом");
        updateTaskDto.setTitle("Начать учить язык программирования");
        manager.update(updateTaskDto);
        manager.getTaskById(task.getId()).setDescription(updateTaskDto.getDescription());
        manager.getTaskById(task.getId()).setTitle(updateTaskDto.getTitle());
        Assertions.assertEquals("Стать программистом",
                manager.getTaskById(task.getId()).getDescription());
        Assertions.assertEquals("Начать учить язык программирования",
                manager.getTaskById(task.getId()).getTitle());
    }

    @Test
    void updateEpicDescriptionAndTitle() {
        manager.add(epic);
        Assertions.assertNull(epic.getDescription());
        Assertions.assertNull(epic.getTitle());

    }

}