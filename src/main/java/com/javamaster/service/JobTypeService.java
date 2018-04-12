package com.javamaster.service;

import com.javamaster.dao.JobTypeDao;
import com.javamaster.model.JobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//TODO
@Service
public class JobTypeService {

    private JobTypeDao jobTypeDao;

    @Autowired
    public JobTypeService(JobTypeDao jobTypeDao) {
        this.jobTypeDao = jobTypeDao;
    }

    public void create(JobType jobType){
        jobTypeDao.create(jobType);
    }

    public ArrayList<JobType> getAll(){
        return jobTypeDao.getAll();
}



}
