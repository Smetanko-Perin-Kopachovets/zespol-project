package com.javamaster.controller;

import com.javamaster.model.Job;
import com.javamaster.service.JobService;
import com.javamaster.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;


//TODO
@Controller
public class JobController {

    private JobService jobService;
    private StoreService storeService;


    @Autowired
    public JobController(JobService jobService, StoreService storeService) {
        this.jobService = jobService;
        this.storeService = storeService;
    }


    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String loadPage(Model model) {

        ArrayList<Job> jobs = jobService.getAll();
        model.addAttribute("jobs", jobs);
        return "jobs";
    }


}
