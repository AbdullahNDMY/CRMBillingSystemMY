package nttdm.bsys.g_alt;

import java.io.Serializable;

public class M_ALT_HDto implements Serializable {
	private String msgType;
	private String status;
	private String repeatMode;
	private String toMsg;
	private String subject;
	private String msg;
	private String idMsg;
	private String idLogin;
	private String isDelete = "0";
	
	
	
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public M_ALT_HDto(){
		
	}
	public M_ALT_HDto(String msgType, String status, String repeatMode, String toMsg, String subject, String msg, String idLogin){
		this.msgType = msgType;
		this.status = status;
		this.repeatMode = repeatMode;
		this.toMsg = toMsg;
		this.subject = subject;
		this.msg = msg;
		this.idLogin = idLogin;
	}
	
	public String getIdMsg() {
		return idMsg;
	}
	public void setIdMsg(String idMsg) {
		this.idMsg = idMsg;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRepeatMode() {
		return repeatMode;
	}
	public void setRepeatMode(String repeatMode) {
		this.repeatMode = repeatMode;
	}
	public String getToMsg() {
		return toMsg;
	}
	public void setToMsg(String toMsg) {
		this.toMsg = toMsg;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
