package com.badaklng.app.model;

import java.util.Hashtable;

import com.badaklng.app.base.BaseModel;

public class FormPair extends BaseModel {
	private static final Hashtable<String, String> lists = new Hashtable<String, String>();

	public void clearCatalog() {
		lists.clear();
	}
	
	public Hashtable<String, String> getCodes() {
		return lists;
	}

	public void setCode(String key, String value) {
		lists.put(key, value);
	}

}
