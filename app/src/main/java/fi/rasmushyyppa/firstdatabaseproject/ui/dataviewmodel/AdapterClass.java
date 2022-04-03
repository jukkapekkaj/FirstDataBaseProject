package fi.rasmushyyppa.firstdatabaseproject.ui.dataviewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;


import fi.rasmushyyppa.firstdatabaseproject.R;
import fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata.User;

public class AdapterClass extends ArrayAdapter<User> {

    private final Context mCtx;
    private ArrayAdapter<User> arrayAdapter;
    private LiveData<List<User>> liveData;
    private List<User> userList;

    public AdapterClass(@NonNull Context context, int textViewResourceId, LiveData<List<User>> livedata) {
        super(context, textViewResourceId, livedata.getValue());
        this.mCtx = context;
        this.liveData = livedata;
        this.userList = livedata.getValue();
        this.arrayAdapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_list_item_1, userList);
//        livedata.observeForever(users -> notifyDataSetChanged());
    }

    public ArrayAdapter<User> getArrayAdapter() {
        return arrayAdapter;
    }

    @Override
    public int getCount() {
        if (liveData.getValue() == null)
            return 0;
        return liveData.getValue().size();
    }

    //
    @Override
    public User getItem(int i) {
        return Objects.requireNonNull(liveData.getValue()).get(i);
    }

    @Override
    public long getItemId(int position) {
        return Objects.requireNonNull(liveData.getValue().get(position).getUid());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User userdata = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.userLvItemTV);

        textView.setText(userdata.getUserName());

        return null;
    }

    public void setUsersInList(List<User> userNames) {
//        liveData = new MutableLiveData<>();
        for (User user : userNames) {
            if (!liveData.getValue().contains(user)) {
                userList.add(user);
            }
        }
        notifyDataSetChanged();
    }

}


