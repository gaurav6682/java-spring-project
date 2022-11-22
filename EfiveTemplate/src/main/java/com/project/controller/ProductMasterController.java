package com.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpHeaders;

import com.project.Util.AjaxResponse;
import com.project.Util.CategoryUtil;
import com.project.Util.FileUpload;
import com.project.model.ProductMaster;
import com.project.service.*;

@Controller
public class ProductMasterController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryUtil categoryUtil;

	@Autowired
	FileUpload fileUpload;
	
	@RequestMapping("addproduct")
	public String AddProduct(Model model) {
		model.addAttribute("categoryList", categoryUtil.getCategory());
		return "add_product_master";
	}

	@RequestMapping("master")
	public String DatatableproductMaster() {
		return "datatable_product_master";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView EditData(@PathVariable("id") Integer id, Model model) {
		// System.out.println("edit controller");
		ProductMaster productModel = productService.findById(id);
		model.addAttribute("categoryList", categoryUtil.getCategory());
		model.addAttribute("subCategoryList", categoryUtil.getSubcategory(productModel.getCategoryid()));
		System.out.println("==========================>>>>>"+ productModel.toString());
		return new ModelAndView("add_product_master", "editproduct", productModel);
	}

	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	public String AddProduct(@RequestPart("productimagedata") MultipartFile file,
			@ModelAttribute("productMaster") ProductMaster productMaster) {
		// System.out.println("-------------save controller------------");
		String filename = null;
		ProductMaster productData = productService.findById(productMaster.getProductid());
		if (productData != null) {
			if (!file.isEmpty()) {
				filename = FileUpload.uploadFile(file, "uploads");
				System.err.println(filename);
				if (filename != null) {
					productMaster.setProductimage(filename);
				}
			} else {
				productMaster.setProductimage(productData.getProductimage());
			}
			productService.update(productMaster);

		} else {
			if (!file.isEmpty()) {
				filename = FileUpload.uploadFile(file, "uploads");
				System.err.println(filename);
				if (filename != null) {
					productMaster.setProductimage(filename);
				}
			} else {
				productMaster.setProductimage("images/empty1.png");
			}
			productService.save(productMaster);
		}
		return "datatable_product_master";
	}

	@ResponseBody
	@RequestMapping(value = "getData")
	public ResponseEntity<Object> getData(HttpServletRequest request, Model model) {

		return new ResponseEntity<>(productService.getPaginatedData(request), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("getSubCategory")
	public String getSubCategory(@RequestParam("categoryid") Integer categoryid) {
		// System.out.println("get sub master ");
		List<?> subCategory = categoryUtil.getSubcategory(categoryid);
		JSONArray jsonArray = new JSONArray(subCategory);
		return String.valueOf(jsonArray);
	}

	@ResponseBody
	@RequestMapping(value = "delete/{productid}", method = RequestMethod.POST)
	public ResponseEntity<Object> delete(@PathVariable("productid") Integer productid) {
		// System.out.println("delete product controller");
		ProductMaster productModel = productService.findById(productid);
		// System.out.println("productModel " + productModel);
		productService.delete(productModel);
		return new ResponseEntity<>(new AjaxResponse("200", "Product has been deleted.", null), HttpStatus.OK);
	}

}
