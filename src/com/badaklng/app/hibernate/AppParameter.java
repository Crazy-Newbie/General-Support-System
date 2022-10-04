package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_PARAMETER")

public class AppParameter extends BaseModel implements java.io.Serializable {

	// Fields

	private String paramKey;
	private String paramValue;
	private String paramDesc;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public AppParameter() {
	}

	/** minimal constructor */
	public AppParameter(String paramKey) {
		this.paramKey = paramKey;
	}

	/** full constructor */
	public AppParameter(String paramKey, String paramValue, String paramDesc, Timestamp lastUpdate) {
		this.paramKey = paramKey;
		this.paramValue = paramValue;
		this.paramDesc = paramDesc;
		this.lastUpdate = lastUpdate;
	}

	// Property accessors
	@Id

	@Column(name = "PARAM_KEY", length = 50)

	public String getParamKey() {
		return this.paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	@Column(name = "PARAM_VALUE", length = 512)

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Column(name = "PARAM_DESC", length = 125)

	public String getParamDesc() {
		return this.paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	@Column(name = "LAST_UPDATE", length = 7)

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}