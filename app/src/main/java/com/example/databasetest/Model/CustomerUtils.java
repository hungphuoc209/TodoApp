package com.example.databasetest.Model;

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
            int lastId = arrayList.get(arrayList.size()-1).getID();
            customerInfoModel.setID(lastId+1);
        }
        arrayList.add(customerInfoModel);
    }
    public void updateCustomer(int id , CustomerInfoModel newCustomerInfoModel){
        CustomerInfoModel oldCustomer = getCustomerbyID(id);
        if(oldCustomer != null){
            oldCustomer.setmName(newCustomerInfoModel.getmName());
            oldCustomer.setmID(newCustomerInfoModel.getmID());
            oldCustomer.setmGender(newCustomerInfoModel.ismGender());
        }

    }
    private CustomerInfoModel getCustomerbyID(int id){
        for(CustomerInfoModel customerInfoModel : arrayList) {
            if(customerInfoModel.getID() == id) return customerInfoModel;
            }
        return null;

    }
    public void deteleCustomer(int id){
        for (int i = 0 ; i <arrayList.size();i++){
            if(arrayList.get(i).getID() == id){
                arrayList.remove(i);
                return;
            }
        }
    }
}
