package nttdm.bsys.e_mex.batch;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class E_MEX_SP1Job extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		//String message = (String) ctx.getJobDetail().getJobDataMap().get("message");
		String returnMessage = "[E_MEX_SP1Job] is running at " + new Date();
		System.out.println(returnMessage);
	}

}
