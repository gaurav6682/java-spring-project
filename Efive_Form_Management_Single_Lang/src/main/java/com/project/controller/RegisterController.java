package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RegisterController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("masterform")
	public String masterform() {
		return "redirect:addform";
	}

	@RequestMapping("fillforms")
	public String fillforms() {
		return "fill_forms";
	}
	
}
