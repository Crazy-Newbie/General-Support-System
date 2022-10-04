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

import com.badaklng.app.base.BaseModel;

/**
 * AppUserGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_USER_GROUP")

public class AppUserGroup extends BaseModel implements java.io.Serializable {

	// Fields

	private AppUserGroupId id;
	private AppGroup appGroup;
	private AppUser appUser;
	private String addedBy;
	private Timestamp addedDate;

	// Constructors

	/** default constructor */
	public AppUserGroup() {
	}

	/** full constructor */
	public AppUserGroup(AppUserGroupId id, AppGroup appGroup, AppUser appUser, String addedBy, Timestamp addedDate) {
		this.id = id;
		this.appGroup = appGroup;
		this.appUser = appUser;
		this.addedBy = addedBy;
		this.addedDate = addedDate;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 8)),
			@AttributeOverride(name = "groupId", column = @Column(name = "GROUP_ID", nullable = false, length = 30)) })

	public AppUserGroupId getId() {
		return this.id;
	}

	public void setId(AppUserGroupId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GROUP_ID", nullable = false, insertable = false, updatable = false)

	public AppGroup getAppGroup() {
		return this.appGroup;
	}

	public void setAppGroup(AppGroup appGroup) {
		this.appGroup = appGroup;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "ADDED_BY", nullable = false, length = 6)

	public String getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	@Column(name = "ADDED_DATE", nullable = false, length = 7)

	public Timestamp getAddedDate() {
		return this.addedDate;
	}

	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}

}