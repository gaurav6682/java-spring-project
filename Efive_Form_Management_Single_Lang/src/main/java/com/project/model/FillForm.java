package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "fill_form_details")
@Component
public class FillForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ansid;

	@Column(name = "userid")
	private Long userid;
	private Long formid;
	private Long quedetailid;
	private String ansname;
	public FillForm() {
		super();
	}
	public FillForm(Long ansid, Long userid, Long formid, Long quedetailid, String ansname) {
		super();
		this.ansid = ansid;
		this.userid = userid;
		this.formid = formid;
		this.quedetailid = quedetailid;
		this.ansname = ansname;
	}
	public Long getAnsid() {
		return ansid;
	}
	public void setAnsid(Long ansid) {
		this.ansid = ansid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
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
	public String getAnsname() {
		return ansname;
	}
	public void setAnsname(String ansname) {
		this.ansname = ansname;
	}
	@Override
	public String toString() {
		return "FillForm [ansid=" + ansid + ", userid=" + userid + ", formid=" + formid + ", quedetailid=" + quedetailid
				+ ", ansname=" + ansname + "]";
	}

	

}
