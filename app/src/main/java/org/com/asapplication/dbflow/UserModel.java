package org.com.asapplication.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Class Des:
 * Created by bjh on 2019/3/20.
 */
@Table(database = FlowDB.class)
public class UserModel extends BaseModel{

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String name;
    @Column
    private String money;

    @Column
    private int age;

    @Column
    private long timeStamp;
    @Column
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", age=" + age +
                ", timeStamp=" + timeStamp +
                ", code=" + code +
                '}';
    }
}
