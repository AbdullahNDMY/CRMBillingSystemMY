package nttdm.bsys.c_cmn001.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;

public class C_CMN001R01Output implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6594098239753929416L;
	
	private BillingSystemUserValueObject uvo;
	
	private int login_attempt;
	
	private String login_time = null;
	
	private String sessionDirectory;

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public int getLogin_attempt() {
		return login_attempt;
	}

	public void setLogin_attempt(int login_attempt) {
		this.login_attempt = login_attempt;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public String getLogin_time() {
		if (login_time == null){
			SimpleDateFormat sdf = 
				new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT);
			return sdf.format(new Date());
		}
		return login_time;
	}

	public String getSessionDirectory() {
		return sessionDirectory;
	}

	public void setSessionDirectory(String sessionDirectory) {
		this.sessionDirectory = sessionDirectory;
	}
}
