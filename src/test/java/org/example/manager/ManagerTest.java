package org.example.manager;

import org.example.dto.EpicDto;
import org.example.dto.SubTaskDto;
import org.example.dto.TaskDto;
import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;
import org.example.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class ManagerTest {
    private final Manager manager;


    public ManagerTest(Manager manager) {
        this.manager = manager;
    }

    @Test
    void addTaskDto() {
        TaskDto taskDtoForAdd = new TaskDto();
        taskDtoForAdd.setDescription("Попить воды");
        taskDtoForAdd.setTitle("Налить воды в стакан");
        int addedTaskId = manager.add(taskDtoForAdd);
        Task addedTask = manager.getTaskById(addedTaskId);
        Assertions.assertEquals(taskDtoForAdd.getDescription(), addedTask.getDescription());
        Assertions.assertEquals(taskDtoForAdd.getTitle(), addedTask.getTitle());
    }

    @Test
    void addEpicDto() {
        EpicDto epicDtoForAdd = new EpicDto();
        epicDtoForAdd.setDescription("Стать программистом");
        epicDtoForAdd.setTitle("Выбрать язык программирования, овладеть технологиями");
        int addedEpicId = manager.add(epicDtoForAdd);
        Epic addedEpic = manager.getEpicById(addedEpicId);

        Assertions.assertEquals(epicDtoForAdd.getDescription(), addedEpic.getDescription());
        Assertions.assertEquals(epicDtoForAdd.getTitle(), addedEpic.getTitle());
    }

    @Test
    void addSubTask() {
        EpicDto epicDtoForAdd = new EpicDto();
        int addedEpicId = manager.add(epicDtoForAdd);
        Epic addedEpicById = manager.getEpicById(addedEpicId);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setDescription("Начать учить язык программирования Java");
        subTaskDtoForAdd.setTitle("Записаться на курсы");
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);

        Assertions.assertEquals(subTaskDtoForAdd.getDescription(), addedSubTask.getDescription());
        Assertions.assertEquals(subTaskDtoForAdd.getTitle(), addedSubTask.getTitle());
        for (Integer idSubTask : manager.getEpicById(addedEpicId).getIdSubTasks()) {
            if (idSubTask == addedSubTaskId) {
                System.out.println("Да");
            } else {
                System.out.println("Нет");
            }
        }
        Assertions.assertEquals(addedSubTask.getId(), manager.getEpicById(addedEpicId).getIdSubTasks());
        Assertions.assertEquals(addedEpicId, manager.getSubTaskById(addedSubTaskId).getEpicId());
    }


    @Test
    void removeTaskById() {
        TaskDto taskDtoForAdd = new TaskDto();
        taskDtoForAdd.setDescription("Попить воды");
        taskDtoForAdd.setTitle("Налить воды в стакан");
        int addedTaskId = manager.add(taskDtoForAdd);
        Task addedTask = manager.getTaskById(addedTaskId);

        Assertions.assertNotNull(addedTask);
        Assertions.assertEquals(taskDtoForAdd.getDescription(), addedTask.getDescription());
        Assertions.assertEquals(taskDtoForAdd.getTitle(), addedTask.getTitle());

        manager.removeTaskById(addedTaskId);
        Assertions.assertNull(manager.getTaskById(addedTaskId));
    }

    @Test
    void removeTaskByIdNotSuccessful() {
        Assertions.assertFalse(manager.removeTaskById(1));
    }

    @Test
    void removeEpicDyId() {
        EpicDto epicDtoForAdd = new EpicDto();
        epicDtoForAdd.setDescription("Стать программистом");
        epicDtoForAdd.setTitle("Выбрать язык программирования, овладеть технологиями");
        int addedEpicId = manager.add(epicDtoForAdd);
        Epic addedEpic = manager.getEpicById(addedEpicId);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setDescription("Начать учить язык программирования Java");
        subTaskDtoForAdd.setTitle("Записаться на курсы");
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);

        Assertions.assertEquals(1, addedEpic.getIdSubTasks().size());
        Assertions.assertEquals(addedSubTaskId, addedEpic.getIdSubTasks().get(0));

        manager.removeEpicById(addedEpicId);

        Assertions.assertNull(manager.getEpicById(addedEpicId));
        Assertions.assertNull(manager.getSubTaskById(addedSubTaskId));
    }

    @Test
    void removeEpicByIdNotSuccessful() {
        Assertions.assertFalse(manager.removeEpicById(1));
    }

    @Test
    void removeSubTaskById() {
        EpicDto epicDtoForAdd = new EpicDto();
        epicDtoForAdd.setDescription("Стать программистом");
        epicDtoForAdd.setTitle("Выбрать язык программирования, овладеть технологиями");
        int addedEpicId = manager.add(epicDtoForAdd);
        Epic addedEpic = manager.getEpicById(addedEpicId);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setDescription("Начать учить язык программирования Java");
        subTaskDtoForAdd.setTitle("Записаться на курсы");
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);
        addedSubTask.setStatus(Status.IN_PROGRESS); // надо заменить изменения статуса через менеджер

        SubTaskDto subTaskDtoForAdd1 = new SubTaskDto();
        subTaskDtoForAdd1.setDescription("Начать учить английский язык");
        subTaskDtoForAdd1.setTitle("Нанять репетитора");
        subTaskDtoForAdd1.setEpicId(addedEpicId);
        int addedSubTaskId1 = manager.add(subTaskDtoForAdd1);
        SubTask addedSubTask1 = manager.getSubTaskById(addedSubTaskId1);

        Assertions.assertEquals(Status.IN_PROGRESS, addedSubTask.getStatus());
        Assertions.assertEquals(Status.NEW, addedSubTask1.getStatus());
        Assertions.assertEquals(Status.IN_PROGRESS, manager.getEpicStatusById(addedEpicId));

        manager.removeSubTaskById(addedSubTaskId);

        Assertions.assertNull(manager.getSubTaskById(addedSubTaskId));
        Assertions.assertNotNull(manager.getSubTaskById(addedSubTaskId1));
        Assertions.assertEquals(Status.NEW, manager.getEpicStatusById(addedEpicId));

    }

    @Test
    void removeSubTaskByIdNotSuccessfully() {
        Assertions.assertFalse(manager.removeSubTaskById(1));
    }

    @Test
    void statusTaskNew() {
        TaskDto taskDtoForAdd = new TaskDto();
        int addedTaskId = manager.add(taskDtoForAdd);

        Assertions.assertEquals(Status.NEW, manager.getTaskById(addedTaskId).getStatus());
    }

    @Test
    void statusTaskNewReplaceDone() {
        TaskDto taskDtoForAdd = new TaskDto();
        int addedTaskId = manager.add(taskDtoForAdd);

        Assertions.assertEquals(Status.NEW, manager.getTaskById(addedTaskId).getStatus());

        manager.getTaskById(addedTaskId).setStatus(Status.DONE);

        Assertions.assertEquals(Status.DONE, manager.getTaskById(addedTaskId).getStatus());
    }

    @Test
    void statusSubTaskNew() {
        EpicDto epicDtoForAdd = new EpicDto();
        int addedEpicId = manager.add(epicDtoForAdd);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);

        Assertions.assertEquals(Status.NEW, addedSubTask.getStatus());
    }

    @Test
    void statusSubTaskNewReplaceInProgress() {
        EpicDto epicDtoForAdd = new EpicDto();
        int addedEpicId = manager.add(epicDtoForAdd);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);

        Assertions.assertEquals(Status.NEW, addedSubTask.getStatus());

        addedSubTask.setStatus(Status.IN_PROGRESS);

        Assertions.assertEquals(Status.IN_PROGRESS, addedSubTask.getStatus());
    }


    @Test
    void statusEpicNewWhenSubTaskNEW() {
        EpicDto epicDtoForAdd = new EpicDto();
        int addedEpicId = manager.add(epicDtoForAdd);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);

        Assertions.assertEquals(Status.NEW, addedSubTask.getStatus());
        Assertions.assertEquals(Status.NEW, manager.getEpicStatusById(addedEpicId));
    }

    @Test
    void statusEpicInProgressWhenSubTaskOneOfTheTaskInProgress() {
        EpicDto epicDtoForAdd = new EpicDto();
        int addedEpicId = manager.add(epicDtoForAdd);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);

        SubTaskDto subTaskDtoForAdd1 = new SubTaskDto();
        subTaskDtoForAdd1.setEpicId(addedEpicId);
        int subTaskId1 = manager.add(subTaskDtoForAdd1);
        SubTask addedSubTask1 = manager.getSubTaskById(subTaskId1);

        Assertions.assertEquals(Status.NEW, addedSubTask.getStatus());
        Assertions.assertEquals(Status.NEW, addedSubTask1.getStatus());
        Assertions.assertEquals(Status.NEW, manager.getEpicStatusById(addedEpicId));

        addedSubTask1.setStatus(Status.IN_PROGRESS); // поменять!

        Assertions.assertEquals(Status.NEW, addedSubTask.getStatus());
        Assertions.assertEquals(Status.IN_PROGRESS, addedSubTask1.getStatus());
        Assertions.assertEquals(Status.IN_PROGRESS, manager.getEpicStatusById(addedEpicId));
    }

    @Test
    void statusEpicDoneWhenAllSubTasksDone() {
        EpicDto epicDtoForAdd = new EpicDto();
        int addedEpicId = manager.add(epicDtoForAdd);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);

        SubTaskDto subTaskDtoForAdd1 = new SubTaskDto();
        subTaskDtoForAdd1.setEpicId(addedEpicId);
        int addedSubTaskId1 = manager.add(subTaskDtoForAdd1);
        SubTask addedSubTask1 = manager.getSubTaskById(addedSubTaskId1);

        Assertions.assertEquals(Status.NEW, addedSubTask.getStatus());
        Assertions.assertEquals(Status.NEW, addedSubTask1.getStatus());
        Assertions.assertEquals(Status.NEW, manager.getEpicStatusById(addedEpicId));

        addedSubTask.setStatus(Status.DONE); // поменять!
        addedSubTask1.setStatus(Status.DONE);

        Assertions.assertEquals(Status.DONE, addedSubTask.getStatus());
        Assertions.assertEquals(Status.DONE, addedSubTask1.getStatus());
        Assertions.assertEquals(Status.DONE, manager.getEpicStatusById(addedEpicId));
    }

    @Test
    void updateTaskSuccessfully() {
        TaskDto taskDtoForAdd = new TaskDto();
        taskDtoForAdd.setDescription("Попить воды");
        taskDtoForAdd.setTitle("Налить воды в стакан");
        int addedTaskId = manager.add(taskDtoForAdd);
        Task addedTask = manager.getTaskById(addedTaskId);

        Assertions.assertEquals(taskDtoForAdd.getDescription(), addedTask.getDescription());
        Assertions.assertEquals(taskDtoForAdd.getTitle(), addedTask.getTitle());

        TaskDto taskDtoForUpdate = new TaskDto();
        taskDtoForUpdate.setId(addedTaskId);
        taskDtoForUpdate.setDescription("Пообедать");
        taskDtoForUpdate.setTitle("Приготовить обед");
        manager.update(taskDtoForUpdate);

        Assertions.assertEquals(taskDtoForUpdate.getDescription(), manager.getTaskById(addedTaskId).getDescription());
        Assertions.assertEquals(taskDtoForUpdate.getTitle(), manager.getTaskById(addedTaskId).getTitle());
    }

    @Test
    void updateEpicSuccessfully() {
        EpicDto epicDtoForAdd = new EpicDto();
        epicDtoForAdd.setDescription("Стать программистом");
        epicDtoForAdd.setTitle("Выбрать язык программирования, овладеть технологиями");
        int addedEpicId = manager.add(epicDtoForAdd);
        Epic addedEpic = manager.getEpicById(addedEpicId);

        Assertions.assertEquals(epicDtoForAdd.getDescription(), addedEpic.getDescription());
        Assertions.assertEquals(epicDtoForAdd.getTitle(), addedEpic.getTitle());

        EpicDto epicDtoForUpdate = new EpicDto();
        epicDtoForUpdate.setId(addedEpicId);
        epicDtoForUpdate.setDescription("Стать программистом");
        epicDtoForUpdate.setTitle("Выбрать язык программирования, овладеть технологиями");

        manager.update(epicDtoForUpdate);

        Assertions.assertEquals(epicDtoForUpdate.getDescription(), addedEpic.getDescription());
        Assertions.assertEquals(epicDtoForUpdate.getTitle(), addedEpic.getTitle());
    }

    @Test
    void updateSubTaskChangeEpicStatus() {
        EpicDto epicDtoForAdd = new EpicDto();
        epicDtoForAdd.setDescription("Стать программистом");
        epicDtoForAdd.setTitle("Выбрать язык программирования, овладеть технологиями");
        int addedEpicId = manager.add(epicDtoForAdd);

        SubTaskDto subTaskDtoForAdd = new SubTaskDto();
        subTaskDtoForAdd.setDescription("Начать учить язык программирования Java");
        subTaskDtoForAdd.setTitle("Записаться на курсы");
        subTaskDtoForAdd.setEpicId(addedEpicId);
        int addedSubTaskId = manager.add(subTaskDtoForAdd);
        SubTask addedSubTask = manager.getSubTaskById(addedSubTaskId);
        addedSubTask.setStatus(Status.IN_PROGRESS); //

        Assertions.assertEquals(Status.IN_PROGRESS, addedSubTask.getStatus());
        Assertions.assertEquals(Status.IN_PROGRESS, manager.getEpicStatusById(addedEpicId));

        SubTaskDto subTaskDtoForUpdate = new SubTaskDto();
        subTaskDtoForUpdate.setId(addedSubTaskId);
        subTaskDtoForUpdate.setTitle("Заниматься с опытным разработчиком");
        subTaskDtoForUpdate.setStatus(Status.DONE);

        manager.update(subTaskDtoForUpdate);

        Assertions.assertEquals(Status.DONE, manager.getSubTaskById(addedSubTaskId).getStatus());
        Assertions.assertEquals(Status.DONE, manager.getEpicStatusById(addedEpicId));
        Assertions.assertEquals(subTaskDtoForUpdate.getTitle(), manager.getSubTaskById(addedSubTaskId).getTitle());
    }
}