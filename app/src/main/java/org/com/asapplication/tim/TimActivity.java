package org.com.asapplication.tim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.qcloud.uikit.business.session.model.SessionManager;

import org.com.asapplication.R;

public class TimActivity extends AppCompatActivity implements SessionManager.MessageUnreadWatcher {

    private SessionFragment sessionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim);
        sessionFragment = new SessionFragment();
        getFragmentManager().beginTransaction().replace(R.id.empty_view, sessionFragment).commitAllowingStateLoss();
        SessionManager.getInstance().addUnreadWatcher(this);
    }

    @Override
    public void updateUnread(int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
