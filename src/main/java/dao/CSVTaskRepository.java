package dao;

import model.*;
import service.Status;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CSVTaskRepository implements TaskRepository {
    private File file;
    private List<Epic> epics = new ArrayList<>();
    private final static String HEADER_FILE = "id,type,name,status,description,epic,startTime,duration\n";
    public CSVTaskRepository(File file) {
        this.file = file;
    }

    @Override
    public TaskData load() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }

        List<Integer> history = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            if (!lines.get(i).isEmpty()) {
                tasks.add(parseTask(lines.get(i)));
            } else {
                history = parseHistory(lines.get(lines.size() - 1));
                break;
            }
        }
        return new TaskData(tasks, history);
    }

    private List<Integer> parseHistory(String historyLine) {
        List<Integer> historyTasks = new ArrayList<>();
        String[] tasksNumber = historyLine.split(",");
        for (int i = 0; i < tasksNumber.length; i++) {
            historyTasks.add(Integer.parseInt(tasksNumber[i]));
        }
        return historyTasks;
    }

    private Task parseTask(String line) {
        String[] propertyTask = line.split(",");
        if (TaskType.TASK.toString().equals(propertyTask[1])) {
            Task task = new Task(propertyTask[2], propertyTask[4], LocalDateTime.parse(propertyTask[5]), Integer.parseInt(propertyTask[6]));
            task.setId(Integer.parseInt(propertyTask[0]));
            task.setStatus(parseStatus(propertyTask[3]));
            return task;
        } else if (TaskType.EPIC.toString().equals(propertyTask[1])) {
            Epic epic = new Epic(propertyTask[2], propertyTask[4]);
            epic.setId(Integer.parseInt(propertyTask[0]));
            epic.setStatus(parseStatus(propertyTask[3]));
            epics.add(epic);
            return epic;
        } else if (TaskType.SUBTASK.toString().equals(propertyTask[1])) {
            SubTask subTask = new SubTask(propertyTask[2], propertyTask[4], LocalDateTime.parse(propertyTask[5]), Integer.parseInt(propertyTask[6]));
            subTask.setId(Integer.parseInt(propertyTask[0]));
            subTask.setStatus(parseStatus(propertyTask[3]));
            subTask.setEpic(findEpic(propertyTask[5]));
            return subTask;
        } else {
            return null;
        }
    }

    private Status parseStatus(String status) {
        switch (status) {
            case "NEW" : {
                return Status.NEW;
            }
            case "IN_PROGRESS" : {
                return Status.IN_PROGRESS;
            }
            case "DONE" : {
                return Status.DONE;
            }
            default: {
                return null;
            }
        }
    }

    private Epic findEpic(String id) {
        for (Epic epic : epics) {
            if (epic.getId() == Integer.parseInt(id)) {
                return epic;
            }
        }
        return null;
    }

    @Override
    public void save(TaskData taskData) { //сохранение данных из ОП в структуру данных для записи в файл
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(HEADER_FILE);
            for (Task task : taskData.getTasks()) {
                writer.append(taskToLine(task));
                writer.newLine();
            }
            writer.newLine();
            for (int i = 0; i < taskData.getHistory().size(); i++) {
                writer.append(String.valueOf(taskData.getHistory().get(i)));
                if (i != taskData.getHistory().size() - 1) {
                    writer.append(",");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String taskToLine(Task task) {
        StringBuilder line = new StringBuilder();
        line.append(task.getId()).append(",").append(task.getType()).append(",").append(task.getName()).append(",").append(task.getStatus()).append(",").append(task.getDescription()).append(",");
        if (TaskType.SUBTASK.equals(task.getType())) {
            SubTask subTask = (SubTask) task;
            line.append(subTask.getEpic());
        } else {
            line.append("null");
        }
        return line.toString();
    }
}
