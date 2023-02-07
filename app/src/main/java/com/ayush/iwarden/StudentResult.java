package com.ayush.iwarden;

public class StudentResult {

    long mid;
    String mname;
    long mroom;

    public StudentResult(){

    }
    public StudentResult(long id,String name,long room){
        mid = id;
        mname = name;
        mroom = room;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setMroom(long mroom) {
        this.mroom = mroom;
    }

    public String getMname() {
        return mname;
    }

    public long getMid() {
        return mid;
    }

    public long getMroom() {
        return mroom;
    }
}
