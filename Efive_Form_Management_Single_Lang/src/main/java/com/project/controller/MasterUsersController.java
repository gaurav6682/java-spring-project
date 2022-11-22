package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MasterUsersController {
	@RequestMapping("master_users")
	public String completedfrom(Model model) {
//		model.addAttribute("formdatalist", formheaderutil.getAllFillForms());
		System.out.println("IN Master USERS LIST");
		return "master_users";
	}

}
