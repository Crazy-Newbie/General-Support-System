package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class CatalogForm extends BaseForm {
	private static final String FORM_CODE = "CATALOG_FORM";

	public CatalogForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
	}
}
