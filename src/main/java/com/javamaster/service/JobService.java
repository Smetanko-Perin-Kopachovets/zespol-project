package com.javamaster.service;

import com.javamaster.dao.JobDao;
import com.javamaster.model.Job;
import com.javamaster.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//TODO
@Service
public class JobService {

    private JobDao jobDao;

    @Autowired
    public JobService(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public void create(Job job){
        jobDao.create(job);
    }

    public ArrayList<Job> getAll(){
        return jobDao.getAll();
}



}
