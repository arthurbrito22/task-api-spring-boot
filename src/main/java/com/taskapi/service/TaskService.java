package com.taskapi.service;

import com.taskapi.entity.Task;
import com.taskapi.repository.TaskRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        Task existingTask = taskRepository.findById(task.getId()).orElse(null);
        if (existingTask != null) {
            existingTask.setTitulo(task.getTitulo());
            existingTask.setDescricao(task.getDescricao());
            existingTask.setCompleta(task.isCompleta());

            return taskRepository.save(existingTask);
        }
        return null;
    }
}
