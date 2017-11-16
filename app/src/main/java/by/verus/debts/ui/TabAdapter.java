package by.verus.debts.ui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
/*
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
*/

    /*public TabAdapter(FragmentManager fm) {
        super(fm);
    }*/

    int mNumOfTabs;

    public TabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    /*public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }*/

    @Override
    public Fragment getItem(int position) {
//        return mFragmentList.get(position);

        switch (position) {
            case 0:
                return new MyDebtListFragment();
            case 1:
                return new TheirDebtListFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
//        return mFragmentList.size();
        return mNumOfTabs;
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }*/

}
