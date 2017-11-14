package by.verus.debts.ui;

import android.arch.lifecycle.Observer;
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

import java.util.ArrayList;
import java.util.List;

import by.verus.debts.DebtLab;
import by.verus.debts.R;
import by.verus.debts.db.entity.Debt;


public class TabFragment extends Fragment {
    private DebtAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public TabFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mAdapter = new DebtAdapter(new ArrayList<Debt>());

        DebtLab.getAll().observe(this, new Observer<List<Debt>>() {
            @Override
            public void onChanged(@Nullable List<Debt> debts) {
                if (debts != null) {
                    mAdapter.setDebtList(debts);
                }
            }
        });
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
        tabAdapter.addFragment(new DebtListFragment(), "I owe");
        tabAdapter.addFragment(new DebtListFragment(), "Owe me");

        viewPager.setAdapter(tabAdapter);
    }

}
