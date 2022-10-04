package com.badaklng.app.hibernate;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppException entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_EXCEPTION")

public class AppException implements java.io.Serializable {

	// Fields

	private String exceptionId;
	private Boolean isSolved;
	private Timestamp createDate;
	private String messages;

	// Constructors

	/** default constructor */
	public AppException() {
	}

	/** minimal constructor */
	public AppException(String exceptionId, Boolean isSolved) {
		this.exceptionId = exceptionId;
		this.isSolved = isSolved;
	}

	/** full constructor */
	public AppException(String exceptionId, Boolean isSolved, Timestamp createDate, String messages) {
		this.exceptionId = exceptionId;
		this.isSolved = isSolved;
		this.createDate = createDate;
		this.messages = messages;
	}

	public AppException(String newId) {
		this.exceptionId = newId;
	}

	// Property accessors
	@Id

	@Column(name = "EXCEPTION_ID", unique = true, nullable = false, length = 20)

	public String getExceptionId() {
		return this.exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	@Column(name = "IS_SOLVED", nullable = false, precision = 1, scale = 0)

	public Boolean getIsSolved() {
		return this.isSolved;
	}

	public void setIsSolved(Boolean isSolved) {
		this.isSolved = isSolved;
	}

	@Column(name = "CREATE_DATE", length = 7)

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "MESSAGES", length = 4000)

	public String getMessages() {
		return this.messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

}