package com.badaklng.app.quartz;

import org.hibernate.JDBCException;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.oxm.jibx.JibxMarshaller;

import com.badaklng.app.constant.SchedulerJobEnum;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppJob;
import com.badaklng.app.hibernate.HibernateSessionFactory;
import com.badaklng.app.utilities.Utility;

public class HousekeepingJob implements Job {

	public static final String JOB_GROUP = "AppGroup";

	public static JobDetail jobDetail(SchedulerJobEnum jobEnum) {
		JobBuilder jb = JobBuilder.newJob(HousekeepingJob.class).withIdentity(jobEnum.getJobName(), JOB_GROUP)
				.usingJobData("scheduler", jobEnum.getScheduler());
		return jb.build();
	}

	public static Trigger schedule(SchedulerJobEnum jobEnum) {
		return (Trigger) TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(jobEnum.getJobCron())).build();
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		AppDAO dao = null;
		JobDataMap jdm = arg0.getJobDetail().getJobDataMap();

		try {

			dao = new AppDAO();
			for (AppJob job : dao.findActiveJobByScheduled(jdm.getString("scheduler"))) {
				try {
					dao.begin();
					dao.executeSQL(job.getSyntax());
					dao.commit();
				} catch (JDBCException e) {
					if (dao != null)
						dao.rollback();

					Utility.logExceptionAndNotify(e);
				}
			}

		} catch (Exception e) {
			Utility.logExceptionAndNotify(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
