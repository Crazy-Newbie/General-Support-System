package com.badaklng.app.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@SuppressWarnings("serial")
@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "getSequenceKeyYear", query = "{ ? = call  pkg_APP_common.generate_sequence_year(:p_key) }", resultClass = SequenceGenerator.class, hints = { @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") }),
		@NamedNativeQuery(name = "getSequenceKey", query = "{ ? = call  pkg_APP_common.generate_sequence(:p_key) }", resultClass = SequenceGenerator.class, hints = { @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") }),
		@NamedNativeQuery(name = "getSequence", query = "select pkg_APP_common.generate_row_id SEQ from dual", resultClass = SequenceGenerator.class)})
public class SequenceGenerator implements java.io.Serializable {

	// Fields

	private String sequence;

	// Constructors

	/** default constructor */
	public SequenceGenerator() {
	}

	/** minimal constructor */
	public SequenceGenerator(String sequence) {
		this.sequence = sequence;
	}

	// Property accessors
	@Id
	@Column(name = "SEQ", nullable = false, length = 75)
	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

}