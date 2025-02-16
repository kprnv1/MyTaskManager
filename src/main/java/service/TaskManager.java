package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.List;
import java.util.Map;

public interface TaskManager {

    Map<Integer, Task> getTask();

    Map<Integer, Epic> getEpic();

    Map<Integer, SubTask> getSubtask();

    Object getId(int id);

    Task create(Task task);

    Epic createEpic(Epic epic);

    SubTask createSubTask(int idEpic, SubTask subTask);

    void update(int id, Task task);

    void updateEpic(int id, Epic epic);

    void updateSubTask(int idSubTask, SubTask subTask);

    void deleteAll();

    void deleteEpicById(int id);

    void deleteId(int id);

    void deleteIdSubTask(int id);

    void calculateStatus(int id);

    void addStatus(int numberSubTask, String Status);

    void addStatusInProgress(int id);

    void addStatusDone(int id);

    List<Task> getHistory();

    List<Task> getListAllTask();

    List<SubTask> getListAllSubTask();

    List<Epic> getListAllEpic();

    Task getIdTask(int id);

    SubTask getIdSubTask(int id);

    Epic getIdEpic(int id);
}