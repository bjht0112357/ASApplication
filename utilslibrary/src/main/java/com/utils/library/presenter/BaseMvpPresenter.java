package com.utils.library.presenter;

import com.utils.library.view.BaseMvpView;

/**
 * Class Des:
 * Created by bjh on 2018/3/27.
 */
public abstract class BaseMvpPresenter<V extends BaseMvpView> {
    private V mvpView;
    /**
     *  绑定View
     * @param mvpView
     */
    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    /**
     * 解绑View
     */
    public void detachView(){
        mvpView = null;
    }

    /**
     *  获取View
     * @return
     */
    public V getMvpView(){
        return mvpView;
    }
}
