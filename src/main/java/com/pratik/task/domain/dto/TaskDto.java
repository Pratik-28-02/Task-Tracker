package com.pratik.task.domain.dto;

import com.pratik.task.domain.entity.TaskPriority;
import com.pratik.task.domain.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority taskPriority,
        TaskStatus taskStatus
) {
}
