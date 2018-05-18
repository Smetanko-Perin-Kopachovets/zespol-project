package com.javamaster.service.entity.impl;


import com.javamaster.dao.JobRepository;
import com.javamaster.model.Job;
import com.javamaster.model.JobType;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    private JobTypeService jobTypeService;

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
}
