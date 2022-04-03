package fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_data")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    protected int uid;

    @ColumnInfo(name = "username")
    protected String userName;

    @ColumnInfo(name = "password")
    protected String password;

    public User(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    public int getUid() {
        return this.uid;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id: " + this.getUid() + ", username: " + this.getUserName() + ", password: " + this.getPassword();
    }

//    public User(Date timestamp, String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.timestamp = timestamp;
//    }
}
