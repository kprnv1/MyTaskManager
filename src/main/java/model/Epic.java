package model;


import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    protected List<SubTask> subTasks = new ArrayList<>();

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