package com.badaklng.app.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.badaklng.app.base.BaseModel;

/**
 * AppCatId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class AppCatId extends BaseModel implements java.io.Serializable {

	// Fields

	private String ttyp;
	private String tcode;

	// Constructors

	/** default constructor */
	public AppCatId() {
	}

	/** full constructor */
	public AppCatId(String ttyp, String tcode) {
		this.ttyp = ttyp;
		this.tcode = tcode;
	}

	// Property accessors

	@Column(name = "TTYP", nullable = false, length = 20)

	public String getTtyp() {
		return this.ttyp;
	}

	public void setTtyp(String ttyp) {
		this.ttyp = ttyp;
	}

	@Column(name = "TCODE", nullable = false, length = 128)

	public String getTcode() {
		return this.tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppCatId))
			return false;
		AppCatId castOther = (AppCatId) other;

		return ((this.getTtyp() == castOther.getTtyp()) || (this.getTtyp() != null && castOther.getTtyp() != null
				&& this.getTtyp().equals(castOther.getTtyp())))
				&& ((this.getTcode() == castOther.getTcode()) || (this.getTcode() != null
						&& castOther.getTcode() != null && this.getTcode().equals(castOther.getTcode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTtyp() == null ? 0 : this.getTtyp().hashCode());
		result = 37 * result + (getTcode() == null ? 0 : this.getTcode().hashCode());
		return result;
	}

}