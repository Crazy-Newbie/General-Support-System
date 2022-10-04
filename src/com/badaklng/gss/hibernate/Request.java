package com.badaklng.gss.hibernate;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Request entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUEST", schema = "GSS")

public class Request implements java.io.Serializable {

	// Fields

	private String reqId;
	private Team team;
	private String createdBy;
	private Timestamp createdDate;
	private String reqBy;
	private String reqDesc;
	private String location;
	private Set<RequestLog> requestLogs = new HashSet<RequestLog>(0);

	// Constructors

	/** default constructor */
	public Request() {
	}

	/** minimal constructor */
	public Request(String reqId, Team team, String createdBy, Timestamp createdDate, String reqBy, String reqDesc,
			String location) {
		this.reqId = reqId;
		this.team = team;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.reqBy = reqBy;
		this.reqDesc = reqDesc;
		this.location = location;
	}

	/** full constructor */
	public Request(String reqId, Team team, String createdBy, Timestamp createdDate, String reqBy, String reqDesc,
			String location, Set<RequestLog> requestLogs) {
		this.reqId = reqId;
		this.team = team;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.reqBy = reqBy;
		this.reqDesc = reqDesc;
		this.location = location;
		this.requestLogs = requestLogs;
	}

	// Property accessors
	@Id

	@Column(name = "REQ_ID", unique = true, nullable = false, length = 16)

	public String getReqId() {
		return this.reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID", nullable = false)

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 64)

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE", nullable = false, length = 7)

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "REQ_BY", nullable = false, length = 64)

	public String getReqBy() {
		return this.reqBy;
	}

	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}

	@Column(name = "REQ_DESC", nullable = false, length = 1000)

	public String getReqDesc() {
		return this.reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}

	@Column(name = "LOCATION", nullable = false, length = 128)

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "request")

	public Set<RequestLog> getRequestLogs() {
		return this.requestLogs;
	}

	public void setRequestLogs(Set<RequestLog> requestLogs) {
		this.requestLogs = requestLogs;
	}

}