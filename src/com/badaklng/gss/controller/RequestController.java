package com.badaklng.gss.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppGroup;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.utilities.Utility;
import com.badaklng.gss.hibernate.GssMainDAO;
import com.badaklng.gss.hibernate.Request;
import com.badaklng.gss.model.RequestForm;
import com.badaklng.gss.model.RequestListForm;

@Controller
@RequestMapping("/request")
public class RequestController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getRequestList(Model model, @ModelAttribute("RequestListForm") RequestListForm form,
			HttpSession session) throws Exception {
		String forward = "requestlistview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;
		
		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			model.addAttribute("teams", dao.findTeamAll());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "list/data", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse getRequestListData(RequestListForm form, HttpSession session) {
		DataResponse res = new DataResponse(form.getDraw());
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			res.setData(dao.findRequestViewByForm(form, res));
		} catch (Exception e) {
			res.setError(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return res;
	}

	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String getRequestForm(Model model, @ModelAttribute("RequestForm") RequestForm form, HttpSession session)
			throws Exception {
		String forward = "requestformview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			if (!Utility.isEmptyString(form.getReqId())) {
				Request request = dao.getReqById(form.getReqId());
				System.out.println(form.getReqId());
				if (request != null) {
					System.out.println(request.getReqId());
					form.setData(request);
					form.setFormModifier(FormModifierEnum.UPDATE);
				}
			}
			model.addAttribute("teams", dao.findTeamAll());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;

	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=CREATE")
	public String postRequestFormCreate(Model model, @ModelAttribute("RequestForm") RequestForm form,
			HttpSession session) throws Exception {
		String forward = null;
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			dao.begin();
			dao.createRequest_pkg(form, user);
			dao.commit();
			forward = "redirect:/request/list.do";

		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(e);
			forward = getRequestForm(model, form, session);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=UPDATE")
	public String postRequestFormUpdate(Model model, @ModelAttribute("RequestForm") RequestForm form,
			HttpSession session) throws Exception {
		String forward = null;
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			dao.begin();
			dao.updateRequest_pkg(form, user);
			dao.commit();
			forward = "redirect:/request/list.do";

		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(e);
			forward = getRequestForm(model, form, session);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

}
