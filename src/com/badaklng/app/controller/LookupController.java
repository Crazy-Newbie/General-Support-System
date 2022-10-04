package com.badaklng.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.badaklng.app.model.CompLkpForm;
import com.badaklng.app.model.LookupForm;

@Controller
@RequestMapping("/lookup")
public class LookupController {

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String initUserLookup(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "userlookup";
	}

	@RequestMapping(value = "employee", method = RequestMethod.GET)
	public String initEmployeeLookup(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "employeelookup";
	}

	@RequestMapping(value = "subemployee", method = RequestMethod.GET)
	public String initSubEmployeeLookup(Model model, @ModelAttribute("LookupForm") LookupForm form,
			HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "subemployeelookup";
	}

	@RequestMapping(value = "organization", method = RequestMethod.GET)
	public String initOrganization(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "organizationlookup";
	}

	@RequestMapping(value = "company", method = RequestMethod.GET)
	public String initCompany(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "companylookup";
	}

	@RequestMapping(value = "position", method = RequestMethod.GET)
	public String initPosition(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "positionlookup";
	}

	@RequestMapping(value = "group", method = RequestMethod.GET)
	public String initGroup(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "grouplookup";
	}

	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String initMenuLookup(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {
		model.addAttribute(form.getAttrKey(), form);
		return "menulookup";
	}

	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String initFormLookup(Model model, @ModelAttribute("LookupForm") LookupForm form, HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "formlookup";
	}

	@RequestMapping(value = "employeeinfo", method = RequestMethod.GET)
	public String initEmployeeinfoLookup(Model model, @ModelAttribute("LookupForm") LookupForm form,
			HttpSession session) {

		model.addAttribute(form.getAttrKey(), form);
		return "employeeinfolookup";
	}
}