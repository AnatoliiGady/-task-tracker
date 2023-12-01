package org.example.manager;

import org.example.dto.EpicDto;
import org.example.dto.SubTaskDto;
import org.example.dto.TaskDto;
import org.example.status.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class ManagerTest {
    private final Manager manager;


    public ManagerTest(Manager manager) {
        this.manager = manager;
    }

    @Test
    void addTaskDtoThenTaskId0() {
        TaskDto taskDto = new TaskDto();
        int taskId = manager.add(taskDto);
        Assertions.assertEquals(0, manager.getTaskById(taskId).getId());
    }

    @Test
    void addTwoTuskDtoThenFirstTaskId0AndSecondTaskId1() {
        TaskDto taskDto = new TaskDto();
        TaskDto taskDto1 = new TaskDto();
        int taskId = manager.add(taskDto);
        int taskId1 = manager.add(taskDto1);
        manager.add(taskDto);
        manager.add(taskDto1);
        Assertions.assertEquals(0, manager.getTaskById(taskId).getId());
        Assertions.assertEquals(1, manager.getTaskById(taskId1).getId());
    }

    @Test
    void addEpicDtoThenEpicId0() {
        EpicDto epicDto = new EpicDto();
        int epicId = manager.add(epicDto);
        Assertions.assertEquals(0, manager.getEpicById(epicId).getId());
    }

    @Test
    void addTwoSubTasksThenSize2() {
        EpicDto epicDto = new EpicDto();
        SubTaskDto subTaskDto = new SubTaskDto();
        SubTaskDto subTaskDto1 = new SubTaskDto();
        int epicId = manager.add(epicDto);
        subTaskDto.setEpicId(manager.getEpicById(epicId).getId());
        subTaskDto1.setEpicId(manager.getEpicById(epicId).getId());
        manager.add(subTaskDto);
        manager.add(subTaskDto1);
        Assertions.assertEquals(2, manager.getEpicById(epicId).getIdSubTasks().size());

    }

    @Test
    void removeTaskByIdThenTrue() {
        TaskDto taskDto = new TaskDto();
        int taskId = manager.add(taskDto);
        Assertions.assertNotNull(manager.getTaskById(taskId));
        Assertions.assertTrue(manager.removeTaskById(taskId));
    }

    @Test
    void removeTaskByIdNotCorrectIdThenFalse() {
        TaskDto taskDto = new TaskDto();
        int taskId = manager.add(taskDto);
        Assertions.assertNotNull(manager.getTaskById(taskId));
        Assertions.assertFalse(manager.removeTaskById(1));
    }

    @Test
    void removeEpicDyIdThenTrue() {
        EpicDto epicDto = new EpicDto();
        int epicId = manager.add(epicDto);
        Assertions.assertNotNull(manager.getEpicById(epicId));
        Assertions.assertTrue(manager.removeEpicById(epicId));
        Assertions.assertNull(manager.getEpicById(epicId));
    }

    @Test
    void removeEpicByIdNotCorrectIdThenFalse() {
        EpicDto epicDto = new EpicDto();
        int epicId = manager.add(epicDto);
        Assertions.assertNotNull(manager.getEpicById(epicId));
        Assertions.assertFalse(manager.removeEpicById(1));
    }

    @Test
    void removeSubTaskByIdTrue() {
        EpicDto epicDto = new EpicDto();
        SubTaskDto subTaskDto = new SubTaskDto();
        SubTaskDto subTaskDto1 = new SubTaskDto();
        int epicId = manager.add(epicDto);
        subTaskDto.setEpicId(epicId);
        subTaskDto1.setEpicId(epicId);
        int subTaskId = manager.add(subTaskDto);
        int subTaskId1 = manager.add(subTaskDto1);
        Assertions.assertEquals(2, manager.getEpicById(epicId).getIdSubTasks().size());
        Assertions.assertTrue(manager.removeSubTaskById(subTaskId));
        Assertions.assertEquals(1, manager.getEpicById(epicId).getIdSubTasks().size());
    }

    @Test
    void removeSubTaskByIdNotCorrectIdThenFalse() {
        EpicDto epicDto = new EpicDto();
        SubTaskDto subTaskDto = new SubTaskDto();
        SubTaskDto subTaskDto1 = new SubTaskDto();
        int epicId = manager.add(epicDto);
        subTaskDto.setEpicId(epicId);
        subTaskDto1.setEpicId(epicId);
        manager.add(subTaskDto);
        manager.add(subTaskDto1);
        Assertions.assertEquals(2, manager.getEpicById(epicId).getIdSubTasks().size());
        Assertions.assertFalse(manager.removeSubTaskById(3));
        Assertions.assertEquals(2, manager.getEpicById(epicId).getIdSubTasks().size());
    }

    @Test
    void statusTaskNew() {
        TaskDto taskDto = new TaskDto();
        int taskId = manager.add(taskDto);
        Assertions.assertEquals(Status.NEW, manager.getTaskById(taskId).getStatus());
    }

    @Test
    void statusTaskNewReplaceDone() {
        TaskDto taskDto = new TaskDto();
        int taskId = manager.add(taskDto);
        Assertions.assertEquals(Status.NEW, manager.getTaskById(taskId).getStatus());
        manager.getTaskById(taskId).setStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE, manager.getTaskById(taskId).getStatus());
    }

    @Test
    void statusSubTaskNew() {
        EpicDto epicDto = new EpicDto();
        int epicId = manager.add(epicDto);
        SubTaskDto subTaskDto = new SubTaskDto();
        subTaskDto.setEpicId(epicId);
        int subTaskId = manager.add(subTaskDto);
        Assertions.assertEquals(Status.NEW, manager.getSubTaskById(subTaskId).getStatus());
    }

    @Test
    void statusSubTaskNewReplaceInProgress() {
        EpicDto epicDto = new EpicDto();
        int epicId = manager.add(epicDto);
        SubTaskDto subTaskDto = new SubTaskDto();
        subTaskDto.setEpicId(epicId);
        int subTaskId = manager.add(subTaskDto);
        Assertions.assertEquals(Status.NEW, manager.getSubTaskById(subTaskId).getStatus());
        manager.getSubTaskById(subTaskId).setStatus(Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, manager.getSubTaskById(subTaskId).getStatus());
    }

    @Test
    void statusEpicNewWhenSubTaskNEW() {
        EpicDto epicDto = new EpicDto();
        SubTaskDto subTaskDto = new SubTaskDto();
        int epicId = manager.add(epicDto);
        subTaskDto.setEpicId(epicId);
        int subTaskId = manager.add(subTaskDto);
        Assertions.assertEquals(Status.NEW, manager.getEpicStatusById(epicId));
    }

    @Test
    void statusEpicInProgressWhenSubTaskOneOfTheTaskInProgress() {
        EpicDto epicDto = new EpicDto();
        SubTaskDto subTaskDto = new SubTaskDto();
        SubTaskDto subTaskDto1 = new SubTaskDto();
        int epicId = manager.add(epicDto);
        subTaskDto.setEpicId(epicId);
        subTaskDto1.setEpicId(epicId);
        int subTaskId = manager.add(subTaskDto);
        manager.getSubTaskById(subTaskId).setStatus(Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, manager.getEpicStatusById(epicId));
    }

    @Test
    void statusEpicDoneWhenAllSubTaskDone() {
        EpicDto epicDto = new EpicDto();
        SubTaskDto subTaskDto = new SubTaskDto();
        SubTaskDto subTaskDto1 = new SubTaskDto();
        int epicId = manager.add(epicDto);
        subTaskDto.setEpicId(epicId);
        subTaskDto1.setEpicId(epicId);
        int subTaskId = manager.add(subTaskDto);
        int subTaskId1 = manager.add(subTaskDto1);
        Assertions.assertEquals(Status.NEW, manager.getEpicStatusById(epicId));
        manager.getSubTaskById(subTaskId).setStatus(Status.DONE);
        manager.getSubTaskById(subTaskId1).setStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE, manager.getSubTaskById(epicId).getStatus());
    }
}