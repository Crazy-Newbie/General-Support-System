package com.badaklng.gss.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.utilities.Utility;
import com.badaklng.gss.model.RequestForm;
import com.badaklng.gss.model.RequestListForm;
import com.badaklng.gss.model.RequestStatusForm;
import com.badaklng.gss.model.RequestStatusListForm;
import com.badaklng.gss.model.TeamForm;
import com.badaklng.gss.model.TeamListForm;

import java.util.*;

public class GssMainDAO extends BaseHibernateDAO {

	public Team getTeamById(String teamId) {
//		return (Team) getSession().createSQLQuery("select * from team where team_id = :id").setParameter("id", teamId)
//				.uniqueResult();
		return (Team) getSession().get(Team.class, teamId);
	}

	public void createTeam_pkg(TeamForm form, AppUser user) {
		Query query = getSession().createSQLQuery("call PKG_GSS.CREATE_TEAM(:id,:name,:by)")
				.setParameter("id", form.getTeamId()).setParameter("name", form.getTeamName())
				.setParameter("by", user.getUserId());
		query.executeUpdate();
	}

	public void updateTeam_pkg(TeamForm form, AppUser user) {
		Query query = getSession().createSQLQuery("call PKG_GSS.UPDATE_TEAM(:id,:name,:by)")
				.setParameter("id", form.getTeamId()).setParameter("name", form.getTeamName())
				.setParameter("by", user.getUserId());
		query.executeUpdate();
	}
	
	public void deleteTeam_pkg(TeamForm form) {
		Query query = getSession().createSQLQuery("call PKG_GSS.DELETE_TEAM(:id,:name)")
				.setParameter("id", form.getTeamId()).setParameter("name", form.getTeamName());
		query.executeUpdate();
	}

	public void createRequest_pkg(RequestForm form, AppUser user) {
		Query query = getSession().createSQLQuery("call PKG_GSS.CREATE_REQUEST(:by,:reqby,:desc,:loc,:teamid)")
				.setParameter("by", user.getUserId()).setParameter("reqby", form.getReqBy())
				.setParameter("desc", form.getReqDesc()).setParameter("loc", form.getLocation())
				.setParameter("teamid", form.getTeamId());
		query.executeUpdate();
	}

	public void updateRequest_pkg(RequestForm form, AppUser user) {
		Query query = getSession().createSQLQuery("call PKG_GSS.UPDATE_REQUEST(:reqid,:by,:reqby,:desc,:loc,:teamid)")
				.setParameter("reqid", form.getReqId()).setParameter("by", user.getUserId())
				.setParameter("reqby", form.getReqBy()).setParameter("desc", form.getReqDesc())
				.setParameter("loc", form.getLocation()).setParameter("teamid", form.getTeamId());
		query.executeUpdate();
	}

	public Request getReqById(String reqId) {
		return (Request) getSession().get(Request.class, reqId);
	}

	public void createRequestStatus(RequestStatusForm form, AppUser user) {
		Query query = getSession().createSQLQuery("call PKG_GSS.CREATE_STATUS(:status,:statusnt,:by,:reqid)")
				.setParameter("status", form.getStatus()).setParameter("statusnt", form.getStatusNote())
				.setParameter("by", user.getUserId()).setParameter("reqid", form.getReqId());
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<TeamView> findTeamViewAll() {
		return getSession().createCriteria(TeamView.class).addOrder(Order.asc("teamId")).list();
	}

	@SuppressWarnings("unchecked")
	public List<RequestView> findRequestViewByForm(RequestListForm form, DataResponse res) {
		Criteria criteria = getSession().createCriteria(RequestView.class);
		Map<String, Object> filters = new HashMap<String, Object>();

		if (!Utility.isEmptyString(form.getReqId()))
			filters.put("reqId", Utility.decorateStringToWildcard(form.getReqId()));
//		if (!Utility.isEmptyString(form.getCreatedDateString()))
//			filters.put("createdDateString", Utility.decorateString(form.getCreatedDateString()));
		// TODO : pencarian by tanggal - range
		if (!Utility.isEmptyString(form.getStartDate()))
			criteria.add(Restrictions.ge("createdDate", Utility.stringDateToTimestamp(form.getStartDate())));
		if (!Utility.isEmptyString(form.getEndDate()))
			criteria.add(Restrictions.le("createdDate",
					Utility.stringDatetimeToTimestamp(form.getEndDate() + " 23:59:59")));
		if (!Utility.isEmptyString(form.getReqBy()))
			filters.put("reqBy", Utility.decorateStringToWildcard(form.getReqBy()));
		if (!Utility.isEmptyString(form.getReqDesc()))
			filters.put("reqDesc", Utility.decorateStringToWildcard(form.getReqDesc()));
		if (!Utility.isEmptyString(form.getLocation()))
			filters.put("location", Utility.decorateStringToWildcard(form.getLocation()));
		if (!Utility.isEmptyString(form.getTeamId()))
			filters.put("teamId", form.getTeamId());
		if (!Utility.isEmptyString(form.getStatus()))
			filters.put("status", form.getStatus());

		arrangeANDFilters(criteria, filters);

		if (res != null) {
			criteria.setProjection(Projections.rowCount());
			res.setRecordsTotal(criteria.uniqueResult());
			res.setRecordsFiltered(res.getRecordsTotal());
			criteria.setProjection(null);
			criteria.setMaxResults(form.getPageSize());
			criteria.setFirstResult(form.getStart());
			criteria.addOrder(Order.desc("reqId"));
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Team> findTeamAll() {
		return getSession().createCriteria(Team.class).addOrder(Order.asc("teamName")).list();
	}

	public RequestView getReqViewById(String reqId) {
		return (RequestView) getSession().get(RequestView.class, reqId);
	}

//	@SuppressWarnings("unchecked")
//	public List<RequestStatusView> findRequestStatusViewByForm(RequestStatusListForm form, DataResponse res) {
//		Criteria criteria = getSession().createCriteria(RequestStatusView.class);
//		Map<String, Object> filters = new HashMap<String, Object>();
//
////		if (!Utility.isEmptyString(form.getLogDateString()))
////			filters.put("logDateString", form.getLogDateString());
//		if (!Utility.isEmptyString(form.getStatus()))
//			filters.put("status", form.getStatus());
//		if (!Utility.isEmptyString(form.getStatusNote()))
//			filters.put("statusNote", form.getStatusNote());
//		if (!Utility.isEmptyString(form.getUpdatedBy()))
//			filters.put("updatedBy", form.getUpdatedBy());
//		if (!Utility.isEmptyString(form.getReqId()))
//			filters.put("id.reqId", Utility.decorateStringToWildcard(form.getReqId()));
//
//		arrangeANDFilters(criteria, filters);
//
//		if (res != null) {
//			criteria.setProjection(Projections.rowCount());
//			res.setRecordsTotal(criteria.uniqueResult());
//			res.setRecordsFiltered(res.getRecordsTotal());
//			criteria.setProjection(null);
//			criteria.setMaxResults(form.getPageSize());
//			criteria.setFirstResult(form.getStart());
//			criteria.addOrder(Order.desc("id.reqId"));
//		}
//
//		return criteria.list();
//	}

	@SuppressWarnings("unchecked")
	public List<TeamMember> findTeamMemberByTeamId(String teamId) {
		return getSession().createCriteria(TeamMember.class).add(Restrictions.eq("id.teamId", teamId))
				.addOrder(Order.asc("id.memberId")).list();
	}

	public TeamMember getTeamMemberById(TeamMemberId teamMemberId) {
		return (TeamMember) getSession().get(TeamMember.class, teamMemberId);
	}

	@SuppressWarnings("unchecked")
	public List<RequestStatusView> findRequestStatusViewByReqId(String reqId) {
		return getSession().createCriteria(RequestStatusView.class).add(Restrictions.eq("id.reqId", reqId))
				.addOrder(Order.asc("id.logDate")).list();
	}

	

}
