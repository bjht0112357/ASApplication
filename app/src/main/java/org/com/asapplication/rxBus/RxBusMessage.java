package org.com.asapplication.rxBus;

/**
 * Class Des:
 * Created by bjh on 2018/3/16.
 */

public class RxBusMessage {
    private String type;
    private Object passValue;

    public RxBusMessage(String type, Object passValue) {
        this.type = type;
        this.passValue = passValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPassValue() {
        return passValue;
    }

    public void setPassValue(Object passValue) {
        this.passValue = passValue;
    }
}
