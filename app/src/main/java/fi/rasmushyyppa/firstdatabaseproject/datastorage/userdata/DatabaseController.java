package fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Future;


public class DatabaseController {

    private UserDao userDao;
    private LiveData<List<User>> allUserRecords;
//    private List<User> allUsersList;

    public DatabaseController(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        allUserRecords = userDao.getAll();
//        allUsersList = userDao.getAllList();

    }

//    public Future<List<User>> findByTime(long start, long end) {
//        return fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata.AppDatabase.executor.submit(() -> userDao.findByTime(start, end));
//    }

    public void findByName(String first, String last) {
        AppDatabase.executor.execute(() -> userDao.findByName(first, last));
    }

    public void delete(User newUser) {
        AppDatabase.executor.execute(() -> userDao.delete(newUser));
    }

    public void insert(User newUser) {
        AppDatabase.executor.execute(() -> userDao.insert(newUser));
    }

    public void insertAll(List<User> newUserList) {
        AppDatabase.executor.execute(() -> userDao.insertAll(newUserList));
    }

    public void update(User updateUser) {
        AppDatabase.executor.execute(() -> userDao.update(updateUser));
    }

    public void updateAll(List<User> updateList) {
        AppDatabase.executor.execute(() -> userDao.updateAll(updateList));
    }

    public LiveData<List<User>> getAllUserRecords() {
        return this.allUserRecords;
    }

//    public List<User> getAllUserList() {
//        return this.allUsersList;
//    }

}