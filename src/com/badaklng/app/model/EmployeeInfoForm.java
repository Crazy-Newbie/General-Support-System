package com.badaklng.app.model;

import java.util.List;

import com.badaklng.app.hibernate.AppEmpInfoView;

public class EmployeeInfoForm {
	private AppEmpInfoView empInfo;
	private List<AppEmpInfoView> empInfoList;

	public List<AppEmpInfoView> getEmpInfoList() {
		return empInfoList;
	}

	public void setEmpInfoList(List<AppEmpInfoView> empInfoList) {
		this.empInfoList = empInfoList;
	}

	public AppEmpInfoView getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(AppEmpInfoView empInfo) {
		this.empInfo = empInfo;
	}
	
	
}
