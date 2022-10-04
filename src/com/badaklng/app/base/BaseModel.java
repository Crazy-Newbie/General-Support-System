package com.badaklng.app.base;

public class BaseModel {

	public String getModelAttrKey() {
		return this.getClass().getSimpleName();
	}

	public String getModelListAttrKey() {
		return this.getClass().getSimpleName() + "List";
	}

}