package com.badaklng.app.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppPositionListView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_POSITION_LIST_VIEW")

public class AppPositionListView extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 7006694262061507506L;

	// Fields
	private Long no;
	private String positionId;
	private String posTitle;
	private String superiorId;
	private String employeeId;
	private String surname;
	private String birthPlace;
	private String birthDate;
	private String department;
	private String section;
	private String hireDt;
	private String servDt;
	private String profServDt;
	private String isassistant;
	private String picture;
	private String empGradeCat;
	private String employeeIdZp;
	private String emplid;
	private String supid;
	private String posid;
	private String status;

	// Constructors

	/** default constructor */
	public AppPositionListView() {
	}

	/** minimal constructor */
	public AppPositionListView(Long no) {
		this.no = no;
	}

	/** full constructor */
	public AppPositionListView(String positionId, String posTitle, String superiorId, String employeeId, String surname,
			String birthPlace, String birthDate, String department, String section, String hireDt, String servDt,
			String profServDt, String isassistant, String picture, String empGradeCat, String employeeIdZp,
			String emplid, String supid, String posid) {
		this.positionId = positionId;
		this.posTitle = posTitle;
		this.superiorId = superiorId;
		this.employeeId = employeeId;
		this.surname = surname;
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.department = department;
		this.section = section;
		this.hireDt = hireDt;
		this.servDt = servDt;
		this.profServDt = profServDt;
		this.isassistant = isassistant;
		this.picture = picture;
		this.empGradeCat = empGradeCat;
		this.employeeIdZp = employeeIdZp;
		this.emplid = emplid;
		this.supid = supid;
		this.posid = posid;
	}

	// Property accessors
	@Id
	@Column(name = "NO", nullable = false, precision = 0)

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	@Column(name = "POSITION_ID", nullable = false, length = 10)

	public String getPositionId() {
		return this.positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	@Column(name = "POS_TITLE", length = 40)

	public String getPosTitle() {
		return this.posTitle;
	}

	public void setPosTitle(String posTitle) {
		this.posTitle = posTitle;
	}

	@Column(name = "SUPERIOR_ID", length = 10)

	public String getSuperiorId() {
		return this.superiorId;
	}

	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}

	@Column(name = "EMPLOYEE_ID", length = 10)

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "SURNAME", length = 30)

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "BIRTH_PLACE", length = 50)

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Column(name = "BIRTH_DATE", length = 8)

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "DEPARTMENT", length = 50)

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "SECTION", length = 50)

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Column(name = "HIRE_DT", length = 8)

	public String getHireDt() {
		return this.hireDt;
	}

	public void setHireDt(String hireDt) {
		this.hireDt = hireDt;
	}

	@Column(name = "SERV_DT", length = 8)

	public String getServDt() {
		return this.servDt;
	}

	public void setServDt(String servDt) {
		this.servDt = servDt;
	}

	@Column(name = "PROF_SERV_DT", length = 8)

	public String getProfServDt() {
		return this.profServDt;
	}

	public void setProfServDt(String profServDt) {
		this.profServDt = profServDt;
	}

	@Column(name = "ISASSISTANT", length = 1)

	public String getIsassistant() {
		return this.isassistant;
	}

	public void setIsassistant(String isassistant) {
		this.isassistant = isassistant;
	}

	@Column(name = "PICTURE", length = 42)

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "EMP_GRADE_CAT", length = 4)

	public String getEmpGradeCat() {
		return this.empGradeCat;
	}

	public void setEmpGradeCat(String empGradeCat) {
		this.empGradeCat = empGradeCat;
	}

	@Column(name = "EMPLOYEE_ID_ZP", length = 10)

	public String getEmployeeIdZp() {
		return this.employeeIdZp;
	}

	public void setEmployeeIdZp(String employeeIdZp) {
		this.employeeIdZp = employeeIdZp;
	}

	@Column(name = "EMPLID", length = 10)

	public String getEmplid() {
		return this.emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	@Column(name = "SUPID", length = 10)

	public String getSupid() {
		return this.supid;
	}

	public void setSupid(String supid) {
		this.supid = supid;
	}

	@Column(name = "POSID", length = 10)

	public String getPosid() {
		return this.posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	@Column(name = "STATUS", length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}