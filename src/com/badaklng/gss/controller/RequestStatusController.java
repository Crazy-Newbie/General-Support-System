package com.badaklng.gss.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.badaklng.app.constant.MessageTypeEnum;
//import com.badaklng.app.base.BaseForm;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.utilities.Utility;
import com.badaklng.gss.hibernate.GssMainDAO;
import com.badaklng.gss.hibernate.RequestView;
import com.badaklng.gss.model.RequestStatusForm;

@Controller
@RequestMapping("/request")
public class RequestStatusController {

	@RequestMapping(value = "status/{reqId}", method = RequestMethod.GET)
	public String getRequestStatusList(Model model, @ModelAttribute("RequestStatusForm") RequestStatusForm form,
			HttpSession session) throws Exception {
		if (Utility.isEmptyString(form.getReqId())) {
			form.addMessage(MessageTypeEnum.WARNING, "Request ID not found");
			model.addAttribute(form.getAttrKey(), form);
			return "errormin";
		}

		String forward = "requeststatuslistview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			model.addAttribute("logs", dao.findRequestStatusViewByReqId(form.getReqId()));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "statusform", method = RequestMethod.GET)
	public String getRequestStatusForm(Model model, @ModelAttribute("RequestStatusForm") RequestStatusForm form,
			HttpSession session) throws Exception {
		if (Utility.isEmptyString(form.getReqId()))
			return "redirect:/request/list.do";
		String forward = "requeststatusformview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			RequestView request = dao.getReqViewById(form.getReqId());
			if (request == null || Utility.in(request.getStatus(), "CANC", "CMPL")) {
				forward = "redirect:/request/list.do";
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "statusform", method = RequestMethod.POST, params = "action=CREATE")
	public String postRequestStatusFormCreate(Model model, @ModelAttribute("RequestStatusForm") RequestStatusForm form,
			HttpSession session) throws Exception {
		String forward = "requeststatusformview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			dao.begin();
			dao.createRequestStatus(form, user);
			dao.commit();
			forward = "redirect:/request/list.do";
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

}
