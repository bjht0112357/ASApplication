package org.com.asapplication.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.com.asapplication.R;
import org.com.asapplication.greendao.dao.DaoSession;
import org.com.asapplication.greendao.dao.DayStepDao;
import org.com.asapplication.greendao.dao.dbutils.DBManager;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GreenDBDemoActivity extends AppCompatActivity implements View.OnClickListener {

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
    private DaoSession daoSession;
    private DayStep dayStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_dbdemo);
        setTitle("GreenDBDemoActivity");
        ButterKnife.bind(this);
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

        daoSession = DBManager.getInstance(this).getDaoSession();

    }
    private void insert(){
        dayStep = new DayStep();
        dayStep.setId(100);
        dayStep.setDate("2019.3.22");
        dayStep.setSportId(100L);
        dayStep.setStep(100000);
        //会检查是否已有该主键的数据有的话会报错
        //long l = daoSession.insert(dayStep);
        long l = daoSession.insertOrReplace(dayStep);
        tvdb.setText("insert() l="+l);

    }
    private void update(){
        dayStep = new DayStep();
        dayStep.setId(100);
        dayStep.setDate("2019.05.03");
        dayStep.setSportId(100L);
        dayStep.setStep(1000);
        daoSession.update(dayStep);
        tvdb.setText("update()");

    }

    /**
     * 删除的是根据主键进行删除
     */
    private void delete(){
        dayStep = new DayStep();
        dayStep.setId(100);
        dayStep.setDate("2019.3.23");
        dayStep.setSportId(100L);
        dayStep.setStep(100000);
        daoSession.delete(dayStep);
        tvdb.setText("delete()");
    }
    private String select(){
        QueryBuilder<DayStep> dayStepQueryBuilder = daoSession.getDayStepDao().queryBuilder();
        QueryBuilder<DayStep> qb = dayStepQueryBuilder.where(DayStepDao.Properties.Date.eq("2019.3.22"));
        List<DayStep> list = qb.list();
        if (list.size()>0){
            DayStep dayStep = list.get(0);
            return dayStep.toString();
        }
        return "select result is null";
    }
    private String selectById(String id){
        QueryBuilder<DayStep> dayStepQueryBuilder = daoSession.getDayStepDao().queryBuilder();
        QueryBuilder<DayStep> qb = dayStepQueryBuilder.where(DayStepDao.Properties.Id.eq(id));
        List<DayStep> list = qb.list();
        if (list.size()>0){
            DayStep dayStep = list.get(0);
            return dayStep.toString();
        }
        return "";
    }
    private void selectBySQL(){
        //查询名字为“羞羞的铁拳”的电影
        //使用Dao.queryBuilder().where() 配合 WhereCondition.StringCondition() 实现SQL查询
        Query query =daoSession.getDayStepDao().queryBuilder()
                        .where(new WhereCondition.StringCondition("DATE = ?", "2019.3.22")).build();
        List<DayStep> dayStepDaos = query.list();

       //使用Dao.queryRawCreate() 实现SQL查询
        Query query1 = daoSession.getDayStepDao().queryRawCreate("WHERE DATE = ?", "2019.3.22");
        List<DayStep> dayStep = query.list();
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
                tvdb.setText(selectById("100"));
                break;
        	default:
        		break;
        }
    }
}
