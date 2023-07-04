/**
 * @(#)PR_E_SET_S02Output.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 *
 */
package nttdm.bsys.e_set.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * PR_E_SET_S02Output class.
 */
public class PR_E_SET_S02Output implements Serializable {

    private static final long serialVersionUID = 2606585411158646982L;

    private String SCR_ID;

    private String FUNC_ID;

    private String TITLE_HDR;

    private Integer row;

    private Integer totalRow;

    private Integer startIndex;

    private List<Map<String, Object>> listHistories;

    public String getSCR_ID() {
        return SCR_ID;
    }

    public void setSCR_ID(String SCR_ID) {
        this.SCR_ID = SCR_ID;
    }

    public String getFUNC_ID() {
        return FUNC_ID;
    }

    public void setFUNC_ID(String FUNC_ID) {
        this.FUNC_ID = FUNC_ID;
    }

    public String getTITLE_HDR() {
        return TITLE_HDR;
    }

    public void setTITLE_HDR(String TITLE_HDR) {
        this.TITLE_HDR = TITLE_HDR;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
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

    public List<Map<String, Object>> getListHistories() {
        return listHistories;
    }

    public void setListHistories(List<Map<String, Object>> listHistories) {
        this.listHistories = listHistories;
    }
}
