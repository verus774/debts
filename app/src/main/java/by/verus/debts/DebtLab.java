package by.verus.debts;


import android.arch.lifecycle.LiveData;

import java.util.List;

import by.verus.debts.db.dao.DebtDao;
import by.verus.debts.db.entity.Debt;
import by.verus.debts.model.Debtor;

public class DebtLab {

    private static DebtDao debtDao = App.getInstance().getDatabase().debtDao();

    public static LiveData<List<Debt>> getAll() {
        return debtDao.getAll();
    }

    public static LiveData<List<Debt>> getMyDebts() {
        return debtDao.getMyDebts();
    }

    public static LiveData<List<Debt>> getTheirDebts() {
        return debtDao.getTheirDebts();
    }

    public static LiveData<List<Debtor>> getDebtors() {
        return debtDao.getDebtors();
    }

    public static List<Debt> getAllSync() {
        return debtDao.getAllSync();
    }

    public static Debt getById(long id) {
        return debtDao.getById(id);
    }

    public static void save(Debt debt) {
        App.getInstance().getDatabase().debtDao().insert(debt);
    }

    public static void saveAll(List<Debt> debts) {
        debtDao.insertAll(debts);
    }

    public static void update(Debt debt) {
        debtDao.update(debt);
    }

    public static void deleteAll() {
        debtDao.deleteAll();
    }

    public static void deleteById(long id) {
        debtDao.deleteById(id);
    }

}
