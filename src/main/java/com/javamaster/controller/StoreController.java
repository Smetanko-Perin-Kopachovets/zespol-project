package com.javamaster.controller;

import com.javamaster.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String loadPage( Model model){
        model.addAttribute("storesList", storeService.getAll());
        return "stores";
    }

    @RequestMapping(value = "/createStore", method = RequestMethod.POST)
    public String createStore(@RequestParam("name") String name,
                            @RequestParam("city") String city) {

        storeService.create(name, city);

        return "redirect:/stores";
    }
}
