package org.com.asapplication.tim;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfoUtil;

import org.com.asapplication.R;
import org.com.asapplication.apputils.AppLogger;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    String userId_1 = "user1";
    String userSig_1 = "eJxlz11LwzAUgOH7-orQ24mmSbpWwYu6BZGuUO0*rDchW7Ls4PpBmupE-O9iVSx4bp-3cDjvHkLIXy6Kc7nbNX3thHtrtY*ukI-9sz9sW1BCOkGt*of61ILVQu6dtgMGYRgSjMcNKF072MNP0XfaBiPu1LMYbnzvM4wJJuySjBMwA2Z8NbvjaTnla3pYZeWtmd3nLJc3FB-lwaXzDibFVhWnqI0rAiYBnpgFbTRP42OUm-XW8GZjH5LqwlTzSblRjXxcVsB6mpVPr9ejkw4q-fvQFMc0YNFIX7TtoKmHgOAgDAjFX*N7H94nxXBcuQ__";
    String userId_2 = "user2";
    String userSig_2 = "eJxlz01Pg0AQgOE7v4Ls2dj96BJr0kOrgCi16cdBvBBgl7pturuBoQWN-92IGjHO9Xknk3lzXNdF23hzmRWFaTSk0FmJ3GsXYXTxi9YqkWaQskr8Q9laVck0K0FWPRLOOcV42CghNahSfRdNLSs64Foc0v7G1-4YY4rpePInUbseF-7qJgqAUFFEQb5ItuuX80gf-cksyG0br83jMt8nRrPwrmlNoGZqHprG3uv40D3fektI8CjbRyYonzxpVuH5ofNPYDeEwet8N50OToI6yp*HPHzFGOUDPcmqVkb3AcWEE8rw5yDn3fkAfeReIg__";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkPermission();
    }

    //权限检查
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                String[] permissionsArray = permissions.toArray(new String[1]);
                ActivityCompat.requestPermissions(this,
                        permissionsArray,
                        1);
                return false;
            }
        }

        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginUser1:
                onRecvUserSig(userId_1, userSig_1);
                break;
            case R.id.loginUser2:
                onRecvUserSig(userId_2, userSig_2);
                break;
            case R.id.btnSendMsg:
//                sendMsg(getConversation("user4"));
                sendRecord(getConversation("user4"));
                break;
            default:
                break;
        }
    }
    private TIMConversation getConversation(String toUserId){
        //获取单聊会话
        return  TIMManager.getInstance().getConversation(
                TIMConversationType.C2C,
                toUserId);
    }
    private String recordUrl="/storage/emulated/0/com.tencent.qcloud.tim.tuikit/record/auto_1556180130728";
    private int recordDuration=2174;
    private void sendRecord(TIMConversation conversation) {
//        //构造一条消息
//        TIMMessage msg = new TIMMessage();
//
//       //添加文本内容
//        TIMTextElem elem = new TIMTextElem();
//        elem.setText("test msg");
//
//       //将elem添加到消息
//        if (msg.addElement(elem) != 0) {
//            return;
//        }
        TIMMessage msg =   MessageInfoUtil.buildAudioMessage(recordUrl,recordDuration).getTIMMessage();
       //发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                AppLogger.e("send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                AppLogger.e("SendMsg ok");
            }
        });
    }
    private void sendMsg(TIMConversation conversation) {
//        //构造一条消息
//        TIMMessage msg = new TIMMessage();
//
//       //添加文本内容
//        TIMTextElem elem = new TIMTextElem();
//        elem.setText("test msg");
//
//       //将elem添加到消息
//        if (msg.addElement(elem) != 0) {
//            return;
//        }
        TIMMessage msg =   MessageInfoUtil.buildTextMessage("test msg").getTIMMessage();
       //发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                AppLogger.e("send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                AppLogger.e("SendMsg ok");
            }
        });
    }

    /**
     * 在收到服务器颁发的 userSig 后，调用IMSDK的 login 接口
     * userId 用户账号
     * userSig 您服务器给这个用户账号颁发的 IMSDk 鉴权认证
     */
    private void onRecvUserSig(String userId, String userSig) {
        TIMManager.getInstance().login(userId, userSig, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                AppLogger.e("onError:" + s);
            }

            @Override
            public void onSuccess() {
                AppLogger.e("onSuccess");
            }
        });
    }

}
