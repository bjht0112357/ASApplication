
package com.utils.library.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {
    private AppUtils() {
        
    }
    
    /**
     * 隐藏输入框
     * @param view view
     */
    public static void hideSoftInput(View view) {
        if (view != null && view.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    
    /**
     * 显示输入框
     * @param view view
     */
    public static void showSoftInput(final View view) {
        view.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                InputMethodManager m = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }

        }, 200);
    }

    /**
     * 移动光标到文本框最后
     * @param editText EditText
     */
    public static void moveSelectionToEnd(EditText editText) {
        Editable etext = editText.getText();
        Selection.setSelection(etext, etext.length());
    }

    /**
     * 获取版本号
     * @param application application
     */
    public static int getVersionCode(Application application) {
        PackageInfo info = getPackageInfo(application.getApplicationContext());
        if (info != null) {
            return info.versionCode;
        }

        return -1;
    }
    
    /**
     * 获取版本名
     * @return 版本名称
     */
    public static String getVersionName(Application application){
        String versionName;
        Context context=application.getApplicationContext();
        //获取版本类型
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            versionName = "other";
        }
        
        return versionName;
    }

    /**
     * 获取包信息
     * @param context 上下文对象
     */
    public static PackageInfo getPackageInfo(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info;
        } catch (NameNotFoundException e) {
        }

        return null;
    }

    /**
     * 是否包含中文
     * @param sequence 验证字符串
     * @return 是否包含中文
     */
    public static boolean isContainChinese(String sequence) {
        final String format = "[\\u4E00-\\u9FA5\\uF900-\\uFA2D]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(sequence);
        return matcher.find();
    }

    /**
     * 判断邮箱格式
     * @param str 验证字符串
     * @return 是否邮箱
     */
    public static boolean isEmail(String str) {
        String check = "\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证是否是手机号码
     * @param str 验证字符串
     * @return 是否手机号
     */
    public static boolean isMobile(String str) {
        String NUM = "+86";
        boolean flag = false;
        if (TextUtils.isEmpty(str)) {
            return flag;
        } else {
            if (str.indexOf(NUM) > -1) {
                str = str.substring(NUM.length(), str.length());
            }
            if (str.charAt(0) == '0') {
                str = str.substring(1, str.length());
            }
            String rex = "^1\\d{10}$";
            str = removeBlanks(str);
            if (str.matches(rex)) {
                flag = true;
            }
            return flag;
        }
    }

    /**
     * 删除字符串中的空白符
     * @param content 字符串内容
     * @return String 删除后的内容
     */
    public static String removeBlanks(String content) {
        if (content == null) {
            return null;
        }
        StringBuffer buff = new StringBuffer();
        buff.append(content);
        for (int i = buff.length() - 1; i >= 0; i--) {
            if (' ' == buff.charAt(i) || ('\n' == buff.charAt(i)) || ('\t' == buff.charAt(i)) || ('\r' == buff.charAt(i))) {
                buff.deleteCharAt(i);
            }
        }
        return buff.toString();
    }

    /**
     * 18位或者15位身份证验证 18位的最后一位可以是字母x
     * @param text 字符串
     * @return 是否是身份证
     */
    public static boolean personIdValidation(String text) {
        boolean flag = false;
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        flag = text.matches(regx) || text.matches(reg1) || text.matches(regex);
        return flag;
    }

    /**
     * 点击输入法中“下一个”将焦点与光标跳转到下一输入框中
     * @param currentEt 当前的输入框
     * @param nextEt 下一个输入框
     */
    public static void setInputType(final EditText currentEt, final EditText nextEt) {
        currentEt.setSingleLine(true); // android:singleLine=”true”
        currentEt.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        currentEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    currentEt.clearFocus();
                    nextEt.requestFocus();
                    return true;
                }
                return false;
            }
        });
    }
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    /**
     * 获取包名
     * @param context
     * @return
     */
    public static String getPackageName(Context context){
        return context.getPackageName();
    }
    /**
     * 获取应用程序的icon图标
     * @return
     * 当包名错误时，返回null
     */
    public static Drawable getApplicationIcon(Application application){
        PackageManager packageManager = application.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(application), 0);
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * dip值转换为pix像素值
     * @param context context
     * @param dpValue dip
     * @return pix
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 复制内容到剪切板
     * 如需自由复制TextView等控件的文字，只是要在该控件上加上这句就好了android:textIsSelectable="true"，或者java代码加 setTextIsSelectable（true）；
     */
    public static void copy(Application app, String copyString) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) app.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", copyString);
        // 将ClipData内容放到系统剪贴板里。
        assert cm != null;
        cm.setPrimaryClip(mClipData);
    }
    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    public static void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }
}
