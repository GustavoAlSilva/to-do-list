package com.gustavo.todolist.service;

import com.gustavo.todolist.entity.Task;
import com.gustavo.todolist.enums.TaskStatus;
import com.gustavo.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public Task create(Task task) {
        task.setStatus(TaskStatus.todo);
        return taskRepository.save(task);
    }

    public Task update(Integer id, Task updatedTask) {
        Task existingTask = findById(id);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(existingTask);
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
}
