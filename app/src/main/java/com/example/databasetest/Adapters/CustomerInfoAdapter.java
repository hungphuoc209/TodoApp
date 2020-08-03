package com.example.databasetest.Adapters;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasetest.Model.CustomerInfoModel;
import com.example.databasetest.Model.CustomerUtils;
import com.example.databasetest.databinding.CustomerInfoBinding;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Objects;

public class CustomerInfoAdapter extends RecyclerView.Adapter<CustomerInfoAdapter.ViewHolder> {


    public CustomerInfoAdapter(ArrayList<CustomerInfoModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    private ArrayList<CustomerInfoModel> arrayList;
    private Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        CustomerInfoBinding itemView = CustomerInfoBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        arrayList = CustomerUtils.getInstance().getAll();
        holder.binding.mEtName.setText(CustomerUtils.getInstance().getAll().get(position).getmName());
        holder.binding.mEtId.setText(CustomerUtils.getInstance().getAll().get(position).getmID());
        if (arrayList.get(position).ismGender()) {
            holder.binding.mEtMale.setText("Male");
        } else {
            holder.binding.mEtMale.setText("Female");
        }
        holder.binding.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });


    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ArrayList<CustomerInfoModel> arrayList;
        CustomerInfoBinding binding;
        ViewHolder(@NonNull final CustomerInfoBinding itemView)  {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
