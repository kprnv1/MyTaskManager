package service;

import dao.TaskRepository;
import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskData;

import java.util.*;

import static service.Status.*;

public class InMemoryTaskManager implements TaskManager, TaskRepository {

    protected Map<Integer, Task> tasks;
    protected Map<Integer, Epic> epics;
    protected Map<Integer, SubTask> subTasks;


    @Override
    public Map<Integer, Task> getTask() {
        return tasks;
    }

    @Override
    public Map<Integer, Epic> getEpic() {
        return epics;
    }

    @Override
    public Map<Integer, SubTask> getSubtask() {
        return subTasks;
    }

    HistoryManager historyManager;

    private int seq = 0;

    public InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
        this.historyManager = new InMemoryHistoryManager();

    }

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
        this.historyManager = historyManager;
    }

    public int generateId() {
        return ++seq;
    }

    public Task getIdTask(int id) {
        Task task = getTask().get(id);
        if (task == null) {
            return null;
        }
        historyManager.add(task);
        return task;
    }

    public Epic getIdEpic(int id) {
        Epic epic = getEpic().get(id);
        if (epic == null) {
            return null;
        }
        historyManager.add(epic);
        return epic;
    }

    public SubTask getIdSubTask(int id) {
        SubTask subTask = getSubtask().get(id);
        if (subTask == null) {
            return null;
        }
        historyManager.add(subTask);
        return subTask;
    }

    @Override
    public Object getId(int id) {
        if (tasks.containsKey(id)) {
            return getIdTask(id);
        } else if (epics.containsKey(id)) {
            return getIdEpic(id);
        } else if (subTasks.containsKey(id)) {
            return getIdSubTask(id);
        } else {
            System.out.println("Такой задачи нет");
            return id;
        }
    }

    @Override
    public Task create(Task task) {
        task.setId(generateId());
        task.setStatus(NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epic.setStatus(NEW);
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public SubTask createSubTask(int idEpic, SubTask subTask) {
        subTask.setId(generateId());
        subTask.setStatus(NEW);
        subTasks.put(subTask.getId(), subTask);
        subTask.setEpic(epics.get(idEpic));
        calculateStatus(idEpic);
        return subTask;
    }

    @Override
    public void update(int id, Task task) {
        task.setId(id);
        task.setStatus(NEW);
        tasks.put(id, task);
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        deleteEpicById(id);
        epic.setStatus(NEW);
        epic.setId(id);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void updateSubTask(int idSubTask, SubTask subTask) {
        deleteIdSubTask(idSubTask);
        subTask.setId(idSubTask);
        subTask.setStatus(NEW);
        subTasks.put(idSubTask, subTask);
    }

    @Override
    public void deleteAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        seq = 0;
    }

    @Override
    public void deleteEpicById(int id) {
        for (SubTask subTask : epics.get(id).getSubTasks()) {
            subTasks.remove(subTask.getId());
            epics.remove(id);
        }
    }

    @Override
    public void deleteId(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            historyManager.remove(id);
        } else if (epics.containsKey(id)) {
            deleteEpicById(id);
            historyManager.remove(id);
        } else if (subTasks.containsKey(id)) {
            deleteIdSubTask(id);
            historyManager.remove(id);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    @Override
    public void deleteIdSubTask(int id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = subTask.getEpic();
        epic.getSubTasks().remove(subTask);
        subTasks.remove(id);
        calculateStatus(epic.getId());
    }

    @Override
    public void calculateStatus(int id) {
        boolean getNew = false;
        boolean getDone = false;
        boolean getInProgress = false;
        for (SubTask subTask : subTasks.values()) {
            getNew = subTask.getStatus().equals(NEW) || getNew;
            getInProgress = subTask.getStatus().equals(IN_PROGRESS) || getInProgress;
            getDone = subTask.getStatus().equals(DONE) || getDone;
        }
        if (getNew && !getInProgress && !getDone) {
            epics.get(id).setStatus(NEW);
        } else if (!getNew && !getInProgress && getDone) {
            epics.get(id).setStatus(DONE);
        } else epics.get(id).setStatus(IN_PROGRESS);
    }

    @Override
    public void addStatus(int numberSubTask, String Status) {
        if (Status.equals("IN_PROGRESS")) {
            addStatusInProgress(numberSubTask);
        } else if (Status.equals("DONE")) {
            addStatusDone(numberSubTask);
        }
    }

    @Override
    public void addStatusInProgress(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(IN_PROGRESS);
            calculateStatus(epic.getId());
        }
    }

    @Override
    public void addStatusDone(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(DONE);
            calculateStatus(epic.getId());
        }
    }

    @Override
    public List<Task> getHistory() {
        System.out.println(historyManager.getHistory().size());
        return historyManager.getHistory();
    }

    @Override
    public List<Task> getListAllTask() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<SubTask> getListAllSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public List<Epic> getListAllEpic() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public TaskData load() { // загрузить в файл
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(this.tasks.values());
        tasks.addAll(this.epics.values());
        tasks.addAll(this.subTasks.values());

        List<Integer> history = new ArrayList<>();
        for (Task task : tasks) {
            history.add(task.getId());
        }
        TaskData taskData = new TaskData(tasks, history);

        return taskData;
    }

    @Override
    public void save(TaskData taskData) { // сохранить из файл
        Task task;
        try {
            for (int i = 0; i < taskData.getTasks().size(); i++) {
                task = taskData.getTasks().get(i);
                switch (task.getType()) {
                    case TASK: {
                        tasks.put(task.getId(), task);
                        break;
                    }
                    case EPIC: {
                        epics.put(task.getId(), (Epic) task);
                        break;
                    }
                    case SUBTASK: {
                        subTasks.put(task.getId(), (SubTask) task);
                        break;
                    }
                    default: {
                        throw new Exception("Не удалось определить тип файла");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (taskData.getTasks().size() != 0) {
            seq = taskData.getTasks().stream().max(new TaskComparator()).get().getId();
        }

        for (int id : taskData.getHistory()) {
            if (tasks.get(id) != null) {
                getHistory().add(tasks.get(id));
            } else if (epics.get(id) != null) {
                getHistory().add(epics.get(id));
            } else if (subTasks.get(id) != null) {
                getHistory().add(subTasks.get(id));
            }
        }
    }

}