/*
 * @(#)RP_E_DIM_SP1_01Output.java
 *
 *
 */
package nttdm.bsys.e_dim.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_DIM_SP1_01Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6564265781243535802L;

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
     * Bank Account
     */
    private String bankAcc;

    /**
     * Bank Account
     */
    private List<LabelValueBean> cbBankAcc;

    private String retStatusStr = "";
    
    public String getRetStatusStr() {
        return retStatusStr;
    }

    public void setRetStatusStr(String retStatusStr) {
        this.retStatusStr = retStatusStr;
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