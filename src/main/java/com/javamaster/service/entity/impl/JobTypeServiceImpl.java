package com.javamaster.service.entity.impl;


import com.javamaster.dao.JobTypeRepository;
import com.javamaster.model.JobType;
import com.javamaster.service.entity.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTypeServiceImpl implements JobTypeService {

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public JobType create(JobType jobType) {
        return jobTypeRepository.saveAndFlush(jobType);
    }

    @Override
    public void delete(Long id) {
        jobTypeRepository.delete(id);
    }

    @Override
    public JobType getById(Long id) {
        return jobTypeRepository.findOne(id);
    }

    @Override
    public JobType update(JobType jobType) {
        return jobTypeRepository.saveAndFlush(jobType);
    }

    @Override
    public List<JobType> getAll() {
        return jobTypeRepository.findAll();
    }

}
