package com.badaklng.gss.hibernate;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Team entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TEAM", schema = "GSS")

public class Team implements java.io.Serializable {

	// Fields

	private String teamId;
	private String teamName;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;
	private Set<Request> requests = new HashSet<Request>(0);
	private Set<TeamMember> teamMembers = new HashSet<TeamMember>(0);

	// Constructors

	/** default constructor */
	public Team() {
	}

	/** minimal constructor */
	public Team(String teamId) {
		this.teamId = teamId;
	}

	/** full constructor */
	public Team(String teamId, String teamName, String createdBy, Timestamp createdDate, String updatedBy,
			Timestamp updatedDate, Set<Request> requests, Set<TeamMember> teamMembers) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.requests = requests;
		this.teamMembers = teamMembers;
	}

	// Property accessors
	@Id

	@Column(name = "TEAM_ID", unique = true, nullable = false, length = 8)

	public String getTeamId() {
		return this.teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	@Column(name = "TEAM_NAME", length = 32)

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "CREATED_BY", length = 64)

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE", length = 7)

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "UPDATED_BY", length = 64)

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "UPDATED_DATE", length = 7)

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")

	public Set<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")

	public Set<TeamMember> getTeamMembers() {
		return this.teamMembers;
	}

	public void setTeamMembers(Set<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

}