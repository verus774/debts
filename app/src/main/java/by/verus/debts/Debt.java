package by.verus.debts;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "debts")
public class Debt {

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


    public boolean isDebtor() {
        return mDebtor;
    }

    public void setDebtor(boolean debtor) {
        mDebtor = debtor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getSum() {
        return mSum;
    }

    public void setSum(float sum) {
        mSum = sum;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    @Override
    public String toString() {
        return "debt: [" + this.mName + ", " + this.mSum + "]";
    }

}
