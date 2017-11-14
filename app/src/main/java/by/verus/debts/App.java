package by.verus.debts;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import by.verus.debts.db.AppDatabase;
import by.verus.debts.db.DatabaseMockUtils;

import static by.verus.debts.db.AppDatabase.DATABASE_NAME;


public class App extends Application {
    private static App instance;
    private AppDatabase database;

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        if (database == null) {
            database =
                    Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = prefs.getBoolean("first_run", true);

        if (isFirstRun) {
            DatabaseMockUtils.populateMockDataAsync();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_run", false);
            editor.apply();
        }

    }
}
