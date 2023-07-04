/*
 * @(#)RP_E_MIM_US2_01Output.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US2_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6808298391364224140L;

	/**
     * 
     */
	private String example;

	/**
     * 
     */
	private Integer totalRow;

	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;

	/**
     * 
     */
	private List<HashMap> listHistories;

	/**
     * 
     */
	private List<LabelValueBean> listMonth;

	/**
     * 
     */
	private List<LabelValueBean> listYear;

	/**
     * 
     */
	private String month;

	/**
     * 
     */
	private String year;

	private String retStatusStr = "";
	
    public String getRetStatusStr() {
        return retStatusStr;
    }

    public void setRetStatusStr(String retStatusStr) {
        this.retStatusStr = retStatusStr;
    }

    /**
	 * example を取得する
	 * 
	 * @return example
	 */
	public String getExample() {
		return example;
	}

	/**
	 * example を設定する
	 * 
	 * @param example
	 */
	public void setExample(String example) {
		this.example = example;
	}

	/**
	 * totalRow を取得する
	 * 
	 * @return totalRow
	 */
	public Integer getTotalRow() {
		return totalRow;
	}

	/**
	 * totalRow を設定する
	 * 
	 * @param totalRow
	 */
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	/**
	 * row を取得する
	 * 
	 * @return row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * row を設定する
	 * 
	 * @param row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * startIndex を取得する
	 * 
	 * @return startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * startIndex を設定する
	 * 
	 * @param startIndex
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * listHistories を取得する
	 * 
	 * @return listHistories
	 */
	public List<HashMap> getListHistories() {
		return listHistories;
	}

	/**
	 * listHistories を設定する
	 * 
	 * @param listHistories
	 */
	public void setListHistories(List<HashMap> listHistories) {
		this.listHistories = listHistories;
	}

	/**
	 * listMonth を取得する
	 * 
	 * @return listMonth
	 */
	public List<LabelValueBean> getListMonth() {
		return listMonth;
	}

	/**
	 * listMonth を設定する
	 * 
	 * @param listMonth
	 */
	public void setListMonth(List<LabelValueBean> listMonth) {
		this.listMonth = listMonth;
	}

	/**
	 * listYear を取得する
	 * 
	 * @return listYear
	 */
	public List<LabelValueBean> getListYear() {
		return listYear;
	}

	/**
	 * listYear を設定する
	 * 
	 * @param listYear
	 */
	public void setListYear(List<LabelValueBean> listYear) {
		this.listYear = listYear;
	}

	/**
	 * month を取得する
	 * 
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * month を設定する
	 * 
	 * @param month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * year を取得する
	 * 
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * year を設定する
	 * 
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}

}