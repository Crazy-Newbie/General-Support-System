package com.badaklng.app.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.SchedulerJobEnum;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.quartz.HousekeepingJob;
import com.badaklng.app.quartz.MailJob;

/*
 * This is the Quartz Scheduler submitter class, for each Job class
 * must have one Servlet responsible for submitting itself
 */
public class SchedulerServlet extends GenericServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SchedulerServlet.class);

	public void init(ServletConfig cfg) throws ServletException {
		if (Constants.CORE_INSTANCE) {
			// DAO initialization
			AppDAO dao = null;
			// Quartz Standard initialization code
			ServletContext servletContext = cfg.getServletContext();
			StdSchedulerFactory factory = (StdSchedulerFactory) servletContext
					.getAttribute(Constants.QUARTZ_SCHEDULER_KEY);

			try {
				dao = new AppDAO();
				Scheduler scheduler = factory.getScheduler(Constants.QUARTZ_INSTANCE_NAME);
				if (!Constants.IS_DEV_MODE) {
					// interval 5 min from start
					scheduler.scheduleJob(MailJob.jobDetail(false), MailJob.schedule());
					dao.logAppActivity(logger, MailJob.JOB_NAME + " scheduler started", true);

					// housekeeping job
					for (SchedulerJobEnum en : SchedulerJobEnum.values()) {
						scheduler.scheduleJob(HousekeepingJob.jobDetail(en), HousekeepingJob.schedule(en));
					}
				}

//				scheduler.scheduleJob(HourlyJob.jobDetail(), HourlyJob.schedule());
//				scheduler.scheduleJob(DailyJob.jobDetail(), DailyJob.schedule());
//				scheduler.scheduleJob(WeeklyJob.jobDetail(), WeeklyJob.schedule());
//				scheduler.scheduleJob(MonthlyJob.jobDetail(), MonthlyJob.schedule());
//				scheduler.scheduleJob(YearlyJob.jobDetail(), YearlyJob.schedule());

				scheduler.start();
			} catch (SchedulerException e) {
				throw new ServletException(e);
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}

	}

	public String getServletInfo() {
		return null;
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {

	}
}
