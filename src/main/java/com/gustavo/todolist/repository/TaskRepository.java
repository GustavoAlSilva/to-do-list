package com.gustavo.todolist.repository;

import com.gustavo.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByTitle(String title);
}
