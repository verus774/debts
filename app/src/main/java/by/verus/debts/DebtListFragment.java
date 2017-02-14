package by.verus.debts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }

    private void updateList() {
        List<Debt> debts = DebtLab.getAll();
        mAdapter = new DebtAdapter(debts);
        mDebtRecyclerView.setAdapter(mAdapter);
    }
}
