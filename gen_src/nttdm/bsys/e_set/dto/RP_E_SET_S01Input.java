package nttdm.bsys.e_set.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class RP_E_SET_S01Input implements Serializable {

    private static final long serialVersionUID = -6195843300952917947L;

    private BillingSystemUserValueObject uvo;

    private String CCAlertMode;

    private String CCMode;

    private String CCMonths;

    private String CCDay;

    private String CCHour;

    private String CCMinute;

    private String[] CCUsers;

    private String CCRetStatus;

    private String SDAlertMode;

    private String SDMonths;

    private String SDMode;

    private String SDDay;

    private String SDHour;

    private String SDMinute;

    private String[] SDUsers;

    private String SDRetStatus;

    private String GBMonths;

    private String GBMode;

    private String GBDay;

    private String GBHour;

    private String GBMinute;

    private String[] GBUsers;

    private String GBRetStatus;

    private String SAMonths;

    private String SAMode;

    private String SAStatementMonth;

    private String SADay;

    private String SAHour;

    private String SAMinute;

    private String[] SAUsers;

    private String SApopupClickYesFlg;

    private String SAPopupInfo;

    private String SARunDateEntry;

    private String SARetStatus;

    private String CBCustomerType;

    private String CBMonths;

    private String CBMode;

    private String CBDay;

    private String CBHour;

    private String CBMinute;

    private String[] CBUsers;

    private String CBRetStatus;

    private String SSMonths;

    private String SSMode;

    private String SSDay;

    private String SSHour;

    private String SSMinute;

    private String[] SSUsers;

    private String ARMonths;

    private String ARMode;

    private String ARDay;

    private String ARHour;

    private String ARMinute;

    private String[] ARUsers;

    private String ARRetStatus;

    private String BACalc;

    private String[] BAUsers;

    private String BARetStatus;

    private String forward_arRun;

    private String forward_ccRun;

    private String forward_sdRun;

    private String forward_gbRun;

    private String forward_saRun;

    private String forward_ssRun;

    private String forward_cbRun;

    private String forward_baRun;

    private String forward_save;

    private List<LabelValueBean> userList;

    private String esetRunDate;

    private String esetRunDateValue;

    private String systemBase;
    
    // #192 Start
    private String billingOption;
    // #192 End

    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    public String getCCAlertMode() {
        return CCAlertMode;
    }

    public void setCCAlertMode(String cCAlertMode) {
        CCAlertMode = cCAlertMode;
    }

    public String getCCMode() {
        return CCMode;
    }

    public void setCCMode(String cCMode) {
        CCMode = cCMode;
    }

    public String getCCMonths() {
        return CCMonths;
    }

    public void setCCMonths(String cCMonths) {
        CCMonths = cCMonths;
    }

    public String getCCDay() {
        return CCDay;
    }

    public void setCCDay(String cCDay) {
        CCDay = cCDay;
    }

    public String getCCHour() {
        return CCHour;
    }

    public void setCCHour(String cCHour) {
        CCHour = cCHour;
    }

    public String getCCMinute() {
        return CCMinute;
    }

    public void setCCMinute(String cCMinute) {
        CCMinute = cCMinute;
    }

    public String[] getCCUsers() {
        return CCUsers;
    }

    public void setCCUsers(String[] cCUsers) {
        CCUsers = cCUsers;
    }

    public String getSDAlertMode() {
        return SDAlertMode;
    }

    public void setSDAlertMode(String sDAlertMode) {
        SDAlertMode = sDAlertMode;
    }

    public String getSDMonths() {
        return SDMonths;
    }

    public void setSDMonths(String sDMonths) {
        SDMonths = sDMonths;
    }

    public String getSDMode() {
        return SDMode;
    }

    public void setSDMode(String sDMode) {
        SDMode = sDMode;
    }

    public String getSDDay() {
        return SDDay;
    }

    public void setSDDay(String sDDay) {
        SDDay = sDDay;
    }

    public String getSDHour() {
        return SDHour;
    }

    public void setSDHour(String sDHour) {
        SDHour = sDHour;
    }

    public String getSDMinute() {
        return SDMinute;
    }

    public void setSDMinute(String sDMinute) {
        SDMinute = sDMinute;
    }

    public String[] getSDUsers() {
        return SDUsers;
    }

    public void setSDUsers(String[] sDUsers) {
        SDUsers = sDUsers;
    }

    public String getGBMonths() {
        return GBMonths;
    }

    public void setGBMonths(String gBMonths) {
        GBMonths = gBMonths;
    }

    public String getGBMode() {
        return GBMode;
    }

    public void setGBMode(String gBMode) {
        GBMode = gBMode;
    }

    public String getGBDay() {
        return GBDay;
    }

    public void setGBDay(String gBDay) {
        GBDay = gBDay;
    }

    public String getGBHour() {
        return GBHour;
    }

    public void setGBHour(String gBHour) {
        GBHour = gBHour;
    }

    public String getGBMinute() {
        return GBMinute;
    }

    public void setGBMinute(String gBMinute) {
        GBMinute = gBMinute;
    }

    public String[] getGBUsers() {
        return GBUsers;
    }

    public void setGBUsers(String[] gBUsers) {
        GBUsers = gBUsers;
    }

    public String getSAMonths() {
        return SAMonths;
    }

    public void setSAMonths(String sAMonths) {
        SAMonths = sAMonths;
    }

    public String getSAMode() {
        return SAMode;
    }

    public void setSAMode(String sAMode) {
        SAMode = sAMode;
    }

    public String getSAStatementMonth() {
        return SAStatementMonth;
    }

    public void setSAStatementMonth(String sAStatementMonth) {
        SAStatementMonth = sAStatementMonth;
    }

    public String getSADay() {
        return SADay;
    }

    public void setSADay(String sADay) {
        SADay = sADay;
    }

    public String getSAHour() {
        return SAHour;
    }

    public void setSAHour(String sAHour) {
        SAHour = sAHour;
    }

    public String getSAMinute() {
        return SAMinute;
    }

    public void setSAMinute(String sAMinute) {
        SAMinute = sAMinute;
    }

    public String[] getSAUsers() {
        return SAUsers;
    }

    public void setSAUsers(String[] sAUsers) {
        SAUsers = sAUsers;
    }

    public String getCBMonths() {
        return CBMonths;
    }

    public void setCBMonths(String cBMonths) {
        CBMonths = cBMonths;
    }

    public String getCBMode() {
        return CBMode;
    }

    public void setCBMode(String cBMode) {
        CBMode = cBMode;
    }

    public String getCBDay() {
        return CBDay;
    }

    public void setCBDay(String cBDay) {
        CBDay = cBDay;
    }

    public String getCBHour() {
        return CBHour;
    }

    public void setCBHour(String cBHour) {
        CBHour = cBHour;
    }

    public String getCBMinute() {
        return CBMinute;
    }

    public void setCBMinute(String cBMinute) {
        CBMinute = cBMinute;
    }

    public String[] getCBUsers() {
        return CBUsers;
    }

    public void setCBUsers(String[] cBUsers) {
        CBUsers = cBUsers;
    }

    public String getSSMonths() {
        return SSMonths;
    }

    public void setSSMonths(String sSMonths) {
        SSMonths = sSMonths;
    }

    public String getSSMode() {
        return SSMode;
    }

    public void setSSMode(String sSMode) {
        SSMode = sSMode;
    }

    public String getSSDay() {
        return SSDay;
    }

    public void setSSDay(String sSDay) {
        SSDay = sSDay;
    }

    public String getSSHour() {
        return SSHour;
    }

    public void setSSHour(String sSHour) {
        SSHour = sSHour;
    }

    public String getSSMinute() {
        return SSMinute;
    }

    public void setSSMinute(String sSMinute) {
        SSMinute = sSMinute;
    }

    public String[] getSSUsers() {
        return SSUsers;
    }

    public void setSSUsers(String[] sSUsers) {
        SSUsers = sSUsers;
    }

    public String getARMonths() {
        return ARMonths;
    }

    public void setARMonths(String aRMonths) {
        ARMonths = aRMonths;
    }

    public String getARMode() {
        return ARMode;
    }

    public void setARMode(String aRMode) {
        ARMode = aRMode;
    }

    public String getARDay() {
        return ARDay;
    }

    public void setARDay(String aRDay) {
        ARDay = aRDay;
    }

    public String getARHour() {
        return ARHour;
    }

    public void setARHour(String aRHour) {
        ARHour = aRHour;
    }

    public String getARMinute() {
        return ARMinute;
    }

    public void setARMinute(String aRMinute) {
        ARMinute = aRMinute;
    }

    public String[] getARUsers() {
        return ARUsers;
    }

    public void setARUsers(String[] aRUsers) {
        ARUsers = aRUsers;
    }

    public String getForward_arRun() {
        return forward_arRun;
    }

    public void setForward_arRun(String forward_arRun) {
        this.forward_arRun = forward_arRun;
    }

    public String getForward_ccRun() {
        return forward_ccRun;
    }

    public void setForward_ccRun(String forward_ccRun) {
        this.forward_ccRun = forward_ccRun;
    }

    public String getForward_sdRun() {
        return forward_sdRun;
    }

    public void setForward_sdRun(String forward_sdRun) {
        this.forward_sdRun = forward_sdRun;
    }

    public String getForward_gbRun() {
        return forward_gbRun;
    }

    public void setForward_gbRun(String forward_gbRun) {
        this.forward_gbRun = forward_gbRun;
    }

    public String getForward_saRun() {
        return forward_saRun;
    }

    public void setForward_saRun(String forward_saRun) {
        this.forward_saRun = forward_saRun;
    }

    public String getForward_ssRun() {
        return forward_ssRun;
    }

    public void setForward_ssRun(String forward_ssRun) {
        this.forward_ssRun = forward_ssRun;
    }

    public String getForward_cbRun() {
        return forward_cbRun;
    }

    public void setForward_cbRun(String forward_cbRun) {
        this.forward_cbRun = forward_cbRun;
    }

    public String getForward_save() {
        return forward_save;
    }

    public void setForward_save(String forward_save) {
        this.forward_save = forward_save;
    }

    public List<LabelValueBean> getUserList() {
        return userList;
    }

    public void setUserList(List<LabelValueBean> userList) {
        this.userList = userList;
    }

    public String getEsetRunDate() {
        return esetRunDate;
    }

    public void setEsetRunDate(String esetRunDate) {
        this.esetRunDate = esetRunDate;
    }

    public String getEsetRunDateValue() {
        return esetRunDateValue;
    }

    public void setEsetRunDateValue(String esetRunDateValue) {
        this.esetRunDateValue = esetRunDateValue;
    }

    public String getSystemBase() {
        return systemBase;
    }

    public void setSystemBase(String systemBase) {
        this.systemBase = systemBase;
    }

    /**
     * @return the sApopupClickYesFlg
     */
    public String getSApopupClickYesFlg() {
        return SApopupClickYesFlg;
    }

    /**
     * @param sApopupClickYesFlg
     *            the sApopupClickYesFlg to set
     */
    public void setSApopupClickYesFlg(String sApopupClickYesFlg) {
        SApopupClickYesFlg = sApopupClickYesFlg;
    }

    /**
     * @return the sAPopupInfo
     */
    public String getSAPopupInfo() {
        return SAPopupInfo;
    }

    /**
     * @param sAPopupInfo
     *            the sAPopupInfo to set
     */
    public void setSAPopupInfo(String sAPopupInfo) {
        SAPopupInfo = sAPopupInfo;
    }

    /**
     * @return the sARunDateEntry
     */
    public String getSARunDateEntry() {
        return SARunDateEntry;
    }

    /**
     * @param sARunDateEntry
     *            the sARunDateEntry to set
     */
    public void setSARunDateEntry(String sARunDateEntry) {
        SARunDateEntry = sARunDateEntry;
    }

    public String getBACalc() {
        return BACalc;
    }

    public void setBACalc(String bACalc) {
        BACalc = bACalc;
    }

    public String[] getBAUsers() {
        return BAUsers;
    }

    public void setBAUsers(String[] bAUsers) {
        BAUsers = bAUsers;
    }

    public String getForward_baRun() {
        return forward_baRun;
    }

    public void setForward_baRun(String forward_baRun) {
        this.forward_baRun = forward_baRun;
    }

    /**
     * @return the cCRetStatus
     */
    public String getCCRetStatus() {
        return CCRetStatus;
    }

    /**
     * @param cCRetStatus
     *            the cCRetStatus to set
     */
    public void setCCRetStatus(String cCRetStatus) {
        CCRetStatus = cCRetStatus;
    }

    /**
     * @return the sDRetStatus
     */
    public String getSDRetStatus() {
        return SDRetStatus;
    }

    /**
     * @param sDRetStatus
     *            the sDRetStatus to set
     */
    public void setSDRetStatus(String sDRetStatus) {
        SDRetStatus = sDRetStatus;
    }

    /**
     * @return the gBRetStatus
     */
    public String getGBRetStatus() {
        return GBRetStatus;
    }

    /**
     * @param gBRetStatus
     *            the gBRetStatus to set
     */
    public void setGBRetStatus(String gBRetStatus) {
        GBRetStatus = gBRetStatus;
    }

    /**
     * @return the sARetStatus
     */
    public String getSARetStatus() {
        return SARetStatus;
    }

    /**
     * @param sARetStatus
     *            the sARetStatus to set
     */
    public void setSARetStatus(String sARetStatus) {
        SARetStatus = sARetStatus;
    }

    /**
     * @return the cBRetStatus
     */
    public String getCBRetStatus() {
        return CBRetStatus;
    }

    /**
     * @param cBRetStatus
     *            the cBRetStatus to set
     */
    public void setCBRetStatus(String cBRetStatus) {
        CBRetStatus = cBRetStatus;
    }

    /**
     * @return the aRRetStatus
     */
    public String getARRetStatus() {
        return ARRetStatus;
    }

    /**
     * @param aRRetStatus
     *            the aRRetStatus to set
     */
    public void setARRetStatus(String aRRetStatus) {
        ARRetStatus = aRRetStatus;
    }

    /**
     * @return the bARetStatus
     */
    public String getBARetStatus() {
        return BARetStatus;
    }

    /**
     * @param bARetStatus
     *            the bARetStatus to set
     */
    public void setBARetStatus(String bARetStatus) {
        BARetStatus = bARetStatus;
    }

    public String getCBCustomerType() {
        return CBCustomerType;
    }

    public void setCBCustomerType(String cBCustomerType) {
        CBCustomerType = cBCustomerType;
    }

	/**
	 * @return the billingOption
	 */
	public String getBillingOption() {
		return billingOption;
	}

	/**
	 * @param billingOption the billingOption to set
	 */
	public void setBillingOption(String billingOption) {
		this.billingOption = billingOption;
	}


}