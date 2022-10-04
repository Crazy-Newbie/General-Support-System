package com.badaklng.app.model;

import java.util.ArrayList;
import java.util.List;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.hibernate.AppRole;
import com.badaklng.app.hibernate.AppRoleMenu;
import com.badaklng.app.hibernate.AppRoleMenuId;
import com.badaklng.app.hibernate.AppRoleMenuView;

public class RoleForm extends BaseForm {

	private static final String FORM_CODE = "ROLE_FORM";

	private String roleId;
	private String roleName;
	private List<AppRoleMenu> appRoleMenu;

	public RoleForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.CREATE);
		roleId = "";
		roleName = "";
		appRoleMenu = new ArrayList<AppRoleMenu>();
	}
	
	public void setRole(AppRole role) {
		this.roleId = role.getRoleId();
		this.roleName = role.getRoleDesc();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<AppRoleMenu> getAppRoleMenu() {
		return appRoleMenu;
	}

	public void setAppRoleMenu(List<AppRoleMenu> appRoleMenu) {
		this.appRoleMenu = appRoleMenu;
	}

	public void setAppRoleMenuView(List<AppRoleMenuView> view) {
		for (AppRoleMenuView arm : view) {
			appRoleMenu.add(new AppRoleMenu(new AppRoleMenuId(arm.getId().getRoleId(), arm.getId().getMenuId()), null,
					null, arm.getIsShowed(), arm.getAccessCode()));
		}
	}



}
