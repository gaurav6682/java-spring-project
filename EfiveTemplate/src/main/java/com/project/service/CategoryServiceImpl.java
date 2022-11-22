package com.project.service;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.CategoryMaster;
import com.project.repository.CategoryRepository;


@Service
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void save(CategoryMaster categoryMaster) {
		categoryRepository.save(categoryMaster);
	}

	@Override
	public void update(CategoryMaster categoryMaster) {
		categoryRepository.update(categoryMaster);
	}

	@Override
	public void delete(CategoryMaster categoryMaster) {
		categoryRepository.delete(categoryMaster);
	}

	@Override
	public CategoryMaster findById(Long categoryId) {
		return categoryRepository.findById(categoryId);
	}

	@Override
	public Map<String, Object> getPaginatedData(HttpServletRequest request) {
		return categoryRepository.getPaginatedData(request);

	}

}





