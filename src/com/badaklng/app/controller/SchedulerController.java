package com.badaklng.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.constant.SchedulerJobEnum;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppJob;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.AssertionDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.model.AjaxResponse;
import com.badaklng.app.model.LogIntention;
import com.badaklng.app.model.SchedulerListForm;
import com.badaklng.app.model.SchedulerRow;
import com.badaklng.app.quartz.HousekeepingJob;
import com.badaklng.app.quartz.MailJob;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.Utility;

@Controller
@RequestMapping("/sys")
public class SchedulerController {

	private static final Logger logger = Logger.getLogger(SchedulerController.class);

	@RequestMapping(value = "scheduler", method = RequestMethod.GET)
	public String initSchedulerList(Model model, @ModelAttribute("SchedulerListForm") SchedulerListForm form,
			HttpSession session, HttpServletRequest request) throws Exception {
		String forward = "schedulerlistview";
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).getPermission();

			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			if (scheduler.isInStandbyMode()) {
				form.setSchedulerStatus("Stand By");
				form.setIsSchedulerRunning(false);
			}
			if (scheduler.isShutdown()) {
				form.setSchedulerStatus("Shutdown");
				form.setIsSchedulerRunning(false);
			}
			if (scheduler.isStarted()) {
				form.setSchedulerStatus("Started");
				form.setIsSchedulerRunning(true);
			}
		} catch (SchedulerException e) {
			form.addMessage(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "scheduler/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<SchedulerRow> getScheduler(SchedulerListForm form, HttpSession session) {
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		List<SchedulerRow> row = new ArrayList<SchedulerRow>();
		try {
			new AssertionDAO(form, user).getPermission();

			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			for (String groupName : scheduler.getJobGroupNames()) {

				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();
					row.add(new SchedulerRow(jobName, jobGroup, scheduler.getTriggersOfJob(jobKey).get(0)));
				}
			}
		} catch (Exception e) {
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return row;
	}

	@RequestMapping(value = "scheduler", method = RequestMethod.POST, params = "action=DISABLE")
	@ResponseBody
	public AjaxResponse stopJob(SchedulerListForm form, HttpSession session) {
		AjaxResponse res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			new AssertionDAO(form, user).assertPermission();

			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			scheduler.deleteJob(new JobKey(form.getJobName(), form.getJobGroup()));
			res = new AjaxResponse(true, "Disabling job success");
		} catch (Exception e) {
			res = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "scheduler", method = RequestMethod.POST, params = "action=ENABLE")
	@ResponseBody
	public AjaxResponse startJob(SchedulerListForm form, HttpSession session) {
		AjaxResponse res = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			if (!Utility.isEmptyString(form.getJobName())) {
				new AssertionDAO(form, user).assertPermission();
				String jobName = form.getJobName();

				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

				if (jobName.equalsIgnoreCase("MailJob")) {
					scheduler.scheduleJob(MailJob.jobDetail(false), MailJob.schedule());
				}
				SchedulerJobEnum dt = SchedulerJobEnum.fromString(jobName);
				if (dt != null) {
					scheduler.scheduleJob(HousekeepingJob.jobDetail(dt), HousekeepingJob.schedule(dt));
				}

				res = new AjaxResponse(true, "Enabling job success");
			} else {
				res = new AjaxResponse(false, "Job Name not supplied");
			}
		} catch (Exception e) {
			res = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "scheduler", method = RequestMethod.POST, params = "action=RUN")
	@ResponseBody
	public AjaxResponse runJob(SchedulerListForm form, HttpSession session) {
		AjaxResponse res = null;
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			if (!Utility.isEmptyString(form.getJobName())) {
				new AssertionDAO(form, user).assertPermission();
				String jobName = form.getJobName();
				dao = new AppDAO();
				LogIntention li = new LogIntention(user, form);
				li.registerArguments(LogKeyAttributeEnum.WHAT, jobName);

				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

				if (jobName.equalsIgnoreCase("MailJob")) {
					scheduler.scheduleJob(MailJob.jobDetail(true), MailJob.runOnce());
				} else {
					res = new AjaxResponse(false, "This scheduler [" + jobName + "] doesn't support run once");
				}

				if (res == null) {
					res = new AjaxResponse(true, "Enabling job success");
					dao.logActivity(li);
				}
			} else {
				res = new AjaxResponse(false, "Job Name not supplied");
			}
		} catch (Exception e) {
			res = new AjaxResponse(false, ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return res;
	}

	@RequestMapping(value = "scheduler/start", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse startScheduler(HttpSession session) {
		AjaxResponse res = null;

		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			if (!scheduler.isStarted()) {
				scheduler.start();
			}
			res = new AjaxResponse(true, "Scheduler Started");
		} catch (SchedulerException e) {
			res = new AjaxResponse(false,
					"Starting scheduler failed : " + ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		}

		return res;

	}

	@RequestMapping(value = "scheduler/joblist", method = RequestMethod.GET)
	public String initJobList(Model model, @ModelAttribute("SchedulerListForm") SchedulerListForm form,
			HttpSession session) {
		String forward = "dbjobslistview";

		model.addAttribute(form.getAttrKey(), form);
		return forward;
	}

	@RequestMapping(value = "scheduler/joblist/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<AppJob> getAppDbJob() {
		List<AppJob> result = new ArrayList<AppJob>();
		AppDAO jobDAO = null;

		try {
			jobDAO = new AppDAO();
			result = jobDAO.findJobAll();
		} catch (Exception e) {

		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

	// DON'T DELETE IT YET AS IT CAN BE RE-ADDED LATTER
	@RequestMapping(value = "scheduler/job/disable", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse disableJob(@RequestParam String id) {
		AjaxResponse result = null;
		AppDAO jobDAO = null;

		try {
			jobDAO = new AppDAO();
			AppJob job = jobDAO.findJobById(id);

			jobDAO.begin();
			job.setIsActive(false);
			jobDAO.attachDirty(job);
			jobDAO.commit();
			result = new AjaxResponse(true, "Disable job success");
		} catch (Exception e) {
			result = new AjaxResponse(false,
					"Disable job failed : " + ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

	@RequestMapping(value = "scheduler/job/enable", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse enableJob(@RequestParam String id) {
		AjaxResponse result = null;
		AppDAO jobDAO = null;

		try {
			jobDAO = new AppDAO();
			AppJob job = jobDAO.findJobById(id);

			jobDAO.begin();
			job.setIsActive(true);
			jobDAO.attachDirty(job);
			jobDAO.commit();
			result = new AjaxResponse(true, "Enable job success");
		} catch (Exception e) {
			result = new AjaxResponse(false,
					"Enable job failed : " + ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

	@RequestMapping(value = "scheduler/job/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse deleteJob(@RequestParam String id) {
		AjaxResponse result = null;
		AppDAO dao = null;

		try {
			dao = new AppDAO();
			dao.begin();
			AppJob job = dao.findJobById(id);
			dao.delete(job);
			dao.commit();
			result = new AjaxResponse(true, "Delete job success");
		} catch (Exception e) {
			result = new AjaxResponse(false,
					"Delete job failed : " + ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}

	@RequestMapping(value = "scheduler/job/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse addJob(SchedulerListForm form, HttpSession session) {
		AjaxResponse result = null;
		AppDAO dao = null;
		AppUser user = (AppUser) session.getAttribute(new AppUser().getModelAttrKey());

		try {
			dao = new AppDAO();
			dao.begin();
			AppJob job = new AppJob(Utility.getNow("yyMMddHHmmss"), form.getJobName(), form.getJobSyntax(), true,
					user.getUserId(), Utility.getNow(), form.getJobScheduler());
			if (!Utility.isEmptyString(form.getJobDesc())) {
				job.setJobDesc(form.getJobDesc());
			}

			dao.attachDirty(job);
			dao.commit();
			result = new AjaxResponse(true, "Add job success");
		} catch (Exception e) {
			result = new AjaxResponse(false,
					"Add job failed : " + ExceptionTokenizer.getMessageSegment(e.getMessage(), 1));
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return result;
	}
}
