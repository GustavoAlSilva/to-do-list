package com.gustavo.todolist.dto.task;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskCreateDTO {
    private String title;
    private String description;
    private LocalDate dueDate;
    private Integer userId;
}
