/**
 * Billing System
 * 
 * SUBSYSTEM NAME : G_BTH_P01 
 * SERVICE NAME :  B_BTH_P01
 * FUNCTION : Print Billing Document
 * FILE NAME : G_BTH_P01_2.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 * 2011/09/22 [Duoc Nguyen] update generateReport4MY() method (B_BTH_F01_NTTMSC)
 * 2011/10/31 [Duoc Nguyen] fix bug #2698 ,#2703, #2704
 * 2011/11/04 [Duoc Nguyen] fix bug #2865
 * 2011/11/16 [Long Huynh] fix bug #2886
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.b_bil.blogic.B_BIL_CommonUtil;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * BusinessLogic class.
							
									  
 * 
 * @author NTT Data Vietnam Action export file Billing Document
 * 
 */
public class G_BTH_P01_SG {

	public static String ID_MODULE = "B";
	public static String ID_SUB_MODULE = "B-BTH";
	public static String ACTION_AUTHENTICATION_FAILED = "failed";
	public static String ACTION_EDIT = "edit";
	public static String ACTION_VIEW = "view";

	public static final String STATUS_SUCCESS = "0";
	public static final String STATUS_FAILED = "1";
	public static final String STATUS_INPROCESS = "2";
	public static final String STATUS_NOT_CLOSED = "0";
	public static final String STATUS_CLOSED = "1";
	public static final String STATUS_NOT_DELETED = "0";
	public static final String INVOICED = "1";
	public static final String NOT_INVOICED = "0";
	public static final String ID_CUST_PLAN = "7324";
	public static final String ID_SET_BATCH_G_SGP_P02 = "BATCH_G_BTH_P1";
	public static final String ID_BATCH_TYPE_G_SGP_P02 = "G_BTH_P01";
	public static final String ID_SET_BATCH_TIMEOUT = "BATCH_TIME_INTERVAL";
	public static final Integer SET_SEQ = 1;
	public static final String CUSTOMER_CELL = "A";
	public static final String INVOICE_CELL = "B";
	public static final String ID_SET_NOPRINT = "NOPRINTDOC";
	public static final int ACTIVE_SET_SEQ = 1;
	public static final String NOT_DELETED = "0";

	private static final int MAXIMUN_CHAR_PER_ROW = 65;
	private static final int ROW_PER_PAGE = 28;

	private static final int PER_INCH = 72;
	private static final Font font20b = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
	private static final Font font10 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	private static final Font font10b = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private static final Font font09 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
	private static final Font font09b = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
	private static final Font font11b = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
	private static final Font font08 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

	private NumberFormat formatter;
	private NumberFormat formatter4d;
	private List<Integer> realPagePrinted;

	private Document document;
	private PdfWriter pdfW = null;
	private String billingType;

	private Map<String, Object> headerInfo;
	private List<Map<String, Object>> bankInfoList;
	private Map<String, Object> companyInfo;
	private List<Map<String, Object>> detailInfo;
	private PdfPTable tableDetail;

	private BigDecimal subTotalDetail = new BigDecimal("0");
	private BigDecimal subTotalWithGST = new BigDecimal("0");
	private BigDecimal subTotalWithoutGST = new BigDecimal("0");
	private BigDecimal totalGST = new BigDecimal("0");
	private BigDecimal totalGST0 = new BigDecimal("0");
	private BigDecimal totalGSTOnSubTotal = new BigDecimal("0");
	Boolean displayGST0 = false;

	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;

	public G_BTH_P01_SG(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
	}

	public String generate(String[] idRefs, BillingSystemUserValueObject uvo) {
		String tmpFolder = uvo.getSESSION_DIRECTORY();
		List<String> pdfFilePaths = new ArrayList<String>();
		// String idStr = "|";
		// for(String idRef : idRefs) {
		// idStr += (idRef + "|");
		// }
		// idRefs = this.queryDAO.executeForObjectArray("B_BTH.reOrdersID",
		// idStr, String.class);
		// get total allowed print doc
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("idSet", ID_SET_NOPRINT);
		m.put("setSeq", ACTIVE_SET_SEQ);
		m.put("isDeleted", NOT_DELETED);
		Map<String, Object> mGsetD = this.queryDAO.executeForMap("B_BTH.getTotalAllowedPrintDoc", m);
		int totalAllowedPrintDoc = CommonUtils.toInteger(mGsetD.get("SET_VALUE"));

		for (String idRef : idRefs) {
			HashMap<String, Object> m1 = new HashMap<String, Object>();
			m1.put("idRef", idRef);
			HashMap<String, Object> conditions = (HashMap<String, Object>) this.queryDAO
					.executeForMap("B_BTH.getContentByIdRef", m1);
			// if no printed < totalAllow print doc, allow for exporting pdf
			if (conditions != null && CommonUtils.toInteger(conditions.get("NO_PRINTED")) < totalAllowedPrintDoc) {
				String filePath = tmpFolder + File.separator + idRef.trim() + ".pdf";
				pdfFilePaths.add(generate2(new String[] { idRef }, filePath, uvo, null));
			}
		}
		try {
			List<File> pdfs = new ArrayList<File>();
			for (String pdfFilePath : pdfFilePaths) {
				pdfs.add(new File(pdfFilePath));
			}

			File[] files = new File[pdfs.size()];
			pdfs.toArray(files);
			return ZipUtil.zip(files, tmpFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// deleteFile(pdfFilePaths);
		return "";
	}
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	public String generateMultipleDocInOnePDF(String[] idRefs, BillingSystemUserValueObject uvo) {
		String tmpFolder = System.getProperty("java.io.tmpdir");
		Calendar cal = Calendar.getInstance();
		String filePath = tmpFolder + "/BatchPrint_" + CommonUtils.formatDate(cal, "yyyyMMdd");
		List<String> pdfFilePaths = new ArrayList<String>();
		//Map<String, Object> param = new HashMap<String, Object>();
		//param.put("idRefs", idRefs);
		//idRefs = this.queryDAO.executeForObjectArray("B_BTH.reOrdersID", param, String.class);
		
		// used database to sort the idrefs
		if(idRefs.length>0){
			Comparator<String> comparator = new Comparator<String>(){

				public int compare(String str1, String str2) {
					return str1.compareToIgnoreCase(str2);
				}
				
			};
			Arrays.sort(idRefs, comparator);
		}
		for(String idRef : idRefs) {
			pdfFilePaths.add(generate2(new String[]{idRef}, filePath + "_" + idRef.trim() + "_" + ((int) (Math.random() * 100))+".pdf", uvo, null));
		}
		filePath += ".pdf";
        try {
    		List<InputStream> pdfs = new ArrayList<InputStream>();
    		for(String pdfFilePath : pdfFilePaths)
    			pdfs.add(new FileInputStream(pdfFilePath));
	        OutputStream output = new FileOutputStream(filePath);
	        concatPDFs(pdfs, output, false);
	        //close tmp file
	        for(InputStream tmpPdf : pdfs)
	        	tmpPdf.close();
	        
	        deleteFile(pdfFilePaths);
			String [] filePathArray = {filePath};
			return ZipUtil.zip(filePathArray, tmpFolder);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "";
	}
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	// #252 Batch Email Billing Document: generate PDF / email CT 09052017 ST
	public void emailGeneratePdf(String filePath, String idRef, String autSign, BillingSystemUserValueObject uvo,
			String pdfPsd) {
		generate2(new String[] { idRef }, filePath, uvo, pdfPsd);
	}
	// #252 Batch Email Billing Document: generate PDF / email CT 09052017 EN

	private String generate2(String[] idRefs, String outputFile, BillingSystemUserValueObject uvo, String pdfPsd) {
		// reset some field
		formatter = new DecimalFormat("###,##0.00");
		formatter.setRoundingMode(RoundingMode.HALF_UP);
		formatter4d = new DecimalFormat("0.0000");
		formatter4d.setRoundingMode(RoundingMode.HALF_UP);
		realPagePrinted = new ArrayList<Integer>();
		int n = idRefs.length;
		int i = 0;
		int j = 0;
		if (j == n) {

		} else {
			// Generate pdf file
			this.document = new Document(PageSize.A4);
			this.document.setMarginMirroring(true);
			// Generate pdf file
			this.document = new Document(PageSize.A4);
			this.document.setMarginMirroring(true);
			// left right top bottom
			// 0.694inch 0.389inch 1.0inch 1.0inch
			this.document.setMargins(0.694f * PER_INCH, 0.389f * PER_INCH, 1.0f * PER_INCH, 0.2f * PER_INCH);
			FileOutputStream outputStream = null;

			try {
				outputStream = new FileOutputStream(outputFile);
				pdfW = PdfWriter.getInstance(this.document, outputStream);
				// #252 Batch Email Billing Document: generate PDF / email CT
				// 09052017 ST
				if (!StringUtils.isEmpty(pdfPsd)) {
					pdfW.setEncryption(pdfPsd.getBytes(), pdfPsd.getBytes(), PdfWriter.ALLOW_PRINTING,
								
							PdfWriter.ENCRYPTION_AES_128);
				}
				// #252 Batch Email Billing Document: generate PDF / email CT
				// 09052017 EN

				G_BTH_P01_PageEvent pageEvent = new G_BTH_P01_PageEvent();
				pdfW.setPageEvent(pageEvent);
				this.document.open();

				for (int k = 0; k < idRefs.length; k++) {
					boolean isNeedUpdate = true;
					String idRef = idRefs[k];
					HashMap<String, Object> m1 = new HashMap<String, Object>();
					m1.put("idRef", idRef);
					HashMap<String, Object> conditions = (HashMap<String, Object>) this.queryDAO
							.executeForMap("B_BTH.getContentByIdRef", m1);
					// get ID_CUST
					String idCust = conditions.get("ID_CUST").toString();
					this.billingType = conditions.get("BILL_TYPE").toString();

					HashMap<String, Object> m2 = new HashMap<String, Object>();
					m2.put("idRef", idRef);
					m2.put("idCust", idCust);
					m2.put("billType", billingType);

					// check type of doc
					if (billingType.equals("IN") || billingType.equals("DN") || billingType.equals("CN")) {
						// new page
						pdfW.setPageEmpty(true);
						this.document.newPage();
						// get invoice header
						this.headerInfo = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BTH.getHeaderInfo",
								m2);
						String billCurrency = CommonUtils.toString(headerInfo.get("BILL_CURRENCY")).trim();
						B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
						// get Bank info
						this.bankInfoList = this.queryDAO.executeForMapList("B_BTH.getBankInfoBillDocument", null);
						this.bankInfoList = bilUtil.getBankFooterInfo(billCurrency, bankInfoList);
						// get Company info
						this.companyInfo = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BTH.getCompanyInfo",
								null);
						// get detail info
						this.detailInfo = this.queryDAO.executeForObjectList("B_BTH.getDetailBillDocument", m2);
						// generate invoice pdf
						generatePDF();
					} else {
						// no update
						isNeedUpdate = false;
					}
					i++;
					if (isNeedUpdate) {
						// update T_BIL_H
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("datePrinted", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
						map.put("userPrinted", uvo.getId_user());
						map.put("dateUpdated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
						map.put("idLogin", uvo.getId_user());
						map.put("idRef", idRef);
						this.updateDAO.execute("B_BTH.updateSingleNoPrintedT_BIL_H", map);
					}
				}
				outputStream.flush();
				this.document.close();
				pdfW.close();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (this.document.isOpen())
					this.document.close();
				if (!pdfW.isCloseStream())
					pdfW.close();
				if (outputStream != null)
					try {
						outputStream.close();
					} catch (IOException e) {
					}
			}
		}
		return outputFile;
	}

	private void generatePDF() {
		try {
			// title
			this.displayTitle();
			// build table header
			this.displayHeader();
			// build table detail
			// build table detail header
			this.displayDetail();
			// footer
			this.displayFooter();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void displayTitle() throws DocumentException {
		Paragraph pTitle = new Paragraph();
		pTitle.setAlignment(Element.ALIGN_CENTER);
		Phrase phTitle = new Phrase(this.billingType.equals("IN") ? "TAX INVOICE"
				: this.billingType.equals("DN") ? "DEBIT NOTE" : "CREDIT NOTE", font20b);
		pTitle.add(phTitle);
		this.document.add(pTitle);
	}

	private void displayHeader() throws DocumentException {
		/*
		 * A4 = 8.27inch x 11.69inch
		 */
		float[] colWidths = { 62f, 4f, 135f };
		PdfPTable tHeader = new PdfPTable(3);
		tHeader.setWidthPercentage(50);
		tHeader.setWidths(colWidths);
		tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// tHeader.setSpacingBefore(10);
		tHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		float padding = 1f;

		String idRefTitleName = "";
		if ("IN".equalsIgnoreCase(this.billingType)) {
			idRefTitleName = "Invoice No";
		} else if ("DN".equalsIgnoreCase(this.billingType)) {
			idRefTitleName = "Debit Note No";
		} else {
			idRefTitleName = "Credit Note No";
		}

		this.displayHeader_CellLeft(tHeader, padding, idRefTitleName, font10);
		this.displayHeader_CellLeft(tHeader, padding, ":", font10);
		this.displayHeader_CellLeft(tHeader, padding, CommonUtils.toString(this.headerInfo.get("ID_REF")).trim(),
				font10);

		this.displayHeader_CellLeft(tHeader, padding, "Date", font10);
		this.displayHeader_CellLeft(tHeader, padding, ":", font10);
		this.displayHeader_CellLeft(tHeader, padding, CommonUtils.toString(this.headerInfo.get("DATE_REQ")), font10);

		this.displayHeader_CellLeft(tHeader, padding, "Due Date", font10);
		this.displayHeader_CellLeft(tHeader, padding, ":", font10);
		this.displayHeader_CellLeft(tHeader, padding, CommonUtils.toString(this.headerInfo.get("DUE_DATE")), font10);

		this.displayHeader_CellLeft(tHeader, padding, "Billing Account", font10);
		this.displayHeader_CellLeft(tHeader, padding, ":", font10);
		this.displayHeader_CellLeft(tHeader, padding, CommonUtils.toString(this.headerInfo.get("BILL_ACC")).trim(),
				font10);

		this.displayHeader_CellLeft(tHeader, padding, "Customer PO", font10);
		this.displayHeader_CellLeft(tHeader, padding, ":", font10);
		this.displayHeader_CellLeft(tHeader, padding, CommonUtils.toString(this.headerInfo.get("CUST_PO")).trim(),
				font10);

		realPagePrinted.add(pdfW.getCurrentPageNumber());

		this.document.add(tHeader);

		float[] colWidths1 = { 280f, 150f };
		PdfPTable tHeaderCustomer = new PdfPTable(2);
		tHeaderCustomer.setWidthPercentage(100);
		tHeaderCustomer.setWidths(colWidths1);
		tHeaderCustomer.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tHeaderCustomer.setHorizontalAlignment(Element.ALIGN_LEFT);

		String customerName = CommonUtils.getCodeMapListNameByValue("LIST_SALUTATION",
				CommonUtils.toString(headerInfo.get("SALUTATION"))) + " "
				+ CommonUtils.toString(headerInfo.get("CUST_NAME"));
		customerName = CommonUtils.toString(customerName).trim();
		String customerAddress1 = CommonUtils.toString(this.headerInfo.get("ADDRESS1")).trim();
		String customerAddress2 = CommonUtils.toString(this.headerInfo.get("ADDRESS2")).trim();
		String customerAddress3 = CommonUtils.toString(this.headerInfo.get("ADDRESS3")).trim();
		String customerAddress4 = CommonUtils.toString(this.headerInfo.get("CUST_ADDR4")).trim();
		String attentionToStr = "Attention To : " + CommonUtils.toString(this.headerInfo.get("CONTACT_NAME")).trim();
		String custTelAndFax = "Tel : " + CommonUtils.toString(this.headerInfo.get("TEL_NO")).trim() + "  Fax : "
				+ CommonUtils.toString(this.headerInfo.get("FAX_NO")).trim();
		List<String> customerAddressList = addressDisplayChange(customerAddress1, customerAddress2, customerAddress3,
				customerAddress4);
		customerAddress1 = customerAddressList.get(0);
		customerAddress2 = customerAddressList.get(1);
		customerAddress3 = customerAddressList.get(2);
		customerAddress4 = customerAddressList.get(3);

		CommonUtils.fixAddress4n(this.companyInfo, "COM_ADR_LINE4");
		String companyName = CommonUtils.toString(this.companyInfo.get("COM_NAME")).trim();
		String companyAddress1 = CommonUtils.toString(this.companyInfo.get("COM_ADR_LINE1")).trim();
		String companyAddress2 = CommonUtils.toString(this.companyInfo.get("COM_ADR_LINE2")).trim();
		String companyAddress3 = CommonUtils.toString(this.companyInfo.get("COM_ADR_LINE3")).trim();
		String companyAddress4 = CommonUtils.toString(this.companyInfo.get("COM_ADR_LINE4")).trim();
		String TelAndFax = "Tel : " + CommonUtils.toString(this.companyInfo.get("COM_TEL_NO")).trim() + "  Fax : "
				+ CommonUtils.toString(this.companyInfo.get("COM_FAX_NO")).trim();
		// String regNoStr = "Reg. No :
		// "+CommonUtils.toString(this.companyInfo.get("COM_REGNO")).trim();
		String gstRegNoStr = "Co./GST Reg. No. : " + CommonUtils.toString(this.companyInfo.get("COM_GST_REG_NO")).trim();
		String websiteStr = "Website : " + CommonUtils.toString(this.companyInfo.get("COM_WEBSITE")).trim();

		List<String> companyAddressList = addressDisplayChange(companyAddress1, companyAddress2, companyAddress3,
				companyAddress4);
		companyAddress1 = companyAddressList.get(0);
		companyAddress2 = companyAddressList.get(1);
		companyAddress3 = companyAddressList.get(2);
		companyAddress4 = companyAddressList.get(3);

		this.displayHeader_CellLeft(tHeaderCustomer, padding, "Page                        :", font10);
		this.displayHeader_CellLeft(tHeaderCustomer, padding, companyName, font11b);

		this.displayHeader_CellLeft(tHeaderCustomer, padding, customerName, font10);
		this.displayHeader_CellLeft(tHeaderCustomer, 2f, companyAddress1, font08);

		this.displayHeader_CellLeft(tHeaderCustomer, padding, customerAddress1, font10);
		this.displayHeader_CellLeft(tHeaderCustomer, padding, companyAddress2, font08);

		this.displayHeader_CellLeft(tHeaderCustomer, padding, customerAddress2, font10);
		this.displayHeader_CellLeft(tHeaderCustomer, padding, companyAddress3, font08);

		if (CommonUtils.isEmpty(customerAddress3) && CommonUtils.isEmpty(companyAddress4)) {
			this.displayHeader_CellLeft(tHeaderCustomer, padding, " ", font10);
			this.displayHeader_CellLeft(tHeaderCustomer, padding, " ", font08);
		} else {
			this.displayHeader_CellLeft(tHeaderCustomer, padding, customerAddress3, font10);
			this.displayHeader_CellLeft(tHeaderCustomer, padding, companyAddress4, font08);
		}

		this.displayHeader_CellLeft(tHeaderCustomer, padding, customerAddress4, font10);
		this.displayHeader_CellLeft(tHeaderCustomer, padding, TelAndFax, font08);

		this.displayHeader_CellLeft(tHeaderCustomer, padding, attentionToStr, font10);
		this.displayHeader_CellLeft(tHeaderCustomer, padding, gstRegNoStr, font08);

		this.displayHeader_CellLeft(tHeaderCustomer, padding, custTelAndFax, font10);
		this.displayHeader_CellLeft(tHeaderCustomer, padding, websiteStr, font08);

		// this.displayHeader_CellLeft(tHeaderCustomer, padding, "", font10);
		// this.displayHeader_CellLeft(tHeaderCustomer, padding, websiteStr,
		// font08);

		this.document.add(tHeaderCustomer);
	}

	private void displayDetail() throws DocumentException {
		PdfPCell c = null;
		this.displayDetail_Header(false);
		// in fact no of page
		this.subTotalDetail = new BigDecimal("0");
		this.subTotalWithGST = new BigDecimal("0");
		this.subTotalWithoutGST = new BigDecimal("0");
		this.totalGST = new BigDecimal("0");
		this.totalGST0 = new BigDecimal("0");
		this.totalGSTOnSubTotal = new BigDecimal("0");

		// loop for each detail and display
		int index = 0, rowPerPage = 0, nextRow = 0;
		String sItem, description, sQty, sPrice, sAmt, sGst = "", sGstAmount;
		this.displayGST0 = false;
		String period = null;
		boolean isServiceDesc = false;
		// #155 Start
		boolean lumpsumFlag = false;
		String itemDiscAmt1 = "";
		// #155 End
		// #156 Start
		String billCnAmtNegative = queryDAO.executeForObject("B_BTH.getBillCnAmtNegative", null, String.class);
		// #156 End
		for (int i = 0; i < this.detailInfo.size(); i++) {
			Map<String, Object> detail = this.detailInfo.get(i);
			String isDisplay = CommonUtils.toString(detail.get("IS_DISPLAY"));
			String applyGst = CommonUtils.toString(detail.get("APPLY_GST"));
			String itemLevel = CommonUtils.toString(detail.get("ITEM_LEVEL"));
			String itemCat = CommonUtils.toString(detail.get("ITEM_CAT"));
			String itemDiscAmt = formatter.format(detail.get("ITEM_DISC_AMT"));
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			String itemTaxRate = CommonUtils.toString(detail.get("TAX_RATE"));
			String itemTaxRateAmount = formatter.format(detail.get("ITEM_GST"));
			String itemTaxRateAmount4d = formatter4d.format(detail.get("ITEM_GST"));
			String itemExportTaxRateAmount = CommonUtils.toString(detail.get("ITEM_EXPORT_GST"));
			String isLumpSum = CommonUtils.toString(detail.get("IS_LUMP_SUM"));
			sGstAmount = "";
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			// #156 Start
			if ("CN".equals(this.billingType) && "1".equals(billCnAmtNegative)) {
				itemDiscAmt = formatter.format(Math.abs(CommonUtils.toDouble(detail.get("ITEM_DISC_AMT"))));
			}
			// #156 End

			// #155 <SubTotal> Start
			// BigDecimal itemAmt = new BigDecimal("0");
			BigDecimal itemSubtotal = new BigDecimal("0");
			BigDecimal itemExportSubTotal = new BigDecimal("0");
			// #155 <SubTotal> End
			if (!CommonUtils.isEmpty(isDisplay)) {
				if ("0".equals(isDisplay)) {
					sItem = "";
				} else {
					index++;
					sItem = index + "";
				}
			} else {
				index++;
				sItem = index + "";
			}
			description = CommonUtils.toString(detail.get("ITEM_DESC"));
			if (!CommonUtils.isEmpty(isDisplay)) {
				if ("0".equals(isDisplay)) {// without lump sum
					sQty = "";
					sPrice = "";
					sAmt = "";
					itemSubtotal = (BigDecimal) detail.get("ITEM_SUBTOTAL");
					itemExportSubTotal = (BigDecimal) detail.get("ITEM_EXPORT_AMT");
				} else {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
					// #155 <SubTotal> Start
					// itemAmt = (BigDecimal)detail.get("ITEM_AMT");
					// this.subTotalDetail = this.subTotalDetail.add(itemAmt);
					itemSubtotal = (BigDecimal) detail.get("ITEM_SUBTOTAL");
					itemExportSubTotal = (BigDecimal) detail.get("ITEM_EXPORT_AMT");
					this.subTotalDetail = this.subTotalDetail.add(itemSubtotal);
					// #155 <SubTotal> End
				}
			} else {
				try {
					if (Double.parseDouble(detail.get("ITEM_QTY").toString()) == 0
							|| Double.parseDouble(detail.get("ITEM_UPRICE").toString()) == 0
							|| Double.parseDouble(detail.get("ITEM_AMT").toString()) == 0) {
						sQty = "";
						sPrice = "";
						sAmt = "";
					} else {
						sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
						sPrice = formatter.format(detail.get("ITEM_UPRICE"));
						sAmt = formatter.format(detail.get("ITEM_AMT"));
						// #155 <SubTotal> Start
						// itemAmt = (BigDecimal)detail.get("ITEM_AMT");
						// this.subTotalDetail =
						// this.subTotalDetail.add(itemAmt);
						itemSubtotal = (BigDecimal) detail.get("ITEM_SUBTOTAL");
						this.subTotalDetail = this.subTotalDetail.add(itemSubtotal);
						// #155 <SubTotal> End
					}
				} catch (Exception e) {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
				}
			}
			
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 11072017
			if (!CommonUtils.isEmpty(itemLevel)) {
				if("1".equals(itemLevel)){
					if (!"0.00".equals(itemTaxRateAmount)) {
						this.subTotalWithGST = subTotalWithGST.add(getSubTotalAmountByConvertion(itemSubtotal.toString(),itemExportSubTotal.toString(),formatter,isLumpSum));
					} else {
						this.subTotalWithoutGST = subTotalWithoutGST.add(getSubTotalAmountByConvertion(itemSubtotal.toString(),itemExportSubTotal.toString(),formatter,isLumpSum));
					}
				}
			}
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 11072017
			
			// #176 Start
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			if (!"0.00".equals(itemTaxRateAmount) && "1".equals(isDisplay)) {
				// #176 End
				sGst = itemTaxRate;
				sGstAmount = itemTaxRateAmount;
				totalGST = totalGST.add(getSubTotalAmountByConvertion(itemTaxRateAmount4d,itemExportTaxRateAmount,formatter,isLumpSum));
				totalGSTOnSubTotal = totalGSTOnSubTotal.add(getSubTotalAmount(itemTaxRateAmount4d,formatter4d));
			} else if("0.00".equals(itemTaxRateAmount) && "1".equals(isDisplay)){
				sGst = "0";
				sGstAmount = "0.00";
				totalGST0 = totalGST0.add(getSubTotalAmountByConvertion(itemTaxRateAmount4d,itemExportTaxRateAmount,formatter,isLumpSum));
				displayGST0 = true;
			} else {
				sGst = "";
				sGstAmount = "";
			}
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			// ITEM_LEVEL is Service and ITEM_CAT is CPM
			if ("0".equals(itemLevel) && "1".equals(itemCat)) {
				isServiceDesc = true;
			} else {
				isServiceDesc = false;
			}
			if ("CN".equals(this.billingType)) {
				sPrice = CommonUtils.toString(sPrice);
				sAmt = CommonUtils.toString(sAmt);
				sGstAmount = CommonUtils.toString(sGstAmount);
				if (!CommonUtils.isEmpty(sPrice)) {
					sPrice = "-" + sPrice;
				}
				if (!CommonUtils.isEmpty(sAmt)) {
					sAmt = "-" + sAmt;
				}
				if (!CommonUtils.isEmpty(sGstAmount)) {
					sGstAmount = "-" + sGstAmount;
				}
			}
			// billing period
			period = "";

			if ((i + 1 < this.detailInfo.size() && this.detailInfo.get(i + 1).get("ITEM_LEVEL").equals("0")
					&& "1".equals(itemCat)) || (i == this.detailInfo.size() - 1 && "1".equals(itemCat))) {
											
																				  
				if (detail.get("BILL_FROM") != null && detail.get("BILL_TO") != null) {
					period = "Billing Period: From " + CommonUtils.toString(detail.get("BILL_FROM")) + " To "
																				 
							+ CommonUtils.toString(detail.get("BILL_TO")) + "\r\n";
				}
				if (i != this.detailInfo.size() - 1 && !period.equals(""))
					period += "\r\n";// break line for next description
				if (i == this.detailInfo.size() - 1)
					period += "\r\n";// end of detail
			}

			String cfValue = CommonUtils.toString(formatter.format(this.subTotalDetail));
			if ("CN".equals(this.billingType)) {
				if (!"0.00".equals(cfValue)) {
					cfValue = "-" + cfValue;
				}
			}
			// calculate to decide new page or not
			List<String> rows = getRows(description, MAXIMUN_CHAR_PER_ROW);
			if (period.equals("")) {
				if ("1".equals(itemCat)) {
					nextRow = rows.size();
				} else {
					nextRow = rows.size() + 1;
				}
			} else {
				nextRow = rows.size() + 2;// description + period
				rows.add(period);
			}
			// #155 Start
			boolean itemFlag = true;
			// #196 Start
			if ("1".equals(detail.get("DISPLAY_DISC")) && "0".equals(detail.get("ITEM_LEVEL"))
					&& !"0.00".equals(itemDiscAmt)) {
				// #196 End
				lumpsumFlag = true;
				itemDiscAmt1 = itemDiscAmt;
			}
			// #155 End
			if (rowPerPage >= ROW_PER_PAGE) {// new page
				rowPerPage = 0;

				// print
				this.displayDetail_Description(sItem, description, sQty, sPrice, sAmt, sGst, sGstAmount, isServiceDesc);
				period = CommonUtils.toString(period);
				if (CommonUtils.isEmpty(period)) {
					if (!"1".equals(itemCat)) {
						this.displayDetail_Description("", period, "", "", "", "", "", 0f, isServiceDesc);
					}
				} else {
					this.displayDetail_Description("", period, "", "", "", "", "", 0f, isServiceDesc);
				}
				// sub total balance line
				c = getCellColSpan("Balance c/f (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")) + ")",
						BaseColor.WHITE, Element.ALIGN_CENTER, 4, font09);
				c.setFixedHeight(0.2f * PER_INCH);// 0.2inch
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				c = getCell(cfValue, BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
								
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);

				c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				// c.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM);
				this.tableDetail.addCell(c);

				c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				// c.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM);
				this.tableDetail.addCell(c);

				this.document.add(this.tableDetail);
				pdfW.setPageEmpty(true);
				this.document.newPage();
			} else if (rowPerPage + nextRow > ROW_PER_PAGE) {
				String desc = "";
				boolean isNewPage = true;
				boolean isFistPage = true;
				while (!rows.isEmpty()) {
					desc = "";
					isNewPage = true;
					int k = 0;
					for (k = 0; k < ROW_PER_PAGE - rowPerPage; k++) {
						if (rows.isEmpty()) {
							isNewPage = false;
							break;
						}
						desc += ("\r\n" + rows.remove(0));
					}
					rowPerPage = k;
					// only print full information in first page
					if (isFistPage) {
						isFistPage = false;
						this.displayDetail_Description(sItem, desc.substring(2), sQty, sPrice, sAmt, sGst, sGstAmount,
								isServiceDesc);
						// #155 Start
						if (rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag) {
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))
										&& !"0.00".equals(itemDiscAmt)) {
									rowPerPage = rowPerPage + 1;
									this.displayDetail_Description_discount("", "Discount", "", "", itemDiscAmt, sGst);
								}
							}
							if (period != null && !period.equals("")) {
								// Discount (Lumpsum)
								if (lumpsumFlag) {
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description("", " ", "", "", "", "", "", 0f, isServiceDesc);
									this.displayDetail_Description_discount("", "Discount", "", "", itemDiscAmt1, sGst);
									this.displayDetail_Description("", " ", "", "", "", "", "", 0f, isServiceDesc);
								}
							}
						}
						// #155 End
					} else {
						this.displayDetail_Description("", desc.substring(2), "", "", "", "", "", 0f, isServiceDesc);
						// #155 start
						if (rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag) {
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))
										&& !"0.00".equals(itemDiscAmt)) {
									rowPerPage = rowPerPage + 1;
									this.displayDetail_Description_discount("", "Discount", "", "", itemDiscAmt, sGst);
								}
							}
							if (period != null && !period.equals("")) {
								// Discount (Lumpsum)
								if (lumpsumFlag) {
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description("", " ", "", "", "", "", "", 0f, isServiceDesc);
									this.displayDetail_Description_discount("", "Discount", "", "", itemDiscAmt1, sGst);
									this.displayDetail_Description("", " ", "", "", "", "", "", 0f, isServiceDesc);
								}
							}
						}
						// #155 end
					}
					// new page
					if (isNewPage) {
						rowPerPage = 0;
						// sub total balance line
						c = getCellColSpan(
								"Balance c/f (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")) + ")",
								BaseColor.WHITE, Element.ALIGN_CENTER, 4, font09);
						c.setFixedHeight(0.2f * PER_INCH);// 0.2inch
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						c = getCell(cfValue, BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);

						c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						// c.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM);
						this.tableDetail.addCell(c);

						c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						// c.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM);
						this.tableDetail.addCell(c);

						this.document.add(this.tableDetail);
						pdfW.setPageEmpty(true);
						this.document.newPage();
					}
				}
			} else {
				rowPerPage += nextRow;
				// print
				this.displayDetail_Description(sItem, description, sQty, sPrice, sAmt, sGst, sGstAmount, isServiceDesc);
				// #155 START
				// Discount (Item)
				if (itemFlag) {
					itemFlag = false;
					if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))
							&& !"0.00".equals(itemDiscAmt)) {
						rowPerPage = rowPerPage + 1;
						this.displayDetail_Description_discount("", "Discount", "", "", itemDiscAmt, sGst);
					}
				}
				if (period != null && !period.equals("")) {
					// Discount (Lumpsum)
					if (lumpsumFlag) {
						lumpsumFlag = false;
						rowPerPage = rowPerPage + 3;
						this.displayDetail_Description("", " ", "", "", "", "", "", 0f, isServiceDesc);
						this.displayDetail_Description_discount("", "Discount", "", "", itemDiscAmt1, sGst);
						this.displayDetail_Description("", " ", "", "", "", "", "", 0f, isServiceDesc);
					}
				}
				// #155 END
				period = CommonUtils.toString(period);
				if (CommonUtils.isEmpty(period)) {
					if (!"1".equals(itemCat)) {
						this.displayDetail_Description("", period, "", "", "", "", "", 0f, isServiceDesc);
					}
				} else {
					this.displayDetail_Description("", period, "", "", "", "", "", 0f, isServiceDesc);
				}
			}
		}
		int onePageMaxRow = 15;
		if (rowPerPage > onePageMaxRow) {
			String cfValue = CommonUtils.toString(formatter.format(this.subTotalDetail));
			if ("CN".equals(this.billingType)) {
				if (!"0.00".equals(cfValue)) {
					cfValue = "-" + cfValue;
				}
			}

			c = getCellColSpan("Balance c/f (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")) + ")",
					BaseColor.WHITE, Element.ALIGN_CENTER, 4, font09);
			c.setFixedHeight(0.2f * PER_INCH);// 0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			c = getCell(cfValue, BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);

			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			// c.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM);
			this.tableDetail.addCell(c);

			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			// c.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM);
			this.tableDetail.addCell(c);

			this.document.add(this.tableDetail);
			pdfW.setPageEmpty(true);
			this.document.newPage();
		}

		String subTotalStr = CommonUtils.toString(formatter.format(this.subTotalDetail));
		String gstAmountStr = CommonUtils.toString(formatter.format(totalGSTOnSubTotal));
		String billAmountStr = CommonUtils.toString(formatter.format(this.headerInfo.get("BILL_AMOUNT")));
		String expAmountStr = CommonUtils.toString(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")));
		if ("CN".equals(this.billingType)) {
			if (!"0.00".equals(subTotalStr)) {
				subTotalStr = "-" + subTotalStr;
			}
			if (!"0.00".equals(gstAmountStr)) {
				gstAmountStr = "-" + gstAmountStr;
			}
			if (!"0.00".equals(billAmountStr)) {
				billAmountStr = "-" + billAmountStr;
			}
			if (!"0.00".equals(expAmountStr)) {
				expAmountStr = "-" + expAmountStr;
			}
		}
		// CUR RATE
		DecimalFormat formatter1 = new DecimalFormat("###,##0.00000000");
		formatter1.setRoundingMode(RoundingMode.HALF_UP);
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
		String expCurrency = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		Object curRate = this.headerInfo.get("CUR_RATE");
		// sub total line
		c = getCellColSpan("Sub Total (" + billCurrency + ")", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font09b);
												  
		c.setFixedHeight(0.2f * PER_INCH);// 0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		c = getCell(subTotalStr, BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
						
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		c = getCell("", BaseColor.WHITE, Element.ALIGN_CENTER, font09);
		c.setBorder(PdfPCell.TOP);
		this.tableDetail.addCell(c);
		c = getCell("", BaseColor.WHITE, Element.ALIGN_CENTER, font09);
		c.setBorder(PdfPCell.TOP);
		this.tableDetail.addCell(c);
		// gst line
		// #144 Start
		String gst = queryDAO.executeForObject("B_BTH.getGstAmount", null, String.class);
		if (!("0.00".equals(gstAmountStr))) {
			c = getCellColSpan("GST " + gst + "%", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font09b);
			c.setFixedHeight(0.2f * PER_INCH);// 0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			c = getCell(gstAmountStr, BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setBorder(PdfPCell.NO_BORDER);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setBorder(PdfPCell.NO_BORDER);
			this.tableDetail.addCell(c);
		}
		if (("0.00".equals(gstAmountStr)) || displayGST0) {
			c = getCellColSpan("GST 0%", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font09b);
			c.setFixedHeight(0.2f * PER_INCH);// 0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			c = getCell("0.00", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);

			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setBorder(PdfPCell.NO_BORDER);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setBorder(PdfPCell.NO_BORDER);
			this.tableDetail.addCell(c);
		}
		// grant total line
		c = getCellColSpan("Grand Total (" + billCurrency + ")", BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER, 4,
				font09b);
		c.setFixedHeight(0.25f * PER_INCH);// 0.25inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM | PdfPCell.RIGHT);
		this.tableDetail.addCell(c);
		c = getCell(billAmountStr, BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT, font09);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
		this.tableDetail.addCell(c);
		c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
		c.setBorder(PdfPCell.NO_BORDER);
		this.tableDetail.addCell(c);
		c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
		c.setBorder(PdfPCell.NO_BORDER);
		this.tableDetail.addCell(c);

		if (!CommonUtils.isEmpty(expCurrency) && !"-".equals(expCurrency) && !billCurrency.equals(expCurrency)) {
										  
													  
			String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
			String fixedForexStr = "";
			if (!CommonUtils.isEmpty(fixedForex)) {
				fixedForexStr = "Fixed Forex ";
			}
			c = getCellColSpan(
					"Grand Total " + expCurrency + " (1 " + billCurrency + " = " + fixedForexStr
							+ formatter1.format(curRate) + " " + expCurrency + ")",
					BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER, 4, font09b);
			c.setFixedHeight(0.25f * PER_INCH);// 0.25inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM | PdfPCell.RIGHT);
			this.tableDetail.addCell(c);
			c = getCell(expAmountStr, BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT, font09);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setBorder(PdfPCell.NO_BORDER);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
			c.setBorder(PdfPCell.NO_BORDER);
			this.tableDetail.addCell(c);
		}

		c = getCellColSpan("", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font09b);
		c.setFixedHeight(0.2f * PER_INCH);// 0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.TOP);
		this.tableDetail.addCell(c);
		c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.TOP);
		this.tableDetail.addCell(c);
		c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT, font09);
		c.setBorder(PdfPCell.NO_BORDER);
		this.tableDetail.addCell(c);

		this.document.add(this.tableDetail);
	}

	private void displayHeader_CellLeft(PdfPTable tHeader, float padding, String value, Font font) {
		PdfPCell c = new PdfPCell(new Phrase(value, font));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
	}

	private void displayDetail_Header(boolean isAdded) throws DocumentException {
		PdfPCell c = null;
		// 0.5inch 4.25inch 0.875inch 0.875inch 1inch
		float[] colWidths = { 0.5f * PER_INCH, 4.25f * PER_INCH, 0.5f * PER_INCH, 0.8f * PER_INCH, 0.9f * PER_INCH,
				0.5f * PER_INCH, 0.7f * PER_INCH };
		this.tableDetail = new PdfPTable(7);
		this.tableDetail.setSpacingBefore(7);
		this.tableDetail.setWidthPercentage(100);
		this.tableDetail.setWidths(colWidths);
		c = new PdfPCell(new Phrase("No", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);// 0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("Item", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);// 0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("Qty", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);// 0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("Unit Price (" + this.headerInfo.get("BILL_CURRENCY") + ")", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);// 0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("Price\r\n(" + this.headerInfo.get("BILL_CURRENCY") + ")", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);// 0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("GST\r\n(%)", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);// 0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
		c = new PdfPCell(new Phrase("GST\r\n(" + this.headerInfo.get("BILL_CURRENCY") + ")", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);// 0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017

		if (isAdded) {// this case for more 1 page (from 2 page)
			String bfValue = CommonUtils.toString(formatter.format(this.subTotalDetail));
			if ("CN".equals(this.billingType)) {
				if (!"0.00".equals(bfValue)) {
					bfValue = "-" + bfValue;
				}
			}

			c = new PdfPCell();
			c.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
			this.tableDetail.addCell(c);

			c = new PdfPCell(new Phrase("Balance b/f\r\n\r\n", font10b));
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);

			c = new PdfPCell();
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);

			c = new PdfPCell();
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);

			c = new PdfPCell(new Phrase(bfValue, font10b));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setVerticalAlignment(PdfPCell.ALIGN_TOP);
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);

			c = new PdfPCell();
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);

			c = new PdfPCell();
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);
		}
	}

	private static List<String> getRows(String str, int maxCharPerRow) {
		List<String> result = new ArrayList<String>();
		String[] acc = str.split("\r\n");
		for (int i = 0; i < acc.length; i++) {
			int row = (acc[i].length() % maxCharPerRow == 0 ? acc[i].length() / maxCharPerRow
					: (acc[i].length() / maxCharPerRow) + 1);
			for (int j = 0; j < row; j++) {
				result.add(acc[i].substring(j * maxCharPerRow, Math.min(acc[i].length(), (j + 1) * maxCharPerRow)));
			}
		}
		return result;
	}

	/**
	 * Create colspan pdf cell
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param String
	 *            title, Color color, int align, int colspan
	 * @return PdfPCell
	 * @exception None
	 */
	private PdfPCell getCellColSpan(String title, BaseColor color, int align, int colspan, Font font) {
		PdfPCell cell = new PdfPCell(new Paragraph(title, font));
		cell.setBackgroundColor(color);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		return cell;
	}

	/**
	 * Create normal pdf cell
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param String
	 *            title, Color color, int align
	 * @return PdfPCell
	 * @exception None
	 */
	private PdfPCell getCell(String title, BaseColor color, int align, Font font) {
		PdfPCell cell = new PdfPCell(new Paragraph(title, font));
		cell.setBackgroundColor(color);
		cell.setHorizontalAlignment(align);
		return cell;
	}

	private void displayDetail_Description(String item, String description, String quantity, String unitPrice,
			String amount, String gst, String gstAmount, float paddingTop, boolean isServiceDesc)
			throws DocumentException {
		displayDetail_DescriptionHelp(item, description, quantity, unitPrice, amount, gst, gstAmount, 0f, paddingTop,
				isServiceDesc);
	}

	private void displayDetail_Description(String item, String description, String quantity, String unitPrice,
			String amount, String gst, String gstAmount, boolean isServiceDesc) throws DocumentException {
		displayDetail_DescriptionHelp(item, description, quantity, unitPrice, amount, gst, gstAmount, 3.0f, 0.0f,
				isServiceDesc);// 1.5 line
	}

	private void displayDetail_DescriptionHelp(String item, String description, String quantity, String unitPrice,
			String amount, String gst, String gstAmount, float paragraphSpace, float paddingTop, boolean isServiceDesc)
			throws DocumentException {
		PdfPCell c = null;
		c = new PdfPCell(new Phrase(item, font09));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		if (isServiceDesc) {
			c = new PdfPCell(new Phrase(description, font09b));
		} else {
			c = new PdfPCell(new Phrase(description, font09));
		}
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase(quantity, font09));
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase(unitPrice, font09));
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase(amount, font09));
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
		c = new PdfPCell(new Phrase(gst, font09));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase(gstAmount, font09));
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
	}

	// #155 start
	private void displayDetail_Description_discount(String item, String description, String quantity, String unitPrice,
			String amount, String taxcode) throws DocumentException {
		displayDetail_DescriptionHelp(item, description, quantity, unitPrice, amount, taxcode, 3.0f, 2.0f);// 1.5
																											// line
	}

	private void displayDetail_DescriptionHelp(String item, String description, String quantity, String unitPrice,
			String amount, String taxcode, float paragraphSpace, float paddingTop) throws DocumentException {
		PdfPCell c = null;
		c = new PdfPCell(new Phrase(item, font10));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		if ("Discount".equals(description)) {
			font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC | Font.BOLD);
		}
		c = new PdfPCell(new Phrase(description, font));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase(quantity, font10));
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase(unitPrice, font10));
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase(amount, font10));
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);

		if (!this.billingType.equals("NT")) {
			c = new PdfPCell(new Phrase(taxcode, font09));
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			c.setBorder(PdfPCell.RIGHT);
			c.setExtraParagraphSpace(paragraphSpace);
			c.setPaddingTop(paddingTop);
			this.tableDetail.addCell(c);
			
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			c = new PdfPCell(new Phrase("", font09));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setBorder(PdfPCell.RIGHT);
			c.setExtraParagraphSpace(paragraphSpace);
			c.setPaddingTop(paddingTop);
			this.tableDetail.addCell(c);
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
		} else {
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			c = new PdfPCell(new Phrase("", font09));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setBorder(PdfPCell.RIGHT);
			c.setExtraParagraphSpace(paragraphSpace);
			c.setPaddingTop(paddingTop);
			this.tableDetail.addCell(c);
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
			c = new PdfPCell(new Phrase("", font09));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setBorder(PdfPCell.RIGHT);
			c.setExtraParagraphSpace(paragraphSpace);
			c.setPaddingTop(paddingTop);
			this.tableDetail.addCell(c);
			// #266 [T14] [B-BTH-F01] invoice PDF modification CT 13062017
		}
	}
	// #155 end

	private void displayFooter() throws DocumentException {
		// Export Currency not empty
		// if (!"CN".equals(this.billingType)){
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
		String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		if (exportCur.equals("") || exportCur.equals("-")) {
			exportCur = billCurrency;
		}
		// CUR RATE
		DecimalFormat formatter1 = new DecimalFormat("###,##0.00000000");
		formatter1.setRoundingMode(RoundingMode.HALF_UP);
		Object curRate = headerInfo.get("CUR_RATE");
		String gstRate = CommonUtils.toString((BigDecimal) headerInfo.get("GST_PERCENT"));
		String changeSubTotalStr = "";
		String changeGstAmountStr = "";
		String changeBillAmountStr = "";
		String subTotalWithGSTStr = "";
		String subTotalWithoutGSTStr = "";
		String convertedGSTAmount = "";
		String changeGst0AmountStr = "";
		BigDecimal gstAmount = new BigDecimal("0");
		BigDecimal curRateAmount = (BigDecimal) curRate;
		if (!billCurrency.equals(exportCur)) {
			/*G_CUR_P01 gCurP01 = new G_CUR_P01(this.queryDAO);
			Map<String, Object> convertCur2 = gCurP01.convertCurrency(billCurrency, this.subTotalDetail.doubleValue(),
					exportCur, curRate);*/
			changeSubTotalStr = CommonUtils.toString(formatter.format(customeRounding(this.subTotalDetail,2)));
			
			//Calculate export gst (after convert)
			/*Map<String, Object> convertCur = gCurP01.convertCurrency(billCurrency, totalGST.doubleValue(),
					exportCur, curRate);*/
			changeGstAmountStr = CommonUtils.toString(formatter.format(customeRounding(totalGST,2)));
			
			changeGst0AmountStr = CommonUtils.toString(formatter.format(customeRounding(totalGST0,2)));
			//Calculate export total amount with gst (after convert)
			/*convertCur = gCurP01.convertCurrency(billCurrency, this.subTotalWithGST.doubleValue(),
					exportCur, curRate);*/
			subTotalWithGSTStr = CommonUtils.toString(formatter.format(customeRounding(this.subTotalWithGST,2)));
			//Calculate export total amount without gst (after convert)
			/*convertCur = gCurP01.convertCurrency(billCurrency, this.subTotalWithoutGST.doubleValue(),
					exportCur, curRate);*/
			subTotalWithoutGSTStr = CommonUtils.toString(formatter.format(customeRounding(this.subTotalWithoutGST,2)));
			
			//Check one cent
			/*BigDecimal diffAmount = checkOneCent(subTotalWithGSTStr,subTotalWithoutGSTStr,changeSubTotalStr);
			if(diffAmount.compareTo(BigDecimal.ZERO) != 0){
				subTotalWithGSTStr = CommonUtils.toString(formatter.format(
						(formatStringToBigDecimal(subTotalWithGSTStr)).add(diffAmount.multiply(new BigDecimal(-1)))
						));
			}*/
			
			changeBillAmountStr = formatter.format(this.headerInfo.get("EXPORT_AMOUNT"));
			
			// #252 Batch Email Billing Document: generate PDF / email CT
			// 15052017
			convertedGSTAmount = changeGstAmountStr;
			// #252 Batch Email Billing Document: generate PDF / email CT
			// 15052017
		} else {
			//changeSubTotalStr = formatter.format(this.subTotalDetail);
			changeGstAmountStr = formatter.format(totalGST);
			changeGst0AmountStr = formatter.format(totalGST0);
			changeBillAmountStr = formatter.format(this.headerInfo.get("BILL_AMOUNT"));
			// #252 Batch Email Billing Document: generate PDF / email CT
			// 15052017
			subTotalWithGSTStr = CommonUtils.toString(formatter.format(this.subTotalWithGST));
			subTotalWithoutGSTStr = CommonUtils.toString(formatter.format(this.subTotalWithoutGST));
			// #252 Batch Email Billing Document: generate PDF / email CT
			// 15052017
		}
		if ("CN".equals(this.billingType)) {
			if (!"0.00".equals(changeSubTotalStr)) {
				changeSubTotalStr = "-" + changeSubTotalStr;
			}
			if (!"0.00".equals(changeGstAmountStr)) {
				changeGstAmountStr = "-" + changeGstAmountStr;
			}
			if (!"0.00".equals(changeBillAmountStr)) {
				changeBillAmountStr = "-" + changeBillAmountStr;
			}
			if (!"0.00".equals(subTotalWithGSTStr)) {
				subTotalWithGSTStr = "-" + subTotalWithGSTStr;
			}
			if (!"0.00".equals(subTotalWithoutGSTStr)) {
				subTotalWithoutGSTStr = "-" + subTotalWithoutGSTStr;
			}
			if (!"0.00".equals(convertedGSTAmount)) {
				convertedGSTAmount = "-" + convertedGSTAmount;
			}
		}

		float[] colWidths = { 2.7f * PER_INCH, 1.5f * PER_INCH, 1.0f * PER_INCH };
		PdfPTable footerTable2 = new PdfPTable(3);
		footerTable2.setWidthPercentage(85);
		footerTable2.setSpacingBefore(10);
		footerTable2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		footerTable2.setWidths(colWidths);
		footerTable2.setHorizontalAlignment(Element.ALIGN_LEFT);

		// Sub Total
		PdfPCell cell2 = new PdfPCell(new Paragraph("Exchange Rate: " + formatter1.format(curRate), font09));
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.LEFT | PdfPCell.TOP);
		footerTable2.addCell(cell2);

		cell2 = new PdfPCell(new Paragraph("Amount(GST applicable)", font09));
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.TOP);
		footerTable2.addCell(cell2);

		cell2 = new PdfPCell(new Paragraph(subTotalWithGSTStr, font09));
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.TOP | PdfPCell.RIGHT);
		footerTable2.addCell(cell2);
		// Service Tax
		cell2 = new PdfPCell(new Paragraph(" ", font09));
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.LEFT);
		footerTable2.addCell(cell2);

		cell2 = new PdfPCell(new Paragraph("Amount(GST not applicable)", font09));
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.NO_BORDER);
		footerTable2.addCell(cell2);

		cell2 = new PdfPCell(new Paragraph(subTotalWithoutGSTStr, font09));
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.RIGHT);
		footerTable2.addCell(cell2);
		// Service Tax
		/*#270 PDF Enhancement CT 26072017*/
		if(!("0.00".equals(changeGstAmountStr))){
			cell2 = new PdfPCell(new Paragraph(" ", font09));
			cell2.setBackgroundColor(BaseColor.WHITE);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell2.setBorder(PdfPCell.LEFT);
			footerTable2.addCell(cell2);
			
			//cell2 = new PdfPCell(new Paragraph("GST ("+gstRate+"%)", font09));
			cell2 = new PdfPCell(new Paragraph("GST (7%)", font09));
			cell2.setBackgroundColor(BaseColor.WHITE);
			cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell2.setBorder(PdfPCell.NO_BORDER);
			footerTable2.addCell(cell2);
		
			cell2 = new PdfPCell(new Paragraph(changeGstAmountStr, font09));
			cell2.setBackgroundColor(BaseColor.WHITE);
			cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell2.setBorder(PdfPCell.RIGHT);
			footerTable2.addCell(cell2);
		}
		
		if(("0.00".equals(changeGstAmountStr)) || displayGST0){
			cell2 = new PdfPCell(new Paragraph(" ", font09));
			cell2.setBackgroundColor(BaseColor.WHITE);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell2.setBorder(PdfPCell.LEFT);
			footerTable2.addCell(cell2);
			
			//cell2 = new PdfPCell(new Paragraph("GST ("+gstRate+"%)", font09));
			cell2 = new PdfPCell(new Paragraph("GST (0%)", font09));
			cell2.setBackgroundColor(BaseColor.WHITE);
			cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell2.setBorder(PdfPCell.NO_BORDER);
			footerTable2.addCell(cell2);

			cell2 = new PdfPCell(new Paragraph(changeGst0AmountStr, font09));
			cell2.setBackgroundColor(BaseColor.WHITE);
			cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell2.setBorder(PdfPCell.RIGHT);
			footerTable2.addCell(cell2);
		}
		/*#270 PDF Enhancement CT 26072017*/
		// Grand Total
		cell2 = new PdfPCell(new Paragraph("For GST purpose only, equivalent amount in " + exportCur, font09));
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM);
		footerTable2.addCell(cell2);

		cell2 = new PdfPCell(new Paragraph("Total Amount Including GST", font09));
		cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.BOTTOM);
		footerTable2.addCell(cell2);

		cell2 = new PdfPCell(new Paragraph(changeBillAmountStr, font09));
		cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell2.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
		footerTable2.addCell(cell2);

		this.document.add(footerTable2);
		// }
		// String isManual =
		// CommonUtils.toString(this.headerInfo.get("IS_MANUAL"));
		// if("CN".equals(this.billingType) || "1".equals(isManual)) {
		// PdfPTable signatureTable = new PdfPTable(1);
		// signatureTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		// signatureTable.setWidthPercentage(45);
		// signatureTable.setSpacingBefore(50);
		// signatureTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//
		// PdfPCell cell = new PdfPCell(new Phrase("For NTT Singapore Pte.
		// Ltd.", font09));
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBorder(PdfPCell.TOP);
		// signatureTable.addCell(cell);
		//
		// this.document.add(signatureTable);
		// }

		// Footer
		// if (!"CN".equals(this.billingType)){
		List<Map<String, Object>> fInfosList = this.queryDAO.executeForMapList("B_BTH.getFooterInfo", null);
		if (fInfosList != null && 0 < fInfosList.size()) {
			PdfPTable footerTable = new PdfPTable(1);
			footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			footerTable.setWidthPercentage(100);
			footerTable.setSpacingBefore(30);

			for (Map<String, Object> footInfo : fInfosList) {
				String footerStr = CommonUtils.toString(footInfo.get("SET_VALUE")).trim();
				if (!CommonUtils.isEmpty(footerStr)) {
					PdfPCell cell = new PdfPCell(new Phrase(footerStr, font09));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(PdfPCell.NO_BORDER);
					footerTable.addCell(cell);
				}
			}
			this.document.add(footerTable);
		}

		float[] bankColWidths = { 60f, 4f, 80f, 80f, 80f };
		PdfPTable bankInfoTable = new PdfPTable(5);
		bankInfoTable.setWidthPercentage(100);
		bankInfoTable.setSpacingBefore(2);
		bankInfoTable.setWidths(bankColWidths);
		bankInfoTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		if (bankInfoList != null && 0 < bankInfoList.size()) {
			if (bankInfoList.size() == 1) {
				List<String> bankInfo = getBankInfo(bankInfoList.get(0));
				setBankInfo(bankInfoTable, "Bank", ":", bankInfo.get(0), "", "", "1");
				setBankInfo(bankInfoTable, "", "", bankInfo.get(1), "", "", "2");
				setBankInfo(bankInfoTable, "Account Name", ":", bankInfo.get(2), "", "", "2");
				setBankInfo(bankInfoTable, "Account No", ":", bankInfo.get(3), "", "", "2");
				setBankInfo(bankInfoTable, "SWIFT Code", ":", bankInfo.get(4), "", "", "3");
			} else if (bankInfoList.size() == 2) {
				List<String> bankInfo1 = getBankInfo(bankInfoList.get(0));
				List<String> bankInfo2 = getBankInfo(bankInfoList.get(1));
				setBankInfo(bankInfoTable, "Bank", ":", bankInfo1.get(0), bankInfo2.get(0), "", "1");
				setBankInfo(bankInfoTable, "", "", bankInfo1.get(1), bankInfo2.get(1), "", "2");
				setBankInfo(bankInfoTable, "Account Name", ":", bankInfo1.get(2), bankInfo2.get(2), "", "2");
				setBankInfo(bankInfoTable, "Account No", ":", bankInfo1.get(3), bankInfo2.get(3), "", "2");
				setBankInfo(bankInfoTable, "SWIFT Code", ":", bankInfo1.get(4), bankInfo2.get(4), "", "3");
			} else if (bankInfoList.size() == 3) {
				List<String> bankInfo1 = getBankInfo(bankInfoList.get(0));
				List<String> bankInfo2 = getBankInfo(bankInfoList.get(1));
				List<String> bankInfo3 = getBankInfo(bankInfoList.get(2));
				setBankInfo(bankInfoTable, "Bank", ":", bankInfo1.get(0), bankInfo2.get(0), bankInfo3.get(0), "1");
				setBankInfo(bankInfoTable, "", "", bankInfo1.get(1), bankInfo2.get(1), bankInfo3.get(1), "2");
				setBankInfo(bankInfoTable, "Account Name", ":", bankInfo1.get(2), bankInfo2.get(2), bankInfo3.get(2),
						"2");
				setBankInfo(bankInfoTable, "Account No", ":", bankInfo1.get(3), bankInfo2.get(3), bankInfo3.get(3),
						"2");
				setBankInfo(bankInfoTable, "SWIFT Code", ":", bankInfo1.get(4), bankInfo2.get(4), bankInfo3.get(4),
						"3");
			}
		} else {
			setBankInfo(bankInfoTable, "Bank", ":", "", "", "", "1");
			setBankInfo(bankInfoTable, "", "", "", "", "", "2");
			setBankInfo(bankInfoTable, "Account Name", ":", "", "", "", "2");
			setBankInfo(bankInfoTable, "Account No", ":", "", "", "", "2");
			setBankInfo(bankInfoTable, "SWIFT Code", ":", "", "", "", "3");
		}

		this.document.add(bankInfoTable);
		// }
	}

	
	private List<String> getBankInfo(Map<String, Object> bankInfo) {
		List<String> bankList = new ArrayList<String>();

		if (bankInfo != null) {
			String bankName = CommonUtils.toString(bankInfo.get("BANK_NAME"));
			String branchName = CommonUtils.toString(bankInfo.get("BRANCH_NAME"));
			String acctName = CommonUtils.toString(bankInfo.get("COM_ACCT_NAME"));
			String acctNoAndCur = "";
			String acctNo = CommonUtils.toString(bankInfo.get("COM_ACCT_NO"));
			String cur = CommonUtils.toString(bankInfo.get("COM_CUR"));
			String swift = CommonUtils.toString(bankInfo.get("COM_SWIFT"));
			if (!CommonUtils.isEmpty(cur)) {
				acctNoAndCur = acctNo + "(" + cur + ")";
			} else {
				acctNoAndCur = acctNo;
			}
			bankList.add(bankName);
			bankList.add(branchName);
			bankList.add(acctName);
			bankList.add(acctNoAndCur);
			bankList.add(swift);
		} else {
			bankList.add("");
			bankList.add("");
			bankList.add("");
			bankList.add("");
			bankList.add("");
		}
		return bankList;
	}

	private void setBankInfo(PdfPTable bankInfoTable, String colValue1, String colValue2, String colValue3,
			String colValue4, String colValue5, String lineType) {
												
							  
		PdfPCell bankCell = new PdfPCell(new Paragraph(colValue1, font09));
		bankCell.setBackgroundColor(BaseColor.WHITE);
		bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		// first Line
		if ("1".equals(lineType)) {
			bankCell.setBorder(PdfPCell.LEFT | PdfPCell.TOP);
		} else if ("2".equals(lineType)) {
			// middle Line
			bankCell.setBorder(PdfPCell.LEFT);
		} else if ("3".equals(lineType)) {
			// last Line
			bankCell.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM);
		}
		bankCell.setBorderColor(BaseColor.LIGHT_GRAY);
		bankCell.setPaddingLeft(3);
		bankInfoTable.addCell(bankCell);

		bankCell = new PdfPCell(new Paragraph(colValue2, font09));
		bankCell.setBackgroundColor(BaseColor.WHITE);
		bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		// first Line
		if ("1".equals(lineType)) {
			bankCell.setBorder(PdfPCell.TOP);
		} else if ("2".equals(lineType)) {
			// middle Line
			bankCell.setBorder(PdfPCell.NO_BORDER);
		} else if ("3".equals(lineType)) {
			// last Line
			bankCell.setBorder(PdfPCell.BOTTOM);
		}
		bankCell.setBorderColor(BaseColor.LIGHT_GRAY);
		bankInfoTable.addCell(bankCell);

		bankCell = new PdfPCell(new Paragraph(colValue3, font09));
		bankCell.setBackgroundColor(BaseColor.WHITE);
		bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		// first Line
		if ("1".equals(lineType)) {
			bankCell.setBorder(PdfPCell.TOP);
		} else if ("2".equals(lineType)) {
			// middle Line
			bankCell.setBorder(PdfPCell.NO_BORDER);
		} else if ("3".equals(lineType)) {
			// last Line
			bankCell.setBorder(PdfPCell.BOTTOM);
		}
		bankCell.setBorderColor(BaseColor.LIGHT_GRAY);
		bankInfoTable.addCell(bankCell);

		bankCell = new PdfPCell(new Paragraph(colValue4, font09));
		bankCell.setBackgroundColor(BaseColor.WHITE);
		bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		// first Line
		if ("1".equals(lineType)) {
			bankCell.setBorder(PdfPCell.TOP);
		} else if ("2".equals(lineType)) {
			// middle Line
			bankCell.setBorder(PdfPCell.NO_BORDER);
		} else if ("3".equals(lineType)) {
			// last Line
			bankCell.setBorder(PdfPCell.BOTTOM);
		}
		bankCell.setBorderColor(BaseColor.LIGHT_GRAY);
		bankInfoTable.addCell(bankCell);

		bankCell = new PdfPCell(new Paragraph(colValue5, font09));
		bankCell.setBackgroundColor(BaseColor.WHITE);
		bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		// first Line
		if ("1".equals(lineType)) {
			bankCell.setBorder(PdfPCell.TOP | PdfPCell.RIGHT);
		} else if ("2".equals(lineType)) {
			// middle Line
			bankCell.setBorder(PdfPCell.RIGHT);
		} else if ("3".equals(lineType)) {
			// last Line
			bankCell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
		}
		bankCell.setBorderColor(BaseColor.LIGHT_GRAY);
		bankInfoTable.addCell(bankCell);
	}

	private List<String> addressDisplayChange(String addr1, String addr2, String addr3, String addr4) {
		List<String> addressList = new ArrayList<String>();
		List<String> addressRetList = new ArrayList<String>();
		if (!CommonUtils.isEmpty(addr1)) {
			addressList.add(addr1);
		}
		if (!CommonUtils.isEmpty(addr2)) {
			addressList.add(addr2);
		}
		if (!CommonUtils.isEmpty(addr3)) {
			addressList.add(addr3);
		}
		if (!CommonUtils.isEmpty(addr4)) {
			addressList.add(addr4);
		}
		// if (!CommonUtils.isEmpty(addr5)) {
		// addressList.add(addr5);
		// }
		// if (!CommonUtils.isEmpty(addr6)) {
		// addressList.add(addr6);
		// }
		// if (!CommonUtils.isEmpty(addr7)) {
		// addressList.add(addr7);
		// }
		// if (!CommonUtils.isEmpty(addr8)) {
		// addressList.add(addr8);
		// }
		if (addressList.size() == 0) {
			addressRetList.add("");
			addressRetList.add("");
			addressRetList.add("");
			addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
		} else if (addressList.size() == 1) {
			addressRetList.add(addressList.get(0));
			addressRetList.add("");
			addressRetList.add("");
			addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
		} else if (addressList.size() == 2) {
			addressRetList.add(addressList.get(0));
			addressRetList.add(addressList.get(1));
			addressRetList.add("");
			addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
		} else if (addressList.size() == 3) {
			addressRetList.add(addressList.get(0));
			addressRetList.add(addressList.get(1));
			addressRetList.add(addressList.get(2));
			addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
		} else if (addressList.size() == 4) {
			addressRetList.add(addressList.get(0));
			addressRetList.add(addressList.get(1));
			addressRetList.add(addressList.get(2));
			addressRetList.add(addressList.get(3));
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
			// addressRetList.add("");
		}
		// else if(addressList.size()==5) {
		// addressRetList.add(addressList.get(0));
		// addressRetList.add(addressList.get(1));
		// addressRetList.add(addressList.get(2));
		// addressRetList.add(addressList.get(3));
		// addressRetList.add(addressList.get(4));
		// addressRetList.add("");
		// addressRetList.add("");
		// addressRetList.add("");
		// } else if(addressList.size()==6) {
		// addressRetList.add(addressList.get(0));
		// addressRetList.add(addressList.get(1));
		// addressRetList.add(addressList.get(2));
		// addressRetList.add(addressList.get(3));
		// addressRetList.add(addressList.get(4));
		// addressRetList.add(addressList.get(5));
		// addressRetList.add("");
		// addressRetList.add("");
		// } else if(addressList.size()==7) {
		// addressRetList.add(addressList.get(0));
		// addressRetList.add(addressList.get(1));
		// addressRetList.add(addressList.get(2));
		// addressRetList.add(addressList.get(3));
		// addressRetList.add(addressList.get(4));
		// addressRetList.add(addressList.get(5));
		// addressRetList.add(addressList.get(6));
		// addressRetList.add("");
		// } else if(addressList.size()==8) {
		// addressRetList.add(addressList.get(0));
		// addressRetList.add(addressList.get(1));
		// addressRetList.add(addressList.get(2));
		// addressRetList.add(addressList.get(3));
		// addressRetList.add(addressList.get(4));
		// addressRetList.add(addressList.get(5));
		// addressRetList.add(addressList.get(6));
		// addressRetList.add(addressList.get(7));
		// }
		return addressRetList;
	}
	
	private BigDecimal getSubTotalAmountByConvertion(String subTotalAmountStr, String exportSubTotalAmountStr, NumberFormat formatter,String isLumpSum){
		//formatter
		BigDecimal subTotalAmount = new BigDecimal(0);
		BigDecimal exportSubTotalAmount = new BigDecimal(0);
		try {
			subTotalAmount = new BigDecimal(formatter.parse(subTotalAmountStr).toString());
			exportSubTotalAmount = new BigDecimal(formatter.parse(exportSubTotalAmountStr).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
		String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		Object curRate = this.headerInfo.get("CUR_RATE");
		if (exportCur.equals("") || exportCur.equals("-")) {
			exportCur = billCurrency;
		}
		if(exportCur.equals(billCurrency)){
			return subTotalAmount;
		}else{
			G_CUR_P01 gCurP01 = new G_CUR_P01(this.queryDAO);
			Map<String, Object> convertCur2 = gCurP01.convertCurrency(billCurrency, subTotalAmount.doubleValue(),
					exportCur, curRate);
			BigDecimal result = new BigDecimal(convertCur2.get(G_CUR_P01.EXPORT_AMOUNT).toString());
			if("1".equals(isLumpSum.trim())){
				return result.setScale(2, RoundingMode.HALF_UP);
			}else{
				return result;
			}
			
		}
		
	}
	
	private BigDecimal getSubTotalAmount(String subTotalAmountStr, NumberFormat formatter){
		//formatter
		BigDecimal subTotalAmount = new BigDecimal(0);
		try {
			subTotalAmount = new BigDecimal(subTotalAmountStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return subTotalAmount;
	}

	class G_BTH_P01_PageEvent extends PdfPageEventHelper {
		private PdfTemplate total;
		private BaseFont font;

		private G_BTH_P01_PageEvent() {
		}

		@Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			total = writer.getDirectContent().createTemplate(30, 16);
			try {
				this.font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			try {
				if (document.getPageNumber() > 1 && document.isOpen()) {
					displayTitle();
					displayHeader();
					displayDetail_Header(true);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			super.onStartPage(writer, document);
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			int currentPage = Integer.parseInt(String.format("%d", writer.getPageNumber()));
			if (realPagePrinted.contains(currentPage)) {
				PdfContentByte cb = writer.getDirectContent();
				cb.saveState();
				String text = writer.getPageNumber() + "/";
				float textBase = document.top() - 88;// align top
				float textSize = font.getWidthPoint(text, 10);
				// float adjust = font.getWidthPoint("0", 10);
				cb.beginText();
				cb.setFontAndSize(font, 10);
				// write current page
				// cb.setTextMatrix(document.right() - textSize - adjust - 108,
				// textBase);
				cb.setTextMatrix(document.left() + 85, textBase);
				cb.showText(text);
				cb.endText();
				// cb.addTemplate(total, document.right() - adjust - 108,
				// textBase);
				cb.addTemplate(total, document.left() + textSize + 85, textBase);
				cb.restoreState();
			}

			// repeat header for each page
			PdfContentByte cb = writer.getDirectContent();
			// #77 ADD LOGO START
			try {
				// String contact = "Telephone +603-8319 0000 Facsimile
				// +603-8319 0199";
				float xLogoHeader = document.getPageSize().getWidth() - (3.28f) * PER_INCH;
				float yLogoHeader = document.getPageSize().getHeight() - (1.9f) * PER_INCH;
				// image path
				File logoFile = new File(getClass().getClassLoader().getResource("../../image/nttcom_min.jpg").toURI());
				String logoPath = logoFile.getAbsolutePath();
				Image logo = Image.getInstance(logoPath);
				logo.scaleAbsolute(2.55f * PER_INCH, 0.6f * PER_INCH);
				logo.setAbsolutePosition(xLogoHeader, yLogoHeader);
				cb.addImage(logo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onCloseDocument(PdfWriter writer, Document document) {
			// write total page
			total.beginText();
			total.setFontAndSize(font, 10);
			total.setTextMatrix(0, 0);
			total.showText(String.valueOf(writer.getPageNumber() - 1));
			total.endText();
		}
	}
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	private static void concatPDFs(List<InputStream> streamOfPDFFiles,
            OutputStream outputStream, boolean paginate) { 
        Document document = new Document();
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();
 
            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
 
            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data
 
            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();
 
            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();
 
                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);
 
                    // Code for pagination.
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
                                + currentPageNumber + " / " + totalPages, 520,
                                5, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
	
	private static void deleteFile(List<String> filePaths) {
		for(String filePath : filePaths)
			new File(filePath).delete();
	}
	
	private BigDecimal checkOneCent(String value1, String value2, String originalTotalStr) {
		BigDecimal subTotalWithGSTAfterConvert = new BigDecimal(0);
		BigDecimal subTotalWithoutGSTAfterConvert = new BigDecimal(0);
		BigDecimal subTotalAfterConvert = new BigDecimal(0);
		BigDecimal originalTotal = new BigDecimal(0);
		
		try {
			subTotalWithGSTAfterConvert = new BigDecimal(formatter.parse(value1).toString());
			subTotalWithoutGSTAfterConvert = new BigDecimal(formatter.parse(value2).toString());
			subTotalAfterConvert = subTotalWithGSTAfterConvert.add(subTotalWithoutGSTAfterConvert);
			originalTotal = new BigDecimal(formatter.parse(originalTotalStr).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return subTotalAfterConvert.subtract(originalTotal);
	}
	
	private BigDecimal formatStringToBigDecimal(String valueStr) {
		BigDecimal value = new BigDecimal(0);
		
		try {
			value = new BigDecimal(formatter.parse(valueStr).toString());
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	private BigDecimal customeRounding(BigDecimal s, int scale) {
        if (s == null) {
            s = BigDecimal.ZERO;
        }
        s = s.setScale(4, BigDecimal.ROUND_HALF_UP);
        String sStr = s.toString();
        for(int i=sStr.length()-1 ; i>sStr.indexOf('.') && i>sStr.indexOf('.')+1 ; i--){
        	s = s.setScale(i-(sStr.indexOf('.')), BigDecimal.ROUND_HALF_UP);
        }
        
        return s;
    }
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
}