package com.javamaster.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="market")
public class Store {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "city", nullable = false, length = 100)
    private String city;


    @NotEmpty
    @Column(name = "street", nullable = false, length = 120)
    private String street;

    public Store(Long id, String name, String city, String street) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;

    }

    public Store() { }

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
