package org.com.asapplication.rxjava.bean;

/**
 * Class Des:
 * Created by bjh on 2018/3/9.
 */

public class Translation {
    private int status;

    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "status=" + status +
                ", content=" + content +
                '}';
    }
}
