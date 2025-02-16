package model;

import service.Status;

import java.time.LocalDateTime;

public class SubTask extends Task {

    private Epic epic;

    public SubTask() {

    }

    public SubTask(String name, String description) {
        super(name, description);
        type = TaskType.SUBTASK;
    }

    public SubTask(String name, String description, LocalDateTime startDate, int duration) {
        super(name, description, startDate, duration);
        type = TaskType.SUBTASK;
    }

    public TaskType getType() {
        return TaskType.SUBTASK;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public SubTask(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() + " ," +
                "name=" + getName() + " ," +
                "status=" + getStatus() +
                '}';
    }

    public Status getStatus() {
        return super.getStatus();
    }

}