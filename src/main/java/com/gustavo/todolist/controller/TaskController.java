package com.gustavo.todolist.controller;

import com.gustavo.todolist.dto.TaskResponseDTO;
import com.gustavo.todolist.entity.Task;
import com.gustavo.todolist.enums.TaskStatus;
import com.gustavo.todolist.mapper.TaskMapper;
import com.gustavo.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        List<TaskResponseDTO> response = taskMapper.toDTOList(tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id) {
        Task task = taskService.findById(id);
        TaskResponseDTO response = taskMapper.toDTO(task);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<TaskResponseDTO> getTaskByTitle(@PathVariable String title) {
        Task task = taskService.findByTitle(title);
        TaskResponseDTO response = taskMapper.toDTO(task);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(@PathVariable Integer userId) {
        List<Task> tasks = taskService.findByUserId(userId);
        List<TaskResponseDTO> response = taskMapper.toDTOList(tasks);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody Task task) {
        Task createdTask = taskService.create(task);
        TaskResponseDTO response = taskMapper.toDTO(createdTask);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        Task updatedTask = taskService.update(id, task);
        TaskResponseDTO response = taskMapper.toDTO(updatedTask);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(
            @PathVariable Integer id,
            @RequestParam TaskStatus status
    ) {

        Task updatedTask = taskService.updateTaskStatus(id, status);
        TaskResponseDTO response = taskMapper.toDTO(updatedTask);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
