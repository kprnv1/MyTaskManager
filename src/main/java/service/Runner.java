package service;

import model.Epic;
import model.SubTask;
import model.Task;

import static service.Managers.getDefaultHistory;

public class Runner {
    protected TaskManager taskManager = new InMemoryTaskManager(getDefaultHistory());
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();


    public void start() {
//        taskManager.createEpic(new Epic("1"));
//        taskManager.createSubTask(1, new SubTask("01"));
//        taskManager.createSubTask(1, new SubTask("02"));
//        taskManager.createSubTask(1, new SubTask("03"));
//
//        System.out.println(taskManager.getHistory());


        historyManager.linkLast(new Epic("Строим дом"));
        System.out.println(historyManager.getHistory());
        historyManager.linkLast(new SubTask("Заливаем фундамент"));
        System.out.println(historyManager.getHistory());
        historyManager.linkLast(new SubTask("Выкладываем стены"));
        System.out.println(historyManager.getHistory());
        historyManager.linkLast(new SubTask("Ставим крышу"));
        System.out.println(historyManager.getHistory());

        historyManager.linkLast(new Epic("Строим дом"));
        System.out.println(historyManager.getHistory());

        historyManager.removeNode(historyManager.first);
        System.out.println(historyManager.getHistory());
//        historyManager.removeNode(historyManager.last);
        historyManager.linkLast(new SubTask("1"));
        historyManager.linkLast(new SubTask("2"));
        historyManager.linkLast(new SubTask("3"));
        historyManager.linkLast(new SubTask("4"));
        historyManager.linkLast(new SubTask("5"));
        historyManager.linkLast(new SubTask("6"));
        historyManager.linkLast(new SubTask("7"));
        historyManager.linkLast(new SubTask("8"));
        historyManager.linkLast(new SubTask("9"));

        System.out.println(historyManager.getHistory());




    }
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("1: Создаем простую задачу");
//        Task task = new Task("Посетить музей");
//        taskManager.create(task);
//        System.out.println(taskManager.getId(task.getId()));
//        System.out.println("Закончили создавать простую задачу");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("2: Создаем сложную задачу");
//        Epic epic = new Epic("Заплатить налоги");
//        taskManager.createEpic(epic);
//        System.out.println(taskManager.getId(epic.getId()));
//        System.out.println("Закончили создавать сложную задачу");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("3: Создаем подзадачу");
//        System.out.println("И добавляем подзадачу в сложную задачу");
//        SubTask subTask = new SubTask("Заплатить за авто");
//        epic.addSubtaskInEpic(subTask);
//        taskManager.createSubTask(epic.getId(), subTask);
//        System.out.println(taskManager.getId(subTask.getId()));
//        System.out.println(epic);
//        System.out.println("Закончили создавать подзадачу");
//        System.out.println("И добавили в сложную задачу");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("4: Обновляем задачу по id");
//        taskManager.update(1, new Task("Посетить музыкальный театр"));
//        System.out.println(taskManager.getId(task.getId()));
//        System.out.println("Закочили обновлять задачу по id");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("5: Обновляем сложную задачу по id");
//        Epic epic1 = new Epic("Купить стройматериалы");
//        taskManager.updateEpic(2, epic1);
//        System.out.println(taskManager.getId(epic1.getId()));
//        System.out.println("Закончили обновлять сложную задачу по id");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("6: Создаем 2 подзадачи");
//        System.out.println("И добавляем подзадачу в сложную задачу");
//        SubTask subTask2 = new SubTask("Купить плитку");
//        SubTask subTask3 = new SubTask("Купить цемент");
//        epic1.addSubtaskInEpic(subTask2);
//        epic1.addSubtaskInEpic(subTask3);
//        taskManager.createSubTask(epic1.getId(), subTask2);
//        taskManager.createSubTask(epic1.getId(), subTask3);
//        System.out.println(taskManager.getId(subTask2.getId()));
//        System.out.println(taskManager.getId(subTask3.getId()));
//        System.out.println("epic1=" + epic1);
//        System.out.println("Закончили создавать подзадачу");
//        System.out.println("И добавили в сложную задачу");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("7: Получаем задачи по id");
//        System.out.println(taskManager.getId(2));
//        System.out.println(taskManager.getId(1));
//        System.out.println(taskManager.getId(4));
//        System.out.println("Закончили получать задачи по id");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("8: Обновляем подзадачу");
//        SubTask subTask4 = new SubTask("Лучше купить паркет");
//        taskManager.updateSubTask(5, subTask4);
//        epic1.addSubtaskInEpic(subTask4);
//        System.out.println(taskManager.getSubtask());
//        System.out.println("Закончили обновлять подзадачу");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("9: Удаляем по id");
//        taskManager.deleteId(1);
//        System.out.println(taskManager.getTask());
//        System.out.println("Закончили удалять по id");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("10: Устанавливаем статус");
//        taskManager.addStatus(4, "DONE");
//        taskManager.addStatus(5, "NEW");
//        System.out.println(taskManager.getSubtask());
//        System.out.println(taskManager.getEpic());
//        System.out.println("Установили статус");
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("11: Создаем сложную задачу");
//        Epic epic2 = new Epic("Купить обои");
//        taskManager.createEpic(epic2);
//        System.out.println(taskManager.getId(epic2.getId()));
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("12: Создаем сложную задачу");
//        Epic epic3 = new Epic("Купить краску");
//        taskManager.createEpic(epic3);
//        System.out.println(taskManager.getId(epic3.getId()));
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("13: Создаем сложную задачу");
//        Epic epic5 = new Epic("Нанять рабочих");
//        taskManager.createEpic(epic5);
//        System.out.println(taskManager.getId(epic5.getId()));
//        System.out.println(taskManager.getId(epic5.getId()));
//        System.out.println(taskManager.getId(epic5.getId()));
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("");
//
//        System.out.println("14: И удаляем все задачи и подзадачи");
//        taskManager.deleteAll();
//        System.out.println("Все задачи и подзадачи удалены");
//        System.out.println("");
//
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("История задач: " + taskManager.getHistory());
//        System.out.println("-----------------------------------------------------------");
//    }
}