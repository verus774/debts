package by.verus.debts.ui.tabs;

import android.support.v4.app.Fragment;

import by.verus.debts.ui.FragmentActivity;

public class TabActivity extends FragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TabFragment();
    }

}
