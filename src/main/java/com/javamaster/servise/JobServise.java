package com.javamaster.servise;

import com.javamaster.Dao.JobDao;
import com.javamaster.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JobServise {
    private JobDao jobDao;

    @Autowired
    public JobServise(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public void createJobs(String store, String title){
        Job job = new Job(store, title);
        jobDao.CreateJob(job);

    }

    public ArrayList<Job> showJob(){

        return jobDao.getById();
}

}
