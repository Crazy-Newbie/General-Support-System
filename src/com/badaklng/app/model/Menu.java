package com.badaklng.app.model;
import java.util.List;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.hibernate.AppMenu;

/**
 * @author NN6ZZN2212
 *
 */
/**
 * @author NN6ZZN2212
 *
 */
public class Menu extends BaseModel {
	
	private List<AppMenu> appMenu;
	private List<Menu> retValue;
	private String prefix;
	private String middle;
	private String url;
	private String suffix;
	private boolean orangTua;
	private boolean anakBungsu;

	public Menu(){
		
	}
	
	public Menu(String prefix, String middle, String url, String suffix){
		this.prefix = prefix;
		this.middle = middle;
		this.url = url;
		this.suffix = suffix;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public boolean isOrangTua() {
		return orangTua;
	}

	public void setOrangTua(boolean orangTua) {
		this.orangTua = orangTua;
	}

	public boolean isAnakBungsu() {
		return anakBungsu;
	}

	public void setAnakBungsu(boolean anakBungsu) {
		this.anakBungsu = anakBungsu;
	}

	public List<Menu> getMenu(){
		return retValue;
	}

	public List<AppMenu> getAppMenu() {
		return appMenu;
	}

	public void setAppMenu(List<AppMenu> appMenu) {
		this.appMenu = appMenu;
	}

	public List<Menu> getRetValue() {
		return retValue;
	}

	public void setRetValue(List<Menu> retValue) {
		this.retValue = retValue;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
}
