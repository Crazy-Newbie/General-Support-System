package com.badaklng.gss.hibernate;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RequestView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUEST_VIEW", schema = "GSS")

public class RequestView implements java.io.Serializable {

	// Fields

	private String reqId;
	private Timestamp createdDate;
	private String reqBy;
	private String reqByName;
	private String reqDesc;
	private String location;
	private String teamId;
	private String teamName;
	private String status;
	private String statusDesc;

	// Constructors

	/** default constructor */
	public RequestView() {
	}

	/** minimal constructor */
	public RequestView(String reqId, Timestamp createdDate, String reqBy, String reqDesc, String location,
			String status) {
		this.reqId = reqId;
		this.createdDate = createdDate;
		this.reqBy = reqBy;
		this.reqDesc = reqDesc;
		this.location = location;
		this.status = status;
	}

	/** full constructor */
	public RequestView(String reqId, Timestamp createdDate, String reqBy, String reqByName, String reqDesc,
			String location, String teamId, String teamName, String status, String statusDesc) {
		this.reqId = reqId;
		this.createdDate = createdDate;
		this.reqBy = reqBy;
		this.reqByName = reqByName;
		this.reqDesc = reqDesc;
		this.location = location;
		this.teamId = teamId;
		this.teamName = teamName;
		this.status = status;
		this.statusDesc = statusDesc;
	}

	// Property accessors
	@Id

	@Column(name = "REQ_ID", nullable = false, length = 16)

	public String getReqId() {
		return this.reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
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

	@Column(name = "REQ_BY_NAME", length = 64)

	public String getReqByName() {
		return this.reqByName;
	}

	public void setReqByName(String reqByName) {
		this.reqByName = reqByName;
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

	@Column(name = "TEAM_ID", length = 8)

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

	@Column(name = "STATUS", nullable = false, length = 32)

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS_DESC")

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}