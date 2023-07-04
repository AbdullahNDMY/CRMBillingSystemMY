package nttdm.bsys.g_alt;

import java.io.Serializable;

public class G_ALT_P06SqlInput implements Serializable {
	private String alertUser;
	private String batchId;
	private String userType;
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAlertUser() {
		return alertUser;
	}
	public void setAlertUser(String alertUser) {
		this.alertUser = alertUser ;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
}
