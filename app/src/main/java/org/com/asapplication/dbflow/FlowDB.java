package org.com.asapplication.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;
import com.raizlabs.android.dbflow.sql.migration.UpdateTableMigration;

import org.com.asapplication.constants.AppConfig;

/**
 * Class Des:
 * Created by bjh on 2019/3/20.
 */
@Database(name = AppConfig.DB_NAME,version = AppConfig.DB_VERSION)
public class FlowDB {
    /**
     * 数据库的修改：
     * 1、PatientSession 表结构的变化
     * 2、增加表字段，考虑到版本兼容性，老版本不建议删除字段
     *
     */
    @Migration(version = AppConfig.DB_VERSION, priority = 1,database = FlowDB.class)
    public static class BookMigration extends AlterTableMigration<Book> {

        public BookMigration(Class<Book> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
//            addColumn(SQLiteType.TEXT, "detail");
        }
    }
    @Migration(version = AppConfig.DB_VERSION, priority = AppConfig.DB_VERSION,database = FlowDB.class)
    public static class UserModlelMigration extends AlterTableMigration<UserModel> {

        public UserModlelMigration(Class<UserModel> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
//            addColumn(SQLiteType.INTEGER, "code");
        }
    }
    @Migration(version = AppConfig.DB_VERSION, database = FlowDB.class)
    public static class BookUpdateTableMigration extends UpdateTableMigration<Book> {

        /**
         * Creates an update migration.
         *
         * @param table The table to update
         */
        public BookUpdateTableMigration( Class<Book> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            super.onPreMigrate();
//            where(Book_Table.code.eq(1));
//            set(Book_Table.code.eq(100));
        }
    }
}
