package com.project.Util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AjaxResponse {
	private String status;
	private String message;

	private Map<String, Object> dataMap = new HashMap<>();

	public AjaxResponse(String status, String message, Map<String, Object> dataMap) {
		super();
		this.status = status;
		this.message = message;
		this.dataMap = dataMap;
	}

	public AjaxResponse() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		return "AjaxResponse [status=" + status + ", message=" + message + ", dataMap=" + dataMap + "]";
	}
}
