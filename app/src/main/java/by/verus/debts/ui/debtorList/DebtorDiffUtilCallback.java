package by.verus.debts.ui.debtorList;


import android.support.v7.util.DiffUtil;

import java.util.List;

import by.verus.debts.model.Debtor;

public class DebtorDiffUtilCallback extends DiffUtil.Callback {
    private final List<Debtor> oldList;
    private final List<Debtor> newList;

    public DebtorDiffUtilCallback(List<Debtor> oldList, List<Debtor> newList) {
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
        Debtor oldDebt = oldList.get(oldItemPosition);
        Debtor newDebt = newList.get(newItemPosition);
        return oldDebt.getName().equals(newDebt.getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Debtor oldDebt = oldList.get(oldItemPosition);
        Debtor newDebt = newList.get(newItemPosition);
        return oldDebt.isDebtor() == newDebt.isDebtor();
    }
}
