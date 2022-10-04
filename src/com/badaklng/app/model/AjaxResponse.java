package com.badaklng.app.model;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.JDBCException;

import com.badaklng.app.utilities.ExceptionTokenizer;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AjaxResponse {

	public Boolean success;
	public String desc;
	public Map<String, String> data = new HashMap<String, String>();
	public Map<String, Object> obj = new HashMap<String, Object>();

	// additional for accomodating Sweet Alert
	public String icon;

	public AjaxResponse() {

	}

	public AjaxResponse(Boolean status) {
		this.success = status;
		if (status) {
			desc = "Success";
			icon = "success";
		} else {
			desc = "Failed";
			icon = "error";
		}
	}

	public AjaxResponse(Exception e) {
		this.success = false;
		if (e instanceof JDBCException) {
			int errorCode = ((JDBCException) e).getErrorCode();
			this.desc = ExceptionTokenizer.getMessageSegment(e.getMessage(), 1);
			if (errorCode == 20000) {
				this.icon = "error";
			} else if (errorCode == 20001) {
				this.icon = "warning";
			} else if (errorCode == 20002) {
				this.icon = "info";
			} else {
				this.icon = "error";
				this.desc = e.getMessage();
			}
		} else {
			this.icon = "error";
			this.desc = e.getMessage();
		}
	}

	public AjaxResponse(Boolean status, String desc) {
		this.success = status;
		if (status) {
			icon = "success";
		} else {
			icon = "error";
		}
		this.desc = desc;
	}

	public void setData(String key, String value) {
		data.put(key, value);
	}

	public String getData(String key) {
		return data.get(key);
	}

	public void setObj(String key, Object value) {
		obj.put(key, value);
	}

	@JsonIgnore
	public Object getObj(String key) {
		return obj.get(key);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
