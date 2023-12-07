package org.example.io;

import org.example.dto.TaskDto;
import org.example.manager.Manager;
import org.example.manager.ManagerImpl;
import org.example.task.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFileTasks {

    ArrayList<Integer> tasksId = new ArrayList<>();
    Manager manager = new ManagerImpl();
    TaskDto taskDtoAdd = new TaskDto();

    public void generateTaskFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:/Tasks/Task.txt"));
            String textFile;
            while ((textFile = reader.readLine()) != null) {
                String[] arraysTextFile = textFile.split(":");
                taskDtoAdd.setId(Integer.parseInt(arraysTextFile[0]));
                taskDtoAdd.setDescription(arraysTextFile[1]);
                taskDtoAdd.setTitle(arraysTextFile[2]);
                int addedTaskId = manager.add(taskDtoAdd);
                tasksId.add(addedTaskId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer idTask : tasksId) {
            System.out.println(manager.getTaskById(idTask));
        }
    }
}
