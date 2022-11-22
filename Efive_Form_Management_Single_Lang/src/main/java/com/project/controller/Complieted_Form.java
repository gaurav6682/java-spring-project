package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Util.FormHeaderUtil;
import com.project.repository.FillFormHeaderRepository;

@Controller
public class Complieted_Form {

	@Autowired
	FillFormHeaderRepository fillformheaderrepository;

	@Autowired
	FormHeaderUtil formheaderutil;

	@RequestMapping("completed_form")
	public String completedfrom(Model model) {
//		model.addAttribute("formdatalist", formheaderutil.getAllFillForms());
		System.out.println("IN FormHeader LIST");
		return "completed_forms";
	}
}
