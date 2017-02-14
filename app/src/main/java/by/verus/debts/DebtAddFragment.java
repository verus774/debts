package by.verus.debts;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
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

import static android.app.Activity.RESULT_OK;
import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;


public class DebtAddFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private Debt mDebt;
    private EditText mDebtNameEditText;
    private EditText mDebtSumEditText;
    private Button mDebtDateButton;
    private Button mDebtSaveButton;
    private ImageButton mPickContactImageButton;
    private AwesomeValidation mAwesomeValidation;

    private final static int CONTACT_PICKER = 1;
    private static final String DIALOG_DATE = "DialogDate";


    public DebtAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debt_add, container, false);

        mDebtNameEditText = (EditText) view.findViewById(R.id.debt_name_edit_text);
        mDebtSumEditText = (EditText) view.findViewById(R.id.debt_sum_edit_text);

        mPickContactImageButton = (ImageButton) view.findViewById(R.id.pick_contact_image_button);
        mPickContactImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickContact();
            }
        });


        mDebtDateButton = (Button) view.findViewById(R.id.debt_date_button);
        Date date = new Date();
        mDebtDateButton.setText(date.toString());
        mDebtDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(new Date(), DebtAddFragment.this);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mDebt = new Debt();

        mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(mDebtNameEditText, RegexTemplate.NOT_EMPTY, getString(R.string.err_required));
        mAwesomeValidation.addValidation(mDebtSumEditText, RegexTemplate.NOT_EMPTY, getString(R.string.err_required));

        mDebtSaveButton = (Button) view.findViewById(R.id.debt_save_button);
        mDebtSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAwesomeValidation.validate()) {
                    mDebt.setName(mDebtNameEditText.getText().toString());
                    mDebt.setSum(Integer.parseInt(mDebtSumEditText.getText().toString()));

                    DebtLab.save(mDebt);

                    getActivity().finish();
                }
            }
        });

        return view;
    }

    private void pickContact() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER);
    }

    private String getContactName(Intent data) {
        Uri contactData = data.getData();
        Cursor cursor = getActivity().getContentResolver().query(contactData, null, null, null, null);

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
        mDebtDateButton.setText(newDate.toString());
    }
}
