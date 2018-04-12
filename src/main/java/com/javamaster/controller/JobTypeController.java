package com.javamaster.controller;

import com.javamaster.model.JobType;
import com.javamaster.model.Store;
import com.javamaster.service.JobTypeService;
import com.javamaster.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobTypeController {

    private JobTypeService jobTypeService;
    private StoreService storeService;


    @Autowired
    public JobTypeController(JobTypeService jobTypeService, StoreService storeService) {
        this.jobTypeService = jobTypeService;
        this.storeService = storeService;
    }

    @RequestMapping(value = "/jobtype", method = RequestMethod.GET)
    public String loadPage(Model model){
        model.addAttribute("storelist", storeService.getAll());
        model.addAttribute("jobtypeList", jobTypeService.getAll());
        return "jobtype";

    }

    @RequestMapping(value = "/createJobType", method = RequestMethod.POST)
    public String createJobType(@RequestParam("type") String type,
                                @RequestParam("pricePerHour") Float pricePerHour,
                                @RequestParam("id-store")Long id){

       Store store =  storeService.getById(id);
       jobTypeService.create(new JobType(type, pricePerHour, store));

        return "redirect:/jobtype";
    }





}
