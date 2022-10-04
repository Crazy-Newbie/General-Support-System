package com.badaklng.app.model;

import java.util.Hashtable;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.hibernate.AppCat;

public class CatalogPair extends BaseModel {
	private static final Hashtable<String, Hashtable<String, AppCat>> lists = new Hashtable<String, Hashtable<String, AppCat>>();

	public void clearCatalog() {
		lists.clear();
	}
	
	public Hashtable<String, Hashtable<String, AppCat>> getCodes() {
		return lists;
	}

	public void setCode(String key, Hashtable<String, AppCat> value) {
		lists.put(key, value);
	}

}
