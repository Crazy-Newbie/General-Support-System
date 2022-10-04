package com.badaklng.app.hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_ROLE_MENU")

public class AppRoleMenu extends BaseModel implements java.io.Serializable {

	// Fields

	private AppRoleMenuId id;
	private AppRole appRole;
//private AppMenu appMenu;
//	private Integer menuId;
	private Boolean isShowed;
	private Integer accessCode;

	// Constructors

	/** default constructor */
	public AppRoleMenu() {
	}

	/** full constructor */
	public AppRoleMenu(AppRoleMenuId id, AppRole appRole, Integer appMenu, Boolean isShowed, Integer accessCode) {
		this.id = id;
		this.appRole = appRole;
//		this.id.setMenuId(appMenu); = appMenu;
		this.isShowed = isShowed;
		this.accessCode = accessCode;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, length = 16) ),
			@AttributeOverride(name = "menuId", column = @Column(name = "MENU_ID", nullable = false, precision = 0) ) })

	public AppRoleMenuId getId() {
		return this.id;
	}

	public void setId(AppRoleMenuId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)

	public AppRole getAppRole() {
		return this.appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "MENU_ID", nullable = false, insertable = false, updatable = false)

//	public AppMenu getAppMenu() {
//		return this.appMenu;
//	}
//
//	public void setAppMenu(AppMenu appMenu) {
//		this.appMenu = appMenu;
//	}
	
	

	@Column(name = "IS_SHOWED", nullable = false, precision = 1, scale = 0)

	public Boolean getIsShowed() {
		return this.isShowed;
	}
	
//	@Column(name = "MENU_ID")
//	public Integer getMenuId() {
//		return menuId;
//	}
//
//	public void setMenuId(Integer menuId) {
//		this.menuId = menuId;
//	}

	public void setIsShowed(Boolean isShowed) {
		this.isShowed = isShowed;
	}

	@Column(name = "ACCESS_CODE", nullable = false, precision = 0)

	public Integer getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(Integer accessCode) {
		this.accessCode = accessCode;
	}

}