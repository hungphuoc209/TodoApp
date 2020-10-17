package com.example.databasetest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.databasetest.Adapters.CustomerInfoAdapter;
import com.example.databasetest.Adapters.RealmContext.RealmContext;
import com.example.databasetest.Model.CustomerInfoModel;
import com.example.databasetest.R;
import com.example.databasetest.databinding.ActivityMainBinding;
import com.example.databasetest.interf.OnCheckHelper;
import com.example.databasetest.interf.OnCustomItemClickListener;
import java.util.List;
import java.util.Objects;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
public class MainActivity extends AppCompatActivity implements OnCustomItemClickListener,OnCheckHelper {

    public ActivityMainBinding binding;
    RealmContext instance;
    CustomerInfoAdapter adapter;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        Objects.requireNonNull(getSupportActionBar()).hide();
    //  setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initView();
        OnAddClick();
    }

    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvCustomerInfo.setLayoutManager(layoutManager);
        realm = Realm.getDefaultInstance();
        instance = RealmContext.getInstance(realm);
        adapter = new CustomerInfoAdapter(instance.getAll(),this);
        adapter.setOnCheckChange(this);
        adapter.setOnItemClick(this);
        binding.rvCustomerInfo.setAdapter(adapter);

    }

    public void OnAddClick(){
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.etName.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Add your task please !!!", Toast.LENGTH_SHORT).show();
                }else
                {
                    instance.addCustomer(binding.etName.getText().toString(), binding.etId.getText().toString(),false);
                    binding.etName.setText("");
                    binding.etId.setText("");
                }
            }
        });
    }

    @Override
    public void OnItemClick(CustomerInfoModel customerInfoModel) {
        Toast.makeText(this, "HRhr"+customerInfoModel.getID()+customerInfoModel.getDescription(), Toast.LENGTH_SHORT).show();

    }
    @Override
    public void OnDeleteClick(long id) {
        for(int i = 0;i<instance.getAll().size();i++){
            if(Objects.requireNonNull(instance.getAll().get(i)).getID() == id){
                instance.deleteCustomer(id);
            }
        }
    }

    @Override
    public void OnSetChangedListener(boolean isChecked, int position) {
        if(!binding.rvCustomerInfo.isComputingLayout()){
            instance.SetChangedListener(isChecked, position);
        }
    }
}
