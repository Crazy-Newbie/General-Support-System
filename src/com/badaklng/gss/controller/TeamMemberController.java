package com.badaklng.gss.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.exception.NoAccessException;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AppUserGroup;
import com.badaklng.app.hibernate.AppUserGroupId;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.GroupForm;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;
import com.badaklng.gss.hibernate.GssMainDAO;
import com.badaklng.gss.hibernate.TeamMember;
import com.badaklng.gss.hibernate.TeamMemberId;
import com.badaklng.gss.model.TeamMemberForm;

@Controller
@RequestMapping("/team/member")
public class TeamMemberController {
	@RequestMapping(method = RequestMethod.GET)
	public String getTeamMember(Model model, @ModelAttribute("TeamMemberForm") TeamMemberForm form, HttpSession session)
			throws Exception {
		String forward = "teammemberview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();
//			if (Utility.isEmptyString(form.getTeamId()))
//				forward = "redirect:/team/list.do";
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<TeamMember> memberajax(TeamMemberForm form, HttpSession session) throws Exception {
		List<TeamMember> ret = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			ret = dao.findTeamMemberByTeamId(form.getTeamId());
		} catch (NoAccessException e) {
			ret = new ArrayList<TeamMember>();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}

	@RequestMapping(method = RequestMethod.POST, params = "action=ADD")
	@ResponseBody
	public AjaxResponse memberadd(@ModelAttribute("TeamMemberForm") @Validated TeamMemberForm form,
			BindingResult result, HttpSession session) {
		AjaxResponse res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				LogIntention li = new LogIntention(user, form);
//				li.registerArguments(LogKeyAttributeEnum.GROUP_ID, form.getGroupId());
//				li.registerArguments(LogKeyAttributeEnum.USER_ID, form.getUserId());

				dao = new AppDAO();
				dao.begin();
//				dao.save(new AppUserGroup(new AppUserGroupId(form.getUserId(), form.getGroupId()), null, null,
//						user.getUserId(), Utility.getNow()));
				dao.saveOrUpdate(new TeamMember(new TeamMemberId(form.getMemberId(), form.getTeamId()), null, user,
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
	
	@RequestMapping(method = RequestMethod.POST, params = "action=REMOVE")
	@ResponseBody
	public AjaxResponse memberremove(@ModelAttribute("TeamMemberForm") @Validated TeamMemberForm form,
			BindingResult result, HttpSession session) {
		AjaxResponse res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(AssertionTypeEnum.FORM, form, user).assertPermission();
				LogIntention li = new LogIntention(user, form);
				

				dao = new GssMainDAO();
				dao.begin();

				dao.getSession().delete(dao.getTeamMemberById(new TeamMemberId(form.getMemberId(), form.getTeamId())));
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

}
