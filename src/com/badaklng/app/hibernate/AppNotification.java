package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppNotification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_NOTIFICATION")

public class AppNotification implements java.io.Serializable {

	// Fields

	private String notifId;
	private String toUser;
	private String message;
	private String fromUser;
	private Timestamp notifTime;
	private Boolean isOpened;
	private String link;
	private String notifKey;

	// Constructors

	/** default constructor */
	public AppNotification() {
	}

	/** minimal constructor */
	public AppNotification(String notifId, String toUser, Timestamp notifTime, Boolean isOpened) {
		this.notifId = notifId;
		this.toUser = toUser;
		this.notifTime = notifTime;
		this.isOpened = isOpened;
	}

	/** full constructor */
	public AppNotification(String notifId, String toUser, String message, String fromUser, Timestamp notifTime,
			Boolean isOpened, String notifKey) {
		this.notifId = notifId;
		this.toUser = toUser;
		this.message = message;
		this.fromUser = fromUser;
		this.notifTime = notifTime;
		this.isOpened = isOpened;
		this.notifKey = notifKey;
	}

	// Property accessors
	@Id

	@Column(name = "NOTIF_ID", unique = true, nullable = false, length = 15)

	public String getNotifId() {
		return this.notifId;
	}

	public void setNotifId(String notifId) {
		this.notifId = notifId;
	}

	@Column(name = "TO_USER", nullable = false, length = 6)

	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	@Column(name = "MESSAGE")

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "FROM_USER", length = 6)

	public String getFromUser() {
		return this.fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	@Column(name = "NOTIF_TIME", nullable = false, length = 7)

	public Timestamp getNotifTime() {
		return this.notifTime;
	}

	public void setNotifTime(Timestamp notifTime) {
		this.notifTime = notifTime;
	}

	@Column(name = "IS_OPENED", nullable = false, precision = 1, scale = 0)

	public Boolean getIsOpened() {
		return this.isOpened;
	}

	public void setIsOpened(Boolean isOpened) {
		this.isOpened = isOpened;
	}

	@Column(name = "LINK")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Column(name = "NOTIF_KEY", length = 15)
	public String getNotifKey() {
		return notifKey;
	}

	public void setNotifKey(String notifKey) {
		this.notifKey = notifKey;
	}
	
	

}