package by.verus.debts.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.verus.debts.Debt;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DebtDao {
    @Query("SELECT * FROM debts ORDER BY date DESC")
    LiveData<List<Debt>> getAll();

    @Query("SELECT * FROM debts ORDER BY date DESC")
    List<Debt> getAllSync();

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
