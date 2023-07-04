/*
 * @(#)RP_E_MEX_CT1Input.java
 *
 *
 */
package nttdm.bsys.e_mex.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author thinhtv
 */
public class RP_E_MEX_CT1Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6279851983955987953L;

    /**
     * 
     */
    private String forward_update;

    /**
     * 
     */
    private String forward_execute;

    /**
     * 
     */
    private String forward_pageLinks;

    /**
     * 
     */
    private BillingSystemUserValueObject uvo;

    /**
     * 
     */
    private String runtimeType;

    /**
     * 
     */
    private String scheduleStatus;

    /**
     * 
     */
    private String scheduleDay;

    /**
     * 
     */
    private String scheduleHour;

    /**
     * 
     */
    private String scheduleMinute;

    /**
     * 
     */
    private String closingMonth;

    /**
     * 
     */
    private String closingYear;

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
    private String event;

    /**
     * Bank Account
     */
    private String bankAcc;

    /**
     * Bank Account
     */
    private List<LabelValueBean> cbBankAcc;

    /**
     * @return the bankAcc
     */
    public String getBankAcc() {
        return bankAcc;
    }

    /**
     * @param bankAcc
     *            the bankAcc to set
     */
    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    /**
     * @return the cbBankAcc
     */
    public List<LabelValueBean> getCbBankAcc() {
        return cbBankAcc;
    }

    /**
     * @param cbBankAcc
     *            the cbBankAcc to set
     */
    public void setCbBankAcc(List<LabelValueBean> cbBankAcc) {
        this.cbBankAcc = cbBankAcc;
    }

    /**
     * SCR
     */
    private boolean scr;

    /**
     * @return the src
     */
    public boolean isScr() {
        return scr;
    }

    /**
     * @param scr
     *            the src to set
     */
    public void setScr(boolean scr) {
        this.scr = scr;
    }

    /**
     * forward_update を取得する
     * 
     * @return forward_update
     */
    public String getForward_update() {
        return forward_update;
    }

    /**
     * forward_update を設定する
     * 
     * @param forward_update
     */
    public void setForward_update(String forward_update) {
        this.forward_update = forward_update;
    }

    /**
     * forward_execute を取得する
     * 
     * @return forward_execute
     */
    public String getForward_execute() {
        return forward_execute;
    }

    /**
     * forward_execute を設定する
     * 
     * @param forward_execute
     */
    public void setForward_execute(String forward_execute) {
        this.forward_execute = forward_execute;
    }

    /**
     * forward_pageLinks を取得する
     * 
     * @return forward_pageLinks
     */
    public String getForward_pageLinks() {
        return forward_pageLinks;
    }

    /**
     * forward_pageLinks を設定する
     * 
     * @param forward_pageLinks
     */
    public void setForward_pageLinks(String forward_pageLinks) {
        this.forward_pageLinks = forward_pageLinks;
    }

    /**
     * uvo を取得する
     * 
     * @return uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * uvo を設定する
     * 
     * @param uvo
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    /**
     * runtimeType を取得する
     * 
     * @return runtimeType
     */
    public String getRuntimeType() {
        return runtimeType;
    }

    /**
     * runtimeType を設定する
     * 
     * @param runtimeType
     */
    public void setRuntimeType(String runtimeType) {
        this.runtimeType = runtimeType;
    }

    /**
     * scheduleStatus を取得する
     * 
     * @return scheduleStatus
     */
    public String getScheduleStatus() {
        return scheduleStatus;
    }

    /**
     * scheduleStatus を設定する
     * 
     * @param scheduleStatus
     */
    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    /**
     * scheduleDay を取得する
     * 
     * @return scheduleDay
     */
    public String getScheduleDay() {
        return scheduleDay;
    }

    /**
     * scheduleDay を設定する
     * 
     * @param scheduleDay
     */
    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    /**
     * scheduleHour を取得する
     * 
     * @return scheduleHour
     */
    public String getScheduleHour() {
        return scheduleHour;
    }

    /**
     * scheduleHour を設定する
     * 
     * @param scheduleHour
     */
    public void setScheduleHour(String scheduleHour) {
        this.scheduleHour = scheduleHour;
    }

    /**
     * scheduleMinute を取得する
     * 
     * @return scheduleMinute
     */
    public String getScheduleMinute() {
        return scheduleMinute;
    }

    /**
     * scheduleMinute を設定する
     * 
     * @param scheduleMinute
     */
    public void setScheduleMinute(String scheduleMinute) {
        this.scheduleMinute = scheduleMinute;
    }

    /**
     * closingMonth を取得する
     * 
     * @return closingMonth
     */
    public String getClosingMonth() {
        return closingMonth;
    }

    /**
     * closingMonth を設定する
     * 
     * @param closingMonth
     */
    public void setClosingMonth(String closingMonth) {
        this.closingMonth = closingMonth;
    }

    /**
     * closingYear を取得する
     * 
     * @return closingYear
     */
    public String getClosingYear() {
        return closingYear;
    }

    /**
     * closingYear を設定する
     * 
     * @param closingYear
     */
    public void setClosingYear(String closingYear) {
        this.closingYear = closingYear;
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
     * event を取得する
     * 
     * @return event
     */
    public String getEvent() {
        return event;
    }

    /**
     * event を設定する
     * 
     * @param event
     */
    public void setEvent(String event) {
        this.event = event;
    }

}