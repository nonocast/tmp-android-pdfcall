package com.example.hello.model;

public class Agenda {
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "subject='" + subject + '\'' +
                ", reporter='" + reporter + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String reporter;
    private String time;

}
