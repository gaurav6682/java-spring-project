package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "form_question_detail")
@Component
public class FormQuestionDetail {
	@Id
	private Long quedetailid;

	@Column(name = "formid")
	private Long formid;
	private String quelablename;
	private String quename;
	private String quedescription;
	private Boolean iscompulsory;
	private Boolean hasoption;
	private Long anstypeid;
	private Long sortorder;

	public FormQuestionDetail() {
		super();
	}

	public FormQuestionDetail(Long quedetailid, Long formid, String quelablename, String quename, String quedescription,
			Boolean iscompulsory, Boolean hasoption, Long anstypeid, Long sortorder) {
		super();
		this.quedetailid = quedetailid;
		this.formid = formid;
		this.quelablename = quelablename;
		this.quename = quename;
		this.quedescription = quedescription;
		this.iscompulsory = iscompulsory;
		this.hasoption = hasoption;
		this.anstypeid = anstypeid;
		this.sortorder = sortorder;
	}

	public Long getQuedetailid() {
		return quedetailid;
	}

	public void setQuedetailid(Long quedetailid) {
		this.quedetailid = quedetailid;
	}

	public Long getFormid() {
		return formid;
	}

	public void setFormid(Long formid) {
		this.formid = formid;
	}

	public String getQuelablename() {
		return quelablename;
	}

	public void setQuelablename(String quelablename) {
		this.quelablename = quelablename;
	}

	public String getQuename() {
		return quename;
	}

	public void setQuename(String quename) {
		this.quename = quename;
	}

	public String getQuedescription() {
		return quedescription;
	}

	public void setQuedescription(String quedescription) {
		this.quedescription = quedescription;
	}

	public Boolean getIscompulsory() {
		return iscompulsory;
	}

	public void setIscompulsory(Boolean iscompulsory) {
		this.iscompulsory = iscompulsory;
	}

	public Boolean getHasoption() {
		return hasoption;
	}

	public void setHasoption(Boolean hasoption) {
		this.hasoption = hasoption;
	}

	public Long getAnstypeid() {
		return anstypeid;
	}

	public void setAnstypeid(Long anstypeid) {
		this.anstypeid = anstypeid;
	}

	public Long getSortorder() {
		return sortorder;
	}

	public void setSortorder(Long sortorder) {
		this.sortorder = sortorder;
	}

	@Override
	public String toString() {
		return "FormQuestionDetail [quedetailid=" + quedetailid + ", formid=" + formid + ", quelablename=" + quelablename
				+ ", quename=" + quename + ", quedescription=" + quedescription + ", iscompulsory=" + iscompulsory
				+ ", hasoption=" + hasoption + ", anstypeid=" + anstypeid + ", sortorder=" + sortorder + "]";
	}

}
