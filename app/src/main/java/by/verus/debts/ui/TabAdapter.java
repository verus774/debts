package by.verus.debts.ui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

}
