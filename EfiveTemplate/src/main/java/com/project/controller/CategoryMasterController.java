package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane.*;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Util.AjaxResponse;
import com.project.Util.CategoryUtil;
import com.project.Util.FileUpload;
import com.project.model.CategoryMaster;
import com.project.model.ProductMaster;
import com.project.model.SubCategoryMaster;
import com.project.repository.CategoryRepository;
import com.project.service.CategoryService;
import com.project.service.SubCategoryService;

import javassist.bytecode.Descriptor.Iterator;

@Controller
public class CategoryMasterController {
	@Autowired
	CategoryService CategoryService;

	@Autowired
	CategoryUtil categoryUtil;

	@Autowired
	EntityManager entityManager;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FileUpload fileUpload;

	@Autowired
	AjaxResponse ajaxresponse;

	@RequestMapping("addcategory")
	public String addcategory(Model model) {
		model.addAttribute("categoryList", categoryUtil.getCategory());
		return "cat_master";
	}

	@RequestMapping(value = "editcategory/{categoryid}", method = RequestMethod.GET)
	public ResponseEntity<Object> editcategory(@PathVariable("categoryid") Long categoryid) {
		try {
			CategoryMaster categorymaster = categoryRepository.findById(categoryid);
			if (categorymaster != null) {
				ajaxresponse.setStatus("200");
				ajaxresponse.setMessage("Add/Edit State Successfully");
				ajaxresponse.getDataMap().clear();
				ajaxresponse.getDataMap().put("categorymasterlist", categorymaster);
				// System.out.println(ajaxresponse.toString());

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
	

	@RequestMapping(value = "/savecategoryData", method = RequestMethod.POST)
	public ModelAndView addcategory(HttpServletRequest request,
			@ModelAttribute("categoryMaster") CategoryMaster categoryMaster) {

		// String message = "* Please Enter UNIQUE Category Name.!!!";
		ModelAndView mav = new ModelAndView();

		//System.out.println(categoryMaster.toString());
		String cur_cat_name = categoryMaster.getcategoryname();
		System.out.println("==================>CURRENT CATEGORY=> " + cur_cat_name);
		List<?> catname = null;
		String mainQuery = "SELECT categorymaster.categoryname FROM springdb.categorymaster WHERE categorymaster.categoryname='"
				+ cur_cat_name + "'";
		Session session = entityManager.unwrap(Session.class);
		catname = session.createNativeQuery(mainQuery).getResultList();
//		System.out.println("==========>" + catname);
		boolean ans = catname.isEmpty();
//		System.out.println("+================ ANS==> " + ans);
		if (ans == false) {
//			ajaxresponse.setStatus("404");
//			ajaxresponse.setMessage("Please Enter UNIQUE Category Name.");
//			System.out.println(ajaxresponse.toString());
//			return new ResponseEntity<>(ajaxresponse, HttpStatus.OK);
			System.out.println("=============>catname EXIST");
//			mav.addObject("response", ajaxresponse);

			request.setAttribute("error", "error");

			// mav.addObject("message", message);
			mav.setViewName("cat_master");
			return mav;
			// return new ResponseEntity<>(new AjaxResponse("404", "Category allready
			// Exiest.", null), HttpStatus.OK);

		} else {
			System.out.println("===================> SAVE CATEGORY");
			categoryRepository.save(categoryMaster);
//			ajaxresponse.setStatus("200");
//			ajaxresponse.setMessage("Add/Edit State Successfully");
//			ajaxresponse.getDataMap().clear();
//			ajaxresponse.getDataMap().put("redirectUrl", "/addcategory");
			// return new ResponseEntity<Object>(ajaxresponse, HttpStatus.NOT_FOUND);
			mav.setViewName("redirect:addcategory");
			// mav.addObject("message", message);
//			mav.addObject("response", ajaxresponse);
			return mav;

		}
	}

	@ResponseBody
	@RequestMapping(value = "getcategoryData")
	public ResponseEntity<Object> getcategoryData(HttpServletRequest request, Model model) {
		return new ResponseEntity<>(categoryRepository.getPaginatedData(request), HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "deletecategory/{categoryid}", method = RequestMethod.POST)
	public ResponseEntity<Object> deletecategory(@PathVariable("categoryid") Long categoryid) {
		CategoryMaster categoryModel = categoryRepository.findById(categoryid);
		categoryRepository.delete(categoryModel);
		return new ResponseEntity<>(new AjaxResponse("200", "Category has been deleted.", null), HttpStatus.OK);
	}

}
