package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppEmpInfoView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_EMP_INFO_VIEW")

public class AppEmpInfoView extends BaseModel implements java.io.Serializable {

	// Fields

	private String emplid;
	private String employeeIdZp;
	private String ptmId;
	private String nameDisplay;
	private String deptid;
	private String deptDesc;
	private String pbSection;
	private String sectionDesc;
	private String positionNbr;
	private String positionDesc;
	private Timestamp positionEntryDt;
	private String posReportTo;
	private String posReportToDesc;
	private String nameReportTo;
	private String salGrade;
	private String salGradeAlpha;
	private Timestamp salGradeDt;
	private String pbPtmSalGrade;
	private String jobcode;
	private String jobGrade;
	private Timestamp jobGradeDt;
	private Timestamp hireDt;
	private Timestamp serviceDt;
	private Timestamp profesionalExperienceDt;
	private Timestamp hireDtPanjarPap;
	private Timestamp hireDtPap;
	private Timestamp birthdate;
	private String birthCityCd;
	private String birthCityDesc;
	private String birthStateCd;
	private String birthStateDesc;
	private Timestamp mppDate;
	private String genderDesc;
	private String payGroup;
	private String taxMaritalSts;
	private Timestamp marStatusDt;
	private String highestEducLvl;
	private String farthestCity;
	private String religionDesc;
	private String pbEthnicGroup;
	private String bloodType;
	private String phoneHome;
	private String phoneOffice;
	private String phoneHp;
	private String perOrg;
	private String pbEmpCategory;
	private String empCategory;
	private String location;
	private String pbSubLocation;
	private String subLoc;
	private String shift;
	private String shiftDesc;
	private String unionCd;
	private String housingType;
	private String address;
	private String addressLong;
	private String city;
	private String state;
	private String postal;
	private String email;
	private Double eki1;
	private String eki1Category;
	private Double eki2;
	private String eki2Category;
	private Double eki3;
	private String eki3Category;
	private Double eki4;
	private String eki4Category;
	private Double eki5;
	private String eki5Category;
	private String spouseNm;
	private String spouseBirthCityCd;
	private String spouseBirthCityDesc;
	private String spouseBirthStateCd;
	private String spouseBirthStateDesc;
	private String highestEducDesc;
	private String jpmProfileId;
	private String education;
	private String npwp;
	private Double pbUpk;
	private Double upokps;
	private String passportNo;
	private Timestamp passportExp;
	private String passportCity;
	private String emplidReportTo;
	private String educCd;
	private String educShort;
	private String educJurusan;
	private String educName;
	private String educCity;
	private Timestamp effdtJobData;
	private String action;
	private String actionDesc;
	private String reason;
	private String reasonDesc;
	private String empStatus;
	private String empStatusDesc;
	private String payrollStatus;
	private String costCenter;
	private String rosterId;
	private String stateDesc;
	private String addressCountryCd;
	private String jobFamily;
	private String jobFamDesc;
	//private Double masaKerja;

	// Constructors

	/** default constructor */
	public AppEmpInfoView() {
	}

	/** minimal constructor */
	public AppEmpInfoView(String emplid) {
		this.emplid = emplid;
	}

	/** full constructor */
	public AppEmpInfoView(String emplid, String employeeIdZp, String ptmId, String nameDisplay, String deptid,
			String deptDesc, String pbSection, String sectionDesc, String positionNbr, String positionDesc,
			Timestamp positionEntryDt, String posReportTo, String posReportToDesc, String nameReportTo, String salGrade,
			String salGradeAlpha, Timestamp salGradeDt, String pbPtmSalGrade, String jobcode, String jobGrade,
			Timestamp jobGradeDt, Timestamp hireDt, Timestamp serviceDt, Timestamp profesionalExperienceDt,
			Timestamp hireDtPanjarPap, Timestamp hireDtPap, Timestamp birthdate, String birthCityCd,
			String birthCityDesc, String birthStateCd, String birthStateDesc, Timestamp mppDate, String genderDesc,
			String payGroup, String taxMaritalSts, Timestamp marStatusDt, String highestEducLvl, String farthestCity,
			String religionDesc, String pbEthnicGroup, String bloodType, String phoneHome, String phoneOffice,
			String phoneHp, String perOrg, String pbEmpCategory, String empCategory, String location,
			String pbSubLocation, String subLoc, String shift, String shiftDesc, String unionCd, String housingType,
			String address, String addressLong, String city, String state, String postal, String email, Double eki1,
			String eki1Category, Double eki2, String eki2Category, Double eki3, String eki3Category, Double eki4,
			String eki4Category, Double eki5, String eki5Category, String spouseNm, String spouseBirthCityCd,
			String spouseBirthCityDesc, String spouseBirthStateCd, String spouseBirthStateDesc, String highestEducDesc,
			String jpmProfileId, String education, String npwp, Double pbUpk, Double upokps, String passportNo,
			Timestamp passportExp, String passportCity, String emplidReportTo, String educCd, String educShort,
			String educJurusan, String educName, String educCity, Timestamp effdtJobData, String action,
			String actionDesc, String reason, String reasonDesc, String empStatus, String empStatusDesc,
			String payrollStatus, String costCenter, String rosterId, String stateDesc, String addressCountryCd, String jobFamily, String jobFamDesc) {
		this.emplid = emplid;
		this.employeeIdZp = employeeIdZp;
		this.ptmId = ptmId;
		this.nameDisplay = nameDisplay;
		this.deptid = deptid;
		this.deptDesc = deptDesc;
		this.pbSection = pbSection;
		this.sectionDesc = sectionDesc;
		this.positionNbr = positionNbr;
		this.positionDesc = positionDesc;
		this.positionEntryDt = positionEntryDt;
		this.posReportTo = posReportTo;
		this.posReportToDesc = posReportToDesc;
		this.nameReportTo = nameReportTo;
		this.salGrade = salGrade;
		this.salGradeAlpha = salGradeAlpha;
		this.salGradeDt = salGradeDt;
		this.pbPtmSalGrade = pbPtmSalGrade;
		this.jobcode = jobcode;
		this.jobGrade = jobGrade;
		this.jobGradeDt = jobGradeDt;
		this.hireDt = hireDt;
		this.serviceDt = serviceDt;
		this.profesionalExperienceDt = profesionalExperienceDt;
		this.hireDtPanjarPap = hireDtPanjarPap;
		this.hireDtPap = hireDtPap;
		this.birthdate = birthdate;
		this.birthCityCd = birthCityCd;
		this.birthCityDesc = birthCityDesc;
		this.birthStateCd = birthStateCd;
		this.birthStateDesc = birthStateDesc;
		this.mppDate = mppDate;
		this.genderDesc = genderDesc;
		this.payGroup = payGroup;
		this.taxMaritalSts = taxMaritalSts;
		this.marStatusDt = marStatusDt;
		this.highestEducLvl = highestEducLvl;
		this.farthestCity = farthestCity;
		this.religionDesc = religionDesc;
		this.pbEthnicGroup = pbEthnicGroup;
		this.bloodType = bloodType;
		this.phoneHome = phoneHome;
		this.phoneOffice = phoneOffice;
		this.phoneHp = phoneHp;
		this.perOrg = perOrg;
		this.pbEmpCategory = pbEmpCategory;
		this.empCategory = empCategory;
		this.location = location;
		this.pbSubLocation = pbSubLocation;
		this.subLoc = subLoc;
		this.shift = shift;
		this.shiftDesc = shiftDesc;
		this.unionCd = unionCd;
		this.housingType = housingType;
		this.address = address;
		this.addressLong = addressLong;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.email = email;
		this.eki1 = eki1;
		this.eki1Category = eki1Category;
		this.eki2 = eki2;
		this.eki2Category = eki2Category;
		this.eki3 = eki3;
		this.eki3Category = eki3Category;
		this.eki4 = eki4;
		this.eki4Category = eki4Category;
		this.eki5 = eki5;
		this.eki5Category = eki5Category;
		this.spouseNm = spouseNm;
		this.spouseBirthCityCd = spouseBirthCityCd;
		this.spouseBirthCityDesc = spouseBirthCityDesc;
		this.spouseBirthStateCd = spouseBirthStateCd;
		this.spouseBirthStateDesc = spouseBirthStateDesc;
		this.highestEducDesc = highestEducDesc;
		this.jpmProfileId = jpmProfileId;
		this.education = education;
		this.npwp = npwp;
		this.pbUpk = pbUpk;
		this.upokps = upokps;
		this.passportNo = passportNo;
		this.passportExp = passportExp;
		this.passportCity = passportCity;
		this.emplidReportTo = emplidReportTo;
		this.educCd = educCd;
		this.educShort = educShort;
		this.educJurusan = educJurusan;
		this.educName = educName;
		this.educCity = educCity;
		this.effdtJobData = effdtJobData;
		this.action = action;
		this.actionDesc = actionDesc;
		this.reason = reason;
		this.reasonDesc = reasonDesc;
		this.empStatus = empStatus;
		this.empStatusDesc = empStatusDesc;
		this.payrollStatus = payrollStatus;
		this.costCenter = costCenter;
		this.rosterId = rosterId;
		this.stateDesc = stateDesc;
		this.addressCountryCd = addressCountryCd;
		this.jobFamily = jobFamily;
		this.jobFamDesc = jobFamDesc;
		//this.masaKerja = masaKerja;
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

	@Column(name = "EMPLOYEE_ID_ZP", length = 10)

	public String getEmployeeIdZp() {
		return this.employeeIdZp;
	}

	public void setEmployeeIdZp(String employeeIdZp) {
		this.employeeIdZp = employeeIdZp;
	}

	@Column(name = "PTM_ID", length = 11)

	public String getPtmId() {
		return this.ptmId;
	}

	public void setPtmId(String ptmId) {
		this.ptmId = ptmId;
	}

	@Column(name = "NAME_DISPLAY", length = 50)

	public String getNameDisplay() {
		return this.nameDisplay;
	}

	public void setNameDisplay(String nameDisplay) {
		this.nameDisplay = nameDisplay;
	}

	@Column(name = "DEPTID", length = 10)

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	@Column(name = "DEPT_DESC", length = 30)

	public String getDeptDesc() {
		return this.deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	@Column(name = "PB_SECTION", length = 10)

	public String getPbSection() {
		return this.pbSection;
	}

	public void setPbSection(String pbSection) {
		this.pbSection = pbSection;
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

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	@Column(name = "POSITION_ENTRY_DT", length = 7)

	public Timestamp getPositionEntryDt() {
		return this.positionEntryDt;
	}

	public void setPositionEntryDt(Timestamp positionEntryDt) {
		this.positionEntryDt = positionEntryDt;
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

	@Column(name = "SAL_GRADE", length = 3)

	public String getSalGrade() {
		return this.salGrade;
	}

	public void setSalGrade(String salGrade) {
		this.salGrade = salGrade;
	}

	@Column(name = "SAL_GRADE_ALPHA", length = 4)

	public String getSalGradeAlpha() {
		return this.salGradeAlpha;
	}

	public void setSalGradeAlpha(String salGradeAlpha) {
		this.salGradeAlpha = salGradeAlpha;
	}

	@Column(name = "SAL_GRADE_DT", length = 7)

	public Timestamp getSalGradeDt() {
		return this.salGradeDt;
	}

	public void setSalGradeDt(Timestamp salGradeDt) {
		this.salGradeDt = salGradeDt;
	}

	@Column(name = "PB_PTM_SAL_GRADE", length = 3)

	public String getPbPtmSalGrade() {
		return this.pbPtmSalGrade;
	}

	public void setPbPtmSalGrade(String pbPtmSalGrade) {
		this.pbPtmSalGrade = pbPtmSalGrade;
	}

	@Column(name = "JOBCODE", length = 6)

	public String getJobcode() {
		return this.jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

	@Column(name = "JOB_GRADE", length = 10)

	public String getJobGrade() {
		return this.jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	@Column(name = "JOB_GRADE_DT", length = 7)

	public Timestamp getJobGradeDt() {
		return this.jobGradeDt;
	}

	public void setJobGradeDt(Timestamp jobGradeDt) {
		this.jobGradeDt = jobGradeDt;
	}

	@Column(name = "HIRE_DT", length = 7)

	public Timestamp getHireDt() {
		return this.hireDt;
	}

	public void setHireDt(Timestamp hireDt) {
		this.hireDt = hireDt;
	}

	@Column(name = "SERVICE_DT", length = 7)

	public Timestamp getServiceDt() {
		return this.serviceDt;
	}

	public void setServiceDt(Timestamp serviceDt) {
		this.serviceDt = serviceDt;
	}

	@Column(name = "PROFESIONAL_EXPERIENCE_DT", length = 7)

	public Timestamp getProfesionalExperienceDt() {
		return this.profesionalExperienceDt;
	}

	public void setProfesionalExperienceDt(Timestamp profesionalExperienceDt) {
		this.profesionalExperienceDt = profesionalExperienceDt;
	}

	@Column(name = "HIRE_DT_PANJAR_PAP", length = 7)

	public Timestamp getHireDtPanjarPap() {
		return this.hireDtPanjarPap;
	}

	public void setHireDtPanjarPap(Timestamp hireDtPanjarPap) {
		this.hireDtPanjarPap = hireDtPanjarPap;
	}

	@Column(name = "HIRE_DT_PAP", length = 7)

	public Timestamp getHireDtPap() {
		return this.hireDtPap;
	}

	public void setHireDtPap(Timestamp hireDtPap) {
		this.hireDtPap = hireDtPap;
	}

	@Column(name = "BIRTHDATE", length = 7)

	public Timestamp getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "BIRTH_CITY_CD", length = 30)

	public String getBirthCityCd() {
		return this.birthCityCd;
	}

	public void setBirthCityCd(String birthCityCd) {
		this.birthCityCd = birthCityCd;
	}

	@Column(name = "BIRTH_CITY_DESC", length = 30)

	public String getBirthCityDesc() {
		return this.birthCityDesc;
	}

	public void setBirthCityDesc(String birthCityDesc) {
		this.birthCityDesc = birthCityDesc;
	}

	@Column(name = "BIRTH_STATE_CD", length = 6)

	public String getBirthStateCd() {
		return this.birthStateCd;
	}

	public void setBirthStateCd(String birthStateCd) {
		this.birthStateCd = birthStateCd;
	}

	@Column(name = "BIRTH_STATE_DESC", length = 30)

	public String getBirthStateDesc() {
		return this.birthStateDesc;
	}

	public void setBirthStateDesc(String birthStateDesc) {
		this.birthStateDesc = birthStateDesc;
	}

	@Column(name = "MPP_DATE", length = 7)

	public Timestamp getMppDate() {
		return this.mppDate;
	}

	public void setMppDate(Timestamp mppDate) {
		this.mppDate = mppDate;
	}

	@Column(name = "GENDER_DESC", length = 30)

	public String getGenderDesc() {
		return this.genderDesc;
	}

	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}

	@Column(name = "PAY_GROUP", length = 10)

	public String getPayGroup() {
		return this.payGroup;
	}

	public void setPayGroup(String payGroup) {
		this.payGroup = payGroup;
	}

	@Column(name = "TAX_MARITAL_STS", length = 4)

	public String getTaxMaritalSts() {
		return this.taxMaritalSts;
	}

	public void setTaxMaritalSts(String taxMaritalSts) {
		this.taxMaritalSts = taxMaritalSts;
	}

	@Column(name = "MAR_STATUS_DT", length = 7)

	public Timestamp getMarStatusDt() {
		return this.marStatusDt;
	}

	public void setMarStatusDt(Timestamp marStatusDt) {
		this.marStatusDt = marStatusDt;
	}

	@Column(name = "HIGHEST_EDUC_LVL", length = 10)

	public String getHighestEducLvl() {
		return this.highestEducLvl;
	}

	public void setHighestEducLvl(String highestEducLvl) {
		this.highestEducLvl = highestEducLvl;
	}

	@Column(name = "FARTHEST_CITY", length = 50)

	public String getFarthestCity() {
		return this.farthestCity;
	}

	public void setFarthestCity(String farthestCity) {
		this.farthestCity = farthestCity;
	}

	@Column(name = "RELIGION_DESC", length = 30)

	public String getReligionDesc() {
		return this.religionDesc;
	}

	public void setReligionDesc(String religionDesc) {
		this.religionDesc = religionDesc;
	}

	@Column(name = "PB_ETHNIC_GROUP", length = 8)

	public String getPbEthnicGroup() {
		return this.pbEthnicGroup;
	}

	public void setPbEthnicGroup(String pbEthnicGroup) {
		this.pbEthnicGroup = pbEthnicGroup;
	}

	@Column(name = "BLOOD_TYPE", length = 3)

	public String getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Column(name = "PHONE_HOME", length = 24)

	public String getPhoneHome() {
		return this.phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	@Column(name = "PHONE_OFFICE", length = 24)

	public String getPhoneOffice() {
		return this.phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	@Column(name = "PHONE_HP", length = 24)

	public String getPhoneHp() {
		return this.phoneHp;
	}

	public void setPhoneHp(String phoneHp) {
		this.phoneHp = phoneHp;
	}

	@Column(name = "PER_ORG", length = 3)

	public String getPerOrg() {
		return this.perOrg;
	}

	public void setPerOrg(String perOrg) {
		this.perOrg = perOrg;
	}

	@Column(name = "PB_EMP_CATEGORY", length = 4)

	public String getPbEmpCategory() {
		return this.pbEmpCategory;
	}

	public void setPbEmpCategory(String pbEmpCategory) {
		this.pbEmpCategory = pbEmpCategory;
	}

	@Column(name = "EMP_CATEGORY", length = 10)

	public String getEmpCategory() {
		return this.empCategory;
	}

	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}

	@Column(name = "LOCATION", length = 10)

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "PB_SUB_LOCATION", length = 4)

	public String getPbSubLocation() {
		return this.pbSubLocation;
	}

	public void setPbSubLocation(String pbSubLocation) {
		this.pbSubLocation = pbSubLocation;
	}

	@Column(name = "SUB_LOC", length = 30)

	public String getSubLoc() {
		return this.subLoc;
	}

	public void setSubLoc(String subLoc) {
		this.subLoc = subLoc;
	}

	@Column(name = "SHIFT", length = 1)

	public String getShift() {
		return this.shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	@Column(name = "SHIFT_DESC", length = 30)

	public String getShiftDesc() {
		return this.shiftDesc;
	}

	public void setShiftDesc(String shiftDesc) {
		this.shiftDesc = shiftDesc;
	}

	@Column(name = "UNION_CD", length = 3)

	public String getUnionCd() {
		return this.unionCd;
	}

	public void setUnionCd(String unionCd) {
		this.unionCd = unionCd;
	}

	@Column(name = "HOUSING_TYPE", length = 27)

	public String getHousingType() {
		return this.housingType;
	}

	public void setHousingType(String housingType) {
		this.housingType = housingType;
	}

	@Column(name = "ADDRESS", length = 55)

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ADDRESS_LONG", length = 223)

	public String getAddressLong() {
		return this.addressLong;
	}

	public void setAddressLong(String addressLong) {
		this.addressLong = addressLong;
	}

	@Column(name = "CITY", length = 30)

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE", length = 6)

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "POSTAL", length = 12)

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	@Column(name = "EMAIL", length = 70)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EKI1", precision = 5)

	public Double getEki1() {
		return this.eki1;
	}

	public void setEki1(Double eki1) {
		this.eki1 = eki1;
	}

	@Column(name = "EKI1_CATEGORY", length = 3)

	public String getEki1Category() {
		return this.eki1Category;
	}

	public void setEki1Category(String eki1Category) {
		this.eki1Category = eki1Category;
	}

	@Column(name = "EKI2", precision = 5)

	public Double getEki2() {
		return this.eki2;
	}

	public void setEki2(Double eki2) {
		this.eki2 = eki2;
	}

	@Column(name = "EKI2_CATEGORY", length = 3)

	public String getEki2Category() {
		return this.eki2Category;
	}

	public void setEki2Category(String eki2Category) {
		this.eki2Category = eki2Category;
	}

	@Column(name = "EKI3", precision = 5)

	public Double getEki3() {
		return this.eki3;
	}

	public void setEki3(Double eki3) {
		this.eki3 = eki3;
	}

	@Column(name = "EKI3_CATEGORY", length = 3)

	public String getEki3Category() {
		return this.eki3Category;
	}

	public void setEki3Category(String eki3Category) {
		this.eki3Category = eki3Category;
	}

	@Column(name = "EKI4", precision = 5)

	public Double getEki4() {
		return this.eki4;
	}

	public void setEki4(Double eki4) {
		this.eki4 = eki4;
	}

	@Column(name = "EKI4_CATEGORY", length = 3)

	public String getEki4Category() {
		return this.eki4Category;
	}

	public void setEki4Category(String eki4Category) {
		this.eki4Category = eki4Category;
	}

	@Column(name = "EKI5", precision = 5)

	public Double getEki5() {
		return this.eki5;
	}

	public void setEki5(Double eki5) {
		this.eki5 = eki5;
	}

	@Column(name = "EKI5_CATEGORY", length = 3)

	public String getEki5Category() {
		return this.eki5Category;
	}

	public void setEki5Category(String eki5Category) {
		this.eki5Category = eki5Category;
	}

	@Column(name = "SPOUSE_NM", length = 50)

	public String getSpouseNm() {
		return this.spouseNm;
	}

	public void setSpouseNm(String spouseNm) {
		this.spouseNm = spouseNm;
	}

	@Column(name = "SPOUSE_BIRTH_CITY_CD", length = 30)

	public String getSpouseBirthCityCd() {
		return this.spouseBirthCityCd;
	}

	public void setSpouseBirthCityCd(String spouseBirthCityCd) {
		this.spouseBirthCityCd = spouseBirthCityCd;
	}

	@Column(name = "SPOUSE_BIRTH_CITY_DESC", length = 30)

	public String getSpouseBirthCityDesc() {
		return this.spouseBirthCityDesc;
	}

	public void setSpouseBirthCityDesc(String spouseBirthCityDesc) {
		this.spouseBirthCityDesc = spouseBirthCityDesc;
	}

	@Column(name = "SPOUSE_BIRTH_STATE_CD", length = 6)

	public String getSpouseBirthStateCd() {
		return this.spouseBirthStateCd;
	}

	public void setSpouseBirthStateCd(String spouseBirthStateCd) {
		this.spouseBirthStateCd = spouseBirthStateCd;
	}

	@Column(name = "SPOUSE_BIRTH_STATE_DESC", length = 30)

	public String getSpouseBirthStateDesc() {
		return this.spouseBirthStateDesc;
	}

	public void setSpouseBirthStateDesc(String spouseBirthStateDesc) {
		this.spouseBirthStateDesc = spouseBirthStateDesc;
	}

	@Column(name = "HIGHEST_EDUC_DESC", length = 8)

	public String getHighestEducDesc() {
		return this.highestEducDesc;
	}

	public void setHighestEducDesc(String highestEducDesc) {
		this.highestEducDesc = highestEducDesc;
	}

	@Column(name = "JPM_PROFILE_ID", length = 12)

	public String getJpmProfileId() {
		return this.jpmProfileId;
	}

	public void setJpmProfileId(String jpmProfileId) {
		this.jpmProfileId = jpmProfileId;
	}

	@Column(name = "EDUCATION", length = 555)

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "NPWP", length = 20)

	public String getNpwp() {
		return this.npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	@Column(name = "PB_UPK", precision = 18)

	public Double getPbUpk() {
		return this.pbUpk;
	}

	public void setPbUpk(Double pbUpk) {
		this.pbUpk = pbUpk;
	}

	@Column(name = "UPOKPS", precision = 18)

	public Double getUpokps() {
		return this.upokps;
	}

	public void setUpokps(Double upokps) {
		this.upokps = upokps;
	}

	@Column(name = "PASSPORT_NO", length = 15)

	public String getPassportNo() {
		return this.passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	@Column(name = "PASSPORT_EXP", length = 7)

	public Timestamp getPassportExp() {
		return this.passportExp;
	}

	public void setPassportExp(Timestamp passportExp) {
		this.passportExp = passportExp;
	}

	@Column(name = "PASSPORT_CITY", length = 20)

	public String getPassportCity() {
		return this.passportCity;
	}

	public void setPassportCity(String passportCity) {
		this.passportCity = passportCity;
	}

	@Column(name = "EMPLID_REPORT_TO", length = 11)

	public String getEmplidReportTo() {
		return this.emplidReportTo;
	}

	public void setEmplidReportTo(String emplidReportTo) {
		this.emplidReportTo = emplidReportTo;
	}

	@Column(name = "EDUC_CD", length = 10)

	public String getEducCd() {
		return this.educCd;
	}

	public void setEducCd(String educCd) {
		this.educCd = educCd;
	}

	@Column(name = "EDUC_SHORT", length = 10)

	public String getEducShort() {
		return this.educShort;
	}

	public void setEducShort(String educShort) {
		this.educShort = educShort;
	}

	@Column(name = "EDUC_JURUSAN", length = 30)

	public String getEducJurusan() {
		return this.educJurusan;
	}

	public void setEducJurusan(String educJurusan) {
		this.educJurusan = educJurusan;
	}

	@Column(name = "EDUC_NAME", length = 254)

	public String getEducName() {
		return this.educName;
	}

	public void setEducName(String educName) {
		this.educName = educName;
	}

	@Column(name = "EDUC_CITY", length = 254)

	public String getEducCity() {
		return this.educCity;
	}

	public void setEducCity(String educCity) {
		this.educCity = educCity;
	}

	@Column(name = "EFFDT_JOB_DATA", length = 7)

	public Timestamp getEffdtJobData() {
		return this.effdtJobData;
	}

	public void setEffdtJobData(Timestamp effdtJobData) {
		this.effdtJobData = effdtJobData;
	}

	@Column(name = "ACTION", length = 3)

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "ACTION_DESC", length = 30)

	public String getActionDesc() {
		return this.actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	@Column(name = "REASON", length = 3)

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "REASON_DESC", length = 30)

	public String getReasonDesc() {
		return this.reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
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

	@Column(name = "PAYROLL_STATUS", length = 30)

	public String getPayrollStatus() {
		return this.payrollStatus;
	}

	public void setPayrollStatus(String payrollStatus) {
		this.payrollStatus = payrollStatus;
	}

	@Column(name = "COST_CENTER", length = 10)

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	@Column(name = "ROSTER_ID", length = 10)

	public String getRosterId() {
		return this.rosterId;
	}

	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}

	@Column(name = "STATE_DESC", length = 30)

	public String getStateDesc() {
		return this.stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	@Column(name = "ADDRESS_COUNTRY_CD", length = 3)

	public String getAddressCountryCd() {
		return this.addressCountryCd;
	}

	public void setAddressCountryCd(String addressCountryCd) {
		this.addressCountryCd = addressCountryCd;
	}
	
	@Column(name = "JOB_FAMILY", length = 6)
	public String getJobFamily() {
		return this.jobFamily;
	}

	public void setJobFamily(String jobFamily) {
		this.jobFamily = jobFamily;
	}
	
	@Column(name = "JOB_FAM_DESC", length = 30)
	public String getJobFamDesc() {
		return this.jobFamDesc;
	}

	public void setJobFamDesc(String jobFamDesc) {
		this.jobFamDesc = jobFamDesc;
	}

	/*@Column(name = "MASA_KERJA", precision = 0, insertable = false, updatable = false)

	public Double getMasaKerja() {
		return this.masaKerja;
	}

	public void setMasaKerja(Double masaKerja) {
		this.masaKerja = masaKerja;
	}*/

}