package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class GroupListForm extends BaseForm {

	private static final String FORM_CODE = "GROUP_LIST_FORM";
	private String groupId;
	private String groupName;

	public GroupListForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
