package com.pratik.task.repository;

import com.pratik.task.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    // Find all tasks by TaskList
    List<Task> findByTaskListId(UUID taskListId);

    // Find a specific task by TaskList and Task ID
    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);

    // Delete a specific task by TaskList and Task ID
    void deleteByTaskListIdAndId(UUID taskListId, UUID id);
}