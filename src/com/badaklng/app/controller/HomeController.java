package com.badaklng.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.exception.NoAccessException;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppMenu;
import com.badaklng.app.hibernate.AppMenuDAO;
import com.badaklng.app.hibernate.AppNotification;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AppUserDAO;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.DashboardForm;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.model.Message;
import com.badaklng.app.model.NotificationForm;
import com.badaklng.app.model.PasswordForm;
import com.badaklng.app.model.UserForm;
import com.badaklng.app.utilities.ExceptionTokenizer;

@Controller
@RequestMapping("/*")
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String initDashboard(Model model, @ModelAttribute("DashboardForm") DashboardForm form, HttpSession session)
			throws Exception {

		model.addAttribute(form.getAttrKey(), form);
		return "dashboard";
	}

	@RequestMapping(value = "password", method = RequestMethod.GET)
	public String initPassword(Model model, @ModelAttribute("PasswordForm") PasswordForm form, HttpSession session)
			throws Exception {
		String forward = "passwordform";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();

			if (user.getIsLdap()) {
				throw new NoAccessException("Cannot change password for GroupWise user. Use GroupWise client instead");
			} else {
				model.addAttribute("isAdmin", new AppUserDAO().inGroup(user.getUserId(), "ADMIN"));
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "password", method = RequestMethod.POST)
	public String password(Model model, @ModelAttribute("PasswordForm") PasswordForm form, HttpSession session)
			throws Exception {
		String forward = "passwordform";
		AppUserDAO userDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			form.setFormModifier(FormModifierEnum.UPDATE);
			userDAO = new AppUserDAO();
			LogIntention li = new LogIntention(user, form);

			try {

				// if user non-ldap valid then change password
				userDAO.begin();
				userDAO.changePassword(user.getUserId(), form.getEmployeeId().toUpperCase(), form.getNewPass(),
						form.getNewPassConf());
				li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getEmployeeId().toUpperCase());
				userDAO.commit();
				userDAO.logActivity(li);
				form.reset();
				form.getMessages().add(new Message(MessageTypeEnum.SUCCESS, "Change Password Success"));
			} catch (JDBCException e) {
				if (e.getErrorCode() != 20000) {
					throw e;
				} else {
					form.addMessage(e);
				}
			}

		} catch (Exception e) {
			form.getMessages().add(new Message(MessageTypeEnum.SUCCESS, "Change Password Failed : " + e.getMessage()));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "switch", method = RequestMethod.GET)
	public String initSwitchUser(Model model, @ModelAttribute("UserForm") UserForm form, HttpSession session)
			throws Exception {
		String forward = "switch";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		try {
			new AssertionDAO(form, user.getOriginalUser() != null ? user.getOriginalUser() : user).getPermission();
			form.setUserId(user.getUserId());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "switch", method = RequestMethod.POST)
	public String switchUser(Model model, @ModelAttribute("UserForm") UserForm form, HttpSession session) {
		String forward = "redirect:/dashboard.do";
		AppMenuDAO menuDAO = null;
		AppUserDAO userDAO = null;
		// AppPersonDAO persDAO = null;
		AppUser current = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUser original = current.getOriginalUser(), newUser = null, finalUser = null;

		// addition, to prevent user to switch to themselves
		if (current.getUserId().equals(form.getUserId())) {
			form.addMessage(MessageTypeEnum.WARNING, "Your user account already similar with selected user account");
			model.addAttribute(form.getAttrKey(), form);
			return "switch";
		}

		try {
			userDAO = new AppUserDAO();
			menuDAO = new AppMenuDAO();
			LogIntention li = new LogIntention(current);
			String menuHTML = new String();

			if (original != null) {
				finalUser = original;
			} else {
				finalUser = current;
			}

			if (original == null) {
				newUser = userDAO.findById(form.getUserId());
//				Hibernate.initialize(newUser);
				newUser.setInfo(userDAO.findEmployeeLkpInfo(newUser.getUserId()));
				newUser.setOriginalUser(finalUser);

				// additional
				newUser.setIpAddress(finalUser.getIpAddress());
				session.setAttribute(newUser.getModelAttrKey(), newUser);
				menuHTML = menuDAO.generateMenu(newUser, 0);
			} else {
				if (original.getUserId().equalsIgnoreCase(form.getUserId())) {
					session.setAttribute(original.getModelAttrKey(), original);
					menuHTML = menuDAO.generateMenu(original, 0);
					newUser = original;
				} else {
					newUser = userDAO.findById(form.getUserId());
//					Hibernate.initialize(newUser);
					newUser.setInfo(userDAO.findEmployeeLkpInfo(newUser.getUserId()));
					newUser.setOriginalUser(original);
					newUser.setIpAddress(finalUser.getIpAddress());
					session.setAttribute(newUser.getModelAttrKey(), newUser);
					menuHTML = menuDAO.generateMenu(newUser, 0);
				}
			}

			li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getUserId());
			userDAO.logActivity(li);
			session.setAttribute(new AppMenu().getModelAttrKey(), menuHTML);
		} catch (Exception e) {
			form.getMessages()
					.add(new Message(MessageTypeEnum.DANGER, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1)));
			model.addAttribute(form.getAttrKey(), form);
			forward = "switch";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return forward;
	}

	@RequestMapping(value = "notification/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<AppNotification> getNotification(HttpSession session) {
		List<AppNotification> ret = null;
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AppDAO dao = null;

		try {
			dao = new AppDAO();
			ret = dao.findUserNotification(user.getUserId(), false);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "notification/mark", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse markNotif(@ModelAttribute("NotificationForm") NotificationForm form, HttpSession session)
			throws Exception {
		AjaxResponse ret = null;
		AppDAO dao = null;

		try {
			dao = new AppDAO();
			dao.begin();
			dao.markNotification(form.getNotifId());
			dao.commit();
			ret = new AjaxResponse(true);
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			e.printStackTrace();
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "notification/mark/all", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse markAllNotif(@ModelAttribute("NotificationForm") NotificationForm form, HttpSession session) {
		AjaxResponse ret = null;
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AppDAO dao = null;

		try {
			dao = new AppDAO();
			dao.begin();
			dao.markAllNotification(user.getUserId());
			dao.commit();
			ret = new AjaxResponse(true);
		} catch (Exception e) {
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "log/count", method = RequestMethod.GET)
	@ResponseBody
	public Integer getLogCount() {
		Integer ret = null;
		AppDAO dao = null;

		try {
			dao = new AppDAO();
			ret = dao.getTodayLog();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

//	@RequestMapping(value = "trigger/exception")
//	public String triggerException() throws Exception {
//		throw new Exception("Testing Exception handling and notification");
//	}
//
//	@RequestMapping(value = "trigger/mail")
//	public String triggerMail(Model model, @ModelAttribute("DashboardForm") DashboardForm form, HttpSession session)
//			throws Exception {
//		String forward = "popup";
//		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
//		AppMailDAO mailDAO = null;
//		String newId;
//
//		try {
//			mailDAO = new AppMailDAO();
//			mailDAO.begin();
//			mailDAO.queueMail(newId = mailDAO.getSequence(), mailDAO.getDeveloperContact(),
//					"[" + Constants.APPL_SHORTNAME + "] Testing Email Notification " + newId,
//					"<div class='RepTitle'>Testing</div><div class='BoxContent'>A Testing email has been sent to this address by "
//							+ user.getUserId() + ".<b> Ref : " + newId + "</b></div><br /><br />",
//					null, null);
//			mailDAO.commit();
//			form.addMessage(MessageTypeEnum.SUCCESS, "Sending Testing Email success");
//		} catch (Exception e) {
//			if (mailDAO != null)
//				mailDAO.rollback();
//			form.addMessage(MessageTypeEnum.WARNING, e);
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//
//		model.addAttribute(form.getBaseFormAttributeKey(), form);
//		return forward;
//	}
}
