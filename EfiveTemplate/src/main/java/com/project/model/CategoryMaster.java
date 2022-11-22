package com.project.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Entity
@Table(name = "categorymaster")
@Component
public class CategoryMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryid;
	@Column(name = "categoryname")
	private String categoryname;
	private String categorydescription;
	private String active;

	
	
	
	public CategoryMaster() {
		super();
	}

	public CategoryMaster(Long categoryid, String categoryname, String categorydescription, String active) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.categorydescription = categorydescription;
		this.active = active;
	}

	public Long getcategoryid() {
		return categoryid;
	}

	public void setcategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getcategoryname() {
		return categoryname;
	}

	public void setcategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getcategorydescription() {
		return categorydescription;
	}

	public void setcategorydescription(String categorydescription) {
		this.categorydescription = categorydescription;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "CategoryMaster [categoryid=" + categoryid + ", categoryname=" + categoryname + ", categorydescription="
				+ categorydescription + ", active=" + active + "]";
	}
	
	
	
	
}
