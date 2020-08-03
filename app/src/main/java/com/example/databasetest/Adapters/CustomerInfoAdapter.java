package com.example.databasetest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasetest.Model.CustomerInfoModel;
import com.example.databasetest.Model.CustomerUtils;
import com.example.databasetest.databinding.CustomerInfoBinding;
import com.example.databasetest.interf.OnCustomItemClickListener;

import java.util.ArrayList;

public class CustomerInfoAdapter extends RecyclerView.Adapter<CustomerInfoAdapter.ViewHolder>{


    public CustomerInfoAdapter(ArrayList<CustomerInfoModel> arrayList, Context context, OnCustomItemClickListener listener) {
        this.arrayList = arrayList;
        this.context = context;
        this.listener = listener;
    }

    private ArrayList<CustomerInfoModel> arrayList;
    private Context context;
    OnCustomItemClickListener listener;

    @NonNull
    @Override
    public CustomerInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        CustomerInfoBinding itemView = CustomerInfoBinding.inflate(layoutInflater, parent, false);
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



    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public int returna(){
        return arrayList.get()
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements OnCustomItemClickListener {
        CustomerInfoBinding binding;
        ArrayList<CustomerInfoModel> arrayList;

        ViewHolder(@NonNull final CustomerInfoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            arrayList = CustomerUtils.getInstance().getAll();

            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnID(arrayList.get(getAdapterPosition()));
                }
            });

        }

        @Override
        public int returnID(CustomerInfoModel customerInfoModel) {
            return customerInfoModel.getID();
        }
    }
}
