package com.project.Util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.model.FormHeader;
import com.project.model.FormQuestionDetail;
import com.project.model.FromQuestionOption;

@Component
public class FormData {

	FormHeader formheader;
	List<FormQuestionDetail> formquestiondetail;
	List<FromQuestionOption> formquestionoption;
	public FormData() {
		super();
	}
	public FormData(FormHeader formheader, List<FormQuestionDetail> formquestiondetail,
			List<FromQuestionOption> formquestionoption) {
		super();
		this.formheader = formheader;
		this.formquestiondetail = formquestiondetail;
		this.formquestionoption = formquestionoption;
	}
	public FormHeader getFormheader() {
		return formheader;
	}
	public void setFormheader(FormHeader formheader) {
		this.formheader = formheader;
	}
	public List<FormQuestionDetail> getFormquestiondetail() {
		return formquestiondetail;
	}
	public void setFormquestiondetail(List<FormQuestionDetail> formquestiondetail) {
		this.formquestiondetail = formquestiondetail;
	}
	public List<FromQuestionOption> getFormquestionoption() {
		return formquestionoption;
	}
	public void setFormquestionoption(List<FromQuestionOption> formquestionoption) {
		this.formquestionoption = formquestionoption;
	}
	@Override
	public String toString() {
		return "FormData [formheader=" + formheader + ", formquestiondetail=" + formquestiondetail
				+ ", formquestionoption=" + formquestionoption + "]";
	}
	
	

	
}
