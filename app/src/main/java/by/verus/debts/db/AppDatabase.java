package by.verus.debts.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import by.verus.debts.db.converter.DateTypeConverter;
import by.verus.debts.db.dao.DebtDao;
import by.verus.debts.db.entity.Debt;


@Database(entities = {Debt.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "debts.db";

    public abstract DebtDao debtDao();
}
