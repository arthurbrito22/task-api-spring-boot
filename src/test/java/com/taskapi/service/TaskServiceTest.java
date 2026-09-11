package com.taskapi.service;

import com.taskapi.dto.TaskRequest;
import com.taskapi.dto.TaskUpdateRequest;
import com.taskapi.entity.Task;
import com.taskapi.exception.ResourceNotFoundException;
import com.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Deve retornar todas as tarefas")
    void getAllTasks() {
        Task mockTask = new Task("Estudar JUnit 5", "Aprender Mockito no Spring Boot", false);
        when(taskRepository.findAll(any(Sort.class))).thenReturn(List.of(mockTask));
        List<Task> result = taskService.getAllTasks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Estudar JUnit 5", result.get(0).getTitulo());

        verify(taskRepository, times(1)).findAll(any(Sort.class));
    }

    @Test
    @DisplayName("Deve retornar a tarefa com id especifico")
    void getTaskById() {
        Long taskId = 1L;
        Task mockTask2 = new Task("Estudar JUnit 5", "Aprender Mockito no Spring Boot", false);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask2));

        Task result = taskService.getTaskById(taskId);

        assertNotNull(result);
        assertEquals("Estudar JUnit 5", result.getTitulo());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    @DisplayName("Deve lancar ResourceNotFoundException quando o ID nao for encontrado")
    void getTaskByIdInexistente() {
        Long taskId = 99L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.getTaskById(taskId)
        );

        assertEquals("Task not found with ID: " + taskId, exception.getMessage());

        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    @DisplayName("Deve criar uma task com sucesso")
    void createTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitulo("Estudar JUnit 5");
        taskRequest.setDescricao("Aprender Mockito no Spring Boot");

        Task mockTask = new Task();
        mockTask.setId(1L);
        mockTask.setTitulo("Estudar JUnit 5");
        mockTask.setDescricao("Aprender Mockito no Spring Boot");
        mockTask.setCompleta(false);

        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

        Task result = taskService.createTask(taskRequest);
        assertNotNull(result);
        assertEquals("Estudar JUnit 5", result.getTitulo());
        assertEquals("Aprender Mockito no Spring Boot", result.getDescricao());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve atualizar uma task existente")
    void updateTask() {
        Long taskId = 1L;
        TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest();
        taskUpdateRequest.setTitulo("Título Atualizado");
        taskUpdateRequest.setDescricao("Descrição Atualizada");

        Task existingTask = new Task("Título Antigo", "Descrição Antiga", false);
        existingTask.setId(taskId);

        Task updatedTask = new Task("Título Atualizado", "Descrição Atualizada", false);
        updatedTask.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(taskId, taskUpdateRequest);

        assertNotNull(result);
        assertEquals("Título Atualizado", result.getTitulo());
        assertEquals("Descrição Atualizada", result.getDescricao());

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve lancar ResourceNotFoundException ao tentar atualizar tarefa inexistente")
    void updateTaskInexistente() {
        Long taskId = 99L;
        TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest();
        taskUpdateRequest.setTitulo("Título Atualizado");
        taskUpdateRequest.setDescricao("Descrição Atualizada");

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.updateTask(taskId, taskUpdateRequest)
        );

        assertEquals("Task not found with ID: " + taskId, exception.getMessage());

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }


    @Test
    @DisplayName("Deve deletar uma task com sucesso")
    void deleteTaskById() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task()));

        taskService.deleteTaskById(taskId);


        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).delete(any(Task.class));
    }

    @Test
    @DisplayName("Deve lancar ResourceNotFoundException ao tentar deletar tarefa inexistente")
    void deleteTaskByIdInexistente() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.deleteTaskById(taskId)
        );

        assertEquals("Task not found with ID: " + taskId, exception.getMessage());

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).deleteById(taskId);
    }
}