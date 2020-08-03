package com.example.databasetest.interf;

import androidx.recyclerview.widget.RecyclerView;

import com.example.databasetest.Adapters.CustomerInfoAdapter;
import com.example.databasetest.Model.CustomerInfoModel;

public interface OnCustomItemClickListener {
    int returnID(CustomerInfoModel customerInfoModel);

}
