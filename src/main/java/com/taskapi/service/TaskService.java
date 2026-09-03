package com.taskapi.service;

import com.taskapi.entity.Task;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service

public class TaskService {
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        Task t = new Task(1, "Exercicio Sprint.", "Exercicio com o objetivo aprender Spring.", false);
        tasks.add(t);
        return tasks;
    }

    public Task getTaskById(long id) {
        List<Task> getTasks = this.getAllTasks();
        for (Task task : getTasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
