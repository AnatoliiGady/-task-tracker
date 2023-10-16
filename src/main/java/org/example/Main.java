package org.example;

import org.example.manager.ManagerImpl;
import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;

public class Main {
    public static void main(String[] args) {
        ManagerImpl manager = new ManagerImpl();


        SubTask subTask1 = new SubTask();
        subTask1.setTitle("Собрать чемодан");
        subTask1.setDescription("Положить вещи в чемодан");
        manager.add(subTask1);
        subTask1.setStatus(Status.DONE);

        SubTask subTask2 = new SubTask();
        subTask2.setTitle("Собрать коробку");
        subTask2.setDescription("Найти коробку и сложить в нее посуду");
        manager.add(subTask2);
        subTask2.setStatus(Status.DONE);


        Epic epic1 = new Epic();
        epic1.setTitle("Переезд");
        epic1.setDescription("Собрать вещи");
        manager.add(epic1);
        epic1.addSubTaskId(0);
        epic1.addSubTaskId(1);

        System.out.println("В задаче под номером " + epic1.getId() + " находятся подзадачи под номером: " + epic1.getIdSubTasks().get(0)
                + ", " + epic1.getIdSubTasks().get(1));
        System.out.println("Статус задачи под номером " + epic1.getId() + " — " + manager.getEpicStatusByIdV2(epic1.getId())
                + ", а статус подзадач " + subTask1.getStatus() + " , " + subTask2.getStatus());

    }
}