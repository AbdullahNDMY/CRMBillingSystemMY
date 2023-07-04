/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (Export)
 * SERVICE NAME   : B-CPM-E01 Utility
 * FUNCTION       : export StreamInfo object
 * FILE NAME      : ByteArrayStreamInfo.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.struts.actions.DownloadAction.StreamInfo;

/**
 * ByteArrayStreamInfo.class<br>
 * <ul>
 * <li>override return type for StreamInfo</li>
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1
 */
public class ByteArrayStreamInfo implements StreamInfo {
	
	protected String contentType;
	protected byte[] bytes;
	
	public ByteArrayStreamInfo(String contentType, byte[] bytes) {
		this.contentType = contentType;
		this.bytes = bytes;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public ByteArrayInputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(bytes);
		}
}
