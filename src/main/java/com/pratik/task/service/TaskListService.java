package com.pratik.task.service;

import com.pratik.task.domain.entity.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID taskListid, TaskList taskList);
    void deleteTaskList(UUID taskListid);
}
