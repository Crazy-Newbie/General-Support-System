package com.badaklng.app.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Restrictions;

import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.constant.Constants;
import com.badaklng.app.utilities.Utility;

public class AppMailDAO extends BaseHibernateDAO {
	public AppMailDAO() {
		super();
	}

	public AppMailDAO(AppUser usr) {
		super(usr);
	}

	public void archiveAndPurge() throws Exception {

		Query query = getSession().createSQLQuery("{CALL PKG_APP_MAIL.archive_and_purge}");

		query.executeUpdate();
	}

	public void mailError(String mailId, String errorMessage) throws Exception {

		Query query = getSession().createSQLQuery("CALL PKG_APP_MAIL.mail_error(:p_mail_id,:p_msg )")
				.setParameter("p_mail_id", mailId).setParameter("p_msg", errorMessage);

		query.executeUpdate();
	}

	public void mailInprocess(String mailId) throws Exception {

		Query query = getSession().createSQLQuery("CALL PKG_APP_MAIL.mail_inprocess(:p_mail_id )")
				.setParameter("p_mail_id", mailId);

		query.executeUpdate();
	}

	public void mailSuccess(String mailId) throws Exception {

		Query query = getSession().createSQLQuery("CALL PKG_APP_MAIL.mail_success(:p_mail_id )")
				.setParameter("p_mail_id", mailId);

		query.executeUpdate();
	}

	public void acquireLock() throws Exception {

		Query query = getSession().createSQLQuery("{CALL PKG_APP_MAIL.acquire_lock}");

		query.executeUpdate();
	}

	public void queueMail(String mailId, String to, String subject, String body, String mailCategory, String key)
			throws Exception {
		Query query = getSession()
				.createSQLQuery("call PKG_APP_MAIL.QUEUE_MAIL(:mailid,:to,:subject,:body,:mailcat,:key)")
				.setParameter("mailid", mailId).setParameter("to", to).setParameter("subject", subject)
				.setParameter("body", body).setParameter("mailcat", mailCategory).setParameter("key", key);
		query.executeUpdate();
	}

	public void queueMail(String mailId, String to, String subject, String body, String mailCategory, String key,
			String cc, String bcc) {
		Query query = getSession()
				.createSQLQuery("call PKG_APP_MAIL.QUEUE_MAIL(:mailid,:to,:subject,:body,:mailcat,:key,:cc,:bcc)")
				.setParameter("mailid", mailId).setParameter("to", to).setParameter("subject", subject)
				.setParameter("body", body).setParameter("mailcat", mailCategory).setParameter("key", key)
				.setParameter("cc", cc).setParameter("bcc", bcc);
		query.executeUpdate();
	}

	public ScrollableResults getMailQueue(Map<String, Object> filters) throws Exception {
		Criteria criteria = getSession().createCriteria(AppMailQueueView.class);
		arrangeANDFilters(criteria, filters);
		criteria.setCacheable(false);
		criteria.setFetchSize(Constants.SCROLLABLE_FETCH_SIZE);
		criteria.setLockMode(LockMode.READ);

		return criteria.scroll(ScrollMode.FORWARD_ONLY);
	}

	public String getDeveloperContact() {
		AppParameter param = (AppParameter) getSession().get(AppParameter.class, "ADMIN_EMAIL");
		if (param != null)
			return param.getParamValue();
		return null;
	}

	public String getEmailAddrByEmplId(String reqBy) {
		AppEmpLkpView lkp = (AppEmpLkpView) getSession().get(AppEmpLkpView.class, reqBy);
		if (lkp != null)
			return lkp.getEmail();
		return null;
	}

	public AppEmpLkpView getEmployeeByEmplId(String reqBy) {
		return (AppEmpLkpView) getSession().get(AppEmpLkpView.class, reqBy);
	}

	public String getEmailsByPosId(String posId) {
		return getEmailsByPosId(posId, ",");
	}

	@SuppressWarnings("unchecked")
	public String getEmailsByPosId(String posId, String delimiter) {
		List<String> list = new ArrayList<String>();

		Criteria criteria = getSession().createCriteria(AppEmpLkpView.class);
		criteria.add(Restrictions.eq("positionNbr", posId));
		for (AppEmpLkpView v : (List<AppEmpLkpView>) criteria.list()) {
			list.add(v.getEmail());
		}

		return Utility.join(list, delimiter);
	}
}