/*
 * @(#)RP_E_DIM_SP1_02Input.java
 *
 *
 */
package nttdm.bsys.e_dim.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_DIM_SP1_02Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7462690362176644482L;

    /**
     * 
     */
    private FormFile formFile;

    /**
     * 
     */
    private String month;

    /**
     * 
     */
    private String year;

    /**
     * 
     */
    private BillingSystemUserValueObject uvo;

    /**
     * Bank Account
     */
    private String bankAcc;

    /**
     * Bank Account
     */
    private List<LabelValueBean> cbBankAcc;

    /**
     * formFile を取得する
     * 
     * @return formFile
     */
    public FormFile getFormFile() {
        return formFile;
    }

    /**
     * formFile を設定する
     * 
     * @param formFile
     */
    public void setFormFile(FormFile formFile) {
        this.formFile = formFile;
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

}