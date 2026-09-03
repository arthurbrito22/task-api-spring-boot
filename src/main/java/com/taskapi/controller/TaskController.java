package com.taskapi.controller;

import com.taskapi.entity.Task;
import com.taskapi.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")

public class TaskController {

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
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task retornoTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(retornoTask);
    }
}
