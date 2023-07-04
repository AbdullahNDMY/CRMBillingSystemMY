/**
 * @(#)FileImportExportException.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_cst.common;

/**
 * A exception for file import and export
 * 
 * @author p-minzh
 */
public class FileImportExportException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7728236141215276442L;

	/**
	 * <div>Constructor</div>
	 * 
	 * @param errorMessage
	 *            String
	 * 
	 */
	public FileImportExportException(String errorMessage) {
		super(errorMessage);
	}

}
