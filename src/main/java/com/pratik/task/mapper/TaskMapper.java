package com.pratik.task.mapper;

import com.pratik.task.domain.dto.TaskDto;
import com.pratik.task.domain.entity.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
