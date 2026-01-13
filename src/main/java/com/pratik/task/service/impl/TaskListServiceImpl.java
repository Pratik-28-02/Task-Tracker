package com.pratik.task.service.impl;

import com.pratik.task.domain.entity.TaskList;
import com.pratik.task.repository.TaskListRepository;
import com.pratik.task.service.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Transactional
    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null != taskList.getId()) {
            throw new IllegalArgumentException("TaskList already has a Id");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList title must be present");
        }
        LocalDateTime now = LocalDateTime.now();
        // Fixed: Now actually returning the saved TaskList
        TaskList newTaskList = new TaskList();
        newTaskList.setTitle(taskList.getTitle());
        newTaskList.setDescription(taskList.getDescription());
        newTaskList.setCreated(now);
        newTaskList.setUpdated(now);
        return taskListRepository.save(newTaskList);
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null == taskList.getId()) {
            throw new IllegalArgumentException("TaskList id must be present");
        }
        if(!Objects.equals(taskList.getId(),taskListId)){
            throw new IllegalArgumentException("TaskList id does not match");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() ->
                new IllegalArgumentException("TaskList does not exist"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Transactional
    @Override
    public void deleteTaskList(UUID taskListid) {
        taskListRepository.deleteById(taskListid);
    }
}