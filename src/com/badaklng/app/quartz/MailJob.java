package com.badaklng.app.quartz;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.hibernate.ScrollableResults;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.mail.MailException;

import com.badak.utility.mail.HTMLMail;
import com.badak.utility.mail.MailProperties;
import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.MailTypeEnum;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppMailDAO;
import com.badaklng.app.hibernate.AppMailQueueView;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.utilities.Utility;

@DisallowConcurrentExecution
public class MailJob implements Job {
	private static Logger logger = Logger.getLogger(MailJob.class);

	public static final String JOB_GROUP = "AppGroup";
	public static final String JOB_TRIGGER = "MailTrigger";
	public static final String JOB_NAME = "MailJob";

	public static JobDetail jobDetail(Boolean runOnce) {
		JobBuilder jb = JobBuilder.newJob(MailJob.class).withIdentity(JOB_NAME, JOB_GROUP);
		if (runOnce)
			jb.withIdentity(JOB_NAME + Utility.getNow(Utility.TIMESTAMP_DD));
		return jb.build();
	}

	public static Trigger runOnce() {
		return (Trigger) TriggerBuilder.newTrigger().withIdentity(JOB_NAME + Utility.getNow(Utility.TIMESTAMP_DD))
				.startNow().endAt(DateBuilder.futureDate(1, IntervalUnit.HOUR)).build();
	}

	public static Trigger schedule() {
		return (Trigger) TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(5))
				.build();
	}

	@SuppressWarnings("static-access")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String jobName = MailJob.JOB_NAME;
		logger.info("Scheduler " + jobName + " : started");

		// CatalogPair catPair = (CatalogPair)
		// arg0.getJobDetail().getJobDataMap().get(Constants.JOB_MAP_CATALOG_KEY);

		AppMailDAO dao = null;
		AppDAO appDao = null;
		HTMLMail mail = null;
		MailProperties mprops = new MailProperties();

		mprops.setTransportProtocol("smtp");

		try {
			appDao = new AppDAO();
			String host = appDao.getParameterById("MAIL_SERVER").getParamValue();
			String from = appDao.getParameterById("MAIL_FROM").getParamValue();
			mprops.setHost(host);
			dao = new AppMailDAO();
			dao.beginTransaction();
			dao.acquireLock();
			logger.info("Scheduler " + jobName + " : lock acquired");
			ScrollableResults mqs = dao.getMailQueue(null);
			logger.info("Scheduler " + jobName + " : found emails awaiting to send");
			while (mqs.next()) {
				AppMailQueueView mq = (AppMailQueueView) mqs.get(0);
				mail = new HTMLMail();
				mail.setProperties(mprops);
				mail.setFrom(from);
				// mail.setRecipients(mq.getMailTo().split(","));
				mail.setSubject(mq.getMailSubject());

				if (mq.getMailTo() != null)
					mail.setTo(Arrays.asList(mq.getMailTo().split(",")));
				if (mq.getMailCc() != null)
					mail.setCc(Arrays.asList(mq.getMailCc().split(",")));
				if (mq.getMailBcc() != null)
					mail.setBc(Arrays.asList(mq.getMailBcc().split(",")));

				if (mq.getMailCat() != null && mq.getMailCat().equalsIgnoreCase(MailTypeEnum.PUBLIC.getMailTypeValue()))
					mail.setContent(Constants.MAIL_HEADER + mq.getMailBody() + Constants.PUBLIC_MAIL_FOOTER);
				else
					mail.setContent(Constants.MAIL_HEADER + mq.getMailBody() + Constants.MAIL_FOOTER);

				try {
					mail.send(true);
					dao.mailSuccess(mq.getMailId());
				} catch (Exception e) {
					logger.warn("Scheduler " + jobName + " : error on sending mail id " + mq.getMailId() + " for "
							+ mq.getMailSubject() + " to " + mq.getMailTo());
					dao.mailError(mq.getMailId(), e.getMessage());
				}
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException ie) {
				}
			}
			dao.commitTransaction();

			logger.info("Scheduler " + jobName + " : all emails processed");
			logger.info("Scheduler " + jobName + " : begin archive and purging activity");
			dao.beginTransaction();
			dao.archiveAndPurge();
			dao.commitTransaction();
			logger.info("Scheduler " + jobName + " : archive and purging activity completed");
		} catch (Exception jdbe) {
			if (dao != null)
				dao.rollbackTransaction();

			logger.error("Scheduler " + jobName + " : due to fatal error the schedule cannot continue", jdbe);

			Utility.logExceptionOnly(jdbe);

		} finally {
			HibernateSessionFactory.closeSession();
		}

		logger.info("Scheduler " + jobName + " : finished");
	}
}
