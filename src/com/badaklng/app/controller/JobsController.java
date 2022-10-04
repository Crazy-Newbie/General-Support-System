package com.badaklng.app.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.badaklng.app.model.JobsListForm;

@Controller
@RequestMapping("/jobs")
public class JobsController {
	private static Logger logger = Logger.getLogger(JobsController.class);

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String initList(Model model, @ModelAttribute("JobsListForm") JobsListForm form, HttpSession session) {
		String forward = "jobslistview";

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}
}
