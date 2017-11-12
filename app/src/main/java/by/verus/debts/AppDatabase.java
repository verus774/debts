package by.verus.debts;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import by.verus.debts.dao.DebtDao;


@Database(entities = {Debt.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract DebtDao debtDao();
}
