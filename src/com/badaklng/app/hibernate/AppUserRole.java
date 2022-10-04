package com.badaklng.app.hibernate;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AppUserRole entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APP_USER_ROLE")

public class AppUserRole implements java.io.Serializable {

	// Fields

	private AppUserRoleId id;
	private AppRole appRole;
	private AppUser appUser;
	private String assignBy;
	private Timestamp assignDate;

	// Constructors

	/** default constructor */
	public AppUserRole() {
	}

	/** full constructor */
	public AppUserRole(AppUserRoleId id, AppRole appRole, AppUser appUser, String assignBy, Timestamp assignDate) {
		this.id = id;
		this.appRole = appRole;
		this.appUser = appUser;
		this.assignBy = assignBy;
		this.assignDate = assignDate;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 12)),
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, length = 25)) })

	public AppUserRoleId getId() {
		return this.id;
	}

	public void setId(AppUserRoleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)

	public AppRole getAppRole() {
		return this.appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "ASSIGN_BY", length = 6)

	public String getAssignBy() {
		return this.assignBy;
	}

	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}

	@Column(name = "ASSIGN_DATE", length = 7)

	public Timestamp getAssignDate() {
		return this.assignDate;
	}

	public void setAssignDate(Timestamp assignDate) {
		this.assignDate = assignDate;
	}

}