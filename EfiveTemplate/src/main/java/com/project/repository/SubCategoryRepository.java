package com.project.repository;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.project.model.*;

public interface SubCategoryRepository {

	public void save(SubCategoryMaster subcategoryMaster);

	public void update(SubCategoryMaster subcategoryMaster);

	public void delete(SubCategoryMaster subcategoryMaster);

	public SubCategoryMaster findById(Long subcategoryid);

	
	public Map<String,Object> getPaginatedData(HttpServletRequest request);
	
	public List<SubCategoryMaster> findAllSubCategorys();
}
