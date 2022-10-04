package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppPositionTmpView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_POSITION_TMP_VIEW")

public class AppPositionTmpView extends BaseModel implements java.io.Serializable {

	// Fields

	private String positionId;
	private String employeeId;
	private Timestamp startDate;
	private Timestamp endDate;
	private String posid;
	private String empid;

	// Constructors

	/** default constructor */
	public AppPositionTmpView() {
	}

	/** minimal constructor */
	public AppPositionTmpView(String positionId) {
		this.positionId = positionId;
	}

	/** full constructor */
	public AppPositionTmpView(String positionId, String employeeId, Timestamp startDate, Timestamp endDate,
			String posid, String empid) {
		this.positionId = positionId;
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.posid = posid;
		this.empid = empid;
	}

	// Property accessors
	@Id

	@Column(name = "POSITION_ID", length = 10)

	public String getPositionId() {
		return this.positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	@Column(name = "EMPLOYEE_ID", length = 10)

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "START_DATE", length = 7)

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@Column(name = "END_DATE", length = 7)

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@Column(name = "POSID", length = 10)

	public String getPosid() {
		return this.posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	@Column(name = "EMPID", length = 10)

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

}