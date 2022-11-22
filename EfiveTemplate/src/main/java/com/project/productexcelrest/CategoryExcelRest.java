package com.project.productexcelrest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.createexcel.CategoryExcelGenerator;
import com.project.repository.CategoryRepository;
import com.project.model.*;

@RestController
public class CategoryExcelRest {

	@Autowired
	CategoryRepository catres;

	@Autowired
	CategoryMaster categorymaster;

	public void categorytoexcel(CategoryRepository catres) {
		this.catres = catres;
	}

	@GetMapping("/download/category.xlsx")
//@RequestMapping(path="download/category.xlsx")
	public ResponseEntity<InputStreamResource> excelCategoryReport() throws IOException {
		List<CategoryMaster> list = (List<CategoryMaster>) catres.findAllCategorys();
		ByteArrayInputStream in = CategoryExcelGenerator.categorytoexcel(list);
		// System.out.println("=============IN Download...........................");
		HttpHeaders headres = new HttpHeaders();
		headres.add("Content-Disposition", "attachment; filename=Category.xlsx");
		return ResponseEntity.ok().headers(headres).body(new InputStreamResource(in));
	}
}
