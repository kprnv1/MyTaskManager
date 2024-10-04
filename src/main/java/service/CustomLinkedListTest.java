package service;

import model.Task;

public class CustomLinkedListTest {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    public void start() {

        historyManager.add(new Task("add-0"));
        historyManager.add(new Task("add-1"));
        historyManager.add(new Task("add-1"));
        historyManager.add(new Task("add-2"));
        historyManager.add(new Task("add-1"));
        //

        historyManager.add(new Task("add-3"));
        historyManager.add(new Task("add-4"));
        historyManager.add(new Task("add-5"));
        historyManager.add(new Task("add-6"));
        historyManager.add(new Task("add-7"));
        historyManager.add(new Task("add-8"));
        historyManager.add(new Task("add-9"));
        historyManager.add(new Task("add-10"));
        historyManager.add(new Task("add-11"));
        historyManager.add(new Task("add-12"));
        System.out.println("Добавили задачи: ");
        System.out.println(historyManager.getHistory());
        System.out.println();
        System.out.print("Удаляем задачу № : ");
//        historyManager.remove(1);
        System.out.println();

        System.out.println("FINISH " + historyManager.getHistory());

    }
}
