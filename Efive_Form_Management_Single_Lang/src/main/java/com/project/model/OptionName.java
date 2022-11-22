package com.project.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OptionName {

	List<OptionName> optionname;
	

	public OptionName(List<OptionName> optionname) {
		super();
		this.optionname = optionname;
	}

	public OptionName() {
		super();
	}

	public List<OptionName> getOptionname() {
		return optionname;
	}

	public void setOptionname(List<OptionName> optionname) {
		this.optionname = optionname;
	}

	@Override
	public String toString() {
		return "OptionName [optionname=" + optionname + "]";
	}
	
	
}
