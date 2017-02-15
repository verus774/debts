package by.verus.debts;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

import java.util.Date;

public class Debt extends Model {

    @Column(name = "name")
    private String mName;

    @Column(name = "sum")
    private int mSum;

    @Column(name = "date", index = true)
    private Date mDate;


    public Debt() {
        super();
        mDate = new Date();
    }

    public Debt(String name, int sum, Date date) {
        super();

        mName = name;
        mSum = sum;
        mDate = new Date();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getSum() {
        return mSum;
    }

    public void setSum(int sum) {
        mSum = sum;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    @Override
    public String toString() {
        return "debt: [" + this.mName + ", " + this.mSum + "]";
    }

}
