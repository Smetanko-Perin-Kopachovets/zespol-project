package com.javamaster.model;

public class Job {
    private long id;
    private String store;
    private String title;
    private Market market;

    public Job(long id, String store, String title) {
        this.id = id;
        this.store = store;
        this.title = title;
    }

    public Job() {

    }

    public Job(String store, String title) {
        this.store = store;
        this.title = title;
    }

    public Job(long id) {
        this.id = id;
    }





    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
