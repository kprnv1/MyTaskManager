//package service;
//
////import dao.CSVTaskRepository;
//import dao.TaskRepository;
//import model.Epic;
//import model.SubTask;
//import model.Task;
//import model.TaskData;
//
//import java.util.List;
//import java.util.Map;
//
//public class TaskService implements TaskManager {
//
//    private final InMemoryTaskManager taskManager;
//    private final TaskRepository taskRepository;
//
//    public TaskService(InMemoryTaskManager taskManager, TaskRepository taskRepository) {
//        this.taskManager = taskManager;
//        this.taskRepository = taskRepository;
//    }
//
////    private void save() {
////        taskRepository.save(taskManager.load());
////    }
////
////    private void load() {
////        taskManager.save(taskRepository.load());
////    }
//
////    public static TaskService loadFromFile(String fileName) {
////        final TaskService manager = new TaskService(new InMemoryTaskManager(new InMemoryHistoryManager()), new CSVTaskRepository(fileName));
////        manager.load();
////        return manager;
////    }
//
//    @Override
//    public Map<Integer, Task> getTask() {
//        return null;
//    }
//
//    @Override
//    public Map<Integer, Epic> getEpic() {
//        return null;
//    }
//
//    @Override
//    public Map<Integer, SubTask> getSubtask() {
//        return null;
//    }
//
//    @Override
//    public Object getId(int id) {
//        Object task = taskManager.getId(id);
//        save();
//        return task;
//    }
//
//    @Override
//    public void create(Task task) {
//        taskManager.create(task);
//        save();
//    }
//
//    @Override
//    public void createEpic(Epic epic) {
//        taskManager.createEpic(epic);
//        save();
//    }
//
//    @Override
//    public void createSubTask(int idEpic, SubTask subTask) {
//        taskManager.createSubTask(idEpic, subTask);
//        save();
//    }
//
//    @Override
//    public void update(int id, Task task) {
//        taskManager.update(id, task);
//        save();
//    }
//
//    @Override
//    public void updateEpic(int id, Epic epic) {
//        taskManager.updateEpic(id, epic);
//        save();
//    }
//
//    @Override
//    public void updateSubTask(int idSubTask, SubTask subTask) {
//        taskManager.updateSubTask(idSubTask, subTask);
//        save();
//    }
//
//    @Override
//    public void deleteAll() {
//        taskManager.deleteAll();
//        save();
//    }
//
//    @Override
//    public void deleteEpicById(int id) {
//        taskManager.deleteEpicById(id);
//        save();
//    }
//
//    @Override
//    public void deleteId(int id) {
//        taskManager.deleteId(id);
//        save();
//    }
//
//    @Override
//    public void deleteIdSubTask(int id) {
//        taskManager.deleteIdSubTask(id);
//        save();
//    }
//
//    @Override
//    public void calculateStatus(int id) {
//
//    }
//
//    @Override
//    public void addStatus(int numberSubTask, String Status) {
//
//    }
//
//    @Override
//    public void addStatusInProgress(int id) {
//
//    }
//
//    @Override
//    public void addStatusDone(int id) {
//
//    }
//
//    @Override
//    public List<Task> getHistory() {
//        return taskManager.getHistory();
//    }
//}