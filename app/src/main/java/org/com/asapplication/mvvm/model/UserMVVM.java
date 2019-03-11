package org.com.asapplication.mvvm.model;

import android.databinding.ObservableField;

/**
 * Class Des:
 * Created by bjh on 2019/3/8.
 */
public class UserMVVM {
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> userPassword = new ObservableField<>();
    public final ObservableField<String> imgUrl = new ObservableField<>();
}