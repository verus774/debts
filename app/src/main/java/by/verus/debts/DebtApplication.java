package by.verus.debts;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class DebtApplication extends Application {
    private static final String DATABASE_NAME = "debts.db";
    public static DebtApplication instance;
    private AppDatabase database;

    public static DebtApplication get() {
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
            DebtLab.generateDebts();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_run", false);
            editor.apply();
        }

    }
}
