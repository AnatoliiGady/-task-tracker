package org.example;

import org.example.dto.EpicDto;
import org.example.dto.SubTaskDto;
import org.example.io.ReadFileTasks;
import org.example.manager.Manager;
import org.example.manager.ManagerImpl;
import org.example.status.Status;
import org.example.task.Epic;
import org.example.task.SubTask;

public class Main {

    public static void main(String[] args) {
        ReadFileTasks readFileTasks = new ReadFileTasks();
        readFileTasks.generateTaskFromFile();

    }
}