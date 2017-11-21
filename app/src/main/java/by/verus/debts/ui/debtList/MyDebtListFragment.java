package by.verus.debts.ui.debtList;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.verus.debts.R;
import by.verus.debts.db.DebtLab;
import by.verus.debts.db.entity.Debt;


public class MyDebtListFragment extends Fragment {
    private RecyclerView mDebtRecyclerView;
    private DebtAdapter mAdapter;


    public MyDebtListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mAdapter = new DebtAdapter(new ArrayList<Debt>());

        DebtLab.getMyDebts().observe(this, new Observer<List<Debt>>() {
            @Override
            public void onChanged(@Nullable List<Debt> debts) {
                if (debts != null) {
                    DebtDiffUtilCallback debtDiffUtilCallback = new DebtDiffUtilCallback(mAdapter.getData(), debts);
                    DiffUtil.DiffResult debtDiffResult = DiffUtil.calculateDiff(debtDiffUtilCallback);

                    mAdapter.setDebtList(debts);
                    debtDiffResult.dispatchUpdatesTo(mAdapter);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_debt_list, container, false);

        mDebtRecyclerView = view.findViewById(R.id.debt_recycler_view);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mDebtRecyclerView.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lm.getOrientation());
        mDebtRecyclerView.addItemDecoration(dividerItemDecoration);
        mDebtRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
