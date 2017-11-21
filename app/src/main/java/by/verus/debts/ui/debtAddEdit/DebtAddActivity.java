package by.verus.debts.ui.debtAddEdit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import by.verus.debts.ui.FragmentActivity;

public class DebtAddActivity extends FragmentActivity {
    private static final String EXTRA_IS_DEBTOR = "by.verus.debts.is_debtor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        boolean isDebtor = getIntent().getBooleanExtra(EXTRA_IS_DEBTOR, false);
        return DebtAddFragment.newInstance(isDebtor);
    }

    public static Intent newIntent(Context context, boolean isDebtor) {
        Intent intent = new Intent(context, DebtAddActivity.class);
        intent.putExtra(EXTRA_IS_DEBTOR, isDebtor);
        return intent;
    }
}
