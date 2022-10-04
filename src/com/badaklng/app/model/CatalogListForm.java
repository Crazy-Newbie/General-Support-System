package com.badaklng.app.model;

import java.util.List;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.hibernate.AppCat;

public class CatalogListForm extends BaseForm {

	private static final String FORM_CODE = "CATALOG_LIST_FORM";

	private List<AppCat> appCats;

	public CatalogListForm() {
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.CREATE);
	}

	public List<AppCat> getAppCats() {
		return appCats;
	}

	public void setAppCats(List<AppCat> appCats) {
		this.appCats = appCats;
	}

}
