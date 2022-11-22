package com.project.model;

import org.springframework.stereotype.Component;

@Component
public class Characteristic {

	private Long charactoristicId;
	private String charactoristicname;
	private String active;
	private String createdby;
	private String createdon;
	private String modifiedby;
	private String modifiedon;
	private String ipaddress;
	
	
	public Characteristic() {
		super();
	}
	
	
	public Characteristic(Long charactoristicId, String charactoristicname, String active, String createdby,
			String createdon, String modifiedby, String modifiedon, String ipaddress) {
		super();
		this.charactoristicId = charactoristicId;
		this.charactoristicname = charactoristicname;
		this.active = active;
		this.createdby = createdby;
		this.createdon = createdon;
		this.modifiedby = modifiedby;
		this.modifiedon = modifiedon;
		this.ipaddress = ipaddress;
	}


	public Long getCharactoristicId() {
		return charactoristicId;
	}
	public void setCharactoristicId(Long charactoristicId) {
		this.charactoristicId = charactoristicId;
	}
	public String getCharactoristicname() {
		return charactoristicname;
	}
	public void setCharactoristicname(String charactoristicname) {
		this.charactoristicname = charactoristicname;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public String getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	@Override
	public String toString() {
		return "Characteristic [charactoristicId=" + charactoristicId + ", charactoristicname=" + charactoristicname
				+ ", active=" + active + ", createdby=" + createdby + ", createdon=" + createdon + ", modifiedby="
				+ modifiedby + ", modifiedon=" + modifiedon + ", ipaddress=" + ipaddress + "]";
	}
	
	
}
