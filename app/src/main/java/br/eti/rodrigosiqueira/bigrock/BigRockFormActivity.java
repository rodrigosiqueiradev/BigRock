package br.eti.rodrigosiqueira.bigrock;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class BigRockFormActivity extends AppCompatActivity {

    private int mYear;
    private int mMonth;
    private int mDay;

    private TextView mDateDisplay;
    private TextView nDateDisplay1;
    private Button mPickDate;

    static final int DATE_DIALOG_ID = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rock_form);

        mDateDisplay = (TextView) findViewById(R.id.showMyDate);
        nDateDisplay1 = (TextView) findViewById(R.id.showMyDate1);
        mPickDate = (Button) findViewById(R.id.myDatePickerButton);

        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        updateDisplay();

        Button btnSave = (Button) findViewById(R.id.btnSave);
        Button btnBack = (Button) findViewById(R.id.btnBack);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText name = (EditText) findViewById(R.id.nmAluno);
                //EditText email = (EditText) findViewById(R.id.dsEmail);


                /*Stutend stutend = new Stutend();

                stutend.setId("1");
                stutend.setName(name.getText().toString());
                stutend.setEmail(email.getText().toString());


                Intent newIntent = getIntent().putExtra("student", stutend);
                setResult(RESULT_OK, newIntent);*/
                finish();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void updateDisplay() {
        this.mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mMonth + 1).append("/")
                        .append(mDay).append("/")
                        .append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
}
