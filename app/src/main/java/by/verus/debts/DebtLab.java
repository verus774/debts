package by.verus.debts;


import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DebtLab {

    public static LiveData<List<Debt>> getAll() {
        return DebtApplication.get().getDatabase().debtDao().getAll();
    }

    public static List<Debt> getAllSync() {
        return DebtApplication.get().getDatabase().debtDao().getAllSync();
    }

    public static Debt getById(long id) {
        return DebtApplication.get().getDatabase().debtDao().getById(id);
    }

    public static void save(Debt debt) {
        DebtApplication.get().getDatabase().debtDao().insert(debt);
    }

    public static void saveAll(List<Debt> debts) {
        DebtApplication.get().getDatabase().debtDao().insertAll(debts);
    }

    public static void update(Debt debt) {
        DebtApplication.get().getDatabase().debtDao().update(debt);
    }

    public static void deleteAll() {
        DebtApplication.get().getDatabase().debtDao().deleteAll();
    }

    public static void deleteById(long id) {
        DebtApplication.get().getDatabase().debtDao().deleteById(id);
    }

    public static void generateDebts() {
        Calendar calendar = Calendar.getInstance();
        List<Debt> debts = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            calendar.add(Calendar.DATE, -2);
            Date date = calendar.getTime();

            Debt debt = new Debt();
            debt.setName("Vasya " + i);
            debt.setSum(i * 100);
            debt.setDate(date);
            debt.setDebtor(((i % 2) == 0));
            debts.add(debt);
        }

        saveAll(debts);
    }
}
