package nttdm.bsys.c_cmn001.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import nttdm.bsys.common.util.BillingSystemConstants;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class C_CMN001FormBean extends ValidatorActionFormEx{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5471385394462378902L;
	
	private String id_user;
	
	private String password;
	
	private int login_attempt;
	
	private String login_time = null;

	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
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
}
