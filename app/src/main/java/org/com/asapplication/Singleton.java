package org.com.asapplication;

/**
 * Class Des:
 * Created by bjh on 2019/4/28.
 */
public class Singleton {
    private static  volatile Singleton instance=null;
    public Singleton getInstance(){
        if (instance==null){
            synchronized (instance){
                if (instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
    private Singleton(){}
}
