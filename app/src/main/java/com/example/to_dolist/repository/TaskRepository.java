package com.example.to_dolist.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.to_dolist.data.Task;
import com.example.to_dolist.data.TaskDao;
import com.example.to_dolist.data.TaskDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public TaskRepository(Application application){
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public void insert(Task task){
        executorService.execute(()->{
            taskDao.insert(task);}
        );
    }

    public void update(Task task){
        executorService.execute(()-> taskDao.update(task));
    }

    public void delete(Task task){
        executorService.execute(()-> taskDao.delete(task));
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

}
