package com.example.databasetest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.databasetest.Adapters.CustomerInfoAdapter;
import com.example.databasetest.Model.CustomerInfoModel;
import com.example.databasetest.Model.CustomerUtils;
import com.example.databasetest.databinding.ActivityMainBinding;
import com.example.databasetest.databinding.CustomerInfoBinding;
import com.example.databasetest.interf.OnCustomItemClickListener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TooManyListenersException;

public class MainActivity extends AppCompatActivity implements OnCustomItemClickListener {

    public ActivityMainBinding binding;
    ArrayList<CustomerInfoModel> arrayList;
    OnCustomItemClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

    //  setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initView();
        main();
    }

    public void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvCustomerInfo.setLayoutManager(layoutManager);
    }
    public void main(){
        final CustomerUtils customerUtils;
        final OnCustomItemClickListener listener = null;
        arrayList = CustomerUtils.getInstance().getAll();
        final ArrayList<CustomerInfoModel> arrayList;


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerUtils.getInstance().addCustomer(new CustomerInfoModel(binding.etName.getText().toString(), binding.etId.getText().toString(), binding.cbMale.isChecked()));
                binding.etName.setText("");
                binding.etId.setText("");
                binding.cbMale.setChecked(false);
                CustomerInfoAdapter customerInfoAdapter = new CustomerInfoAdapter(arrayList, getApplicationContext(),listener);
                binding.rvCustomerInfo.setAdapter(customerInfoAdapter);



            }
        });
        binding.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = CustomerInfoAdapter.ViewHolder.
                CustomerUtils.getInstance().deteleCustomer(id);
            }
        });



    }

    @Override
    public int getID(CustomerInfoModel customerInfoModel) {
        ArrayList<CustomerInfoModel> arrayList;
        arrayList = CustomerUtils.getInstance().getAll();
        return arrayList.get(0).getID();
    }
}
