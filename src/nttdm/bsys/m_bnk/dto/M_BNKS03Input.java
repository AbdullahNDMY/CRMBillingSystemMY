package nttdm.bsys.m_bnk.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class M_BNKS03Input implements Serializable {

    private static final long serialVersionUID = -7466401836857442359L;

    private String bank_fullname;
    private String bank_code;
    private String branch_code;
    private String index;

    private int startIndex;
    private int totalCount;
    private int row;
    private int checkpagetype = 0;

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCheckpagetype() {
        return checkpagetype;
    }

    public void setCheckpagetype(int checkpagetype) {
        this.checkpagetype = checkpagetype;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    private BillingSystemUserValueObject uvo;

    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    public String getBank_fullname() {
        return bank_fullname;
    }

    public void setBank_fullname(String bank_fullname) {
        this.bank_fullname = bank_fullname;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
