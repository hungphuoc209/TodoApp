package com.example.databasetest.Adapters.RealmContext;
import com.example.databasetest.Model.CustomerInfoModel;
import java.util.Objects;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmContext {
    private Realm realm;
    public static RealmContext instance;

    public RealmContext(Realm realm) {
        this.realm = Realm.getDefaultInstance();
        this.realm = realm;
    }

    public static RealmContext getInstance(Realm realm) {
        if (instance == null) instance = new RealmContext(realm);
        return instance;
    }

    public RealmResults<CustomerInfoModel> getAll() {
        {
            realm.beginTransaction();
            RealmResults<CustomerInfoModel> realmArray = realm.where(CustomerInfoModel.class).findAll();
            realm.commitTransaction();
            return realmArray;
        }

    }

    public void addCustomer(final String task, final String description, boolean isSelected) {
       realm.executeTransaction(new Realm.Transaction() {
           @Override
           public void execute(Realm realm) {
               Number maxId = realm.where(CustomerInfoModel.class).max("ID");
               final long ID = (maxId != null) ? (long) maxId + 1 : 0;
               CustomerInfoModel newCustomerInfoModel = realm.createObject(CustomerInfoModel.class, ID);
               newCustomerInfoModel.setDescription(description);
               newCustomerInfoModel.setTask(task);
               newCustomerInfoModel.setSelected(false);
           }
       });
    }

    public void deleteCustomer(final long id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CustomerInfoModel infoModel = realm.where(CustomerInfoModel.class).equalTo("ID", id).findFirst();
                if (infoModel != null) {
                    infoModel.deleteFromRealm();
                }
            }
        });
    }

    public void SetChangedListener(final boolean isChecked, final int position) {
        final long id = Objects.requireNonNull(getAll().get(position)).getID();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CustomerInfoModel customerInfoModel =realm.where(CustomerInfoModel.class).equalTo("ID",id).findFirst();
                if(isChecked){
                    customerInfoModel.setSelected(true);
                }
                else {
                    customerInfoModel.setSelected(false);
                }

            }
        });
    }
}
