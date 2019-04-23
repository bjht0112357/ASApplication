package org.com.asapplication.dbflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.com.asapplication.R;
import org.com.asapplication.apputils.AppLogger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FlowDBDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tvdb)
    TextView tvdb;
    @BindView(R.id.btnInsert)
    Button btnInsert;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnSelect)
    Button btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbdemo);
        ButterKnife.bind(this);
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
//        rxDB();
    }

    private void insert() {
        UserModel user = new UserModel();
        user.setName("测试s");
        user.setMoney("100");
        user.setTimeStamp(1000);
        user.setAge(18);
        user.save();
        UserModel userQuery = SQLite.select().from(UserModel.class).where(UserModel_Table.name.eq("测试s")).querySingle();
        AppLogger.e("UserModel " + userQuery.toString());
    }
    private void update(){
//        //        name.like("P%")
        SQLite.update(UserModel.class)
                .set(UserModel_Table.name.eq("测试1"))
                .where(UserModel_Table.id.eq(1))
                .execute();
        UserModel userQuery = SQLite.select().from(UserModel.class).where(UserModel_Table.name.eq("测试1")).querySingle();
        AppLogger.e("userQuery="+userQuery.toString());

    }
    private void delete(){
        SQLite.delete(UserModel.class)
                .where(UserModel_Table.name.is("测试"))
                .execute();
        //如果删除整个表，可以这样：
        Delete.table(UserModel.class);
    }
    private void select(){

        List<UserModel> userModels = SQLite.select().from(UserModel.class).queryList();
//        tvdb.setText("size="+userModels.size());
        if (userModels!=null&&userModels.size()>0) {
            AppLogger.e("userModels" + userModels.get(0).toString());
        }
        AppLogger.e("size" + userModels.size());
    }
    private void asyncSelect(){
        SQLite.select().from(UserModel.class)
                .async()//异步查询
                .queryListResultCallback((transaction, tResult) -> {
                    //更新UI
                }).error((transaction, error) -> Log.i("zhh_db_sync", "SyncList--error---" + error.getMessage())).success(new Transaction.Success() {
            @Override
            public void onSuccess( Transaction transaction) {
                Log.i("zhh_db_sync", "SyncList---success--" );
            }
        }).execute();
    }
    private void rxDB() {
        Observable.create(new ObservableOnSubscribe<List<UserModel>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserModel>> emitter) {
                List<UserModel>  userModels = SQLite.select().from(UserModel.class).queryList();
                emitter.onNext(userModels);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<UserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<UserModel> userModels) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    private void insertBook() {
        Book book = new Book();
        book.setBookName("史记");
        book.setSellMoney("100");
        book.setCode(100);
        book.save();
        Book book1 = SQLite.select().from(Book.class).where(UserModel_Table.id.eq(1)).querySingle();
        AppLogger.e("book1 " + book1.toString());
    }

    /**
     * 添加表字段后测试使用
     */
    private void insertBookAndUser() {
        Book book = new Book();
        book.setBookName("史记");
        book.setSellMoney("10000");
        book.setCode(100);
        book.setMsg("中国历史上第一部纪传体通史");
        book.save();
        Book book1 = SQLite.select().from(Book.class).where(Book_Table.sellMoney.eq("10000")).querySingle();
        AppLogger.e("book1 " + book1.toString());
        UserModel user = new UserModel();
        user.setName("sdas");
        user.setMoney("100");
        user.setTimeStamp(1000);
        user.setAge(18);
        user.setCode(100);
        user.save();
        UserModel userQuery = SQLite.select().from(UserModel.class).where(UserModel_Table.name.eq("sdas")).querySingle();
        AppLogger.e("UserModel " + userQuery.toString());
    }
    /**
     * 保存多条
     */
    private void saveList() {
//        FlowManager.getDatabase(FlowDB.class)
//                .getTransactionManager()
//                .getSaveQueue()
//                .addAll2(SourceList.getList());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        	case R.id.btnInsert:
//                insertBook();
                insertBookAndUser();
        		break;
        	case R.id.btnUpdate:
                update();
        		break;
        	case R.id.btnDelete:
                delete();
        		break;
        	case R.id.btnSelect:
//                select();
                Book book = new Book();
                book.setBookName("史记s");
                book.setSellMoney("100000");
                book.setCode(1);
                book.setMsg("中国历史上第一部纪传体通史");
                book.save();
                Book book1 = SQLite.select().from(Book.class).where(Book_Table.bookName.eq("史记s")).querySingle();
                tvdb.setText(book1.toString());
                break;
        	default:
        		break;
        }
    }
}
