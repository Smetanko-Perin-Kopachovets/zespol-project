package com.javamaster.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

public enum Role {

    ROLE_MANAGER(1, "ROLE_MANAGER"),
    ROLE_WORKER(2, "ROLE_WORKER");

    private Integer id;
    private String name;

    Role(Integer id, String role) {
        this.id = id;
        this.name = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }
}
