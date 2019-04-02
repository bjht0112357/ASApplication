package org.com.asapplication.dblitepal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.com.asapplication.R;
import org.com.asapplication.apputils.AppLogger;
import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * LitePal采用对象关系映射(ORM)的模式
 *
 *
 */
public class LitepalActivity extends AppCompatActivity implements View.OnClickListener {

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
        setTitle("LitepalActivity");
        ButterKnife.bind(this);
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSelect.setOnClickListener(this);


    }
    private void insert(){
        Book book = new Book();
        book.setName("The Da Vinci Code");
        book.setAuthor("Dan Brown");
        book.setPages(454);
        book.setPrice(16.96);
        book.save();
        Book book1 = new Book();
        book1.setName("The Da Vinci");
        book1.setAuthor("Dan Brown");
        book1.setPages(300);
        book1.setPrice(20.00);
        book1.save();
        tvdb.setText("insert()");

    }
    private void update(){
//        Book book = LitePal.find(Book.class, 1);
//         //第二步，改变某个字段的值
//        book.setPages(500);
//         //第三步，保存数据
//        book.save();
        /************************/
        Book book1 = new Book();
        book1.setName("The Da Vinci Code");
        book1.setPages(600);
        book1.update(1);
        /************************/
        //Book book = new Book();
        //book.setPrice(500);
        //book.setPages(500);
        //将更新所有name为The Da Vinci Code，director为Dan Brown的记录price和pages均改为500
        //book.updateAll("name=? and director=?", "The Da Vinci Code"，"Dan Brown");
        tvdb.setText("update()");

    }

    /**
     *
     */
    private void delete(){
        //删除数据库中Book表的所有记录
        // LitePal.deleteAll(Book.class);

        //删除数据库Book表中id为1的记录
        //LitePal.delete(Book.class,1);

       //删除数据库Book表中pages大于3500的记录
       //LitePal.deleteAll(Book.class, "pages > ?" , "300");
        tvdb.setText("delete()");
    }
    private void select(){

        //查找Book表中id为是1的Book
//        Book book = LitePal.find(Book.class, 1);

//        List<Book> books1 = LitePal.where("name = ?", "The Da Vinci Code").order("pages").find(Book.class);
        //查找Book表的所有记录
        List<Book> books= LitePal.findAll(Book.class);
        for (Book book :
                books ) {
            AppLogger.e(book.toString());
        }
    }
    private String selectById(String id){
        return "";
    }
    private void selectBySQL(){
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        	case R.id.btnInsert:
                insert();
        		break;
        	case R.id.btnUpdate:
                update();
        		break;
        	case R.id.btnDelete:
                delete();
        		break;
        	case R.id.btnSelect:
                select();
                break;
        	default:
        		break;
        }
    }
}
