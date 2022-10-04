package com.badaklng.app.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.EmailListForm;

@Controller
@RequestMapping("/sys/email")
public class EmailController {
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	// Initialization to view users list
	@RequestMapping(method = RequestMethod.GET)
	public String initList(Model model, @ModelAttribute("EmailListForm") EmailListForm form, HttpSession session)
			throws Exception {
		String forward = "emaillistview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		try {
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

//	@RequestMapping(value = "list", params = { "action=SEARCH" }, method = RequestMethod.POST)
//	public String searchList(Model model, @ModelAttribute("EmailListForm") EmailListForm form, HttpSession session)
//			throws Exception {
//		String forward = "emaillistview";
//		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
//		AppMailDAO dao = null;
//		try {
//			new AssertionDAO(AssertionTypeEnum.FORM, form, user).getPermission();
//			dao = new AppMailDAO();
//
//			Map<String, Object> criteria = new HashMap<String, Object>();
//
//			if (!Utility.isEmptyString(form.getAnyKey()))
//				criteria.put("anyKey", form.getAnyKey());
//
//			criteria.put("mailTo", Utility.decorateStringToStartWith(form.getMailTo()));
//
//			criteria.put("mailSubject", Utility.decorateStringToWildcard(form.getSubject()));
//
//			if (!Utility.isEmptyString(form.getStatus()))
//				criteria.put("status", form.getStatus());
//
//			if (!Utility.isEmptyString(form.getMailCat()))
//				criteria.put("mailCat", form.getMailCat());
//
//			form.setMailList(dao.findByCriteria(Utility.stringDateHMToTimestamp(form.getFromDate()),
//					Utility.stringDateHMToTimestamp(form.getToDate()), criteria));
//
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//
//		model.addAttribute(form.getBaseAttrKey(), form);
//		return forward;
//	}
//
//	@RequestMapping(value = "list", params = { "action=RESEND" }, method = RequestMethod.POST)
//	public String resend(Model model, @ModelAttribute("EmailListForm") EmailListForm form, BindingResult result,
//			HttpSession session) throws Exception {
//
//		model.addAttribute(Constants.BASE_FORM_ATTR_KEY, form);
//		String forward = "emaillistview";
//		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
//
//		AppMailDAO dao = null;
//		try {
//			if (!result.hasErrors()) {
//				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
//				LogIntention li = new LogIntention(form, user);
//				dao = new AppMailDAO();
//				dao.begin();
//				if (form.getMailEdit() != null) {
//					for (BaseMailAllV mail : form.getMailEdit()) {
//						if (mail.isChecked()) {
//							li.registerArguments(LogKeyAttributeEnum.ID, mail.getMailId());
//							dao.mailResend(mail.getMailId());
//						}
//					}
//					dao.commit();
//					dao.logActivity(li);
//				} else {
//					form.addMessage("Select email need to resend first !", MessageTypeEnum.WARNING);
//				}
//
//				// get data
//				Map<String, Object> criteria = new HashMap<String, Object>();
//
//				if (!Utility.isEmptyString(form.getAnyKey()))
//					criteria.put("anyKey", form.getAnyKey());
//
//				criteria.put("mailTo", Utility.decorateStringToStartWith(form.getMailTo()));
//
//				criteria.put("mailSubject", Utility.decorateStringToWildcard(form.getSubject()));
//
//				if (!Utility.isEmptyString(form.getStatus()))
//					criteria.put("status", form.getStatus());
//
//				if (!Utility.isEmptyString(form.getMailCat()))
//					criteria.put("mailCat", form.getMailCat());
//
//				form.setMailList(dao.findByCriteria(Utility.dateToTimeStamp(form.getFromDate()),
//						Utility.dateToTimeStamp(form.getToDate()), criteria));
//
//			}
//		} catch (JDBCException e) {
//			if (dao != null)
//				dao.rollback();
//
//			if (e.getErrorCode() != 20000) {
//				throw e;
//			} else
//				form.addMessage(e, MessageTypeEnum.DANGER);
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//		return forward;
//	}
}