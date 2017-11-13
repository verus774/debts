package by.verus.debts.model;

import java.util.Date;


public interface IDebt {
    boolean isDebtor();

    void setDebtor(boolean debtor);

    String getName();

    void setName(String name);

    float getSum();

    void setSum(float sum);

    Date getDate();

    void setDate(Date date);

    long getId();

    void setId(long id);
}
