package by.verus.debts;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;


@Table(name = "debts")
public class Debt extends Model {

    @Column(name = "name")
    private String mName;

    @Column(name = "sum")
    private float mSum;

    @Column(name = "date", index = true)
    private Date mDate;

    @Column(name = "debtor")
    private boolean mDebtor;


    public Debt() {
        super();
        mDate = new Date();
    }


    public Debt(String name, int sum, boolean debtor) {
        super();

        mName = name;
        mSum = sum;
        mDebtor = debtor;
        mDate = new Date();
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

    public boolean isDebtor() {
        return mDebtor;
    }

    public void setDebtor(boolean debtor) {
        mDebtor = debtor;
    }

    @Override
    public String toString() {
        return "debt: [" + this.mName + ", " + this.mSum + "]";
    }

}
