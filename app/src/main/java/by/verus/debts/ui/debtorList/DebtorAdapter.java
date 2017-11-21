package by.verus.debts.ui.debtorList;


import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import by.verus.debts.R;
import by.verus.debts.model.Debtor;

public class DebtorAdapter extends RecyclerView.Adapter<DebtorAdapter.DebtorHolder> {
    private List<Debtor> mDebtors;
    private Context mContext;


    public DebtorAdapter(List<Debtor> debtors) {
        mDebtors = debtors;
    }

    public void setDebtList(List<Debtor> debtors) {
        mDebtors = debtors;
    }

    public void setDebtorList(List<Debtor> debtors) {
        mDebtors = debtors;
        notifyDataSetChanged();
    }

    public List<Debtor> getData() {
        return mDebtors;
    }

    @Override
    public DebtorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_debtor, parent, false);
        mContext = view.getContext();
        return new DebtorHolder(view);
    }

    @Override
    public void onBindViewHolder(DebtorHolder holder, int position) {
        Debtor debtor = mDebtors.get(position);
        holder.bindDebt(debtor);
    }

    @Override
    public int getItemCount() {
        return mDebtors.size();
    }

    public class DebtorHolder extends RecyclerView.ViewHolder {
        private Debtor mDebtor;
        private TextView mNameTextView;
        private TextView mSumTextView;

        public DebtorHolder(View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.list_item_debtor_name_text_view);
            mSumTextView = itemView.findViewById(R.id.list_item_debtor_sum_text_view);
        }

        public void bindDebt(Debtor debtor) {
            mDebtor = debtor;

            mNameTextView.setText(mDebtor.getName());
            mSumTextView.setText(String.format(Locale.getDefault(), "%.2f", mDebtor.getSum()));
            mSumTextView.setTextColor(mDebtor.isDebtor() ?
                    ResourcesCompat.getColor(mContext.getResources(), R.color.item_debt_their_debt, null) :
                    ResourcesCompat.getColor(mContext.getResources(), R.color.item_debt_my_debt, null)
            );

        }

    }

}
