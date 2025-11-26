package com.pratik.task.mapper;

import com.pratik.task.domain.dto.TaskListDto;
import com.pratik.task.domain.entity.TaskList;

public interface TaskListMapper {

    TaskListDto toDto(TaskList taskList);

    TaskList fromDto(TaskListDto taskListDto);
}
