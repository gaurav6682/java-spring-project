package com.project.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.Util.AjaxResponse;
import com.project.Util.CategoryUtil;
import com.project.Util.FileUpload;
import com.project.model.CategoryMaster;
import com.project.model.ProductMaster;
import com.project.model.SubCategoryMaster;
import com.project.repository.SubCategoryRepository;
import com.project.service.SubCategoryService;

@Controller

public class SubCategoryMasterController {

	SubCategoryService SubCategoryService;

	@Autowired
	CategoryUtil categoryUtil;

	@Autowired
	SubCategoryRepository subcategoryRepository;

	@Autowired
	FileUpload fileUpload;

	@Autowired
	AjaxResponse ajaxresponse;

	@Autowired
	SubCategoryMaster subcategoryMaster;

	@RequestMapping("addsubcategory")
	public String addsubcategory(Model model) {
		model.addAttribute("categoryList", categoryUtil.getCategory());
		return "sub_cat_master";
	}

	@RequestMapping(value = "editsubcategory/{subcategoryid}", method = RequestMethod.GET)
	public ResponseEntity<Object> editsubcategory(@PathVariable("subcategoryid") Long subcategoryid) {
		try {
			SubCategoryMaster subcategorymaster = subcategoryRepository.findById(subcategoryid);
			if (subcategorymaster != null) {
				ajaxresponse.setStatus("200");
				ajaxresponse.setMessage("Add/Edit Sub-catatory Successfully");
				ajaxresponse.getDataMap().clear();
				ajaxresponse.getDataMap().put("subcategorymasterlist", subcategorymaster);
				System.out.println(ajaxresponse.toString());
				return new ResponseEntity<>(ajaxresponse, HttpStatus.OK);
			} else {
				ajaxresponse.setStatus("404");
				ajaxresponse.setMessage("data not found.");
				ajaxresponse.getDataMap().clear();
				ajaxresponse.getDataMap().put("redirectUrl", "/");
				return new ResponseEntity<Object>(ajaxresponse, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	@RequestMapping(value = "/savesubcategoryData", method = RequestMethod.POST)
	public String addsubcategory(@ModelAttribute("subcategoryMaster") SubCategoryMaster subcategoryMaster) {
		System.out.println("-------------------save controller-----------------");
		System.out.println("IN SAVE====> "+subcategoryMaster.toString());
		
		subcategoryRepository.save(subcategoryMaster);
		return "redirect:addsubcategory";
		//return "sub_cat_master";
	}

	@ResponseBody
	@RequestMapping(value = "/getsubcategoryData")
	public ResponseEntity<Object> getsubcategoryData(HttpServletRequest request, Model model) {
		//System.out.println("get SUB Category Data");
		return new ResponseEntity<>(subcategoryRepository.getPaginatedData(request), HttpStatus.OK);
	}


	@ResponseBody
	@RequestMapping(value = "deletesubcategory/{subcategoryid}", method = RequestMethod.POST)
	public ResponseEntity<Object> deletesubcategory(@PathVariable("subcategoryid") Long subcategoryid) {
		//System.out.println("delete sub Category controller");
		SubCategoryMaster subcategoryModel = subcategoryRepository.findById(subcategoryid);
		//System.out.println("subcategoryModel " + subcategoryModel);
		subcategoryRepository.delete(subcategoryModel);
		return new ResponseEntity<>(new AjaxResponse("200", " Sub Category has been deleted.", null), HttpStatus.OK);
	}

}
