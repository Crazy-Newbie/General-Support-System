package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.hibernate.AppGroup;

public class GroupForm extends BaseForm {

	private static final String FORM_CODE = "GROUP_FORM";

	private String groupId;
	private String groupName;

	// addition for member of group
	private String userId;

	public GroupForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.CREATE);
	}

	public void setGroup(AppGroup grp) {
		groupId = grp.getGroupId();
		groupName = grp.getName();
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
