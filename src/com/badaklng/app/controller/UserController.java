package com.badaklng.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppOrganization;
import com.badaklng.app.hibernate.AppRole;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AppUserDAO;
import com.badaklng.app.hibernate.AppUserRole;
import com.badaklng.app.hibernate.AppUserRoleId;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.model.UserForm;
import com.badaklng.app.model.UserListForm;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;
import com.badaklng.app.hibernate.HibernateSessionFactory;

@Controller
@RequestMapping("/sys")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@InitBinder(value = { "UserForm" })
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("UserListForm") UserListForm form, HttpSession session)
			throws Exception {
		String forward = "userview";
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "user/ajax", method = RequestMethod.GET)
	@ResponseBody
	public DataResponse listAjax(@ModelAttribute("UserListForm") UserListForm form, HttpSession session,
			HttpServletRequest request) {
		DataResponse res = new DataResponse(form.getDraw());
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AppUserDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new AppUserDAO();
			res.setData(dao.findByKeyword(form, res));
		} catch (Exception e) {
			res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "user/form", method = RequestMethod.GET)
	public String user(Model model, @ModelAttribute("UserForm") UserForm form, HttpSession session) throws Exception {
		String forward = "userform";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUserDAO userDAO = null;
		AppDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new AppDAO();
			userDAO = new AppUserDAO();

			if (!Utility.isEmptyString(form.getUserId())) {
				AppUser usr = userDAO.findById(form.getUserId());
				if (usr != null) {
					form.setUser(usr);
					form.setFormModifier(FormModifierEnum.UPDATE);
				} else {
					form.setUserId("");
				}
			}

			model.addAttribute(new AppRole().getModelListAttrKey(), dao.findRoleAll());
			model.addAttribute(new AppOrganization().getModelListAttrKey(), dao.findOrganization());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "user/form", method = RequestMethod.POST, params = "action=CREATE")
	public String userCreate(Model model, @ModelAttribute("UserForm") @Validated UserForm form, BindingResult result,
			HttpSession session) throws Exception {
		String forward = "userform";
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AppUserDAO userDAO = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getUserId());
				li.registerArguments(LogKeyAttributeEnum.WHO, form.getName());
				userDAO = new AppUserDAO();
				userDAO.begin();
				userDAO.save_pkg(form, user);
				for (String role : form.getRoles()) {
					userDAO.assignRole_pkg(form.getUserId(), role, user);
				}
				userDAO.commit();
				userDAO.logActivity(li);
				form.addMessage(MessageTypeEnum.SUCCESS, form.getAction() + " User Success");
				form.reset();
				forward = user(model, form, session);
			}
		} catch (Exception e) {
			if (userDAO != null) {
				userDAO.rollback();
			}
			form.addMessage(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "user/form", method = RequestMethod.POST, params = "action=UPDATE")
	public String userUpdate(Model model, @ModelAttribute("UserForm") @Validated UserForm form, BindingResult result,
			HttpSession session) throws Exception {
		String forward = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUserDAO userDAO = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getUserId());
				li.registerArguments(LogKeyAttributeEnum.WHO, form.getName());
				userDAO = new AppUserDAO();
				userDAO.begin();
				userDAO.update_pkg(form, user);
				userDAO.commit();
				userDAO.logActivity(li);
				forward = "redirect:/sys/user.do";
			}
		} catch (Exception e) {
			if (userDAO != null) {
				userDAO.rollback();
			}
			form.addMessage(e);
			forward = "userform";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "user/form", method = RequestMethod.POST, params = "action=DELETE")
	@ResponseBody
	public AjaxResponse deleteUsers(UserForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		AppUserDAO userDAO = null;
		try {
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			LogIntention li = new LogIntention(user, form);
			li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getUserId());
			userDAO = new AppUserDAO();
			userDAO.begin();
			userDAO.deleteUser_pkg(form.getUserId());
			userDAO.commit();
			userDAO.logActivity(li);
			ret = new AjaxResponse(true, "Delete user Success");
		} catch (Exception e) {
			if (userDAO != null) {
				userDAO.rollback();
			}
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "user/role", method = RequestMethod.GET)
	public String initRole(Model model, @ModelAttribute("UserForm") UserForm form, HttpSession session)
			throws Exception {
		String forward = "userroleview";
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new AppDAO();
			model.addAttribute(new AppRole().getModelListAttrKey(), dao.findRoleAll());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "user/role/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<AppUserRole> getUserRole(@RequestParam String userId) {
		List<AppUserRole> ret = new ArrayList<AppUserRole>();
		AppDAO dao = null;

		try {
			dao = new AppDAO();
			if (!Utility.isEmptyString(userId)) {
				ret = dao.findByUser(userId);
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return ret;
	}

	@RequestMapping(value = "user/role", method = RequestMethod.POST, params = "action=ADD")
	@ResponseBody
	public AjaxResponse grantRole(@RequestParam String userId, @RequestParam String roleId, HttpSession session) {
		AjaxResponse ret = null;
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			UserForm form = new UserForm();
			form.setFormModifierEnum(FormModifierEnum.ADD);
			dao = new AppDAO();
			LogIntention li = new LogIntention(user, form);
			li.registerArguments(LogKeyAttributeEnum.USER_ID, userId);
			li.registerArguments(LogKeyAttributeEnum.ROLE, roleId);

			dao.begin();
//			dao.attachDirty(
//				new AppUserRole(new AppUserRoleId(userId, roleId), null, null, user.getUserId(), Utility.getNow()));
			dao.addUserRole_pkg(userId, roleId, user);
			dao.commit();
			dao.logActivity(li);
			ret = new AjaxResponse(true, "Grant Role Success");
		} catch (HibernateException e) {
			if (dao != null)
				dao.rollback();
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "user/role", method = RequestMethod.POST, params = "action=REMOVE")
	@ResponseBody
	public AjaxResponse revokeRole(@RequestParam String userId, @RequestParam String roleId, HttpSession session) {
		AjaxResponse ret = null;
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			UserForm form = new UserForm();
			form.setFormModifierEnum(FormModifierEnum.REMOVE);
			dao = new AppDAO();
			LogIntention li = new LogIntention(user, form);
			li.registerArguments(LogKeyAttributeEnum.USER_ID, userId);
			li.registerArguments(LogKeyAttributeEnum.ROLE, roleId);

			AppUserRole aur = dao.findById(new AppUserRoleId(userId, roleId));

			if (aur != null) {
				dao.begin();
				dao.delete(aur);
				dao.commit();
				dao.logActivity(li);
				ret = new AjaxResponse(true, "Revoke Role Success");
			} else
				ret = new AjaxResponse(false, "User Role not found");
		} catch (HibernateException e) {
			if (dao != null)
				dao.rollback();
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

}
