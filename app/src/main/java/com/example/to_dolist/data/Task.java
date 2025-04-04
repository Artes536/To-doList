package com.example.to_dolist.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "isComplete")
    private Boolean isComplete = false;

    @ColumnInfo(name = "createdAt")
    private Long createdAt;

    public Task() {
    }
    @Ignore
    public Task(String text) {
        this.text = text;
        this.isComplete = false;
        this.createdAt = System.currentTimeMillis();
    }
    public String getText(){
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
