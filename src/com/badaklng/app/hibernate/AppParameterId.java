package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.badaklng.app.base.BaseModel;

/**
 * AppParameterId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class AppParameterId extends BaseModel implements java.io.Serializable {

	// Fields

	private String paramKey;
	private String paramValue;
	private String paramDesc;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public AppParameterId() {
	}

	/** full constructor */
	public AppParameterId(String paramKey, String paramValue, String paramDesc, Timestamp lastUpdate) {
		this.paramKey = paramKey;
		this.paramValue = paramValue;
		this.paramDesc = paramDesc;
		this.lastUpdate = lastUpdate;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppParameterId))
			return false;
		AppParameterId castOther = (AppParameterId) other;

		return ((this.getParamKey() == castOther.getParamKey()) || (this.getParamKey() != null
				&& castOther.getParamKey() != null && this.getParamKey().equals(castOther.getParamKey())))
				&& ((this.getParamValue() == castOther.getParamValue()) || (this.getParamValue() != null
						&& castOther.getParamValue() != null && this.getParamValue().equals(castOther.getParamValue())))
				&& ((this.getParamDesc() == castOther.getParamDesc()) || (this.getParamDesc() != null
						&& castOther.getParamDesc() != null && this.getParamDesc().equals(castOther.getParamDesc())))
				&& ((this.getLastUpdate() == castOther.getLastUpdate())
						|| (this.getLastUpdate() != null && castOther.getLastUpdate() != null
								&& this.getLastUpdate().equals(castOther.getLastUpdate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getParamKey() == null ? 0 : this.getParamKey().hashCode());
		result = 37 * result + (getParamValue() == null ? 0 : this.getParamValue().hashCode());
		result = 37 * result + (getParamDesc() == null ? 0 : this.getParamDesc().hashCode());
		result = 37 * result + (getLastUpdate() == null ? 0 : this.getLastUpdate().hashCode());
		return result;
	}

}