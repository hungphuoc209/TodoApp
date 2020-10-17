package com.example.databasetest.interf;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.databasetest.Adapters.CustomerInfoAdapter;
import com.example.databasetest.Model.CustomerInfoModel;
import com.example.databasetest.Model.CustomerUtils;

import java.util.List;

public interface OnCustomItemClickListener {
    void OnItemClick(CustomerInfoModel customerInfoModel);
    void OnDeleteClick(long id);
}