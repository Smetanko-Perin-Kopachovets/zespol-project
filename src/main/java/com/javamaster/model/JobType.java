package com.javamaster.model;

//TODO
public class JobType {
    private Long id;
    private String type;
    private Float pricePerHour;
    private Store store;


    public JobType(long id, float pricePerHour, String type, Store store) {
        this.id = id;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.store = store;
    }

    public JobType(Long id) {
        this.id = id;
    }

    public JobType(String type, Float pricePerHour, Store store) {
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.store = store;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getType() {
        return type;
    }

    public void setType(String jobtype) {
        this.type = type;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
