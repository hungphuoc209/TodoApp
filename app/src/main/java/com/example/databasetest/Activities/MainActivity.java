package com.example.databasetest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.databasetest.Adapters.CustomerInfoAdapter;
import com.example.databasetest.Model.CustomerInfoModel;
import com.example.databasetest.Model.CustomerUtils;
import com.example.databasetest.databinding.ActivityMainBinding;
import com.example.databasetest.databinding.CustomerInfoBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    public ActivityMainBinding binding;
    ArrayList<CustomerInfoModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

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
        CustomerUtils customerUtils;
        arrayList = CustomerUtils.getInstance().getAll();


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerUtils.getInstance().addCustomer(new CustomerInfoModel(binding.etName.getText().toString(), binding.etId.getText().toString(), binding.cbMale.isChecked()));
                binding.etName.setText("");
                binding.etId.setText("");
                binding.cbMale.setChecked(false);
                CustomerInfoAdapter customerInfoAdapter = new CustomerInfoAdapter(arrayList, getApplicationContext());
                binding.rvCustomerInfo.setAdapter(customerInfoAdapter);


            }
        });




    }

}
