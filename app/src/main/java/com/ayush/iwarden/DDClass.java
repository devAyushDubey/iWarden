package com.ayush.iwarden;

public class DDClass {

    int micon_id;
    String mname;
    boolean done = false;

    public DDClass(int icon,String name){
        micon_id = icon;
        mname = name;
    }

    public DDClass(int icon,String name,boolean done){
        micon_id = icon;
        mname = name;
        this.done = done;
    }

    public void setIcon(int icon){
        micon_id = icon;
    }
    public  void setMname(String name){
        mname = name;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    public int getMicon_id() {
        return micon_id;
    }

    public String getMname() {
        return mname;
    }

    public boolean isDone() {
        return done;
    }
}
