/**
 * @(#)E_MWF_AR1_Scheluder.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mwf_ar1.batch;

import jp.terasoluna.fw.exception.SystemException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Batch Escalation Workflow Process
 */
public class E_MWF_AR1_Scheluder extends QuartzJobBean {
	@Autowired
	private E_MWF_AR1_Task myTask;

	/**
	 * <div>executeInternal<div>
	 * 
	 * @param context
	 *            JobExecutionContext
	 * @throws JobExecutionException
	 */
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			if (this.myTask != null) {
				this.myTask.printMessage();
			}
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
	}

	/**
	 * <div>getMyTask<div>
	 * 
	 * @return the myTask
	 */
	public E_MWF_AR1_Task getMyTask() {
		return myTask;
	}

	/**
	 * <div>setMyTask<div>
	 * 
	 * @param myTask
	 *            the myTask to set
	 */
	public void setMyTask(E_MWF_AR1_Task myTask) {
		this.myTask = myTask;
	}
}
