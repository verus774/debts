package by.verus.debts.ui;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.Calendar;
import java.util.Date;

import by.verus.debts.DebtLab;
import by.verus.debts.R;
import by.verus.debts.db.entity.Debt;
import by.verus.debts.utils.DateUtils;

import static android.app.Activity.RESULT_OK;
import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;


public class DebtAddFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private Debt mDebt;
    private EditText mDebtNameEditText;
    private EditText mDebtSumEditText;
    private Button mDebtDateButton;
    private SwitchCompat mDebtorSwitch;

    private final static int CONTACT_PICKER = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_DEBT_ID = "debt_id";
    private static final String ARG_IS_DEBTOR = "is_debtor";


    public DebtAddFragment() {
    }

    public static DebtAddFragment newInstance(long crimeId) {
        Bundle args = new Bundle();
        args.putLong(ARG_DEBT_ID, crimeId);
        DebtAddFragment fragment = new DebtAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DebtAddFragment newInstance(boolean isDebtor) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_DEBTOR, isDebtor);
        DebtAddFragment fragment = new DebtAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DebtAddFragment newInstance() {
        Bundle args = new Bundle();
        DebtAddFragment fragment = new DebtAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_debt_add, container, false);

        mDebtNameEditText = view.findViewById(R.id.debt_name_edit_text);
        mDebtSumEditText = view.findViewById(R.id.debt_sum_edit_text);

        ImageButton pickContactImageButton = view.findViewById(R.id.pick_contact_image_button);
        pickContactImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickContact();
            }
        });

        mDebtorSwitch = view.findViewById(R.id.debtor_switch);
        mDebtorSwitch.setChecked(true);

        mDebtDateButton = view.findViewById(R.id.debt_date_button);
        mDebtDateButton.setText(DateUtils.getStrFromDate(new Date()));
        mDebtDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(new Date(), DebtAddFragment.this);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        long debtId = getArguments().getLong(ARG_DEBT_ID);
        boolean isDebtor = getArguments().getBoolean(ARG_IS_DEBTOR);

        if (debtId != 0) {
            mDebt = DebtLab.getById(debtId);

            mDebtNameEditText.append(mDebt.getName());
            mDebtSumEditText.append(String.valueOf(mDebt.getSum()));
            mDebtDateButton.setText(DateUtils.getStrFromDate(mDebt.getDate()));
            mDebtorSwitch.setChecked(mDebt.isDebtor());
        }

        mDebtorSwitch.setChecked(isDebtor);

        return view;
    }

    private void pickContact() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER);
    }

    private String getContactName(Intent data) {
        Uri contactData = data.getData();
        Cursor cursor = null;
        if (contactData != null) {
            cursor = getActivity().getContentResolver().query(contactData, null, null, null, null);
        }

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }
            cursor.close();
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CONTACT_PICKER:
                    mDebtNameEditText.setText("");
                    mDebtNameEditText.append(getContactName(data));
                    break;
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);
        Date newDate = c.getTime();

        mDebt.setDate(newDate);
        mDebtDateButton.setText(DateUtils.getStrFromDate(newDate));
    }

    private boolean isValidFields() {
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(mDebtNameEditText, RegexTemplate.NOT_EMPTY, getString(R.string.err_required));
        mAwesomeValidation.addValidation(mDebtSumEditText, RegexTemplate.NOT_EMPTY, getString(R.string.err_required));

        return mAwesomeValidation.validate();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_debt_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save_debt:
                if (isValidFields()) {
                    String name = mDebtNameEditText.getText().toString();
                    float sum = Float.parseFloat(mDebtSumEditText.getText().toString());
                    Date date = new Date();
                    boolean debtor = mDebtorSwitch.isChecked();

                    if (mDebt != null) {
                        mDebt.setName(name);
                        mDebt.setSum(sum);
                        mDebt.setDebtor(debtor);

                        DebtLab.update(mDebt);
                    } else {
                        mDebt = new Debt(name, sum, date, debtor);
                        DebtLab.save(mDebt);
                    }

                    getActivity().finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
