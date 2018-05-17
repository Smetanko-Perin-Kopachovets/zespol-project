package com.javamaster.controller;

import com.javamaster.service.gmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private MailService mailService;

	@RequestMapping("/")
	public String loadPage(){
//	    mailService.sendEmail("andriismetanko@gmail.com", "test");
		return "main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model) {
		if (error != null) {
			model.addAttribute("errorMessage", "Invalid login or password");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "You've been logged out successfully");
		}

		return "login";
	}

}
