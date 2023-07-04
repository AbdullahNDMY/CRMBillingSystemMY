/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Subscription Information
 * SERVICE NAME : B_SSM_S03
 * FUNCTION : processing reports from B_SSM_S03
 * FILE NAME : B_RPT_Output.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 */
package nttdm.bsys.b_ssm.s03.b_rpt.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Java ouput bean for all export<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_RPT_Output {
	
	private InputStream inputStream;
	
	private List<InputStream> inputStreams;
	
	private ContentType contentType;
	
	private String fileName;
	
	private List<String> fileNames;

	private String packageName;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<InputStream> getInputStreams() {	
		if (inputStreams == null) {
			inputStreams = new ArrayList<InputStream>();
		}
		return inputStreams;
	}

	public void setInputStreams(List<InputStream> inputStreams) {
		this.inputStreams = inputStreams;
	}

	public void setFileNames(List<String> fileNames) {		
		this.fileNames = fileNames;
	}

	public List<String> getFileNames() {
		if (fileNames == null) {
			fileNames = new ArrayList<String>();
		}
		return fileNames;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getPackageName() {
		return packageName;
	}
}
