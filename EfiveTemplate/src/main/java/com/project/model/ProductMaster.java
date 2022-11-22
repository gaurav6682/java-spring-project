package com.project.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "productmaster")
@Component
public class ProductMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productid;
	
	@Column(name="productname")
	private String productname;
	private String productdescription;
	private Integer categoryid;
	private String categoryname;
	private Integer subcategoryid;
	private BigDecimal productprice;
	private String manufacturedate;
	private String productserialno;
	private String productwarrenty;
	private String productimage;
	private String productcondition;
	private String productcolor;
	private String productdiscount;
	private String productvalidDate;
	private String productvalidtoDate;
	private String active;
	
	
	public ProductMaster() {}
		
	public ProductMaster(int productid, String productname, String productdescription, Integer categoryid,
			String categoryname, Integer subcategoryid, BigDecimal productprice, String manufacturedate,
			String productserialno, String productwarrenty, String productimage, String productcondition,
			String productcolor, String productdiscount, String productvalidDate, String productvalidtoDate,
			String active) {
		super();
		
		this.productid = productid;
		this.productname = productname;
		this.productdescription = productdescription;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.subcategoryid = subcategoryid;
		this.productprice = productprice;
		this.manufacturedate = manufacturedate;
		this.productserialno = productserialno;
		this.productwarrenty = productwarrenty;
		this.productimage = productimage;
		this.productcondition = productcondition;
		this.productcolor = productcolor;
		this.productdiscount = productdiscount;
		this.productvalidDate = productvalidDate;
		this.productvalidtoDate = productvalidtoDate;
		this.active = active;
	}
	
	
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductdescription() {
		return productdescription;
	}
	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public Integer getSubcategoryid() {
		return subcategoryid;
	}
	public void setSubcategoryid(Integer subcategoryid) {
		this.subcategoryid = subcategoryid;
	}
	public BigDecimal getProductprice() {
		return productprice;
	}
	public void setProductprice(BigDecimal productprice) {
		this.productprice = productprice;
	}
	public String getManufacturedate() {
		return manufacturedate;
	}
	public void setManufacturedate(String manufacturedate) {
		this.manufacturedate = manufacturedate;
	}
	public String getProductserialno() {
		return productserialno;
	}
	public void setProductserialno(String productserialno) {
		this.productserialno = productserialno;
	}
	public String getProductwarrenty() {
		return productwarrenty;
	}
	public void setProductwarrenty(String productwarrenty) {
		this.productwarrenty = productwarrenty;
	}
	public String getProductimage() {
		return productimage;
	}
	public void setProductimage(String productimage) {
		this.productimage = productimage;
	}
	public String getProductcondition() {
		return productcondition;
	}
	public void setProductcondition(String productcondition) {
		this.productcondition = productcondition;
	}
	public String getProductcolor() {
		return productcolor;
	}
	public void setProductcolor(String productcolor) {
		this.productcolor = productcolor;
	}
	public String getProductdiscount() {
		return productdiscount;
	}
	public void setProductdiscount(String productdiscount) {
		this.productdiscount = productdiscount;
	}
	public String getProductvalidDate() {
		return productvalidDate;
	}
	public void setProductvalidDate(String productvalidDate) {
		this.productvalidDate = productvalidDate;
	}
	public String getProductvalidtoDate() {
		return productvalidtoDate;
	}
	public void setProductvalidtoDate(String productvalidtoDate) {
		this.productvalidtoDate = productvalidtoDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "productmaster [productid=" + productid + ", productname=" + productname + ", productdescription="
				+ productdescription + ", categoryid=" + categoryid + ", categoryname=" + categoryname +", subcategoryid=" + subcategoryid
				+ ", productprice=" + productprice + ", manufacturedate=" + manufacturedate + ", productserialno="
				+ productserialno + ", productwarrenty=" + productwarrenty + ", productimage=" + productimage
				+ ", productcondition=" + productcondition + ", productcolor=" + productcolor + ", productdiscount="
				+ productdiscount + ", productvalidDate=" + productvalidDate + ", productvalidtoDate="
				+ productvalidtoDate + ", active=" + active + "]";
	}

	
}
