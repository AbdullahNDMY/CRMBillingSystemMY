/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Subscription Information
 * SERVICE NAME : B_SSM_S03
 * FUNCTION : processing reports from B_SSM_S03
 * FILE NAME : ContentType.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 */
package nttdm.bsys.b_ssm.s03.b_rpt.data;

/**
 * Content type of download file<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public enum ContentType {
	OCTET_STREAM("application/octet-stream"), 
	MS_WORD("application/msword; charset=UTF-8"),
	MS_EXCEL("application/x-excel"),
	PDF("application/pdf"),
	ZIP("application/zip");
	
	private String value;
	
	ContentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
