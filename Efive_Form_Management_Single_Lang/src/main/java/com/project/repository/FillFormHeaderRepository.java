package com.project.repository;

import java.util.List;

import com.project.model.FillForm;
import com.project.model.FillFormHeader;
import com.project.model.FormQuestionDetail;

public interface FillFormHeaderRepository {

	public void save(FillFormHeader fillformheader);
	
	public void update(FillFormHeader fillformheader);

	public void delete(FillFormHeader fillformheader);

	public FillFormHeader findByFormid(Long formid);


	public List<FillFormHeader> findAllForm();
	
	
	
}
