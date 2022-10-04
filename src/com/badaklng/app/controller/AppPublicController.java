package com.badaklng.app.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.AjaxResponse;

@Controller
@RequestMapping("/public")
public class AppPublicController {

    private static final Logger logger = Logger.getLogger(AppPublicController.class);

    @RequestMapping(value = "counter")
    @ResponseBody
	public AjaxResponse getActivityCounter() {
		AjaxResponse res = null;
		AppDAO dao = null;

		try {
			dao = new AppDAO();
			Integer ret = dao.getTodayLog();
			res = new AjaxResponse(true);
			res.setData("count", ret.toString());
		} catch (Exception e) {
			res = new AjaxResponse(false);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}
}
