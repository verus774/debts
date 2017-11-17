package by.verus.debts.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.verus.debts.db.entity.Debt;
import by.verus.debts.model.Debtor;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DebtDao {
    @Query("SELECT * FROM debts ORDER BY date DESC")
    LiveData<List<Debt>> getAll();

    @Query("SELECT * FROM debts ORDER BY date DESC")
    List<Debt> getAllSync();

    @Query("SELECT * FROM debts WHERE debtor = 0 ORDER BY date DESC")
    LiveData<List<Debt>> getMyDebts();

    @Query("SELECT * FROM debts WHERE debtor = 1 ORDER BY date DESC")
    LiveData<List<Debt>> getTheirDebts();

    @Query("SELECT name, debtor, SUM(sum) AS sum FROM debts GROUP BY name ORDER BY name ASC")
    LiveData<List<Debtor>> getDebtors();

    @Insert(onConflict = REPLACE)
    void insert(Debt debt);

    @Insert(onConflict = REPLACE)
    void insertAll(List<Debt> debts);

    @Delete
    void delete(Debt debt);

    @Query("DELETE FROM debts WHERE id = :id")
    void deleteById(long id);

    @Update
    void update(Debt debt);

    @Query("DELETE FROM debts")
    void deleteAll();

    @Query("SELECT * FROM debts WHERE id = :id")
    Debt getById(long id);
}
