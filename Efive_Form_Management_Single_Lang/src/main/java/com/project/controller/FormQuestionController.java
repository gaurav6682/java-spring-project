package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.FormQuestionDetail;
import com.project.repository.FormQuestionDetailRepository;
import com.project.Util.*;
@Controller
public class FormQuestionController {
	@Autowired
	FormHeaderUtil MasterFormUtil;
	
	@Autowired
	FormQuestionDetailRepository formQuestionRepository;

	@Autowired
	AjaxResponse ajaxresponse;


	
	
	@ResponseBody
	@PostMapping(value = "/saveque", produces = { MediaType.APPLICATION_JSON_VALUE })
		public String addquedata(@RequestBody FormQuestionDetail formQuestiondetail) {
			System.out.println("-------------------QUESTION-save controller-----------------");
			System.out.println("IN QUE SAVE====> "+formQuestiondetail.toString());
			return "mater_form";
	}
}
