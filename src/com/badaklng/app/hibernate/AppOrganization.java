package com.badaklng.app.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppOrganization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_ORGANIZATION")

public class AppOrganization extends BaseModel implements java.io.Serializable {

	// Fields

	private String orgId;
	private String description;
	private String superiorOrgId;
	private String orgLevel;

	// Constructors

	/** default constructor */
	public AppOrganization() {
	}

	/** minimal constructor */
	public AppOrganization(String orgId) {
		this.orgId = orgId;
	}

	/** full constructor */
	public AppOrganization(String orgId, String description, String superiorOrgId, String orgLevel) {
		this.orgId = orgId;
		this.description = description;
		this.superiorOrgId = superiorOrgId;
		this.orgLevel = orgLevel;
	}

	// Property accessors
	@Id

	@Column(name = "ORG_ID", unique = true, nullable = false, length = 10)

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "DESCRIPTION", length = 75)

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SUPERIOR_ORG_ID", length = 10)

	public String getSuperiorOrgId() {
		return this.superiorOrgId;
	}

	public void setSuperiorOrgId(String superiorOrgId) {
		this.superiorOrgId = superiorOrgId;
	}

	@Column(name = "ORG_LEVEL", length = 3)

	public String getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

}