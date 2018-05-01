package com.javamaster.service;

import com.javamaster.model.JobType;

import java.util.List;

public interface JobTypeService {

    JobType create(JobType jobType);

    void delete(Long id);

    JobType getById(Long id);

    JobType update(JobType jobType);

    List<JobType> getAll();

}
