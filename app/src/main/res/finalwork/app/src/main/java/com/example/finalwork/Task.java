package com.example.finalwork;

import com.google.firebase.Timestamp;

public class Task {
    String title;
    String content;
    String date, time;

    Timestamp timestamp;
    String email;



    public Task() {
        Task note = new ConcreteTask();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public com.google.firebase.Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(com.google.firebase.Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email;}

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

    private class ConcreteTask extends Task {
    }
}


