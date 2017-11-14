package by.verus.debts.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.verus.debts.DebtLab;
import by.verus.debts.R;
import by.verus.debts.db.DatabaseMockUtils;
import by.verus.debts.db.entity.Debt;


public class DebtListFragment extends Fragment {
    private RecyclerView mDebtRecyclerView;
    private DebtAdapter mAdapter;


    public DebtListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_debt_list, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mDebtRecyclerView = view.findViewById(R.id.debt_recycler_view);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mDebtRecyclerView.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lm.getOrientation());
        mDebtRecyclerView.addItemDecoration(dividerItemDecoration);
        mDebtRecyclerView.setAdapter(mAdapter);

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
