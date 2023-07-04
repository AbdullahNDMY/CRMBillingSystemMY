/*
 * @(#)RP_E_MIM_US3_01Output.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class RP_E_MIM_US3_01Output implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private List<HashMap<String,Object>> listHistory;

    private Integer totalRow;

    private Integer startIndex;

    private Integer row;

    private String month;

    private String year;

    private List<org.apache.struts.util.LabelValueBean> listMonth;

    private List<org.apache.struts.util.LabelValueBean> listYear;

    private HashMap<String, Object> batchScheduleInfo;
    
    private String selDay;
    
    private String selHour;
    
    private String selMinute;
    
    private String activeStatus;

    public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getSelDay() {
		return selDay;
	}

	public void setSelDay(String selDay) {
		this.selDay = selDay;
	}

	public String getSelHour() {
		return selHour;
	}

	public void setSelHour(String selHour) {
		this.selHour = selHour;
	}

	public String getSelMinute() {
		return selMinute;
	}

	public void setSelMinute(String selMinute) {
		this.selMinute = selMinute;
	}

	public List<HashMap<String,Object>> getListHistory() {
    	return listHistory;
    }

    public void setListHistory(List<HashMap<String,Object>> listHistory) {
    	this.listHistory = listHistory;
    }

    public Integer getTotalRow() {
    	return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
    	this.totalRow = totalRow;
    }

    public Integer getStartIndex() {
    	return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
    	this.startIndex = startIndex;
    }

    public Integer getRow() {
    	return row;
    }

    public void setRow(Integer row) {
    	this.row = row;
    }

    public String getMonth() {
    	return month;
    }

    public void setMonth(String month) {
    	this.month = month;
    }

    public String getYear() {
    	return year;
    }

    public void setYear(String year) {
    	this.year = year;
    }

    public List<org.apache.struts.util.LabelValueBean> getListMonth() {
    	return listMonth;
    }

    public void setListMonth(List<org.apache.struts.util.LabelValueBean> listMonth) {
    	this.listMonth = listMonth;
    }

    public List<org.apache.struts.util.LabelValueBean> getListYear() {
    	return listYear;
    }

    public void setListYear(List<org.apache.struts.util.LabelValueBean> listYear) {
    	this.listYear = listYear;
    }

    public HashMap<String, Object> getBatchScheduleInfo() {
    	return batchScheduleInfo;
    }

    public void setBatchScheduleInfo(HashMap<String, Object> batchScheduleInfo) {
    	this.batchScheduleInfo = batchScheduleInfo;
    }

}