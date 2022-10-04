package com.badaklng.app.base;

import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.badaklng.app.constant.ConnTypeEnum;
import com.badaklng.app.constant.Constants;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.hibernate.SequenceGenerator;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.utilities.Utility;

public class BaseHibernateDAO {
	private static final Logger logger = Logger.getLogger(BaseHibernateDAO.class);

	protected Transaction tx = null;
	protected AppUser sessionUser = null;
	protected ConnTypeEnum connType = ConnTypeEnum.FMS;

	public BaseHibernateDAO() {
	}

	public BaseHibernateDAO(AppUser usr) {
		this.sessionUser = usr;
	}

	public BaseHibernateDAO(ConnTypeEnum connTypeEnum) {
		this.connType = connTypeEnum;
	}

	public void beginTransaction() {
		tx = this.getSession().getTransaction();
		tx.begin();
	}

	public void begin() {
		beginTransaction();
	}

	public void rollbackTransaction() {
		if (tx != null) {
			try {
				tx.rollback();
			} catch (Exception e) {
				logger.warn("Rollback failure", e);
			}
			tx = null;
		}
	}

	public void rollback() {
		rollbackTransaction();
	}

	public void commitTransaction() {
		if (tx != null) {
			tx.commit();
			tx = null;
		}
	}

	public void commit() {
		commitTransaction();
	}

	public Session getSession() {
		return getSession(this.sessionUser);
	}

	public Session getSession(AppUser usr) {

		Session session = null;
		if (connType == ConnTypeEnum.FMS)
			session = HibernateSessionFactory.getSession();
//		else if (connType == ConnTypeEnum.SMS)
//			session = SMSSessionFactory.getSession();
//		else if (connType == ConnTypeEnum.EKI)
//			session = EKISessionFactory.getSession();

		try {
			session.flush();
		} catch (ConstraintViolationException cve) {
			throw cve; // do not ignore, instead re-throw it in order to notify
						// the controller that constraint violation has occured
		} catch (HibernateException he) {
			logger.warn("Errored Hibernate bean exists, ignoring and proceed to session clearance", he);
		} finally {
			session.clear();
		}

		// usr variable is null because it might be called from ajax
		if (usr != null) {
			Query query = null;
			if (connType == ConnTypeEnum.FMS)
				query = session.createSQLQuery("CALL PKG_APP_COMMON.SET_CONTEXT_VALUE('user_id',:p_user_id)")
						.setParameter("p_user_id", usr.getUserId());

			query.executeUpdate();
		}
		return session;
	}

	public StatelessSession getStatelessSession() {
		StatelessSession session = null;
		session = HibernateSessionFactory.getStatelessSession();
		return session;
	}

	public StatelessSession getStatelessSession(AppUser usr) {
		StatelessSession session = null;
		session = HibernateSessionFactory.getStatelessSession();
		if (usr != null) {
			Query query = null;
			if (connType == ConnTypeEnum.FMS)
				query = session.createSQLQuery("CALL PKG_APP_COMMON.SET_CONTEXT_VALUE('user_id',:p_user_id)")
						.setParameter("p_user_id", usr.getUserId());

			if (query != null)
				query.executeUpdate();
		}
		return session;
	}

	public void executeSQL(String syntax) {
		Query query = getSession().createSQLQuery(syntax);
		query.executeUpdate();
	}

	public void executeHQL(String syntax) {
		Query query = getSession().createQuery(syntax);
		query.executeUpdate();
	}

	public void logActivity(LogIntention li) {

		Query query = null;

		if (connType == ConnTypeEnum.FMS) {
			query = getSession()
					.createSQLQuery("CALL pkg_APP_log.do_log(:p_user_id,:p_ip_address,:p_log_type,:p_arguments)")
					.setParameter("p_user_id", Utility.decorateString(li.getUser().getUserId()))
					.setParameter("p_ip_address", Utility.decorateString(li.getUser().getIpAddress()))
					.setParameter("p_log_type", li.getType());

			query.setParameter("p_arguments", li.translateArguments());
			query.executeUpdate();
		} else {
			// do nothing as all log will be stored in HRIS database
		}
	}

	/**
	 * Log application activity into database(APP_log) with optional system logger
	 *
	 * @param action  application detail
	 * @param withLog log action (info) into system logger
	 */
	public void logAppActivity(Logger logger, String action, boolean withLog) {
		if (withLog) {
			logger.info("[" + Constants.APPL_SHORTNAME + "] " + action);
		}
		String sql2 = "CALL PKG_APP_LOG.DO_LOG(:userId,:ipAddress,:logType,:arg)";
		Query query = getSession().createSQLQuery(sql2).setParameter("userId", "SYSTEM")
				.setParameter("ipAddress", "SERVER").setParameter("logType", "APPLICATION LOGGER")
				.setParameter("arg", action);
		query.executeUpdate();
	}

	public String getSequence() {
		return ((SequenceGenerator) getSession().getNamedQuery("getSequence").list().get(0)).getSequence();
	}

	public String getSequenceKey(String p_key) throws Exception {
		if (tx == null || !tx.isActive())
			throw new Exception("Sequence must be called within active transaction");

		return ((SequenceGenerator) getSession().getNamedQuery("getSequenceKey").setParameter("p_key", p_key).list()
				.get(0)).getSequence();
	}

	public String getSequenceKeyYear(String p_key) throws Exception {
		if (tx == null || !tx.isActive())
			throw new Exception("Sequence must be called within active transaction");

		return ((SequenceGenerator) getSession().getNamedQuery("getSequenceKeyYear").setParameter("p_key", p_key).list()
				.get(0)).getSequence();
	}

	protected final void arrangeORFilters(Criteria criteria, Map<String, Object> filters) {
		arrangeORFilters(criteria, filters, false);
	}

	protected final void arrangeORFilters(Criteria criteria, Map<String, Object> filters, Boolean isCaseSensitive) {

		if (filters == null || filters.isEmpty())
			return;

		Criterion prevCriterion = null, currentCriterion = null;

		int counter = 1;

		for (String a : filters.keySet()) {

			if (isCaseSensitive)
				currentCriterion = Restrictions.like(a, filters.get(a));
			else
				currentCriterion = Restrictions.ilike(a, filters.get(a));

			if (counter == 1)
				prevCriterion = currentCriterion;
			else {
				prevCriterion = Restrictions.or(prevCriterion, currentCriterion);
			}

			counter++;
		}
		criteria.add(prevCriterion);
	}

	protected final void arrangeANDFilters(Criteria criteria, Map<String, Object> filters) {
		arrangeANDFilters(criteria, filters, false);
	}

	protected final void arrangeANDFilters(Criteria criteria, Map<String, Object> filters, Boolean isCaseSensitive) {
		if (filters == null || filters.isEmpty())
			return;
		Criterion prevCriterion = null, currentCriterion = null;

		int counter = 1;

		for (String a : filters.keySet()) {

			if (isCaseSensitive && filters.get(a).getClass().equals(String.class))
				currentCriterion = Restrictions.like(a, filters.get(a));
			else if (filters.get(a).getClass().equals(String.class))
				currentCriterion = Restrictions.ilike(a, filters.get(a));
			else
				currentCriterion = Restrictions.eq(a, filters.get(a));
			
			if (counter == 1)
				prevCriterion = currentCriterion;
			else {
				prevCriterion = Restrictions.and(prevCriterion, currentCriterion);
			}

			counter++;
		}
		criteria.add(prevCriterion);
	}

	protected final void arrangeExactFilters(Criteria criteria, Map<String, Object> filters) {
		if (filters == null || filters.isEmpty())
			return;
		Criterion prevCriterion = null, currentCriterion = null;

		int counter = 1;

		for (String a : filters.keySet()) {

			currentCriterion = Restrictions.eq(a, filters.get(a));
			if (counter == 1)
				prevCriterion = currentCriterion;
			else {
				prevCriterion = Restrictions.and(prevCriterion, currentCriterion);
			}

			counter++;
		}
		criteria.add(prevCriterion);
	}

}