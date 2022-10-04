package com.badaklng.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.LogIntention;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model, HttpSession session) throws Exception {
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		LogIntention li = new LogIntention(user);
		li.setType("LOGIN_FORM.LOGOUT");
		li.registerArguments(LogKeyAttributeEnum.USERNAME, user.getUserId());
		li.registerArguments(LogKeyAttributeEnum.LDAPUSER, user.getLdapUser());
		BaseHibernateDAO dao = new BaseHibernateDAO(user);
		dao.logActivity(li);
		HibernateSessionFactory.closeSession();

		session.removeAttribute(user.getModelAttrKey());
		session.invalidate();
		return "redirect:/login.do";
//		RedirectView redir = new RedirectView();
//		redir.setUrl("http://www.badaklng.com");
//		return redir;
	}

}