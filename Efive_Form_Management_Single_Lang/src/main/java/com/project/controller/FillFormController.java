package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.Util.FillFormUtil;
import com.project.Util.FormData;
import com.project.Util.FormHeaderUtil;
import com.project.model.FillForm;
import com.project.repository.FillFormHeaderRepository;
import com.project.repository.FillFormRepository;
import com.project.repository.FormHeaderRepository;

@Controller
public class FillFormController {

	
	@Autowired
	FillFormUtil fillformutil;
	
	@Autowired
	FillFormRepository fillformrepository;
	
	@Autowired
	FillFormHeaderRepository fillformheaderrepository;
	
	
	@RequestMapping("fill_form")
	public String AddProduct(Model model) {
		model.addAttribute("fillformdatalist", fillformutil.getFillformheader());
		System.out.println("IN FormHeader LIST");
		return "fill_forms";
	}
	
	@RequestMapping(value = "/savefillform", method = RequestMethod.POST)
	public ResponseEntity<FillFormUtil> savewithmultiobj(@RequestBody FillFormUtil fillformutil) {
		System.out.println("FORM Fill DATA ===>" + fillformutil.toString());
		
		
		fillformheaderrepository.save(fillformutil.getFillformheader());
		

//FORM QUE DETAIL SAVE
		for (FillForm fillform : fillformutil.getFillform()) {
			System.out.println(("FILL FORM SINGLE=========>>>>>>>>"+ fillform));
			fillformrepository.save(fillform);
		}

		
		return new ResponseEntity<FillFormUtil>(fillformutil, HttpStatus.OK);

	}
	
	
}
