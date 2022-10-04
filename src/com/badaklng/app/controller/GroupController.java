package com.badaklng.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
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
import com.badaklng.app.exception.NoAccessException;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppGroup;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AppUserGroup;
import com.badaklng.app.hibernate.AppUserGroupId;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.GroupForm;
import com.badaklng.app.model.GroupListForm;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;
import com.badaklng.app.hibernate.HibernateSessionFactory;

@Controller
@RequestMapping("/sys/group")
public class GroupController {

	@InitBinder(value = { "GroupForm" })
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new GroupValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("GroupListForm") GroupListForm form, HttpSession session)
			throws Exception {
		String forward = "groupview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "list/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<AppGroup> ajax(GroupListForm form, HttpSession session) {
		List<AppGroup> ret = new ArrayList<AppGroup>();
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		AppDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new AppDAO();
			ret = dao.findGroupAll();
		} catch (Exception e) {
			ret = new ArrayList<AppGroup>();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=DELETE")
	@ResponseBody
	public AjaxResponse delete(GroupForm form, HttpSession session) {
		AjaxResponse res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
			LogIntention li = new LogIntention(user, form);
			li.registerArguments(LogKeyAttributeEnum.GROUP_ID, form.getGroupId());

			dao = new AppDAO();
			AppGroup grp = dao.findGroupById(form.getGroupId());
			if (grp != null) {
				dao.begin();
				dao.delete(grp);
				dao.commit();
				dao.logActivity(li);
				res = new AjaxResponse(true, "Group deleted");
			} else {
				res = new AjaxResponse(false, "Group not found");
			}
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			res = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form(Model model, @ModelAttribute("GroupForm") GroupForm form, HttpSession session) throws Exception {
		String forward = "groupform";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			dao = new AppDAO();
			if (!Utility.isEmptyString(form.getGroupId())) {
				AppGroup grp = dao.findGroupById(form.getGroupId());
				if (grp != null) {
					form.setFormModifier(FormModifierEnum.UPDATE);
					form.setGroup(grp);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=CREATE")
	public String formCreate(Model model, @ModelAttribute("GroupForm") @Validated GroupForm form, BindingResult result,
			HttpSession session) throws Exception {
		String forward = "redirect:/sys/group.do";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				dao = new AppDAO();
				dao.begin();
				AppGroup grp = new AppGroup(form.getGroupId().toUpperCase(), form.getGroupName().toUpperCase(), null);
				dao.save(grp);
				dao.commit();
			}
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(e);
			forward = "groupform";
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=UPDATE")
	public String formUpdate(Model model, @ModelAttribute("GroupForm") @Validated GroupForm form, BindingResult result,
			HttpSession session) throws Exception {
		String forward = "redirect:/sys/group.do";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				dao = new AppDAO();
				dao.begin();
				AppGroup grp = dao.findGroupById(form.getGroupId());
				if (grp != null) {
					grp.setName(form.getGroupName().toUpperCase());
					dao.attachDirty(grp);
					dao.commit();
				} else {
					form.addMessage(MessageTypeEnum.DANGER, "Group not found");
				}
			}
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(e);
			forward = "groupform";
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "member", method = RequestMethod.GET)
	public String member(Model model, @ModelAttribute("GroupForm") GroupForm form, HttpSession session)
			throws Exception {
		String forward = "groupmemberform";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			if (Utility.isEmptyString(form.getGroupId()))
				forward = "redirect:/sys/group.do";
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "member/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<AppUserGroup> memberajax(GroupForm form, HttpSession session) throws Exception {
		List<AppUserGroup> ret = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			dao = new AppDAO();
			dao.begin();
			AppGroup grp = dao.findGroupById(form.getGroupId());
			if (grp != null) {
				Hibernate.initialize(grp.getAppUserGroups());
				ret = new ArrayList<AppUserGroup>(grp.getAppUserGroups());
			} else {
				ret = new ArrayList<AppUserGroup>();
			}
			dao.rollback();
		} catch (NoAccessException e) {
			ret = new ArrayList<AppUserGroup>();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(value = "member/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse memberadd(@ModelAttribute("GroupForm") @Validated GroupForm form, BindingResult result,
			HttpSession session) {
		AjaxResponse res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.GROUP_ID, form.getGroupId());
				li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getUserId());

				dao = new AppDAO();
				dao.begin();
				dao.save(new AppUserGroup(new AppUserGroupId(form.getUserId(), form.getGroupId()), null, null,
						user.getUserId(), Utility.getNow()));
				dao.commit();
				dao.logActivity(li);
				res = new AjaxResponse(true);
			} else {
				res = new AjaxResponse(false, result.getAllErrors().get(0).getDefaultMessage());
			}
		} catch (Exception e) {
			res = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "member/remove", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse memberremove(@ModelAttribute("GroupForm") @Validated GroupForm form, BindingResult result,
			HttpSession session) {
		AjaxResponse res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.GROUP_ID, form.getGroupId());
				li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getUserId());

				dao = new AppDAO();
				dao.begin();
				AppUserGroup aug = dao.findAppUserGroupById(new AppUserGroupId(form.getUserId(), form.getGroupId()));
				if (aug != null) {
					dao.delete(aug);
					dao.commit();
					dao.logActivity(li);
					res = new AjaxResponse(true);
				} else {
					res = new AjaxResponse(false, "App User Group not found");
				}

			} else {
				res = new AjaxResponse(false, result.getAllErrors().get(0).getDefaultMessage());
			}
		} catch (Exception e) {
			res = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}
}
