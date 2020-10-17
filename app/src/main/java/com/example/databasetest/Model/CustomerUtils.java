package com.example.databasetest.Model;

import android.widget.Toast;

import com.example.databasetest.Activities.MainActivity;
import com.example.databasetest.Adapters.CustomerInfoAdapter;

import java.util.ArrayList;

public class CustomerUtils {

    private CustomerUtils() {
        arrayList = new ArrayList<>();
    }
    private static CustomerUtils customerUtils;
    public  static  CustomerUtils getInstance(){
        if(customerUtils ==null){
            customerUtils = new CustomerUtils();
        }
        return customerUtils;
    }

    private ArrayList<CustomerInfoModel> arrayList;

    public ArrayList<CustomerInfoModel> getAll(){
        return arrayList;
    }
    public void addCustomer(CustomerInfoModel customerInfoModel) {
        if (arrayList.isEmpty()) {
            customerInfoModel.setID(0);
        }
        else {
            int lastId = (int) arrayList.get(arrayList.size()-1).getID();
            customerInfoModel.setID(lastId+1);
        }
        arrayList.add(customerInfoModel);

    }
    private CustomerInfoModel getCustomerbyID(int id){
        for(CustomerInfoModel customerInfoModel : arrayList) {
            if(customerInfoModel.getID() == id) return customerInfoModel;
            }
        return null;

    }
    public void deleteCustomer(int id) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getID() == id) {
                arrayList.remove(i);
                return;
            }
        }
    }
}
