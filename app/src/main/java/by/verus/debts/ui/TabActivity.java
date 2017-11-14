package by.verus.debts.ui;

import android.support.v4.app.Fragment;

public class TabActivity extends FragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TabFragment();
    }

}
