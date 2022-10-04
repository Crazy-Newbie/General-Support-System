package com.badaklng.gss.hibernate;

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

import com.badaklng.app.hibernate.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TeamMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TEAM_MEMBER", schema = "GSS")

public class TeamMember implements java.io.Serializable {

	// Fields

	private TeamMemberId id;
	private Team team;
	private AppUser appUser;
	private String assignBy;
	private Timestamp assignDate;

	// Constructors

	/** default constructor */
	public TeamMember() {
	}

	/** full constructor */
	public TeamMember(TeamMemberId id, Team team, AppUser appUser, String assignBy, Timestamp assignDate) {
		this.id = id;
		this.team = team;
		this.appUser = appUser;
		this.assignBy = assignBy;
		this.assignDate = assignDate;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "memberId", column = @Column(name = "MEMBER_ID", nullable = false, length = 8)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, length = 8)) })

	public TeamMemberId getId() {
		return this.id;
	}

	public void setId(TeamMemberId id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID", nullable = false, insertable = false, updatable = false)
	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID", nullable = false, insertable = false, updatable = false)

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "ASSIGN_BY", nullable = false, length = 64)

	public String getAssignBy() {
		return this.assignBy;
	}

	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}

	@Column(name = "ASSIGN_DATE", nullable = false, length = 7)

	public Timestamp getAssignDate() {
		return this.assignDate;
	}

	public void setAssignDate(Timestamp assignDate) {
		this.assignDate = assignDate;
	}

}