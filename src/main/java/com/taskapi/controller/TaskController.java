package com.taskapi.controller;

import com.taskapi.dto.TaskRequest;
import com.taskapi.entity.Task;
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

        Task retornoTask = taskService.getTaskById(id);

        if (retornoTask != null) {
            return ResponseEntity.ok(retornoTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task retornoTask = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(retornoTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {

        task.setId(id);

        task = taskService.updateTask(task);

        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
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