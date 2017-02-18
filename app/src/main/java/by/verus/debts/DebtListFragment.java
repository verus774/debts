package by.verus.debts;

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

import java.util.List;


public class DebtListFragment extends Fragment {
    private RecyclerView mDebtRecyclerView;
    private DebtAdapter mAdapter;
    private FloatingActionButton mAddDebtFab;


    public DebtListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debt_list, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mDebtRecyclerView = (RecyclerView) view.findViewById(R.id.debt_recycler_view);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mDebtRecyclerView.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lm.getOrientation());
        mDebtRecyclerView.addItemDecoration(dividerItemDecoration);
        updateList();

        mAddDebtFab = (FloatingActionButton) view.findViewById(R.id.add_debt_fab);
        mAddDebtFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = DebtAddActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
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
                updateList();
                return true;
            case R.id.action_generate_debts:
                DebtLab.generateDebts(10);
                updateList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void updateList() {
        List<Debt> debts = DebtLab.getAll();
        mAdapter = new DebtAdapter(debts);
        mDebtRecyclerView.setAdapter(mAdapter);
    }
}
