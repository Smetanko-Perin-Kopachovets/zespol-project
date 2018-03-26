package com.javamaster.controller;

import com.javamaster.model.Job;
import com.javamaster.model.Store;
import com.javamaster.service.JobService;
import com.javamaster.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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

    @RequestMapping(value = "/jobtype", method = RequestMethod.GET)
    public String loadPage(Model model){
        model.addAttribute("storelist", storeService.getAll());
        model.addAttribute("jobtypeList", jobService.getAll());
        return "jobtype";

    }

    @RequestMapping(value = "/createJobType", method = RequestMethod.POST)
    public String createJobType(@RequestParam("type") String type,
                                @RequestParam("pricePerHour") Float pricePerHour,
                                @RequestParam("id-store")Long id){

       Store store =  storeService.getById(id);
       jobService.create(new Job(type, pricePerHour, store));

        return "redirect:/jobtype";
    }





}
