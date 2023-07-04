/**
 * Billing System
 * 
 * SUBSYSTEM NAME : RP_B_BTH_P01 
 * SERVICE NAME :  B_BTH
 * FUNCTION : Layout Billing Document
 * FILE NAME : ExportBillDocument.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 * 2011/09/22 [Duoc Nguyen] Modify template path
 * 2011/11/04 [Duoc Nguyen] fix bug #2865
 */
package nttdm.bsys.b_bth.blogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import nttdm.bsys.common.util.ZipUtil;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Action export file RP_B_BTH_P01
 * 
 */
public class ExportBillDocument {
	
	public static String ID_MODULE="B";
	public static String ID_SUB_MODULE="B-BTH";
	public static String ACTION_AUTHENTICATION_FAILED="failed";
	public static String ACTION_EDIT="edit";
	public static String ACTION_VIEW="view";
	public static final String ID_SET_NOPRINT = "NOPRINTDOC";
	public static final int ACTIVE_SET_SEQ = 1;
	public static final String NOT_DELETED = "0";
	private String template_path="";
	
	/*
	 * Constructor
	 */
	public ExportBillDocument(String template_path){
		this.template_path=template_path;
	}
	
	public String getTemplate_path() {
		return template_path;
	}

	public void setTemplate_path(String template_path) {
		this.template_path = template_path;
	}

	public File billdocument(Map<String, Object> data,String SESSION_DIRECTORY) throws FileNotFoundException, IllegalAccessException,
		ClassNotFoundException, Exception {
	    //JasperPrint jasperPrint = JasperFillManager.fillReport(classLoader.getResourceAsStream("../report_pdf/billDocument.jasper"), data, new JREmptyDataSource());            
		JasperPrint jasperPrint = JasperFillManager.fillReport(this.template_path, data, new JREmptyDataSource());
	    String fileoutput= SESSION_DIRECTORY + File.separator + data.get("idRef").toString().trim() +".pdf";            
	    File outputFile = new File(fileoutput);
	    
	    JRPdfExporter exporterXLS = new JRPdfExporter();
	    exporterXLS.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
	    exporterXLS.setParameter(JRPdfExporterParameter.OUTPUT_FILE, outputFile);
//	    exporterXLS.setParameter(JRPdfExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
//	    exporterXLS.setParameter(JRPdfExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
//	    exporterXLS.setParameter(JRPdfExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
//	    exporterXLS.setParameter(JRPdfExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//	    exporterXLS.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	    exporterXLS.exportReport();
		return outputFile;
		
	}
	
}