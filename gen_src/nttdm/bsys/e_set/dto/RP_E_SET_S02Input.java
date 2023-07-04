/**
 * @(#)RP_E_SET_S02Input.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 *
 */
package nttdm.bsys.e_set.dto;

import java.io.Serializable;

/**
 * RP_E_SET_S02Input class.
 */
public class RP_E_SET_S02Input implements Serializable {

    private static final long serialVersionUID = 6581673239093022028L;

    private String SCR_ID;

    private String FUNC_ID;

    private String TITLE_HDR;

    private Integer startIndex;

    public String getSCR_ID() {
        return SCR_ID;
    }

    public void setSCR_ID(String sCR_ID) {
        SCR_ID = sCR_ID;
    }

    public String getFUNC_ID() {
        return FUNC_ID;
    }

    public void setFUNC_ID(String fUNC_ID) {
        FUNC_ID = fUNC_ID;
    }

    public String getTITLE_HDR() {
        return TITLE_HDR;
    }

    public void setTITLE_HDR(String tITLE_HDR) {
        TITLE_HDR = tITLE_HDR;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
