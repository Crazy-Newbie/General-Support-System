package com.badaklng.gss.hibernate;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TeamView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TEAM_VIEW", schema = "GSS")

public class TeamView implements java.io.Serializable {

	// Fields

	private String teamId;
	private String teamName;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;
	private Integer teamMember;
	private Integer neww;
	private Integer asg;
	private Integer pnd;
	private Integer cmpl;
	private Integer exe;
	private Integer canc;
	private Integer nos;

	// Constructors

	/** default constructor */
	public TeamView() {
	}

	// Property accessors
	@Id

	@Column(name = "TEAM_ID", nullable = false, length = 8)

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

	@Column(name = "TEAM_MEMBER", precision = 0)

	public Integer getTeamMember() {
		return this.teamMember;
	}

	public void setTeamMember(Integer teamMember) {
		this.teamMember = teamMember;
	}

	@Column(name = "NEWW", precision = 0)

	public Integer getNeww() {
		return this.neww;
	}

	public void setNeww(Integer neww) {
		this.neww = neww;
	}

	@Column(name = "ASG", precision = 0)

	public Integer getAsg() {
		return this.asg;
	}

	public void setAsg(Integer asg) {
		this.asg = asg;
	}

	@Column(name = "PND", precision = 0)

	public Integer getPnd() {
		return this.pnd;
	}

	public void setPnd(Integer pnd) {
		this.pnd = pnd;
	}

	@Column(name = "CMPL", precision = 0)

	public Integer getCmpl() {
		return this.cmpl;
	}

	public void setCmpl(Integer cmpl) {
		this.cmpl = cmpl;
	}

	@Column(name = "CANC", precision = 0)

	public Integer getCanc() {
		return this.canc;
	}

	public void setCanc(Integer canc) {
		this.canc = canc;
	}

	@Column(name = "NOS", precision = 0)

	public Integer getNos() {
		return this.nos;
	}

	public void setNos(Integer nos) {
		this.nos = nos;
	}

	@Column(name = "EXE", precision = 0)
	public Integer getExe() {
		return exe;
	}

	public void setExe(Integer exe) {
		this.exe = exe;
	}

}