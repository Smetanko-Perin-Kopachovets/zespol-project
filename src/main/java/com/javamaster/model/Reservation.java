package com.javamaster.model;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "users_id_fk", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "job_id_fk", nullable = false)
    private Job job;

    public Reservation(User user, Job job) {
        this.user = user;
        this.job = job;
    }

    public Reservation() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
