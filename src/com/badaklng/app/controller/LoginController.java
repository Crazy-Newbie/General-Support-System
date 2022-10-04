package com.badaklng.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.badak.ldap.LDAPAccount;
import com.badak.ldap.LDAPException;
import com.badak.ldap.LDAPManager;
import com.badak.ldap.LDAPProperty;
import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.constant.SessionAttributeEnum;
import com.badaklng.app.hibernate.AppMenu;
import com.badaklng.app.hibernate.AppMenuDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AppUserDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.model.LoginForm;
import com.badaklng.app.model.Message;
import com.badaklng.app.model.MessageEnum;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@InitBinder("LoginForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new LoginValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model, @ModelAttribute("LoginForm") LoginForm form, HttpSession session)
			throws Exception {
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);

		if (user != null) {
			if (Constants.CORE_INSTANCE) {
				return "redirect:/dashboard.do";
			} else
				return "redirect:/pm/inspect/start.do";
		}
		model.addAttribute(form.getAttrKey(), form);
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("LoginForm") @Validated LoginForm form, BindingResult result,
			HttpSession session) throws Exception {

		String forward = "login";
		List<Message> messages = form.getMessages();

//		if (!Constants.CORE_INSTANCE) {
//			messages.add(new Message(MessageTypeEnum.WARNING, "Cannot Login from a Non-Core Web Application of "
//					+ Constants.APPLICATION_NAME + ". Ask your administrator for application core link"));
//			model.addAttribute(form.getAttrKey(), form);
//			return forward;
//		}
		AppUserDAO userDAO = null;
		AppUser user = null;
		form.setFormModifierEnum(FormModifierEnum.LOGIN);
		LogIntention li = new LogIntention(user, form);
		li.registerArguments(LogKeyAttributeEnum.USERNAME, form.getUsername());

		// Check form validation results
		if (!result.hasErrors()) {
			try {
				userDAO = new AppUserDAO();

				// First attempt checking for non-LDAP users to avoid LDAP
				// verification timeout
				user = userDAO.findById(form.getUsername());

				// The user is non-LDAP user, proceed with password
				// authentication
				if (user != null) {
					// userDAO.logActivity(user, this.getClass(), "login",
					// form.getUsername());
					user.setIpAddress((String) session.getAttribute(SessionAttributeEnum.IP_ADDRESS.toString()));
					// user.setAnonymId(Utility.generateMD5(user.getUserId()));

					try {
						userDAO.attemptLogin(user.getIpAddress(), user.getUserId(), form.getPassword());
						if (Constants.CORE_INSTANCE) {
							if (!Utility.isEmptyString(form.getRedirect()))
								forward = "redirect:" + form.getRedirect();
							else
								forward = "redirect:/dashboard.do";
						} else
							forward = "redirect:/pm/inspect/start.do";
					} catch (JDBCException e) {
						logger.warn("Non-LDAP user " + form.getUsername() + " authentication failed");
						// logger.warn("Non-LDAP user {} authentication
						// failed",form.getUsername());
						if (e.getErrorCode() != 20000) {
							throw e;
						} else {
							messages.add(new Message(MessageTypeEnum.DANGER,
									ExceptionTokenizer.getMessageSegment(e.getSQLException().getMessage(), 1)));
						}

						// TODO Set user ip address logger
						li.getUser().setIpAddress(
								(String) session.getAttribute(SessionAttributeEnum.IP_ADDRESS.toString()));
					}
				} else {
					try {
						LDAPManager LDAPMgr = null;
						LDAPAccount LDAPAcc = null;
						try {
							LDAPMgr = new LDAPManager((LDAPProperty) session.getServletContext()
									.getAttribute(SessionAttributeEnum.BTG_USER_ORGANIZATION.toString()));

							LDAPAcc = LDAPMgr.authenticateUser(form.getUsername(), form.getPassword());

						} catch (LDAPException ldpe) {
							LDAPMgr = new LDAPManager((LDAPProperty) session.getServletContext()
									.getAttribute(SessionAttributeEnum.JKT_USER_ORGANIZATION.toString()));

							LDAPAcc = LDAPMgr.authenticateUser(form.getUsername(), form.getPassword());
						}

						userDAO.attemptLogin((String) session.getAttribute(SessionAttributeEnum.IP_ADDRESS.toString()),
								(String) ((LDAPAccount.Attribute) LDAPAcc.getAttribute("badgeNumber").get(0))
										.getValue());

						user = userDAO
								.findById((String) ((LDAPAccount.Attribute) LDAPAcc.getAttribute("badgeNumber").get(0))
										.getValue());

						user.setIpAddress((String) session.getAttribute(SessionAttributeEnum.IP_ADDRESS.toString()));

						if (Constants.CORE_INSTANCE) {
							if (!Utility.isEmptyString(form.getRedirect()))
								forward = "redirect:" + form.getRedirect();
							else
								forward = "redirect:/dashboard.do";
						} else
							forward = "redirect:/pm/inspect/start.do";
					} catch (LDAPException ldape) {

						li.getUser().setIpAddress(
								(String) session.getAttribute(SessionAttributeEnum.IP_ADDRESS.toString()));

						logger.warn("LDAP user " + form.getUsername() + " authentication failed");
						// logger.warn("LDAP user {} authentication failed",
						// form.getUsername(), ldape);
						messages.add(new Message(MessageTypeEnum.WARNING, "Invalid username or password"));

					} catch (JDBCException e) {
						logger.warn("LDAP user " + form.getUsername() + " authentication failed");
						// logger.warn("LDAP user {} authentication failed",
						// form.getUsername());
						if (e.getErrorCode() != 20000) {
							throw e;
						} else {
							messages.add(new Message(MessageTypeEnum.DANGER,
									ExceptionTokenizer.getMessageSegment(e.getSQLException().getMessage(), 1)));
						}
						li.getUser().setIpAddress(
								(String) session.getAttribute(SessionAttributeEnum.IP_ADDRESS.toString()));

					}
				}

				if (user != null) {
//                	Hibernate.initialize(user);
					user.setInfo(userDAO.findEmployeeLkpInfo(user.getUserId()));
					li.setUser(user);
					session.setAttribute(user.getModelAttrKey(), user);
					AppMenuDAO menuDAO = new AppMenuDAO(user);

					String menuHTML = new String();
					menuHTML = menuDAO.generateMenu(user, 0);
					session.setAttribute(new AppMenu().getModelAttrKey(), menuHTML);
					userDAO.logActivity(li);
				}
			} catch (JDBCException e) {
				if (e.getErrorCode() == 20000)
					messages.add(new Message(MessageTypeEnum.WARNING,
							ExceptionTokenizer.getMessageSegment(e.getMessage(), 1)));
				else {
					messages.add(new Message(MessageTypeEnum.WARNING, e.getMessage()));
				}
			} finally {
				HibernateSessionFactory.closeSession();
			}

		} else {
			for (ObjectError err : result.getAllErrors()) {
				messages.add(new Message(MessageTypeEnum.WARNING, MessageEnum.valueOf(err.getCode()).getDescription()));
			}
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}
}
