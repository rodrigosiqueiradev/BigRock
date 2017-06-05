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
                intent = new Intent(getApplicationContext(), BigRockGridActivity.class);
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

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), BigRockGridActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), SliderTarefas.class);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                }
            }
        });
    }

    protected void openWindows(int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getApplicationContext(), BigRockGridActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getApplicationContext(), SliderTarefas.class);
                startActivity(intent);
                break;
            case 2:
                break;
        }
    }
}
