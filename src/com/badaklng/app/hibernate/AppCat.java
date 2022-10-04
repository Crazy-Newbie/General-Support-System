package com.badaklng.app.hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.utilities.Utility;

/**
 * AppCat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_CAT")

public class AppCat extends BaseModel implements java.io.Serializable {

	// Fields

	private AppCatId id;
	private String tval;
	private String tattr1;
	private String tattr2;
	private String tattr3;
	private String tattr4;
	private String tattr5;
	private String tattr6;
	private String tattr7;
	private String tattr8;
	private String tattr9;
	private String tattr10;
	private Boolean active;
	private String orderBy;

	// addition for CatalogForm
	private Boolean delete;

	// Constructors

	/** default constructor */
	public AppCat() {
		delete = false;
		active = true;
	}

//	/** minimal constructor */
//	public AppCat(AppCatId id) {
//		this.id = id;
//	}
//
//	/** full constructor */
//	public AppCat(AppCatId id, String tval, String tattr1, String tattr2, String tattr3, String tattr4, String tattr5,
//			String tattr6, String tattr7, String tattr8, String tattr9, String tattr10, Boolean active,
//			String orderBy) {
//		this.id = id;
//		this.tval = tval;
//		this.tattr1 = tattr1;
//		this.tattr2 = tattr2;
//		this.tattr3 = tattr3;
//		this.tattr4 = tattr4;
//		this.tattr5 = tattr5;
//		this.tattr6 = tattr6;
//		this.tattr7 = tattr7;
//		this.tattr8 = tattr8;
//		this.tattr9 = tattr9;
//		this.tattr10 = tattr10;
//		this.active = active;
//		this.orderBy = orderBy;
//	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "ttyp", column = @Column(name = "TTYP", nullable = false, length = 20)),
			@AttributeOverride(name = "tcode", column = @Column(name = "TCODE", nullable = false, length = 128)) })

	public AppCatId getId() {
		return this.id;
	}

	public void setId(AppCatId id) {
		this.id = id;
	}

	@Column(name = "TVAL")

	public String getTval() {
		return this.tval;
	}

	public void setTval(String tval) {
		this.tval = tval;
	}

	@Column(name = "TATTR1", length = 512)

	public String getTattr1() {
		return this.tattr1;
	}

	public void setTattr1(String tattr1) {
		this.tattr1 = tattr1;
	}

	@Column(name = "TATTR2", length = 512)

	public String getTattr2() {
		return this.tattr2;
	}

	public void setTattr2(String tattr2) {
		this.tattr2 = tattr2;
	}

	@Column(name = "TATTR3", length = 512)

	public String getTattr3() {
		return this.tattr3;
	}

	public void setTattr3(String tattr3) {
		this.tattr3 = tattr3;
	}

	@Column(name = "TATTR4", length = 512)

	public String getTattr4() {
		return this.tattr4;
	}

	public void setTattr4(String tattr4) {
		this.tattr4 = tattr4;
	}

	@Column(name = "TATTR5", length = 512)

	public String getTattr5() {
		return this.tattr5;
	}

	public void setTattr5(String tattr5) {
		this.tattr5 = tattr5;
	}

	@Column(name = "TATTR6", length = 512)

	public String getTattr6() {
		return this.tattr6;
	}

	public void setTattr6(String tattr6) {
		this.tattr6 = tattr6;
	}

	@Column(name = "TATTR7", length = 512)

	public String getTattr7() {
		return this.tattr7;
	}

	public void setTattr7(String tattr7) {
		this.tattr7 = tattr7;
	}

	@Column(name = "TATTR8", length = 512)

	public String getTattr8() {
		return this.tattr8;
	}

	public void setTattr8(String tattr8) {
		this.tattr8 = tattr8;
	}

	@Column(name = "TATTR9", length = 512)

	public String getTattr9() {
		return this.tattr9;
	}

	public void setTattr9(String tattr9) {
		this.tattr9 = tattr9;
	}

	@Column(name = "TATTR10", length = 512)

	public String getTattr10() {
		return this.tattr10;
	}

	public void setTattr10(String tattr10) {
		this.tattr10 = tattr10;
	}

	@Column(name = "ACTIVE", precision = 1, scale = 0)

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "ORDER_BY")

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Transient
	public String getTvalToEscapeJS() {
		return Utility.encodeURIComponent(this.tval);
	}

	@Transient
	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

}