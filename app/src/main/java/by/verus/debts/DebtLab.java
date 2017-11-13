package by.verus.debts;


import android.arch.lifecycle.LiveData;

import java.util.List;

import by.verus.debts.db.entity.Debt;

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

}
