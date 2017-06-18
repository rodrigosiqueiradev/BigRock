package br.eti.rodrigosiqueira.bigrock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.eti.rodrigosiqueira.bigrock.dao.BigRockDAO;
import br.eti.rodrigosiqueira.bigrock.model.BigRock;

public class SliderTarefas extends AppCompatActivity {

    private ArrayList<BigRock> bigRocks = new ArrayList<>();

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_view, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_all:
                Toast.makeText(this, String.valueOf(item.getItemId()),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_tarefas);

        mPager = (ViewPager) findViewById(R.id.pager_tarefa);
        mPagerAdapter = new TarefaPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        BigRockDAO bigRockDAO = new BigRockDAO(mPager.getContext());
        List<BigRock> rocks = bigRockDAO.getBigRocks();

        for (BigRock rock : rocks) {
            this.bigRocks.add(rock);
        }
        mPagerAdapter.notifyDataSetChanged();
    }

    public class TarefaPageAdapter extends FragmentStatePagerAdapter {

        public TarefaPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            CardTarefa cardTarefa = new CardTarefa();

            Bundle args = new Bundle();
            args.putString("BigRock", new Gson().toJson(bigRocks.get(position)));

            if(position % 2 == 0) {
                args.putString("background", "#e6e6e6");
            } else {
                args.putString("background", "#ff00ff");
            }

            cardTarefa.setArguments(args);

            return cardTarefa;
        }

        @Override
        public int getCount() {
            return bigRocks.size();
        }
    }
}
