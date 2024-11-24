package com.gustavo.todolist.controller;

import com.gustavo.todolist.dto.TaskResponseDTO;
import com.gustavo.todolist.entity.Task;
import com.gustavo.todolist.enums.TaskStatus;
import com.gustavo.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<Task> getTaskByTitle(@PathVariable String title) {
        return ResponseEntity.ok(taskService.findByTitle(title));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(taskService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(id, task));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Integer id,
            @RequestParam TaskStatus status
    ) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
