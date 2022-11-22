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


import com.project.model.CategoryMaster;

public class CategoryExcelGenerator {
	public static ByteArrayInputStream categorytoexcel(List<CategoryMaster> categorymasters) throws IOException{
	String[] colums = {"Category ID","Category Name","Description","Active"};
	try(
		Workbook workbook=new XSSFWorkbook();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
	){
		Sheet sheet=workbook.createSheet("Category");
		
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
		for(CategoryMaster cat:categorymasters) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(cat.getcategoryid());
			row.createCell(1).setCellValue(cat.getcategoryname());
			row.createCell(2).setCellValue(cat.getcategorydescription());
			row.createCell(3).setCellValue(cat.getActive());
		}
		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
	}
	}

}


 
