package com.badaklng.gss.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
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
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.utilities.Utility;
import com.badaklng.gss.hibernate.GssMainDAO;
import com.badaklng.gss.hibernate.Team;
import com.badaklng.gss.model.TeamForm;
import com.badaklng.gss.model.TeamListForm;

@Controller
@RequestMapping("/team")
public class TeamController {

	@InitBinder(value = { "TeamForm" })
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new TeamValidator());
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getTeamList(Model model, @ModelAttribute("TeamListForm") TeamListForm form, HttpSession session)
			throws Exception {
		String forward = "teamlistview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new GssMainDAO();
			model.addAttribute("listdata", dao.findTeamViewAll());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;

	}
	
	
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String getTeamForm(Model model, @ModelAttribute("TeamForm") TeamForm form, HttpSession session)
			throws Exception {
		String forward = "teamformview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			// memunculkan data / menginisiasi form
			if (!Utility.isEmptyString(form.getTeamId())) {
				dao = new GssMainDAO();
				Team team = dao.getTeamById(form.getTeamId().trim().toUpperCase());
				if (team != null) {
					form.setData(team);
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
	public String postTeamFormCreate(Model model, @ModelAttribute("TeamForm") @Validated TeamForm form,
			BindingResult result, HttpSession session) throws Exception {
		String forward = "teamformview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(form, user).getPermission();

				// CARA 1
//				Team team = new Team();
//				team.setTeamId(form.getTeamId().trim().toUpperCase());
//				team.setTeamName(form.getTeamName());
//				team.setCreatedBy(user.getUserId());
//				team.setCreatedDate(Utility.getNow());
//
//				dao = new GssMainDAO();
//				dao.begin();
//				dao.getSession().save(team);
//				dao.commit();
				// return back to team list

				// CARA 2

				dao = new GssMainDAO();
				dao.begin();
				dao.createTeam_pkg(form, user);
				dao.commit();
				forward = "redirect:/team/list.do";

				// return back to team form
//				form.reset();
//				form.addMessage(MessageTypeEnum.SUCCESS, "Pembuatan Team Berhasil");
			}
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=UPDATE")
	public String postTeamFormUpdate(Model model, @ModelAttribute("TeamForm") @Validated TeamForm form,
			BindingResult result, HttpSession session) throws Exception {
		String forward = "teamformview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		GssMainDAO dao = null;

		try {
			if (!result.hasErrors()) {
				new AssertionDAO(form, user).getPermission();
				dao = new GssMainDAO();
				// nge save data yang ada di form
//				Team team = dao.getTeamById(form.getTeamId());
//				team.setTeamName(form.getTeamName());
//				team.setUpdatedBy(user.getUserId());
//				team.setUpdatedDate(Utility.getNow());

				dao = new GssMainDAO();
				dao.begin();
				dao.updateTeam_pkg(form, user);
				dao.commit();
				// return back to team list
				forward = "redirect:/team/list.do";

				// return back to team form
//			form.reset();
//			form.addMessage(MessageTypeEnum.SUCCESS, "Pembuatan Team Berhasil");
			}
		} catch (Exception e) {
			if (dao != null)
				dao.rollback();
			form.addMessage(MessageTypeEnum.WARNING, e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}
	
//	@RequestMapping(value = "form", method = RequestMethod.POST, params = "action=DELETE")
//	@ResponseBody
//	public AjaxResponse postTeamFormDelete(TeamForm form, HttpSession session) {
//		AjaxResponse res = null;
//		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
//		GssMainDAO dao = null;
//
//		try {
//			new AssertionDAO(form, user).assertPermission();
//			LogIntention li = new LogIntention(user, form);
//			li.registerArguments(LogKeyAttributeEnum.ID, form.getTeamId());
//			dao = new GssMainDAO();
//			dao.begin();
//			dao.deleteTeam_pkg(form);
//			dao.commit();
//			dao.logActivity(li);
//			res = new AjaxResponse(true);
//		} catch (Exception e) {
//			if (dao != null)
//				dao.rollback();
//			res = new AjaxResponse(e);
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//
//		return res;
//	}
	

}
