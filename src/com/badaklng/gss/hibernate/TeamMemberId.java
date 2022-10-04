package com.badaklng.gss.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TeamMemberId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class TeamMemberId implements java.io.Serializable {

	// Fields

	private String memberId;
	private String teamId;

	// Constructors

	/** default constructor */
	public TeamMemberId() {
	}

	/** full constructor */
	public TeamMemberId(String memberId, String teamId) {
		this.memberId = memberId;
		this.teamId = teamId;
	}

	// Property accessors

	@Column(name = "MEMBER_ID", nullable = false, length = 8)

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "TEAM_ID", nullable = false, length = 8)

	public String getTeamId() {
		return this.teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TeamMemberId))
			return false;
		TeamMemberId castOther = (TeamMemberId) other;

		return ((this.getMemberId() == castOther.getMemberId()) || (this.getMemberId() != null
				&& castOther.getMemberId() != null && this.getMemberId().equals(castOther.getMemberId())))
				&& ((this.getTeamId() == castOther.getTeamId()) || (this.getTeamId() != null
						&& castOther.getTeamId() != null && this.getTeamId().equals(castOther.getTeamId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMemberId() == null ? 0 : this.getMemberId().hashCode());
		result = 37 * result + (getTeamId() == null ? 0 : this.getTeamId().hashCode());
		return result;
	}

}