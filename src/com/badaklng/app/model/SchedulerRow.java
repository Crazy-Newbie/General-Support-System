package com.badaklng.app.model;

import java.sql.Timestamp;

import org.quartz.Trigger;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.utilities.Utility;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SchedulerRow extends BaseModel {

	private String jobName;
	private String jobGroup;
	private Trigger trigger;

	public SchedulerRow() {

	}

	public SchedulerRow(String jobName, String jobGroup) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}

	public SchedulerRow(String jobName, String jobGroup, Trigger trigger) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.trigger = trigger;
	}

	public String getStart() {
		return Utility.timestampToShiftDatetimeString(new Timestamp(trigger.getStartTime().getTime()));
	}

//	public String getLast(){
//		if(trigger.getPreviousFireTime() == null)
//			return "Not Yet Started";
//		return Utility.datetimeString(new Timestamp(trigger.getPrevFireTime().getTime()));
//	}

	public String getEnd() {
		if (trigger.getEndTime() != null)
			return Utility.timestampToShiftDatetimeString(new Timestamp(trigger.getEndTime().getTime()));
		return "Forever";
	}

	public String getPrev() {
		if (trigger.getPreviousFireTime() != null)
			return Utility.timestampToShiftDatetimeString(new Timestamp(trigger.getPreviousFireTime().getTime()));
		return "";
	}

	public String getNext() {
		if (trigger.getNextFireTime() != null)
			return Utility.timestampToShiftDatetimeString(new Timestamp(trigger.getNextFireTime().getTime()));
		return "N/A";
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	@JsonIgnore
	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

}
