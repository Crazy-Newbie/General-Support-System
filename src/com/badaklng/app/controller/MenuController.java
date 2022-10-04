package com.badaklng.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.hibernate.AppMenu;
import com.badaklng.app.hibernate.AppMenuDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.model.MenuForm;
import com.badaklng.app.model.MenuListForm;
import com.badaklng.app.model.Message;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.hibernate.HibernateSessionFactory;

@Controller
@RequestMapping("/sys/menu")
public class MenuController {

	@InitBinder(value = { "MenuForm" })
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new MenuValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String menuList(Model model, @ModelAttribute("MenuListForm") MenuListForm form, HttpSession session)
			throws Exception {
		String forward = "menuview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String menuForm(Model model, @ModelAttribute("MenuForm") MenuForm form, HttpSession session)
			throws Exception {
		String forward = "menuform";
		AppMenuDAO menuDAO = null;
		AppMenu menu = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
			menuDAO = new AppMenuDAO();

			if (form.getMenuId() != null && !form.getMenuId().equals(-1)) {
				menu = menuDAO.findById(form.getMenuId());
				if (menu != null) {
					form.setMenuForm(menu, menuDAO.findById(menu.getMenuParent()));
					form.setFormModifier(FormModifierEnum.UPDATE);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=CREATE")
	public String menuFormCreate(Model model, @ModelAttribute("MenuForm") @Validated MenuForm form,
			BindingResult result, HttpSession session) throws Exception {
		String forward = "redirect:/sys/menu.do";

		AppMenuDAO menuDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				menuDAO = new AppMenuDAO();
				LogIntention li = new LogIntention(user, form);

				menuDAO.begin();
				menuDAO.createMenu_pkg(form, user);
				menuDAO.commit();
				menuDAO.logActivity(li);
				form.getMessages().add(new Message(MessageTypeEnum.SUCCESS, form.getAction() + " Menu Success"));
			} else {
				forward = "menuform";
			}
		} catch (JDBCException e) {
			if (menuDAO != null)
				menuDAO.rollback();
			form.addMessage(e);
			forward = "menuform";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=UPDATE")
	public String menuFormUpdate(Model model, @ModelAttribute("MenuForm") @Validated MenuForm form,
			BindingResult result, HttpSession session) {
		String forward = "redirect:/sys/menu.do";

		AppMenuDAO menuDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		List<Message> msg = form.getMessages();

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				menuDAO = new AppMenuDAO();
				LogIntention li = new LogIntention(user, form);
				menuDAO.begin();
				menuDAO.updateMenu_pkg(form, user);
				menuDAO.commit();
				menuDAO.logActivity(li);
				form.getMessages().add(new Message(MessageTypeEnum.SUCCESS, form.getAction() + " Menu Success"));
			} else {
				forward = "menuform";
			}
		} catch (HibernateException e) {
			msg.add(new Message(MessageTypeEnum.WARNING, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1)));
			forward = "menuform";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=DELETE")
	@ResponseBody
	public AjaxResponse menuFormDelete(Model model, @ModelAttribute("MenuForm") @Validated MenuForm form,
			BindingResult result, HttpSession session) {
		AjaxResponse res = null;
		AppMenuDAO menuDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				menuDAO = new AppMenuDAO();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.MENU, form.getMenuId().toString());
				menuDAO.begin();
				menuDAO.deleteMenu_pkg(form);
				menuDAO.commit();
				menuDAO.logActivity(li);
				res = new AjaxResponse(true);
			}
		} catch (JDBCException e) {
			if (menuDAO != null)
				menuDAO.rollback();
			res = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return res;
	}

	@RequestMapping(value = "uporder", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse incOrder(MenuForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppMenuDAO menuDAO = null;

		try {
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			if (form.getMenuId() != null) {
				menuDAO = new AppMenuDAO();
				menuDAO.begin();
				menuDAO.upOrder_pkg(form.getMenuId());
				menuDAO.commit();
				ret = new AjaxResponse(true, "Re-ordering Success");
			} else {
				ret = new AjaxResponse(false, "Re-Ordering Failed. Menu ID not correctly supplied");
			}
		} catch (Exception e) {
			if (menuDAO != null)
				menuDAO.rollback();
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "downorder", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse decrOrder(MenuForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppMenuDAO menuDAO = null;

		try {
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			if (form.getMenuId() != null) {
				menuDAO = new AppMenuDAO();
				menuDAO.begin();
				menuDAO.downOrder_pkg(form.getMenuId());
				menuDAO.commit();
				ret = new AjaxResponse(true, "Re-ordering Success");
			} else {
				ret = new AjaxResponse(false, "Re-Ordering Failed. Menu ID not correctly supplied");
			}
		} catch (Exception e) {
			if (menuDAO != null)
				menuDAO.rollback();
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

//	@RequestMapping(value = "deletemenu", method = RequestMethod.POST)
//	@ResponseBody
//	public AjaxResponse delete(@RequestParam Integer menuId, HttpSession session) {
//		AjaxResponse ret = null;
//		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
//		AppMenuDAO menuDAO = null;
//
//		try {
//			MenuForm form = new MenuForm();
//			form.setFormModifier(FormModifierEnum.DELETE);
//			LogIntention li = new LogIntention(user, form);
//			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
//			menuDAO = new AppMenuDAO();
//			AppMenu menu = menuDAO.findById(menuId);
//			if (menu != null) {
//				li.registerArguments(LogKeyAttributeEnum.MENU, menu.getMenuName());
//				menuDAO.begin();
//				if (menu.getAppMenus().size() > 0) {
//					for (AppMenu mn : menu.getAppMenus()) {
//						mn.setMenuParent(menu.getMenuParent());
//						menuDAO.attachDirty(mn);
//					}
//				}
//				menuDAO.delete(menu);
//				menuDAO.reorderMenu(menu.getAppMenu().getMenuId());
//				menuDAO.commit();
//				menuDAO.logActivity(li);
//				ret = new AjaxResponse(true, "Delete menu success");
//			} else {
//				ret = new AjaxResponse(false, "Delete menu failed");
//			}
//		} catch (JDBCException e) {
//			ret = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//
//		return ret;
//	}
}
