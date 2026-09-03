package com.taskapi.service;

import com.taskapi.entity.Task;
import com.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) { this.taskRepository = taskRepository; }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
