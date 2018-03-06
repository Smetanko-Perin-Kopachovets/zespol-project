package com.javamaster.controller;

import com.javamaster.model.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@Controller
public class JobController {

    public ArrayList<Job> jobs;


    public JobController() {
        jobs = new ArrayList<Job>();
        jobs.add(new Job(1l, "Biedronka", "Title"));
        jobs.add(new Job(2l, "Auchan", "Title"));
        jobs.add(new Job(3l, "Makro", "Title"));
    }


    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String loadPage(Model model) {


        model.addAttribute("list", jobs);

        return "jobs";
    }

    @RequestMapping(value = "/createJob", method = RequestMethod.POST)
    public String createJob(@RequestParam("id") Long id,
                            @RequestParam("stors") String stors,
                            @RequestParam("title") String title) {

        jobs.add(new Job(id,stors,title));

        return "redirect:/jobs";
    }

    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id")int id, Model model){
        Job job = jobs.get(id);
        model.addAttribute("job", job);
        return "job1";
    }
}
