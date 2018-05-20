package com.javamaster.controller;


import com.javamaster.model.Job;
import com.javamaster.model.JobType;
import com.javamaster.service.entity.JobService;
import com.javamaster.service.entity.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

@Controller
public class JobController {

    private JobService jobService;
    private JobTypeService jobTypeService;

    @Autowired
    public JobController(JobService jobService, JobTypeService jobTypeService) {
        this.jobService = jobService;
        this.jobTypeService = jobTypeService;
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String loadPage(Model model) {
        model.addAttribute("jobList", jobService.getAll());
        model.addAttribute("jobTypeList", jobTypeService.getAll());
        model.addAttribute("job", new Job());
        return "jobs";
    }

    @RequestMapping(value = "/jobs/create", method = RequestMethod.POST)
    public String createJobs(@RequestParam("jobTypeId") Long id,
                             @RequestParam("date") String date,
                             @RequestParam("timeFrom") String timeFrom,
                             @RequestParam("timeTo") String timeTo,
                             RedirectAttributes redirectAttributes) {

        LocalDate localDate = LocalDate.parse(date);
        LocalTime dateTimeFrom = LocalTime.parse(timeFrom, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime dateTimeTo = LocalTime.parse(timeTo, DateTimeFormatter.ofPattern("HH:mm"));

        JobType jobType = jobTypeService.getById(id);
        Float salary = countSalary(dateTimeFrom, dateTimeTo, jobType);
        Job job = new Job(jobType, localDate, salary, dateTimeFrom, dateTimeTo);

        Job createjob = jobService.create(job);


        if(createjob != null) {
            redirectAttributes.addFlashAttribute("message", "Job with id " + createjob.getId()  + " created");
        } else {
            redirectAttributes.addFlashAttribute("message", "Job not created");
        }
        return "redirect:/jobs";
    }


    @RequestMapping(value = "/jobs/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteByid(@PathVariable("id") Long id,
                                   RedirectAttributes redirectAttributes) {
        jobService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Success deleted item with id - " + id);
        return new ModelAndView("redirect:/jobs");

    }

    private Float countSalary (LocalTime dateTimeFrom, LocalTime dateTimeTo, JobType jobType){
        long hours = ChronoUnit.HOURS.between(dateTimeFrom,dateTimeTo);
        return hours * jobType.getPricePerHour();
    }
}
