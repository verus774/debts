package by.verus.debts;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DebtHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Debt mDebt;
    private TextView mNameTextView;
    private TextView mSumTextView;
    private TextView mDateTextView;
    private TextView mMoreTextView;


    public DebtHolder(View itemView) {
        super(itemView);

        mNameTextView = (TextView) itemView.findViewById(R.id.list_item_debt_name_text_view);
        mSumTextView = (TextView) itemView.findViewById(R.id.list_item_debt_sum_text_view);
        mDateTextView = (TextView) itemView.findViewById(R.id.list_item_debt_date_text_view);
        mMoreTextView = (TextView) itemView.findViewById(R.id.list_item_debt_more_text_view);

        itemView.setOnClickListener(this);
    }

    public void bindDebt(Debt debt) {
        mDebt = debt;

        mNameTextView.setText(mDebt.getName());
        mSumTextView.setText(String.valueOf(mDebt.getSum()));
        mDateTextView.setText(mDebt.getDate().toString());
    }

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = DebtAddActivity.newIntent(context, mDebt.getId());
        context.startActivity(intent);
    }
}
