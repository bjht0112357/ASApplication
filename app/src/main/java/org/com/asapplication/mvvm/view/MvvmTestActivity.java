package org.com.asapplication.mvvm.view;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.com.asapplication.R;
import org.com.asapplication.databinding.ActivityMvvmTestBinding;
import org.com.asapplication.mvvm.model.UserMVVM;
import org.com.asapplication.mvvm.viewmodel.QueryWeatherViewModel;

public class MvvmTestActivity extends AppCompatActivity {
//    Null Coalescing 运算符
//    android:text="@{user.displayName ?? user.lastName}"
//    就等价于
//
//    android:text="@{user.displayName != null ? user.displayName : user.lastName}"
    private ActivityMvvmTestBinding viewDataBinding;
    //当数据有变化时，可以通知更新数据
    private MutableLiveData<String> text = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_test);
        UserMVVM userMVVM = new UserMVVM();
        userMVVM.userName.set("张三");
        userMVVM.userPassword.set("123456");
        userMVVM.imgUrl.set("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg");
        viewDataBinding.setUserMVVM(userMVVM);
        QueryWeatherViewModel queryWeatherViewModel = new QueryWeatherViewModel();
        viewDataBinding.setViewModel(queryWeatherViewModel);
    }
}
