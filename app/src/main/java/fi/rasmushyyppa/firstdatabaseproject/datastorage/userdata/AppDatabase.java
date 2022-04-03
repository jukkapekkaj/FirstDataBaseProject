package fi.rasmushyyppa.firstdatabaseproject.datastorage.userdata;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final int THREAD_AMOUNT = 4;

    static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_AMOUNT);

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "user_database")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
}
