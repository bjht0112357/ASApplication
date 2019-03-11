package org.com.asapplication.xrecycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.com.asapplication.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XRecycleviewActivity extends AppCompatActivity {


    @BindView(R.id.xrclv)
    XRecyclerView mRecyclerView;
    XRecycleViewAdapter xRecycleViewAdapter;
    private ArrayList<String> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("XRecycleviewActivity");
        setContentView(R.layout.activity_xrecycleview);
        ButterKnife.bind(this);
        xRecycleViewAdapter = new XRecycleViewAdapter(this);
        initData();
        //ProgressStyle 加载样式
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        mRecyclerView.setAdapter(xRecycleViewAdapter);

        //允许刷新，加载更多
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        //线性布局
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> {
                    initData();
                    mRecyclerView.refreshComplete();
                },2000);
            }

            @Override
            public void onLoadMore() {
               new Handler().postDelayed(()->{
                   addData();
                   mRecyclerView.loadMoreComplete();
               },2000);

            }
        });

    }
    private void initData(){
        data.clear();
        int i=0;
        while (i<50) {
            data.add("data"+i);
            i++;
        }
        xRecycleViewAdapter.setDataSource(data);
    }
    private void addData(){
        int i=0;
        while (i<50) {
            data.add("addData"+i);
            i++;
        }
        xRecycleViewAdapter.setDataSource(data);
    }
}
