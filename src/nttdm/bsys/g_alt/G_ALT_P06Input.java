package nttdm.bsys.g_alt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class G_ALT_P06Input implements Serializable {
	private List listAlertUser = new ArrayList<AlertUserDto>();
	private String[] batchIds;
	private String[] subjects;
	private String[] msgs;
	private String filename;
	private String filelocation;
	private String batchId;
	private String subject;
	private String msg;
	
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	public List getListAlertUser() {
		return listAlertUser;
	}
	public void setListAlertUser(List listAlertUser) {
		this.listAlertUser = listAlertUser;
	}
	public String[] getBatchIds() {
		return batchIds;
	}
	public void setBatchIds(String[] batchIds) {
		this.batchIds = batchIds;
	}
	public String[] getSubjects() {
		return subjects;
	}
	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}
	public String[] getMsgs() {
		return msgs;
	}
	public void setMsgs(String[] msgs) {
		this.msgs = msgs;
	}
    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }
    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    /**
     * @return the filelocation
     */
    public String getFilelocation() {
        return filelocation;
    }
    /**
     * @param filelocation the filelocation to set
     */
    public void setFilelocation(String filelocation) {
        this.filelocation = filelocation;
    }

}
