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
public class Book extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column(defaultValue = "no")
    private String bookName;
    @Column(defaultValue = "100")
    private String sellMoney;
    @Column
    private int code;
    @Column
    private String msg;
    @Column(defaultValue = "no")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(String sellMoney) {
        this.sellMoney = sellMoney;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", sellMoney='" + sellMoney + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
