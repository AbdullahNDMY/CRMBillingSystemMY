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

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bil.blogic.B_BIL_CommonUtil;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

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
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class G_BTH_P01_MY {
	
	public static String ID_MODULE="B";
	public static String ID_SUB_MODULE="B-BTH";
	public static String ACTION_AUTHENTICATION_FAILED="failed";
	public static String ACTION_EDIT="edit";
	public static String ACTION_VIEW="view";
	
	public static final String STATUS_SUCCESS = "0";
	public static final String STATUS_FAILED = "1";
	public static final String STATUS_INPROCESS = "2";
	
	public static final String STATUS_NOT_CLOSED = "0";
	public static final String STATUS_CLOSED = "1";
	
	public static final String STATUS_NOT_DELETED = "0";
	
	public static final String INVOICED = "1";
	public static final String NOT_INVOICED = "0";
	
	private static final int MAXIMUN_CHAR_PER_ROW = 50;
	// Fix extra Bank Line ST
	private static int ROW_PER_PAGE = 27;
	private static final int BANKINFO_MAXIMUN_CHAR_PER_ROW = 40;
	
	private static final int FEEDBACK_MAXIMUN_CHAR_PER_ROW = 157;
	private static final int GLOBAL_MAXIMUN_CHAR_PER_ROW = 2;
	private static final int SCOPEINFO1_PER_PAGE = 3;
	private static final int SCOPEINFO_PER_PAGE = 3;
	private static final int SCOPEINFO_FORMAT3_PER_PAGE = 3;
	private static final int SCOPEINFO1_FORMAT3_PER_PAGE = 4;
	// Fix extra Bank Line EN
	
	public static final String ID_SET_BATCH_G_SGP_P02 = "BATCH_G_BTH_P1";
	public static final String ID_BATCH_TYPE_G_SGP_P02 = "G_BTH_P01";
	public static final String ID_SET_BATCH_TIMEOUT = "BATCH_TIME_INTERVAL";
	public static final Integer SET_SEQ = 1;
	
	public static final String CUSTOMER_CELL = "A";
	public static final String INVOICE_CELL = "B";
	
	public static final String ID_SET_NOPRINT = "NOPRINTDOC";
	public static final int ACTIVE_SET_SEQ = 1;
	public static final String NOT_DELETED = "0";
	private static final int PER_INCH = 72;
	private static final Font font08b =  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	private static final Font font09 =  new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
	private static final Font font10 =  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	private static final Font font10b = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private static final Font font14b = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private Map<String, Object> currencyMap = null;
	private String footerInfo = null;
	private NumberFormat formatter;
	private List<Integer> realPagePrinted;
	
	private PdfWriter pdfW = null;
	
	//information to print
	private Document document;
	private String billingType;
	private Map<String, Object> headerInfo;
	private List<Map<String, Object>> detailInfo;
	private List<Map<String, Object>> detailScopeInfo;
	private List<Map<String, Object>> bankInfoList;
	private List<Map<String, Object>> firstBankInfoList;
	// Fix extra Bank Line ST
	private int bankInfoCount;
	private int feedbackCount;
	private int globalCount;
	private int taxCount;
	private int footerInfoCount;
	private String feedbackStr = "";
	//20190912 New Logo CT ST
	private String globalStr = "";
	//20190912 New Logo CT EN
	// Fix extra Bank Line EN
	private PdfPTable tableDetail;
	private BigDecimal subTotalDetail = new BigDecimal("0");
	private BigDecimal subTotalDetail2 = new BigDecimal("0");
	private BigDecimal subGSTAmount = new BigDecimal("0");
	private BigDecimal carryforward = new BigDecimal("0"); 
	private QueryDAO queryDAO;
    private UpdateDAO updateDAO;
    /*#594: [B1] Customer Maintenance CT 20190604*/
	private List<String> taxExemptedList = new ArrayList<String>();
	private BigDecimal subTaxExemptedAmount = new BigDecimal("0");
	/*#594: [B1] Customer Maintenance CT 20190604*/

    private String billCurrency;
    private String defaultCurrency;
    private BigDecimal subItemExportAmt = new BigDecimal("0");
    private BigDecimal subItemExportAmt2 = new BigDecimal("0");
    private BigDecimal subItemExportGst = new BigDecimal("0");
    
    private String oldFlag;
    private Map<String, List<String>> accAllMap=new HashMap<String, List<String>>();
    
    private String serviceTaxPercentage ;
    
    private int footerLineNo = 12;
    //20190912 New Logo CT ST
    private int globalLineNo = 13;
    //20190912 New Logo CT EN
    private int feedbackLineNo = 14;
    
    public G_BTH_P01_MY(QueryDAO queryDAO, UpdateDAO updateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
    }
	
	public String generate(String[] idRefs, BillingSystemUserValueObject uvo) {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		deleteFile(pdfFilePaths);
		return filePath;
	}
	
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	public String generateSingleDocInOnePDF(String[] idRefs, BillingSystemUserValueObject uvo) {
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
	
	public void emailGeneratePdf(String filePath, String idRef,
			BillingSystemUserValueObject uvo,String psd) {
		generate2(new String[] { idRef }, filePath, uvo, psd);
	}
	
	private String generate2(String[] idRefs, String outputFile, BillingSystemUserValueObject uvo, String pdfPsd) {
		//reset some field
		this.footerInfo = null;
		formatter = new DecimalFormat("###,##0.00");
		formatter.setRoundingMode(RoundingMode.HALF_UP);
		realPagePrinted = new ArrayList<Integer>();
		// set n = # of record in list
		int n = idRefs.length;
		// get total allowed print doc
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("idSet", ID_SET_NOPRINT);
		m.put("setSeq", ACTIVE_SET_SEQ);
		m.put("isDeleted", NOT_DELETED);
		Map<String, Object> mGsetD = this.queryDAO.executeForMap("B_BTH.getTotalAllowedPrintDoc", m);
		int totalAllowedPrintDoc = CommonUtils.toInteger(mGsetD.get("SET_VALUE"));
		int i = 0;
		int j = 0;
		if(j == n){
			// return i
		}else{
			//Generate pdf file
			this.document = new Document (PageSize.A4);
			this.document.setMarginMirroring(true); 
			//left right top bottom
			//0.3inch 0.3inch 2.0inch 0.25inch
			this.document.setMargins(0.3f * PER_INCH, 0.3f * PER_INCH, 1.8f * PER_INCH, 0f * PER_INCH);
			FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream (outputFile);
				pdfW = PdfWriter.getInstance(this.document, outputStream);
				if (!StringUtils.isEmpty(pdfPsd)){
					pdfW.setEncryption(pdfPsd.getBytes(), pdfPsd.getBytes(),
							PdfWriter.ALLOW_PRINTING,
							PdfWriter.ENCRYPTION_AES_128);
				}
				G_BTH_P01_PageEvent pageEvent = new G_BTH_P01_PageEvent();
				pdfW.setPageEvent(pageEvent);
				this.document.open();
				/*d.open ();
				Paragraph p = new Paragraph ("Some sample Text");
				d.add (p);
				d.close ();*/
				// Service Tax <Tax%> (<Cur>)
				serviceTaxPercentage = queryDAO.executeForObject("B_BTH.getGstAmount", null, String.class);
				
				for(int k = 0 ; k < idRefs.length; k ++){
					// totalAmonnt Cleared 
					subTotalDetail = new BigDecimal("0");
					subTotalDetail2 = new BigDecimal("0");
					subGSTAmount = new BigDecimal("0");
				    subItemExportAmt = new BigDecimal("0");
				    subItemExportAmt2 = new BigDecimal("0");
				    subItemExportGst = new BigDecimal("0");
				    
					boolean isNeedUpdate = true;
					String idRef = idRefs[k];
					HashMap<String, Object> m1 = new HashMap<String, Object>();
					m1.put("idRef", idRef);
					HashMap<String, Object> conditions = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BTH.getPrintContentByIdRef", m1);
					// if no printed < totalAllow print doc, allow for exporting pdf 
					if(conditions != null 
							&& CommonUtils.toInteger(conditions.get("NO_PRINTED")) < totalAllowedPrintDoc){
						// get ID_CUST
						String idCust = conditions.get("ID_CUST").toString();
						this.billingType = conditions.get("BILL_TYPE").toString();
						
						HashMap<String, Object> m2 = new HashMap<String, Object>();
						m2.put("idRef", idRef);
						m2.put("idCust", idCust);
						m2.put("billType", billingType);
						/*#594: [B1] Customer Maintenance CT 20190604*/
						m2.put("TAX_EXEMPTED_AMT", "true");
						/*#594: [B1] Customer Maintenance CT 20190604*/
						// check type of doc
						if(billingType.equals("IN") || billingType.equals("DN") || billingType.equals("CN")|| billingType.equals("NT")) {
							// new page
							pdfW.setPageEmpty(true);
							this.document.newPage();
							// get invoice header
							this.headerInfo = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BTH.getHeaderInfo", m2);
							// get detail info
							this.detailInfo = this.queryDAO.executeForObjectList("B_BTH.getDetailBillDocument_MY", m2);
							// get Scope of GST detail info
							this.defaultCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BTH.getSetValueOfSystemBase", null, String.class));
	                        this.billCurrency = CommonUtils.toString(headerInfo.get("BILL_CURRENCY")).trim();
							this.detailScopeInfo = this.queryDAO.executeForObjectList("B_BTH.getDetailScopeOfGST_MY", m2);
							//get company Bank info
                            //String accNo=CommonUtils.toString(this.headerInfo.get("CUST_ACC_NO"));
                            String companyBankInfo = queryDAO.executeForObject("B_BTH.getCompanyBankInfo", idCust,String.class);
                            // get Bank info
                            if("ST".equals(CommonUtils.toString(companyBankInfo))){
                                this.bankInfoList = this.queryDAO.executeForMapList("B_BTH.getBankInfoBillDocument", null);
                            }
                            else{
                                Map<String, Object> companybankinfo=new HashMap<String, Object>();
                                companybankinfo.put("idBank", CommonUtils.toString(companyBankInfo));
                                this.bankInfoList = this.queryDAO.executeForMapList("B_BTH.getSelectedBankInfoBillDocument", companybankinfo);
                            }
                            /*#594: [B1] Customer Maintenance CT 20190604*/
                            String taxExemptedListStr = queryDAO.executeForObject("B_BTH.getTaxExemptedList", null, String.class);
                            if(taxExemptedListStr != null || "".equals(taxExemptedListStr.trim())) {
                            	taxExemptedList =  Arrays.asList(taxExemptedListStr.split(","));
                            }
                            /*#594: [B1] Customer Maintenance CT 20190604*/
                            //get the acc_no what is not been set defalt
                            /*this.accAllMap=getAccNo(this.bankInfoList);
                            B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
                            this.bankInfoList =  bilUtil.getBankFooterInfo(billCurrency, bankInfoList);*/
                            B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
                            this.firstBankInfoList = bilUtil.getBankFooterInfo(billCurrency, bankInfoList);
                            this.accAllMap=getAccNo(this.firstBankInfoList, this.bankInfoList);
    						this.bankInfoList = this.firstBankInfoList;
    						// Fix extra Bank Line ST
    						this.bankInfoCount = getBankInfoCount();
    						
    						List<Map<String, Object>> fInfosList = this.queryDAO.executeForMapList("B_BTH.getFooterInfo", null);
    						Map<String, Object> feedbackLine = fInfosList.get(feedbackLineNo);
    						feedbackStr = CommonUtils.toString(feedbackLine.get("SET_VALUE")).trim();
    						
    						//20190912 New Logo CT ST
    						Map<String, Object> globalLine = fInfosList.get(globalLineNo);
    						globalStr = CommonUtils.toString(globalLine.get("SET_VALUE")).trim();
    						//20190912 New Logo CT EN
    						
    						this.feedbackCount = getFeedbackCount();
    						this.globalCount = getGlobalCount();
    						this.taxCount = getTaxCount();
    						this.footerInfoCount = getFooterInfoCount();
    						
    						try {
    							String recordPerPage = this.queryDAO.executeForObject("B_BTH.getNoRecordPerPage", null, String.class);
    							this.ROW_PER_PAGE = Integer.parseInt(recordPerPage);
    						}catch (Exception e) {
    							this.ROW_PER_PAGE = 27;
							}
    						// Fix extra Bank Line EN
    						
							// generate invoice pdf
							oldFlag = CommonUtils.toString(headerInfo.get("GSTAPPLYFLAG")).trim();
							if("0".equals(oldFlag)){
								generatePDF_old();
							}else{
								generatePDF();
							}
						}else{
							// no update
							isNeedUpdate = false; 	
						}
						i ++;
					}else{
						// go to next loop
						isNeedUpdate = false;
						continue;
					}
					if(isNeedUpdate){
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
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(this.document.isOpen())
					this.document.close();
				if(!pdfW.isCloseStream())
					pdfW.close();
				if(outputStream != null)
					try {
						outputStream.close();
					} catch (IOException e) {}
			}
		}
		return outputFile;
	}
	
	private void generatePDF() {
		try {
			// Format2
			if ("1".equals(oldFlag)) {
				// title
				this.displayTitle();
				// build table header
				this.displayHeader();
				// build table detail
				// build table detail header
				this.displayDetail();
				// footer
				this.displayFooter();
			}else if("9".equals(oldFlag)){
				// Format3
				// title
				this.displayTitle_Format3();
				// build table header
				this.displayHeader();
				// build table detail
				// build table detail header
				this.displayDetail_Format3();
				// footer
				this.displayFooter();
			}else {
				// Format3
				// title
				this.displayTitle_Format3();
				// build table header
				this.displayHeader_Format4();
				// build table detail
				// build table detail header
				this.displayDetail_Format4();
				// footer
				this.displayFooter();
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void generateError(){ 
	
	}
	
	/** 
	  * Create normal pdf cell
	  * <p> 
	  * 
	  * </p> 
	  * @param String title, Color color, int align
	  * @return PdfPCell
	  * @exception None
	  */
	private PdfPCell getCell(String title, BaseColor color, int align, Font font){
		PdfPCell cell = new PdfPCell(new Paragraph (title, font));
		cell.setBackgroundColor(color);
		cell.setHorizontalAlignment(align);
		return cell; 
	}
	
	/** 
	  * Create colspan pdf cell
	  * <p> 
	  * 
	  * </p> 
	  * @param String title, Color color, int align, int colspan
	  * @return PdfPCell
	  * @exception None
	  */
	private PdfPCell getCellColSpan(String title, BaseColor color, int align, int colspan, Font font){
		PdfPCell cell = new PdfPCell(new Paragraph (title, font));
		cell.setBackgroundColor(color);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		return cell; 
	}
	
	private void displayTitle() throws DocumentException {
		Paragraph pTitle = new Paragraph();
		pTitle.setAlignment(Element.ALIGN_CENTER);
		String XTitle = "INVOICE";
		if (this.billingType.equals("IN")){
			XTitle = "TAX INVOICE";
		} else if(this.billingType.equals("DN")){
			XTitle = "DEBIT NOTE";
		} else if(this.billingType.equals("CN")){
			XTitle = "CREDIT NOTE";
		}
		Phrase phTitle = new Phrase(XTitle, font14b);
		pTitle.add(phTitle);
		this.document.add(pTitle);
	}
	
	private void displayTitle_Format3() throws DocumentException {
		Paragraph pTitle = new Paragraph();
		pTitle.setAlignment(Element.ALIGN_CENTER);
		String XTitle = "INVOICE";
		if (this.billingType.equals("IN")){
			XTitle = "INVOICE";
		} else if(this.billingType.equals("DN")){
			XTitle = "DEBIT NOTE";
		} else if(this.billingType.equals("CN")){
			XTitle = "CREDIT NOTE";
		}
		Phrase phTitle = new Phrase(XTitle, font14b);
		pTitle.add(phTitle);
		this.document.add(pTitle);
	}
	
	private void displayHeader() throws DocumentException {
		/*
		 * A4 = 8.27inch x 11.69inch
		 */
		float[] colWidths = {346.9f, 90f, 7f, 135f};
		PdfPTable tHeader = new PdfPTable(4);
		tHeader.setWidthPercentage(100);
		tHeader.setWidths(colWidths);
		tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tHeader.setSpacingBefore(18);
		float padding = 0f;
		
		//data for column LEFT and RIGHT
		List<String> dataLeft = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean add(String e) {
				if(e == null) return super.add(" ");
				if(!e.trim().equals(""))
					return super.add(e);
				return false;
			}
		};
		List<String[]> dataRight = new ArrayList<String[]>();
		
		dataLeft.add(CommonUtils.toString(headerInfo.get("CUST_NAME")));
		String XTitle2 = "Invoice";
		if (this.billingType.equals("DN")){
			XTitle2 = "Debit Note";
		} else if(this.billingType.equals("CN")){
			XTitle2 = "Credit Note";
		}
		XTitle2 = XTitle2 + " No.";
		dataRight.add(new String[]{XTitle2, ":", CommonUtils.toString(this.headerInfo.get("ID_REF"))});

		// format: page/totalPage, totalPage will fill by Event object
		realPagePrinted.add(pdfW.getCurrentPageNumber());
		String address1 = StringEscapeUtils.unescapeHtml(CommonUtils.toString(this.headerInfo.get("ADDRESS1")));
		dataLeft.add(address1);
		dataRight.add(new String[] {"Page", ":", ""});
		
		String address2 = CommonUtils.toString(this.headerInfo.get("ADDRESS2"));
		dataLeft.add(address2);
		//String jobNo = CommonUtils.toString(this.headerInfo.get("JOB_NO"));
		//dataRight.add(new String[] {"Job No", ":", jobNo.substring(0, Math.min(7, jobNo.length()))});
		
		String address3 = "";
		if(! CommonUtils.toString(this.headerInfo.get("ADDRESS3")).isEmpty())
			address3 = CommonUtils.toString(this.headerInfo.get("ADDRESS3"));
		dataLeft.add(address3);
		dataRight.add(new String[] {"Issue Date", ":", CommonUtils.toString(this.headerInfo.get("DATE_REQ"))});
		
		String address4 = "";
		if(! CommonUtils.toString(this.headerInfo.get("ADDRESS4")).isEmpty()) {
			CommonUtils.fixAddress4(this.headerInfo);
			address4 = CommonUtils.toString(this.headerInfo.get("ADDRESS4"));
		}
		if(this.billingType.equals("IN")) {
			dataLeft.add(address4);
			addHeaderTerm(dataRight);
			
			addHeaderTel(dataLeft);
			addHeaderPO(dataRight);
			
			addHeaderQuo(dataRight);
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			dataLeft.add(null);
			addHeaderAc(dataRight);
			
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		else if(this.billingType.equals("DN")) {
			dataLeft.add(address4);
			addHeaderTerm(dataRight);
			
			addHeaderTel(dataLeft);
			addHeaderAc(dataRight);
			
			dataRight.add(new String[] {"", "", ""});
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);

			dataLeft.add(null);
			dataRight.add(new String[] {"", "", ""});
			
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		else if(this.billingType.equals("CN")) {
			dataLeft.add(address4);
			addHeaderAc(dataRight);
			
			addHeaderTel(dataLeft);
			dataRight.add(new String[] {"", "", ""});
			
			dataRight.add(new String[] {"", "", ""});
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			dataLeft.add(null);
            dataRight.add(new String[] {"", "", ""});
            
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		} else if(this.billingType.equals("NT")) {
			dataLeft.add(address4);
			addHeaderTerm(dataRight);
			
			addHeaderTel(dataLeft);
			addHeaderPO(dataRight);
			
			addHeaderQuo(dataRight);
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			dataLeft.add(null);
			addHeaderAc(dataRight);
			
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		//<dataLeft> has same length with <dataRight>
		int n = dataLeft.size();
		for(int i = 0; i < n; i++) {
			if(i < n - 1)
				this.displayHeader_CellLeft(tHeader, padding, dataLeft.get(i), font10);
			else
				this.displayHeader_CellLeft(tHeader, padding, dataLeft.get(i), font10b);
			this.displayHeader_CellRight(tHeader, padding, dataRight.get(i)[0], dataRight.get(i)[1], dataRight.get(i)[2]);
		}
		// add table header
		this.document.add(tHeader);
	}
	
	/*#594: [B1] Customer Maintenance CT 20190604*/
	private void displayHeader_Format4() throws DocumentException {
		/*
		 * A4 = 8.27inch x 11.69inch
		 */
		float[] colWidths = {346.9f, 90f, 7f, 135f};
		PdfPTable tHeader = new PdfPTable(4);
		tHeader.setWidthPercentage(100);
		tHeader.setWidths(colWidths);
		tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tHeader.setSpacingBefore(18);
		float padding = 0f;
		
		//data for column LEFT and RIGHT
		List<String> dataLeft = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean add(String e) {
				if(e == null) return super.add(" ");
				if(!e.trim().equals(""))
					return super.add(e);
				return false;
			}
		};
		List<String[]> dataRight = new ArrayList<String[]>();
		
		dataLeft.add(CommonUtils.toString(headerInfo.get("CUST_NAME")));
		String XTitle2 = "Invoice";
		if (this.billingType.equals("DN")){
			XTitle2 = "Debit Note";
		} else if(this.billingType.equals("CN")){
			XTitle2 = "Credit Note";
		}
		XTitle2 = XTitle2 + " No.";
		dataRight.add(new String[]{XTitle2, ":", CommonUtils.toString(this.headerInfo.get("ID_REF"))});

		// format: page/totalPage, totalPage will fill by Event object
		realPagePrinted.add(pdfW.getCurrentPageNumber());
		String address1 = StringEscapeUtils.unescapeHtml(CommonUtils.toString(this.headerInfo.get("ADDRESS1")));
		dataLeft.add(address1);
		dataRight.add(new String[] {"Page", ":", ""});
		
		String address2 = CommonUtils.toString(this.headerInfo.get("ADDRESS2"));
		dataLeft.add(address2);
		//String jobNo = CommonUtils.toString(this.headerInfo.get("JOB_NO"));
		//dataRight.add(new String[] {"Job No", ":", jobNo.substring(0, Math.min(7, jobNo.length()))});
		
		String address3 = "";
		if(! CommonUtils.toString(this.headerInfo.get("ADDRESS3")).isEmpty())
			address3 = CommonUtils.toString(this.headerInfo.get("ADDRESS3"));
		dataLeft.add(address3);
		dataRight.add(new String[] {"Issue Date", ":", CommonUtils.toString(this.headerInfo.get("DATE_REQ"))});
		
		String address4 = "";
		if(! CommonUtils.toString(this.headerInfo.get("ADDRESS4")).isEmpty()) {
			CommonUtils.fixAddress4(this.headerInfo);
			address4 = CommonUtils.toString(this.headerInfo.get("ADDRESS4"));
		}
		if(this.billingType.equals("IN")) {
			dataLeft.add(address4);
			addHeaderTerm(dataRight);
			
			addHeaderTel(dataLeft);
			addHeaderPO(dataRight);
			
			addHeaderQuo(dataRight);
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			//dataLeft.add(null);
			/*#594: [B1] Customer Maintenance CT 20190604*/
			addServiceTaxNo(dataLeft);
			/*#594: [B1] Customer Maintenance CT 20190604*/
			addHeaderAc(dataRight);
			
			dataLeft.add(null);
			dataRight.add(new String[] {" ", " ", " "});
			
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		else if(this.billingType.equals("DN")) {
			dataLeft.add(address4);
			addHeaderTerm(dataRight);
			
			addHeaderTel(dataLeft);
			addHeaderAc(dataRight);
			
			dataRight.add(new String[] {"", "", ""});
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);

			//dataLeft.add(null);
			/*#594: [B1] Customer Maintenance CT 20190604*/
			addServiceTaxNo(dataLeft);
			/*#594: [B1] Customer Maintenance CT 20190604*/
			dataRight.add(new String[] {"", "", ""});
			
			dataLeft.add("a ");
			dataRight.add(new String[] {" ", "", ""});
			
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		else if(this.billingType.equals("CN")) {
			dataLeft.add(address4);
			addHeaderAc(dataRight);
			
			addHeaderTel(dataLeft);
			dataRight.add(new String[] {"", "", ""});
			
			dataRight.add(new String[] {"", "", ""});
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			//dataLeft.add(null);
			/*#594: [B1] Customer Maintenance CT 20190604*/
			addServiceTaxNo(dataLeft);
			/*#594: [B1] Customer Maintenance CT 20190604*/
            dataRight.add(new String[] {"", "", ""});
            
            dataLeft.add("a ");
			dataRight.add(new String[] {" a", "", ""});
            
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		} else if(this.billingType.equals("NT")) {
			dataLeft.add(address4);
			addHeaderTerm(dataRight);
			
			addHeaderTel(dataLeft);
			addHeaderPO(dataRight);
			
			addHeaderQuo(dataRight);
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			//dataLeft.add(null);
			/*#594: [B1] Customer Maintenance CT 20190604*/
			addServiceTaxNo(dataLeft);
			/*#594: [B1] Customer Maintenance CT 20190604*/
			addHeaderAc(dataRight);
			
			dataLeft.add("a ");
			dataRight.add(new String[] {"a ", "", ""});
			
			addHeaderAtt(dataLeft);
			dataRight.add(new String[] {"", "", ""});			
		}
		//<dataLeft> has same length with <dataRight>
		int n = dataLeft.size();
		for(int i = 0; i < n; i++) {
			if(i < n - 1)
				this.displayHeader_CellLeft(tHeader, padding, dataLeft.get(i), font10);
			else
				this.displayHeader_CellLeft(tHeader, padding, dataLeft.get(i), font10b);
			this.displayHeader_CellRight(tHeader, padding, dataRight.get(i)[0], dataRight.get(i)[1], dataRight.get(i)[2]);
		}
		// add table header
		this.document.add(tHeader);
	}
	
	/*#594: [B1] Customer Maintenance CT 20190604*/
	private void addServiceTaxNo(List<String> data) {
	    String serviceTaxNo = CommonUtils.toString(this.headerInfo.get("OTHERS_REF_NO")).trim();
	    if (!CommonUtils.isEmpty(serviceTaxNo)){
	        data.add("Service Tax No."+": "+serviceTaxNo);
        }else {
        	data.add("Service Tax No."+": "+"-");
        }
	}
	/*#594: [B1] Customer Maintenance CT 20190604*/
	
	private void addHeaderTerm(List<String[]> data) {
		data.add(new String[]{"Term", ":", CommonUtils.toString(this.headerInfo.get("TERM"))});
	}
	
	private void addHeaderTel(List<String> data) {
	    String telNo = CommonUtils.toString(this.headerInfo.get("TEL_NO")).trim();
	    String faxNo = CommonUtils.toString(this.headerInfo.get("FAX_NO")).trim();
	    if (!CommonUtils.isEmpty(telNo)&&!CommonUtils.isEmpty(faxNo)){
	        data.add("Tel: "+CommonUtils.toString(this.headerInfo.get("TEL_NO")) + "    Fax: " + CommonUtils.toString(this.headerInfo.get("FAX_NO")));
	    } else if(!CommonUtils.isEmpty(telNo)&&CommonUtils.isEmpty(faxNo)){
	        data.add("Tel: "+CommonUtils.toString(this.headerInfo.get("TEL_NO")));
	    } else if (!CommonUtils.isEmpty(telNo)&&!CommonUtils.isEmpty(faxNo)){
            data.add("Fax: " + CommonUtils.toString(this.headerInfo.get("FAX_NO")));
        }
	}
	
	private void addHeaderAtt(List<String> data) {
		data.add("Attention : " + CommonUtils.toString(this.headerInfo.get("CONTACT_NAME")));
	}
	
	private void addHeaderPO(List<String[]> data) {
		data.add(new String[]{"P/O No.", ":", CommonUtils.toString(this.headerInfo.get("CUST_PO"))});
	}
	
	private void addHeaderQuo(List<String[]> data) {
		data.add(new String[]{"Quotation No.", ":", CommonUtils.toString(this.headerInfo.get("QUO_REF"))});
	}
	
	private void addHeaderAc(List<String[]> data) {
		data.add(new String[]{"A/C Manager", ":", CommonUtils.toString(this.headerInfo.get("ID_CONSULT_NAME"))});
	}
	
	private void displayHeader_CellLeft(PdfPTable tHeader, float padding, String value, Font font) {
		PdfPCell c = new PdfPCell(new Phrase(value, font));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
	}
	
	private void displayHeader_CellRight(PdfPTable tHeader, float padding, String label, String commas, Object value) {
		PdfPCell c = null;
		c = new PdfPCell(new Phrase(label, font10));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
		c = new PdfPCell(new Phrase(commas, font10));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
		c = new PdfPCell(new Phrase(CommonUtils.toString(value), font10));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
	}
	
	private void displayDetail_Header(boolean isAdded) throws DocumentException {
		PdfPCell c = null;
		//0.5inch 4.25inch 0.875inch 0.875inch 1inch
		if (this.billingType.equals("NT")){
			this.tableDetail = new PdfPTable(5);
			float[] colWidths = {0.5f * PER_INCH, 4.25f * PER_INCH, 0.875f * PER_INCH, 0.875f * PER_INCH, 1f * PER_INCH};
			this.tableDetail.setWidths(colWidths);
			this.tableDetail.setSpacingBefore(6);
		} else {
			this.tableDetail = new PdfPTable(6);
			if ("9".equals(oldFlag)) {
				float[] colWidths = {0.5f * PER_INCH, 4.25f * PER_INCH, 0.875f * PER_INCH, 0.875f * PER_INCH, 1f * PER_INCH, 0.7f * PER_INCH};
				this.tableDetail.setWidths(colWidths);
			}else {
				float[] colWidths = {0.5f * PER_INCH, 4.25f * PER_INCH, 0.875f * PER_INCH, 0.875f * PER_INCH, 1f * PER_INCH, 0.5f * PER_INCH};
				this.tableDetail.setWidths(colWidths);
			}
			this.tableDetail.setSpacingBefore(7);
		}
		this.tableDetail.setWidthPercentage(100);
		c = new PdfPCell(new Phrase("Item", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("Description", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Quantity", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Unit Price", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Amount ("+this.headerInfo.get("BILL_CURRENCY")+")", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		if (!this.billingType.equals("NT")){
			if ("9".equals(oldFlag)) {
				c = new PdfPCell(new Phrase("Tax Amount", font10b));
			}else {
				c = new PdfPCell(new Phrase("Tax Code", font10b));
			}
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			c.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c.setFixedHeight(0.35f * PER_INCH);//0.35inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		
		if(isAdded) {//this case for more 1 page (from 2 page)
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
			
			c = new PdfPCell(new Phrase(CommonUtils.toString(formatter.format(this.carryforward)), font10b));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setVerticalAlignment(PdfPCell.ALIGN_TOP);
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);
			
			if (!this.billingType.equals("NT")){
				c = new PdfPCell();
				c.setBorder(PdfPCell.RIGHT);
				this.tableDetail.addCell(c);
			}
		}
	}
	
	private void displayDetail() throws DocumentException {
		PdfPCell c = null;
		this.displayDetail_Header(false);
		// in fact no of page
		this.subTotalDetail = new BigDecimal("0");
		this.carryforward = new BigDecimal("0");
		// loop for each detail and display
		int index = 0, rowPerPage = 0, nextRow = 0;
		String sItem, description, sQty, sPrice, sAmt, sTax;
		String period = null;
		boolean lumpsumFlag = false;
		String itemDiscAmt1 = "", sTax1 = "";
		for(int i = 0; i < this.detailInfo.size(); i++){
			Map<String,Object> detail = this.detailInfo.get(i);
			String isDisplay = CommonUtils.toString(detail.get("IS_DISPLAY"));
			String itemCat = CommonUtils.toString(detail.get("ITEM_CAT"));
			String jobNo = CommonUtils.toString(detail.get("JOB_NO")).trim();
			String isDisplayJobno = CommonUtils.toString(detail.get("IS_DISPLAY_JOBNO")).trim();
			String taxCode = CommonUtils.toString(detail.get("TAX_CODE")).trim();
			String itemDiscAmt = formatter.format(detail.get("ITEM_DISC_AMT"));
			String GSTAmount = CommonUtils.toString(detail.get("ITEM_GST")).trim();
			
			// GST Amount
			if("1".equals(detail.get("ITEM_LEVEL"))){
				this.subGSTAmount = this.subGSTAmount.add(new BigDecimal(GSTAmount));
				BigDecimal itemAmt = (BigDecimal)detail.get("ITEM_SUBTOTAL");
				BigDecimal itemExportAmt = (BigDecimal)detail.get("ITEM_EXPORT_AMT");
				BigDecimal itemExportGst = (BigDecimal)detail.get("ITEM_EXPORT_GST");
				if (itemAmt != null){
					this.subTotalDetail = this.subTotalDetail.add(itemAmt);
				}
				if (itemExportAmt != null){
					this.subItemExportAmt = this.subItemExportAmt.add(itemExportAmt);
				}
				if (itemExportGst != null){
					this.subItemExportGst = this.subItemExportGst.add(itemExportGst);
				}
			}
			
			if (!CommonUtils.isEmpty(isDisplay)) {
			    if ("0".equals(isDisplay)) {//without lump sum
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
			    if ("0".equals(isDisplay)) {//without lump sum
					sQty = "";
					sPrice = "";
					sAmt = "";
				}
				else {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
					
					if("0".equals(sQty)) {
					    sQty = "";
					}
					if("0.00".equals(sPrice)) {
					    sPrice = "";
                    }
					if("0.00".equals(sAmt)) {
					    sAmt = "";
                    }
				}
			} else {
				try {
					if(Double.parseDouble(detail.get("ITEM_QTY").toString()) == 0 || 
							Double.parseDouble(detail.get("ITEM_UPRICE").toString()) == 0 ||
							Double.parseDouble(detail.get("ITEM_AMT").toString()) == 0) {
						sQty = "";
						sPrice = "";
						sAmt = "";
					}
					else {
						sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
						sPrice = formatter.format(detail.get("ITEM_UPRICE"));
						sAmt = formatter.format(detail.get("ITEM_AMT"));
                        
                        if("0".equals(sQty)) {
                            sQty = "";
                        }
                        if("0.00".equals(sPrice)) {
                            sPrice = "";
                        }
                        if("0.00".equals(sAmt)) {
                            sAmt = "";
                        }
					}
				} catch(Exception e) {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
				}
			}
			// billing period
			period = "";
			if((i + 1 < this.detailInfo.size() 
                    && this.detailInfo.get(i + 1).get("ITEM_LEVEL").equals("0")
                    && "1".equals(itemCat)) 
                    || (i == this.detailInfo.size() - 1 && "1".equals(itemCat))) {
				if(detail.get("BILL_FROM") != null && detail.get("BILL_TO") != null) {	
					if(!CommonUtils.toString(detail.get("BILL_FROM")).equals(CommonUtils.toString(detail.get("BILL_TO")))){
						period = "Billing Period: " 
	                        + CommonUtils.toString(detail.get("BILL_FROM")) + " ~ " 
	                        + CommonUtils.toString(detail.get("BILL_TO")) + "\r\n";
					} else {
					    period = " ";
					}
				}
				if(i != this.detailInfo.size() - 1 && !period.equals(""))
					period += "\r\n";//break line for next description
				if(i == this.detailInfo.size() - 1)
					period += "\r\n";//end of detail
			}
			// calculate to decide new page or not
			List<String> rows = getRows(description, MAXIMUN_CHAR_PER_ROW);
			if(period.equals("")) nextRow = rows.size();
			else {
			    if (period.equals(" \r\n") || period.equals("\r\n")) {
			        nextRow = rows.size() + 1;
			    } else {
			        nextRow = rows.size() + 2;//description + period	
			    }
				rows.add(period);
			}
			if((rowPerPage) > ROW_PER_PAGE) {//new page
				rowPerPage = 0;
				// sub total balance line
				c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
				c.setFixedHeight(0.2f * PER_INCH);//0.2inch
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				if (this.billingType.equals("NT")){
					c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER,
							font10);
				} else {
					c = getCellColSpan(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER, 2,
							font10);
				}
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				
				this.document.add(this.tableDetail);
				pdfW.setPageEmpty(true);
				this.document.newPage();
			}
			boolean itemFlag = true;
			if ("1".equals(detail.get("DISPLAY_DISC")) && "0".equals(detail.get("ITEM_LEVEL"))){
				lumpsumFlag = true;
				itemDiscAmt1 = itemDiscAmt;
				sTax1 = taxCode;
			}
			if(rowPerPage + nextRow > ROW_PER_PAGE) {
				String desc = "";
				boolean isNewPage = true;
				boolean isFistPage = true;
				while(!rows.isEmpty()) {
					desc = "";
					isNewPage = true;
					int k = 0;
					for(k = 0; k < ROW_PER_PAGE - rowPerPage; k++) {
						if(rows.isEmpty()) {
							isNewPage = false;
							break;
						}
						if(!(!period.equals("") && rows.size() == 1)){
						    
						    String rowDesc = rows.remove(0);
                            if (rowDesc != null) {
                                if ( (rowDesc.length() >=1 && rowDesc.substring(rowDesc.length() - 1, rowDesc.length()).equals("\r"))
                                        || (rowDesc.length() >=2 && rowDesc.substring(rowDesc.length() - 2, rowDesc.length()).equals("\r\n"))) {
                                    desc += rowDesc;
                                } else {
                                    desc += rowDesc + "\r";
                                }
                            }
                            
//							desc += ("\r\n" + rows.remove(0));
						}else{
							rows.remove(0);
						}
					}
					rowPerPage += k;
					//only print full information in first page
					if ("1".equals(isDisplayJobno)) {
                        this.displayDetail_Description("", "Job No.: " + jobNo, "", "", "", "", 0f);
                        rowPerPage = rowPerPage + 1;
                    }
					if(isFistPage) {
						isFistPage = false;
						if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
							sTax = taxCode;
						}else{
							sTax = "";
						}

						this.displayDetail_Description(sItem, desc, sQty, sPrice, sAmt, sTax);
			
						if(rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag){
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
									rowPerPage = rowPerPage + 1;
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
								}
							}
							if(period != null && !period.equals("")) {
							    // Discount (Lumpsum)
								if (lumpsumFlag){
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description("", " ", "", "", "", "", 0f);
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
									this.displayDetail_Description("", " ", "", "", "", "", 0f);
								}
							    this.displayDetail_Description("", period, "", "", "", "", 0f);
							}
						}
					} else {
				
					    this.displayDetail_Description("", desc, "", "", "", "", 0f);
					    
						
						if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
							sTax = taxCode;
						}else{
							sTax = "";
						}
						if(rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag){
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
									rowPerPage = rowPerPage + 1;
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
								}
							}
							if(period != null && !period.equals("")) {
							    // Discount (Lumpsum)
								if (lumpsumFlag){
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description("", " ", "", "", "", "", 0f);
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
							        this.displayDetail_Description("", " ", "", "", "", "", 0f);
								}
							    this.displayDetail_Description("", period, "", "", "", "", 0f);
							}
						}
					}
					//new page
					if(isNewPage) {
						rowPerPage = 0;
						// sub total balance line
						c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
						c.setFixedHeight(0.2f * PER_INCH);//0.2inch
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						if (this.billingType.equals("NT")){
							c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER,
									font10);
						} else {
							c = getCellColSpan(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER, 2,
									font10);
						}
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						
						this.document.add(this.tableDetail);
						pdfW.setPageEmpty(true);
						this.document.newPage();
					}
				}
			} else {
				//rowPerPage = rowPerPage + nextRow;
				rowPerPage = rowPerPage + nextRow;
				//print
				if ("1".equals(isDisplayJobno)) {
                    this.displayDetail_Description("", "Job No.: " + jobNo, "", "", "", "", 0f);
                    rowPerPage = rowPerPage + 1;
                }
				if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
					sTax = taxCode;
				}else{
					sTax = "";
				}
				this.displayDetail_Description(sItem, description, sQty, sPrice, sAmt, sTax);
				// Discount (Item)
				if (itemFlag){
					itemFlag = false;
					if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
						rowPerPage = rowPerPage + 1;
						this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
					}
				}
				if(period != null && !period.equals("")) {
				    // Discount (Lumpsum)
					if (lumpsumFlag){
						lumpsumFlag = false;
						rowPerPage = rowPerPage + 3;
						this.displayDetail_Description("", " ", "", "", "", "", 0f);
						this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
						this.displayDetail_Description("", " ", "", "", "", "", 0f);
					}
				    this.displayDetail_Description("", period, "", "", "", "", 0f);
				}
			}
		}
		if(rowPerPage > ROW_PER_PAGE) {
			c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
			c.setFixedHeight(0.2f * PER_INCH);//0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			if (this.billingType.equals("NT")){
				c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER,
						font10);
			} else {
				c = getCellColSpan(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER, 2,
						font10);
			}
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			
			this.document.add(this.tableDetail);
			pdfW.setPageEmpty(true);
			this.document.newPage();
		}
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
        String expCurrency = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		// sub total line
		c = getCellColSpan("Sub Total (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(CommonUtils.toString(formatter.format(this.subTotalDetail)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(CommonUtils.toString(formatter.format(this.subTotalDetail)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// GST Amount
		c = getCellColSpan("GST Amount (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(CommonUtils.toString(formatter.format(subGSTAmount)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(CommonUtils.toString(formatter.format(subGSTAmount)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// grant total line 
		c = getCellColSpan("Grand Total (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.LIGHT_GRAY, 
				Element.ALIGN_CENTER, 4, font10b);
		c.setFixedHeight(0.25f * PER_INCH);//0.25inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(formatter.format(this.headerInfo.get("BILL_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
					font10b);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(formatter.format(this.headerInfo.get("BILL_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
					font10b);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setBorderColorRight(BaseColor.LIGHT_GRAY);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// grant total fixed forex
		// if C.IS_DISPLAY = 1, display, else do not display.
		//String cIsDisplay = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY"));
		String isDisplayExpAmt = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY_EXP_AMT"));
		if ("1".equals(isDisplayExpAmt)) {
		    String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BTH.getSetValueOfSystemBase", null, String.class));
		    String grandWording = "";
		    if (billCurrency.equals(defCurrency)) {
		        grandWording = "Grand Total " + expCurrency + " ";
	            grandWording = grandWording + "(1 " + expCurrency + " = ";
	            String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
	            if (!CommonUtils.isEmpty(fixedForex)) {
	                grandWording += "Fixed Forex ";
	            }
	            grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + billCurrency + ")";
		    } else {
		        grandWording = "Grand Total " + expCurrency + " ";
                grandWording = grandWording + "(1 " + billCurrency + " = ";
                String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
                if (!CommonUtils.isEmpty(fixedForex)) {
                    grandWording += "Fixed Forex ";
                }
                grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + expCurrency + ")";
		    }
		    
		    c = getCellColSpan(grandWording, BaseColor.LIGHT_GRAY, 
                    Element.ALIGN_CENTER, 4, font10b);
            c.setFixedHeight(0.25f * PER_INCH);//0.25inch
            c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            this.tableDetail.addCell(c);
            if (this.billingType.equals("NT")){
            	c = getCell(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
                        font10b);
    			this.tableDetail.addCell(c);
    		} else {
    			c = getCell(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
                        font10b);
                c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
        		this.tableDetail.addCell(c);
        		c = getCell("", BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,font10);
        		c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        		c.setBorderColorRight(BaseColor.LIGHT_GRAY);
        		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        		this.tableDetail.addCell(c);
    		}
		}
		this.document.add(this.tableDetail);
	}
	
	private void displayDetail_Format3() throws DocumentException{

		PdfPCell c = null;
		this.displayDetail_Header(false);
		// in fact no of page
		this.subTotalDetail = new BigDecimal("0");
		this.subTotalDetail2 = new BigDecimal("0");
		this.carryforward = new BigDecimal("0");
		// loop for each detail and display
		int index = 0, rowPerPage = 0, nextRow = 0;
		String sItem, description, sQty, sPrice, sAmt, sTax;
		String period = null;
		boolean lumpsumFlag = false;
		String itemDiscAmt1 = "", sTax1 = "";
		for(int i = 0; i < this.detailInfo.size(); i++){
			Map<String,Object> detail = this.detailInfo.get(i);
			String isDisplay = CommonUtils.toString(detail.get("IS_DISPLAY"));
			String itemCat = CommonUtils.toString(detail.get("ITEM_CAT"));
			String jobNo = CommonUtils.toString(detail.get("JOB_NO")).trim();
			String isDisplayJobno = CommonUtils.toString(detail.get("IS_DISPLAY_JOBNO")).trim();
			String taxCode = CommonUtils.toString(detail.get("TAX_CODE")).trim();
			String itemDiscAmt = formatter.format(detail.get("ITEM_DISC_AMT"));
			String GSTAmount = CommonUtils.toString(detail.get("ITEM_GST")).trim();
			String taxRate = CommonUtils.toString(detail.get("TAX_RATE")).trim();
			
			// GST Amount --> Service Tax
			if("1".equals(detail.get("ITEM_LEVEL"))){
				this.subGSTAmount = this.subGSTAmount.add(new BigDecimal(GSTAmount));								
			}
			
			if("1".equals(detail.get("ITEM_LEVEL"))){
				BigDecimal itemAmt = (BigDecimal)detail.get("ITEM_SUBTOTAL");
				if (itemAmt != null){
					if (!"0".equals(taxRate)) {
						// SubTotal for Taxable Service (USD)
						this.subTotalDetail = this.subTotalDetail.add(itemAmt);
					}else if ("0".equals(taxRate)) {
						// Sub Total for Non-Taxable Service (USD)
						this.subTotalDetail2 = this.subTotalDetail2.add(itemAmt);
					}
				}
			}
			
			BigDecimal itemExportAmt = (BigDecimal)detail.get("ITEM_EXPORT_AMT");
			
			if (itemExportAmt != null){
				
				if (!"0".equals(taxRate)) {
					// SubTotal for Taxable Service
					this.subItemExportAmt = this.subItemExportAmt.add(itemExportAmt);
				}else if ("0".equals(taxRate)) {
					// Sub Total for Non-Taxable Service 
					this.subItemExportAmt2 = this.subItemExportAmt2.add(itemExportAmt);
				}
			}
			
			BigDecimal itemExportGst = (BigDecimal)detail.get("ITEM_EXPORT_GST");
			if (itemExportGst != null){
				this.subItemExportGst = this.subItemExportGst.add(itemExportGst);
			}
			
			if ("0".equals(GSTAmount)) {
				GSTAmount = "-";
			}else {
				GSTAmount = formatter.format(new BigDecimal(GSTAmount));
			}
			
			if (!CommonUtils.isEmpty(isDisplay)) {
			    if ("0".equals(isDisplay)) {//without lump sum
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
			    if ("0".equals(isDisplay)) {//without lump sum
					sQty = "";
					sPrice = "";
					sAmt = "";
				}
				else {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
					
					if("0".equals(sQty)) {
					    sQty = "";
					}
					if("0.00".equals(sPrice)) {
					    sPrice = "";
                    }
					if("0.00".equals(sAmt)) {
					    sAmt = "";
                    }
				}
			} else {
				try {
					if(Double.parseDouble(detail.get("ITEM_QTY").toString()) == 0 || 
							Double.parseDouble(detail.get("ITEM_UPRICE").toString()) == 0 ||
							Double.parseDouble(detail.get("ITEM_AMT").toString()) == 0) {
						sQty = "";
						sPrice = "";
						sAmt = "";
					}
					else {
						sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
						sPrice = formatter.format(detail.get("ITEM_UPRICE"));
						sAmt = formatter.format(detail.get("ITEM_AMT"));
                        
                        if("0".equals(sQty)) {
                            sQty = "";
                        }
                        if("0.00".equals(sPrice)) {
                            sPrice = "";
                        }
                        if("0.00".equals(sAmt)) {
                            sAmt = "";
                        }
					}
				} catch(Exception e) {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
				}
			}
			// billing period
			period = "";
			if((i + 1 < this.detailInfo.size() 
                    && this.detailInfo.get(i + 1).get("ITEM_LEVEL").equals("0")
                    && "1".equals(itemCat)) 
                    || (i == this.detailInfo.size() - 1 && "1".equals(itemCat))) {
				if(detail.get("BILL_FROM") != null && detail.get("BILL_TO") != null) {	
					if(!CommonUtils.toString(detail.get("BILL_FROM")).equals(CommonUtils.toString(detail.get("BILL_TO")))){
						period = "Billing Period: " 
	                        + CommonUtils.toString(detail.get("BILL_FROM")) + " ~ " 
	                        + CommonUtils.toString(detail.get("BILL_TO")) + "\r\n";
					} else {
					    period = " ";
					}
				}
				if(i != this.detailInfo.size() - 1 && !period.equals(""))
					period += "\r\n";//break line for next description
				if(i == this.detailInfo.size() - 1)
					period += "\r\n";//end of detail
			}
			// calculate to decide new page or not
			List<String> rows = getRows(description, MAXIMUN_CHAR_PER_ROW);
			if(period.equals("")) nextRow = rows.size();
			else {
			    if (period.equals(" \r\n") || period.equals("\r\n")) {
			        nextRow = rows.size() + 1;
			    } else {
			        nextRow = rows.size() + 2;//description + period	
			    }
				rows.add(period);
			}
			//if((rowPerPage + this.bankInfoCount + this.feedbackCount + this.taxCount) > ROW_PER_PAGE + this.BANKINFO_ROW_PER_PAGE + this.FEEDBACK_ROW_PER_PAGE) { 
			if(rowPerPage >= ROW_PER_PAGE) {//new page
				rowPerPage = 0;
				// sub total balance line
				c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
				c.setFixedHeight(0.2f * PER_INCH);//0.2inch
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				if (this.billingType.equals("NT")){
					c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER,
							font10);
				} else {
					c = getCellColSpan(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER, 2,
							font10);
				}
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				
				this.document.add(this.tableDetail);
				pdfW.setPageEmpty(true);
				this.document.newPage();
			}
			boolean itemFlag = true;
			if ("1".equals(detail.get("DISPLAY_DISC")) && "0".equals(detail.get("ITEM_LEVEL"))){
				lumpsumFlag = true;
				itemDiscAmt1 = itemDiscAmt;
				sTax1 = GSTAmount;
			}
			//if((rowPerPage + nextRow + this.bankInfoCount + this.feedbackCount + this.taxCount) > ROW_PER_PAGE + this.BANKINFO_ROW_PER_PAGE + this.FEEDBACK_ROW_PER_PAGE) { 
			if(rowPerPage + nextRow > ROW_PER_PAGE) {
				String desc = "";
				boolean isNewPage = true;
				boolean isFistPage = true;
				while(!rows.isEmpty()) {
					desc = "";
					isNewPage = true;
					int k = 0;
					for(k = 0; k < ROW_PER_PAGE - rowPerPage; k++) {
						if(rows.isEmpty()) {
							isNewPage = false;
							break;
						}
						if(!(!period.equals("") && rows.size() == 1)){
						    
						    String rowDesc = rows.remove(0);
                            if (rowDesc != null) {
                                if ( (rowDesc.length() >=1 && rowDesc.substring(rowDesc.length() - 1, rowDesc.length()).equals("\r"))
                                        || (rowDesc.length() >=2 && rowDesc.substring(rowDesc.length() - 2, rowDesc.length()).equals("\r\n"))) {
                                    desc += rowDesc;
                                } else {
                                    desc += rowDesc + "\r";
                                }
                            }
                            
//							desc += ("\r\n" + rows.remove(0));
						}else{
							rows.remove(0);
						}
					}
					rowPerPage = k;
					//only print full information in first page
					if ("1".equals(isDisplayJobno)) {
                        this.displayDetail_Description("", "Job No.: " + jobNo, "", "", "", "", 0f);
                        rowPerPage = rowPerPage + 1;
                    }
					if(isFistPage) {
						isFistPage = false;
						if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
							sTax = GSTAmount;
						}else{
							sTax = "";
						}

						this.displayDetail_Description(sItem, desc, sQty, sPrice, sAmt, sTax);
			
						if(rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag){
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
									rowPerPage = rowPerPage + 1;
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, "-");
								}
							}
							if(period != null && !period.equals("")) {
							    // Discount (Lumpsum)
								if (lumpsumFlag){
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description("", " ", "", "", "", "", 0f);
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, "-");
									this.displayDetail_Description("", " ", "", "", "", "", 0f);
								}
							    this.displayDetail_Description("", period, "", "", "", "", 0f);
							}
						}
					} else {
				
					    this.displayDetail_Description("", desc, "", "", "", "", 0f);
					    
						
						if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
							sTax = GSTAmount;
						}else{
							sTax = "";
						}
						if(rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag){
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
									rowPerPage = rowPerPage + 1;
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, "-");
								}
							}
							if(period != null && !period.equals("")) {
							    // Discount (Lumpsum)
								if (lumpsumFlag){
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description("", " ", "", "", "", "", 0f);
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
									this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, "-");
							        this.displayDetail_Description("", " ", "", "", "", "", 0f);
								}
							    this.displayDetail_Description("", period, "", "", "", "", 0f);
							}
						}
					}
					//new page
					if(isNewPage) {
						rowPerPage = 0;
						// sub total balance line
						c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
						c.setFixedHeight(0.2f * PER_INCH);//0.2inch
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						if (this.billingType.equals("NT")){
							c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER,
									font10);
						} else {
							c = getCellColSpan(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER, 2,
									font10);
						}
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						
						this.document.add(this.tableDetail);
						pdfW.setPageEmpty(true);
						this.document.newPage();
					}
				}
			} else {
				// Fix extra Bank Line ST
				rowPerPage = rowPerPage + nextRow;
				// Fix extra Bank Line EN
				//print
				if ("1".equals(isDisplayJobno)) {
                    this.displayDetail_Description("", "Job No.: " + jobNo, "", "", "", "", 0f);
                    rowPerPage = rowPerPage + 1;
                }
				if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
					sTax = GSTAmount;
				}else{
					sTax = "";
				}
				this.displayDetail_Description(sItem, description, sQty, sPrice, sAmt, sTax);
				// Discount (Item)
				if (itemFlag){
					itemFlag = false;
					if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
						rowPerPage = rowPerPage + 1;
						//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
						this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, "-");
					}
				}
				if(period != null && !period.equals("")) {
				    // Discount (Lumpsum)
					if (lumpsumFlag){
						lumpsumFlag = false;
						rowPerPage = rowPerPage + 3;
						this.displayDetail_Description("", " ", "", "", "", "", 0f);
						//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
						this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, "-");
						this.displayDetail_Description("", " ", "", "", "", "", 0f);
					}
				    this.displayDetail_Description("", period, "", "", "", "", 0f);
				}
			}
		}
		//if((rowPerPage + this.bankInfoCount + this.feedbackCount + this.taxCount) > ROW_PER_PAGE + this.BANKINFO_ROW_PER_PAGE + this.FEEDBACK_ROW_PER_PAGE) {
		int onePageMaxRow = ROW_PER_PAGE - this.bankInfoCount - this.feedbackCount - this.globalCount - this.taxCount - this.footerInfoCount;
		if (rowPerPage > onePageMaxRow) {
			c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
			c.setFixedHeight(0.2f * PER_INCH);//0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			if (this.billingType.equals("NT")){
				c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER,
						font10);
			} else {
				c = getCellColSpan(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_CENTER, 2,
						font10);
			}
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			
			this.document.add(this.tableDetail);
			pdfW.setPageEmpty(true);
			this.document.newPage();
		}
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
        String expCurrency = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		// sub total line--->Subtotal for Taxable Service
		c = getCellColSpan("Subtotal for Taxable Service (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(CommonUtils.toString(formatter.format(this.subTotalDetail)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(CommonUtils.toString(formatter.format(this.subTotalDetail)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// sub total line--->Subtotal for Non-Taxable Service
		/*System.out.println(subItemExportAmt);
		System.out.println(subItemExportGst);
		System.out.println(subItemExportAmt2);*/
		
		
		c = getCellColSpan("Subtotal for Non-Taxable Service (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(CommonUtils.toString(formatter.format(this.subTotalDetail2)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(CommonUtils.toString(formatter.format(this.subTotalDetail2)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// GST Amount--->Service Tax 6% 
		c = getCellColSpan("Service Tax "+ serviceTaxPercentage +"% (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(CommonUtils.toString(formatter.format(subGSTAmount)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(CommonUtils.toString(formatter.format(subGSTAmount)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// grant total line 
		c = getCellColSpan("Grand Total (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.LIGHT_GRAY, 
				Element.ALIGN_CENTER, 4, font10b);
		c.setFixedHeight(0.25f * PER_INCH);//0.25inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(formatter.format(this.headerInfo.get("BILL_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
					font10b);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(formatter.format(this.headerInfo.get("BILL_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
					font10b);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setBorderColorRight(BaseColor.LIGHT_GRAY);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// grant total fixed forex
		// if C.IS_DISPLAY = 1, display, else do not display.
		//String cIsDisplay = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY"));
		String isDisplayExpAmt = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY_EXP_AMT"));
		if ("1".equals(isDisplayExpAmt)) {
		    String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BTH.getSetValueOfSystemBase", null, String.class));
		    String grandWording = "";
		    if (billCurrency.equals(defCurrency)) {
		        grandWording = "Grand Total " + expCurrency + " ";
	            grandWording = grandWording + "(1 " + expCurrency + " = ";
	            String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
	            if (!CommonUtils.isEmpty(fixedForex)) {
	                grandWording += "Fixed Forex ";
	            }
	            grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + billCurrency + ")";
		    } else {
		        grandWording = "Grand Total " + expCurrency + " ";
                grandWording = grandWording + "(1 " + billCurrency + " = ";
                String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
                if (!CommonUtils.isEmpty(fixedForex)) {
                    grandWording += "Fixed Forex ";
                }
                grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + expCurrency + ")";
		    }
		    
		    c = getCellColSpan(grandWording, BaseColor.LIGHT_GRAY, 
                    Element.ALIGN_CENTER, 4, font10b);
            c.setFixedHeight(0.25f * PER_INCH);//0.25inch
            c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            this.tableDetail.addCell(c);
            if (this.billingType.equals("NT")){
            	c = getCell(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
                        font10b);
    			this.tableDetail.addCell(c);
    		} else {
    			c = getCell(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
                        font10b);
                c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
        		this.tableDetail.addCell(c);
        		c = getCell("", BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,font10);
        		c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        		c.setBorderColorRight(BaseColor.LIGHT_GRAY);
        		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        		this.tableDetail.addCell(c);
    		}
		}
		this.document.add(this.tableDetail);
	
	} 
	
	/*#594: [B1] Customer Maintenance CT 20190604*/
	private void displayDetail_Format4() throws DocumentException{

		PdfPCell c = null;
		this.displayDetail_Header_Format4(false);
		// in fact no of page
		this.subTotalDetail = new BigDecimal("0");
		this.subTotalDetail2 = new BigDecimal("0");
		this.carryforward = new BigDecimal("0");
		// loop for each detail and display
		int index = 0, rowPerPage = 0, nextRow = 0;
		String sItem, description, sQty, sPrice, sAmt, sTax, sTaxExempted;
		String period = null;
		boolean lumpsumFlag = false;
		String itemDiscAmt1 = "", sTax1 = "";
		for(int i = 0; i < this.detailInfo.size(); i++){
			Map<String,Object> detail = this.detailInfo.get(i);
			String isDisplay = CommonUtils.toString(detail.get("IS_DISPLAY"));
			String itemCat = CommonUtils.toString(detail.get("ITEM_CAT"));
			String jobNo = CommonUtils.toString(detail.get("JOB_NO")).trim();
			String isDisplayJobno = CommonUtils.toString(detail.get("IS_DISPLAY_JOBNO")).trim();
			String taxCode = CommonUtils.toString(detail.get("TAX_CODE")).trim();
			String itemDiscAmt = formatter.format(detail.get("ITEM_DISC_AMT"));
			String GSTAmount = CommonUtils.toString(detail.get("ITEM_GST")).trim();
			String taxRate = CommonUtils.toString(detail.get("TAX_RATE")).trim();
			/*#594: [B1] Customer Maintenance CT 20190604*/
			String taxExemptedAmt = CommonUtils.toString(detail.get("TAX_EXEMPTED_AMT")).trim();
			List <String>taxCodeList = new ArrayList<String>(); 
			if(taxCode != null || !"".equals(taxCode)) {
				taxCodeList = Arrays.asList(taxCode.split(","));
			}
			
			// GST Amount --> Service Tax
			if("1".equals(detail.get("ITEM_LEVEL"))){
				this.subGSTAmount = this.subGSTAmount.add(new BigDecimal(GSTAmount));
				if(CollectionUtils.containsAny(taxExemptedList,taxCodeList)) {
					this.subTaxExemptedAmount = this.subTaxExemptedAmount.add(new BigDecimal(taxExemptedAmt));
				}
			}
			/*#594: [B1] Customer Maintenance CT 20190604*/
			if("1".equals(detail.get("ITEM_LEVEL"))){
				BigDecimal itemAmt = (BigDecimal)detail.get("ITEM_SUBTOTAL");
				if (itemAmt != null){
					if (!"0".equals(taxRate)) {
						// SubTotal for Taxable Service (USD)
						this.subTotalDetail = this.subTotalDetail.add(itemAmt);
					}else if ("0".equals(taxRate)) {
						// Sub Total for Non-Taxable Service (USD)
						this.subTotalDetail2 = this.subTotalDetail2.add(itemAmt);
					}
				}
			}
			
			BigDecimal itemExportAmt = (BigDecimal)detail.get("ITEM_EXPORT_AMT");
			
			if (itemExportAmt != null){
				
				if (!"0".equals(taxRate)) {
					// SubTotal for Taxable Service
					this.subItemExportAmt = this.subItemExportAmt.add(itemExportAmt);
				}else if ("0".equals(taxRate)) {
					// Sub Total for Non-Taxable Service 
					this.subItemExportAmt2 = this.subItemExportAmt2.add(itemExportAmt);
				}
			}
			
			BigDecimal itemExportGst = (BigDecimal)detail.get("ITEM_EXPORT_GST");
			if (itemExportGst != null){
				this.subItemExportGst = this.subItemExportGst.add(itemExportGst);
			}
			
			if ("0".equals(GSTAmount)) {
				GSTAmount = "-";
			}else {
				GSTAmount = formatter.format(new BigDecimal(GSTAmount));
			}
			
			/*#594: [B1] Customer Maintenance CT 20190604*/
			if ("0".equals(taxExemptedAmt)) {
				taxExemptedAmt = "";
			}else {
				taxExemptedAmt = formatter.format(new BigDecimal(taxExemptedAmt));
			}
			/*#594: [B1] Customer Maintenance CT 20190604*/
			
			if (!CommonUtils.isEmpty(isDisplay)) {
			    if ("0".equals(isDisplay)) {//without lump sum
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
			    if ("0".equals(isDisplay)) {//without lump sum
					sQty = "";
					sPrice = "";
					sAmt = "";
				}
				else {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
					
					if("0".equals(sQty)) {
					    sQty = "";
					}
					if("0.00".equals(sPrice)) {
					    sPrice = "";
                    }
					if("0.00".equals(sAmt)) {
					    sAmt = "";
                    }
				}
			} else {
				try {
					if(Double.parseDouble(detail.get("ITEM_QTY").toString()) == 0 || 
							Double.parseDouble(detail.get("ITEM_UPRICE").toString()) == 0 ||
							Double.parseDouble(detail.get("ITEM_AMT").toString()) == 0) {
						sQty = "";
						sPrice = "";
						sAmt = "";
					}
					else {
						sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
						sPrice = formatter.format(detail.get("ITEM_UPRICE"));
						sAmt = formatter.format(detail.get("ITEM_AMT"));
                        
                        if("0".equals(sQty)) {
                            sQty = "";
                        }
                        if("0.00".equals(sPrice)) {
                            sPrice = "";
                        }
                        if("0.00".equals(sAmt)) {
                            sAmt = "";
                        }
					}
				} catch(Exception e) {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
				}
			}
			// billing period
			period = "";
			if((i + 1 < this.detailInfo.size() 
                    && this.detailInfo.get(i + 1).get("ITEM_LEVEL").equals("0")
                    && "1".equals(itemCat)) 
                    || (i == this.detailInfo.size() - 1 && "1".equals(itemCat))) {
				if(detail.get("BILL_FROM") != null && detail.get("BILL_TO") != null) {	
					if(!CommonUtils.toString(detail.get("BILL_FROM")).equals(CommonUtils.toString(detail.get("BILL_TO")))){
						period = "Billing Period: " 
	                        + CommonUtils.toString(detail.get("BILL_FROM")) + " ~ " 
	                        + CommonUtils.toString(detail.get("BILL_TO")) + "\r\n";
					} else {
					    period = " ";
					}
				}
				if(i != this.detailInfo.size() - 1 && !period.equals(""))
					period += "\r\n";//break line for next description
				if(i == this.detailInfo.size() - 1)
					period += "\r\n";//end of detail
			}
			// calculate to decide new page or not
			List<String> rows = getRows(description, MAXIMUN_CHAR_PER_ROW);
			if(period.equals("")) nextRow = rows.size();
			else {
			    if (period.equals(" \r\n") || period.equals("\r\n")) {
			        nextRow = rows.size() + 1;
			    } else {
			        nextRow = rows.size() + 2;//description + period	
			    }
				rows.add(period);
			}
			//if((rowPerPage + this.bankInfoCount + this.feedbackCount + this.taxCount) > ROW_PER_PAGE + this.BANKINFO_ROW_PER_PAGE + this.FEEDBACK_ROW_PER_PAGE) { 
			if(rowPerPage >= ROW_PER_PAGE) {//new page
				rowPerPage = 0;
				// sub total balance line
				c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
				c.setFixedHeight(0.2f * PER_INCH);//0.2inch
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				if (this.billingType.equals("NT")){
					c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_LEFT,
							font10);
					c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
					this.tableDetail.addCell(c);
				} else {
					c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_RIGHT, font10);
					c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
					c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
					this.tableDetail.addCell(c);
					
					c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
					c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
					c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
					this.tableDetail.addCell(c);
					
					c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
					c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
					c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
					this.tableDetail.addCell(c);
				}
				
				
				this.document.add(this.tableDetail);
				pdfW.setPageEmpty(true);
				this.document.newPage();
			}
			boolean itemFlag = true;
			if ("1".equals(detail.get("DISPLAY_DISC")) && "0".equals(detail.get("ITEM_LEVEL"))){
				lumpsumFlag = true;
				itemDiscAmt1 = itemDiscAmt;
				sTax1 = GSTAmount;
			}
			//if((rowPerPage + nextRow + this.bankInfoCount + this.feedbackCount + this.taxCount) > ROW_PER_PAGE + this.BANKINFO_ROW_PER_PAGE + this.FEEDBACK_ROW_PER_PAGE) { 
			if(rowPerPage + nextRow > ROW_PER_PAGE) {
				String desc = "";
				boolean isNewPage = true;
				boolean isFistPage = true;
				while(!rows.isEmpty()) {
					desc = "";
					isNewPage = true;
					int k = 0;
					for(k = 0; k < ROW_PER_PAGE - rowPerPage; k++) {
						if(rows.isEmpty()) {
							isNewPage = false;
							break;
						}
						if(!(!period.equals("") && rows.size() == 1)){
						    
						    String rowDesc = rows.remove(0);
                            if (rowDesc != null) {
                                if ( (rowDesc.length() >=1 && rowDesc.substring(rowDesc.length() - 1, rowDesc.length()).equals("\r"))
                                        || (rowDesc.length() >=2 && rowDesc.substring(rowDesc.length() - 2, rowDesc.length()).equals("\r\n"))) {
                                    desc += rowDesc;
                                } else {
                                    desc += rowDesc + "\r";
                                }
                            }
                            
//							desc += ("\r\n" + rows.remove(0));
						}else{
							rows.remove(0);
						}
					}
					rowPerPage = k;
					//only print full information in first page
					if ("1".equals(isDisplayJobno)) {
                        this.displayDetail_Description_Format4("", "Job No.: " + jobNo, "", "", "", "", "", 0f);
                        rowPerPage = rowPerPage + 1;
                    }
					if(isFistPage) {
						isFistPage = false;
						if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
							sTax = GSTAmount;
							if(CollectionUtils.containsAny(taxExemptedList,taxCodeList)) {
								sTaxExempted = taxExemptedAmt;
							}else{
								sTaxExempted = "";
							}
						}else{
							sTax = "";
							sTaxExempted = "";
						}
						//#594: [B1] Customer Maintenance CT 20190604
						this.displayDetail_Description_Format4(sItem, desc, sQty, sPrice, sAmt, sTax,sTaxExempted);
						//this.displayDetail_Description(sItem, desc, sQty, sPrice, sAmt, sTax);
						//#594: [B1] Customer Maintenance CT 20190604
						
						if(rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag){
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
									rowPerPage = rowPerPage + 1;
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
									this.displayDetail_Description_Format4("", "Discount", "", "", itemDiscAmt, "-", "");
								}
							}
							if(period != null && !period.equals("")) {
							    // Discount (Lumpsum)
								if (lumpsumFlag){
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description_Format4("", " ", "", "", "", "", "", 0f);
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
									this.displayDetail_Description_Format4("", "Discount", "", "", itemDiscAmt1, "-", "");
									this.displayDetail_Description_Format4("", " ", "", "", "", "", "", 0f);
								}
							    this.displayDetail_Description_Format4("", period, "", "", "", "", "", 0f);
							}
						}
					} else {
				
					    this.displayDetail_Description_Format4("", desc, "", "", "", "", "", 0f);
					    
						
						if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
							sTax = GSTAmount;
							if(CollectionUtils.containsAny(taxExemptedList,taxCodeList)) {
								sTaxExempted = taxExemptedAmt;
							}else{
								sTaxExempted = "";
							}
						}else{
							sTax = "";
							sTaxExempted = "";
						}
						if(rows.isEmpty()) {
							// Discount (Item)
							if (itemFlag){
								itemFlag = false;
								if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
									rowPerPage = rowPerPage + 1;
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
									this.displayDetail_Description_Format4("", "Discount", "", "", itemDiscAmt, "-", "");
								}
							}
							if(period != null && !period.equals("")) {
							    // Discount (Lumpsum)
								if (lumpsumFlag){
									lumpsumFlag = false;
									rowPerPage = rowPerPage + 3;
									this.displayDetail_Description_Format4("", " ", "", "", "", "", "", 0f);
									//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
									this.displayDetail_Description_Format4("", "Discount", "", "", itemDiscAmt1, "-", "");
							        this.displayDetail_Description_Format4("", " ", "", "", "", "", "", 0f);
								}
							    this.displayDetail_Description_Format4("", period, "", "", "", "", "", 0f);
							}
						}
					}
					//new page
					if(isNewPage) {
						rowPerPage = 0;
						// sub total balance line
						c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
						c.setFixedHeight(0.2f * PER_INCH);//0.2inch
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						if (this.billingType.equals("NT")){
							c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_LEFT,
									font10);
							c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
							this.tableDetail.addCell(c);
						} else {
							c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_RIGHT, font10);
							c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
							c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
							this.tableDetail.addCell(c);
							
							c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
							c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
							c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
							this.tableDetail.addCell(c);
							
							c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
							c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
							c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
							this.tableDetail.addCell(c);
						}
						
						this.document.add(this.tableDetail);
						pdfW.setPageEmpty(true);
						this.document.newPage();
					}
				}
			} else {
				// Fix extra Bank Line ST
				rowPerPage = rowPerPage + nextRow;
				// Fix extra Bank Line EN
				//print
				if ("1".equals(isDisplayJobno)) {
                    this.displayDetail_Description_Format4("", "Job No.: " + jobNo, "", "", "", "", "", 0f);
                    rowPerPage = rowPerPage + 1;
                }
				if ("1".equals(isDisplay) && sAmt != null && !"".equals(sAmt) && BigDecimal.ZERO.compareTo(new BigDecimal(sAmt.replaceAll(",", ""))) != 0){
					sTax = GSTAmount;
					if(CollectionUtils.containsAny(taxExemptedList,taxCodeList)) {
						sTaxExempted = taxExemptedAmt;
					}else{
						sTaxExempted = "";
					}
				}else{
					sTax = "";
					sTaxExempted = "";
				}
				this.displayDetail_Description_Format4(sItem, description, sQty, sPrice, sAmt, sTax,sTaxExempted);
				// Discount (Item)
				if (itemFlag){
					itemFlag = false;
					if ("1".equals(detail.get("DISPLAY_DISC")) && "1".equals(detail.get("ITEM_LEVEL"))){
						rowPerPage = rowPerPage + 1;
						//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt, sTax);
						this.displayDetail_Description_Format4("", "Discount", "", "", itemDiscAmt, "-", "");
					}
				}
				if(period != null && !period.equals("")) {
				    // Discount (Lumpsum)
					if (lumpsumFlag){
						lumpsumFlag = false;
						rowPerPage = rowPerPage + 3;
						this.displayDetail_Description_Format4("", " ", "", "", "", "", "", 0f);
						//this.displayDetail_Description("", "Discount", "", "", itemDiscAmt1, sTax1);
						this.displayDetail_Description_Format4("", "Discount", "", "", itemDiscAmt1, "-", "");
						this.displayDetail_Description_Format4("", " ", "", "", "", "", "", 0f);
					}
				    this.displayDetail_Description_Format4("", period, "", "", "", "", "", 0f);
				}
			}
		}
		//if((rowPerPage + this.bankInfoCount + this.feedbackCount + this.taxCount) > ROW_PER_PAGE + this.BANKINFO_ROW_PER_PAGE + this.FEEDBACK_ROW_PER_PAGE) {
		int onePageMaxRow = ROW_PER_PAGE - this.bankInfoCount - this.feedbackCount - this.globalCount - this.taxCount - this.footerInfoCount;
		if (rowPerPage > onePageMaxRow) {
			c = getCellColSpan("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
			c.setFixedHeight(0.2f * PER_INCH);//0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			if (this.billingType.equals("NT")){
				c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_LEFT,
						font10);
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
			} else {
				c = getCell(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_RIGHT, font10);
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
				this.tableDetail.addCell(c);
				
				c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
				this.tableDetail.addCell(c);
				
				c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
				this.tableDetail.addCell(c);
			}
			
			this.document.add(this.tableDetail);
			pdfW.setPageEmpty(true);
			this.document.newPage();
		}
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
        String expCurrency = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
        String sSubTotalDetail = subTotalDetail.compareTo(BigDecimal.ZERO) != 0 ? CommonUtils.toString(formatter.format(this.subTotalDetail)) : "-";
        String sSubGSTAmount = subGSTAmount.compareTo(BigDecimal.ZERO) != 0 ? CommonUtils.toString(formatter.format(this.subGSTAmount)) : "-";
        String sSubTaxExemptedAmount = subTaxExemptedAmount.compareTo(BigDecimal.ZERO) != 0 ? CommonUtils.toString(formatter.format(this.subTaxExemptedAmount)) : "-";
        String sSubTotalDetail2 = subTotalDetail2.compareTo(BigDecimal.ZERO) != 0 ? CommonUtils.toString(formatter.format(this.subTotalDetail2)) : "-";
        // sub total line--->Subtotal for Taxable Service
		c = getCellColSpan("Subtotal for Taxable Service (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(sSubTotalDetail, BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(sSubTotalDetail, BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			
			c = getCell(sSubGSTAmount, BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			
			c = getCellColSpan(sSubTaxExemptedAmount, BaseColor.WHITE, Element.ALIGN_RIGHT, 3,
					font10);
			c.setFixedHeight(0.2f * PER_INCH);//0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// sub total line--->Subtotal for Non-Taxable Service
		/*System.out.println(subItemExportAmt);
		System.out.println(subItemExportGst);
		System.out.println(subItemExportAmt2);*/
		
		
		c = getCellColSpan("Subtotal for Non-Taxable Service (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(sSubTotalDetail2, BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(sSubTotalDetail2, BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("-", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.LEFT);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// GST Amount--->Service Tax 6% 
		/*c = getCellColSpan("Service Tax "+ serviceTaxPercentage +"% (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if (this.billingType.equals("NT")){
			c = getCell(CommonUtils.toString(formatter.format(subGSTAmount)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			this.tableDetail.addCell(c);
		} else {
			c = getCell(CommonUtils.toString(formatter.format(subGSTAmount)), BaseColor.WHITE, Element.ALIGN_RIGHT,
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.LEFT);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}*/
		// grant total line 
		c = getCellColSpan("Grand Total (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.LIGHT_GRAY, 
				Element.ALIGN_CENTER, 4, font10b);
		c.setFixedHeight(0.25f * PER_INCH);//0.25inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		/*c = getCell("", BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,font10);
		c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
		c.setBorderColorRight(BaseColor.LIGHT_GRAY);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);*/
		
		if (this.billingType.equals("NT")){
			c = getCell(formatter.format(this.headerInfo.get("BILL_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,
					font10b);
			this.tableDetail.addCell(c);
		} else {
			c = getCellColSpan(formatter.format(this.headerInfo.get("BILL_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT, 2, font10b);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
			this.tableDetail.addCell(c);
			
			c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
			c.setBorder(Rectangle.LEFT);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
		}
		// grant total fixed forex
		// if C.IS_DISPLAY = 1, display, else do not display.
		//String cIsDisplay = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY"));
		String isDisplayExpAmt = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY_EXP_AMT"));
		if ("1".equals(isDisplayExpAmt)) {
		    String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BTH.getSetValueOfSystemBase", null, String.class));
		    String grandWording = "";
		    if (billCurrency.equals(defCurrency)) {
		        grandWording = "Grand Total " + expCurrency + " ";
	            grandWording = grandWording + "(1 " + expCurrency + " = ";
	            String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
	            if (!CommonUtils.isEmpty(fixedForex)) {
	                grandWording += "Fixed Forex ";
	            }
	            grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + billCurrency + ")";
		    } else {
		        grandWording = "Grand Total " + expCurrency + " ";
                grandWording = grandWording + "(1 " + billCurrency + " = ";
                String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
                if (!CommonUtils.isEmpty(fixedForex)) {
                    grandWording += "Fixed Forex ";
                }
                grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + expCurrency + ")";
		    }
		    
		    c = getCellColSpan(grandWording, BaseColor.LIGHT_GRAY, 
                    Element.ALIGN_CENTER, 4, font10b);
            c.setFixedHeight(0.25f * PER_INCH);//0.25inch
            c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            this.tableDetail.addCell(c);
            
            /*c = getCell("", BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT,font10);
            c.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
    		c.setBorderColorRight(BaseColor.LIGHT_GRAY);
    		c.setVerticalAlignment(PdfPCell.ALIGN_RIGHT);
        	this.tableDetail.addCell(c);*/
    		
            if (this.billingType.equals("NT")){
            	c = getCellColSpan(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT, 2, font10b);
    			this.tableDetail.addCell(c);
    		} else {
    			c = getCellColSpan(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT, 2, font10b);
                c.setVerticalAlignment(PdfPCell.ALIGN_RIGHT);
                c.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        		this.tableDetail.addCell(c);
        		
        		c = getCell("", BaseColor.WHITE, Element.ALIGN_RIGHT,font10);
    			c.setBorder(Rectangle.LEFT);
    			c.setVerticalAlignment(PdfPCell.ALIGN_RIGHT);
    			this.tableDetail.addCell(c);
    		}
		}
		this.document.add(this.tableDetail);
	
	}
	
	private void displayDetail_Header_Format4(boolean isAdded) throws DocumentException {
		PdfPCell c = null;
		//0.5inch 4.25inch 0.875inch 0.875inch 1inch
		if (this.billingType.equals("NT")){
			this.tableDetail = new PdfPTable(5);
			float[] colWidths = {0.5f * PER_INCH, 4.25f * PER_INCH, 0.875f * PER_INCH, 0.875f * PER_INCH, 1f * PER_INCH};
			this.tableDetail.setWidths(colWidths);
			this.tableDetail.setSpacingBefore(6);
		} else {
			
			if ("2".equals(oldFlag)) {
				/*#594: [B1] Customer Maintenance CT 20190604*/
				this.tableDetail = new PdfPTable(7);
				float[] colWidths = {0.5f * PER_INCH, 4.25f * PER_INCH, 0.7f * PER_INCH, 1f * PER_INCH, 1f * PER_INCH, 0.8f * PER_INCH, 0.8f * PER_INCH};
				this.tableDetail.setWidths(colWidths);
				/*#594: [B1] Customer Maintenance CT 20190604*/
			}else {
				this.tableDetail = new PdfPTable(6);
				float[] colWidths = {0.5f * PER_INCH, 4.25f * PER_INCH, 0.875f * PER_INCH, 0.875f * PER_INCH, 1f * PER_INCH, 0.5f * PER_INCH};
				this.tableDetail.setWidths(colWidths);
			}
			this.tableDetail.setSpacingBefore(7);
		}
		this.tableDetail.setWidthPercentage(100);
		c = new PdfPCell(new Phrase("Item", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//c.setFixedHeight(0.4f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("Description", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//c.setFixedHeight(0.4f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Quantity", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//c.setFixedHeight(0.4f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Unit Price", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//c.setFixedHeight(0.4f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Amount ("+this.headerInfo.get("BILL_CURRENCY")+")", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//c.setFixedHeight(0.4f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		/*#594: [B1] Customer Maintenance CT 20190604*/
		if (!this.billingType.equals("NT")){
			if ("2".equals(oldFlag)) {
				c = new PdfPCell(new Phrase("Service Tax", font10b));
			}else {
				c = new PdfPCell(new Phrase("Tax Code", font10b));
			}
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			c.setBackgroundColor(BaseColor.LIGHT_GRAY);
			//c.setFixedHeight(0.4f * PER_INCH);//0.35inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			
			if ("2".equals(oldFlag)) {
				c = new PdfPCell(new Phrase("*Service Tax\nExempted", font10b));
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				c.setBackgroundColor(BaseColor.LIGHT_GRAY);
				//c.setFixedHeight(0.4f * PER_INCH);//0.35inch
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
			}
		}
		/*#594: [B1] Customer Maintenance CT 20190604*/
		
		if(isAdded) {//this case for more 1 page (from 2 page)
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
			
			c = new PdfPCell(new Phrase(CommonUtils.toString(formatter.format(this.carryforward)), font10b));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setVerticalAlignment(PdfPCell.ALIGN_TOP);
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);
			
			if (!this.billingType.equals("NT")){
				c = new PdfPCell();
				c.setBorder(PdfPCell.RIGHT);
				this.tableDetail.addCell(c);
				
				c = new PdfPCell();
				c.setBorder(PdfPCell.RIGHT);
				this.tableDetail.addCell(c);
			}
		}
	}
	
	private void displayDetail_Description_Format4(String item, String description, String quantity, String unitPrice,
			String amount, String taxcode, String taxExemptedAmt, float paddingTop) throws DocumentException {
		displayDetail_DescriptionHelp_Format4(item, description, quantity, unitPrice, amount, taxcode, taxExemptedAmt, 0f, paddingTop);
	}
	
	private void displayDetail_Description_Format4(String item, String description, String quantity, String unitPrice,
			String amount, String taxcode, String taxExemptedAmt) throws DocumentException {
		displayDetail_DescriptionHelp_Format4(item, description, quantity, unitPrice, amount, taxcode, taxExemptedAmt, 3.0f, 2.0f);// 1.5 line
	}
	
	private void displayDetail_DescriptionHelp_Format4(String item, String description, String quantity, String unitPrice,
			String amount, String taxcode, String taxExemptedAmt, float paragraphSpace, float paddingTop) throws DocumentException {
		PdfPCell c = null;		
		c = new PdfPCell(new Phrase(item, font10));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		
		Font font =  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		if("Discount".equals(description)){
			font =  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC | Font.BOLD);
		}
		c = new PdfPCell(new Phrase(description, font));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase(quantity, font10));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
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
		//Fix c/f amount CT 20170207 START
		try {
			if ("".equals(amount)) {
				this.carryforward = this.carryforward.add(new BigDecimal(0));
			}else {
				this.carryforward = this.carryforward.add(new BigDecimal(formatter.parse(amount).toString()));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Fix c/f amount CT 20170207 END
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		
		if (!this.billingType.equals("NT")){
			c = new PdfPCell(new Phrase(taxcode, font10));
			if ("2".equals(oldFlag)) {
				c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			}else {
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			c.setBorder(PdfPCell.RIGHT);
			c.setExtraParagraphSpace(paragraphSpace);
			c.setPaddingTop(paddingTop);
			this.tableDetail.addCell(c);
			
			if ("2".equals(oldFlag)) {
				c = new PdfPCell(new Phrase(taxExemptedAmt, font10));
				c.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c.setBorder(PdfPCell.RIGHT);
				c.setExtraParagraphSpace(paragraphSpace);
				c.setPaddingTop(paddingTop);
				this.tableDetail.addCell(c);
			}
		}
	}
	/*#594: [B1] Customer Maintenance CT 20190604*/
	
	private void displayDetail_Description(String item, String description, String quantity, String unitPrice,
			String amount, String taxcode, float paddingTop) throws DocumentException {
		displayDetail_DescriptionHelp(item, description, quantity, unitPrice, amount, taxcode, 0f, paddingTop);
	}
	
	private void displayDetail_Description(String item, String description, String quantity, String unitPrice,
			String amount, String taxcode) throws DocumentException {
		displayDetail_DescriptionHelp(item, description, quantity, unitPrice, amount, taxcode, 3.0f, 2.0f);// 1.5 line
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
		
		Font font =  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		if("Discount".equals(description)){
			font =  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC | Font.BOLD);
		}
		c = new PdfPCell(new Phrase(description, font));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase(quantity, font10));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
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
		//Fix c/f amount CT 20170207 START
		try {
			if ("".equals(amount)) {
				this.carryforward = this.carryforward.add(new BigDecimal(0));
			}else {
				this.carryforward = this.carryforward.add(new BigDecimal(formatter.parse(amount).toString()));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Fix c/f amount CT 20170207 END
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		
		if (!this.billingType.equals("NT")){
			c = new PdfPCell(new Phrase(taxcode, font10));
			if ("2".equals(oldFlag)) {
				c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			}else {
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			c.setBorder(PdfPCell.RIGHT);
			c.setExtraParagraphSpace(paragraphSpace);
			c.setPaddingTop(paddingTop);
			this.tableDetail.addCell(c);
		}
	}
	
	private void displayFooter() throws DocumentException {
		Paragraph pFooter = new Paragraph();
		pFooter.setAlignment(Element.ALIGN_LEFT);
		Phrase phFooter1 = null;
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
		String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		if (billCurrency.equals(exportCur)){
		    phFooter1 = new Phrase("("+this.getCurWording(billCurrency)+" : " 
	                + EnglishNumberToWords.convert(CommonUtils.toString(this.headerInfo.get("BILL_AMOUNT"))) 
	                + " only)\n\n", 
	                font10);
		} else {
			String isDisAmt = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY_EXP_AMT"));
			if("1".equals(isDisAmt)){
				phFooter1 = new Phrase("("+this.getCurWording(exportCur)+" : " 
		                + EnglishNumberToWords.convert(CommonUtils.toString(this.headerInfo.get("EXPORT_AMOUNT"))) 
		                + " only)\n\n", 
		                font10);
			}
			else{
				phFooter1 = new Phrase("("+this.getCurWording(billCurrency)+" : " 
		                + EnglishNumberToWords.convert(CommonUtils.toString(this.headerInfo.get("BILL_AMOUNT"))) 
		                + " only)\n\n", 
		                font10);
			}
		}
		
		
		pFooter.add(phFooter1);
		this.document.add(pFooter);
		
		// Scope of GST
		if(!this.billingType.equals("NT")){
			if ("1".equals(oldFlag)) {
				this.getScopeInfo();
			}else if ("9".equals(oldFlag)) {
				this.getScopeInfo_Format3();
			}else if("2".equals(oldFlag)) {
				this.getScopeInfo_Format4();
			}
		} else {
			if(!defaultCurrency.equals(billCurrency)){
				if ("1".equals(oldFlag)) {
					this.getScopeInfo1();
				}else if ("9".equals(oldFlag)) {
					this.getScopeInfo1_Format3();
				}else if("2".equals(oldFlag)) {
					this.getScopeInfo1_Format4();
				}
			}else {
				if("2".equals(oldFlag)) {
					this.getScopeInfo2_Format4();
				}
			}
		}
        
		/**
		 * use nested table of table to ensure footer fix on one page.
		 */
		List<Map<String, Object>> fInfosList = this.queryDAO.executeForMapList("B_BTH.getFooterInfo", null);
		if(!this.billingType.equals("CN")) {
			if (fInfosList != null && 0 < fInfosList.size()) {
				PdfPTable footerTable = new PdfPTable(1);
				footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				footerTable.setWidthPercentage(100);
				//footerTable.setSpacingBefore(30);
	
				/*for (Map<String, Object> footInfo : fInfosList) {*/
				for(int i = 0 ; i < footerLineNo ; i++) {
					Map<String, Object> footInfo = fInfosList.get(i);
					String footerStr = CommonUtils.toString(footInfo.get("SET_VALUE")).trim();
					if (!CommonUtils.isEmpty(footerStr)) {
						PdfPCell cell = new PdfPCell(new Phrase(footerStr, font09));
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setBorder(PdfPCell.NO_BORDER);
						footerTable.addCell(cell);
					/*}*/
				}
				}
				this.document.add(footerTable);
			}
			
			//set Bankinfo
	        getBankInfo();
		}
		
		/*StringBuffer footerStr = new StringBuffer("");
		if(!this.billingType.equals("CN")) {
			footerStr += "\n";
			footerStr += this.getFooterInfo();
		    footerStr.append("\n");
            footerStr.append("Please quote this Invoice / Debit Note when making payment to "+'"'+"NTT MSC SDN BHD"+'"'+"\n\n");
            footerStr.append("1     For any enquiries, please contact our Customer Service at 1-800-881736 / 03-8318-1121.\n");
            footerStr.append("2     Interest at 1.5% per month will be imposed on all overdue invoices.\n");
            footerStr.append("3     Please direct the payment into our bank account:\n\n");
		}
		//footerStr += "\n\n\n";//3 line
		
		PdfPTable footerTable1 = new PdfPTable(1);
        footerTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        footerTable1.setWidthPercentage(100);
        
        PdfPCell cell1 = new PdfPCell(new Phrase(footerStr.toString(), font09));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(PdfPCell.NO_BORDER);
        footerTable1.addCell(cell1);
        
        this.document.add(footerTable1);*/
        
		PdfPTable footerTable = new PdfPTable(1);
		footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		footerTable.setWidthPercentage(100);
		
		
		PdfPCell cell = new PdfPCell(new Phrase("\r\n", font09));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        footerTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase("This document is computer generated and no signature is required.", font09));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setFixedHeight(20);
        footerTable.addCell(cell);
        
        //globalStr = StringEscapeUtils.unescapeJava(globalStr);
        cell = new PdfPCell(new Paragraph (new Phrase(StringEscapeUtils.unescapeJava(globalStr), font09)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        footerTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase("\r\n", font09));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        footerTable.addCell(cell);
        
        cell = new PdfPCell(new Paragraph (new Phrase(StringEscapeUtils.unescapeJava(feedbackStr), font09)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        footerTable.addCell(cell);

        this.document.add(footerTable);
	}
	
	private void getScopeInfo() throws DocumentException {
		int len = this.detailScopeInfo.size();
		if(len > 0){
			if(len == 1){
				this.detailScopeInfo.add(null);
				this.detailScopeInfo.add(null);
			}
			if(len == 2){
				this.detailScopeInfo.add(null);
			}
			for (int i = 0; i < this.detailScopeInfo.size(); i++) {
				Map<String, Object> scopeInfo = this.detailScopeInfo.get(i);
				String strScope = "";
				if (scopeInfo != null){
					strScope = scopeInfo.get("TAX_CODE") + " - " + scopeInfo.get("DESCRIPTION1") +" (" + scopeInfo.get("TAX_RATE") + "%)";
				}
				boolean flag = false;
		        if(!this.defaultCurrency.equals(this.billCurrency)){
					flag = true;
				}
		        BigDecimal curRate = (BigDecimal)this.headerInfo.get("CUR_RATE");
//		        if("JPY".equals(this.billCurrency)){
//		        	curRate = curRate.multiply(new BigDecimal(100));
//				}
		        String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		        String amountEquivalent = "Amount equivalent in " + exportCur;
		        String exhangeRate = "Exchange Rate: "+CommonUtils.toString(curRate);
				if(i == 0){
					if(flag){
						this.setScopeInfoTable("Scope of GST:", strScope, amountEquivalent, "Sub Total", CommonUtils.toString(formatter.format(subItemExportAmt)), 1);
					}else{
						this.setScopeInfoTable("Scope of GST:", strScope, "", "", "", 0);
					}
				}else if(i == 1){
					if(flag){
						this.setScopeInfoTable("", strScope, exhangeRate, "GST Amount", CommonUtils.toString(formatter.format(subItemExportGst)), 2);
					}else{
						this.setScopeInfoTable("", strScope, "", "", "", 0);
					}
				}else if(i == 2){
					if(flag){
						this.setScopeInfoTable("", strScope, "", "Grand Total", CommonUtils.toString(formatter.format(new BigDecimal(formatter.format(subItemExportAmt).replaceAll(",", "")).add(new BigDecimal(formatter.format(subItemExportGst).replaceAll(",", ""))))), 3);
					}else{
						this.setScopeInfoTable("", strScope, "", "", "", 0);
					}
				} else {
					this.setScopeInfoTable("", strScope, "", "", "", 0);
				}
		        
			}
		}
	}
	
	private void getScopeInfo_Format3() throws DocumentException {
		int len = this.detailScopeInfo.size();
		if(len > 0){
			if(len == 1){
				this.detailScopeInfo.add(null);
				this.detailScopeInfo.add(null);
				this.detailScopeInfo.add(null);
			}
			if(len == 2){
				this.detailScopeInfo.add(null);
				this.detailScopeInfo.add(null);
			}
			for (int i = 0; i < this.detailScopeInfo.size(); i++) {
				boolean flag = false;
		        if(!this.defaultCurrency.equals(this.billCurrency)){
					flag = true;
				}
		        BigDecimal curRate = (BigDecimal)this.headerInfo.get("CUR_RATE");
		        String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		        String amountEquivalent = "Amount equivalent in " + exportCur;
		        String exhangeRate = "Exchange Rate: "+CommonUtils.toString(curRate);
				if(i == 0){
					if(flag){
						this.setScopeInfoTable_Format3("", "", amountEquivalent, "Subtotal for Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt)), 1);
						
					}else{
						this.setScopeInfoTable_Format3("", "", "", "", "", 0);
					}
				}else if(i == 1){
					if(flag){
						this.setScopeInfoTable_Format3("", "", exhangeRate, "Subtotal for Non-Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt2)), 2);
					}else{
						this.setScopeInfoTable_Format3("", "", "", "", "", 0);
					}
				}else if(i == 2){
					if(flag){
						this.setScopeInfoTable_Format3("", "", "", "Service Tax " + serviceTaxPercentage + " %", CommonUtils.toString(formatter.format(subItemExportGst)), 3);
					}else{
						this.setScopeInfoTable_Format3("", "", "", "", "", 0);
					}
				}else if(i == 3){
					if(flag){
						this.setScopeInfoTable_Format3("", "", "", "Grand Total", 
								CommonUtils.toString(formatter.format(new BigDecimal(formatter.format(subItemExportAmt).replaceAll(",", ""))
																	.add(new BigDecimal(formatter.format(subItemExportAmt2).replaceAll(",", "")))
																	.add(new BigDecimal(formatter.format(subItemExportGst).replaceAll(",", ""))))), 4);
					}else{
						this.setScopeInfoTable_Format3("", "", "", "", "", 0);
					}
				} else {
					this.setScopeInfoTable_Format3("", "", "", "", "", 0);
				}
		        
			}
		}
	}
	
	///*#594: [B1] Customer Maintenance CT 20190604*/
	private void getScopeInfo_Format4() throws DocumentException {
		int len = this.detailScopeInfo.size();
		if(len > 0){
			if(len == 1){
				this.detailScopeInfo.add(null);
				this.detailScopeInfo.add(null);
				this.detailScopeInfo.add(null);
			}
			if(len == 2){
				this.detailScopeInfo.add(null);
				this.detailScopeInfo.add(null);
			}
			for (int i = 0; i < this.detailScopeInfo.size(); i++) {
				boolean flag = false;
		        if(!this.defaultCurrency.equals(this.billCurrency)){
					flag = true;
				}
		        BigDecimal curRate = (BigDecimal)this.headerInfo.get("CUR_RATE");
		        String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		        String amountEquivalent = "Amount equivalent in " + exportCur;
		        String exhangeRate = "Exchange Rate: "+CommonUtils.toString(curRate);
				if(i == 0){
					if(flag){
						this.setScopeInfoTable_Format4("*Service Tax Exempted under Group G - Professionals.", amountEquivalent, "Subtotal for Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt)), 1);
						
					}else{
						getScopeInfo2_Format4();
						//this.setScopeInfoTable_Format3("", "", "", "", "", 0);
					}
				}else if(i == 1){
					if(flag){
						this.setScopeInfoTable_Format4("", exhangeRate, "Subtotal for Non-Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt2)), 2);
					}else{
						this.setScopeInfoTable_Format4("", "", "", "", 0);
					}
				}else if(i == 2){
					if(flag){
						this.setScopeInfoTable_Format4("", "", "Service Tax " + serviceTaxPercentage + " %", CommonUtils.toString(formatter.format(subItemExportGst)), 3);
					}else{
						this.setScopeInfoTable_Format4("", "", "", "", 0);
					}
				}else if(i == 3){
					if(flag){
						this.setScopeInfoTable_Format4("", "", "Grand Total", 
								CommonUtils.toString(formatter.format(new BigDecimal(formatter.format(subItemExportAmt).replaceAll(",", ""))
																	.add(new BigDecimal(formatter.format(subItemExportAmt2).replaceAll(",", "")))
																	.add(new BigDecimal(formatter.format(subItemExportGst).replaceAll(",", ""))))), 4);
					}else{
						this.setScopeInfoTable_Format4("", "", "", "", 0);
					}
				} else {
					this.setScopeInfoTable_Format4("", "", "", "", 0);
				}
		        
			}
		}
	}
	///*#594: [B1] Customer Maintenance CT 20190604*/
	
	private void getScopeInfo1() throws DocumentException {
		BigDecimal curRate = (BigDecimal)this.headerInfo.get("CUR_RATE");
//      if("JPY".equals(this.billCurrency)){
//      	curRate = curRate.multiply(new BigDecimal(100));
//		}
      String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
      String amountEquivalent = "Amount equivalent in " + exportCur;
      String exhangeRate = "Exchange Rate: "+CommonUtils.toString(curRate);
      this.setScopeInfoTable("", "", amountEquivalent, "Sub Total", CommonUtils.toString(formatter.format(subItemExportAmt)), 1);
      this.setScopeInfoTable("", "", exhangeRate, "GST Amount", CommonUtils.toString(formatter.format(subItemExportGst)), 2);
      this.setScopeInfoTable("", "", "", "Grand Total", CommonUtils.toString(formatter.format(new BigDecimal(formatter.format(subItemExportAmt).replaceAll(",", "")).add(new BigDecimal(formatter.format(subItemExportGst).replaceAll(",", ""))))), 3);
	}
	
	private void getScopeInfo1_Format3() throws DocumentException {
        BigDecimal curRate = (BigDecimal)this.headerInfo.get("CUR_RATE");
//        if("JPY".equals(this.billCurrency)){
//        	curRate = curRate.multiply(new BigDecimal(100));
//		}
        String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
        String amountEquivalent = "Amount equivalent in " + exportCur;
        String exhangeRate = "Exchange Rate: "+CommonUtils.toString(curRate);
        this.setScopeInfoTable_Format3("", "", amountEquivalent, "Subtotal for Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt)), 1);
        this.setScopeInfoTable_Format3("", "", amountEquivalent, "Subtotal for Non-Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt2)), 2);
        this.setScopeInfoTable_Format3("", "", exhangeRate, "Service Tax " + serviceTaxPercentage + " %", CommonUtils.toString(formatter.format(subItemExportGst)), 3);
        this.setScopeInfoTable_Format3("", "", "", "Grand Total", CommonUtils.toString(formatter.format(new BigDecimal(formatter.format(subItemExportAmt).replaceAll(",", ""))
        													.add(new BigDecimal(formatter.format(subItemExportAmt2).replaceAll(",", "")))
        													.add(new BigDecimal(formatter.format(subItemExportGst).replaceAll(",", ""))))), 4);
	}
	/*#594: [B1] Customer Maintenance CT 20190604*/
	private void getScopeInfo1_Format4() throws DocumentException {
        BigDecimal curRate = (BigDecimal)this.headerInfo.get("CUR_RATE");
//        if("JPY".equals(this.billCurrency)){
//        	curRate = curRate.multiply(new BigDecimal(100));
//		}
        String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
        String amountEquivalent = "Amount equivalent in " + exportCur;
        String exhangeRate = "Exchange Rate: "+CommonUtils.toString(curRate);
        this.setScopeInfoTable_Format3("*Service Tax Exempted under", "", amountEquivalent, "Subtotal for Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt)), 1);
        this.setScopeInfoTable_Format3("Group G - Professionals.", "", amountEquivalent, "Subtotal for Non-Taxable Service", CommonUtils.toString(formatter.format(subItemExportAmt2)), 2);
        this.setScopeInfoTable_Format3("", "", exhangeRate, "Service Tax " + serviceTaxPercentage + " %", CommonUtils.toString(formatter.format(subItemExportGst)), 3);
        this.setScopeInfoTable_Format3("", "", "", "Grand Total", CommonUtils.toString(formatter.format(new BigDecimal(formatter.format(subItemExportAmt).replaceAll(",", ""))
        													.add(new BigDecimal(formatter.format(subItemExportAmt2).replaceAll(",", "")))
        													.add(new BigDecimal(formatter.format(subItemExportGst).replaceAll(",", ""))))), 4);
	}
	
	private void getScopeInfo2_Format4() throws DocumentException {
        PdfPTable scopeTable = new PdfPTable(1);
		scopeTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		scopeTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("*Service Tax Exempted under Group G - Professionals.", font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        scopeTable.addCell(cell);
		
		this.document.add(scopeTable);
	}
	/*#594: [B1] Customer Maintenance CT 20190604*/
	private void setScopeInfoTable(String str1, String str2, String str3, String str4, String str5, int i) throws DocumentException {
		PdfPTable scopeTable;
		if(!this.billingType.equals("NT")){
			scopeTable = new PdfPTable(6);
			float[] colWidths = {1.1f * PER_INCH, 2.7f * PER_INCH, 2.3f * PER_INCH, 1.4f * PER_INCH, 1.2f * PER_INCH, 0.6f * PER_INCH};
			scopeTable.setWidths(colWidths);
		}else{
			scopeTable = new PdfPTable(5);
			float[] colWidths = {1f * PER_INCH, 3.8f * PER_INCH, 3.2f * PER_INCH, 1.8f * PER_INCH, 1.5f * PER_INCH};
			scopeTable.setWidths(colWidths);
		}
		
		scopeTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		scopeTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase(str1, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str2, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str3, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        if(i == 1){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.TOP | PdfPCell.RIGHT );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
        }else if(i == 3){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM  | PdfPCell.RIGHT);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str4, font09));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        if(i == 1){
        	cell.setBorder(PdfPCell.TOP );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.NO_BORDER);
        }else if(i == 3){
        	cell.setBorder(PdfPCell.BOTTOM );
        	cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str5, font09));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        if(i == 1){
        	cell.setBorder(PdfPCell.RIGHT | PdfPCell.TOP );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.RIGHT);
        }else if(i == 3){
        	cell.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM );
        	cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        if(!this.billingType.equals("NT")){
        	cell = new PdfPCell(new Phrase("", font09));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            scopeTable.addCell(cell);
        }
        
		if(!this.billingType.equals("NT")){
        	cell = new PdfPCell(new Phrase("", font09));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            scopeTable.addCell(cell);
        }
        
        this.document.add(scopeTable);
	}
	
	private void setScopeInfoTable_Format3(String str1, String str2, String str3, String str4, String str5, int i) throws DocumentException {
		PdfPTable scopeTable;
		if(!this.billingType.equals("NT")){
			scopeTable = new PdfPTable(6);
			float[] colWidths = {1.1f * PER_INCH, 2.7f * PER_INCH, 2.3f * PER_INCH, 2.5f * PER_INCH, 1.2f * PER_INCH, 0.6f * PER_INCH};
			scopeTable.setWidths(colWidths);
		}else{
			scopeTable = new PdfPTable(5);
			float[] colWidths = {1f * PER_INCH, 3.8f * PER_INCH, 3.2f * PER_INCH, 2.5f * PER_INCH, 1.5f * PER_INCH};
			scopeTable.setWidths(colWidths);
		}
		
		scopeTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		scopeTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase(str1, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str2, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str3, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        if(i == 1){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.TOP | PdfPCell.RIGHT );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT );
        }else if(i == 3){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
        }else if(i == 4){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM  | PdfPCell.RIGHT);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str4, font09));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        if(i == 1){
        	cell.setBorder(PdfPCell.TOP );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.NO_BORDER);
        }else if(i == 3){
        	cell.setBorder(PdfPCell.NO_BORDER);
        }else if(i == 4){
        	cell.setBorder(PdfPCell.BOTTOM );
        	cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str5, font09));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        if(i == 1){
        	cell.setBorder(PdfPCell.RIGHT | PdfPCell.TOP );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.RIGHT);
        }else if(i == 3){
        	cell.setBorder(PdfPCell.RIGHT);
        }else if(i == 4){
        	cell.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM );
        	cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        if(!this.billingType.equals("NT")){
        	cell = new PdfPCell(new Phrase("", font09));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            scopeTable.addCell(cell);
        }
        
		if(!this.billingType.equals("NT")){
        	cell = new PdfPCell(new Phrase("", font09));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            scopeTable.addCell(cell);
        }
        
        this.document.add(scopeTable);
	}
	
	//
	private void setScopeInfoTable_Format4(String str2, String str3, String str4, String str5, int i) throws DocumentException {
		PdfPTable scopeTable;
		if(!this.billingType.equals("NT")){
			scopeTable = new PdfPTable(5);
			float[] colWidths = {4f * PER_INCH, 2.3f * PER_INCH, 2.5f * PER_INCH, 1.2f * PER_INCH, 0.4f * PER_INCH};
			scopeTable.setWidths(colWidths);
		}else{
			scopeTable = new PdfPTable(4);
			float[] colWidths = {4.8f * PER_INCH, 3.2f * PER_INCH, 2.5f * PER_INCH, 1.5f * PER_INCH};
			scopeTable.setWidths(colWidths);
		}
		
		scopeTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		scopeTable.setWidthPercentage(100);
        
		PdfPCell cell = new PdfPCell(new Phrase(str2, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str3, font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        if(i == 1){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.TOP | PdfPCell.RIGHT );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT );
        }else if(i == 3){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
        }else if(i == 4){
        	cell.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM  | PdfPCell.RIGHT);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str4, font09));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        if(i == 1){
        	cell.setBorder(PdfPCell.TOP );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.NO_BORDER);
        }else if(i == 3){
        	cell.setBorder(PdfPCell.NO_BORDER);
        }else if(i == 4){
        	cell.setBorder(PdfPCell.BOTTOM );
        	cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(str5, font09));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        if(i == 1){
        	cell.setBorder(PdfPCell.RIGHT | PdfPCell.TOP );
        }else if(i == 2){
        	cell.setBorder(PdfPCell.RIGHT);
        }else if(i == 3){
        	cell.setBorder(PdfPCell.RIGHT);
        }else if(i == 4){
        	cell.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM );
        	cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }else{
        	cell.setBorder(PdfPCell.NO_BORDER);
        }
        scopeTable.addCell(cell);
        
        if(!this.billingType.equals("NT")){
        	cell = new PdfPCell(new Phrase("", font09));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            scopeTable.addCell(cell);
        }
        
		if(!this.billingType.equals("NT")){
        	cell = new PdfPCell(new Phrase("", font09));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            scopeTable.addCell(cell);
        }
        
        this.document.add(scopeTable);
	}
	//
	
	@SuppressWarnings("unchecked")
	private String getCurWording(Object curCode) {
		if(currencyMap == null) {
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			MappedCodeListLoader currencyCodeList = (MappedCodeListLoader) context.getBean("LIST_CURRENCY");
	        currencyMap = currencyCodeList.getCodeListMap();
		}
        if(currencyMap.containsKey(curCode))
        	return currencyMap.get(curCode).toString().trim();
        return "Unknown";
	}
	
	private String getFooterInfo() {
		if(this.footerInfo == null) {
			this.footerInfo = "";
			List<Map<String, Object>> fInfos = this.queryDAO.executeForMapList("B_BTH.getFooterInfo", null);
			boolean hasStr = false;
			for(int i = fInfos.size() - 1; i >= 0; i--) {
				if(fInfos.get(i).get("SET_VALUE") == null) {
					if(hasStr)
						this.footerInfo = ("\n" + this.footerInfo);
				}
				else {
					hasStr = true;
					this.footerInfo = ((fInfos.get(i).get("SET_VALUE").toString() + "\n") + this.footerInfo);
				}
			}
		}
		return this.footerInfo;
	}
/*	
	private String getCompanyName() {
		if(this.companyName == null) {
			this.companyName = "";
			List<Map<String, Object>> cInfos = this.queryDAO.executeForMapList("B_BTH.getCompanyInfo", null);
			for(Map<String, Object> info : cInfos) {
				if(info.get("COM_NAME") == null) {
					this.companyName += "\n";
				}
				else {
					this.companyName += (info.get("COM_NAME").toString() + "\n");
				}
			}
		}
		return this.companyName;
	}
*/	
	private static List<String> getRows(String str, int maxCharPerRow) {
		List<String> result = new ArrayList<String>();
		String[] acc = str.split("\r\n");
		acc = str.split("\n");
		for(int i = 0; i < acc.length; i++) {
			int row = (acc[i].length() % maxCharPerRow == 0 ? acc[i].length() / maxCharPerRow : (acc[i].length() / maxCharPerRow) + 1);
			for(int j = 0; j < row; j++) {
				result.add(acc[i].substring(j * maxCharPerRow, Math.min(acc[i].length(), (j + 1) * maxCharPerRow)));
			}
		}
		return result;
	}
	
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
	
	class G_BTH_P01_PageEvent extends PdfPageEventHelper {
		private PdfTemplate total;
        private BaseFont font;
        private Font font10b_arial;
        private Font font8_arial;
        private Font font8b_arial;
        
        private G_BTH_P01_PageEvent() {}
        
        @Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			total = writer.getDirectContent().createTemplate(30, 16);
			try {
				this.font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
				this.font10b_arial = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
				this.font8b_arial = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
				this.font8_arial = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			try {
				if(document.getPageNumber() > 1 && document.isOpen()) {
					if("0".equals(oldFlag)){
						displayTitle_old();
						displayHeader_old();
						displayDetail_Header_old(true);
					}else{
						if("1".equals(oldFlag)) {
							displayTitle();
							displayHeader();
							displayDetail_Header(true);
						}else if("9".equals(oldFlag)){
							displayTitle_Format3();
							displayHeader();
							displayDetail_Header(true);
						}else {
							displayTitle_Format3();
							displayHeader_Format4();
							displayDetail_Header_Format4(true);
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			super.onStartPage(writer, document);
		}
		
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			int currentPage = Integer.parseInt(String.format("%d", writer.getPageNumber()));
			if(realPagePrinted.contains(currentPage)) {
				PdfContentByte cb = writer.getDirectContent();
	        	cb.saveState();
	        	String text = writer.getPageNumber() + "/";
	        	float textBase = document.top() - 55;//align top
	        	float textSize = font.getWidthPoint(text, 10);
//	        	float adjust = font.getWidthPoint("0", 10);
	        	cb.beginText();
	        	cb.setFontAndSize(font, 10);
	        	//write current page
//	        	cb.setTextMatrix(document.right() - textSize - adjust - 108, textBase);
	        	cb.setTextMatrix(document.left() + 422, textBase);
	        	cb.showText(text);
	        	cb.endText();
//	        	cb.addTemplate(total, document.right() - adjust - 108, textBase);
	        	cb.addTemplate(total, document.left() + textSize + 422, textBase);
	        	cb.restoreState();
			}
			
			//repeat header for each page
			float xHeader = 0.3f * PER_INCH;
			float yHeader = document.getPageSize().getHeight() - (0.5f * PER_INCH);
			String headerStr1 = "A (STRICTLY PRIVATE)";
			String headerStr2_1 = "Created By (Dept./Name) :";
			String headerStr2_2 = "AR/" + CommonUtils.toString(headerInfo.get("PREPARED_BY"));
			String headerStr3_1 = "Distributed To :";
			String headerStr3_2 = CommonUtils.toString(headerInfo.get("CUST_NAME"));
			String headerStr4_1 = "Division/Dept. :";
			String headerStr4_2 = "Accounts Dept";
			PdfContentByte cb = writer.getDirectContent();
			// #77 ADD LOGO START
			try {
			    //Font font9_arial = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
                //Font font9_arial = FontFactory.getFont("Arial", 9, Font.NORMAL);
				Font font12_arial = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
				Font font9_arial = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
				String company_name = "NTT MSC SDN BHD (437563-H)";
                String address1 = "No.43000, Persiaran APEC,";
                String address2 = "63000 Cyberjaya, Selangor Darul Ehsan, Malaysia.";
                String contact = "Telephone +603-8319 0000 Facsimile +603-8319 0199";
                
                /*float xLogoHeader = document.getPageSize().getWidth() - (4.8f) * PER_INCH;
                float yLogoHeader = document.getPageSize().getHeight() - (1.3f) * PER_INCH;*/
                float xLogoHeader = document.getPageSize().getWidth() - (3.37f) * PER_INCH;
                float yLogoHeader = document.getPageSize().getHeight() - (1.0f) * PER_INCH;
                
                //image path
                File logoFile = new File(getClass().getClassLoader().getResource("../../image/nttmsclogo.png").toURI());
                String logoPath = logoFile.getAbsolutePath();
                Image logo = Image.getInstance(logoPath);
                //logo.scaleAbsolute(4.5f * PER_INCH, 0.7f * PER_INCH);
                logo.scaleAbsolute(122f,44f);
                logo.setAbsolutePosition(xLogoHeader, yLogoHeader+10);
                cb.addImage(logo);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(company_name, font12_arial), xLogoHeader, yLogoHeader - 10, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(address1, font9_arial), xLogoHeader, yLogoHeader - 20, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(address2, font9_arial), xLogoHeader, yLogoHeader - 30, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(contact, font9_arial), xLogoHeader, yLogoHeader - 40, 0);
                if(!"0".equals(oldFlag)){
                	String gstno = "";
                	if ("2".equals(oldFlag) || "9".equals(oldFlag)) {
						gstno = "Service Tax No:  W24-1808-31039434";
					}else {
						gstno = "GST No:  001 888 223 232";
					}
                	ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(gstno, font9_arial), xLogoHeader, yLogoHeader - 50, 0);
				}
            } catch(Exception e) {
                e.printStackTrace();
            }

			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(headerStr1, font10b_arial), xHeader, yHeader, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(headerStr2_1, font8b_arial), xHeader, yHeader - 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(headerStr2_2, font8_arial), xHeader + 95, yHeader - 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(headerStr3_1, font8b_arial), xHeader, yHeader - 20, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(headerStr3_2, font8_arial), xHeader + 58, yHeader - 20, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(headerStr4_1, font8b_arial), xHeader, yHeader - 30, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(headerStr4_2, font8_arial), xHeader + 56, yHeader - 30, 0);
		}
		
		public void onCloseDocument(PdfWriter writer, Document document) {
			//write total page
        	total.beginText();
        	total.setFontAndSize(font, 10);
        	total.setTextMatrix(0, 0);
        	total.showText(String.valueOf(writer.getPageNumber() - 1));
        	total.endText();
		}
	}
	
	
	
	
	
	// old pdf
	public void generatePDF_old() {
		try {
			// title
			this.displayTitle_old();
			// build table header
			this.displayHeader_old();
			// build table detail
			// build table detail header
			this.displayDetail_old();
			// footer
			this.displayFooter_old();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	  * Create normal pdf cell
	  * <p> 
	  * 
	  * </p> 
	  * @param String title, Color color, int align
	  * @return PdfPCell
	  * @exception None
	  */
	private PdfPCell getCell_old(String title, BaseColor color, int align, Font font){
		PdfPCell cell = new PdfPCell(new Paragraph (title, font));
		cell.setBackgroundColor(color);
		cell.setHorizontalAlignment(align);
		return cell; 
	}
	
	/** 
	  * Create colspan pdf cell
	  * <p> 
	  * 
	  * </p> 
	  * @param String title, Color color, int align, int colspan
	  * @return PdfPCell
	  * @exception None
	  */
	private PdfPCell getCellColSpan_old(String title, BaseColor color, int align, int colspan, Font font){
		PdfPCell cell = new PdfPCell(new Paragraph (title, font));
		cell.setBackgroundColor(color);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		return cell; 
	}
	
	private void displayTitle_old() throws DocumentException {
		Paragraph pTitle = new Paragraph();
		pTitle.setAlignment(Element.ALIGN_CENTER);
		Phrase phTitle = new Phrase(this.billingType.equals("IN") ? "INVOICE" : this.billingType.equals("DN") ? "DEBIT NOTE" : "CREDIT NOTE", font14b);
		pTitle.add(phTitle);
		this.document.add(pTitle);
	}
	
	private void displayHeader_old() throws DocumentException {
		/*
		 * A4 = 8.27inch x 11.69inch
		 */
		float[] colWidths = {346.9f, 90f, 7f, 135f};
		PdfPTable tHeader = new PdfPTable(4);
		tHeader.setWidthPercentage(100);
		tHeader.setWidths(colWidths);
		tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tHeader.setSpacingBefore(18);
		float padding = 0f;
		
		//data for column LEFT and RIGHT
		List<String> dataLeft = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean add(String e) {
				if(e == null) return super.add(" ");
				if(!e.trim().equals(""))
					return super.add(e);
				return false;
			}
		};
		List<String[]> dataRight = new ArrayList<String[]>();
		
		dataLeft.add(CommonUtils.toString(headerInfo.get("CUST_NAME")));
		dataRight.add(new String[]{this.billingType.equals("IN") ? "Invoice No." : this.billingType.equals("DN") ? "Debit Note No." : "Credit Note No.", ":", CommonUtils.toString(this.headerInfo.get("ID_REF"))});

		// format: page/totalPage, totalPage will fill by Event object
		realPagePrinted.add(pdfW.getCurrentPageNumber());
		String address1 = CommonUtils.toString(this.headerInfo.get("ADDRESS1"));
		dataLeft.add(address1);
		dataRight.add(new String[] {"Page", ":", ""});
		
		String address2 = CommonUtils.toString(this.headerInfo.get("ADDRESS2"));
		dataLeft.add(address2);
		//String jobNo = CommonUtils.toString(this.headerInfo.get("JOB_NO"));
		//dataRight.add(new String[] {"Job No", ":", jobNo.substring(0, Math.min(7, jobNo.length()))});
		
		String address3 = "";
		if(! CommonUtils.toString(this.headerInfo.get("ADDRESS3")).isEmpty())
			address3 = CommonUtils.toString(this.headerInfo.get("ADDRESS3"));
		dataLeft.add(address3);
		dataRight.add(new String[] {"Issue Date", ":", CommonUtils.toString(this.headerInfo.get("DATE_REQ"))});
		
		String address4 = "";
		if(! CommonUtils.toString(this.headerInfo.get("ADDRESS4")).isEmpty()) {
			CommonUtils.fixAddress4(this.headerInfo);
			address4 = CommonUtils.toString(this.headerInfo.get("ADDRESS4"));
		}
		if(this.billingType.equals("IN")) {
			dataLeft.add(address4);
			addHeaderTerm_old(dataRight);
			
			addHeaderTel_old(dataLeft);
			addHeaderPO_old(dataRight);
			
			addHeaderQuo_old(dataRight);
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			dataLeft.add(null);
			addHeaderAc_old(dataRight);
			
			addHeaderAtt_old(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		else if(this.billingType.equals("DN")) {
			dataLeft.add(address4);
			addHeaderTerm_old(dataRight);
			
			addHeaderTel_old(dataLeft);
			addHeaderAc_old(dataRight);
			
			dataRight.add(new String[] {"", "", ""});
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);

			dataLeft.add(null);
			dataRight.add(new String[] {"", "", ""});
			
			addHeaderAtt_old(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		else if(this.billingType.equals("CN")) {
			dataLeft.add(address4);
			addHeaderPO_old(dataRight);
			
			addHeaderTel_old(dataLeft);
			addHeaderQuo_old(dataRight);
			
			addHeaderAc_old(dataRight);
			//add blank row - padding
			while(dataLeft.size() < dataRight.size())
				dataLeft.add(null);
			
			dataLeft.add(null);
            dataRight.add(new String[] {"", "", ""});
            
			addHeaderAtt_old(dataLeft);
			dataRight.add(new String[] {"", "", ""});
		}
		//<dataLeft> has same length with <dataRight>
		int n = dataLeft.size();
		for(int i = 0; i < n; i++) {
			if(i < n - 1)
				this.displayHeader_CellLeft_old(tHeader, padding, dataLeft.get(i), font10);
			else
				this.displayHeader_CellLeft_old(tHeader, padding, dataLeft.get(i), font10b);
			this.displayHeader_CellRight_old(tHeader, padding, dataRight.get(i)[0], dataRight.get(i)[1], dataRight.get(i)[2]);
		}
		// add table header
		this.document.add(tHeader);
	}
	
	private void addHeaderTerm_old(List<String[]> data) {
		data.add(new String[]{"Term", ":", CommonUtils.toString(this.headerInfo.get("TERM"))});
	}
	
	private void addHeaderTel_old(List<String> data) {
	    String telNo = CommonUtils.toString(this.headerInfo.get("TEL_NO")).trim();
	    String faxNo = CommonUtils.toString(this.headerInfo.get("FAX_NO")).trim();
	    if (!CommonUtils.isEmpty(telNo)&&!CommonUtils.isEmpty(faxNo)){
	        data.add("Tel: "+CommonUtils.toString(this.headerInfo.get("TEL_NO")) + "    Fax: " + CommonUtils.toString(this.headerInfo.get("FAX_NO")));
	    } else if(!CommonUtils.isEmpty(telNo)&&CommonUtils.isEmpty(faxNo)){
	        data.add("Tel: "+CommonUtils.toString(this.headerInfo.get("TEL_NO")));
	    } else if (!CommonUtils.isEmpty(telNo)&&!CommonUtils.isEmpty(faxNo)){
            data.add("Fax: " + CommonUtils.toString(this.headerInfo.get("FAX_NO")));
        }
	}
	
	private void addHeaderAtt_old(List<String> data) {
		data.add("Attention : " + CommonUtils.toString(this.headerInfo.get("CONTACT_NAME")));
	}
	
	private void addHeaderPO_old(List<String[]> data) {
		data.add(new String[]{"P/O No.", ":", CommonUtils.toString(this.headerInfo.get("CUST_PO"))});
	}
	
	private void addHeaderQuo_old(List<String[]> data) {
		data.add(new String[]{"Quotation No.", ":", CommonUtils.toString(this.headerInfo.get("QUO_REF"))});
	}
	
	private void addHeaderAc_old(List<String[]> data) {
		data.add(new String[]{"A/C Manager", ":", CommonUtils.toString(this.headerInfo.get("ID_CONSULT_NAME"))});
	}
	
	private void displayHeader_CellLeft_old(PdfPTable tHeader, float padding, String value, Font font) {
		PdfPCell c = new PdfPCell(new Phrase(value, font));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
	}
	
	private void displayHeader_CellRight_old(PdfPTable tHeader, float padding, String label, String commas, Object value) {
		PdfPCell c = null;
		c = new PdfPCell(new Phrase(label, font10));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
		c = new PdfPCell(new Phrase(commas, font10));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
		c = new PdfPCell(new Phrase(CommonUtils.toString(value), font10));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setPadding(padding);
		tHeader.addCell(c);
	}
	
	private void displayDetail_Header_old(boolean isAdded) throws DocumentException {
		PdfPCell c = null;
		//0.5inch 4.25inch 0.875inch 0.875inch 1inch
		float[] colWidths = {0.5f * PER_INCH, 4.25f * PER_INCH, 0.875f * PER_INCH, 0.875f * PER_INCH, 1f * PER_INCH};
		this.tableDetail = new PdfPTable(5);
		this.tableDetail.setSpacingBefore(6);
		this.tableDetail.setWidthPercentage(100);
		this.tableDetail.setWidths(colWidths);
		c = new PdfPCell(new Phrase("Item", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);

		c = new PdfPCell(new Phrase("Description", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Quantity", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Unit Price", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase("Amount ("+this.headerInfo.get("BILL_CURRENCY")+")", font10b));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c.setFixedHeight(0.35f * PER_INCH);//0.35inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		if(isAdded) {//this case for more 1 page (from 2 page)
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
			
			c = new PdfPCell(new Phrase(CommonUtils.toString(formatter.format(this.carryforward)), font10b));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setVerticalAlignment(PdfPCell.ALIGN_TOP);
			c.setBorder(PdfPCell.RIGHT);
			this.tableDetail.addCell(c);
		}
	}
	
	private void displayDetail_old() throws DocumentException {
		PdfPCell c = null;
		this.displayDetail_Header_old(false);
		// in fact no of page
		this.subTotalDetail = new BigDecimal("0");
		// loop for each detail and display
		int index = 0, rowPerPage = 0, nextRow = 0;
		String sItem, description, sQty, sPrice, sAmt;
		String period = null;
		for(int i = 0; i < this.detailInfo.size(); i++){
			Map<String,Object> detail = this.detailInfo.get(i);
			String isDisplay = CommonUtils.toString(detail.get("IS_DISPLAY"));
			String itemCat = CommonUtils.toString(detail.get("ITEM_CAT"));
			String jobNo = CommonUtils.toString(detail.get("JOB_NO")).trim();
			String isDisplayJobno = CommonUtils.toString(detail.get("IS_DISPLAY_JOBNO")).trim();
			BigDecimal itemAmt = new BigDecimal("0");
			
			if (!CommonUtils.isEmpty(isDisplay)) {
			    if ("0".equals(isDisplay)) {//without lump sum
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
			    if ("0".equals(isDisplay)) {//without lump sum
					sQty = "";
					sPrice = "";
					sAmt = "";
				}
				else {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
					itemAmt = (BigDecimal)detail.get("ITEM_AMT");
					this.subTotalDetail = this.subTotalDetail.add(itemAmt);
					
					if("0".equals(sQty)) {
					    sQty = "";
					}
					if("0.00".equals(sPrice)) {
					    sPrice = "";
                    }
					if("0.00".equals(sAmt)) {
					    sAmt = "";
                    }
				}
			} else {
				try {
					if(Double.parseDouble(detail.get("ITEM_QTY").toString()) == 0 || 
							Double.parseDouble(detail.get("ITEM_UPRICE").toString()) == 0 ||
							Double.parseDouble(detail.get("ITEM_AMT").toString()) == 0) {
						sQty = "";
						sPrice = "";
						sAmt = "";
					}
					else {
						sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
						sPrice = formatter.format(detail.get("ITEM_UPRICE"));
						sAmt = formatter.format(detail.get("ITEM_AMT"));
						itemAmt = (BigDecimal)detail.get("ITEM_AMT");
                        this.subTotalDetail = this.subTotalDetail.add(itemAmt);
                        
                        if("0".equals(sQty)) {
                            sQty = "";
                        }
                        if("0.00".equals(sPrice)) {
                            sPrice = "";
                        }
                        if("0.00".equals(sAmt)) {
                            sAmt = "";
                        }
					}
				} catch(Exception e) {
					sQty = CommonUtils.toString(detail.get("ITEM_QTY"));
					sPrice = formatter.format(detail.get("ITEM_UPRICE"));
					sAmt = formatter.format(detail.get("ITEM_AMT"));
				}
			}
			// billing period
			period = "";
			if((i + 1 < this.detailInfo.size() 
                    && this.detailInfo.get(i + 1).get("ITEM_LEVEL").equals("0")
                    && "1".equals(itemCat)) 
                    || (i == this.detailInfo.size() - 1 && "1".equals(itemCat))) {
				if(detail.get("BILL_FROM") != null && detail.get("BILL_TO") != null) {	
					if(!CommonUtils.toString(detail.get("BILL_FROM")).equals(CommonUtils.toString(detail.get("BILL_TO")))){
						period = "Billing Period: " 
	                        + CommonUtils.toString(detail.get("BILL_FROM")) + " ~ " 
	                        + CommonUtils.toString(detail.get("BILL_TO")) + "\r\n";
					} else {
					    period = " ";
					}
				}
				if(i != this.detailInfo.size() - 1 && !period.equals(""))
					period += "\r\n";//break line for next description
				if(i == this.detailInfo.size() - 1)
					period += "\r\n";//end of detail
			}
			
			// calculate to decide new page or not
			List<String> rows = getRows_old(description, MAXIMUN_CHAR_PER_ROW);
			if(period.equals("")) nextRow = rows.size();
			else {
				nextRow = rows.size() + 2;//description + period	
				rows.add(period);
			}
			if(rowPerPage >= ROW_PER_PAGE) {//new page
				rowPerPage = 0;
				// sub total balance line
				c = getCellColSpan_old("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
				c.setFixedHeight(0.2f * PER_INCH);//0.2inch
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				BigDecimal before = BigDecimal.ZERO;
				if("1".equals(isDisplay)){
				    before = (BigDecimal)detail.get("ITEM_AMT");
				}
				this.subTotalDetail = this.subTotalDetail.subtract(before);
				c = getCell_old(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_RIGHT, 
						font10);
				c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				this.tableDetail.addCell(c);
				
				this.document.add(this.tableDetail);
				pdfW.setPageEmpty(true);
				this.document.newPage();
				this.subTotalDetail = this.subTotalDetail.add(before);
			}
			if(rowPerPage + nextRow > ROW_PER_PAGE) {
				String desc = "";
				boolean isNewPage = true;
				boolean isFistPage = true;
				while(!rows.isEmpty()) {
					desc = "";
					isNewPage = true;
					int k = 0;
					for(k = 0; k < ROW_PER_PAGE - rowPerPage; k++) {
						if(rows.isEmpty()) {
							isNewPage = false;
							break;
						}
                        if(!(!period.equals("") && rows.size() == 1)){
                            
                            String rowDesc = rows.remove(0);
                            if (rowDesc != null) {
                                if ( (rowDesc.length() >=1 && rowDesc.substring(rowDesc.length() - 1, rowDesc.length()).equals("\r"))
                                        || (rowDesc.length() >=2 && rowDesc.substring(rowDesc.length() - 2, rowDesc.length()).equals("\r\n"))) {
                                    desc += rowDesc;
                                } else {
                                    desc += rowDesc + "\r";
                                }
                            }
                        }else{
                            rows.remove(0);
                        }
						//desc += ("\r\n" + rows.remove(0));
					}
					rowPerPage = k;
					//only print full information in first page
					if ("1".equals(isDisplayJobno)) {
                        this.displayDetail_Description_old("", "Job No.: " + jobNo, "", "", "", 0f);
                    }
					if(isFistPage) {
						isFistPage = false;
						this.displayDetail_Description(sItem, desc, sQty, sPrice, sAmt);
					} else {
						this.displayDetail_Description_old("", desc, "", "", "", 0f);
					}
					//new page
					if(isNewPage) {
						rowPerPage = 0;
						// sub total balance line
						c = getCellColSpan_old("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
						c.setFixedHeight(0.2f * PER_INCH);//0.2inch
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						c = getCell_old(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_RIGHT, 
								font10);
						c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
						this.tableDetail.addCell(c);
						
						this.document.add(this.tableDetail);
						pdfW.setPageEmpty(true);
						this.document.newPage();
					}
				}
			} else {
				//rowPerPage += nextRow;
				rowPerPage = rowPerPage + nextRow;
				//print
				if ("1".equals(isDisplayJobno)) {
                    this.displayDetail_Description_old("", "Job No.: " + jobNo, "", "", "", 0f);
                }
				this.displayDetail_Description(sItem, description, sQty, sPrice, sAmt);
				if(period != null && !period.equals("")) {
				    this.displayDetail_Description_old("", period, "", "", "", 0f);
				}
			}
		}
		if(rowPerPage > 14) {
			c = getCellColSpan_old("Balance c/f ", BaseColor.WHITE, Element.ALIGN_CENTER, 4, font10b);
			c.setFixedHeight(0.2f * PER_INCH);//0.2inch
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			c = getCell_old(CommonUtils.toString(formatter.format(this.carryforward)), BaseColor.WHITE, Element.ALIGN_RIGHT, 
					font10);
			c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			this.tableDetail.addCell(c);
			
			this.document.add(this.tableDetail);
			pdfW.setPageEmpty(true);
			this.document.newPage();
		}
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
        String expCurrency = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		// sub total line
		c = getCellColSpan_old("Sub Total (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.WHITE, 
				Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		c = getCell_old(CommonUtils.toString(formatter.format(this.subTotalDetail)), BaseColor.WHITE, Element.ALIGN_RIGHT, 
				font10);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		// gst line
		c = getCellColSpan_old("", BaseColor.WHITE, Element.ALIGN_RIGHT, 4, font10);
		c.setFixedHeight(0.2f * PER_INCH);//0.2inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		c = getCell_old("", BaseColor.WHITE, Element.ALIGN_RIGHT, font10);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		// grant total line 
		c = getCellColSpan_old("Grand Total (" + CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY"))+")", BaseColor.LIGHT_GRAY, 
				Element.ALIGN_CENTER, 4, font10b);
		c.setFixedHeight(0.25f * PER_INCH);//0.25inch
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		c = getCell_old(formatter.format(this.headerInfo.get("BILL_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT, 
				font10b);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		this.tableDetail.addCell(c);
		// grant total fixed forex
		// if C.IS_DISPLAY = 1, display, else do not display.
		//String cIsDisplay = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY"));
		String isDisplayExpAmt = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY_EXP_AMT"));
		if ("1".equals(isDisplayExpAmt)) {
		    String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BTH.getSetValueOfSystemBase", null, String.class));
		    String grandWording = "";
		    if (billCurrency.equals(defCurrency)) {
		        grandWording = "Grand Total " + expCurrency + " ";
	            grandWording = grandWording + "(1 " + expCurrency + " = ";
	            String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
	            if (!CommonUtils.isEmpty(fixedForex)) {
	                grandWording += "Fixed Forex ";
	            }
	            grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + billCurrency + ")";
		    } else {
		        grandWording = "Grand Total " + expCurrency + " ";
                grandWording = grandWording + "(1 " + billCurrency + " = ";
                String fixedForex = CommonUtils.toString(this.headerInfo.get("FIXED_FOREX")).trim();
                if (!CommonUtils.isEmpty(fixedForex)) {
                    grandWording += "Fixed Forex ";
                }
                grandWording += CommonUtils.toString(this.headerInfo.get("CUR_RATE")) + " " + expCurrency + ")";
		    }
		    
		    c = getCellColSpan_old(grandWording, BaseColor.LIGHT_GRAY, 
                    Element.ALIGN_CENTER, 4, font10b);
            c.setFixedHeight(0.25f * PER_INCH);//0.25inch
            c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            this.tableDetail.addCell(c);
            c = getCell_old(formatter.format(this.headerInfo.get("EXPORT_AMOUNT")), BaseColor.LIGHT_GRAY, Element.ALIGN_RIGHT, 
                    font10b);
            c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            this.tableDetail.addCell(c);
		}
		this.document.add(this.tableDetail);
	}
	
	private void displayDetail_Description_old(String item, String description, String quantity, String unitPrice,
			String amount, float paddingTop) throws DocumentException {
		displayDetail_DescriptionHelp_old(item, description, quantity, unitPrice, amount, 0f, paddingTop);
	}
	
	private void displayDetail_Description(String item, String description, String quantity, String unitPrice,
			String amount) throws DocumentException {
		displayDetail_DescriptionHelp_old(item, description, quantity, unitPrice, amount, 3.0f, 2.0f);// 1.5 line
	}
	
	private void displayDetail_DescriptionHelp_old(String item, String description, String quantity, String unitPrice,
			String amount, float paragraphSpace, float paddingTop) throws DocumentException {
		PdfPCell c = null;		
		c = new PdfPCell(new Phrase(item, font10));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase(description, font10));
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		c.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		c.setBorder(PdfPCell.RIGHT);
		c.setExtraParagraphSpace(paragraphSpace);
		c.setPaddingTop(paddingTop);
		this.tableDetail.addCell(c);
		
		c = new PdfPCell(new Phrase(quantity, font10));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
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
	}
	
	private void displayFooter_old() throws DocumentException {
		Paragraph pFooter = new Paragraph();
		pFooter.setAlignment(Element.ALIGN_LEFT);
		Phrase phFooter1 = null;
		String billCurrency = CommonUtils.toString(this.headerInfo.get("BILL_CURRENCY")).trim();
		String exportCur = CommonUtils.toString(this.headerInfo.get("EXPORT_CUR")).trim();
		if (billCurrency.equals(exportCur)){
		    phFooter1 = new Phrase("("+this.getCurWording_old(billCurrency)+" : " 
	                + EnglishNumberToWords.convert(CommonUtils.toString(this.headerInfo.get("BILL_AMOUNT"))) 
	                + " only)\n", 
	                font10);
		} else {
			String isDisAmt = CommonUtils.toString(this.headerInfo.get("IS_DISPLAY_EXP_AMT"));
			if("1".equals(isDisAmt)){
				phFooter1 = new Phrase("("+this.getCurWording_old(exportCur)+" : " 
		                + EnglishNumberToWords.convert(CommonUtils.toString(this.headerInfo.get("EXPORT_AMOUNT"))) 
		                + " only)\n", 
		                font10);
			}
			else{
				phFooter1 = new Phrase("("+this.getCurWording_old(billCurrency)+" : " 
		                + EnglishNumberToWords.convert(CommonUtils.toString(this.headerInfo.get("BILL_AMOUNT"))) 
		                + " only)\n", 
		                font10);
			}
		}
		
		
		pFooter.add(phFooter1);
		this.document.add(pFooter);
		
		/**
		 * use nested table of table to ensure footer fix on one page.
		 */
		StringBuffer footerStr = new StringBuffer("");
		if(this.billingType.equals("IN") || this.billingType.equals("DN")) {
			footerStr.append("\n");
			footerStr.append("Please quote this Invoice / Debit Note when making payment to "+'"'+"NTT MSC SDN BHD"+'"'+"\n\n");
			footerStr.append("1     For any enquiries, please contact our Customer Service at 1-800-881736 / 03-8318-1121.\n");
			footerStr.append("2     Interest at 1.5% per month will be imposed on all overdue invoices.\n");
			footerStr.append("3     Please direct the payment into our bank account:\n\n");
			/*footerStr.append("       Bank Name :   Bank of Tokyo-Mitsubishi UFJ          Sumitomo Mitsui Banking                    Mizuho Bank (Malaysia) Berhad\n");
			footerStr.append("                               (Malaysia) Berhad                               Corporation Malaysia Berhad\n");
			footerStr.append("       Swift Code  :    BOTKMYKX                                     SMBCMYKL                                        MHCBMYKA\n");
			footerStr.append("       Account No :   137782 (MYR)                                    10005000 (MYR)                                  8880001567 (MYR)\n");
			footerStr.append("                                751286 (USD, JPY, SGD,                  10005001 (USD)                                    8880001583 (USD)\n");
			footerStr.append("                                               GBP, EUR,HKD)                10005002 (JPY)                                     8880001575 (JPY)\n");*/
		}
		else if(this.billingType.equals("CN")) {
		}
		//footerStr.append("\n\n\n\n");//3 line
		
		PdfPTable footerTable1 = new PdfPTable(1);
		footerTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		footerTable1.setWidthPercentage(100);
		
        PdfPCell cell = new PdfPCell(new Phrase(footerStr.toString(), font09));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        footerTable1.addCell(cell);
        
        this.document.add(footerTable1);
        
        //set bankinfo
        getBankInfo();
        
        PdfPTable footerTable3 = new PdfPTable(1);
        
        PdfPCell  cell3 = new PdfPCell(new Phrase("\r\n", font09));
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorder(PdfPCell.NO_BORDER);
        footerTable3.addCell(cell3);
        
        cell3 = new PdfPCell(new Phrase("\r\n", font09));
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorder(PdfPCell.NO_BORDER);
        footerTable3.addCell(cell3);
        
        cell3 = new PdfPCell(new Phrase("This document is computer generated and no signature is required.\nIf you encounter any unethical practices or compliance related matters, please let us know by emailing to feedback-my@ntt.com. All feedback will be kept confidential.", font09));
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorder(PdfPCell.NO_BORDER);
        footerTable3.addCell(cell3);

        this.document.add(footerTable3);
	}
	
	@SuppressWarnings("unchecked")
	private String getCurWording_old(Object curCode) {
		if(currencyMap == null) {
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			MappedCodeListLoader currencyCodeList = (MappedCodeListLoader) context.getBean("LIST_CURRENCY");
	        currencyMap = currencyCodeList.getCodeListMap();
		}
        if(currencyMap.containsKey(curCode))
        	return currencyMap.get(curCode).toString().trim();
        return "Unknown";
	}
	
	private static List<String> getRows_old(String str, int maxCharPerRow) {
		List<String> result = new ArrayList<String>();
		String[] acc = str.split("\r\n");
		for(int i = 0; i < acc.length; i++) {
			int row = (acc[i].length() % maxCharPerRow == 0 ? acc[i].length() / maxCharPerRow : (acc[i].length() / maxCharPerRow) + 1);
			for(int j = 0; j < row; j++) {
				result.add(acc[i].substring(j * maxCharPerRow, Math.min(acc[i].length(), (j + 1) * maxCharPerRow)));
			}
		}
		return result;
	}
	
	private void getBankInfo() throws DocumentException {
		float[] bankColWidths = { 60f, 4f, 80f, 80f, 80f };
		PdfPTable bankInfoTable = new PdfPTable(5);
		bankInfoTable.setWidthPercentage(100);
		bankInfoTable.setSpacingBefore(2);
		bankInfoTable.setWidths(bankColWidths);
		bankInfoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		bankInfoTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		if (bankInfoList != null && 0 < bankInfoList.size()) {
			if (bankInfoList.size() == 1) {
				List<String> bankInfo = getBankInfo(bankInfoList.get(0));
				setBankInfo(bankInfoTable, "      Bank", ":", bankInfo.get(0),
						"", "", "1");
				setBankInfo(bankInfoTable, "      SWIFT Code", ":",
						bankInfo.get(4), "", "", "2");
				setBankInfo(bankInfoTable, "      Account Name No", ":",
						bankInfo.get(3), "", "", "2");
				 for (Map<String, Object> bank : bankInfoList) {
			            String DISPLAY_ORDER = CommonUtils.toString(bank.get("DISPLAY_ORDER"));
					//System.out.println(DISPLAY_ORDER);
					if ("1".equals(DISPLAY_ORDER)) {
						List<String> accno1 = accAllMap.get("accNoOne");
						int rows = accno1.size();
						for (int i = 0; i < rows; i++) {
							String acc1 = "";
							if (accno1.size() > i) {
								acc1 = accno1.get(i);
							}
							setBankInfo(bankInfoTable, "", "", acc1, "", "",
									"2");
						}
						setBankInfo(bankInfoTable, "", "", "", "", "", "3");
					} else if ("2".equals(DISPLAY_ORDER)) {
						List<String> accno2 = accAllMap.get("accNoTwo");
						int rows = accno2.size();
						for (int i = 0; i < rows; i++) {
							String acc2 = "";
							if (accno2.size() > i) {
								acc2 = accno2.get(i);
							}
							setBankInfo(bankInfoTable, "", "", acc2, "", "",
									"2");
						}
						setBankInfo(bankInfoTable, "", "", "", "", "", "3");
					} else if ("3".equals(DISPLAY_ORDER)) {
						List<String> accno3 = accAllMap.get("accNoThree");
						int rows = accno3.size();
						for (int i = 0; i < rows; i++) {
							String acc3 = "";
							if (accno3.size() > i) {
								acc3 = accno3.get(i);
							}
							setBankInfo(bankInfoTable, "", "", acc3, "", "",
									"2");
						}
						setBankInfo(bankInfoTable, "", "", "", "", "", "3");
					} 
				 }

			} else {
				List<String> bankInfo1 = getBankInfo(bankInfoList.get(0));
				List<String> bankInfo2 = getBankInfo(bankInfoList.get(1));
				List<String> bankInfo3 = getBankInfo(bankInfoList.get(2));
				setBankInfo(bankInfoTable, "      Bank", ":", bankInfo1.get(0),
						bankInfo2.get(0), bankInfo3.get(0), "1");
//				setBankInfo(bankInfoTable, "", "", bankInfo1.get(1),
//						bankInfo2.get(1), bankInfo3.get(1), "2");
				setBankInfo(bankInfoTable, "      SWIFT Code", ":", bankInfo1.get(4),
						bankInfo2.get(4), bankInfo3.get(4), "2");
				setBankInfo(bankInfoTable, "      Account No", ":", bankInfo1.get(3),
						bankInfo2.get(3), bankInfo3.get(3), "2");
				// show all account
				List<String> accnoTemp1 = accAllMap.get("accNoOne");
				List<String> accno1 = getAccList(accnoTemp1);
				List<String> accnoTemp2 = accAllMap.get("accNoTwo");
				List<String> accno2 = getAccList(accnoTemp2);
				List<String> accnoTemp3 = accAllMap.get("accNoThree");
				List<String> accno3 = getAccList(accnoTemp3);
				int rows = getMaxSize(accno1.size(), accno2.size(),
						accno3.size());
				for (int i = 0; i < rows; i++) {
					String acc1 = "";
					String acc2 = "";
					String acc3 = "";
					if (accno1.size() > i) {
						acc1 = accno1.get(i);
					}
					if (accno2.size() > i) {
						acc2 = accno2.get(i);
					}
					if (accno3.size() > i) {
						acc3 = accno3.get(i);
					}
					setBankInfo(bankInfoTable, "", "", acc1, acc2, acc3, "2");
				}
				setBankInfo(bankInfoTable, "", "", "", "", "", "3");
			}
		} else {
			setBankInfo(bankInfoTable, "      Bank", ":", "", "", "", "1");
			setBankInfo(bankInfoTable, "", "", "", "", "", "2");
			setBankInfo(bankInfoTable, "      SWIFT Code", ":", "", "", "", "2");
			setBankInfo(bankInfoTable, "      Account No", ":", "", "", "", "3");

		}

		this.document.add(bankInfoTable);
	}
	// Fix extra Bank Line ST
	private int getBankInfoCount() throws DocumentException {
		int lineCount = 0;
		if (bankInfoList != null && 0 < bankInfoList.size()) {
			if (bankInfoList.size() == 1) {
				List<String> bankInfo1 = getBankInfo(bankInfoList.get(0));
				// Bank Name Line
				lineCount += extraLineCount(bankInfo1.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				
				// SWIFT Code Line
				lineCount += extraLineCount(bankInfo1.get(4),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				
				// Account No Line
				lineCount += extraLineCount(bankInfo1.get(3),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				
				// show all account
				List<String> accnoTemp1 = accAllMap.get("accNoOne");
				List<String> accno1 = getAccList(accnoTemp1);
				List<String> accnoTemp2 = accAllMap.get("accNoTwo");
				List<String> accno2 = getAccList(accnoTemp2);
				List<String> accnoTemp3 = accAllMap.get("accNoThree");
				List<String> accno3 = getAccList(accnoTemp3);
				int rows = getMaxSize(accno1.size(), accno2.size(),
						accno3.size());
				
				lineCount = lineCount + rows;
			}else {
				List<String> bankInfo1 = getBankInfo(bankInfoList.get(0));
				List<String> bankInfo2 = getBankInfo(bankInfoList.get(1));
				List<String> bankInfo3 = getBankInfo(bankInfoList.get(2));
				//int totalPerLineFor3Bank = BANKINFO_MAXIMUN_CHAR_PER_ROW + BANKINFO_MAXIMUN_CHAR_PER_ROW + BANKINFO_MAXIMUN_CHAR_PER_ROW;
				// Bank Name Line
				int totalExtraLine = 0;
				//if(bankInfo1.get(0).length() > BANKINFO_MAXIMUN_CHAR_PER_ROW) {
					//if(totalExtraLine < (lineCount + extraLineCount(bankInfo1.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					//}
				//}
				if(totalExtraLine < (extraLineCount(bankInfo1.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo1.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				if(totalExtraLine < (extraLineCount(bankInfo2.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo2.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				if(totalExtraLine < (extraLineCount(bankInfo3.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo3.get(0),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				lineCount += totalExtraLine;
				
				// SWIFT Code Line
				totalExtraLine = 0;
				if(totalExtraLine < (extraLineCount(bankInfo1.get(4),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo1.get(4),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				if(totalExtraLine < (extraLineCount(bankInfo2.get(4),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo2.get(4),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				if(totalExtraLine < (extraLineCount(bankInfo3.get(4),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo3.get(4),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				lineCount += totalExtraLine;
				
				// Account No Line
				totalExtraLine = 0;
				if(totalExtraLine < (extraLineCount(bankInfo1.get(3),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo1.get(3),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				if(totalExtraLine < (extraLineCount(bankInfo2.get(3),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo2.get(3),BANKINFO_MAXIMUN_CHAR_PER_ROW);
				}
				if(totalExtraLine < (extraLineCount(bankInfo3.get(3),BANKINFO_MAXIMUN_CHAR_PER_ROW))) {
					totalExtraLine = extraLineCount(bankInfo3.get(3),BANKINFO_MAXIMUN_CHAR_PER_ROW);	
				}
				lineCount += totalExtraLine;
				
				// show all account
				List<String> accnoTemp1 = accAllMap.get("accNoOne");
				List<String> accno1 = getAccList(accnoTemp1);
				List<String> accnoTemp2 = accAllMap.get("accNoTwo");
				List<String> accno2 = getAccList(accnoTemp2);
				List<String> accnoTemp3 = accAllMap.get("accNoThree");
				List<String> accno3 = getAccList(accnoTemp3);
				int rows = getMaxSize(accno1.size(), accno2.size(),
						accno3.size());
				lineCount += rows;
				
			}
		}
		return lineCount;
	}
	
	private int getFeedbackCount() throws DocumentException {
		int lineCount = 0;
		
		if (feedbackStr != null && 0 < feedbackStr.length()) {
			if(feedbackStr.length() > FEEDBACK_MAXIMUN_CHAR_PER_ROW) {
				lineCount = lineCount + extraLineCount(feedbackStr,FEEDBACK_MAXIMUN_CHAR_PER_ROW);
			}
		}
		return lineCount;
	}
	
	private int getGlobalCount() throws DocumentException {
		int lineCount = 0;
		
		/*if (globalStr != null && 0 < globalStr.length()) {
			if(globalStr.length() > GLOBAL_MAXIMUN_CHAR_PER_ROW) {
				lineCount = lineCount + extraLineCount(globalStr,GLOBAL_MAXIMUN_CHAR_PER_ROW);
			}
		}*/
		
		return GLOBAL_MAXIMUN_CHAR_PER_ROW;
	}
	
	private int extraLineCount(String input, int maxLength) {
		double result = 0;
		try {
			Double inputLength = new Double(input.length());
			Double maxLengthCount = new Double(maxLength);
			result = Math.ceil(inputLength/maxLengthCount);
		}catch(Exception E) {
			return (int)result;
		}
		
		return (int)result;
		
	}
	
	private int getTaxCount() {
		// Scope of GST
		if(!this.billingType.equals("NT")){
			if(!this.defaultCurrency.equals(this.billCurrency)){
				if ("1".equals(oldFlag)) {
					return this.SCOPEINFO_PER_PAGE;
				}else if ("2".equals(oldFlag)) {
					return this.SCOPEINFO_FORMAT3_PER_PAGE;
				}
			}
		} else {
			if(!defaultCurrency.equals(billCurrency)){
				if ("1".equals(oldFlag)) {
					return this.SCOPEINFO1_PER_PAGE;
				}else if ("2".equals(oldFlag)) {
					return this.SCOPEINFO1_FORMAT3_PER_PAGE;
				}
			}
		}
		
		return 0;
	}
	
	private int getFooterInfoCount() {
		int lineCount = 0;
		List<Map<String, Object>> fInfosList = this.queryDAO.executeForMapList("B_BTH.getFooterInfo", null);
		if(!this.billingType.equals("CN")) {
			for(int i = 0 ; i < footerLineNo ; i++) {
				Map<String, Object> footInfo = fInfosList.get(i);
				String footerStr = CommonUtils.toString(footInfo.get("SET_VALUE")).trim();
				if (!CommonUtils.isEmpty(footerStr)) {
					lineCount++;
				}
			}
		}
		return lineCount;
	}
	
	// Fix extra Bank Line EN
	private List<String> getAccList(List<String> accnoTemp) {
		List<String> accno = new ArrayList<String>();
		for (String acc : accnoTemp) {
			if (acc.length() > 27) {
				int length = acc.length() % 27 == 0 ? acc.length() / 27 : acc
						.length() / 27 + 1;
				for (int i = 0; i < length; i++) {
					if (i + 1 >= length) {
						accno.add(acc.substring(27 * i));
					} else {
						accno.add(acc.substring(27 * i, 27 * (i + 1)));
					}
				}
			} else {
				accno.add(acc);
			}
		}
		return accno;
	}
    
    private List<String> getBankInfo(Map<String, Object> bankInfo){
        List<String> bankList = new ArrayList<String>();
        
        if(bankInfo!=null) {
            String bankName = CommonUtils.toString(bankInfo.get("BANK_NAME"));
            String branchName = CommonUtils.toString(bankInfo.get("BRANCH_NAME"));
            String acctName = CommonUtils.toString(bankInfo.get("COM_ACCT_NAME"));
            String acctNoAndCur = "";
            String acctNo = CommonUtils.toString(bankInfo.get("COM_ACCT_NO"));
            String cur = CommonUtils.toString(bankInfo.get("COM_CUR"));
            String swift = CommonUtils.toString(bankInfo.get("COM_SWIFT"));
            /*if (!CommonUtils.isEmpty(cur)) {
                acctNoAndCur = acctNo + "(" + cur+ ")";
            } else {*/
                acctNoAndCur = acctNo;
            //}
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
    
    private void setBankInfo(PdfPTable bankInfoTable, String colValue1, 
            String colValue2, String colValue3, 
            String colValue4, String colValue5, 
            String lineType) {
        PdfPCell bankCell = new PdfPCell(new Paragraph (colValue1, font09));
        bankCell.setBackgroundColor(BaseColor.WHITE);
        bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        bankCell.setBorder(PdfPCell.NO_BORDER);
        bankCell.setPaddingLeft(3);
        bankInfoTable.addCell(bankCell);
        
        bankCell = new PdfPCell(new Paragraph (colValue2, font09));
        bankCell.setBackgroundColor(BaseColor.WHITE);
        bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        bankCell.setBorder(PdfPCell.NO_BORDER);
        bankInfoTable.addCell(bankCell);
        
        bankCell = new PdfPCell(new Paragraph (colValue3, font09));
        bankCell.setBackgroundColor(BaseColor.WHITE);
        bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        bankCell.setBorder(PdfPCell.NO_BORDER);
        bankInfoTable.addCell(bankCell);
        
        bankCell = new PdfPCell(new Paragraph (colValue4, font09));
        bankCell.setBackgroundColor(BaseColor.WHITE);
        bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        bankCell.setBorder(PdfPCell.NO_BORDER);
        bankInfoTable.addCell(bankCell);
        
        bankCell = new PdfPCell(new Paragraph (colValue5, font09));
        bankCell.setBackgroundColor(BaseColor.WHITE);
        bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        bankCell.setBorder(PdfPCell.NO_BORDER);
        bankInfoTable.addCell(bankCell);
        
        /*bankCell = new PdfPCell(new Paragraph ("", font09));
        bankCell.setBackgroundColor(BaseColor.WHITE);
        bankCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        bankCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        bankCell.setBorder(PdfPCell.NO_BORDER);
        bankInfoTable.addCell(bankCell);*/

    }
    
    //get accno what is not set defalut
    private Map<String, List<String>> getAccNo(List<Map<String, Object>> firstBankFooterInfo, List<Map<String, Object>> bankFooterInfo){
        Map<String, List<String>> accnomap=new HashMap<String, List<String>>();
        List<String> acclist1=new ArrayList<String>();
        List<String> acclist2=new ArrayList<String>();
        List<String> acclist3=new ArrayList<String>();
        
        for (Map<String, Object> bank : bankFooterInfo) {
            String DISPLAY_ORDER = CommonUtils.toString(bank.get("DISPLAY_ORDER"));
            String DEFAULT_ACC = CommonUtils.toString(bank.get("DEFAULT_ACC"));
            String accno = CommonUtils.toString(bank.get("COM_ACCT_NO"));
            
            for(Map<String, Object> firstBank : firstBankFooterInfo) {
            	String firstAccno = CommonUtils.toString(firstBank.get("COM_ACCT_NO"));
            	String firstDisplayOrder = CommonUtils.toString(firstBank.get("DISPLAY_ORDER"));
                
            	if ("1".equals(DISPLAY_ORDER)) {
            		 if (firstDisplayOrder.equals(DISPLAY_ORDER)) {
	                     if (!firstAccno.equals(accno)) {
	                         acclist1.add(accno);
	                     }
            		 }
                 } else if ("2".equals(DISPLAY_ORDER)) {
                	 if (firstDisplayOrder.equals(DISPLAY_ORDER)) {
	                     if (!firstAccno.equals(accno)) {
	                         acclist2.add(accno);
	                     }
                	 }
                 }else if ("3".equals(DISPLAY_ORDER)) {
                	 if (firstDisplayOrder.equals(DISPLAY_ORDER)) {
	                	 if (!firstAccno.equals(accno)) {
	                         acclist3.add(accno);
	                     }
                	 }
                 }
            }
        }
        accnomap.put("accNoOne", acclist1);
        accnomap.put("accNoTwo", acclist2);
        accnomap.put("accNoThree", acclist3);
        
        return accnomap;
    }
    
	private int getMaxSize(int a, int b, int c) {
		int maxno = a >= b ? a : b;
		return maxno >= c ? maxno : c;
	}
    
}