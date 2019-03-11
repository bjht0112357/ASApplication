package org.com.asapplication.mvp.view;

import com.utils.library.view.BaseMvpView;

/**
 *
 *
 */
public interface TestMVPView<T> extends BaseMvpView {
    void loadSuccess(T result);

    void loadFailure(String msg);
}