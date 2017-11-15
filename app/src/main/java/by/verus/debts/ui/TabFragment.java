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
import android.view.View;
import android.view.ViewGroup;

import by.verus.debts.R;


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

        mViewPager = view.findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        mTabLayout = view.findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton addDebtFab = view.findViewById(R.id.add_debt_fab);
        addDebtFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = DebtAddActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter tabAdapter = new TabAdapter(getChildFragmentManager());
        tabAdapter.addFragment(new MyDebtListFragment(), "My debts");
        tabAdapter.addFragment(new TheirDebtListFragment(), "Their debts");

        viewPager.setAdapter(tabAdapter);
    }

}
