package com.example.citybang;

import android.widget.Button;

public class ContactVO {
    private String date, time, area, test1;

    public ContactVO(String date, String time, String area, String test1) {
        this.date = date;
        this.time = time;
        this.area = area;
        this.test1 = test1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }
}

