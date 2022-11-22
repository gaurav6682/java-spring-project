package com.project.service;

import com.project.model.ProductMaster;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ProductService {

	public void save(ProductMaster productMaster);

	public void update(ProductMaster productMaster);

	public void delete(ProductMaster productMaster);

	public ProductMaster findById(Integer productId);

	public Map<String, Object> getPaginatedData(HttpServletRequest request);
	
}
