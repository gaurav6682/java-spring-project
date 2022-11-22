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

import com.project.createexcel.SubCategoryExcelGenerator;
import com.project.model.SubCategoryMaster;
import com.project.repository.SubCategoryRepository;

@RestController
public class SubCategoryExcelRest {
	@Autowired
	SubCategoryRepository subcatres;

	@Autowired
	SubCategoryMaster subcategorymaster;

	public void subcategorytoexcel(SubCategoryRepository subcatres) {
		this.subcatres = subcatres;
	}

	@GetMapping("/download/subcategory.xlsx")
//@RequestMapping(path="download/category.xlsx")
	public ResponseEntity<InputStreamResource> excelSubCategoryReport() throws IOException {
		List<SubCategoryMaster> list = (List<SubCategoryMaster>) subcatres.findAllSubCategorys();
		ByteArrayInputStream in = SubCategoryExcelGenerator.subcategorytoexcel(list);
		// System.out.println("IN Download....");
		HttpHeaders headres = new HttpHeaders();
		headres.add("Content-Disposition", "attachment; filename=SubCategory.xlsx");
		return ResponseEntity.ok().headers(headres).body(new InputStreamResource(in));
	}
}
