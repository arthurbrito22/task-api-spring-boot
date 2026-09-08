package com.taskapi.controller;

import com.taskapi.dto.TaskRequest;
import com.taskapi.dto.TaskUpdateRequest;
import com.taskapi.entity.Task;
import com.taskapi.exception.ResourceNotFoundException;
import com.taskapi.repository.TaskRepository;
import com.taskapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")

public class TaskController {

    private TaskRepository taskRepo;

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task retornoTask = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(retornoTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
        return ResponseEntity.ok(taskService.updateTask(id, taskUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {

        boolean retorno = taskService.deleteTaskById(id);

        if (retorno) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}