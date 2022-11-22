package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "fill_form_header")
@Component
public class FillFormHeader {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long completedformid;

	@Column(name = "formid")
	private Long formid;
	private Long userid;
	private String compliteddate;
	public FillFormHeader() {
		super();
	}
	public FillFormHeader(Long completedformid, Long formid, Long userid, String compliteddate) {
		super();
		this.completedformid = completedformid;
		this.formid = formid;
		this.userid = userid;
		this.compliteddate = compliteddate;
	}
	public Long getCompletedformid() {
		return completedformid;
	}
	public void setCompletedformid(Long completedformid) {
		this.completedformid = completedformid;
	}
	public Long getFormid() {
		return formid;
	}
	public void setFormid(Long formid) {
		this.formid = formid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getCompliteddate() {
		return compliteddate;
	}
	public void setCompliteddate(String compliteddate) {
		this.compliteddate = compliteddate;
	}
	@Override
	public String toString() {
		return "FillFormHeader [completedformid=" + completedformid + ", formid=" + formid + ", userid=" + userid
				+ ", compliteddate=" + compliteddate + "]";
	}
	
	
	
	
	
}
