package com.badaklng.app.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.badaklng.app.base.BaseModel;

/**
 * AppRoleMenuId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class AppRoleMenuId extends BaseModel implements java.io.Serializable {

	// Fields

	private String roleId;
	private Integer menuId;

	// Constructors

	/** default constructor */
	public AppRoleMenuId() {
	}

	/** full constructor */
	public AppRoleMenuId(String roleId, Integer menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	// Property accessors

	@Column(name = "ROLE_ID", nullable = false, length = 16)

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MENU_ID", nullable = false, precision = 0)

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppRoleMenuId))
			return false;
		AppRoleMenuId castOther = (AppRoleMenuId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this.getMenuId() != null
						&& castOther.getMenuId() != null && this.getMenuId().equals(castOther.getMenuId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result + (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		return result;
	}

}