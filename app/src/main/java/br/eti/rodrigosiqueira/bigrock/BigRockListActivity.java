package br.eti.rodrigosiqueira.bigrock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        listView.setTextFilterEnabled(true);
    }
}
