package com.badaklng.app.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppEmpLkpView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_EMP_LKP_VIEW")

public class AppEmpLkpView extends BaseModel implements java.io.Serializable {

	// Fields

	private String emplid;
	private String nameDisplay;
	private String deptId;
	private String deptDesc;
	private String sectionId;
	private String sectionDesc;
	private String positionNbr;
	private String positionDesc;
	private String posReportTo;
	private String posReportToDesc;
	private String nameReportTo;
	private String email;
	private String emplidReportTo;
	private String empStatus;
	private String empStatusDesc;
	private String costCenter;

	// Constructors

	/** default constructor */
	public AppEmpLkpView() {
	}

	/** minimal constructor */
	public AppEmpLkpView(String emplid) {
		this.emplid = emplid;
	}

	/** full constructor */
	public AppEmpLkpView(String emplid, String nameDisplay, String deptId, String deptDesc, String sectionId,
			String sectionDesc, String positionNbr, String positionDesc, String posReportTo, String posReportToDesc,
			String nameReportTo, String email, String emplidReportTo, String empStatus, String empStatusDesc,
			String costCenter) {
		this.emplid = emplid;
		this.nameDisplay = nameDisplay;
		this.deptId = deptId;
		this.deptDesc = deptDesc;
		this.sectionId = sectionId;
		this.sectionDesc = sectionDesc;
		this.positionNbr = positionNbr;
		this.positionDesc = positionDesc;
		this.posReportTo = posReportTo;
		this.posReportToDesc = posReportToDesc;
		this.nameReportTo = nameReportTo;
		this.email = email;
		this.emplidReportTo = emplidReportTo;
		this.empStatus = empStatus;
		this.empStatusDesc = empStatusDesc;
		this.costCenter = costCenter;
	}

	// Property accessors
	@Id

	@Column(name = "EMPLID", nullable = false, length = 11)

	public String getEmplid() {
		return this.emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	@Column(name = "NAME_DISPLAY", length = 50)

	public String getNameDisplay() {
		return this.nameDisplay;
	}

	public void setNameDisplay(String nameDisplay) {
		this.nameDisplay = nameDisplay;
	}

	@Column(name = "DEPT_ID", length = 10)

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "DEPT_DESC", length = 30)

	public String getDeptDesc() {
		return this.deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	@Column(name = "SECTION_ID", length = 10)

	public String getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	@Column(name = "SECTION_DESC", length = 30)

	public String getSectionDesc() {
		return this.sectionDesc;
	}

	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}

	@Column(name = "POSITION_NBR", length = 8)

	public String getPositionNbr() {
		return this.positionNbr;
	}

	public void setPositionNbr(String positionNbr) {
		this.positionNbr = positionNbr;
	}

	@Column(name = "POSITION_DESC", length = 30)

	public String getPositionDesc() {
		return this.positionDesc;
	}
	
//	@Transient
//	public String getPositionDescCap(){
//		return WordUtils.capitalizeFully(this.positionDesc);
//	}

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	@Column(name = "POS_REPORT_TO", length = 8)

	public String getPosReportTo() {
		return this.posReportTo;
	}

	public void setPosReportTo(String posReportTo) {
		this.posReportTo = posReportTo;
	}

	@Column(name = "POS_REPORT_TO_DESC", length = 30)

	public String getPosReportToDesc() {
		return this.posReportToDesc;
	}

	public void setPosReportToDesc(String posReportToDesc) {
		this.posReportToDesc = posReportToDesc;
	}

	@Column(name = "NAME_REPORT_TO", length = 40)

	public String getNameReportTo() {
		return this.nameReportTo;
	}

	public void setNameReportTo(String nameReportTo) {
		this.nameReportTo = nameReportTo;
	}

	@Column(name = "EMAIL", length = 70)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EMPLID_REPORT_TO", length = 11)

	public String getEmplidReportTo() {
		return this.emplidReportTo;
	}

	public void setEmplidReportTo(String emplidReportTo) {
		this.emplidReportTo = emplidReportTo;
	}

	@Column(name = "EMP_STATUS", length = 1)

	public String getEmpStatus() {
		return this.empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	@Column(name = "EMP_STATUS_DESC", length = 30)

	public String getEmpStatusDesc() {
		return this.empStatusDesc;
	}

	public void setEmpStatusDesc(String empStatusDesc) {
		this.empStatusDesc = empStatusDesc;
	}

	@Column(name = "COST_CENTER", length = 10)

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

}