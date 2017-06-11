package br.eti.rodrigosiqueira.bigrock;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.eti.rodrigosiqueira.bigrock.dao.BigRockDAO;
import br.eti.rodrigosiqueira.bigrock.model.BigRock;

public class BigRockListActivity extends AppCompatActivity {

    private static final int STUDENT_REQUEST_CODE = 200;
    ArrayList<BigRock> bigRocks = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rock_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);

        loadsBigRocks();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(BigRockListActivity.this, BigRockFormActivity.class);
                startActivityForResult(newIntent, STUDENT_REQUEST_CODE);
            }
        });
    }

    public void loadsBigRocks() {

        ListView mainListView = (ListView) findViewById(R.id.mainListView);
        BigRockDAO bigRockDAO = new BigRockDAO(this);
        bigRocks = (ArrayList<BigRock>) bigRockDAO.getBigRocks();


        ArrayList<String> bigRocksNames = new ArrayList<>();

        for (BigRock bigRock: bigRocks) {
            bigRocksNames.add(bigRock.getNmBigRock());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bigRocksNames);
        mainListView.setAdapter(adapter);
    }

}
