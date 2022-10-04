package com.badaklng.app.model;

import com.badaklng.app.base.BaseModel;

public class PageGenerator extends BaseModel {

	public static final int PAGE_SIZE = 10;
	
	private long pageRequested = 1;
	private int maxPage = 0;
	private long totalRecords = 0;

	public PageGenerator() {
		setTotalRecords(0);
	}

	public boolean isFirstPage() {
		return pageRequested == 1;
	}

	public boolean isLastPage() {
		return pageRequested == maxPage;
	}

	public long getPageRequested() {
		return pageRequested;
	}

	public PageGenerator setPageRequested(long pageRequested) {
		this.pageRequested = pageRequested;
		return this;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
		maxPage = Double
				.valueOf(
						Math.ceil(Long.valueOf(totalRecords).doubleValue()
								/ PAGE_SIZE)).intValue();

	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartFrom() {
		long result = (pageRequested - 1) * PAGE_SIZE;
		return (Long.valueOf(result).intValue());

	}

}
