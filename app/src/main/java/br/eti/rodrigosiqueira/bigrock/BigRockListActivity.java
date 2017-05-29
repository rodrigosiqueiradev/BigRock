package br.eti.rodrigosiqueira.bigrock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class BigRockListActivity extends AppCompatActivity {

    private final String[] options = new String[]{
      "TAREFAS", "RESUMO", "SINCRONIZAÇÃO"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rock_list);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        Intent intent = new Intent(getApplicationContext(), SliderTarefas.class);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                }
            }
        });
    }
}
