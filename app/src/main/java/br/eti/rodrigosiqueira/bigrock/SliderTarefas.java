package br.eti.rodrigosiqueira.bigrock;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SliderTarefas extends FragmentActivity {

    private final String[] pages = new String[]{"PAG1","PAG2"};

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_tarefas);

        mPager = (ViewPager) findViewById(R.id.pager_tarefa);
        mPagerAdapter = new TarefaPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    public class TarefaPageAdapter extends FragmentStatePagerAdapter {

        public TarefaPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new CardTarefa();
        }

        @Override
        public int getCount() {
            return pages.length;
        }
    }
}
