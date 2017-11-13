package by.verus.debts.ui;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment {
    private static DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private DatePicker mDatePicker;

    private static final String ARG_DATE = "date";


    public static DatePickerFragment newInstance(Date date, DatePickerDialog.OnDateSetListener onDateSetListener) {
        mOnDateSetListener = onDateSetListener;

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), mOnDateSetListener, year, month, day);
    }
}
