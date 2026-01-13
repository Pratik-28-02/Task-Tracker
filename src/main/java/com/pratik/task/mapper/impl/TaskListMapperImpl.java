package com.pratik.task.mapper.impl;

import com.pratik.task.domain.dto.TaskListDto;
import com.pratik.task.domain.entity.Task;
import com.pratik.task.domain.entity.TaskList;
import com.pratik.task.domain.entity.TaskStatus;
import com.pratik.task.mapper.TaskListMapper;
import com.pratik.task.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks ->
                                tasks.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)
        );
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        TaskList taskList = new TaskList();
        taskList.setId(taskListDto.id());
        taskList.setTitle(taskListDto.title());
        taskList.setDescription(taskListDto.description());

        if (taskListDto.tasks() != null) {
            taskList.setTasks(
                    taskListDto.tasks().stream()
                            .map(taskMapper::fromDto)
                            .toList()
            );
        }

        return taskList;
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks || tasks.isEmpty()){
            return 0.0;
        }
        long closedTaskCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSED == task.getStatus())
                .count();

        return (double) closedTaskCount / tasks.size();
    }
}