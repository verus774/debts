package by.verus.debts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class DebtAddPagerActivity extends AppCompatActivity {
    private static final String EXTRA_DEBT_ID = "by.verus.debts.debt_id";

    private ViewPager mViewPager;
    private List<Debt> mDebts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_add_pager);

        long crimeId = getIntent().getLongExtra(EXTRA_DEBT_ID, 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_debt_pager_view_pager);
        mDebts = DebtLab.getAll();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Debt debt = mDebts.get(position);
                return DebtAddFragment.newInstance(debt.getId());
            }

            @Override
            public int getCount() {
                return mDebts.size();
            }
        });

        for (int i = 0; i < mDebts.size(); i++) {
            if (mDebts.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        setTitle(R.string.activity_debt_add_pager_title);

    }

    public static Intent newIntent(Context context, long debtId) {
        Intent intent = new Intent(context, DebtAddPagerActivity.class);
        intent.putExtra(EXTRA_DEBT_ID, debtId);
        return intent;
    }
}
