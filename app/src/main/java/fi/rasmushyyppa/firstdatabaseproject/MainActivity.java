package fi.rasmushyyppa.firstdatabaseproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata.User;
import fi.rasmushyyppa.firstdatabaseproject.ui.dataviewmodel.AdapterClass;
import fi.rasmushyyppa.firstdatabaseproject.ui.dataviewmodel.DataViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // TAG for our Log.d so we can find them
    private static final String REPLY_USER_DATA = "fi.rasmushyyppa.firstdatabaseproject.REPLY_USER_DATA";
    private LiveData<List<User>> userList;
    private ListView userlv; // list view for users in NewUserActivity
    private static DataViewModel dataViewModel;
    private AdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        userList = dataViewModel.getAllUserRecords();
        if (dataViewModel.getAllUserRecords().getValue() == null) {
            userList = new MutableLiveData<>();
            loadUserList();
        }
        Log.d(TAG, "getValue(): " + userList.getValue());
        userlv = findViewById(R.id.userlv);

        userList.observe(this, users -> {
            adapter = new AdapterClass(this, android.R.layout.activity_list_item, userList);
            Log.d(TAG, "Updating list of users from LiveData in ViewModel");
            adapter.setUsersInList(users);
            userlv.setAdapter(adapter);
            userlv.setOnItemClickListener((parent, view, position, id) -> {
                Intent nextActivity = new Intent(MainActivity.this, DetailActivity.class);
                nextActivity.putExtra(REPLY_USER_DATA, position);
                startActivity(nextActivity);
            });
        });


//        userList = users1;
//        userList = dataViewModel.getAllUserRecords().getValue();
    }

    protected void onStart() {
        super.onStart();
    }

    public void addNewUser(View caller) {
        EditText userName = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextDescription);
        dataViewModel.insert(new User(userName.getText().toString(), password.getText().toString()));
        userName.getText().clear();
        password.getText().clear();
    }

    public static DataViewModel getDataViewModel() {
        return dataViewModel;
    }

    public static String getReplyUserData() {
        return REPLY_USER_DATA;
    }


    private void loadUserList() {
        List<User> users1 = new ArrayList<>();
        users1.add(new User("Tiger", "baby"));
        users1.add(new User("Dolphin", "gaga"));
        users1.add(new User("Elefant", "lemur"));
        users1.add(new User("OG", "Otter"));
        users1.add(new User("rehu", "puntti"));
        users1.add(new User("Pekka", "kissa1kettu2"));
        users1.add(new User("Lepakkomies", "onBaari"));
        users1.add(new User("Nakit", "Silmille"));
        users1.add(new User("Vihannes", "Hanuriin"));
        users1.add(new User("Ulinapetteri", "Kolinamittari"));
        dataViewModel.insertAll(users1);
        userList = dataViewModel.getAllUserRecords();
    }

}