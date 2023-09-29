package org.example;

import org.example.manager.Manager;
import org.example.task.Task;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.add(new Task(4));
        System.out.println(manager);
    }

}