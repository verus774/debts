package by.verus.debts.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import by.verus.debts.model.IDebt;


@Entity(tableName = "debts")
public class Debt implements IDebt {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "sum")
    private float mSum;

    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "debtor")
    private boolean mDebtor;

    public Debt(String name, float sum, Date date, boolean debtor) {
        mName = name;
        mSum = sum;
        mDate = date;
        mDebtor = debtor;
    }

    @Override
    public boolean isDebtor() {
        return mDebtor;
    }

    @Override
    public void setDebtor(boolean debtor) {
        mDebtor = debtor;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        mName = name;
    }

    @Override
    public float getSum() {
        return mSum;
    }

    @Override
    public void setSum(float sum) {
        mSum = sum;
    }

    @Override
    public Date getDate() {
        return mDate;
    }

    @Override
    public void setDate(Date date) {
        mDate = date;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public void setId(long id) {
        this.mId = id;
    }

    @Override
    public String toString() {
        return "debt: [" + this.mName + ", " + this.mSum + "]";
    }

}
