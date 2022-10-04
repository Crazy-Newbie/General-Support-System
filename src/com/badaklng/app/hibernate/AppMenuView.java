package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.badaklng.app.base.BaseModel;

/**
 * AppMenuView entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APP_MENU_VIEW")

public class AppMenuView extends BaseModel implements java.io.Serializable, Comparable<AppMenuView> {

	// Fields

	private Integer menuId;
	private String menuName;
	private Integer menuOrder;
	private String menuUrl;
	private Integer menuLvl;
	private Integer menuParent;
	// private AppMenuView appMenu;
	// private Set<AppMenuView> appMenus;
	private String formId;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private Boolean isMenu;
	private String formName;

	// aesthetic addition
	private String icon;

	// Constructors

	/** default constructor */
	public AppMenuView() {
	}

	// Property accessors
	@Id

	@Column(name = "MENU_ID", nullable = false, precision = 0)

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Column(name = "MENU_NAME", nullable = false, length = 64)

	public String getMenuName() {
		return this.menuName;
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

	@Column(name = "MENU_PARENT", precision = 0, insertable = false, updatable = false)

	public Integer getMenuParent() {
		return this.menuParent;
	}

	public void setMenuParent(Integer menuParent) {
		this.menuParent = menuParent;
	}

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "MENU_PARENT", nullable = true)
	// public AppMenuView getAppMenu() {
	// return this.appMenu;
	// }
	//
	// public void setAppMenu(AppMenuView appMenu) {
	// this.appMenu = appMenu;
	// }

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
	// "menuParent")
	// // @OrderBy(value = "menuOrder")
	// public Set<AppMenuView> getAppMenus() {
	// return this.appMenus;
	// }
	//
	// public void setAppMenus(Set<AppMenuView> appMenus) {
	// this.appMenus = appMenus;
	// }

	// @Transient
	// public List<AppMenuView> getAppMenusSorted() {
	// List<AppMenuView> ret = new ArrayList<AppMenuView>(this.appMenus);
	// Collections.sort(ret, compareByOrder());
	// return ret;
	// }
	//
	// @Transient
	// private static Comparator<AppMenuView> compareByOrder() {
	// Comparator com = new Comparator<AppMenuView>() {
	//
	// @Override
	// public int compare(AppMenuView o1, AppMenuView o2) {
	// return o1.menuOrder.compareTo(o2.menuOrder);
	// }
	// };
	// return com;
	// }

	@Column(name = "FORM_ID", length = 32)

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

	@Transient
	@Override
	public int compareTo(AppMenuView o) {
		return this.menuOrder.compareTo(o.menuOrder);
	}

}