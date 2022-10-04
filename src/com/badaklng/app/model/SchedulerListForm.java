package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class SchedulerListForm extends BaseForm {

	private static final String FORM_CODE = "SCHEDULER_LIST_FORM";

	private String jobName;
	private String jobDesc;
	private String jobSyntax;
	private String jobGroup;
	private String jobScheduler;

	// addition
	private String schedulerStatus;
	private Boolean isSchedulerRunning;

	public SchedulerListForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobSyntax() {
		return jobSyntax;
	}

	public void setJobSyntax(String jobSyntax) {
		this.jobSyntax = jobSyntax;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSchedulerStatus() {
		return schedulerStatus;
	}

	public void setSchedulerStatus(String schedulerStatus) {
		this.schedulerStatus = schedulerStatus;
	}

	public Boolean getIsSchedulerRunning() {
		return isSchedulerRunning;
	}

	public void setIsSchedulerRunning(Boolean isSchedulerRunning) {
		this.isSchedulerRunning = isSchedulerRunning;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobScheduler() {
		return jobScheduler;
	}

	public void setJobScheduler(String jobScheduler) {
		this.jobScheduler = jobScheduler;
	}

}
