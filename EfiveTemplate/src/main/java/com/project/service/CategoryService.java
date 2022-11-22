package com.project.service;




import com.project.model.CategoryMaster;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface CategoryService {

	public void save(CategoryMaster categoryMaster);

	public void update(CategoryMaster categoryMaster);

	public void delete(CategoryMaster categoryMaster);

	public CategoryMaster findById(Long categoryid);

	
	public Map<String,Object> getPaginatedData(HttpServletRequest request);

}
