package by.verus.debts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class DebtAddActivity extends AppCompatActivity {

    private static final String EXTRA_DEBT_ID = "by.verus.debts.debt_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        long crimeId = getIntent().getLongExtra(EXTRA_DEBT_ID, 0);

        if (fragment == null) {
            fragment = DebtAddFragment.newInstance(crimeId);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    public static Intent newIntent(Context context, long crimeId) {
        Intent intent = new Intent(context, DebtAddActivity.class);
        intent.putExtra(EXTRA_DEBT_ID, crimeId);
        return intent;
    }
}
