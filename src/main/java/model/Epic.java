package model;


import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    protected List<SubTask> subTasks = new ArrayList<>();

    private int epicNumber;

    public TaskType getType() {
        return TaskType.EPIC;
    }

    public void setEpic(int epicNumber) {
        this.epicNumber = epicNumber;
    }

    public Epic(String name, String description) {
        super(name, description);
        type = TaskType.EPIC;
    }
    public void addSubtaskInEpic(SubTask subTask) {
        subTasks.add(subTask);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public Epic(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() + ", " +
                "name=" + getName() + ", " +
                "status=" + getStatus() + ", " +
                "description=" + getDescription() + ", " +
                "subtask=" + getSubTasks() +
                '}';
    }

}