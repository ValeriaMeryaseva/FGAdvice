package com.example.lera.fgadvice;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TabHost;

import com.example.lera.fgadvice.model.Advice;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CallBack {

    @ViewById(R.id.tabHost)
    TabHost tabHost;

    @AfterViews
    public void bindView() {
        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator(getResources().getString(R.string.advice_tab));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator(getResources().getString(R.string.favorites_tab));
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
    }

    @Override
    public void updateList(Advice advice) {
        FavoritesFragment_ fragment = new FavoritesFragment_();
        fragment.UpdateList(advice);
        Log.d("Tag", "Update");
    }
}
