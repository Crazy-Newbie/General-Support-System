package com.badaklng.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppEmpInfoView;
import com.badaklng.app.hibernate.AppEmpLkpView;
import com.badaklng.app.hibernate.AppGroup;
import com.badaklng.app.hibernate.AppMenu;
import com.badaklng.app.hibernate.AppMenuDAO;
import com.badaklng.app.hibernate.AppOrganization;
import com.badaklng.app.hibernate.AppPositionLkpView;
import com.badaklng.app.hibernate.AppRole;
import com.badaklng.app.hibernate.AppSubEmpLkpView;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AppUserDAO;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.EmpLkpForm;
import com.badaklng.app.model.LookupForm;
import com.badaklng.app.model.OrgLkpForm;
import com.badaklng.app.model.UserLkpForm;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;

@Controller
@RequestMapping("/helper")
public class HelperController {

	@RequestMapping(value = "employee")
	@ResponseBody
	public DataResponse getEmployeeList(EmpLkpForm form, HttpSession session) {
		DataResponse res = new DataResponse(form.getDraw());
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUserDAO persDAO = null;

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			persDAO = new AppUserDAO();
			if (!Utility.isEmptyString(form.getKeyword())) {
				res.setData(persDAO.findPTBEmployee(form, res));
			} else {
				res.setData(new ArrayList<AppEmpLkpView>());
			}
		} catch (Exception e) {
			res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return res;
	}

	@RequestMapping(value = "subemployee")
	@ResponseBody
	public DataResponse getSubEmployeeList(EmpLkpForm form, HttpSession session) {
		DataResponse res = new DataResponse(form.getDraw());
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUserDAO persDAO = null;

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			persDAO = new AppUserDAO();
			if (!Utility.isEmptyString(form.getKeyword())) {
				res.setData(persDAO.findSubEmployee(form, user, res));
			} else {
				res.setData(new ArrayList<AppSubEmpLkpView>());
			}
		} catch (Exception e) {
			res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return res;
	}

	@RequestMapping(value = "empinfo")
	@ResponseBody
	public DataResponse getEmployeeinfoList(EmpLkpForm form, HttpSession session) {
		DataResponse res = new DataResponse(form.getDraw());
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUserDAO persDAO = null;

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			persDAO = new AppUserDAO();
			if (!Utility.isEmptyString(form.getKeyword())) {
				res.setData(persDAO.findPTBEmployeeinfo(form, res));
			} else {
				res.setData(new ArrayList<AppEmpInfoView>());
			}
		} catch (Exception e) {
			res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return res;
	}

	@RequestMapping(value = "employee/all")
	@ResponseBody
	public List<AppEmpLkpView> getEmployeeAllList(EmpLkpForm form, HttpSession session) {
		List<AppEmpLkpView> res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUserDAO persDAO = null;

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			persDAO = new AppUserDAO();
			if (!Utility.isEmptyString(form.getKeyword())) {
				res = persDAO.findEmployee(form);
			} else {
				res = new ArrayList<AppEmpLkpView>();
			}
		} catch (Exception e) {
			res = new ArrayList<AppEmpLkpView>();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return res;

	}

	@RequestMapping(value = "user")
	@ResponseBody
	public DataResponse getUserList(UserLkpForm form, HttpSession session) {
		DataResponse res = new DataResponse(form.getDraw());
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppUserDAO userDAO = null;

		try {
			AppUser actual = user.getOriginalUser() != null ? user.getOriginalUser() : user;
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, actual).getPermission();
			userDAO = new AppUserDAO();
			res.setData(userDAO.findByKeyword(form, res));
		} catch (Exception e) {
			res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "ptbposition")
	@ResponseBody
	public DataResponse getPtbPos(LookupForm form, HttpSession session) {
		DataResponse res = new DataResponse(form.getDraw());
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		AppDAO dao = null;
		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			dao = new AppDAO();
			if (!Utility.isEmptyString(form.getKeyword())) {
				res.setData(dao.findPTBPosByKeyword(form, res));
			} else {
				res.setData(new ArrayList<AppPositionLkpView>());
			}
		} catch (Exception e) {
			res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return res;
	}

	@RequestMapping(value = "organization")
	@ResponseBody
	public List<AppOrganization> getOrganization(OrgLkpForm form, HttpSession session) {
		AppUser user = (AppUser) session.getAttribute(AppUser.modelAttrKey);
		List<AppOrganization> result = null;
		AppDAO dao = null;

		try {
			new AssertionDAO(form, user).getPermission();
			dao = new AppDAO();
			if (!Utility.isEmptyString(form.getKeyword())) {
				result = dao.findOrganizationByKeyword(form.getKeyword().toUpperCase());
			} else {
				result = new ArrayList<AppOrganization>();
			}
		} catch (Exception e) {
			result = new ArrayList<AppOrganization>();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return result;
	}

	@RequestMapping(value = "department")
	@ResponseBody
	public List<AppOrganization> getDepartment(@RequestParam(required = false) String keyword) {
		List<AppOrganization> result = null;
		AppDAO persDAO = null;

		try {
			persDAO = new AppDAO();
			if (!Utility.isEmptyString(keyword)) {
				result = persDAO.getDepartment(keyword);
			} else {
				result = persDAO.getDepartment();
			}
		} finally {
			HibernateSessionFactory.closeStatelessSession();
		}
		return result;
	}

	@RequestMapping(value = "section")
	@ResponseBody
	public List<AppOrganization> getSection(@RequestParam(required = false) String deptId) {
		List<AppOrganization> result = null;
		AppDAO persDAO = null;

		try {
			persDAO = new AppDAO();
			if (!Utility.isEmptyString(deptId)) {
				result = persDAO.getSubOrg(deptId);
			} else {
				result = persDAO.getSection();
			}
		} finally {
			HibernateSessionFactory.closeStatelessSession();
		}
		return result;
	}

	@RequestMapping(value = "position")
	@ResponseBody
	public List<AppPositionLkpView> getPosition(@RequestParam(required = false) String keyword) {
		List<AppPositionLkpView> result = null;

		AppDAO dao = null;

		try {
			if (!Utility.isEmptyString(keyword)) {
				dao = new AppDAO();
				result = dao.findPositionByKeyword(keyword.toUpperCase());
			} else {
				result = new ArrayList<AppPositionLkpView>();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

	@RequestMapping(value = "group")
	@ResponseBody
	public List<AppGroup> getGroup(@RequestParam String keyword) {
		List<AppGroup> result = null;
		if (Utility.isEmptyString(keyword)) {
			return new ArrayList<AppGroup>();
		}

		AppDAO grpDAO = null;

		try {
			grpDAO = new AppDAO();
			result = grpDAO.findGroupByKeyword(keyword.toUpperCase());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

	@RequestMapping(value = "menu")
	@ResponseBody
	public List<AppMenu> getMenu() {
		List<AppMenu> result = null;
		AppMenuDAO menuDAO = null;

		try {
			menuDAO = new AppMenuDAO();
			result = menuDAO.findAllChildrenOrdered(0);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

//	@RequestMapping(value = "menurole")
//	@ResponseBody
//	public List<AppMenu> getMenuUser(@RequestParam String roleId) {
//		List<AppMenu> result = new ArrayList<AppMenu>();
//		AppMenuDAO menuDAO = null;
//
//		try {
//			menuDAO = new AppMenuDAO();
//			result = menuDAO.generateMenuListByRole(roleId);
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//
//		return result;
//	}

	@RequestMapping(value = "role")
	@ResponseBody
	public List<AppRole> getRole() {
		List<AppRole> result = null;
		AppDAO roleDAO = null;

		try {
			roleDAO = new AppDAO();
			result = roleDAO.findRoleAll();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return result;
	}

}
