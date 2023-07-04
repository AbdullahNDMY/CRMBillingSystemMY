/**
 * @(#)G_SET_P01ReturnValue.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.bean;

/**
 * @author loamanma
 * 
 */
public class G_SET_ReturnValue {

    // Return Message
    private String RET_MSG = "";
    // batch ID G_SET_P01
    int batch_id = -1;
    // retStatus G_SET_P03
    int retStatus;

    public int getRetStatus() {
        return retStatus;
    }

    public void setRetStatus(int retStatus) {
        this.retStatus = retStatus;
    }

    public String getRET_MSG() {
        return RET_MSG;
    }

    public void setRET_MSG(String rET_MSG) {
        RET_MSG = rET_MSG;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

}
