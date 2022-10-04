package com.badaklng.app.servlet;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.badak.ldap.LDAPProperty;
import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.SessionAttributeEnum;
import com.badaklng.app.hibernate.AppCat;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppMenu;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.CatalogOrdered;
import com.badaklng.app.model.CatalogPair;
import com.badaklng.app.model.FormPair;
import com.badaklng.app.utilities.Utility;

public class ApplicationListener implements ServletContextListener {

	// private static final Logger logger = Logger
	// .getLogger(ApplicationListener.class);

	private static final Logger logger = Logger.getLogger(ApplicationListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		AppDAO appDAO = null;
		CatalogOrdered co = new CatalogOrdered();
		CatalogPair cp = new CatalogPair();
		FormPair fp = new FormPair();
		String ttyp = null;
		Hashtable<String, AppCat> catMaps = null;
		ArrayList<AppCat> catList = null;

		try {
			appDAO = new AppDAO();

			// initialize Catalog
			for (AppCat cat : (List<AppCat>) appDAO.findCatAll()) {
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

			arg0.getServletContext().setAttribute(cp.getModelAttrKey(), cp);
			arg0.getServletContext().setAttribute(co.getModelAttrKey(), co);

			// initialize Menu
			for (AppMenu m : (List<AppMenu>) appDAO.findMenuAll()) {
				if (m.getFormId() != null) {
					fp.setCode(m.getFormId(), (m.getFormName().isEmpty() ? m.getMenuName() : m.getFormName()));
				}
			}
			arg0.getServletContext().setAttribute(fp.getModelAttrKey(), fp);

//			if (Constants.CORE_INSTANCE) {
				LDAPProperty prop0 = new LDAPProperty();
				LDAPProperty prop1 = new LDAPProperty();

				prop0.load(getClass().getClassLoader().getResourceAsStream("ldap0.properties"));
				arg0.getServletContext().setAttribute(SessionAttributeEnum.BTG_USER_ORGANIZATION.toString(), prop0);

				prop1.load(getClass().getClassLoader().getResourceAsStream("ldap1.properties"));
				arg0.getServletContext().setAttribute(SessionAttributeEnum.JKT_USER_ORGANIZATION.toString(), prop1);

				appDAO.logAppActivity(logger, "Application-Context Initialized", true);
				if (!Constants.IS_DEV_MODE)
					Utility.notify(Constants.APPLICATION_NAME + " is now go-live");
//			} else {
//				if (!Constants.IS_DEV_MODE)
//					Utility.notify("[NON-Core] " + Constants.APPLICATION_NAME + " is now go-live");
//			}

		} catch (Exception e) {
			appDAO.logAppActivity(logger, Constants.APPL_SHORTNAME + " Application-Context Failed", true);
			logger.error(Constants.APPL_SHORTNAME + " context initialization failed", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
