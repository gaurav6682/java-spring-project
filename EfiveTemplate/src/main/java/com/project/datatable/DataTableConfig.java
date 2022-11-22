package com.project.datatable;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class DataTableConfig {
	
	// final column array
	String columnSpecification[] = { "productid", "productname"};

	// datatable parameters
	public Long draw;
	public Long start;
	public Long length;
	public String search;
	public Integer sortColumn;
	public String sortDir;
	
	// manual process
	private String columnName;
	
	public DataTableConfig(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		if (parameterNames.hasMoreElements()) {
			this.draw = Long.parseLong(request.getParameter("draw"));
			this.start = Long.parseLong(request.getParameter("start"));
			this.length = Long.parseLong(request.getParameter("length"));
			this.search = request.getParameter("search[value]");
			this.sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			this.columnName = columnSpecification[sortColumn];
			this.sortDir = request.getParameter("order[0][dir]");
		}
	}
		
	public Long getDraw() {
		return draw;
	}
	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String[] getColumnSpecification() {
		return columnSpecification;
	}
	public void setColumnSpecification(String[] columnSpecification) {
		this.columnSpecification = columnSpecification;
	}
	public Integer getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(Integer sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	
	
	@Override
	public String toString() {
		return "DataTableConfig [columnSpecification=" + Arrays.toString(columnSpecification) + ", draw=" + draw
				+ ", start=" + start + ", length=" + length + ", search=" + search + ", sortColumn=" + sortColumn
				+ ", sortDir=" + sortDir + ", columnName=" + columnName + "]";
	}
	
	
}
