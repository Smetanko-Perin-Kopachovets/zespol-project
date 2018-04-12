package com.javamaster.controller;

import com.javamaster.model.Store;
import com.javamaster.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class StoreController {

    private StoreService storeService;


    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String loadPage(Model model ) {

        model.addAttribute("storesList", storeService.getAll());
        model.addAttribute("store", new Store());

        return "stores";
    }

    @RequestMapping(value = "/createStore", method = RequestMethod.POST)
    public ModelAndView createStore(@Valid Store store, BindingResult result) {
        ModelAndView mow = new ModelAndView();
        mow.getModelMap().addAttribute("store", new Store());
        mow.setViewName("stores");

        if (result.hasErrors()) {
            mow.getModelMap().addAttribute("storesList", storeService.getAll());
            mow.getModelMap().addAllAttributes(result.getModel());
            return mow;
        } else {
            storeService.create(store.getName(), store.getCity());
            mow.getModelMap().addAttribute("storesList", storeService.getAll());
            mow.getModelMap().addAttribute("message", "Success");
            return mow;
        }
    }


    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.GET)
    public ModelAndView deleteById(@PathVariable("id") int id) {
        storeService.delete(id);
        //redirectAttributes.addAttribute("messege", "succesfull delete");
        return new ModelAndView("redirect:/stores");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String loadupdateShop(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        return "updatemarket";
    }

    @RequestMapping(value = "/updateMarket", method = RequestMethod.POST)
    public String updateShop(@RequestParam("id") long id,
                             @RequestParam("name") String name,
                             @RequestParam("city") String city) {

        storeService.update(id, name, city);

        return "redirect:/stores";
    }


}
