package com.badaklng.app.model;

public enum MessageEnum {
	BDK000("Undefined message"), 
	BDK001("User cannot be empty"), 
	BDK002("Password cannot be empty"),
	BDK003("LDAP Account must be filled when <strong>is LDAP</strong> field set to Yes"),
	BDK004("When <strong>is LDAP</strong> field set to No, name and password must be filled"),
	BDK005("User ID cannot be empty"),
	BDK006("Group ID cannot be empty"),
	BDK007("Group Name cannot be empty"),
	BDK008("Menu ID cannot be empty"),
	BDK009("Menu Name cannot be empty"),
	BDK010("From/to Date cannot be empty"),
	BDK011("Cost cannot be empty and must be numeric"),
	BDK012("Project Number cannot be empty"),
	BDK013("Title/Description cannot be empty"),
	BDK014("<b>Cost code</b> must be filled when <b>Project Info</b> equals to AFE"),
	BDK015("Cost code must be in A-<i><b>XXXXX</b></i>-<i><b>XXXXX</b></i>-<i><b>XX</b></i>-<i><b>XXXXX</b></i>-00 format"),
	BDK016("Follow Up by cannot be empty"),
	BDK017("Verified by cannot be empty"),
	BDK018("Target Date cannot be empty"),
	BDK019("File cannot be empty"),
	BDK020("File size cannot exceed 50 MBytes"), 
	BDK048("Cannot submit for completion while rater does not reviewed all")
	;

	private final String description;

	MessageEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
