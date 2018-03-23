package com.javamaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping("/")
	public String loadPage(){
		return "main";
	}

//	@RequestMapping(value = "/store", method = RequestMethod.GET)
//	public String store(){
//		return "stores";
//	}

}
