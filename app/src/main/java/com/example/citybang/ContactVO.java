package com.example.citybang;

public class ContactVO {
    private String date, time, area;

    public ContactVO(String date, String time, String area) {
        this.date = date;
        this.time = time;
        this.area = area;
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
}

