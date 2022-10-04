package com.badaklng.app.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.JDBCException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.hibernate.AppCat;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppMenu;
import com.badaklng.app.hibernate.AppMenuDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.CatalogOrdered;
import com.badaklng.app.model.CatalogPair;
import com.badaklng.app.model.DashboardForm;
import com.badaklng.app.model.FormForm;
import com.badaklng.app.model.FormPair;
import com.badaklng.app.utilities.ExceptionTokenizer;

@Controller
@RequestMapping("/sys")
public class SystemController {

	@RequestMapping(value = "init/form", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse initForm(FormForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			form.setFormModifier(FormModifierEnum.SYSTEM);
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			dao = new AppDAO();
			FormPair fp = new FormPair();
			for (AppMenu m : (List<AppMenu>) dao.findMenuAll()) {
				if (m.getFormId() != null) {
					fp.setCode(m.getFormId(), (m.getFormName().isEmpty() ? m.getMenuName() : m.getFormName()));
				}
			}
			session.getServletContext().setAttribute(fp.getModelAttrKey(), fp);
			ret = new AjaxResponse(true, "Initialize form name complete");
		} catch (JDBCException e) {
			ret = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "init/catalog", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse initCatalog(FormForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		CatalogOrdered co = new CatalogOrdered();
		CatalogPair cp = new CatalogPair();
		Hashtable<String, AppCat> catMaps = null;
		ArrayList<AppCat> catList = null;
		String ttyp = null;

		try {
			form.setFormModifier(FormModifierEnum.SYSTEM);
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			dao = new AppDAO();

			for (AppCat cat : (List<AppCat>) dao.findCatAll()) {
				if (ttyp == null || !ttyp.equalsIgnoreCase(cat.getId().getTtyp())) {
					ttyp = cat.getId().getTtyp();
					catMaps = new Hashtable<String, AppCat>();
					catList = new ArrayList<AppCat>();
					cp.setCode(ttyp, catMaps);
					co.setCode(ttyp, catList);
				}
				catMaps.put(cat.getId().getTcode(), cat);
				catList.add(cat);
			}

			session.getServletContext().setAttribute(cp.getModelAttrKey(), cp);
			session.getServletContext().setAttribute(co.getModelAttrKey(), co);

			ret = new AjaxResponse(true, "Initialize catalog complete");
		} catch (JDBCException e) {
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "init/menu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse initMenu(DashboardForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppMenuDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			form.setFormModifier(FormModifierEnum.READ);
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			dao = new AppMenuDAO();
			if (user != null) {

				String menuHTML = new String();
				menuHTML = dao.generateMenu(user, 0);
				session.setAttribute(new AppMenu().getModelAttrKey(), menuHTML);
			}
			ret = new AjaxResponse(true, "Re-Initialize menu success");
		} catch (JDBCException e) {
			ret = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}
}
