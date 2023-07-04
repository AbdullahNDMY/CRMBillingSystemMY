/*
 * @(#)M_ALTR03Input.java
 *
 *
 */
package nttdm.bsys.m_alt.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author ss051
 */
public class M_ALTR03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6411949541509940963L;

	
	private String fullFileName;
	
	public String getFullFileName() {
		return fullFileName;
	}

	public void setFullFileName(String fullFileName) {
		this.fullFileName = fullFileName;
	}

}