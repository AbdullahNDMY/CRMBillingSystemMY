/**
 * @(#)FileImportExportException.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

/**
 * A exception for file import and export
 * 
 * @author leonzh
 */
public class FileImportExportException extends RuntimeException{
	
	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = 1L;
	
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
