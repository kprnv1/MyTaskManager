package service;

import java.io.File;

public class Managers {
    public static FileBackedTaskManager getFileBackedTaskManager(String pathFile) {
        return  new FileBackedTaskManager(pathFile);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}
