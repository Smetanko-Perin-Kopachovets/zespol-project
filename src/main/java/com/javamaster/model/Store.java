package com.javamaster.model;

import org.hibernate.validator.constraints.NotEmpty;


public class Store {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String city;

    public Store(long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Store() {

    }

    public Store(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



}
