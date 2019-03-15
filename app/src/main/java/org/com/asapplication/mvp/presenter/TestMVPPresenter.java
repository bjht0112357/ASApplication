package org.com.asapplication.mvp.presenter;

import com.utils.library.presenter.BaseMvpPresenter;

import org.com.asapplication.mvp.model.TestMVPModel;
import org.com.asapplication.mvp.model.User;
import org.com.asapplication.mvp.view.TestMVPView;
import org.com.asapplication.rxjava.utils.Response;

/**
 *
 *
 */
public class TestMVPPresenter extends BaseMvpPresenter<TestMVPView<User>> {
    private final TestMVPModel mvpModel;

    public TestMVPPresenter() {
        this.mvpModel = new TestMVPModel();
    }

    public void requestNetDatas() {
        if (null != getMvpView()) {
            getMvpView().showProgressDialog();
            mvpModel.request(new Response<User>() {
                @Override
                public void onError(String error) {
                    getMvpView().loadFailure(error);
                    getMvpView().hideProgressDialog();

                }

                @Override
                public void onResponse(User result) {
                    getMvpView().loadSuccess(result);
                    getMvpView().hideProgressDialog();
                }
            });
        }
    }
}