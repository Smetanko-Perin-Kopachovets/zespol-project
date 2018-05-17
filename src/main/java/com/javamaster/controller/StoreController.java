package com.javamaster.controller;

import com.javamaster.model.JobType;
import com.javamaster.model.Store;
import com.javamaster.service.entity.JobTypeService;
import com.javamaster.service.entity.StoreService;
import com.javamaster.service.gmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class StoreController {

    private StoreService storeService;
    private JobTypeService jobTypeService;
    private MailService mailService;

    @Autowired
    public StoreController(StoreService storeService, JobTypeService jobTypeService, MailService mailService) {
        this.storeService = storeService;
        this.jobTypeService = jobTypeService;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String loadPage(Model model, Principal principal) {
        System.out.println("user " + principal.getName());
        model.addAttribute("storeList", storeService.getAll());
        model.addAttribute("store", new Store());
        return "stores";
    }

    @RequestMapping(value = "/stores/create", method = RequestMethod.POST)
    public String createStore(@Valid Store store,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        System.out.println(store.getName() + " " + store.getCity());
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            return "redirect:/stores";
        } else {
            Store createdStore = storeService.create(store);
            redirectAttributes.addFlashAttribute("message", "Success created " + createdStore.getName() + " in " + createdStore.getCity());
            return "redirect:/stores";
        }
    }


    @RequestMapping(value = "/stores/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Long id,
                               RedirectAttributes redirectAttributes) {

        storeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Success deleted item with id - " + id);
        return new ModelAndView("redirect:/stores");
    }

    @RequestMapping(value = "/stores/update", method = RequestMethod.POST)
    public String updateShop(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("city") String city,
                             RedirectAttributes redirectAttributes) {


        Store store = new Store(id, name, city);
        Store updatedStore = storeService.update(store);
        if (updatedStore != null) {
            redirectAttributes.addFlashAttribute("message", "Success updated item with id - " + updatedStore.getId());
        }
        return "redirect:/stores";
    }

    @RequestMapping(value = "/stores/get/{id}", method = RequestMethod.GET)
    public String loadStoreById(@PathVariable("id") final Long id,
                                Model model) {

        Store store = storeService.getById(id);
        List<JobType> jobTypesNotSorted = jobTypeService.getAll();
        List<JobType> jobTypes = jobTypesNotSorted.stream().
                filter(JobType -> JobType.getStore().getId().equals(id))
                .collect(Collectors.toList());

        model.addAttribute("id", id);
        model.addAttribute("store", store);
        model.addAttribute("jobTypes", jobTypes);
        model.addAttribute("jobTypesCount", jobTypes.size());
        return "store";

    }

    @RequestMapping(value="/sendMail", method = RequestMethod.GET)
    public String sendMail(RedirectAttributes redirectAttributes) {
        mailService.sendEmail("andriismetanko@gmail.com", "Test title");
        redirectAttributes.addFlashAttribute("message", "Mail sended");
        return "redirect:/stores";
    }

}
