package com.javamaster.controller;

import com.javamaster.service.gmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private MailService mailService;

	@RequestMapping("/")
	public String loadPage(){
//	    mailService.sendEmail("andriismetanko@gmail.com");

		return "main";
	}

}
