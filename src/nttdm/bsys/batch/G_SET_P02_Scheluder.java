/**
 * @(#)G_SET_P02_Scheluder.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Batch Scheduler
 * 
 * @author lixinj
 */
public class G_SET_P02_Scheluder extends QuartzJobBean {
	@Autowired
	private G_SET_P02_Task schedulerTask;

	/**
	 * Set scheduler task
	 * 
	 * @param schedulerTask
	 */
	public void setSchedulerTask(G_SET_P02_Task schedulerTask) {
		this.schedulerTask = schedulerTask;
	}

	/**
	 * execute internal
	 * 
	 */
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			schedulerTask.printMessage();
		} catch (Exception ex) {
		}
	}
}
