package by.verus.debts.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.verus.debts.DebtLab;
import by.verus.debts.R;
import by.verus.debts.model.Debtor;


public class DebtorListFragment extends Fragment {
    private RecyclerView mDebtorRecyclerView;
    private DebtorAdapter mAdapter;


    public DebtorListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mAdapter = new DebtorAdapter(new ArrayList<Debtor>());

        DebtLab.getDebtors().observe(this, new Observer<List<Debtor>>() {
            @Override
            public void onChanged(@Nullable List<Debtor> debtors) {
                if (debtors != null) {
                    mAdapter.setDebtorList(debtors);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debtor_list, container, false);

        mDebtorRecyclerView = view.findViewById(R.id.debtor_recycler_view);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mDebtorRecyclerView.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lm.getOrientation());
        mDebtorRecyclerView.addItemDecoration(dividerItemDecoration);
        mDebtorRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
