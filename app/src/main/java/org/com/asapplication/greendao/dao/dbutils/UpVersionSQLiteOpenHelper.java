package org.com.asapplication.greendao.dao.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.com.asapplication.greendao.dao.DaoMaster;
import org.com.asapplication.greendao.dao.DayStepDao;
import org.greenrobot.greendao.database.Database;

/**
 * Class Des:
 * Created by bjh on 2019/3/22.
 */
public class UpVersionSQLiteOpenHelper extends DaoMaster.DevOpenHelper{
    /**
     *
     * @param context  上下文
     * @param factory  可以null
     */
    public UpVersionSQLiteOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DBManager.dbName+".db", factory);
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     *  更新数据库的时候自己调用
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        /**
         *  将db传入     将gen目录下的所有的Dao.类传入
         */
        UpVersionMigrationHelper.migrate(db, DayStepDao.class);
    }

}
