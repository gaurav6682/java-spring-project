package com.project.repository;

import java.util.List;


import com.project.model.*;

public interface FormHeaderRepository {

	public void save(FormHeader formheader);
	
	public void update(FormHeader formheader);

	public void delete(FormHeader formheader);

	public FormHeader findById(Long formid);


	public List<FormHeader> findAllForm();
}
