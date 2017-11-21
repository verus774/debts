package by.verus.debts.ui.debtList;


import android.support.v7.util.DiffUtil;

import java.util.List;

import by.verus.debts.db.entity.Debt;

public class DebtDiffUtilCallback extends DiffUtil.Callback {
    private final List<Debt> oldList;
    private final List<Debt> newList;

    public DebtDiffUtilCallback(List<Debt> oldList, List<Debt> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Debt oldDebt = oldList.get(oldItemPosition);
        Debt newDebt = newList.get(newItemPosition);
        return oldDebt.getId() == newDebt.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Debt oldDebt = oldList.get(oldItemPosition);
        Debt newDebt = newList.get(newItemPosition);
        return oldDebt.getName().equals(newDebt.getName())
                && oldDebt.getSum() == newDebt.getSum()
                && oldDebt.isDebtor() == newDebt.isDebtor();
    }
}
