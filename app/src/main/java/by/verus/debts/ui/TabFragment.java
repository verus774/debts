package by.verus.debts.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import by.verus.debts.DebtLab;
import by.verus.debts.R;
import by.verus.debts.db.DatabaseMockUtils;


public class TabFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public TabFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mTabLayout = view.findViewById(R.id.tabLayout);
        mViewPager = view.findViewById(R.id.viewPager);

        TabAdapter tabAdapter = new TabAdapter(getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(tabAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton addDebtFab = view.findViewById(R.id.add_debt_fab);
        addDebtFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = mViewPager.getCurrentItem();
                TabAdapter adapter = ((TabAdapter) mViewPager.getAdapter());
                Fragment fragment = adapter.getFragment(index);

                boolean isDebtor = fragment instanceof TheirDebtListFragment;

                Intent intent = DebtAddActivity.newIntent(getActivity(), isDebtor);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_debt_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_all:
                DebtLab.deleteAll();
                return true;
            case R.id.action_generate_debts:
                DatabaseMockUtils.populateMockDataAsync();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
