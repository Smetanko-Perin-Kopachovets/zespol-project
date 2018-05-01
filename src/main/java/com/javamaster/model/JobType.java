package com.javamaster.model;

import javax.persistence.*;

@Entity
@Table(name = "jobtype")
public class JobType {

    @Id
    //Strategy not AUTO, cuz postgres id type: serial
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "pricePerHour", nullable = false)
    private Float pricePerHour;

    @OneToOne
    @JoinColumn(name = "market_id_fk", nullable = false)
    private Store store;

    public JobType() {}

    public JobType(long id, float pricePerHour, String type, Store store) {
        this.id = id;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.store = store;
    }

    public JobType(Long id)  {
        this.id = id;
    }

    public JobType(String type, Float pricePerHour, Store store) {
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.store = store;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
