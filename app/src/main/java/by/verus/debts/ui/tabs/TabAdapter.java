package by.verus.debts.ui.tabs;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import by.verus.debts.ui.debtList.MyDebtListFragment;
import by.verus.debts.ui.debtList.TheirDebtListFragment;
import by.verus.debts.ui.debtorList.DebtorListFragment;

public class TabAdapter extends FragmentPagerAdapter {
    private int mNumOfTabs;

    public TabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyDebtListFragment();
            case 1:
                return new TheirDebtListFragment();
            case 2:
                return new DebtorListFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public Fragment getFragment(int key) {
        return getItem(key);
    }

}
