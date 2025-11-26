package com.pratik.task.mapper;

import com.pratik.task.domain.dto.TaskListDto;
import com.pratik.task.domain.entity.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskList taskList);

    TaskListDto toDto(TaskList taskList);
}
