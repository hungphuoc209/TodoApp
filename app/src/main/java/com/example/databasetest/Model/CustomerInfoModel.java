package com.example.databasetest.Model;

import com.example.databasetest.Adapters.RealmContext.RealmContext;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CustomerInfoModel extends RealmObject {

    @PrimaryKey
    private long ID;

    private String task;
    private String description;
    private boolean selected;

    public CustomerInfoModel() {
    }

    public CustomerInfoModel(String task, String description, boolean selected,long ID) {
        this.task = task;
        this.description = description;
        this.selected = selected;
        this.ID = ID;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

