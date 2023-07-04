package nttdm.bsys.r_rev.blogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class R_REV_R06BLogic {
	private QueryDAO queryDAO;
    private UpdateDAO updateDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	
	protected Integer doSummaryExcel(String periodFrom, String periodTo, String reportMonth) throws Exception{
		String filePath = queryDAO.executeForObject("R_REV.GET_FILE_PATH_REV05BL", null, String.class);
		Integer fileCount = 0;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("reportMonth", reportMonth);
		String todayDate = this.getPrintDate();
		List<Map<String, Object>> advanceList = queryDAO.executeForMapList("R_REV.GET_ADVANCE_REV06BL", param);
		List<Map<String, Object>> totalList = queryDAO.executeForMapList("R_REV.GET_TOTAL_REV06BL", param);
		//--- Get Template Start ---
		InputStream is = this.getClass().getResourceAsStream("/Template-Advance-Revenue.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
		is.close();
		XSSFSheet cellTemplateSheet = wb.getSheetAt(1);
		Map<String, Object> cellStyle = new HashMap<String, Object>(); //= exportTemplate(cellTemplateSheet);
		// get styleSheet
		this.GetStyleSheet(cellTemplateSheet,cellStyle);
		wb.removeSheetAt(1);
		//Clone First Sheet From Template
		XSSFSheet targetSheet = wb.cloneSheet(0);
		// First Row - Report Period Row
		XSSFRow reportPeriodRow = targetSheet.getRow(0);
		XSSFCell reportPeriodCell = reportPeriodRow.getCell(0);
		String reportPeriodText = reportPeriodCell.getStringCellValue();
		reportPeriodText = reportPeriodText.replace("{dateFrom}", this.formatDate(periodFrom));
		reportPeriodText = reportPeriodText.replace("{dateTo}",this.formatDate(periodTo));
	    reportPeriodCell.setCellValue(reportPeriodText);
		
		// Second Row - Category / Print Date Row
		XSSFRow categoryRow = targetSheet.getRow(1);
		XSSFCell printDateCell = categoryRow.getCell(34);
		String printDateText = printDateCell.getStringCellValue();
		printDateText = printDateText.replace("{printDate}", (String)todayDate);
		printDateCell.setCellValue(printDateText);
		
		
		// Fourth Row - Print Monthly Date Header
		XSSFRow columnDateRow = targetSheet.getRow(3);
		XSSFCell columnDateCell;
	    //ReportMonth same AC.FISCAL_MONTH
		SimpleDateFormat insdf = new SimpleDateFormat("yyyyMM");
		Date tempdDate=insdf.parse(reportMonth);
	    for(int i=1;i<13;i++){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(tempdDate);
	    	calendar.add(Calendar.MONTH, i);
			SimpleDateFormat outsdf = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
			columnDateCell = columnDateRow.getCell(20+i);
			columnDateCell.setCellValue(outsdf.format(calendar.getTime()));
		 }
		 XSSFRow ServiceRow;
         XSSFCell ServiceCell;
		 Integer count=0;
		 Double totalTemp =0.00;
		for(Map<String, Object> detlist : advanceList){	    
		    //Shift row by 1
			targetSheet.shiftRows(4+count, targetSheet.getLastRowNum(), 1, true, true);
			//category   
			ServiceRow = targetSheet.createRow(4 + count);
			ServiceCell = ServiceRow.createCell(0);		
			ServiceCell.setCellValue((String)detlist.get("SVC_GRP_NAME"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//service    
			ServiceCell = ServiceRow.createCell(1);		
			ServiceCell.setCellValue((String)detlist.get("PRODUCT_DESC"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//productCode    
			ServiceCell = ServiceRow.createCell(2);		
			ServiceCell.setCellValue((String)detlist.get("PRODUCT_CODE"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Department    
			ServiceCell = ServiceRow.createCell(3);		
			ServiceCell.setCellValue((String)detlist.get("DEPARTMENT"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Affiliate    
			ServiceCell = ServiceRow.createCell(4);		
			ServiceCell.setCellValue((String)detlist.get("AFFILIATE_CODE"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Project
			ServiceCell = ServiceRow.createCell(5);		
			ServiceCell.setCellValue((String)detlist.get("JOB_NO"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//name
			ServiceCell = ServiceRow.createCell(6);		
			ServiceCell.setCellValue((String)detlist.get("CUST_NAME"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Location
			ServiceCell = ServiceRow.createCell(7);		
			ServiceCell.setCellValue((String)detlist.get("LOCATION"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Category
			ServiceCell = ServiceRow.createCell(8);		
			ServiceCell.setCellValue((String)detlist.get("CO_CATEGORY"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Sub Category
			ServiceCell = ServiceRow.createCell(9);		
			ServiceCell.setCellValue((String)detlist.get("CO_SUB_CATEGORY"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Type
			ServiceCell = ServiceRow.createCell(10);		
			ServiceCell.setCellValue((String)detlist.get("CO_TYPE_S"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Service Start/End
			ServiceCell = ServiceRow.createCell(11);		
			ServiceCell.setCellValue(convertClobToString((Clob)detlist.get("SVC_START_END_S")));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Billing Instruction
			ServiceCell = ServiceRow.createCell(12);		
			ServiceCell.setCellValue(convertClobToString((Clob)detlist.get("BILL_INSTRUCT_S")));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//Rate Type
			ServiceCell = ServiceRow.createCell(13);		
			ServiceCell.setCellValue((String)detlist.get("RATE_TYPE2"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
			//plan
			ServiceCell = ServiceRow.createCell(14);		
			ServiceCell.setCellValue(convertClobToString((Clob)detlist.get("PLAN_S")));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell")); 
			//Billing Document
			ServiceCell = ServiceRow.createCell(15);		
			ServiceCell.setCellValue((String)detlist.get("ID_REF"));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell")); 
			//Billing Period 
			/*Ver3.04 #287 [MSC-LiveIssues] [G-REV-P06] Advance Revenue Report add new columns CT 20170925*/
			/*SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			String billFrom=sdf.format((Date)detlist.get("BILL_FROM"));
			String billTo=sdf.format((Date)detlist.get("BILL_TO"));
			ServiceCell = ServiceRow.createCell(16);		
			ServiceCell.setCellValue(billFrom+"-"+billTo);
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell")); */
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String billFrom=sdf.format((Date)detlist.get("BILL_FROM"));
			String billTo=sdf.format((Date)detlist.get("BILL_TO"));
			String billMonth = (String)detlist.get("BILL_MONTH");
			
			ServiceCell = ServiceRow.createCell(16);		
			ServiceCell.setCellValue(billMonth);
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell")); 
			
			ServiceCell = ServiceRow.createCell(17);		
			ServiceCell.setCellValue(billFrom);
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell")); 
			
			ServiceCell = ServiceRow.createCell(18);		
			ServiceCell.setCellValue(billTo);
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell")); 
			/*Ver3.04 #287 [MSC-LiveIssues] [G-REV-P06] Advance Revenue Report add new columns CT 20170925*/
			//Billing Amount
			ServiceCell = ServiceRow.createCell(19);		
			ServiceCell.setCellValue(Double.parseDouble(detlist.get("ORIGINAL_REV_AMOUNT").toString()));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));   
			//Advance Amount
			ServiceCell = ServiceRow.createCell(20);
			ServiceCell.setCellValue(Double.parseDouble(detlist.get("ADVANCE_AMOUNT").toString()));
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));   
			Double sumTemp =0.00;
			for (int j = 0; j < 13; j++) {
				String colNm = "AMOUNT" + j;
				ServiceCell = ServiceRow.createCell(21 + j);
				Double temp=Double.parseDouble(detlist.get(colNm).toString());
				ServiceCell.setCellValue(temp);
				sumTemp+=temp;
				ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle1"));
			}
			//total
			ServiceCell = ServiceRow.createCell(34);		
			ServiceCell.setCellValue(sumTemp);
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle1"));
			totalTemp+=sumTemp;
			count++;
		}
		//set totalRow
		this.SetAdvanceTotal(targetSheet, count, totalList, cellStyle, totalTemp);
	
		wb.removeSheetAt(0);
		// set sheet name 
		wb.setSheetName(0, "advance");
		
		// output to new Excel file
		String fileName = "Advance_Revenue_" + reportMonth + ".xlsx";
		OutputStream out = new FileOutputStream(
				filePath + File.separator + fileName);
		wb.write(out);
		out.close();
		//Insert File Information Into Table T_REV_R_DOC
		Map<String, Object> fileParam = new HashMap<String, Object>();
		fileParam.put("reportMonth", reportMonth);
	    //TODO
		fileParam.put("reportBy", "ADV1");
		fileParam.put("fileName", fileName);
		fileParam.put("fileLocation", filePath+File.separator);
		fileParam.put("idLogin", "sysadmin");
		queryDAO.executeForMap("R_REV.MERGE_FILE_DETAILS", fileParam);
		
		fileCount += 1;
		return fileCount;		
	}
     //Set cell style
	private void GetStyleSheet(XSSFSheet cellTemplateSheet,Map<String, Object> cellStyle)
	{
		//Style for currency in table
		XSSFRow tableCurrencyRow1 = cellTemplateSheet.getRow(0);
		XSSFCell tableCurrencyCell1 = tableCurrencyRow1.getCell(1);
		XSSFCellStyle tableCurrencyCellStyle1 = tableCurrencyCell1.getCellStyle();
		cellStyle.put("tableStyle1", tableCurrencyCellStyle1);
		
		//Style for currency in table with 0 value
		XSSFRow tableCurrencyRow2 = cellTemplateSheet.getRow(1);
		XSSFCell tableCurrencyCell2 = tableCurrencyRow2.getCell(1);
		XSSFCellStyle tableCurrencyCellStyle2 = tableCurrencyCell2.getCellStyle();
		cellStyle.put("tableStyle2", tableCurrencyCellStyle2);
		
		//Style for total sum up currency in table
		XSSFRow tableCurrencyRow3 = cellTemplateSheet.getRow(2);
		XSSFCell tableCurrencyCell3 = tableCurrencyRow3.getCell(1);
		XSSFCellStyle tableCurrencyCellStyle3 = tableCurrencyCell3.getCellStyle();
		cellStyle.put("tableStyle3", tableCurrencyCellStyle3);
		
		//Style for currency in bottom detail
		XSSFRow detailCurrencyRow1 = cellTemplateSheet.getRow(3);
		XSSFCell detailCurrencyCell1 = detailCurrencyRow1.getCell(1);
		XSSFCellStyle detailCurrencyCellStyle1 = detailCurrencyCell1.getCellStyle();
		cellStyle.put("detailStyle1", detailCurrencyCellStyle1);
		
		//Style for currency in bottom detail
		XSSFRow detailCurrencyRow2 = cellTemplateSheet.getRow(4);
		XSSFCell detailCurrencyCell2 = detailCurrencyRow2.getCell(1);
		XSSFCellStyle detailCurrencyCellStyle2 = detailCurrencyCell2.getCellStyle();
		cellStyle.put("detailStyle2", detailCurrencyCellStyle2);
		
		//Style for currency in bottom detail
		XSSFRow detailCurrencyRow3 = cellTemplateSheet.getRow(5);
		XSSFCell detailCurrencyCell3 = detailCurrencyRow3.getCell(1);
		XSSFCellStyle detailCurrencyCellStyle3 = detailCurrencyCell3.getCellStyle();
		cellStyle.put("detailStyle3", detailCurrencyCellStyle3);
		
		//Style for column with border
		XSSFRow borderRow = cellTemplateSheet.getRow(6);
		XSSFCell borderCell = borderRow.getCell(1);
		XSSFCellStyle borderCellStyle = borderCell.getCellStyle();
		cellStyle.put("borderCell", borderCellStyle);
		
		//Style for total with bottom double line
		XSSFRow overallTotalRow = cellTemplateSheet.getRow(7);
		XSSFCell overallTotalCell = overallTotalRow.getCell(1);
		XSSFCellStyle overallTotalCellStyle = overallTotalCell.getCellStyle();
		cellStyle.put("overallTotalStyle", overallTotalCellStyle);
		
		//Cell Style for 0 value
		XSSFRow value0 = cellTemplateSheet.getRow(8);
		XSSFCell value0Cell = value0.getCell(1);
		XSSFCellStyle value0Style = value0Cell.getCellStyle();
		cellStyle.put("value0Style", value0Style);
	}

	// set AmountTotal
	private void SetAdvanceTotal(XSSFSheet targetSheet,Integer  index,List<Map<String, Object>> totalList ,Map<String, Object> cellStyle,Double totalTemp)
	{
		XSSFRow totalRow = targetSheet.getRow(5+index);
		for(Map<String, Object> tlist : totalList)
		{
			//AMOUNT0 total
			XSSFCell totalCell0 = totalRow.getCell(21);
			totalCell0.setCellValue(Double.parseDouble(tlist.get("AMOUNT0").toString()));
			//AMOUNT1 total
			XSSFCell totalCell1 = totalRow.getCell(22);
			totalCell1.setCellValue(Double.parseDouble(tlist.get("AMOUNT1").toString()));
			//AMOUNT2 total
			XSSFCell totalCell2 = totalRow.getCell(23);
			totalCell2.setCellValue(Double.parseDouble(tlist.get("AMOUNT2").toString()));
			//AMOUNT3 total
			XSSFCell totalCell3 = totalRow.getCell(24);
			totalCell3.setCellValue(Double.parseDouble(tlist.get("AMOUNT3").toString()));
			//AMOUNT4 total
			XSSFCell totalCell4 = totalRow.getCell(25);
			totalCell4.setCellValue(Double.parseDouble(tlist.get("AMOUNT4").toString()));
			//AMOUNT5 total
			XSSFCell totalCell5 = totalRow.getCell(26);
			totalCell5.setCellValue(Double.parseDouble(tlist.get("AMOUNT5").toString()));
			//AMOUNT6 total
			XSSFCell totalCell6 = totalRow.getCell(27);
			totalCell6.setCellValue(Double.parseDouble(tlist.get("AMOUNT6").toString()));
			//AMOUNT7 total
			XSSFCell totalCell7 = totalRow.getCell(28);
			totalCell7.setCellValue(Double.parseDouble(tlist.get("AMOUNT7").toString()));
			//AMOUNT8 total
			XSSFCell totalCell8 = totalRow.getCell(29);
			totalCell8.setCellValue(Double.parseDouble(tlist.get("AMOUNT8").toString()));
			//AMOUNT9 total
			XSSFCell totalCell9 = totalRow.getCell(30);
			totalCell9.setCellValue(Double.parseDouble(tlist.get("AMOUNT9").toString()));
			//AMOUNT10 total
			XSSFCell totalCell10 = totalRow.getCell(31);
			totalCell10.setCellValue(Double.parseDouble(tlist.get("AMOUNT10").toString()));
			//AMOUNT11 total
			XSSFCell totalCell11 = totalRow.getCell(32);
			totalCell11.setCellValue(Double.parseDouble(tlist.get("AMOUNT11").toString()));
			//AMOUNT12 total
			XSSFCell totalCell12 = totalRow.getCell(33);
			totalCell12.setCellValue(Double.parseDouble(tlist.get("AMOUNT12").toString()));
		}
		//total 
		XSSFCell totalCell13 = totalRow.getCell(34);
		totalCell13.setCellValue(totalTemp);
	}

	//date format
	private String formatDate(String date) throws Exception {
		SimpleDateFormat insdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat outsdf = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
		Calendar c = Calendar.getInstance();
		c.setTime(insdf.parse(date));
		return outsdf.format(c.getTime());
	}
	
	private String getPrintDate() throws Exception {
		SimpleDateFormat outsdf = new SimpleDateFormat("dd-MMM-yyyy",
				Locale.ENGLISH);
		Calendar c = Calendar.getInstance();
		return outsdf.format(c.getTime());
	}
	
	/**
	 * 
	 * @param clob
	 * @return
	 */
	public String convertClobToString(Clob clob) {
		String toRet = "";
		if(clob!=null) {
			try {
				long length=clob.length();
				toRet=clob.getSubString(1, (int)length);          
			} catch(Exception ex) {
				ex.printStackTrace();
			}      
		}      
		return toRet; 
	}
}
