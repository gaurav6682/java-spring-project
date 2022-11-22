package com.project.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ProductMaster;
import com.project.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void save(ProductMaster productMaster) {
		productRepository.save(productMaster);
	}

	@Override
	public void update(ProductMaster productMaster) {
		productRepository.update(productMaster);
	}

	@Override
	public void delete(ProductMaster productMaster) {
		productRepository.delete(productMaster);
	}

	@Override
	public ProductMaster findById(Integer productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Map<String, Object> getPaginatedData(HttpServletRequest request) {
		return productRepository.getPaginatedData(request);

	}

}
