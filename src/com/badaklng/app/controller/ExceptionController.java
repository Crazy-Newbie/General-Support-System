package com.badaklng.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.exception.NoAccessException;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.AssertionDAO.AssertionTypeEnum;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.DataResponse;
import com.badaklng.app.model.ExceptionListForm;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.utilities.ExceptionTokenizer;

@Controller
@RequestMapping("/sys")
public class ExceptionController {

	@RequestMapping(value = "exception", method = RequestMethod.GET)
	public String initException(Model model, @ModelAttribute("ExceptionListForm") ExceptionListForm form,
			HttpSession session) throws Exception {
		String forward = "exceptionview";
		// AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			// dao = new AppDAO();
			// dao.checkAccess(user, form);
			new AssertionDAO(form, user).getPermission();
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "exception/ajax", method = RequestMethod.GET)
	@ResponseBody
	public DataResponse getExceptionList(ExceptionListForm form, HttpSession session) throws Exception {
		DataResponse res = new DataResponse(form.getDraw());
		AppDAO exDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());
		// List<AppException> exList = null;

		try {
			new AssertionDAO(AssertionTypeEnum.ACCESS, form, user).getPermission();
			exDAO = new AppDAO();
			res.setData(exDAO.findException(form, res));
		} catch (NoAccessException e) {
			res.setError(ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "exception/solved", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse exceptionDone(@RequestParam String exceptionId, HttpSession session) {
		AjaxResponse ret = null;
		AppDAO exDAO = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			ExceptionListForm form = new ExceptionListForm();
			form.setFormModifierEnum(FormModifierEnum.UPDATE);
			exDAO = new AppDAO();
			// exDAO.logActivity(user, getClass(), "exceptionDone",
			// exceptionId);
			LogIntention li = new LogIntention(user, form);
			li.registerArguments(LogKeyAttributeEnum.ID, exceptionId);

			exDAO.begin();
			exDAO.solveById_pkg(exceptionId);
			exDAO.commit();
			exDAO.logActivity(li);

			ret = new AjaxResponse(true, "Exception Status : Solved");
		} catch (Exception e) {
			ret = new AjaxResponse(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return ret;
	}
}
