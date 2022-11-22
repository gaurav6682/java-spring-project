package com.project.repository;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.project.model.*;
public interface CategoryRepository  {

	public void save(CategoryMaster categoryMaster);

	public void update(CategoryMaster categoryMaster);

	public void delete(CategoryMaster categoryMaster);

	public CategoryMaster findById(Long categoryid);

	
	public Map<String,Object> getPaginatedData(HttpServletRequest request);
	public List<CategoryMaster> findAllCategorys();


	//List<CategoryMaster> findCategoryName();

}






