package dao;

import model.TaskData;

import java.io.IOException;

public interface TaskRepository {
    TaskData load() throws IOException;                 //выгрузить из файла
    void save(TaskData taskData) throws IOException;    //сохранить в файл
}
