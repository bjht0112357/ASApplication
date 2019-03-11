package org.com.asapplication.mvp.activity;

import com.utils.library.base.BaseActivity;

import org.com.asapplication.R;
import org.com.asapplication.mvp.model.User;
import org.com.asapplication.mvp.presenter.TestMVPPresenter;
import org.com.asapplication.mvp.view.TestMVPView;

/**
 *
 *
 */
public class TestMVPActivity extends BaseActivity<TestMVPView<User>, TestMVPPresenter> implements TestMVPView<User> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_mvp;
    }

    @Override
    protected TestMVPPresenter createPresenter() {
        return new TestMVPPresenter();
    }

    @Override
    protected void iniViews() {

    }

    /**
     * 通过Presenter中通过requestNetDatas方法{Modle}mvpModel.request获取数据
     */
    @Override
    protected void getRequestDatas() {
     getPresenter().requestNetDatas();
    }

    @Override
    public void loadSuccess(User data) {

    }

    @Override
    public void loadFailure(String msg) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {


    }
}
