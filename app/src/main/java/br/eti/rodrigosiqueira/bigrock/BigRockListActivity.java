package br.eti.rodrigosiqueira.bigrock;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.eti.rodrigosiqueira.bigrock.dao.BigRockDAO;
import br.eti.rodrigosiqueira.bigrock.model.BigRock;

public class BigRockListActivity extends AppCompatActivity {

    private static final int STUDENT_REQUEST_CODE = 200;
    ArrayList<BigRock> bigRocks = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView mainListView;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rock_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainListView = (ListView) findViewById(R.id.mainListView);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BigRock bigRock = (BigRock) mainListView.getItemAtPosition(position);
                Intent intent = new Intent(BigRockListActivity.this, BigRockFormActivity.class);
                intent.putExtra("bigRock", bigRock);
                startActivity(intent);
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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

    @Override
    protected void onResume() {
        super.onResume();
        loadsBigRocks();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == STUDENT_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                loadsBigRocks();
            }
        }
    }

    public void loadsBigRocks() {

        BigRockDAO bigRockDAO = new BigRockDAO(this);
        bigRocks = (ArrayList<BigRock>) bigRockDAO.getBigRocks();

        if (bigRocks.size() == 0) {
            Intent newIntent = new Intent(BigRockListActivity.this, BigRockFormActivity.class);
            startActivityForResult(newIntent, STUDENT_REQUEST_CODE);
            finish();
        }

        ArrayAdapter<BigRock> adapter = new ArrayAdapter<BigRock>(this, android.R.layout.simple_list_item_1, bigRocks);
        mainListView.setAdapter(adapter);
    }

}
