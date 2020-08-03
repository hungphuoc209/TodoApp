package com.example.databasetest.Model;

public class CustomerInfoModel {


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public boolean ismGender() {
        return mGender;
    }

    public void setmGender(boolean mGender) {
        this.mGender = mGender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;

    public CustomerInfoModel(String mName, String mID, boolean mGender) {
        this.mName = mName;
        this.mID = mID;
        this.mGender = mGender;
    }

    private String mName;
    private String mID;
    private boolean mGender;



    }

