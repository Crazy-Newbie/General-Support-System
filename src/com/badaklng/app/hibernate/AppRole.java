package com.badaklng.app.hibernate;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AppRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_ROLE")

public class AppRole extends BaseModel implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleDesc;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
//	private Set<AppUser> appUsers = new HashSet<AppUser>(0);
	private Set<AppRoleMenu> appRoleMenus = new HashSet<AppRoleMenu>(0);

	// Constructors

	/** default constructor */
	public AppRole() {
	}

	/** minimal constructor */
	public AppRole(String roleId, String roleDesc, String createBy, Timestamp createDate) {
		this.roleId = roleId;
		this.roleDesc = roleDesc;
		this.createBy = createBy;
		this.createDate = createDate;
	}

	// Property accessors
	@Id

	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 16)

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_DESC", nullable = false, length = 32)

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "CREATE_BY", nullable = false, length = 6)

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATE_DATE", nullable = false, length = 7)

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY", length = 6)

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "UPDATE_DATE", length = 7)

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appRole")
//	public Set<AppUser> getAppUsers() {
//		return this.appUsers;
//	}
//
//	public void setAppUsers(Set<AppUser> appUsers) {
//		this.appUsers = appUsers;
//	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appRole")

	public Set<AppRoleMenu> getAppRoleMenus() {
		return this.appRoleMenus;
	}

	public void setAppRoleMenus(Set<AppRoleMenu> appRoleMenus) {
		this.appRoleMenus = appRoleMenus;
	}

}