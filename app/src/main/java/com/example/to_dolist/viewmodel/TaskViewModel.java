package com.example.to_dolist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.to_dolist.data.Task;
import com.example.to_dolist.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final TaskRepository repository;
    private final LiveData<List<Task>> allTasks;
    private final LiveData<List<Task>> completedTasks;
    private final LiveData<List<Task>> uncompletedTasks;

    public TaskViewModel(@NonNull Application application){
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
        completedTasks = repository.getCompletedTasks();
        uncompletedTasks = repository.getUncompletedTasks();
    }

    public void insert(Task task){
        repository.insert(task);
    }

    public void update(Task task){
        repository.update(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<Task>> getCompletedTasks(){
        return completedTasks;
    }
    public LiveData<List<Task>> getUncompletedTasks(){
        return uncompletedTasks;
    }


    public void markAsComplete(Task task) {
        task.setComplete(true);
        repository.update(task);
    }
    public void deleteOldTasks() {
        long expirationTime = System.currentTimeMillis() - (24 * 60 * 60 * 1000);
        repository.deleteOldTasks(expirationTime);
    }
}
