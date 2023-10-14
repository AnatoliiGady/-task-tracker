package org.example;

import org.example.manager.ManagerImpl;
import org.example.task.Task;

public class Main {
    public static void main(String[] args) {
        Task task1 = new Task();
        task1.setTitle("fgrefgef");
        task1.setDescription("dadasd");
        ManagerImpl managerImpl = new ManagerImpl();
        System.out.println(managerImpl.add(task1));


    }
}