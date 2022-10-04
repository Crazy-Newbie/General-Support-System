package com.badaklng.app.hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.badaklng.app.base.BaseModel;

/**
 * AppRoleMenuView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_ROLE_MENU_VIEW")

public class AppRoleMenuView extends BaseModel implements java.io.Serializable {

	// Fields

	private AppRoleMenuViewId id;
	private String menuName;
	private Integer menuLvl;
	private String formId;
	private String formName;
	private Boolean isMenu;
	private Boolean isShowed;
	private Integer accessCode;
	private Integer menuOrder;
	private Integer menuParent;

	// Constructors

	/** default constructor */
	public AppRoleMenuView() {
	}

	/** minimal constructor */
	public AppRoleMenuView(AppRoleMenuViewId id) {
		this.id = id;
	}

	/** full constructor */
	public AppRoleMenuView(AppRoleMenuViewId id, String menuName, Integer menuLvl, String formId, String formName,
			Boolean isMenu, Boolean isShowed, Integer accessCode) {
		this.id = id;
		this.menuName = menuName;
		this.menuLvl = menuLvl;
		this.formId = formId;
		this.formName = formName;
		this.isMenu = isMenu;
		this.isShowed = isShowed;
		this.accessCode = accessCode;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", length = 16) ),
			@AttributeOverride(name = "menuId", column = @Column(name = "MENU_ID", precision = 0) ) })

	public AppRoleMenuViewId getId() {
		return this.id;
	}

	public void setId(AppRoleMenuViewId id) {
		this.id = id;
	}

	@Column(name = "MENU_NAME", length = 64)

	public String getMenuName() {
		return this.menuName;
	}

	@Transient
	public String getMenuNamePadding() {
		String ret = menuName;
		for (int i = 0; i < menuLvl; i++)
			ret = "---|" + ret;
		return ret;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_LVL", precision = 0)

	public Integer getMenuLvl() {
		return this.menuLvl;
	}

	public void setMenuLvl(Integer menuLvl) {
		this.menuLvl = menuLvl;
	}

	@Column(name = "FORM_ID", length = 32)

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	@Column(name = "FORM_NAME", length = 64)

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	@Column(name = "IS_MENU", precision = 1, scale = 0)

	public Boolean getIsMenu() {
		return this.isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	@Column(name = "IS_SHOWED", precision = 0)

	public Boolean getIsShowed() {
		return this.isShowed;
	}

	public void setIsShowed(Boolean isShowed) {
		this.isShowed = isShowed;
	}

	@Column(name = "ACCESS_CODE", precision = 0)

	public Integer getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(Integer accessCode) {
		this.accessCode = accessCode;
	}

	@Column(name = "MENU_ORDER")
	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Column(name = "MENU_PARENT")
	public Integer getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Integer menuParent) {
		this.menuParent = menuParent;
	}
	
	

}