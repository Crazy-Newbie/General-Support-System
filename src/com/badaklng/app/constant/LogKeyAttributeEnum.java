package com.badaklng.app.constant;

public enum LogKeyAttributeEnum {
	ROLE("RoleID"), MENU("MenuID"), FORM("Form"), PERMISSION("Permission"), ID("ID"), VALUE("Value"), REF_NO("RefNo"),
	DATE("Date"), START_DATE("StartDate"), END_DATE("EndDate"), USER_ID("UserID"), POS_ID("PosID"), EMP_ID("EmpID"),
	DEPT_ID("DeptID"), SECTION_ID("SectionID"), ATTACH_ID("AttachID"), GROUP_ID("GroupID"), USERNAME("Username"),
	WHAT("What"), JOB_NAME("JobName"), NOMINAL("Nominal"), PARENT_ID("ParentID"), SEQUENCE("Seq"), WHO("Who"),
	TITLE("Title"), DESC("Description"), LDAPUSER("LDAPUser"), MANUFACTURER("Manufacturer"), TOTAL("Total"),
	IP_ADDRESS("IP Address"), PIC_ID("PIC ID"), APPL_ID("Appl. ID"), PERIOD("Period"), PRIMARY("Prim. Comp."),
	SECONDARY("Sec. Comp."), LOC("Location"), YEAR("Year"), SEMESTER("Semester"), STATUS("Status"), TEAMNAME("TeamName");

	private String value;

	LogKeyAttributeEnum(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
