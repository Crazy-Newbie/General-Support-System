package com.badaklng.app.hibernate;

import org.apache.commons.lang.NotImplementedException;
import org.hibernate.JDBCException;
import org.hibernate.Query;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.exception.NoAccessException;
import com.badaklng.app.utilities.ExceptionTokenizer;

public class AssertionDAO extends BaseHibernateDAO {

	public enum AssertionTypeEnum {
		ACCESS, FORM, EKI;
	}

	protected AssertionTypeEnum type;
	protected BaseForm form;
	protected AppUser user;

	public AssertionDAO(BaseForm form, AppUser user) {
		super(user);
		this.type = AssertionTypeEnum.ACCESS;
		this.form = form;
		this.user = user;
	}

	public AssertionDAO(AssertionTypeEnum type, BaseForm form, AppUser user) {
		super(user);
		this.form = form;
		this.user = user;
		this.type = type;
	}

	public AssertionDAO getPermission() throws Exception {
		try {
			if (type.equals(AssertionTypeEnum.ACCESS)) {
				Query query = getSession()
						.createSQLQuery("select pkg_APP_assertion.assert_access(:userid,:formid) as SEQ from dual")
						.addEntity(SequenceGenerator.class).setParameter("userid", user.getUserId())
						.setParameter("formid", form.getFormCode());
				SequenceGenerator res = (SequenceGenerator) query.uniqueResult();

				if (form.getFormModifier().toLevel() <= Integer.parseInt(res.getSequence())) {
					form.setAccessCode(Integer.parseInt(res.getSequence()));
				} else {
					throw new NoAccessException("You don't have enough access level to view");
				}
			}
		} catch (JDBCException e) {
			if (e.getErrorCode() == 20000) {
				throw new NoAccessException(e.getMessage(), e);
			} else {
				throw new Exception(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1), e);
			}
		}

		return this;
	}

	public void assertPermission() throws JDBCException {
		if (type != AssertionTypeEnum.FORM)
			type = AssertionTypeEnum.FORM;
		this.assertPermission(type, form, user);
	}

	public void assertPermission(Object assertionKey) throws JDBCException {
		form.setAssertionKey(assertionKey);
		this.assertPermission(type, form, user);
	}

	public void assertPermission(AssertionTypeEnum type, BaseForm form, AppUser user) throws JDBCException {
		Query query = null;

		switch (this.type) {
		case FORM:
			query = getSession().createSQLQuery("CALL pkg_APP_assertion.Assert_Form(:P_User_Id,:P_Form_Id,:P_Mod_Req)")
					.setParameter("P_User_Id", user.getUserId()).setParameter("P_Form_Id", form.getFormCode())
					.setParameter("P_Mod_Req", form.getFormModifier().toLevel());
			break;
		default:
			throw new NotImplementedException();
		}
		query.executeUpdate();
	}

	public void inGroup(String groupId) {
		getSession().createSQLQuery("call pkg_app_user.in_group(:userid,:grp)").setParameter("userid", user.getUserId())
				.setParameter("grp", groupId).executeUpdate();
	}
}
