package by.verus.debts.db;


import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import by.verus.debts.DebtApplication;
import by.verus.debts.db.entity.Debt;

public class DatabaseMockUtils {

    private static AppDatabase db = DebtApplication.get().getDatabase();

    public static void populateMockDataSync() {
        populateWithTestData(db);
    }

    public static void populateMockDataAsync() {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }
    }

    private static void populateWithTestData(AppDatabase db) {
        db.beginTransaction();
        try {
            db.debtDao().deleteAll();
            generateTestData(db);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private static void generateTestData(AppDatabase db) {
        List<Debt> debts = generateTestDebts(10);
        db.debtDao().insertAll(debts);
    }

    private static List<Debt> generateTestDebts(int count) {
        List<Debt> debts = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        for (int i = 1; i <= count; i++) {
            calendar.add(Calendar.DATE, -2);
            Date date = calendar.getTime();

            Debt debt = new Debt();
            debt.setName("Vasya " + i);
            debt.setSum(i * 100);
            debt.setDate(date);
            debt.setDebtor(((i % 2) == 0));
            debts.add(debt);
        }

        return debts;
    }

}
