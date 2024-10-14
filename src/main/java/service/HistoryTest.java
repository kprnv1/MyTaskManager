package service;

import model.Epic;
import model.SubTask;
import model.Task;

import static service.Managers.getDefaultHistory;

public class HistoryTest {
    protected TaskManager taskManager = new InMemoryTaskManager(getDefaultHistory());

    public void start() {
        Task task1 = new Task("Первый");
        taskManager.create(task1);
        Task task2 = new Task("Второй");
        taskManager.create(task2);
        Task task3 = new Task("Третий");
        taskManager.create(task3);

        Epic epic1 = new Epic("Заплатить налоги");
        taskManager.createEpic(epic1);
        SubTask subTask = new SubTask("Заплатить 1 налог");
        epic1.addSubtaskInEpic(subTask);
        taskManager.createSubTask(epic1.getId(), subTask);

        SubTask subTask1 = new SubTask("Заплатить 2 налог");
        epic1.addSubtaskInEpic(subTask1);
        taskManager.createSubTask(epic1.getId(), subTask1);

        SubTask subTask2 = new SubTask("Заплатить 3 налог");
        epic1.addSubtaskInEpic(subTask2);
        taskManager.createSubTask(epic1.getId(), subTask2);

        taskManager.getId(1);
        System.out.println("history: " + taskManager.getHistory());
        taskManager.getId(2);
        System.out.println("history: " + taskManager.getHistory());
        taskManager.getId(3);
        System.out.println("history: " + taskManager.getHistory());
        taskManager.getId(4);
        System.out.println("history: " + taskManager.getHistory());

        Task task8 = new Task("Восьмой");
        taskManager.create(task8);
        taskManager.getId(8);

        Task task9 = new Task("Девятый");
        taskManager.create(task9);
        taskManager.getId(9);

        Task task10 = new Task("Девятый");
        taskManager.create(task10);
        taskManager.getId(10);

        Task task11 = new Task("Одиннадцатый");
        taskManager.create(task11);
        taskManager.getId(11);

        Task task12 = new Task("Двенадцатый");
        taskManager.create(task12);
        taskManager.getId(12);

        Task task13 = new Task("Тринадцатый");
        taskManager.create(task13);
        taskManager.getId(13);

        Task task14 = new Task("Четырнадцатый");
        taskManager.create(task14);
        taskManager.getId(14);

        System.out.println("history: " + taskManager.getHistory());
        System.out.println(taskManager.getHistory().size());
    }
}



