package by.verus.debts.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class DebtAddActivity extends FragmentActivity {

    @Override
    protected Fragment createFragment() {
        return DebtAddFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, DebtAddActivity.class);
    }
}
