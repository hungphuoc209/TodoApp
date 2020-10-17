package com.example.databasetest.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasetest.Adapters.RealmContext.RealmContext;
import com.example.databasetest.Model.CustomerInfoModel;
import com.example.databasetest.databinding.CustomerInfoBinding;
import com.example.databasetest.interf.OnCheckHelper;
import com.example.databasetest.interf.OnCustomItemClickListener;

import java.util.Objects;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class CustomerInfoAdapter extends RealmRecyclerViewAdapter<CustomerInfoModel,CustomerInfoAdapter.ViewHolder> {

    private Context context;
    private Realm realm;
    private CustomerInfoBinding binding;
    private OnCustomItemClickListener itemListener;
    private OnCheckHelper checkListener;
    private RealmResults<CustomerInfoModel> realmResults;

    public CustomerInfoAdapter(@Nullable RealmResults<CustomerInfoModel> data, Context context) {
        super(data, true);
        this.context = context;
        realmResults = data;
        setHasStableIds(true);
    }
    public void setOnCheckChange(OnCheckHelper checkListener){
        this.checkListener = checkListener;
    }
    public void setOnItemClick(OnCustomItemClickListener itemListener){
        this.itemListener = itemListener;
    }


    @NonNull
    @Override
    public CustomerInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CustomerInfoBinding itemView = CustomerInfoBinding.inflate(layoutInflater, parent,false);
        return new ViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CustomerInfoModel obj = getItem(position);
        if(obj != null){
            holder.binding.mEtName.setText(obj.getTask());
            holder.binding.mEtId.setText(obj.getDescription());
            holder.binding.checkBox.setChecked(obj.isSelected());
            if(holder.binding.checkBox.isChecked()){
                holder.binding.mEtName.setPaintFlags(holder.binding.mEtName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            else {
                holder.binding.mEtName.setPaintFlags(0);
            }
           holder.binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isChecked){
                       holder.binding.mEtName.setPaintFlags(holder.binding.mEtName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                   }
                   else
                   {
                       holder.binding.mEtName.setPaintFlags(0);
                   }
                   checkListener.OnSetChangedListener(isChecked, holder.getLayoutPosition());
               }
           });
        }
    }

    @Override
    public long getItemId(int position) {
            return Objects.requireNonNull(getItem(position)).getID();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        CustomerInfoBinding binding;
        public ViewHolder(@NonNull final CustomerInfoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id = Objects.requireNonNull(realmResults.get(getAdapterPosition())).getID();
                    itemListener.OnDeleteClick(id);
                }
            });


        }
    }

}

