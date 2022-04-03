package fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_data")
    LiveData<List<User>> getAll();

//    @Query("SELECT * FROM user_data")
//    List<User> getAllList();


    @Query("SELECT * FROM user_data WHERE username LIKE :first AND " +
            "password LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(User updateUser);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateAll(List<User> updateList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<User> userList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User newUser);

    @Delete
    void delete(User user);
}