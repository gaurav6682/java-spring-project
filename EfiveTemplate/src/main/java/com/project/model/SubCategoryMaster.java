package com.project.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table(name = "subcategorymaster")
@Component
public class SubCategoryMaster {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long subcategoryid;
		@Column(name = "categoryname")
		private String categoryname;
		private String subcategoryname;
		private String subcategorydescription;
		private String active;
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private int categoryid;
		
		public SubCategoryMaster() {
			super();
		}

		public SubCategoryMaster(Long subcategoryid, String categoryname, String subcategoryname,
				String subcategorydescription, String active, int categoryid) {
			super();
			this.subcategoryid = subcategoryid;
			this.categoryname = categoryname;
			this.subcategoryname = subcategoryname;
			this.subcategorydescription = subcategorydescription;
			this.active = active;
			this.categoryid = categoryid;
		}

		public Long getSubcategoryid() {
			return subcategoryid;
		}

		public void setSubcategoryid(Long subcategoryid) {
			this.subcategoryid = subcategoryid;
		}

		public String getCategoryname() {
			return categoryname;
		}

		public void setCategoryname(String categoryname) {
			this.categoryname = categoryname;
		}

		public String getSubcategoryname() {
			return subcategoryname;
		}

		public void setSubcategoryname(String subcategoryname) {
			this.subcategoryname = subcategoryname;
		}

		public String getSubcategorydescription() {
			return subcategorydescription;
		}

		public void setSubcategorydescription(String subcategorydescription) {
			this.subcategorydescription = subcategorydescription;
		}

		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
		}

		public int getCategoryid() {
			return categoryid;
		}

		public void setCategoryid(int categoryid) {
			this.categoryid = categoryid;
		}

		@Override
		public String toString() {
			return "SubCategoryMaster [subcategoryid=" + subcategoryid + ", categoryname=" + categoryname
					+ ", subcategoryname=" + subcategoryname + ", subcategorydescription=" + subcategorydescription
					+ ", active=" + active + ", categoryid=" + categoryid + "]";
		}
		
		
		
		

		
	}

	

