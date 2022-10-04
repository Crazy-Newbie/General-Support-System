package com.badaklng.app.model;

import com.badaklng.app.constant.SortColumnDirectionEnum;

public class SortColumn {
	private String columnKey;
	private String columnName;
	private SortColumnDirectionEnum sortDirection;

	public String getColumnKey() {
		return this.columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public SortColumn() {
		this.columnName = "";
		this.columnKey = "";
		this.sortDirection = SortColumnDirectionEnum.ASC;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public SortColumnDirectionEnum getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortColumnDirectionEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public SortColumn(String columnName, String columnKey,
			SortColumnDirectionEnum sortDirection) {
		super();
		this.columnName = columnName;
		this.sortDirection = sortDirection;
		this.columnKey = columnKey;
	}

}
