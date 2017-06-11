package br.eti.rodrigosiqueira.bigrock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class BigRockActivity extends AppCompatActivity {

    private static final int STUDENT_REQUEST_CODE = 200;
    private final String[] options = new String[]{
      "TAREFAS", "RESUMO"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rock);

        CardView cardView1 = (CardView) findViewById(R.id.cv);
        CardView cardView2 = (CardView) findViewById(R.id.cv1);

        cardView1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), BigRockListActivity.class);
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SliderTarefas.class);
                startActivity(intent);
            }
        });

    }
}
