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


    @RequestMapping(value = "/back", method = RequestMethod.GET)
        public String back(){
            return"redirect:/jobs";
        }


    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String loadPage(Model model) {

        model.addAttribute("list", jobs);
        return "jobs";
    }

    @RequestMapping(value = "/createJob", method = RequestMethod.POST)
    public String createJob(@RequestParam("name") String name,
                            @RequestParam("city") String city) {


        marketService.createMarket(name, city);



        return "redirect:/jobs";
    }

    @RequestMapping(value = "/jobs/market", method = RequestMethod.GET)
    public String getById( Model model){
        model.addAttribute("list1", marketService.getAllById());
        return "job1";
    }

    @RequestMapping(value = "/jobs/show", method = RequestMethod.GET)
    public String getByAllId(Model model){
        ArrayList<Job> job = jobServise.showJob();
        model.addAttribute("list2", job);
        return "welcome";
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String updatePage() {


        return "updatemarket";
    }


    @RequestMapping(value = "/jobs/update", method = RequestMethod.GET)
    public String updateMarket(@RequestParam("id")int id,
                               @RequestParam("name")String name,
                               @RequestParam("city")String city){



        Market market = new Market(id, name, city);
        marketService.updateMarket(market);

        return "redirect:/jobs";
    }

}
