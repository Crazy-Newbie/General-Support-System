package com.badaklng.app.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.ExceptionListForm;
import com.badaklng.app.model.LogForm;
import com.badaklng.app.model.LookupForm;
import com.badaklng.app.utilities.Utility;
import com.badaklng.gss.hibernate.TeamMember;
import com.badaklng.gss.hibernate.TeamMemberId;

public class AppDAO extends BaseHibernateDAO {

	private static final Logger log = Logger.getLogger(AppDAO.class);

	// Catalog
	@SuppressWarnings("unchecked")
	public List<AppCat> findCatAll() {
		log.debug("finding all AppCat instances");
		try {
			Criteria crit = getSession().createCriteria(AppCat.class);
			crit.add(Restrictions.eq("active", true));
			crit.addOrder(Order.asc("id.ttyp"));
			crit.addOrder(Order.asc("orderBy"));
			return crit.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	// Exception
	public void save(AppException excp) {
		try {
			getSession().save(excp);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public AppException findExceptionById(String id) {
		log.debug("getting AppException instance with id: " + id);
		try {
			AppException instance = (AppException) getSession().get(AppException.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void attachDirty(AppException instance) {
		log.debug("attaching dirty AppException instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AppException> findExceptionByIsSolved(boolean b) {
		Criteria crit = getSession().createCriteria(AppException.class);
		crit.add(Restrictions.eq("isSolved", b));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppException> findException(ExceptionListForm form, DataResponse res) {
		Criteria crit = getSession().createCriteria(AppException.class);
		crit.add(Restrictions.eq("isSolved", false));
		crit.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) crit.uniqueResult());
		res.setRecordsFiltered(res.getRecordsTotal());
		crit.setProjection(null);

		crit.addOrder(Order.asc("createDate"));
		crit.setFirstResult(form.getStart());
		crit.setMaxResults(1);

		return crit.list();
	}

	public void solveById_pkg(String exceptionId) {
		Query query = getSession().createSQLQuery("call PKG_APP_COMMON.SOLVE_EXCEPTION(:id)").setParameter("id",
				exceptionId);
		query.executeUpdate();
	}

	// parameter
	@SuppressWarnings("unchecked")
	public List<AppParameter> findParameterAll() {
		log.debug("finding all AppParameter instances");
		try {
			String queryString = "from AppParameter";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AppParameter getParameterById(java.lang.String id) {
		log.debug("getting AppParameter instance with id: " + id);
		try {
			AppParameter instance = (AppParameter) getSession().get(AppParameter.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void attachDirty(AppParameter instance) {
		log.debug("attaching dirty AppParameter instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	// Job
//	public void delete(AppJob persistentInstance) {
//		log.debug("deleting AppJob instance");
//		try {
//			getSession().delete(persistentInstance);
//			log.debug("delete successful");
//		} catch (RuntimeException re) {
//			log.error("delete failed", re);
//			throw re;
//		}
//	}

	public void delete(Object obj) {
		getSession().delete(obj);
	}

	public AppJob findJobById(java.lang.String id) {
		log.debug("getting AppJob instance with id: " + id);
		try {
			AppJob instance = (AppJob) getSession().get(AppJob.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AppJob> findJobAll() {
		log.debug("finding all AppJob instances");
		try {
			String queryString = "from AppJob";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AppJob> findActiveJob() {
		Criteria criteria = getSession().createCriteria(AppJob.class);
		criteria.add(Restrictions.eq("isActive", true));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppJob> findActiveJobByScheduled(String string) {
		Criteria criteria = getSession().createCriteria(AppJob.class);
		criteria.add(Restrictions.eq("scheduledTime", string));
		criteria.add(Restrictions.eq("isActive", true));
		return criteria.list();
	}

	public void attachDirty(AppJob instance) {
		log.debug("attaching dirty AppJob instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	// Role
	public void delete(AppRole persistentInstance) {
		log.debug("deleting AppRole instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppRole findRoleById(String id) {
		log.debug("getting AppRole instance with id: " + id);
		try {
			AppRole instance = (AppRole) getSession().get(AppRole.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AppRole> findRoleAll() {
		log.debug("finding all AppRole instances");
		try {
//			String queryString = "from AppRole";
//			Query queryObject = getSession().createQuery(queryString);
			Criteria queryObject = getSession().createCriteria(AppRole.class);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void attachDirty(AppRole instance) {
		log.debug("attaching dirty AppRole instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	// Log
	@SuppressWarnings("unchecked")
	public List<AppLog> findLogByDuration(Timestamp startDate, Timestamp endDate) {
		Criteria crit = getSession().createCriteria(AppLog.class);
		crit.add(Restrictions.ge("id.logTime", startDate));
		crit.add(Restrictions.le("id.logTime", endDate));
		crit.addOrder(Order.desc("id.logTime"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppLog> findLogByDuration(LogForm form, DataResponse res) {
		Criteria crit = getSession().createCriteria(AppLog.class);
		Map<String, Object> filters = new HashMap<String, Object>();

		crit.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) crit.uniqueResult());

		crit.add(Restrictions.ge("id.logTime", form.getStartDateTimestamp()));
		crit.add(Restrictions.le("id.logTime", form.getEndDateTimestamp()));

		if (!Utility.isEmptyString(form.getKeyword())) {
			String upperKeyword = form.getKeyword().toUpperCase();
			filters.put("id.userId", Utility.decorateStringToWildcard(upperKeyword));
			filters.put("ipAddress", Utility.decorateStringToWildcard(upperKeyword));
			filters.put("logType", Utility.decorateStringToWildcard(upperKeyword));
			filters.put("arguments", Utility.decorateStringToWildcard(upperKeyword));
			filters.put("formCode", Utility.decorateStringToWildcard(upperKeyword));
			filters.put("formAction", Utility.decorateStringToWildcard(upperKeyword));
			arrangeORFilters(crit, filters);
		}

		crit.addOrder(Order.desc("id.logTime"));

		res.setRecordsFiltered((Long) crit.uniqueResult());
		crit.setProjection(null);

		crit.setFirstResult(form.getStart());
		crit.setMaxResults(form.getPageSize());
		return crit.list();
	}

	public Integer getTodayLog() {
		Criteria criteria = getSession().createCriteria(AppLog.class);

		// System.out.println(Utility.getToday());
		criteria.add(Restrictions.ge("id.logTime", Utility.getToday()));
		criteria.add(Restrictions.ne("id.userId", "SYSTEM"));

		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).intValue();
//		return (Integer) criteria.uniqueResult();
	}

	// Role Menu
	public AppRoleMenu findRoleMenuById(AppRoleMenuId id) {
		log.debug("getting AppRoleMenu instance with id: " + id);
		try {
			AppRoleMenu instance = (AppRoleMenu) getSession().get(AppRoleMenu.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void attachDirty(AppRoleMenu instance) {
		log.debug("attaching dirty AppRoleMenu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AppRoleMenu persistentInstance) {
		log.debug("deleting AppRoleMenu instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	// Menu
	@SuppressWarnings("unchecked")
	public List<AppMenu> findMenuAll() {
		Query query = getSession().createQuery("from AppMenu");
		return query.list();
	}

	// Group
	@SuppressWarnings("unchecked")
	public List<AppGroup> findGroupAll() {
		Query query = getSession().createQuery("from AppGroup");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppGroup> findGroupByKeyword(String keyword) {
		Criteria criteria = getSession().createCriteria(AppGroup.class);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("groupId", keyword);
		filters.put("name", keyword);
		arrangeORFilters(criteria, filters);
		return criteria.list();
	}

	public AppGroup findGroupById(String groupId) {
		return (AppGroup) getSession().get(AppGroup.class, groupId);
	}

	public void attachDirty(AppGroup grp) {
		getSession().saveOrUpdate(grp);
	}

	public void save(AppGroup grp) {
		getSession().save(grp);
	}

	public void save(AppUserGroup appUserGroup) {
		getSession().saveOrUpdate(appUserGroup);
	}

	public void delete(AppGroup grp) {
		getSession().delete(grp);
	}

	public void delete(AppUserGroup aug) {
		getSession().delete(aug);
	}

	public AppUserGroup findAppUserGroupById(AppUserGroupId appUserGroupId) {
		return (AppUserGroup) getSession().get(AppUserGroup.class, appUserGroupId);
	}

	// Organization
	public List<AppOrganization> findOrganization() {
		Criteria criteria = getSession().createCriteria(AppOrganization.class);
		criteria.addOrder(Order.asc("orgId"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppOrganization> findOrganizationByKeyword(String keyword) {
		Criteria criteria = getSession().createCriteria(AppOrganization.class);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("orgId", Utility.decorateStringToWildcard(keyword));
		filters.put("description", Utility.decorateStringToWildcard(keyword));
		arrangeORFilters(criteria, filters);
		return criteria.list();
	}

	public List<AppOrganization> getDepartment() {
		Criteria criteria = getSession().createCriteria(AppOrganization.class);
		criteria.add(Restrictions.ne("orgLevel", "SEC"));
		return criteria.list();
	}

	public List<AppOrganization> getDepartment(String keyword) {
		Criteria criteria = getSession().createCriteria(AppOrganization.class);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("orgIn", Utility.decorateStringToWildcard(keyword));
		filters.put("description", Utility.decorateStringToWildcard(keyword));
		arrangeORFilters(criteria, filters);
		criteria.add(Restrictions.ne("orgLevel", "SEC"));
		return criteria.list();
	}

	public List<AppOrganization> getSection() {
		Criteria criteria = getSession().createCriteria(AppOrganization.class);
		criteria.add(Restrictions.eq("orgLevel", "SEC"));
		return criteria.list();
	}

	public List<AppOrganization> getSection(String keyword) {
		Criteria criteria = getSession().createCriteria(AppOrganization.class);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("orgIn", Utility.decorateStringToWildcard(keyword));
		filters.put("description", Utility.decorateStringToWildcard(keyword));
		arrangeORFilters(criteria, filters);
		criteria.add(Restrictions.eq("orgLevel", "SEC"));
		return criteria.list();
	}

	public List<AppOrganization> getSubOrg(String sup) {
		Criteria criteria = getSession().createCriteria(AppOrganization.class);
		criteria.add(Restrictions.eq("superiorOrgId", sup));
		return criteria.list();
	}

	public List<AppRoleMenuView> findAppRoleMenu(String roleId, Integer parent) {
		List<AppRoleMenuView> currList = new ArrayList<AppRoleMenuView>();
		Criteria criteria = getSession().createCriteria(AppRoleMenuView.class);
		criteria.add(Restrictions.eq("id.roleId", roleId));
		criteria.add(Restrictions.eq("menuParent", parent));
		criteria.addOrder(Order.asc("menuOrder"));
		for (AppRoleMenuView appRoleMenuView : (List<AppRoleMenuView>) criteria.list()) {
			currList.add(appRoleMenuView);
			currList.addAll(findAppRoleMenu(roleId, appRoleMenuView.getId().getMenuId()));
		}
		return currList;
	}

	// App Position

	// LookUp Form getPosition
	public List<AppPositionLkpView> findPTBPosByKeyword(LookupForm form, DataResponse res) {
		Criteria criteria = getSession().createCriteria(AppPositionLkpView.class);

		criteria.setProjection(Projections.rowCount());
		res.setRecordsTotal((Long) criteria.uniqueResult());

		if (!Utility.isEmptyString(form.getKeyword())) {
			String key = form.getKeyword().toUpperCase();
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("positionId", Utility.decorateStringToWildcard(key));
			filters.put("posTitle", Utility.decorateStringToWildcard(key));

			arrangeORFilters(criteria, filters);
			res.setRecordsFiltered((Long) criteria.uniqueResult());
		} else {
			res.setRecordsFiltered(res.getRecordsTotal());
		}

		criteria.setProjection(null);
		criteria.addOrder(Order.asc("positionId"));
		criteria.setFirstResult(form.getStart());
		criteria.setMaxResults(form.getPageSize());
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppPositionLkpView> findPositionByKeyword(String keyword) {
		Criteria criteria = getSession().createCriteria(AppPositionLkpView.class);
		criteria.add(Restrictions.or(Restrictions.like("positionId", Utility.decorateStringToWildcard(keyword)),
				Restrictions.like("posTitle", Utility.decorateStringToWildcard(keyword))));
		criteria.addOrder(Order.asc("positionId"));
		return criteria.list();
	}

	// Notification
	public AppNotification findNotifById(String notifId) {
		return (AppNotification) getSession().get(AppNotification.class, notifId);
	}

	// multirater
	public void addNotification(String empId, String link, String message) {

		Query query = null;
		query = getSession().createSQLQuery("CALL pkg_APP_notif.new_notif_now(:p_user_id,:p_link,:p_message)")
				.setParameter("p_user_id", Utility.decorateString(empId))
				.setParameter("p_link", Utility.decorateString(link))
				.setParameter("p_message", Utility.decorateString(message));
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<AppNotification> findUserNotification(String userId, Boolean isAll) {
		Criteria criteria = getSession().createCriteria(AppNotification.class);
		criteria.add(Restrictions.eq("toUser", userId));
		if (!isAll)
			criteria.add(Restrictions.eq("isOpened", false));
		criteria.addOrder(Order.desc("notifTime"));
		return criteria.list();
	}

	public void markNotification(String notifId) {
		AppNotification not = findNotifById(notifId);
		if (not != null) {
			not.setIsOpened(true);
			getSession().update(not);
		}
	}

	public void markAllNotification(String userId) {
		for (AppNotification not : findUserNotification(userId, false)) {
			not.setIsOpened(true);
			getSession().update(not);
		}
	}

	public List<AppUserRole> findByUser(String userId) {
		Criteria criteria = getSession().createCriteria(AppUserRole.class);
		criteria.add(Restrictions.eq("id.userId", userId));
		return criteria.list();
	}

	public void attachDirty(AppUserRole appUserRole) {
		getSession().saveOrUpdate(appUserRole);
	}

	public AppUserRole findById(AppUserRoleId appUserRoleId) {
		return (AppUserRole) getSession().get(AppUserRole.class, appUserRoleId);
	}

	public void delete(AppUserRole aur) {
		getSession().delete(aur);
	}

	public void addUserRole_pkg(String userId, String roleId, AppUser user) {
		Query query = getSession().createSQLQuery("call PKG_APP_USER.ADD_USER_ROLE(:userid,:roleid,:assignby)")
				.setParameter("userid", userId).setParameter("roleid", roleId)
				.setParameter("assignby", user.getUserId());
		query.executeUpdate();
	}

	public void saveOrUpdate(Object objc) {
		getSession().saveOrUpdate(objc);
	}

	

}
