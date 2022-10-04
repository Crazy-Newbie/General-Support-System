package com.badaklng.app.hibernate;

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
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.model.UserForm;
import com.badaklng.app.utilities.Utility;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AppUser entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APP_USER")

public class AppUser extends BaseModel implements java.io.Serializable {

	public static final String modelAttrKey = "AppUser";
	// Fields
	private String userId;
	private String pwd;
	private Boolean isActive;
	private Boolean isLdap;
	private String ldapUser;
	private Boolean isAllowLoginas;
	private String name;
	private String email;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;

	// addition for filtering subordinate
	private String filter;
	private String filterValue;
	private Boolean includeDescendant;
	private Boolean filterPtb;

	// additional for system
	private String ipAddress;
	private AppUser originalUser;

	// addition for Complete Info
	private AppEmpLkpView info;

	// addition
//	private Set<AppUserGroup> appUserGroups = new HashSet<AppUserGroup>();

	// Constructors
	/**
	 * default constructor
	 */
	public AppUser() {
	}

	public AppUser(UserForm form, AppUser user) {
		this.userId = form.getUserId();
		this.pwd = form.getPwd();
		this.isActive = form.getActive();
		this.isLdap = form.getLdapFlag();
		this.ldapUser = form.getLdapUser();
		this.isAllowLoginas = form.getAllowLoginAs();
		this.name = form.getName().toUpperCase();
		this.email = form.getEmail();
		this.createBy = user.getUserId();
		this.createDate = Utility.getNow();
		this.filter = form.getFilterType();
		this.filterValue = form.getFilterValue();
		this.includeDescendant = form.getIncludeDescendant();
		this.filterPtb = form.getIncludePtb();
	}

	public void setUser(UserForm form, AppUser user) {
//		this.appRole = role;
		this.isActive = form.getActive();
		this.isLdap = form.getLdapFlag();
		this.ldapUser = form.getLdapUser();
		this.isAllowLoginas = form.getAllowLoginAs();
		this.name = form.getName().toUpperCase();
		this.email = form.getEmail();
		this.updateBy = user.getUserId();
		this.updateDate = Utility.getNow();
		this.filter = form.getFilterType();
		this.filterValue = form.getFilterValue();
		this.includeDescendant = form.getIncludeDescendant();
		this.filterPtb = form.getIncludePtb();
	}

	// Property accessors
	@Id

	@Column(name = "USER_ID", unique = true, nullable = false, length = 8)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "ROLE_ID", nullable = false)
//
//	public AppRole getAppRole() {
//		return this.appRole;
//	}
//
//	public void setAppRole(AppRole appRole) {
//		this.appRole = appRole;
//	}

	@Column(name = "PWD")

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "IS_ACTIVE", nullable = false, precision = 1, scale = 0)

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "IS_LDAP", nullable = false, precision = 1, scale = 0)

	public Boolean getIsLdap() {
		return this.isLdap;
	}

	public void setIsLdap(Boolean isLdap) {
		this.isLdap = isLdap;
	}

	@Column(name = "LDAP_USER", length = 32)

	public String getLdapUser() {
		return this.ldapUser;
	}

	public void setLdapUser(String ldapUser) {
		this.ldapUser = ldapUser;
	}

	@Column(name = "IS_ALLOW_LOGINAS", nullable = false, precision = 1, scale = 0)

	public Boolean getIsAllowLoginas() {
		return this.isAllowLoginas;
	}

	public void setIsAllowLoginas(Boolean isAllowLoginas) {
		this.isAllowLoginas = isAllowLoginas;
	}

	@Column(name = "NAME", nullable = false, length = 64)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", length = 64)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CREATE_BY", nullable = false, length = 6)

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATE_DATE", nullable = false, length = 7)

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY", length = 6)

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "UPDATE_DATE", length = 7)

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "FILTER")
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Column(name = "FILTER_VALUE")
	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	@Column(name = "INCLUDE_DESCENDANT")
	public Boolean getIncludeDescendant() {
		return includeDescendant;
	}

	public void setIncludeDescendant(Boolean includeDescendant) {
		this.includeDescendant = includeDescendant;
	}

//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "appUser")
//	public Set<AppUserGroup> getAppUserGroups() {
//		return appUserGroups;
//	}
//
//	public void setAppUserGroups(Set<AppUserGroup> appUserGroups) {
//		this.appUserGroups = appUserGroups;
//	}

	@Transient
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Transient
	public AppUser getOriginalUser() {
		return originalUser;
	}

	public void setOriginalUser(AppUser originalUser) {
		this.originalUser = originalUser;
	}

	@Transient
	public String getInitial() {
		if (this.name.contains(" ") && !this.name.contains(" ."))
			return Utility.initial(this.name);
		else
			return this.name.substring(0, 3);
	}

	@Column(name = "FILTER_PTB")
	public Boolean getFilterPtb() {
		return filterPtb;
	}

	public void setFilterPtb(Boolean filterPtb) {
		this.filterPtb = filterPtb;
	}

	@Transient
	public AppEmpLkpView getInfo() {
		return info;
	}

	public void setInfo(AppEmpLkpView info) {
		this.info = info;
	}
	
	

//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "USER_ID", nullable = true, insertable = false, updatable = false)
//	@NotFound(action = NotFoundAction.IGNORE) // set ignore as this field is for information only
//	public AppEmpLkpView getInfo() {
//		return info;
//	}
//
//	public void setInfo(AppEmpLkpView info) {
//		this.info = info;
//	}
//
//	@Transient
//	public boolean isInGroup(String string) {
//		for (AppUserGroup aug : appUserGroups) {
//			if (aug.getId().getGroupId().equalsIgnoreCase(string))
//				return true;
//		}
//		return false;
//	}

}
