package com.gustavo.todolist.service;

import com.gustavo.todolist.dto.task.TaskCreateDTO;
import com.gustavo.todolist.dto.task.TaskUpdateDTO;
import com.gustavo.todolist.entity.Task;
import com.gustavo.todolist.entity.User;
import com.gustavo.todolist.enums.TaskStatus;
import com.gustavo.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
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

    public List<Task> findByUserId(Integer userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task create(TaskCreateDTO taskCreateDTO) {
        User user = userService.findById(taskCreateDTO.getUserId());
        Task task = new Task();
        task.setTitle(taskCreateDTO.getTitle());
        task.setDescription(taskCreateDTO.getDescription());
        task.setDueDate(taskCreateDTO.getDueDate());
        task.setUser(user);
        task.setStatus(TaskStatus.todo);
        return taskRepository.save(task);
    }

    public Task update(Integer id, TaskUpdateDTO taskUpdateDTO) {
        Task existingTask = findById(id);
        existingTask.setTitle(taskUpdateDTO.getTitle());
        existingTask.setDescription(taskUpdateDTO.getDescription());
        existingTask.setDueDate(taskUpdateDTO.getDueDate());
        return taskRepository.save(existingTask);
    }

    public Task updateTaskStatus(Integer id, TaskStatus status) {
        Task task = findById(id);
        task.setStatus(status);

        if (status == TaskStatus.done) {
            task.setCompletedAt(LocalDate.now());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
