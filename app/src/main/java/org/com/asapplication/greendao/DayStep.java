package org.com.asapplication.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Class Des:
 * Created by bjh on 2019/3/22.
 * （1）@Entity 实体标识
 *   @nameInDb 在数据库中的名字，如不写则为实体中类名
 *   @indexes 索引
 *   @createInDb 是否创建表，默认为true,false时不创建
 *   @schema 指定架构名称为实体
 *   @active 无论是更新生成都刷新
 * （2）@Id 每条数据对应的位置，必写项
 * （3）@Property(nameInDb = "") 表示该属性将作为表的一个字段,其中nameInDb属性值是在数据库中对应的字段名称,可以自定义字段名,例如可以定一个跟实体对象字段不一样的字段名
 * （4）@NotNull 不为null
 * （5）@Unique 唯一约束   该属性值必须在数据库中是唯一值
 * （6）@ToMany 一对多
 * （7）@OrderBy 排序
 * （8）@ToOne 一对一 关系表
 * （9）@Transient 不保存于数据库
 * （10）@generated 由greendao产生的构造函数或方法
 *
 * greenDao默认支持的数据类型
 *  boolean, Boolean
 *  int, Integer
 *  short, Short
 *  long, Long
 *  float, Float
 *  double, Double
 *  byte, Byte
 *  byte[]
 *  String
 *  Date
 */
@Entity
public class DayStep {
    @Id
    private long id;
    private String date;
    private int step;
    private Long sportId;
    @Property(nameInDb = "detail")
    private String detail="测试默认值";

    @Generated(hash = 121003456)
    public DayStep() {
    }


    @Generated(hash = 1057291807)
    public DayStep(long id, String date, int step, Long sportId, String detail) {
        this.id = id;
        this.date = date;
        this.step = step;
        this.sportId = sportId;
        this.detail = detail;
    }


    public String getDetail() {
        return  detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    @Override
    public String toString() {
        return "DayStep{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", step=" + step +
                ", sportId=" + sportId +
                ", detail='" + detail + '\'' +
                '}';
    }
}
