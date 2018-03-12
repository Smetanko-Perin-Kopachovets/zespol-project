package com.javamaster.controller;

import com.javamaster.model.Job;
import com.javamaster.model.Market;
import com.javamaster.servise.JobServise;
import com.javamaster.servise.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class JobController {

    public ArrayList<Job> jobs;
    public JobServise jobServise;
    public MarketService marketService;


    @Autowired
    public JobController(JobServise jobServise, MarketService marketService) {
        this.jobServise = jobServise;
        this.marketService = marketService;
    }


    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String loadPage(Model model) {

        model.addAttribute("list", jobs);
        return "jobs";
    }

    @RequestMapping(value = "/createJob", method = RequestMethod.POST)
    public String createJob(@RequestParam("store") String store,
                            @RequestParam("title") String title) {



     jobServise.createJobs(store, title);

        return "redirect:/jobs";
    }

    @RequestMapping(value = "/jobs/market", method = RequestMethod.GET)
    public String getById( Model model){
        model.addAttribute("list1", marketService.getAllById());
        return "job1";
    }

    @RequestMapping(value = "/jobs/show", method = RequestMethod.GET)
    public String getByAllId(Model model){
        Job job = jobServise.showJob();
        model.addAttribute("jobServise", job);
        return "welcome";
    }

}
