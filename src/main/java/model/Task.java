package model;

import service.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private int id;
    private String name;
    private Status status;
    private String description;
    protected TaskType type;
    private LocalDateTime startTime;
    private int duration;

    public Task(String name) {
        this.name = name;
    }

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = Status.NEW;
        type = TaskType.TASK;
    }

    public Task(String name, String description, LocalDateTime startTime, int duration) {
        this(name, description);
        this.startTime = startTime;
        this.duration = duration;
    }

    public TaskType getType() {
        return TaskType.TASK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id);
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}