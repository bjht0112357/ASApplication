package org.com.asapplication.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Class Des:
 * Created by bjh on 2019/4/12.
 */
@Entity
public class GreenDaoEntity {
    private String date;
    private int step;
    private Long sportId;
    @Property(nameInDb = "detail")
    private String detail="测试默认值";

    @Generated(hash = 802105705)
    public GreenDaoEntity(String date, int step, Long sportId, String detail) {
        this.date = date;
        this.step = step;
        this.sportId = sportId;
        this.detail = detail;
    }

    @Generated(hash = 1963997359)
    public GreenDaoEntity() {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
