package com.gustavo.todolist.service;

import com.gustavo.todolist.dto.TaskResponseDTO;
import com.gustavo.todolist.entity.Task;
import com.gustavo.todolist.enums.TaskStatus;
import com.gustavo.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task findByTitle(String title) {
        Task task = taskRepository.findByTitle(title);

        if (task == null) {
            throw new RuntimeException("Task not found");
        }

        return task;
    }

    public List<TaskResponseDTO> findByUserId(Integer userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TaskResponseDTO create(Task task) {
        task.setStatus(TaskStatus.todo);
        return this.convertToDTO(taskRepository.save(task));
    }

    public TaskResponseDTO update(Integer id, Task updatedTask) {
        Task existingTask = findById(id);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        return this.convertToDTO(taskRepository.save(existingTask));
    }

    public Task updateTaskStatus(Integer id, TaskStatus status) {
        Task task = findById(id);
        task.setStatus(status);

        if (status == TaskStatus.done) {
            task.setCompletedAt(LocalDate.now());
        } else if (status == TaskStatus.canceled) {
            task.setCanceledAt(LocalDate.now());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    private TaskResponseDTO convertToDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setCompletedAt(task.getCompletedAt());
        dto.setCanceledAt(task.getCanceledAt());
        dto.setUserId(task.getUser().getId());
        return dto;
    }
}
