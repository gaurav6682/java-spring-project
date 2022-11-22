package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "form_header")
@Component
public class FormHeader {
	
@Id
private Long formid;

@Column(name="titletext")
private String titletext;
private String aliasname;
private String module;
private String characteristic;
private String subcharacteristic;
private String recurrance;
private String startmonth;
private String complianceperiod;
private String date_from;
private String active;
private String textfield;

public FormHeader() {
	super();
}

public FormHeader(Long formid, String titletext, String aliasname, String module, String characteristic,
		String subcharacteristic, String recurrance, String startmonth, String complianceperiod, String date_from,
		String active, String textfield) {
	super();
	this.formid = formid;
	this.titletext = titletext;
	this.aliasname = aliasname;
	this.module = module;
	this.characteristic = characteristic;
	this.subcharacteristic = subcharacteristic;
	this.recurrance = recurrance;
	this.startmonth = startmonth;
	this.complianceperiod = complianceperiod;
	this.date_from = date_from;
	this.active = active;
	this.textfield = textfield;
}

public Long getFormid() {
	return formid;
}
public void setFormid(Long formid) {
	this.formid = formid;
}
public String getTitletext() {
	return titletext;
}
public void setTitletext(String titletext) {
	this.titletext = titletext;
}
public String getAliasname() {
	return aliasname;
}
public void setAliasname(String aliasname) {
	this.aliasname = aliasname;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public String getCharacteristic() {
	return characteristic;
}
public void setCharacteristic(String characteristic) {
	this.characteristic = characteristic;
}
public String getSubcharacteristic() {
	return subcharacteristic;
}
public void setSubcharacteristic(String subcharacteristic) {
	this.subcharacteristic = subcharacteristic;
}
public String getRecurrance() {
	return recurrance;
}
public void setRecurrance(String recurrance) {
	this.recurrance = recurrance;
}
public String getStartmonth() {
	return startmonth;
}
public void setStartmonth(String startmonth) {
	this.startmonth = startmonth;
}
public String getComplianceperiod() {
	return complianceperiod;
}
public void setComplianceperiod(String complianceperiod) {
	this.complianceperiod = complianceperiod;
}
public String getDate_from() {
	return date_from;
}
public void setDate_from(String date_from) {
	this.date_from = date_from;
}
public String getActive() {
	return active;
}
public void setActive(String active) {
	this.active = active;
}
public String getTextfield() {
	return textfield;
}
public void setTextfield(String textfield) {
	this.textfield = textfield;
}

@Override
public String toString() {
	return "FormHeader [formid=" + formid + ", titletext=" + titletext + ", aliasname=" + aliasname + ", module="
			+ module + ", characteristic=" + characteristic + ", subcharacteristic=" + subcharacteristic
			+ ", recurrance=" + recurrance + ", startmonth=" + startmonth + ", complianceperiod=" + complianceperiod
			+ ", date_from=" + date_from + ", active=" + active + ", textfield=" + textfield + "]";
}





}
