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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String loadPage(Model model) {
        model.addAttribute("storeList", storeService.getAll());
        model.addAttribute("store", new Store());
        return "stores";
    }

    @RequestMapping(value = "/createStore", method = RequestMethod.POST)
    public String createStore(@Valid Store store,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            return "redirect:/stores";
        } else {
            storeService.create(store);
            redirectAttributes.addFlashAttribute("message", "Success created");
            return "redirect:/stores";
        }
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable("id") Long id,
                               RedirectAttributes redirectAttributes) {

        storeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Success deleted item");
        return new ModelAndView("redirect:/stores");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String loadUpdatePage(@PathVariable("id") Long id,
                                 Model model) {

        model.addAttribute("id", id);
        return "updateStore";
    }

    @RequestMapping(value = "/updateStore", method = RequestMethod.POST)
    public String updateShop(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("city") String city,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", "Success updated item");
        Store store = new Store(id, name, city);
        storeService.update(store);
        return "redirect:/stores";
    }

}
