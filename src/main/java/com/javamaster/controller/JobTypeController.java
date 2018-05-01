package com.javamaster.controller;

import com.javamaster.model.JobType;
import com.javamaster.model.Store;

import com.javamaster.service.JobTypeService;
import com.javamaster.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String loadPage(Model model) {
        model.addAttribute("storeList", storeService.getAll());
        model.addAttribute("jobTypeList", jobTypeService.getAll());

        return "jobType";
    }

    @RequestMapping(value = "jobtype/create", method = RequestMethod.POST)
    public String createJobType(@RequestParam("type") String type,
                                @RequestParam("pricePerHour") Float pricePerHour,
                                @RequestParam("storeId") Long id) {

        Store store = storeService.getById(id);
        JobType jobType = new JobType(type, pricePerHour, store);
        System.out.println(jobType.getPricePerHour() + " " + jobType.getStore().getCity() + jobType.getStore().getId());
        jobTypeService.create(new JobType(type, pricePerHour, store));

        return "redirect:/stores/get/" + jobType.getStore().getId();
    }

    @RequestMapping(value = "jobtype/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id ) {

        JobType jobType = jobTypeService.getById(id);
        jobTypeService.delete(id);
        return "redirect:/stores/get/" + jobType.getStore().getId();
    }

}
