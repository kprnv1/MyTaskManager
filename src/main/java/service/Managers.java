package service;

public class Managers {
    public static TaskManager getDefaultTaskManagers(){
        return new InMemoryTaskManager(new InMemoryHistoryManager());
    }

     public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
