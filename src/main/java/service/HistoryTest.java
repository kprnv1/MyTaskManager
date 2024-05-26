package service;

import model.Epic;
import model.SubTask;
import model.Task;

import static service.Managers.getDefaultHistory;

public class HistoryTest {

    protected InMemoryTaskManager taskManager = new InMemoryTaskManager(getDefaultHistory());

    public void start() {

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("1: Создаем простую задачу");
        Task task = new Task("Посетить музей");
        taskManager.create(task);
        System.out.println(taskManager.getId(task.getId()));
        System.out.println("Закончили создавать простую задачу\n");

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("2: Создаем новую задачу");
        Task task1 = new Task("Прогуляться перед сном");
        taskManager.create(task1);
        System.out.println(taskManager.getId(task1.getId()));
        System.out.println("Закончили создавать простую задачу\n");

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("3: Создаем такую же задачу");
        Task task2 = new Task("Посетить музей");
        taskManager.create(task2);
        System.out.println(taskManager.getId(task2.getId()));
        System.out.println("Закончили создавать простую задачу\n");

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("4: Создаем ещё одну задачу, но не вызываем методом getId()");
        System.out.println("Соответственно задача не добавляется в историю");
        Task task3 = new Task("Сходить в кино");
        taskManager.create(task3);
        System.out.println("Закончили создавать простую задачу\n");
        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("5: Вызовем предыдущую задачу 2 раза методом getId(), " +
                "чтобы тоже добавить в историю 2 раза");
        System.out.println(taskManager.getId(task2.getId()));
        System.out.println(taskManager.getId(task2.getId()));
        System.out.println();

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("6: Создаем сложную задачу и вызывем методом getId()");
        Epic epic = new Epic("Заплатить налоги");
        taskManager.createEpic(epic);
        System.out.println(taskManager.getId(epic.getId()));
        System.out.println("Закончили создавать сложную задачу\n");

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("7: Создаем подзадачу");
        System.out.println("И добавляем подзадачу в сложную задачу");
        SubTask subTask = new SubTask("Заплатить за авто");
        epic.addSubtaskInEpic(subTask);
        taskManager.createSubTask(epic.getId(), subTask);
        System.out.println(taskManager.getId(subTask.getId()));
        System.out.println(epic);
        System.out.println("Закончили создавать подзадачу");
        System.out.println();

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("8: Вернем пару задач, " +
                "чтобы общее количество было 10");
        System.out.println(taskManager.getId(task.getId()));
        System.out.println(taskManager.getId(task1.getId()));
        System.out.println(taskManager.getId(task.getId()));
        System.out.println();

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

        System.out.println("9: И напоследок вернем еще одну задачу, " +
                "чтобы общее количество было более 10 и увидим, " +
                "что 1-ая задача Посетить музей была удалена");
        System.out.println(taskManager.getId(task1.getId()));

        System.out.println();

        System.out.println("history: " + taskManager.historyManager.getHistory());
        System.out.println();

    }
}
