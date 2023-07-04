package nttdm.bsys.m_cur.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class M_CURInput implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private String datefrom;
	private String dateto;
	private String cur_code;
	private String cur_code_search;
	/**
	 * <div>row</div>
	 */
	private Integer row;
	private Integer startIndex=0;
	private String rate_date; 
	private String cur_name;
	private String rate_to;
	private String rate_from; 
	private String id_login;
	private String date_created;
	private BillingSystemUserValueObject uvo;
	private String unsearch;
	private String index;
	private boolean duplicate = false;
	private Integer idAudit = null;
	
	private String clickFlg;
	
	public boolean isDuplicate() {
		return duplicate;
	}
	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getUnsearch() {
		if(unsearch==null)
		{
			return "";
		}
		return unsearch;
	}
	public void setUnsearch(String unsearch) {
		this.unsearch = unsearch;
	}
	public String getCur_code_search() {
		return cur_code_search;
	}
	public void setCur_code_search(String cur_code_search) {
		this.cur_code_search = cur_code_search;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getRate_date() {
		return rate_date;
	}
	public void setRate_date(String rate_date) {
		this.rate_date = rate_date;
	}
	public String getCur_name() {
		return cur_name;
	}
	public void setCur_name(String cur_name) {
		this.cur_name = cur_name;
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
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
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
	public String getCur_code() {
		return cur_code;
	}
	public void setCur_code(String cur_code) {
		this.cur_code = cur_code;
	}	
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
    /**
     * @return the clickFlg
     */
    public String getClickFlg() {
        return clickFlg;
    }
    /**
     * @param clickFlg the clickFlg to set
     */
    public void setClickFlg(String clickFlg) {
        this.clickFlg = clickFlg;
    }
}