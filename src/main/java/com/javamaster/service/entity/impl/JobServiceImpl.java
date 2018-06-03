package com.javamaster.service.entity.impl;


import com.javamaster.dao.JobRepository;
import com.javamaster.dao.impl.JobDaoImpl;
import com.javamaster.model.Job;
import com.javamaster.model.Reservation;
import com.javamaster.model.User;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobTypeService jobTypeService;
    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private JobDaoImpl jobDao;

    @Override
    public Job create(Job job) {
        return jobRepository.saveAndFlush(job);
    }


    @Override
    public void delete(Long id) {
        jobRepository.delete(id);
    }

    @Override
    public Job getById(Long id) {
        return jobRepository.findOne(id);
    }

    @Override
    public Job update(Job job) {
        return jobRepository.saveAndFlush(job);
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getFiltrListJob(User user) {
        List<Job> jobs = getAll();
        List<Reservation> reservations = reservationService.getReservationOnUsers(user.getId());
        ListIterator<Job> jobIterator = jobs.listIterator();

        if (reservations.isEmpty()) {
            while (jobIterator.hasNext()) {
                Job job = jobIterator.next();
                if (job.getUser() != null) {
                    jobIterator.remove();
                }
            }
        } else {
            while (jobIterator.hasNext()) {
                Job job = jobIterator.next();
                for (Reservation reservation : reservations) {
                    if (job.getId().equals(reservation.getJob().getId()) || job.getUser() != null) {
                        jobIterator.remove();
                    }
                }
            }

            return jobs;
        }
        return jobs;
    }

    @Override
    public List<Job> jobOnUser(Integer id) {
        return jobDao.getJobByUser(id);
    }

    @Override
    public List<Job> getWithTime(String date) {
        return jobDao.getJobByTime(date);
    }

}

