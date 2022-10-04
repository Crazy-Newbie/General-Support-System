package com.badaklng.app.hibernate;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.model.MenuForm;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AppMenu entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APP_MENU", uniqueConstraints = @UniqueConstraint(columnNames = "FORM_ID"))

public class AppMenu extends BaseModel implements java.io.Serializable {

	// Fields

	private Integer menuId;
//	private AppMenu appMenu;
	private String menuName;
	private Integer menuOrder;
	private String menuUrl;
	private Integer menuLvl;
	private String formId;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private Boolean isMenu;
	private String formName;
	private Integer menuParent;
	//private Set<AppRoleMenu> appRoleMenus = new HashSet<AppRoleMenu>(0);
	//private Set<AppMenu> appMenus = new HashSet<AppMenu>(0);

	// addition for aesthetic
	private String icon;

	// Constructors

	/** default constructor */
	public AppMenu() {
	}

	public AppMenu(MenuForm form, AppMenu newParent, Integer id) {
		this.menuId = id;
		if (form.getMenuOrder() == null)
			menuOrder = 99;
		else
			menuOrder = form.getMenuOrder();
		menuLvl = newParent.getMenuLvl() + 1;
		menuName = form.getMenuName();
		menuParent = newParent.menuId;
		menuUrl = form.getMenuUrl();
		isMenu = form.getIsMenu();
		formId = form.getFormId();
		formName = form.getFormName();
		icon = form.getIcon();
	}

	// /** minimal constructor */
	// public AppMenu(Integer menuId, AppMenu appMenu, String menuName, Integer
	// menuOrder, String menuUrl, Integer menuLvl,
	// String createBy, Timestamp createDate, Boolean isMenu) {
	// this.menuId = menuId;
	// this.appMenu = appMenu;
	// this.menuName = menuName;
	// this.menuOrder = menuOrder;
	// this.menuUrl = menuUrl;
	// this.menuLvl = menuLvl;
	// this.createBy = createBy;
	// this.createDate = createDate;
	// this.isMenu = isMenu;
	// }
	//
	// /** full constructor */
	// public AppMenu(Integer menuId, AppMenu appMenu, String menuName, Integer
	// menuOrder, String menuUrl, Integer menuLvl,
	// String formId, String createBy, Timestamp createDate, String updateBy,
	// Timestamp updateDate, Boolean isMenu,
	// String formName, Set<AppRoleMenu> appRoleMenus, Set<AppMenu> appMenus) {
	// this.menuId = menuId;
	// this.appMenu = appMenu;
	// this.menuName = menuName;
	// this.menuOrder = menuOrder;
	// this.menuUrl = menuUrl;
	// this.menuLvl = menuLvl;
	// this.formId = formId;
	// this.createBy = createBy;
	// this.createDate = createDate;
	// this.updateBy = updateBy;
	// this.updateDate = updateDate;
	// this.isMenu = isMenu;
	// this.formName = formName;
	// this.appRoleMenus = appRoleMenus;
	// this.appMenus = appMenus;
	// }

	// Property accessors
	@Id

	@Column(name = "MENU_ID", unique = true, nullable = false, precision = 0)

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Column(name = "MENU_PARENT", nullable = true)
	public Integer getMenuParent() {
		return this.menuParent;
	}

	public void setMenuParent(Integer menuParent) {
		this.menuParent = menuParent;
	}

//	@Transient
//	public void setMenuParent(AppMenu appMenu) {
//		this.appMenu = appMenu;
//	}

	@Column(name = "MENU_NAME", nullable = false, length = 64)

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

	@Column(name = "MENU_ORDER", nullable = false, precision = 0)

	public Integer getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Column(name = "MENU_URL", nullable = false, length = 128)

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "MENU_LVL", nullable = false, precision = 0)

	public Integer getMenuLvl() {
		return this.menuLvl;
	}

	public void setMenuLvl(Integer menuLvl) {
		this.menuLvl = menuLvl;
	}

	@Column(name = "FORM_ID", unique = true, length = 32)

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
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

	@Column(name = "IS_MENU", nullable = false, precision = 1, scale = 0)

	public Boolean getIsMenu() {
		return this.isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	@Column(name = "FORM_NAME", length = 64)

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	@Column(name = "ICON")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appMenu")
//
//	public Set<AppRoleMenu> getAppRoleMenus() {
//		return this.appRoleMenus;
//	}
//
//	public void setAppRoleMenus(Set<AppRoleMenu> appRoleMenus) {
//		this.appRoleMenus = appRoleMenus;
//	}
//
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "appMenu")
//	@OrderBy(value = "menuOrder")
//	public Set<AppMenu> getAppMenus() {
//		return this.appMenus;
//	}
//
//	public void setAppMenus(Set<AppMenu> appMenus) {
//		this.appMenus = appMenus;
//	}
	

	public void setMenu(MenuForm form, AppMenu newParent) {
		if (form.getMenuOrder() == null)
			menuOrder = 99;
		else
			menuOrder = form.getMenuOrder();
		menuLvl = newParent.getMenuLvl() + 1;
		menuName = form.getMenuName();
		menuParent = newParent.menuId;
		menuUrl = form.getMenuUrl();
		isMenu = form.getIsMenu();
		formId = form.getFormId();
		formName = form.getFormName();
		icon = form.getIcon();
	}

	

}