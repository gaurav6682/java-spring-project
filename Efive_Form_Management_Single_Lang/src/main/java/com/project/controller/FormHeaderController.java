package com.project.controller;

import org.hibernate.Session;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Util.AjaxResponse;
import com.project.Util.FormData;
import com.project.Util.FormHeaderUtil;
import com.project.model.Characteristic;
import com.project.model.FillForm;
import com.project.model.FillFormHeader;
import com.project.model.FormHeader;
import com.project.model.FormQuestionDetail;
import com.project.model.FromQuestionOption;
import com.project.repository.FillFormHeaderRepository;
import com.project.repository.FillFormRepository;
import com.project.repository.FormHeaderRepository;
import com.project.repository.FormQuestionDetailRepository;
import com.project.repository.FormQuestionOptionRepository;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

@Controller
public class FormHeaderController {

	@Autowired
	FormHeaderUtil formheaderutil;
	
	@Autowired
	EntityManager entityManager;

	@Autowired
	FormHeaderRepository formheaderrepository;

	@Autowired
	FormQuestionDetailRepository formquestiondetailrepository;

	@Autowired
	FormQuestionOptionRepository formquestionoptionrepository;
	
	@Autowired
	FillFormRepository fillformrepository;
	
	@Autowired
	FillFormHeaderRepository fillformheaderrepository;

	@Autowired
	AjaxResponse ajaxresponse;

	@RequestMapping("addform")
	public String AddProduct(Model model) {
		model.addAttribute("formdatalist", formheaderutil.getFormData());
		System.out.println("IN FormHeader LIST");
		return "master_form";
	}

	@RequestMapping(value = "/saveform", method = RequestMethod.POST)
	public ResponseEntity<FormData> saveWithMultipleObjects(@RequestBody FormData formdata) {
		System.out.println("FORM DATA ===>" + formdata.toString());
		System.out.println("FORM HEADER ===>" + formdata.getFormheader());
		System.out.println("QUE DETAIL ===>" + formdata.getFormquestiondetail());
		System.out.println("QUE OPTION ===>" + formdata.getFormquestionoption());

//FROM HEADER SAVE
		formheaderrepository.save(formdata.getFormheader());

//FORM QUE DETAIL SAVE
		for (FormQuestionDetail formquetiondetail : formdata.getFormquestiondetail()) {
			formquestiondetailrepository.save(formquetiondetail);
		}

//FORM QUE OPTION SAVE
		String options = "";
		for (FromQuestionOption formquetionoption : formdata.getFormquestionoption()) {
			System.out.println("Form Question OPTION=====>" + formquetionoption);
			formquestionoptionrepository.save(formquetionoption);
			//System.out.println("IN SAVE QUE OPTIONS======>" + Arrays.deepToString(formquetionoption.getOptionname()));

		}
		return new ResponseEntity<FormData>(formdata, HttpStatus.OK);

	}

//	EDIT DATA

	@RequestMapping(value = "editform/{formid}", method = RequestMethod.GET)
	public ResponseEntity<Object> editformheader(@PathVariable("formid") Long formid) {
		try {
			FormHeader formheader = formheaderrepository.findById(formid);
			List<FormQuestionDetail> formquestiondetail = formquestiondetailrepository.findByFormid(formid);
			List<FromQuestionOption> formquestionoption = formquestionoptionrepository.findByFormid(formid);
			List<FillForm> fillformans =  fillformrepository.findByFormid(formid);
			
			
			
			
			
			List<?> fillformheader = null;
			String mainQuery = "SELECT fill_form_header.compliteddate FROM formdb.fill_form_header WHERE fill_form_header.formid='"
					+ formid + "'";
			Session session = entityManager.unwrap(Session.class);
			fillformheader = session.createNativeQuery(mainQuery).getResultList();
			System.out.println("==========>" + fillformheader);
			
			
			
//			FillFormHeader fillformheader = fillformheaderrepository.findByFormid(formid);
//			System.out.println("FIIL FORM HEADER=============>>>>>>>>>>>>>>>>>>>>>"+ fillformheader);
			
			if (formheader != null) {
				ajaxresponse.setStatus("200");
				ajaxresponse.setMessage("Add/Edit State Successfully");
				ajaxresponse.getDataMap().clear();
				ajaxresponse.getDataMap().put("formheaderlist", formheader);
				ajaxresponse.getDataMap().put("quedatalist", formquestiondetail);
				ajaxresponse.getDataMap().put("queoptionlist", formquestionoption);
				ajaxresponse.getDataMap().put("fillformans", fillformans);
				ajaxresponse.getDataMap().put("fillformheader", fillformheader);
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
	
	
//	DELETE FORM WITH FORM-HEADER, ALL-QUETION AND ALL-OPTION

	@ResponseBody
	@RequestMapping(value = "deleteform/{formid}", method = RequestMethod.POST)
	public ResponseEntity<Object> delete(@PathVariable("formid") Long formid) {
		 System.out.println("delete FORM controller");
		 
//		 DELETE FORM HEADER
		FormHeader formheader = formheaderrepository.findById(formid);
		formheaderrepository.delete(formheader);
		
		
//		DELETE QUESTION
		for (FormQuestionDetail formquetiondetail : formquestiondetailrepository.findByFormid(formid)) {
			formquestiondetailrepository.delete(formquetiondetail);
		}
		
		
//		DELETE OPTION
		for (FromQuestionOption formquetionoption : formquestionoptionrepository.findByFormid(formid)) {
			formquestionoptionrepository.delete(formquetionoption);
			}
		
		return new ResponseEntity<>(new AjaxResponse("200", "Product has been deleted.", null), HttpStatus.OK);
	}

//DELETE QUESTION WITH ALL OPTION
	@ResponseBody
	@RequestMapping(value = "deleteque/{quedetailid}", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteque(@PathVariable("quedetailid") Long quedetailid) {
		 System.out.println("delete QUETION controller");
		 
//		 DELETE QUESTION SINGLE QUE BY ID
		 FormQuestionDetail formquetiondetail = formquestiondetailrepository.findById(quedetailid);
		 formquestiondetailrepository.delete(formquetiondetail);
//		 DELETE OPTION by QUe ID
			for (FromQuestionOption formquetionoption : formquestionoptionrepository.findByQuedetailid(quedetailid)) {
				System.out.println("DELETE OPTION BY QUTION ID==>>>>>");
				formquestionoptionrepository.delete(formquetionoption);
				}
		
		return new ResponseEntity<>(new AjaxResponse("200", "Product has been deleted.", null), HttpStatus.OK);
	}
	
	
	
// FIND Charactoristics 
	@ResponseBody
	@GetMapping("getcharactoristics")
	public String getCharactoristics(@RequestParam("moduleid") Integer moduleid) {
		 System.out.println("get Charactoristic ");
		List<?> charactoristic = formheaderutil.getCharacteristic(moduleid);
		JSONArray jsonArray = new JSONArray(charactoristic);
		System.out.println(jsonArray);
		return String.valueOf(jsonArray);
	}
	
	
	

	// FIND Sub-Charactoristics 
		@ResponseBody
		@GetMapping("getsubcharactoristics")
		public String getSubCharacteristic(@RequestParam("characteristicid") Integer characteristicid) {
			 System.out.println("get Charactoristic ");
			List<?> subcharactoristic = formheaderutil.getSubCharacteristic(characteristicid);
			JSONArray jsonArray = new JSONArray(subcharactoristic);
			System.out.println(jsonArray);
			return String.valueOf(jsonArray);
		}
	
}
