package com.ayush.iwarden;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class NoticeClass {
    String mAuthor;
    int mcolor;
    int micon;
    Drawable mback;
    String mnotice;
    String mdate;

    public NoticeClass(String author,int color,int icon,Drawable back,String date,String notice){
        mAuthor = author;
        mcolor = color;
        micon = icon;
        mback = back;
        mdate = date;
        mnotice = notice;
    }



    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setMcolor(int mcolor) {
        this.mcolor = mcolor;
    }

    public void setmBack(Drawable mback) {
        this.mback = mback;
    }

    public void setmIcon(int micon) {
        this.micon = micon;
    }

    public void setmNotice(String mnotice) {
        this.mnotice = mnotice;
    }

    public void setmDate(String mdate) {
        this.mdate = mdate;
    }


    public String getmAuthor() {
        return mAuthor;
    }

    public int getmColor() {
        return mcolor;
    }

    public Drawable getmBack() {
        return mback;
    }

    public int getmIcon() {
        return micon;
    }

    public String getmNotice() {
        return mnotice;
    }

    public String getmDate() {
        return mdate;
    }
}
