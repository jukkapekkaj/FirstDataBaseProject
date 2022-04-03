package fi.rasmushyyppa.firstdatabaseproject.ui.dataviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata.DatabaseController;
import fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata.User;

public class DataViewModel extends AndroidViewModel {
    private DatabaseController dataController;
    private LiveData<List<User>> allUserData;
//    private List<User> allUsersList;

    public DataViewModel(@NonNull Application application) {
        super(application);
        if (application == null) {
            throw new NullPointerException("application is marked @NonNull but is null");
        }
        dataController = new DatabaseController(application);
        allUserData = dataController.getAllUserRecords();
//        allUsersList = dataController.getAllUserList();
    }

//    public Future<List<User>> findByTime(long start, long end) {
//        return dataController.findByTime(start, end);
//    }

    public void findByName(String first, String last) {
        dataController.findByName(first, last);
    }

    public void delete(User newUser) {
        dataController.delete(newUser);
    }

    public void insert(User newUser) {
        dataController.insert(newUser);
    }

    public void insertAll(List<User> newUserList) {
        dataController.insertAll(newUserList);
    }

    public void update(User updateUser) {
        dataController.update(updateUser);
    }

    public void updateAll(List<User> updateList) {
        dataController.updateAll(updateList);
    }

    public LiveData<List<User>> getAllUserRecords() {
        return this.allUserData;
    }


}
