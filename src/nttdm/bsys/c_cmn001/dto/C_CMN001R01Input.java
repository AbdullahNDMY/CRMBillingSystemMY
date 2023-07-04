package nttdm.bsys.c_cmn001.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import nttdm.bsys.common.util.BillingSystemConstants;

public class C_CMN001R01Input implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3940478555002707020L;
	
	private String id_user;
	
	private String password;
	
	private int login_attempt = 1;
	
	private String login_time = null;
    
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

	public int getLogin_attempt() {
		return login_attempt;
	}

	public void setLogin_attempt(int login_attempt) {
		this.login_attempt = login_attempt;
	}

	public String getLogin_time() {
		if (login_time == null){
			SimpleDateFormat sdf = 
				new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT);
			return sdf.format(new Date());
		}
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
}
