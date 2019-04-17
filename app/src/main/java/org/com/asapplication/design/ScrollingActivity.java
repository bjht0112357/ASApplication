package org.com.asapplication.design;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.com.asapplication.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends Activity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<String> data = new ArrayList<>();
    private NestRecyclerAdapter nestRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);

        recycler.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        nestRecycleAdapter = new NestRecyclerAdapter(this);
        recycler.setAdapter(nestRecycleAdapter);
        initData();
    }

    private void initData() {
        data.clear();
        int i = 0;
        while (i < 50) {
            data.add("data" + i);
            i++;
        }
        nestRecycleAdapter.setDataSource(data);
    }
}
