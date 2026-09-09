package com.taskapi.service;

import com.taskapi.dto.TaskRequest;
import com.taskapi.dto.TaskUpdateRequest;
import com.taskapi.entity.Task;
import com.taskapi.exception.ErrorResponse;
import com.taskapi.exception.ResourceNotFoundException;
import com.taskapi.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
    }

    public Task createTask(TaskRequest taskRequest) {
        Task newTask = new Task();
        newTask.setTitulo(taskRequest.getTitulo());
        newTask.setDescricao(taskRequest.getDescricao());
        newTask.setCompleta(false);
        return taskRepository.save(newTask);
    }

    public Task updateTask(Long id, TaskUpdateRequest taskUpdateRequest) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        existingTask.setTitulo(taskUpdateRequest.getTitulo());
        existingTask.setDescricao(taskUpdateRequest.getDescricao());
        existingTask.setCompleta(taskUpdateRequest.getCompleta());
        return taskRepository.save(existingTask);
    }

    public void deleteTaskById(long id) {
        Task deleteTask = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        taskRepository.delete(deleteTask);
    }
}
