/**
 * @(#)MailException.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

/**
 * About mail exception.
 */
public class MailException extends RuntimeException {
	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param errorMessage
	 *            String
	 */
	public MailException(String errorMessage) {
		super(errorMessage);
	}
}
