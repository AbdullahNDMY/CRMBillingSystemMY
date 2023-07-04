package nttdm.bsys.m_cur.dto;

import java.io.Serializable;
import java.util.List; 

import nttdm.bsys.m_cur.bean.M_CURBean;

public class M_CUROutput  implements Serializable{
	private static final long serialVersionUID = -5538432094237818425L;
	private List<M_CURBean> arr_m_cur;
	/**
	 * <div>totalCount</div>
	 */
	private Integer totalCount=0;
	private String datefrom;
	private String dateto;
	private String cur_code;
	/**
	 * <div>row</div>
	 */
	private M_CURBean m_cur;
	private Integer row=3;
	private Integer startIndex=0;
	private String cur_code_search;
	
	private String rate_date; 
	private String rate_to;
	private String rate_from;
	private String unsearch;
	private String clickFlg;
	
	public String getUnsearch() {
		return unsearch;
	}
	public void setUnsearch(String unsearch) {
		this.unsearch = unsearch;
	}
	
	
	public String getRate_date() {
		return rate_date;
	}
	public void setRate_date(String rate_date) {
		this.rate_date = rate_date;
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
	public M_CURBean getM_cur() {
		return m_cur;
	}
	public void setM_cur(M_CURBean m_cur) {
		this.m_cur = m_cur;
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
	public List<M_CURBean> getArr_m_cur() {
		return arr_m_cur;
	}
	public void setArr_m_cur(List<M_CURBean> arr_m_cur) {
		this.arr_m_cur = arr_m_cur;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
