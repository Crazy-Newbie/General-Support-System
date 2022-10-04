package com.badaklng.app.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.utilities.Utility;

public class AppPositionListViewDAO extends BaseHibernateDAO {
	
	public AppPositionListView findById(String id) throws Exception {
		return (AppPositionListView) getSession().createQuery("from AppPositionListView t where emplid like \'%"+id+"%\'").uniqueResult();
	}

	public List<?> findByCriteriaConnectByPrior(String employeeId) throws Exception {
		Criteria criteria = getSession().createCriteria(AppPositionListView.class);
		criteria.add(Restrictions.sqlRestriction(" 1=1 CONNECT BY PRIOR POSITION_ID=SUPERIOR_ID"));
		criteria.add(Restrictions.ne("department", "DUMMY"));
		criteria.add(Restrictions.ne("department", "MPP"));
		if (!Utility.isEmptyString(employeeId)) {
			criteria.add(Restrictions.eq("employeeId", employeeId));
		}
		return criteria.list();
	}
	
	public List<?> findbySupId(String supId) {
		try {
			String queryString = "from AppPositionListView t where t.superiorId like :supId";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("supId", supId);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
