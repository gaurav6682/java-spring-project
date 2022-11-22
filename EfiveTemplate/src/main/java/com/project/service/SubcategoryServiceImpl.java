package com.project.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.SubCategoryMaster;
import com.project.repository.SubCategoryRepository;

@Service
public class SubcategoryServiceImpl implements SubCategoryService{
	@Autowired
	SubCategoryRepository subcategoryRepository;
	
	@Override
	public void save(SubCategoryMaster subcategoryMaster) {
		subcategoryRepository.save(subcategoryMaster);
	}

	@Override
	public void update(SubCategoryMaster subcategoryMaster) {
		subcategoryRepository.update(subcategoryMaster);
	}

	@Override
	public void delete(SubCategoryMaster subcategoryMaster) {
		subcategoryRepository.delete(subcategoryMaster);
	}

	@Override
	public SubCategoryMaster findById(Long subcategoryid) {
		return subcategoryRepository.findById(subcategoryid);
	}

	@Override
	public Map<String, Object> getPaginatedData(HttpServletRequest request) {
		return subcategoryRepository.getPaginatedData(request);

	}
}
