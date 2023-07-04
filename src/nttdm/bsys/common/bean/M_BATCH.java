package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class M_BATCH extends ValidatorActionFormEx{

	private static final long serialVersionUID = -4702133630179390458L;
	private String batch_id;
	private String alert_mode;
    private String frequency_mode;
	private String day_of_month;
	private String time_hour;
	private String time_minute;	
	private String statement_month;
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getAlert_mode() {
		return alert_mode;
	}
	public void setAlert_mode(String alert_mode) {
		this.alert_mode = alert_mode;
	}
	public String getDay_of_month() {
		return day_of_month;
	}
	public void setDay_of_month(String day_of_month) {
		this.day_of_month = day_of_month;
	}
	public String getTime_hour() {
		return time_hour;
	}
	public void setTime_hour(String time_hour) {
		this.time_hour = time_hour;
	}
	public String getTime_minute() {
		return time_minute;
	}
	public void setTime_minute(String time_minute) {
		this.time_minute = time_minute;
	}

    /**
     * @param frequency_mode
     * the frequency_mode to set
     */
    public void setFrequency_mode(String frequency_mode) {
        this.frequency_mode = frequency_mode;
    }

    /**
     * @return the frequency_mode
     */
    public String getFrequency_mode() {
        return frequency_mode;
    }
    
    /**
     * @return the statement_month
     */
    public String getStatement_month() {
        return statement_month;
    }
    /**
     * @param statement_month the statement_month to set
     */
    public void setStatement_month(String statement_month) {
        this.statement_month = statement_month;
    }
}
