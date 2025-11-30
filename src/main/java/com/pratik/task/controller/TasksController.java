package com.pratik.task.controller;

import com.pratik.task.domain.dto.TaskDto;
import com.pratik.task.domain.entity.Task;
import com.pratik.task.mapper.TaskMapper;
import com.pratik.task.repository.TaskRepository;
import com.pratik.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping()
    public List<TaskDto> listTasks(@PathVariable ("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    @PostMapping
    public TaskDto createTask(@PathVariable ("task_list_id") UUID taskListId, @RequestBody TaskDto taskDto) {
        Task createdTask =  taskService.createTask(
                taskListId,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(createdTask);
    }
    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
            @PathVariable ("task_list_id") UUID taskListId,
            @PathVariable ("task_id") UUID taskID
    ) {
        return taskService.getTask(taskListId, taskID).map(taskMapper::toDto);
    }
    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable ("task_list_id") UUID taskListId,
            @PathVariable ("taskId") UUID task_id,
            @RequestBody TaskDto taskDto
    ) {
            Task updatedTask = taskService.updateTask(taskListId, task_id, taskMapper.fromDto(taskDto));
            return taskMapper.toDto(updatedTask);
    }
    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(@PathVariable ("task_list_id") UUID taskListId,
                           @PathVariable ("task_id") UUID taskID
    ) {
        taskService.deleteTask(taskListId, taskID);
    }
}
