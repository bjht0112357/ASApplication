package com.utils.library.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.utils.library.presenter.BaseMvpPresenter;
import com.utils.library.view.BaseMvpView;

/**
 * Class Des:
 * Created by bjh on 2018/3/27.
 */
public abstract class BaseActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends AppCompatActivity {

    private P presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //实例化Presenter
        presenter = createPresenter();
        //绑定
        if (presenter != null){
            presenter.attachView((V) this);
        }
        //初始化控件
        iniViews();
        //获取数据
        getRequestDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (presenter != null){
            presenter.detachView();
        }
    }

    protected abstract int getLayoutId();
    protected abstract P createPresenter();
    protected abstract void iniViews();
    protected abstract void getRequestDatas();

    protected P getPresenter(){
        return presenter;
    }
}
