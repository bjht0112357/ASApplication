package org.com.asapplication.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.com.asapplication.R;

public class TestServiceActivity extends AppCompatActivity {
    private BindService service = null;
    private boolean isBind = false;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            BindService.MyBinder myBinder = (BindService.MyBinder) binder;
            service = myBinder.getService();
            Log.i("BindService", "ActivityA - onServiceConnected");
            int num = service.getRandomNumber();
            Log.i("BindService", "ActivityA - getRandomNumber = " + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            Log.i("BindService", "ActivityA - onServiceDisconnected");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        findViewById(R.id.startService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService
                Intent intent= new Intent(TestServiceActivity.this, MyService.class);
                startService(intent);
            }
        });
        findViewById(R.id.stopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService
                Intent intent = new Intent(TestServiceActivity.this, MyService.class);
                stopService(intent);
            }
        });
        findViewById(R.id.bindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestServiceActivity.this, BindService.class);
                intent.putExtra("from", "ActivityA");
                Log.i("BindService", "----------------------------------------------------------------------");
                Log.i("BindService", "ActivityA 执行 bindService");
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.unbindervice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
    }
}
