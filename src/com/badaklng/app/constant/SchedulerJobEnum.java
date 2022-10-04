package com.badaklng.app.constant;

public enum SchedulerJobEnum {
	YEARLY("YEARLY", "YearlyJob", "0 0 3 1 1 ? *"), MONTHLY("MONTHLY", "MonthlyJob", "0 0 2 1 1/1 ? *"),
	WEEKLY("WEEKLY", "WeeklyJob", "0 0 1 ? * MON *"), DAILY("DAILY", "DailyJob", "0 0 0 1/1 * ?"),
	HOURLY("HOURLY", "HourlyJob", "0 0 0/1 1/1 * ?");

	private String jobName;
	private String cron;
	private String scheduler;

	SchedulerJobEnum(String scheduler, String jobName, String cron) {
		this.jobName = jobName;
		this.cron = cron;
		this.scheduler = scheduler;
	}

	public static SchedulerJobEnum fromString(String scheduler) {
		if (scheduler.equalsIgnoreCase("YearlyJob"))
			return SchedulerJobEnum.YEARLY;
		if (scheduler.equalsIgnoreCase("MonthlyJob"))
			return SchedulerJobEnum.MONTHLY;
		if (scheduler.equalsIgnoreCase("WeeklyJob"))
			return SchedulerJobEnum.WEEKLY;
		if (scheduler.equalsIgnoreCase("DailyJob"))
			return SchedulerJobEnum.DAILY;
		if (scheduler.equalsIgnoreCase("HourlyJob"))
			return SchedulerJobEnum.HOURLY;
		return null;
	}

	public String getScheduler() {
		return this.scheduler;
	}

	public String getJobName() {
		return this.jobName;
	}

	public String getJobCron() {
		return this.cron;
	}
}
