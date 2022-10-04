package com.badaklng.app.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.UserForm;
import com.badaklng.app.model.UserListForm;
import com.badaklng.app.model.UserLkpForm;
import com.badaklng.app.utilities.Utility;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.badaklng.app.hibernate.iss.hibernate.survey.hibernate.AppUser
 * @author MyEclipse Persistence Tools
 */
public class AppUserDAO extends BaseHibernateDAO {
	private static final Logger log = Logger.getLogger(AppUserDAO.class);
	// property constants
	public static final String ROLE_ID = "roleId";
	public static final String MENU_ID = "menuId";
	public static final String PWD = "pwd";
	public static final String IS_ACTIVE = "isActive";
	public static final String IS_LDAP = "isLdap";
	public static final String LDAP_USER = "ldapUser";
	public static final String IS_ALLOW_LOGINAS = "isAllowLoginas";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String SPV_USER_ID = "spvUserId";
	public static final String SPV_NAME = "spvName";
	public static final String SPV_EMAIL = "spvEmail";

	// public void editUser(UserForm form, AppUser user, AppRole role) {
	// user.setEmail(form.getEmail());
	// user.setIsActive(form.getActive());
	// user.setIsAllowLoginas(form.getAllowLoginAs());
	// user.setIsLdap(form.getLdapFlag());
	// user.setLdapUser(form.getLdapUser());
	// user.setName(form.getName());
	// if (form.getPwd() != null && !form.getPwd().equalsIgnoreCase(""))
	// user.setPwd(form.getPwd());
	// user.setAppRole(role);
	//
	// getSession().saveOrUpdate(user);
	// }

	public void saveOrUpdate(AppUser usr) {
		getSession().saveOrUpdate(usr);
	}

	public void addUser(AppUser user) {
		user.setPwd(getEncodePassword(user.getPwd()));
		getSession().saveOrUpdate(user);
	}

	public void addUser(UserForm form, AppUser creator) {
		if (form.getPwd() != null && !form.getPwd().equalsIgnoreCase(""))
			form.setPwd(getEncodePassword(form.getPwd()));
		AppUser user = new AppUser(form, creator);
		getSession().saveOrUpdate(user);
	}

	public void generatePassword(String userid, String pwd) {
		String sql = "{call PKG_APP_USER.GENERATE_SUPPORTED_DATA(?,?)}";
		Query query = getSession().createSQLQuery(sql).setParameter(0, userid).setParameter(1, pwd);
		query.executeUpdate();
	}

	public void deleteUser(String userId) {
		AppUser user = findById(userId);
		getSession().delete(user);
	}

	public void attemptLogin(String ipAddress, String username, String password) throws JDBCException {

		Query query = getSession().createSQLQuery("CALL pkg_APP_user.login(:p_ip_address,:p_user_id,:p_password)")
				.setParameter("p_ip_address", ipAddress).setParameter("p_user_id", username)
				.setParameter("p_password", password);
		query.executeUpdate();
	}

	public void attemptLogin(String ipAddress, String username) throws JDBCException {

		Query query = getSession().createSQLQuery("CALL pkg_APP_user.login(:p_ip_address,:p_user_id)")
				.setParameter("p_ip_address", ipAddress).setParameter("p_user_id", username);
		query.executeUpdate();
	}

	public AppUser findById(java.lang.String id) {
		log.debug("getting AppUser instance with id: " + id);
		try {
			AppUser instance = (AppUser) getSession().get(AppUser.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AppUser> findAll() {
		log.debug("finding all AppUser instances");
		try {
			String queryString = "from AppUser where is_active = 1";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AppUser> findByKeyword(String keyword) {
		String queryString = "from AppUser where is_active = 1 and (email like :key or name like :key or user_id like :key)";
		Query query = getSession().createQuery(queryString).setParameter("key",
				Utility.decorateStringToWildcard(keyword.toUpperCase()));
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppUser> findByKeyword(UserLkpForm form, DataResponse res) {
		Criteria criteria = getSession().createCriteria(AppUser.class);
		// criteria.add(Restrictions.eq("isActive", true));

		criteria.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) criteria.uniqueResult());

		if (!Utility.isEmptyString(form.getKeyword())) {
			String key = form.getKeyword().toUpperCase();
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("email", Utility.decorateStringToWildcard(key));
			filters.put("name", Utility.decorateStringToWildcard(key));
			filters.put("userId", Utility.decorateStringToWildcard(key));
			arrangeORFilters(criteria, filters);
			res.setRecordsFiltered((Long) criteria.uniqueResult());
		} else {
			res.setRecordsFiltered(res.getRecordsTotal());
		}

		criteria.setProjection(null);

		criteria.addOrder(Order.asc("userId"));
		criteria.setFirstResult(form.getStart());
		criteria.setMaxResults(form.getPageSize());

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppUser> findByKeyword(UserListForm form, DataResponse res) {
		Criteria criteria = getSession().createCriteria(AppUser.class);
		// criteria.add(Restrictions.eq("isActive", true));

		criteria.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) criteria.uniqueResult());

		if (!Utility.isEmptyString(form.getKeyword())) {
			String key = form.getKeyword().toUpperCase();
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("email", Utility.decorateStringToWildcard(key));
			filters.put("name", Utility.decorateStringToWildcard(key));
			filters.put("userId", Utility.decorateStringToWildcard(key));
			arrangeORFilters(criteria, filters);
			res.setRecordsFiltered((Long) criteria.uniqueResult());
		} else {
			res.setRecordsFiltered(res.getRecordsTotal());
		}

		criteria.setProjection(null);

		criteria.addOrder(Order.asc("userId"));
		criteria.setFirstResult(form.getStart());
		criteria.setMaxResults(form.getPageSize());

		return criteria.list();
	}

	public List<AppUser> findByKeyword(UserLkpForm form, DataResponse res, Integer limit) {
		Criteria criteria = getSession().createCriteria(AppUser.class);
		criteria.add(Restrictions.eq("isActive", true));

		criteria.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) criteria.uniqueResult());

		if (!Utility.isEmptyString(form.getKeyword())) {
			String key = form.getKeyword().toUpperCase();
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("email", Utility.decorateStringToWildcard(key));
			filters.put("name", Utility.decorateStringToWildcard(key));
			filters.put("userId", Utility.decorateStringToWildcard(key));
			arrangeORFilters(criteria, filters);
			res.setRecordsFiltered((Long) criteria.uniqueResult());
		} else {
			res.setRecordsFiltered(res.getRecordsTotal());
		}

		criteria.setProjection(null);

		criteria.addOrder(Order.asc("userId"));
		criteria.setFirstResult(form.getStart());
		criteria.setMaxResults(form.getPageSize());

		return criteria.list();
	}

	public String getEncodePassword(String inp) {
		Query query = getSession().createSQLQuery("SELECT PKG_APP_USER.ENCODE_PASSWORD(:inp) FROM DUAL")
				.setParameter("inp", inp);
		return (String) query.uniqueResult();
	}

	public void changePassword(String userId, String employeeId, String newPass, String newPassConf)
			throws JDBCException {

		Query query = getSession().createSQLQuery(
				"CALL pkg_APP_user.change_password(:p_user_id,:p_changee_id,:p_new_password,:p_conf_new_password)")
				.setParameter("p_user_id", userId).setParameter("p_changee_id", employeeId)
				.setParameter("p_new_password", newPass).setParameter("p_conf_new_password", newPassConf);
		query.executeUpdate();
	}

	// App Emp Info View

	// public List<AppEmpLkpView> findEmpPTBActive(String upperCase) {
	// Criteria criteria = getSession().createCriteria(AppEmpInfoView.class);
	// Map<String, Object> filters = new HashMap<String, Object>();
	// filters.put("emplid", Utility.decorateStringToWildcard(upperCase));
	// filters.put("nameDisplay", Utility.decorateStringToWildcard(upperCase));
	// filters.put("deptDesc", Utility.decorateStringToWildcard(upperCase));
	// filters.put("sectionDesc", Utility.decorateStringToWildcard(upperCase));
	// filters.put("positionDesc", Utility.decorateStringToWildcard(upperCase));
	// arrangeORFilters(criteria, filters);
	// criteria.addOrder(Order.asc("emplid"));
	// return criteria.list();
	// }

	@SuppressWarnings("unchecked")
	public List<AppEmpLkpView> findPTBEmployee(BaseForm form, DataResponse res) {
		Criteria criteria = getSession().createCriteria(AppEmpLkpView.class);
		Map<String, Object> filters = new HashMap<String, Object>();

		if (!Utility.isEmptyString(form.getKeyword())) {
			String keyword = form.getKeyword().toUpperCase();
			filters.put("emplid", Utility.decorateStringToWildcard(keyword));
			filters.put("nameDisplay", Utility.decorateStringToWildcard(keyword));
			filters.put("deptDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("sectionDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("positionDesc", Utility.decorateStringToWildcard(keyword));
			arrangeORFilters(criteria, filters);
		}

		criteria.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) criteria.uniqueResult());
		res.setRecordsFiltered(res.getRecordsTotal());
		criteria.setProjection(null);

		criteria.setFirstResult(form.getStart());
		criteria.setMaxResults(form.getPageSize());
		criteria.addOrder(Order.asc("emplid"));
		return criteria.list();
	}

	public List<AppSubEmpLkpView> findSubEmployee(BaseForm form, AppUser user, DataResponse res) {
		Criteria criteria = getSession(user).createCriteria(AppSubEmpLkpView.class);
		Map<String, Object> filters = new HashMap<String, Object>();

		if (!Utility.isEmptyString(form.getKeyword())) {
			String keyword = form.getKeyword().toUpperCase();
			filters.put("emplid", Utility.decorateStringToWildcard(keyword));
			filters.put("nameDisplay", Utility.decorateStringToWildcard(keyword));
			filters.put("deptDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("sectionDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("positionDesc", Utility.decorateStringToWildcard(keyword));
			arrangeORFilters(criteria, filters);
		}

		criteria.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) criteria.uniqueResult());
		res.setRecordsFiltered(res.getRecordsTotal());
		criteria.setProjection(null);

		criteria.setFirstResult(form.getStart());
		criteria.setMaxResults(form.getPageSize());
		criteria.addOrder(Order.asc("emplid"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppEmpInfoView> findPTBEmployeeinfo(BaseForm form, DataResponse res) {
		Criteria criteria = getSession().createCriteria(AppEmpInfoView.class);
		Map<String, Object> filters = new HashMap<String, Object>();

		if (!Utility.isEmptyString(form.getKeyword())) {
			String keyword = form.getKeyword().toUpperCase();
			filters.put("emplid", Utility.decorateStringToWildcard(keyword));
			filters.put("nameDisplay", Utility.decorateStringToWildcard(keyword));
			filters.put("deptDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("sectionDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("positionDesc", Utility.decorateStringToWildcard(keyword));
			arrangeORFilters(criteria, filters);
		}

		criteria.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) criteria.uniqueResult());
		res.setRecordsFiltered(res.getRecordsTotal());
		criteria.setProjection(null);

		criteria.setFirstResult(form.getStart());
		criteria.setMaxResults(form.getPageSize());
		criteria.addOrder(Order.asc("emplid"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppEmpLkpView> findEmployee(BaseForm form) {
		Criteria criteria = getSession().createCriteria(AppEmpLkpView.class);
		Map<String, Object> filters = new HashMap<String, Object>();

		if (!Utility.isEmptyString(form.getKeyword())) {
			String keyword = form.getKeyword().toUpperCase();
			filters.put("emplid", Utility.decorateStringToWildcard(keyword));
			filters.put("nameDisplay", Utility.decorateStringToWildcard(keyword));
			filters.put("deptDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("sectionDesc", Utility.decorateStringToWildcard(keyword));
			filters.put("positionDesc", Utility.decorateStringToWildcard(keyword));
			arrangeORFilters(criteria, filters);
		}

		criteria.addOrder(Order.asc("emplid"));
		return criteria.list();
	}

	public void save_pkg(UserForm form, AppUser user) {
		Query query = getSession().createSQLQuery(
				"call PKG_APP_USER.ADD_USER(:userid,:pwd,:active,:isldap,:ldapuser,:loginas,:name,:createby,:filter,:filterval,:incdesc,:email,:filptb)")
				.setParameter("userid", form.getUserId()).setParameter("pwd", form.getPwd())
				.setParameter("active", form.getActive() ? 1 : 0).setParameter("isldap", form.getLdapFlag() ? 1 : 0)
				.setParameter("ldapuser", form.getLdapUser()).setParameter("loginas", form.getAllowLoginAs() ? 1 : 0)
				.setParameter("name", form.getName()).setParameter("filter", form.getFilterType())
				.setParameter("filterval", form.getFilterValue())
				.setParameter("incdesc", form.getIncludeDescendant() ? 1 : 0).setParameter("createby", user.getUserId())
				.setParameter("email", form.getEmail()).setParameter("filptb", form.getIncludePtb() ? 1 : 0);
		query.executeUpdate();
	}

	public void update_pkg(UserForm form, AppUser user) {
		Query query = getSession().createSQLQuery(
				"call PKG_APP_USER.UPDATE_USER(:userid,:active,:isldap,:ldapuser,:loginas,:name,:by,:filter,:filterval,:incdesc,:email,:filptb)")
				.setParameter("userid", form.getUserId()).setInteger("active", form.getActive() ? 1 : 0)
				.setInteger("isldap", form.getLdapFlag() ? 1 : 0).setParameter("ldapuser", form.getLdapUser())
				.setInteger("loginas", form.getAllowLoginAs() ? 1 : 0).setParameter("name", form.getName())
				.setParameter("filter", form.getFilterType()).setParameter("filterval", form.getFilterValue())
				.setParameter("incdesc", form.getIncludeDescendant() ? 1 : 0).setParameter("by", user.getUserId())
				.setParameter("email", form.getEmail()).setParameter("filptb", form.getIncludePtb() ? 1 : 0);
		query.executeUpdate();
	}

	public void deleteUser_pkg(String userId) {
		Query query = getSession().createSQLQuery("call PKG_APP_USER.DELETE_USER(:userid)").setParameter("userid",
				userId);
		query.executeUpdate();
	}

	public Boolean inGroup(String userId, String groupId) {
		AppUserGroup aug = (AppUserGroup) getSession().get(AppUserGroup.class, new AppUserGroupId(userId, groupId));
		return aug != null;
	}

	public AppEmpLkpView findEmployeeLkpInfo(String userId) {
		return (AppEmpLkpView) getSession().get(AppEmpLkpView.class, userId);
	}

	public void whitelistAddress(String attribute) {
		Query query = getSession().createSQLQuery("call PKG_APP_USER.IS_WHITELIST(:ipaddr)").setParameter("ipaddr",
				attribute);
		query.executeUpdate();
	}

	public void assignRole_pkg(String userId, String role, AppUser user) {
		Query query = getSession().createSQLQuery("call PKG_APP_USER.ASSIGN_ROLE(:user,:role,:by)")
				.setParameter("user", userId).setParameter("role", role).setParameter("by", user.getUserId());
		query.executeUpdate();
	}

}