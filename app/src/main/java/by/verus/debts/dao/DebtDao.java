package by.verus.debts.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.verus.debts.Debt;

@Dao
public interface DebtDao {
    @Query("SELECT * FROM debts ORDER BY date DESC")
    List<Debt> getAll();

    @Insert
    void insert(Debt debt);

    @Insert
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
