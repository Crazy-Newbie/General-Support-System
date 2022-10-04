package com.badaklng.app.model;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badaklng.app.base.BaseModel;

public class CatalogOrdered extends BaseModel {
	private static final Hashtable<String, ArrayList<?>> lists = new Hashtable<String, ArrayList<?>>();

	public void clearCatalog() {
		lists.clear();
	}

	public Hashtable<String, ArrayList<?>> getCodes() {
		return lists;
	}

	public void setCode(String key, ArrayList<?> value) {
		lists.put(key, value);
	}
}
