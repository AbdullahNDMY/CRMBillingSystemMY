/*
 * @(#) SaleCostOutputPDF.java
 * 
 * Copyright © 2006 NTTDATA Corporation
 */
package nttdm.bsys.common.util.pdf;

import java.io.FileOutputStream;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/** 
 * SaleCostOutputPDF Class. 
 * <p> 
 * For creating PDF file (it's expected to look like the screen)
 * 
 * </p> 
 * @author DungLQ
 * @version 1.0 April 2nd, 2010
 */ 
public class OutputPDFInvoicePG1 {
	private Document docPDF = null;
	public String filePath = "";
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@SuppressWarnings("unchecked")
	private Map fullData = null;
	
	@SuppressWarnings("unchecked")
	public Map getFullData() {
		return fullData;
	}

	@SuppressWarnings("unchecked")
	public void setFullData(Map fullData) {
		this.fullData = fullData;
	}

	public OutputPDFInvoicePG1(String fileName)throws Exception{
		this.docPDF = new Document(PageSize.A4);
		this.filePath = "C:\\" + fileName;
	}
	
	public void run() throws Exception{
		PdfWriter.getInstance(this.docPDF, new FileOutputStream(this.filePath));
		this.docPDF.open();
		//PDFInvoicePG1
		PDFInvoicePG1 saleCost = new PDFInvoicePG1("", fullData);
		saleCost.addHeader(this.docPDF);
		saleCost.bindingData(this.docPDF);
	}
	
	public void dispose()throws Exception{
		if(this.docPDF!=null){
			this.docPDF.close();
		}
	}
	
}
