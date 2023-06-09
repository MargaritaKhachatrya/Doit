package com.example.finalwork.Model;

import com.google.firebase.Timestamp;

public class ToDoModel {
    private String title, content;
    private Timestamp timestamp;
    private String date;
    private String time;

    public ToDoModel(String title, String content, String timestamp) {
        this.title = title;
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


    public void setTimestamp() {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    public void setTitle() {
        this.title = title;
    }

    public void setContent() {
        this.content = content;
    }



    public byte[] getStatus() {
        return new byte[0];
    }

    public void setId(int anInt) {
    }

    public void setStatus(int anInt) {
    }
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }


}
