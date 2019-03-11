package org.com.asapplication.rxjava.bean;

/**
 * Class Des:
 * Created by bjh on 2018/3/9.
 */

public class Content {
    private String from;
    private String to;
    private String vendor;
    private String out;
    private int errNo;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    @Override
    public String toString() {
        return "Content{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", vendor='" + vendor + '\'' +
                ", out='" + out + '\'' +
                ", errNo=" + errNo +
                '}';
    }
}
