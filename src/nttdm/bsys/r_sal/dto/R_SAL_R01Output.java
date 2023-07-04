/**
 * @(#)R_SAL_R01Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * R_SAL_R01Output class.
 * 
 * @author p-minzh
 */
public class R_SAL_R01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7112562778671150991L;

	/**
     * 
     */
	private String accessType;

	/**
	 * singPost
	 */
	private String chkSingPost;

	/**
	 * manual
	 */
	private String chkManual;

	/**
	 * service groups
	 */
	private List<LabelValueBean> cbServiceGroups = new ArrayList<LabelValueBean>();
	
	/**
     * 
     */
    private String issueTypeSingpost;
    
    /**
     * 
     */
    private String issueTypeAuto;
    
    /**
     * 
     */
    private String issueTypeManual;

	public List<LabelValueBean> getCbServiceGroups() {
		return cbServiceGroups;
	}

	public void setCbServiceGroups(List<LabelValueBean> cbServiceGroups) {
		this.cbServiceGroups = cbServiceGroups;
	}

	/**
	 * @return the accessType
	 */
	public String getAccessType() {
		return accessType;
	}

	/**
	 * @param accessType
	 *            the accessType to set
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	/**
	 * @return the chkSingPost
	 */
	public String getChkSingPost() {
		return chkSingPost;
	}

	/**
	 * @param chkSingPost
	 *            the chkSingPost to set
	 */
	public void setChkSingPost(String chkSingPost) {
		this.chkSingPost = chkSingPost;
	}

	/**
	 * @return the chkManual
	 */
	public String getChkManual() {
		return chkManual;
	}

	/**
	 * @param chkManual
	 *            the chkManual to set
	 */
	public void setChkManual(String chkManual) {
		this.chkManual = chkManual;
	}

    /**
     * @return the issueTypeSingpost
     */
    public String getIssueTypeSingpost() {
        return issueTypeSingpost;
    }

    /**
     * @param issueTypeSingpost the issueTypeSingpost to set
     */
    public void setIssueTypeSingpost(String issueTypeSingpost) {
        this.issueTypeSingpost = issueTypeSingpost;
    }

    /**
     * @return the issueTypeAuto
     */
    public String getIssueTypeAuto() {
        return issueTypeAuto;
    }

    /**
     * @param issueTypeAuto the issueTypeAuto to set
     */
    public void setIssueTypeAuto(String issueTypeAuto) {
        this.issueTypeAuto = issueTypeAuto;
    }

    /**
     * @return the issueTypeManual
     */
    public String getIssueTypeManual() {
        return issueTypeManual;
    }

    /**
     * @param issueTypeManual the issueTypeManual to set
     */
    public void setIssueTypeManual(String issueTypeManual) {
        this.issueTypeManual = issueTypeManual;
    }
}