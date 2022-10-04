/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badaklng.app.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.JDBCException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.LogForm;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.hibernate.HibernateSessionFactory;

/**
 *
 * @author NN6ZZN2212
 */
@Controller
@RequestMapping("/sys")
public class LogController {

	@RequestMapping(value = "log", method = RequestMethod.GET)
	public String initList(Model model, @ModelAttribute("LogForm") LogForm form, HttpSession session)
			throws Exception {
		String forward = "logview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}
	
	@RequestMapping(value = "log/ajax")
    @ResponseBody
    public DataResponse getLog(LogForm form, HttpSession session) {
        DataResponse res = new DataResponse(form.getDraw());
        AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
        AppDAO logDAO = null;

        try {
            new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
            logDAO = new AppDAO();
            res.setData(logDAO.findLogByDuration(form, res));
        } catch (JDBCException e) {
            res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return res;
    }
}
