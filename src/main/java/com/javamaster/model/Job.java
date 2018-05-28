package com.javamaster.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "jobtype_id_fk", nullable = false)
    private JobType jobType;

    @Column(name = "timefrom", nullable = false)
    private LocalTime dateTimeFrom;

    @Column(name = "timeto", nullable = false)
    private LocalTime dateTimeTo;

//    @Column(name = "status", nullable = false)
//    private String status;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "pricePerjob", nullable = false)
    private Float salary;


    public Job() { }

    public Job(JobType jobType, Float salary, LocalTime dateTimeFrom, LocalTime dateTimeTo) {
        this.jobType = jobType;
        this.salary = salary;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    public Job(JobType jobType, LocalDate date, Float salary, LocalTime dateTimeFrom, LocalTime dateTimeTo) {
        this.jobType = jobType;
        this.date = date;
        this.salary = salary;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }


    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getDateTimeFrom() {
        return dateTimeFrom;
    }

    public void setDateTimeFrom(LocalTime dateTimeFrom) {
        this.dateTimeFrom = dateTimeFrom;
    }

    public LocalTime getDateTimeTo() {
        return dateTimeTo;
    }

    public void setDateTimeTo(LocalTime dateTimeTo) {
        this.dateTimeTo = dateTimeTo;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Job(JobType jobType) {
        this.jobType = jobType;
         }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

}
