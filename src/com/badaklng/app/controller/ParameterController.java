/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badaklng.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppParameter;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.ParameterForm;
import com.badaklng.app.hibernate.HibernateSessionFactory;

/**
 *
 * @author NN6ZZN2212
 */
@Controller
@RequestMapping("/sys")
public class ParameterController {

	@RequestMapping(value = "/parameter", method = RequestMethod.GET)
	public String initParam(Model model, @ModelAttribute("ParameterForm") ParameterForm form, HttpSession session)
			throws Exception {
		String forward = "parameterview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "/parameter/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<AppParameter> getParameters() {
		List<AppParameter> result = null;
		AppDAO paramDAO = null;

		try {
			paramDAO = new AppDAO();
			result = paramDAO.findParameterAll();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

	@RequestMapping(value = "/parameter/setvalue", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse setParamValue(ParameterForm form, HttpSession session) {
		AppDAO paramDAO = null;
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AjaxResponse ret;

		try {
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			paramDAO = new AppDAO();
			AppParameter param = paramDAO.getParameterById(form.getParamKey());
			param.setParamValue(form.getValue());
			paramDAO.begin();
			paramDAO.attachDirty(param);
			paramDAO.commit();
			ret = new AjaxResponse(true);
		} catch (Exception e) {
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return ret;
	}
}
