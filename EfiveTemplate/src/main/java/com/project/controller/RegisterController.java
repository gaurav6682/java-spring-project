package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Register;
import com.project.service.UserService;

@Controller

public class RegisterController {
	@Autowired
	UserService userService;
	@Autowired
	Register register;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/signup")
	public String signUp() {
		return "register";
	}

	@RequestMapping("productmaster")
	public String product() {
		return "datatable_product_master";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> save(@RequestBody Register jsonString) {
		System.err.println(jsonString);
		userService.save(jsonString);
		return new ResponseEntity<Object>("{\"status\": 200, \"redirectUrl\":\"\"}", HttpStatus.OK);
	}

	@RequestMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> saveUser(@RequestBody Register reqRegister) {
		System.err.println(reqRegister);
		// System.out.println(reqRegister.getEmail());
		// System.out.println(reqRegister.getPassword());
		// System.out.println("sign in page controller");

		register = userService.ValidateUser(reqRegister.getEmail(), reqRegister.getPassword());
		// System.out.println(register);
		if (register != null && register.getEmail().equals(reqRegister.getEmail())
				&& register.getPassword().equals(reqRegister.getPassword())) {
			return new ResponseEntity<Object>("{\"status\": 200, \"redirectUrl\": \"productmaster\"}", HttpStatus.OK);
		}

		return new ResponseEntity<Object>("{\"status\": 404, \"message\": \"Invalid user credentials.\"}",
				HttpStatus.NOT_FOUND);
	}
}
