package com.badaklng.app.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.badaklng.app.base.BaseHibernateDAO;
import com.badaklng.app.constant.Constants;
import com.badaklng.app.model.MenuForm;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppMenu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @see com.badaklng.app.hibernate.iss.hibernate.survey.hibernate.AppMenu
 * @author MyEclipse Persistence Tools
 */
public class AppMenuDAO extends BaseHibernateDAO {

	private static final Logger log = Logger.getLogger(AppMenuDAO.class);
	// property constants
	public static final String MENU_NAME = "menuName";
	public static final String MENU_ORDER = "menuOrder";
	public static final String MENU_URL = "menuUrl";
	public static final String MENU_LVL = "menuLvl";
	public static final String MENU_PARENT = "menuParent";
	public static final String CREATE_BY = "createBy";
	public static final String UPDATE_BY = "updateBy";

	public AppMenuDAO() {
		super();
	}

	public AppMenuDAO(AppUser user) {
		super(user);
	}
	
	public List<AppMenu> findAllChildrenOrdered(Integer parent) {
		List<AppMenu> currList = new ArrayList<AppMenu>();
		Criteria criteria = getSession().createCriteria(AppMenu.class);
		criteria.add(Restrictions.eq("menuParent", parent));
//		if(parent != null)
//			criteria.add(Restrictions.eq("menuParent", parent));
//		else
//			criteria.add(Restrictions.isNull("menuParent"));
		criteria.addOrder(Order.asc("menuOrder"));
		for (AppMenu appMenu : (List<AppMenu>) criteria.list()) {
			currList.add(appMenu);
			currList.addAll(findAllChildrenOrdered(appMenu.getMenuId()));
		}
		return currList;
	}

//	@SuppressWarnings("unchecked")
//	public List<AppMenu> findAllParentOrdered() {
//		SQLQuery query = getSession().createSQLQuery("SELECT * FROM APP_MENU " + "START WITH MENU_ID = 0 "
//				+ "CONNECT BY PRIOR MENU_ID = MENU_PARENT " + "ORDER SIBLINGS BY MENU_ORDER");
//		query.addEntity(AppMenu.class);
//		return query.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<AppMenu> generateMenuListByRole(String roleId) {
//		SQLQuery query = getSession()
//				.createSQLQuery("SELECT AM.* FROM " + "(SELECT * FROM APP_MENU " + "START WITH MENU_ID = 0 "
//						+ "CONNECT BY PRIOR MENU_ID = MENU_PARENT " + "ORDER SIBLINGS BY MENU_ORDER) AM "
//						+ "INNER JOIN APP_ROLE_MENU ARM ON ARM.MENU_ID = AM.MENU_ID " + "WHERE ARM.ROLE_ID = ?");
//		query.setParameter(0, roleId);
//		query.addEntity(AppMenu.class);
//		return query.list();
//	}

//	private Boolean menuHaveChild(int menuId) {
//		Criteria cr = getSession().createCriteria(AppMenuView.class);
//		cr.add(Restrictions.eq(MENU_PARENT, menuId));
//		return cr.list().size() > 0 ? true : false;
//	}

	public String generateMenu(AppUser user, Integer parentId) {
		getSession(user);
//		String background = "bg-primary";
		String result = "";
		for (AppMenuView bmv : findViewByParent(parentId)) {
			String child = generateMenu(user, bmv.getMenuId());
			String url = "#";
			if (bmv.getMenuUrl() != null && !bmv.getMenuUrl().equalsIgnoreCase("#"))
				url = "/" + Constants.APPL_CONTEXT.toLowerCase() + bmv.getMenuUrl();
			if (child.length() > 0) {
				result += "<li class='sidebar-item'>";
				result += "<a data-bs-target='#menu" + bmv.getMenuId()
						+ "' data-bs-toggle='collapse' class='sidebar-link collapsed' aria-expanded='false'>";
				if (bmv.getIcon() != null)
					result += "<i class='" + bmv.getIcon() + "'></i> ";
				result += "<span>" + bmv.getMenuName() + "</span></a>";
				result += "<ul id='menu" + bmv.getMenuId() + "' class='sidebar-dropdown list-unstyled collapse'>"
						+ child + "</ul>";
			} else {
				result += "<li class='sidebar-item'>";
				result += "<a class='sidebar-link' href='" + url + "'>";
				if (bmv.getIcon() != null)
					result += "<i class='fa " + bmv.getIcon() + "'></i> ";
				result += "<span>" + bmv.getMenuName() + "</span></a>";
			}

			result += "</li>";
		}
		return result;
	}

//	public String generateMenuXMLByUser(AppUser user) {
//		String ret = "";
//		getSession(user); // set user id in context for findViewByParent
//							// (AppMenuView)
//		for (AppMenuView menu : findViewByParent(0)) {
//			ret += "<li>";
//			if (menu.getMenuUrl().equals("#"))
//				ret += "<a href>";
//			else
//				ret += "<a href=\"/" + Constants.APPL_INITIAL.toLowerCase() + menu.getMenuUrl() + "\">";
//
//			if (menu.getIcon() != null)
//				ret += "<i class=\"" + menu.getIcon() + "\"></i>";
//
//			ret += menu.getMenuName();
//
//			if (menuHaveChild(menu.getMenuId())) {
//				ret += "<i class=\"fa arrow\"></i></a>";
//				ret += recursiveMenuUser(findViewByParent(menu.getMenuId()));
//			} else {
//				ret += "</a>";
//			}
//
//			ret += "</li>";
//		}
//
//		return ret;
//	}
//
//	private String recursiveMenuUser(List<AppMenuView> menus) {
//		String ret = "";
//		ret += "<ul class=\"sidebar-nav collapse\">";
//		for (AppMenuView menu : menus) {
//			ret += "<li>";
//			if (menu.getMenuUrl().equals("#"))
//				ret += "<a href>";
//			else
//				ret += "<a href=\"/" + Constants.APPL_CONTEXT.toLowerCase() + menu.getMenuUrl() + "\">";
//
//			if (menu.getIcon() != null)
//				ret += "<i class=\"" + menu.getIcon() + "\"></i>";
//
//			ret += menu.getMenuName();
//
//			if (menuHaveChild(menu.getMenuId())) {
//				ret += "<i class=\"fa arrow\"></i></a>";
//				ret += recursiveMenuUser(findViewByParent(menu.getMenuId()));
//			} else {
//				ret += "</a>";
//			}
//
//			ret += "</li>";
//		}
//		ret += "</ul>";
//		return ret;
//	}

	/**
	 *
	 * @return Last available Id of APP_MENU
	 */
	public int getLastId() {
		Criteria cr = getSession().createCriteria(AppMenu.class);
		cr.setProjection(Projections.max("menuId"));
		return (Integer) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<AppMenu> findByParent(Integer menuParent) {
		Criteria cr = getSession().createCriteria(AppMenu.class).add(Restrictions.eq("appMenu.menuId", menuParent))
				.addOrder(Order.asc("menuOrder"));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppMenuView> findViewByParent(Integer menuParent) {
		Criteria cr = getSession().createCriteria(AppMenuView.class).add(Restrictions.eq("menuParent", menuParent))
				.addOrder(Order.asc("menuOrder"));
		return cr.list();
	}

	public AppMenu findByParentOrder(Integer menuParent, Integer order) {
		String hql = "from AppMenu where appMenu.menuId = :menupar and menuOrder = :menuor";
		Query query = getSession().createQuery(hql).setParameter("menupar", menuParent).setParameter("menuor", order);
		return (AppMenu) query.uniqueResult();
	}

	public void reorderMenu(Integer menuParent) {
		int counter = 1;
		for (AppMenu menu : findByParent(menuParent)) {
			menu.setMenuOrder(counter++);
			attachDirty(menu);
		}
	}

	public AppMenu findById(Integer integer) {
		log.debug("getting AppMenu instance with id: " + integer);
		try {
			AppMenu instance = (AppMenu) getSession().get(AppMenu.class, integer);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void attachDirty(AppMenu instance) {
		log.debug("attaching dirty AppMenu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void save(AppMenu transientInstance) {
		log.debug("saving AppMenu instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppMenu persistentInstance) {
		log.debug("deleting AppMenu instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void upOrder_pkg(Integer menuId) {
		Query query = getSession().createSQLQuery("call PKG_APP_MENU.UP_ORDER(:menuid)").setParameter("menuid", menuId);
		query.executeUpdate();
	}

	public void downOrder_pkg(Integer menuId) {
		Query query = getSession().createSQLQuery("call PKG_APP_MENU.DOWN_ORDER(:menuid)").setParameter("menuid",
				menuId);
		query.executeUpdate();
	}

	public void reOrder_pkg() {
		Query query = getSession().createSQLQuery("call PKG_APP_MENU.RE_ORDER()");
		query.executeUpdate();
	}

	public void createMenu_pkg(MenuForm form, AppUser user) {
		Query query = getSession().createSQLQuery(
				"call PKG_APP_MENU.CREATE_MENU(:name,:url,:ismenu,:formid,:formname,:icon,:order,:parent,:userid)")
				.setParameter("name", form.getMenuName()).setParameter("url", form.getMenuUrl())
				.setParameter("ismenu", form.getIsMenu() ? 1 : 0).setParameter("formid", form.getFormId())
				.setParameter("formname", form.getFormName()).setParameter("icon", form.getIcon())
				.setParameter("order", form.getMenuOrder()).setParameter("parent", form.getMenuParentId())
				.setParameter("userid", user.getUserId());
		query.executeUpdate();
	}

	public void updateMenu_pkg(MenuForm form, AppUser user) {
		Query query = getSession().createSQLQuery(
				"call PKG_APP_MENU.UPDATE_MENU(:id,:name,:url,:ismenu,:formid,:formname,:icon,:order,:parent,:userid)")
				.setParameter("id", form.getMenuId()).setParameter("name", form.getMenuName())
				.setParameter("url", form.getMenuUrl()).setParameter("ismenu", form.getIsMenu() ? 1 : 0)
				.setParameter("formid", form.getFormId()).setParameter("formname", form.getFormName())
				.setParameter("icon", form.getIcon()).setParameter("order", form.getMenuOrder())
				.setParameter("parent", form.getMenuParentId()).setParameter("userid", user.getUserId());
		query.executeUpdate();
	}

	public void deleteMenu_pkg(MenuForm form) {
		Query query = getSession().createSQLQuery("call PKG_APP_MENU.DELETE_MENU(:id,:withchild)")
				.setParameter("id", form.getMenuId()).setParameter("withchild", form.getWithChild() ? 1 : 0);
		query.executeUpdate();
	}
}
