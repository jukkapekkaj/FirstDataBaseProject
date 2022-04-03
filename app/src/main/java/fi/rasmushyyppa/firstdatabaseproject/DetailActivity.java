package fi.rasmushyyppa.firstdatabaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.List;

import fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata.User;
import fi.rasmushyyppa.firstdatabaseproject.ui.dataviewmodel.DataViewModel;

public class DetailActivity extends AppCompatActivity {

    private Intent intent; //intent from the MainActivity
    private int selectedUser; //we storage from intent our int here
    private User user; // we need to single out one president
    private List<User> userList; //List of the users from global
    private DataViewModel dataViewModel;
    private TextView username, password, id, date; //all the textviews for UI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        loadAndFind(); //Find our Text Views first
        getUser(); //Get our president from Intent & insert data from global president list
        updateUI(); //Update our textviews with our president data

    }

    //Find our text views
    private void loadAndFind() {
        dataViewModel = MainActivity.getDataViewModel();
        username = findViewById(R.id.username);
        password = findViewById(R.id.description);
        id = findViewById(R.id.id);
        date = findViewById(R.id.date);
    }

    /* Here we get our president what we clicked in screen. First we use intent to get it.
     *  Then we transfer it back to integer so we can use list .get function with it*/
    private void getUser() {
        intent = getIntent();
        selectedUser = Integer.parseInt(intent.getStringExtra(MainActivity.getReplyUserData())); //back to int
        userList = dataViewModel.getAllUserRecords().getValue();
        if (userList.get(selectedUser) == null) {
            return;
        }
        user = userList.get(selectedUser);
    }

    //Update our president data to textviews
    private void updateUI() {
        username.setText(user.getUserName());
        password.setText(user.getPassword());
        id.setText(user.getUid());
        date.setText(new Date().toString());
    }


}
