package com.badaklng.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.exception.ApplException;
import com.badaklng.app.exception.NoAccessException;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.model.Message;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;

@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger logger = Logger.getLogger(ExceptionHandlerController.class);

	private void unknownException(BaseForm form, Exception e, AppUser user) {
		logger.error("Uncaught exception", e);

		form.getMessages().add(new Message(
				"Something has gone wrong and our administrator has been notified regarding this error. Please click one of the menu above to go back.",
				MessageTypeEnum.INFO));
		form.getMessages()
				.add(new Message(
						"If you wish, you may provide the following messages to " + Constants.APPL_SHORTNAME
								+ " Administrator<br><br><i>\""
								+ ExceptionTokenizer.getMessageSegment(e.getMessage(), 1) + "\"</i>",
						MessageTypeEnum.DANGER));
		Utility.logExceptionAndNotify(e, user);
	}

//	@ExceptionHandler({ RuntimeException.class, IllegalStateException.class })
//	public ModelAndView handleRunTimeException(Exception e) {
//		ModelAndView mav = new ModelAndView("404");
//		BaseForm form = new BaseForm();
//		mav.addObject(Constants.BASE_FORM_ATTR_KEY, form);
//		return mav;
//	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noHandler(NoHandlerFoundException e) {
		ModelAndView mav = new ModelAndView("404");
		BaseForm form = new BaseForm();
		mav.addObject(Constants.BASE_FORM_ATTR_KEY, form);
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
		BaseForm form = new BaseForm();
		ModelAndView mav = new ModelAndView("error");
		AppUser user = (AppUser) request.getSession().getAttribute(new AppUser().getModelAttrKey());

		form.setFormCode("SY999");
		mav.addObject(Constants.BASE_FORM_ATTR_KEY, form);

		if (e instanceof JDBCException) {
			try {
				form.addMessage(e);
			} catch (Exception ee) {
				unknownException(form, ee, user);
			}
		} else if (e instanceof ApplException) {
			form.addMessage(MessageTypeEnum.WARNING, e.getMessage());
		} else {
			unknownException(form, e, user);
		}

		return mav;
	}
}
