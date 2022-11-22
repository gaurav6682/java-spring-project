package com.project.repository;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.project.model.*;
public interface ProductRepository {

	public void save(ProductMaster productMaster);
	
	public void update(ProductMaster productMaster);

	public void delete(ProductMaster productMaster);

	public ProductMaster findById(Integer productid);

	
	public Map<String,Object> getPaginatedData(HttpServletRequest request);

	public List<ProductMaster> findAllProducts();
}
