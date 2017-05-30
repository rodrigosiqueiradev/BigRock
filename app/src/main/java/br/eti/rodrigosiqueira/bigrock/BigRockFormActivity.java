package br.eti.rodrigosiqueira.bigrock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BigRockFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rock_form);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        Button btnBack = (Button) findViewById(R.id.btnBack);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.nmAluno);
                EditText email = (EditText) findViewById(R.id.dsEmail);


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
}
