package com.gustavo.todolist.repository;

import com.gustavo.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByTitle(String title);

    List<Task> findByUserId(Integer userId);
}
