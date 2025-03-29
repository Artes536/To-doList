package com.example.to_dolist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM tasks ORDER BY isComplete ASC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM tasks WHERE isComplete = 0")
    LiveData<List<Task>> getUncompletedTasks();

    @Query("SELECT * FROM tasks WHERE isComplete = 1")
    LiveData<List<Task>> getCompletedTasks();
    @Query("DELETE FROM tasks WHERE createdAt < :expirationTime")
    void deleteOldTasks(long expirationTime);
}
