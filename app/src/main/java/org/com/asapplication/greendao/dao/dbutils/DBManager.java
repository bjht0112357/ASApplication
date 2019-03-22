package org.com.asapplication.greendao.dao.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.com.asapplication.greendao.dao.DaoMaster;
import org.com.asapplication.greendao.dao.DaoSession;

/**
 * Class Des:
 * Created by bjh on 2019/3/22.
 */
public class DBManager {
    public final static String dbName = "green_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new UpVersionSQLiteOpenHelper(context,null);
        daoMaster =new DaoMaster(getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }
    /**
     * 获取可读数据库
     */
    public SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new UpVersionSQLiteOpenHelper(context,null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }
    /**
     * 获取可写数据库
     */
    public SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new UpVersionSQLiteOpenHelper(context,null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
