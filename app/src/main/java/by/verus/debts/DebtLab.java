package by.verus.debts;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DebtLab {

    public static List<Debt> getAll() {
        return new Select()
                .from(Debt.class)
                .orderBy("date DESC")
                .execute();
    }

    public static Debt getById(long id) {
        return new Select()
                .from(Debt.class)
                .where("Id = ?", id)
                .executeSingle();
    }

    public static void save(Debt debt) {
        debt.save();
    }

    public static void generateDebts(int count) {
        Calendar calendar = Calendar.getInstance();

        ActiveAndroid.beginTransaction();
        try {
            for (int i = 1; i <= count; i++) {
                calendar.add(Calendar.DATE, -2);
                Date date = calendar.getTime();

                Debt newDebt = new Debt();

                newDebt.setName("Vasya " + i);
                newDebt.setSum(i * 100);
                newDebt.setDate(date);
                newDebt.setDebtor(((i % 2) == 0));

                save(newDebt);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }

    public static void deleteAll() {
        new Delete().from(Debt.class).execute();
    }

    public static void deleteById(long id) {
        new Delete()
                .from(Debt.class)
                .where("Id=?", id)
                .execute();
    }
}
