package com.javamaster.model;

public class Job {
    private long id;

    public Job(long id, String stors, String title) {
        this.id = id;
        this.stors = stors;
        this.title = title;
    }

    public Job() {

    }

    private String stors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStors() {
        return stors;
    }

    public void setStors(String stors) {
        this.stors = stors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;


}
