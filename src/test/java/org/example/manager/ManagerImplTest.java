package org.example.manager;

import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ManagerImplTest {

    ManagerImpl manager = new ManagerImpl();
    Epic epic = new Epic();
    Task task = new Task();
    Task task1 = new Task();
    SubTask subTask = new SubTask();
    SubTask subTask1 = new SubTask();

    @Test
    void whenIdTask1() {
        manager.add(task);
        manager.add(task1);
        assertThat(task1.getId()).isEqualTo(1);
    }

    @Test
    void whenIdEpic0() {
        manager.add(epic);
        assertThat(epic.getId()).isEqualTo(0);
    }

    @Test
    void whenIdSubTask0() {
        manager.add(epic);
        subTask.setEpicId(epic.getId());
        subTask1.setEpicId(epic.getId());
        manager.add(subTask);
        manager.add(subTask1);
        assertThat(subTask.getId()).isEqualTo(0);
        assertThat(subTask1.getId()).isEqualTo(1);
    }

    @Test
    void statusTaskNew() {
        manager.add(task);
        assertThat(task.getStatus()).isEqualTo(Status.NEW);
    }

    @Test
    void statusTaskNewReplaceDone() {
        manager.add(task);
        task.setStatus(Status.DONE);
        assertThat(task.getStatus()).isEqualTo(Status.DONE);
    }

    @Test
    void statusSubTaskNew() {
        manager.add(epic);
        subTask.setEpicId(epic.getId());
        manager.add(subTask);
        assertThat(subTask.getStatus()).isEqualTo(Status.NEW);
    }

    @Test
    void statusSubTaskNewReplaceInProgress() {
        manager.add(epic);
        subTask.setEpicId(epic.getId());
        manager.add(subTask);
        subTask.setStatus(Status.IN_PROGRESS);
        assertThat(subTask.getStatus()).isEqualTo(Status.IN_PROGRESS);
    }

    @Test
    void statusEpicNewWhenSubTaskNEW() {
        manager.add(epic);
        subTask.setEpicId(epic.getId());
        manager.add(subTask);
        assertThat(manager.getEpicStatusByIdV2(epic.getId())).isEqualTo(Status.NEW);
    }

    @Test
    void statusEpicInProgressWhenSubTaskOneOfTheTaskInProgress() {
        manager.add(epic);
        subTask.setEpicId(epic.getId());
        subTask1.setEpicId(epic.getId());
        manager.add(subTask);
        manager.add(subTask1);
        subTask1.setStatus(Status.IN_PROGRESS);
        assertThat(manager.getEpicStatusByIdV2(epic.getId())).isEqualTo(Status.IN_PROGRESS);
    }

    @Test
    void statusEpicDoneWhenAllSubTaskDone() {
        manager.add(epic);
        subTask.setEpicId(epic.getId());
        subTask1.setEpicId(epic.getId());
        manager.add(subTask);
        manager.add(subTask1);
        subTask.setStatus(Status.DONE);
        subTask1.setStatus(Status.DONE);
        assertThat(manager.getEpicStatusByIdV2(epic.getId())).isEqualTo(Status.DONE);
    }

    @Test
    void removeTaskByIdTrue() {
        manager.add(task);
        assertThat(manager.removeTaskById(task.getId())).isTrue();
    }

    @Test
    void removeEpicByIdTrue() {
        manager.add(epic);
        assertThat(manager.removeEpicById(epic.getId())).isTrue();

    }

    @Test
    void removeSubTaskByIdTrue() {
        manager.add(epic);
        subTask.setEpicId(epic.getId());
        subTask1.setEpicId(epic.getId());
        manager.add(subTask);
        manager.add(subTask1);
        assertThat(epic.getIdSubTasks().size()).isEqualTo(2);
        manager.removeSubTaskById(subTask.getId());
        assertThat(epic.getIdSubTasks().size()).isEqualTo(1);
    }

}