package com.badaklng.app.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.JDBCException;

import com.badaklng.app.utilities.ExceptionTokenizer;

public class DataResponse {

	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<?> data;
	private String error;
	private Boolean stats;

	// additional data for advance query
	private Integer start;
	private Integer length;
	private String search;
	private Boolean regex;

	public DataResponse(Integer draw) {
		this.draw = draw;
		recordsTotal = 0;
		recordsFiltered = 0;
		error = "";
	}

	public DataResponse(HttpServletRequest request) {
		this.setStart(Integer.parseInt(request.getParameter("start")));
		this.setLength(Integer.parseInt(request.getParameter("length")));
		this.setDraw(Integer.parseInt(request.getParameter("draw")));

		this.setSearch(request.getParameter("search[value]"));
		this.setRegex(Boolean.valueOf(request.getParameter("search[regex]")));
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal.intValue();
	}

	public void setRecordsTotal(Object uniqueResult) {
		this.recordsTotal = ((Long) uniqueResult).intValue();
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered.intValue();
	}

	public void setRecordsFiltered(Object uniqueResult) {
		this.recordsFiltered = ((Long) uniqueResult).intValue();
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setError(Exception e) {
		if (e instanceof JDBCException) {
			this.error = ExceptionTokenizer.getMessageSegment(e.getMessage(), 1);
		} else
			this.error = e.getMessage();
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Boolean getRegex() {
		return regex;
	}

	public void setRegex(Boolean regex) {
		this.regex = regex;
	}

	public Boolean getStats() {
		return stats;
	}

	public void setStats(Boolean stats) {
		this.stats = stats;
	}

}
