package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.hibernate.AppMenu;

public class MenuForm extends BaseForm {

	private static final String FORM_CODE = "MENU_FORM";

	private Integer menuId;
	private String menuName;
	private String menuUrl;
	private Integer menuOrder;
	private Integer menuParentId;
	private String menuParentName;

	// additional
	private String formId;
	private String formName;
	private Boolean isMenu;
	private String icon;
	private Boolean withChild;

	public MenuForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.CREATE);
		menuId = -1;
		menuParentId = 0;
		menuOrder = 99;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public Integer getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(Integer menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuParentName() {
		return menuParentName;
	}

	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Boolean getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getWithChild() {
		return withChild;
	}

	public void setWithChild(Boolean withChild) {
		this.withChild = withChild;
	}

	public void setMenuForm(AppMenu menu, AppMenu menuParent) {
		menuId = menu.getMenuId();
		menuName = menu.getMenuName();
		menuUrl = menu.getMenuUrl();
		menuOrder = menu.getMenuOrder();
		menuParentId = menu.getMenuParent();
		if (menuParent != null)
			menuParentName = menuParent.getMenuName();
//		menuParentId = menu.getAppMenu().getMenuId();
//		menuParentName = menu.getMenuParent().getMenuName();
		formId = menu.getFormId();
		formName = menu.getFormName();
		isMenu = menu.getIsMenu();
		icon = menu.getIcon();
	}

}
