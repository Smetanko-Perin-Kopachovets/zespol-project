package com.javamaster.service.entity;

import com.javamaster.model.Job;
import com.javamaster.model.User;

import java.util.List;
import java.util.ListIterator;

public interface JobService extends CRUDService<Job> {
    List<Job> getFiltrListJob(User user);
    List<Job> jobOnUser(Integer id);
}
