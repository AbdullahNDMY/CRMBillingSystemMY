package nttdm.bsys.m_cur.bean;

import java.io.Serializable;
 

public class M_CURBean implements Serializable{
	private static final long serialVersionUID = -5538432094237818425L;
	private String rate_date;
	private String cur_code; 
	private String rate_to;
	private String rate_from;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private String id_login;
	private String index;
	
	private String datefrom;
	private String dateto; 
	private String cur_code_search; 
	private Integer row;
	private Integer startIndex=0;
	private boolean duplicate = false;
	
	public boolean isDuplicate() {
		return duplicate;
	}
	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}
	public String getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateto() {
		return dateto;
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	public String getCur_code_search() {
		return cur_code_search;
	}
	public void setCur_code_search(String cur_code_search) {
		this.cur_code_search = cur_code_search;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	} 	 
	public String getRate_date() {
		return rate_date;
	}
	public void setRate_date(String rate_date) {
		this.rate_date = rate_date;
	}
	public String getCur_code() {
		return cur_code;
	}
	public void setCur_code(String cur_code) {
		this.cur_code = cur_code;
	}
	public String getRate_to() {
		return rate_to;
	}
	public void setRate_to(String rate_to) {
		this.rate_to = rate_to;
	}
	public String getRate_from() {
		return rate_from;
	}
	public void setRate_from(String rate_from) {
		this.rate_from = rate_from;
	}
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	} 

}
