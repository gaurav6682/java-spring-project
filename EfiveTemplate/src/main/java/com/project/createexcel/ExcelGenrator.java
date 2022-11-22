package com.project.createexcel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.project.model.ProductMaster;

public class ExcelGenrator {
	public static ByteArrayInputStream producttoexcel(List<ProductMaster> productmasters) throws IOException{
	String[] colums = {"Product ID","Product Name","Description","Category","Discount","Active"};
	try(
		Workbook workbook=new XSSFWorkbook();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
	){
		Sheet sheet=workbook.createSheet("Product");
		
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLUE.getIndex());
		
		CellStyle headerCellStyle=workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		Row headerRow = sheet.createRow(0);
		
		for(int col=0; col<colums.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(colums[col]);
			cell.setCellStyle(headerCellStyle);
		}
		
		int rowIdx = 1;
		for(ProductMaster pro:productmasters) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(pro.getProductid());
			row.createCell(1).setCellValue(pro.getProductname());
			row.createCell(2).setCellValue(pro.getProductdescription());
			row.createCell(3).setCellValue(pro.getCategoryname());
			row.createCell(4).setCellValue(pro.getProductdiscount());
			row.createCell(5).setCellValue(pro.getActive());
		}
		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
	}
	}

}
