package com.taskapi.service;

import com.taskapi.dto.TaskRequest;
import com.taskapi.entity.Task;
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
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(TaskRequest taskRequest) {
        Task newTask = new Task();
        newTask.setTitulo(taskRequest.getTitulo());
        newTask.setDescricao(taskRequest.getDescricao());
        newTask.setCompleta(false);
        return taskRepository.save(newTask);
    }

    public Task updateTask(Task task) {
        Task existingTask = taskRepository.findById(task.getId()).orElse(null);
        if (existingTask != null) {
            if (task.getTitulo() != null){
                existingTask.setTitulo(task.getTitulo());
            }
            if (task.getDescricao() != null){
                existingTask.setDescricao(task.getDescricao());
            }
            if (task.getCompleta() != null) {
                existingTask.setCompleta(task.getCompleta());
            }
            return taskRepository.save(existingTask);
        }
        return null;
    }

    public boolean deleteTaskById(long id) {
        boolean taskExists = taskRepository.existsById(id);
        if (taskExists) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
