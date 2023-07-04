package nttdm.bsys.common.bean;

public class T_SET_BATCH {
	private String ID_BATCH;
	private String BATCH_TYPE;
	private String STATUS;
	private String FILENAME;
	private String ID_LOGIN;
	private String message;
	private String monthyear;
	public String getMessage() {
		return message;
	}
	public T_SET_BATCH()
	{
		ID_BATCH=" ";
		BATCH_TYPE=" ";
		STATUS=" ";
		FILENAME=" ";
		ID_LOGIN=" ";
		message=" "; 
		monthyear = "";
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getID_BATCH() {
		return ID_BATCH;
	}
	public void setID_BATCH(String id_batch) {
		ID_BATCH = id_batch;
	}
	public String getBATCH_TYPE() {
		return BATCH_TYPE;
	}
	public void setBATCH_TYPE(String batch_type) {
		BATCH_TYPE = batch_type;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String status) {
		STATUS = status;
	}
	public String getFILENAME() {
		return FILENAME;
	}
	public void setFILENAME(String filename) {
		FILENAME = filename;
	}
	public String getID_LOGIN() {
		return ID_LOGIN;
	}
	public void setID_LOGIN(String id_login) {
		ID_LOGIN = id_login;
	}
    /**
     * @return the monthyear
     */
    public String getMonthyear() {
        return monthyear;
    }
    /**
     * @param monthyear the monthyear to set
     */
    public void setMonthyear(String monthyear) {
        this.monthyear = monthyear;
    }
}
