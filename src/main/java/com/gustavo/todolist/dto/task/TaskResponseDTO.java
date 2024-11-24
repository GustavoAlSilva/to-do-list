package com.gustavo.todolist.dto.task;

import com.gustavo.todolist.enums.TaskStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
    private LocalDate completedAt;
    private LocalDate canceledAt;
    private Integer userId;
}
