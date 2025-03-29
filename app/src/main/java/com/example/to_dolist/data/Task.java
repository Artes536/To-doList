package com.example.to_dolist.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "isComplete")
    private Boolean isComplete;

    public Task() {
    }

    public Task(String text, boolean isComplete) {
        this.text = text;
        this.isComplete = isComplete;
    }
    @Ignore
    public Task(String text, Boolean isComplete) {
        this.text = text;
        this.isComplete = isComplete;
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
}
