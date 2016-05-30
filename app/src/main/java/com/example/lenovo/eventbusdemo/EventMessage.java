package com.example.lenovo.eventbusdemo;

/**
 * Created by lenovo on 2016/5/30.
 */
public class EventMessage {

   private  boolean flag;
   private  String content;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
