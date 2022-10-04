package com.badaklng.app.model;

import java.util.List;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.hibernate.AppUser;

/**
 * @author pt badak
 *
 */
public class UserForm extends BaseForm {

	private static final String FORM_CODE = "USER_FORM";

	private String userId;
	private String roleId;
	private String pwd;
	private Boolean active;
	private Boolean ldapFlag;
	private String ldapUser;
	private Boolean allowLoginAs;
	private String name;
	private String email;

	// additional from APP_USER in HBI
	private String assetType;
	private String ownerCode;

	// additional for add non-ldap user

	private String filterType;
	private String filterValue;

	// additional for filtering
	private Boolean includeDescendant;
	private Boolean includePtb;

	// addition for app user role
	private List<String> roles;

	public UserForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.CREATE);
		userId = "";
		name = "";
		roleId = "";
		pwd = "";
		ldapUser = "";
		email = "";
		ldapFlag = false;
		allowLoginAs = false;
		active = false;
		filterType = "";
		filterValue = "";
		includeDescendant = false;
		includePtb = false;
	}

	public void setUser(AppUser appUser) {
		active = appUser.getIsActive();
		ldapFlag = appUser.getIsLdap();
		ldapUser = appUser.getLdapUser();
		allowLoginAs = appUser.getIsAllowLoginas();
		name = appUser.getName();
		email = appUser.getEmail();
		filterType = appUser.getFilter();
		filterValue = appUser.getFilterValue();
		includeDescendant = appUser.getIncludeDescendant();
		includePtb = appUser.getFilterPtb();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getLdapFlag() {
		return ldapFlag;
	}

	public void setLdapFlag(Boolean ldapFlag) {
		this.ldapFlag = ldapFlag;
	}

	public String getLdapUser() {
		return ldapUser;
	}

	public void setLdapUser(String ldapUser) {
		this.ldapUser = ldapUser;
	}

	public Boolean getAllowLoginAs() {
		return allowLoginAs;
	}

	public void setAllowLoginAs(Boolean allowLoginAs) {
		this.allowLoginAs = allowLoginAs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public Boolean getIncludeDescendant() {
		return includeDescendant;
	}

	public void setIncludeDescendant(Boolean includeDescendant) {
		this.includeDescendant = includeDescendant;
	}

	public Boolean getIncludePtb() {
		return includePtb;
	}

	public void setIncludePtb(Boolean includePtb) {
		this.includePtb = includePtb;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
