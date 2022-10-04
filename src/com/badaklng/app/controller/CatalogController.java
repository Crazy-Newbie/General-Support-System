package com.badaklng.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.hibernate.AppCat;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.CatalogListForm;

@Controller
@RequestMapping("/sys/catalog")
public class CatalogController {

	@RequestMapping(method = RequestMethod.GET)
	public String catalogList(Model model, @ModelAttribute("CatalogListForm") CatalogListForm form, HttpSession session)
			throws Exception {
		String forward = "cataloglistform";
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AppDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new AppDAO();
			form.setAppCats(dao.findCatAll());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String catalogListPost(Model model, @ModelAttribute("CatalogListForm") CatalogListForm form,
			HttpSession session) throws Exception {
		String forward = null;
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AppDAO dao = null;

		try {
			new AssertionDAO(form, user).assertPermission();
			dao = new AppDAO();
			dao.begin();
			for (AppCat ac : form.getAppCats()) {
				if (ac.getId() != null && ac.getId().getTtyp() != null && ac.getId().getTcode() != null) {
					if (!ac.getDelete())
						dao.saveOrUpdate(ac);
					else
						dao.delete(ac);
				}
			}
			dao.commit();
			form.addMessage(MessageTypeEnum.SUCCESS, "Update Catalog Success");
			forward = "redirect:/sys/catalog.do";
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(e);
			forward = catalogList(model, form, session);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return forward;
	}
}
