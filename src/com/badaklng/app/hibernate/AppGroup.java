package com.badaklng.app.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AppGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_GROUP")

public class AppGroup extends BaseModel implements java.io.Serializable {

	// Fields

	private String groupId;
	private String name;
	private Set<AppUserGroup> appUserGroups = new HashSet<AppUserGroup>(0);

	// Constructors

	/** default constructor */
	public AppGroup() {
	}

	/** minimal constructor */
	public AppGroup(String groupId) {
		this.groupId = groupId;
	}

	/** full constructor */
	public AppGroup(String groupId, String name, Set<AppUserGroup> appUserGroups) {
		this.groupId = groupId;
		this.name = name;
		this.appUserGroups = appUserGroups;
	}

	// Property accessors
	@Id

	@Column(name = "GROUP_ID", unique = true, nullable = false, length = 30)

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "NAME", length = 125)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appGroup", orphanRemoval = true)
	public Set<AppUserGroup> getAppUserGroups() {
		return this.appUserGroups;
	}

	public void setAppUserGroups(Set<AppUserGroup> appUserGroups) {
		this.appUserGroups = appUserGroups;
	}

}