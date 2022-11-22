package com.project.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "form_question_option")
@Component
public class FromQuestionOption {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long optionid;

	@Column(name = "formid")
	private Long formid;
	private Long quedetailid;
	private String optionname;
	
	public FromQuestionOption() {
		super();
	}
	public FromQuestionOption(Long optionid, Long formid, Long quedetailid, String optionname) {
		super();
		this.optionid = optionid;
		this.formid = formid;
		this.quedetailid = quedetailid;
		this.optionname = optionname;
	}
	public Long getOptionid() {
		return optionid;
	}
	public void setOptionid(Long optionid) {
		this.optionid = optionid;
	}
	public Long getFormid() {
		return formid;
	}
	public void setFormid(Long formid) {
		this.formid = formid;
	}
	public Long getQuedetailid() {
		return quedetailid;
	}
	public void setQuedetailid(Long quedetailid) {
		this.quedetailid = quedetailid;
	}
	public String getOptionname() {
		return optionname;
	}
	public void setOptionname(String optionname) {
		this.optionname = optionname;
	}
	
	
	@Override
	public String toString() {
		return "FromQuestionOption [optionid=" + optionid + ", formid=" + formid + ", quedetailid=" + quedetailid
				+ ", optionname=" + optionname + "]";
	}


}

