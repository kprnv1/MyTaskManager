package service;

import dao.CSVTaskRepository;
import dao.TaskRepository;
import model.Epic;
import model.SubTask;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {

    protected final TaskRepository taskRepository;

    public FileBackedTaskManager(String path) {
        this.taskRepository = new CSVTaskRepository(new File(path));
    }

    public FileBackedTaskManager() {
        this.taskRepository = new CSVTaskRepository(new File("data/task.csv"));
    }

    public void save() {
        try {
            taskRepository.save(super.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Task create(Task task) {
        Task newTask = super.create(task);
        save();
        return newTask;
    }

    @Override
    public SubTask createSubTask(int idEpic, SubTask subTask) {
        SubTask newSubTask = super.createSubTask(idEpic, subTask);
        save();
        return newSubTask;
    }

    @Override
    public Epic createEpic(Epic epic) {
        Epic newEpic = super.createEpic(epic);
        save();
        return newEpic;
    }

    @Override
    public List<Task> getListAllTask() {
        List<Task> listTask = super.getListAllTask();
        save();
        return listTask;
    }

    @Override
    public List<SubTask> getListAllSubTask() {
        List<SubTask> listSubTask = super.getListAllSubTask();
        save();
        return listSubTask;
    }

    @Override
    public List<Epic> getListAllEpic() {
        List<Epic> listEpic = super.getListAllEpic();
        save();
        return listEpic;
    }

    @Override
    public void deleteAll() {
        super.deleteAll();
        save();
    }

    @Override
    public Task getIdTask(int id) {
        Task task = super.getIdTask(id);
        if (task != null) {
            save();
        }
        return task;
    }

    @Override
    public Epic getIdEpic(int id) {
        Epic epic = super.getIdEpic(id);
        if (epic != null) {
            save();
        }
        return epic;
    }

    @Override
    public SubTask getIdSubTask(int id) {
        SubTask subTask = super.getIdSubTask(id);
        if (subTask != null) {
            save();
        }
        return subTask;
    }

    @Override
    public void update(int id, Task task) {
        super.update(id, task);
        save();
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        super.updateEpic(id, epic);
        save();
    }

    @Override
    public void updateSubTask(int id, SubTask subTask) {
        super.updateSubTask(id, subTask);
        save();
    }

    @Override
    public void deleteIdSubTask(int id) {
        super.deleteIdSubTask(id);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void deleteId(int id) {
        super.deleteId(id);
        save();
    }

    public static FileBackedTaskManager loadFromFile(String filePath) {
        final FileBackedTaskManager manager = new FileBackedTaskManager(filePath);
        manager.load();
        return manager;
    }


    public static void main(String[] args) {
        TaskManager taskManagerReload = new FileBackedTaskManager("data/task.csv");

        taskManagerReload.create(new Task("Task - 1"));

        taskManagerReload.create(new Task("Task - 2"));

        taskManagerReload.createEpic(new Epic("Epic - 3"));

        taskManagerReload.createSubTask(taskManagerReload.getIdEpic(3).getId(), new SubTask("SubTask - 1"));
        taskManagerReload.createSubTask(taskManagerReload.getIdEpic(3).getId(), new SubTask("SubTask - 2"));
        taskManagerReload.createSubTask(taskManagerReload.getIdEpic(3).getId(), new SubTask("SubTask - 3"));

        System.out.println(taskManagerReload.getIdTask(1));
        System.out.println(taskManagerReload.getIdEpic(3));


        TaskManager taskManager = FileBackedTaskManager.loadFromFile("data/task.csv");
        System.out.println("Задачи эквивалентны? " + isEqualsTasks(taskManager.getListAllTask(),
                taskManagerReload.getListAllTask()));
        System.out.println("Епики эквивалентны? " + isEqualsTasks(taskManager.getListAllEpic(),
                taskManagerReload.getListAllEpic()));
        System.out.println("Сабтаски эквивалентны? " + isEqualsTasks(taskManager.getListAllSubTask(),
                taskManagerReload.getListAllSubTask()));
    }

    private static boolean isEqualsTasks(List<? extends Task> firstList, List<? extends Task> secondList) {
        boolean isEquals = true;

        if (firstList != null && secondList != null) {
            if (firstList.size() == secondList.size()) {
                for (int i = 0; i < firstList.size(); i++) {
                    if (!firstList.get(i).equals(secondList.get(i))) {
                        isEquals = false;
                        break;
                    }
                }
            }
        }
        return isEquals;
    }
}