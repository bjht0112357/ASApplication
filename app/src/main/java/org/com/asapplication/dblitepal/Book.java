package org.com.asapplication.dblitepal;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * Class Des:
 * Created by bjh on 2019/3/22.
 * 1 不管实体类中有没有id这个属性，都会默认创建一个为整型的id字段，作为自增的主键
 * 2 如果实体类中有一个字段名为id,那么类型只能为int或者long
 * 3 id字段的值始终为当前记录的行号（下标从1开始），即使我们在实体类中定义了int或者long类型的id字段，在添加数据时人为的设置id的值为100,等其他值，查询数据库发现该id字段的值设置是无效的，她始终等于该条记录所在的行id，即第几条记录。
 * 4 所有的column注解总共有四个，defaultValue的默认值为空字符，所以这个注解只能用以是String类型的字段，即字符型才有默认值
 * 5 litepal支持的实体类字段映射类型为 int,long,double,float,byte[],boolean,String,Date;不支持String[]数组型
 */
public class Book extends LitePalSupport {
    private int id;
    private String author;
    private double price;
    private int pages;
    //name是唯一的，且默认值为unknown
    @Column(defaultValue = "unknown")
    private String name;
    //detail是新增字段 设置默认值 数据库升级之前的数据中本没有detail字段，更新后detail=‘detail’
    @Column( defaultValue = "detail")
    private String detail;

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}

