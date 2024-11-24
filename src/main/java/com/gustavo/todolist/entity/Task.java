package com.gustavo.todolist.entity;

import com.gustavo.todolist.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    private LocalDate dueDate;

    private LocalDate completedAt;

    private LocalDate canceledAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
