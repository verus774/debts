package by.verus.debts;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.DebtHolder> {
    private List<Debt> mDebts;


    public DebtAdapter(List<Debt> debts) {
        mDebts = debts;
    }

    @Override
    public DebtHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_debt, parent, false);
        return new DebtHolder(view);
    }

    @Override
    public void onBindViewHolder(DebtHolder holder, int position) {
        Debt debt = mDebts.get(position);
        holder.bindDebt(debt);
    }

    @Override
    public int getItemCount() {
        return mDebts.size();
    }

    public void deleteDebt(int position) {
        mDebts.remove(position);
        notifyItemRemoved(position);
    }


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
            mSumTextView.setText(String.format(Locale.getDefault(), "%.2f", mDebt.getSum()));
            mDateTextView.setText(DateUtils.getStrFromDate(mDebt.getDate()));

            mMoreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final Context context = v.getContext();

                    PopupMenu popup = new PopupMenu(context, v);
                    popup.inflate(R.menu.menu_item_debt);

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_delete_debt:
                                    DebtLab.deleteById(mDebt.getId());
                                    deleteDebt(getAdapterPosition());
                                    return true;
                            }
                            return false;
                        }
                    });

                    popup.show();

                }
            });
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = DebtAddActivity.newIntent(context, mDebt.getId());
            context.startActivity(intent);
        }
    }

}
