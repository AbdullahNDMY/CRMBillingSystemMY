package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class T_BATCH extends ValidatorActionFormEx{

	private static final long serialVersionUID = 4805839006738685975L;
	private String id_batch_type;
	private String exec_day;
	private String exec_hour;
	private String exec_minute;
	
	public String getId_batch_type() {
		return id_batch_type;
	}
	public void setId_batch_type(String id_batch_type) {
		this.id_batch_type = id_batch_type;
	}
	public String getExec_day() {
		return exec_day;
	}
	public void setExec_day(String exec_day) {
		this.exec_day = exec_day;
	}
	public String getExec_hour() {
		return exec_hour;
	}
	public void setExec_hour(String exec_hour) {
		this.exec_hour = exec_hour;
	}
	public String getExec_minute() {
		return exec_minute;
	}
	public void setExec_minute(String exec_minute) {
		this.exec_minute = exec_minute;
	}
		
}
