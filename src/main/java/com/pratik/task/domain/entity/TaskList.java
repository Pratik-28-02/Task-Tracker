package com.pratik.task.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task_list")
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",nullable = false, updatable = false)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;

    @OneToMany(mappedBy = "taskList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(id, taskList.id) && Objects.equals(title, taskList.title) && Objects.equals(description, taskList.description) && Objects.equals(created, taskList.created) && Objects.equals(updated, taskList.updated) && Objects.equals(tasks, taskList.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, created, updated, tasks);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", tasks=" + tasks +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}