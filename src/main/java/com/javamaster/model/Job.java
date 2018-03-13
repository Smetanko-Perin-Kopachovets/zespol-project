package com.javamaster.model;

//TODO
public class Job {
    private Long id;
    private String title;
    private Store store;

    public Job(long id, String title, Store store) {
        this.id = id;
        this.title = title;
        this.store = store;
    }

    public Job() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
