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
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppRole;
import com.badaklng.app.hibernate.AppRoleMenu;
import com.badaklng.app.hibernate.AppRoleMenuView;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.model.Message;
import com.badaklng.app.model.RoleForm;
import com.badaklng.app.model.RoleListForm;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;
import com.badaklng.app.hibernate.HibernateSessionFactory;

@Controller
@RequestMapping(value = "/sys/role")
public class RoleController {

	@InitBinder(value = { "RoleForm" })
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new RoleValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("RoleListForm") RoleListForm form, HttpSession session)
			throws Exception {
		String forward = "roleview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model, @ModelAttribute("RoleForm") RoleForm form, HttpSession session) throws Exception {
		String forward = "roleform";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO roleDAO = null;

		try {
			new AssertionDAO(form, user).getPermission();
			roleDAO = new AppDAO();
			AppRole role = null;

			if (form.getRoleId() != null)
				role = roleDAO.findRoleById(form.getRoleId());

			if (role != null) {
				form.setRoleId(role.getRoleId());
				form.setRoleName(role.getRoleDesc());
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST, params = "action=CREATE")
	public String roleFormCreate(Model model, @ModelAttribute("RoleForm") @Validated RoleForm form,
			BindingResult result, HttpSession session) {
		String forward = "redirect:/sys/role.do";
		AppDAO roleDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				roleDAO = new AppDAO();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.ROLE, form.getRoleId().toUpperCase());
				roleDAO.begin();
				AppRole role = new AppRole(form.getRoleId().toUpperCase(), form.getRoleName().toUpperCase(),
						user.getUserId(), Utility.getNow());
				roleDAO.attachDirty(role);
				roleDAO.commit();
				roleDAO.logActivity(li);
			} else {
				model.addAttribute(form.getAttrKey(), form);
				forward = "roleform";
			}
		} catch (HibernateException e) {
			if (roleDAO != null)
				roleDAO.rollback();
			form.getMessages().add(new Message(MessageTypeEnum.DANGER,
					"Error : " + ExceptionTokenizer.getMessageSegment(e.getMessage(), 1)));
			model.addAttribute(form.getAttrKey(), form);
			forward = "roleform";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return forward;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST, params = "action=UPDATE")
	public String roleFormUpdate(Model model, @ModelAttribute("RoleForm") @Validated RoleForm form,
			BindingResult result, HttpSession session) {
		String forward = "redirect:/sys/role.do";
		AppDAO roleDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		// List<Message> msg = form.getMessages();

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				roleDAO = new AppDAO();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.ROLE, form.getRoleId().toUpperCase());
				roleDAO.begin();
				AppRole role = roleDAO.findRoleById(form.getRoleId().toUpperCase());
				role.setRoleDesc(form.getRoleName().toUpperCase());
				roleDAO.attachDirty(role);
				roleDAO.commit();
				roleDAO.logActivity(li);
			} else {
				model.addAttribute(form.getAttrKey(), form);
				forward = "roleform";
			}
		} catch (HibernateException e) {
			if (roleDAO != null)
				roleDAO.rollback();
			form.getMessages().add(new Message(MessageTypeEnum.DANGER,
					"Error : " + ExceptionTokenizer.getMessageSegment(e.getMessage(), 1)));
			model.addAttribute(form.getAttrKey(), form);
			forward = "roleform";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return forward;
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String roleMenu(Model model, @ModelAttribute("RoleForm") RoleForm form, HttpSession session)
			throws Exception {
		String forward = "rolemenuform";
		AppDAO roleDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
			roleDAO = new AppDAO();

			if (!Utility.isEmptyString(form.getRoleId())) {
				AppRole role = roleDAO.findRoleById(form.getRoleId());
				if (role != null) {
					form.setRole(role);
					List<AppRoleMenuView> view = roleDAO.findAppRoleMenu(form.getRoleId(),0);
					form.setAppRoleMenuView(view);
					model.addAttribute(new AppRoleMenuView().getModelListAttrKey(), view);
					form.setFormModifier(FormModifierEnum.UPDATE);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String roleMenu(Model model, @ModelAttribute("RoleForm") @Validated RoleForm form, BindingResult result,
			HttpSession session) throws Exception {
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				dao = new AppDAO();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.ROLE, form.getRoleName());

				dao.begin();
				for (AppRoleMenu menu : form.getAppRoleMenu()) {
					if (menu.getAccessCode() == 0 && !menu.getIsShowed())
						dao.delete(menu);
					else
						dao.attachDirty(menu);
				}
				dao.commit();
				dao.logActivity(li);
				form.getMessages().add(new Message(MessageTypeEnum.SUCCESS, "Updating Role Menu Success"));
			}
		} catch (HibernateException e) {
			if (dao != null)
				dao.rollback();
			form.getMessages()
					.add(new Message(MessageTypeEnum.DANGER, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1)));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return roleMenu(model, form, session);
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST, params = "action=DELETE")
	@ResponseBody
	public AjaxResponse roleFormDelete(@ModelAttribute("RoleForm") RoleForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			LogIntention li = new LogIntention(user, form);
			if (!Utility.isEmptyString(form.getRoleId())) {
				dao = new AppDAO();
				AppRole role = dao.findRoleById(form.getRoleId());
				li.registerArguments(LogKeyAttributeEnum.ROLE, role.getRoleDesc());
				dao.begin();
				dao.delete(role);
				dao.commit();
				dao.logActivity(li);
				ret = new AjaxResponse(true, "Delete role success");
			} else
				ret = new AjaxResponse(false, "Delete role failed : RoleId empty");
		} catch (JDBCException e) {
			if (dao != null)
				dao.rollback();
			ret = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}
}
