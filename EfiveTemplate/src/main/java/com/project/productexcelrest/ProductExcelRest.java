package com.project.productexcelrest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.createexcel.ExcelGenrator;
import com.project.repository.ProductRepository;
import com.project.model.*;

@RestController
public class ProductExcelRest {

	@Autowired
	ProductRepository prores;

	@Autowired
	ProductMaster productmaster;

	public void producttoexcel(ProductRepository prores) {
		this.prores = prores;
	}

	@GetMapping("download/product.xlsx")
	public ResponseEntity<InputStreamResource> excelProductReport() throws IOException {
		List<ProductMaster> list = (List<ProductMaster>) prores.findAllProducts();
		ByteArrayInputStream in = ExcelGenrator.producttoexcel(list);
		// System.out.println("IN Download...........................");
		HttpHeaders headres = new HttpHeaders();
		headres.add("Content-Disposition", "attachment; filename=Product.xlsx");
		return ResponseEntity.ok().headers(headres).body(new InputStreamResource(in));
	}
}
