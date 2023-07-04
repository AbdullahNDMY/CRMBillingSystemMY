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
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_SET_P01;
import nttdm.bsys.r_rev.dto.R_REV_R02Input;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionMessages;

public class R_REV_R05BLogic {
	private QueryDAO queryDAO;
    private UpdateDAO updateDAO;
    // # RevenueReport TotalFormula calculate start from second column 01112017 CT
    private Integer columnBeforeDate = 15;
    private Integer dateColumn = 12;
    private Integer rowStartList = 4;
    // # RevenueReport TotalFormula calculate start from second column 01112017 CT

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
		param.put("fiscalStart", periodFrom);
		param.put("fiscalEnd", periodTo);
		
		String todayDate = this.getPrintDate();
		//Get R1
		List<Map<String, Object>> dR1List = queryDAO.executeForMapList("R_REV.GET_DETAILS_R1_REV05BL", param);
		List<Map<String, Object>> amountList = queryDAO.executeForMapList("R_REV.GET_AMOUNT_REV05BL", param);
		for(Map<String, Object> r1list : dR1List){
			String svcLevel1=r1list.get("SVC_LEVEL1").toString();
			String svcGrpName=(String)r1list.get("SVC_GRP_NAME");
			Map<String, Object> pramTemp = new HashMap<String, Object>();
			pramTemp.put("fiscalStart", periodFrom);
			pramTemp.put("fiscalEnd", periodTo);
			pramTemp.put("svcLevel", svcLevel1);
			//Get R2
			List<Map<String, Object>> listChecking = queryDAO.executeForMapList("R_REV.GET_DETAILS_R2_REV05BL", pramTemp);
			if(listChecking.size() > 0){
				//--- Get Template Start ---
				InputStream is = this.getClass().getResourceAsStream("/Template-Details-Final.xlsx");
				XSSFWorkbook wb = new XSSFWorkbook(is);
				is.close();
				// get Style
				XSSFSheet cellTemplateSheet = wb.getSheetAt(1);
				Map<String, Object> cellStyle = new HashMap<String, Object>(); 
				this.GetStyleSheet(cellTemplateSheet,cellStyle);
				wb.removeSheetAt(1);	
				
				//Clone First Sheet From Template
				XSSFSheet targetSheet = wb.cloneSheet(0);
				// Fourth Row - Print Monthly Date Header
				XSSFRow columnDateRow = targetSheet.getRow(3);
				XSSFCell columnDateCell;
			    Integer cellIndex=0;
				for(Map<String, Object> amlist : amountList){
					if(cellIndex==0){
						SimpleDateFormat insdf = new SimpleDateFormat("yyyyMM");
						Date tempdDate=insdf.parse((String)amlist.get("FISCAL_MONTH"));
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(tempdDate);
						calendar.add(Calendar.MONTH, -1);
						SimpleDateFormat outsdf = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
						columnDateCell = columnDateRow.getCell(15);
						columnDateCell.setCellValue(outsdf.format(calendar.getTime()));
						}
					columnDateCell = columnDateRow.getCell(16 + cellIndex);
					columnDateCell.setCellValue(this.formatDate((String)amlist.get("FISCAL_MONTH")));
					cellIndex++;
				}
		
				Map<String, Object> tempParam = new HashMap<String, Object>();
				tempParam.put("fiscalStart", periodFrom);
				tempParam.put("fiscalEnd", periodTo);
				tempParam.put("svcLevel", svcLevel1);
				//Get R2
				List<Map<String, Object>> dR2List = queryDAO.executeForMapList("R_REV.GET_DETAILS_R2_REV05BL", tempParam);
				List<Map<String, Object>> totalList = queryDAO.executeForMapList("R_REV.GET_TOTAL_REV05BL", tempParam);
				List<Map<String, Object>> summaryList = queryDAO.executeForMapList("R_REV.GET_SUMMARY_REV05BL", tempParam);
				Integer count=0;
				//Double totalTemp =0.00;
				for(Map<String, Object> r2list : dR2List)
				{
					 XSSFRow ServiceRow;
			         XSSFCell ServiceCell;
					 if(count==0){
							// First Row - Report Period Row
							XSSFRow reportPeriodRow = targetSheet.getRow(0);
							XSSFCell reportPeriodCell = reportPeriodRow.getCell(0);
							String reportPeriodText = reportPeriodCell.getStringCellValue();
							reportPeriodText = reportPeriodText.replace("{dateFrom}", this.formatDate(periodFrom));
							reportPeriodText = reportPeriodText.replace("{dateTo}",this.formatDate(periodTo));
						    reportPeriodCell.setCellValue(reportPeriodText);
							
							// Second Row - Category / Print Date Row
							XSSFRow categoryRow = targetSheet.getRow(1);
							XSSFCell printDateCell = categoryRow.getCell(28);
							String printDateText = printDateCell.getStringCellValue();
							printDateText = printDateText.replace("{printDate}", (String)todayDate);
							printDateCell.setCellValue(printDateText);
						}
				     	//Shift row by 1
						targetSheet.shiftRows(4+count, targetSheet.getLastRowNum(), 1, true, true);
						//category   
						ServiceRow = targetSheet.createRow(4 + count);
						ServiceCell = ServiceRow.createCell(0);		
						ServiceCell.setCellValue((String)r2list.get("SVC_GRP_NAME"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Product Code    
						ServiceCell = ServiceRow.createCell(1);		
						ServiceCell.setCellValue((String)r2list.get("PRODUCT_CODE"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//service    
						ServiceCell = ServiceRow.createCell(2);		
						ServiceCell.setCellValue((String)r2list.get("PRODUCT_DESC"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Project
						ServiceCell = ServiceRow.createCell(3);		
						ServiceCell.setCellValue((String)r2list.get("JOB_NO"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//name
						ServiceCell = ServiceRow.createCell(4);		
						ServiceCell.setCellValue((String)r2list.get("CUST_NAME"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Location
						ServiceCell = ServiceRow.createCell(5);		
						ServiceCell.setCellValue((String)r2list.get("LOCATION_S"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Category
						ServiceCell = ServiceRow.createCell(6);		
						ServiceCell.setCellValue((String)r2list.get("CO_CATEGORY_S"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Sub Category
						ServiceCell = ServiceRow.createCell(7);		
						ServiceCell.setCellValue((String)r2list.get("CO_SUB_CATEGORY_S"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Type
						ServiceCell = ServiceRow.createCell(8);		
						ServiceCell.setCellValue((String)r2list.get("CO_TYPE_S"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Service Start/End
						ServiceCell = ServiceRow.createCell(9);		
						ServiceCell.setCellValue(convertClobToString((Clob)r2list.get("SVC_START_END_S")));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//Billing Instruction
						ServiceCell = ServiceRow.createCell(10);		
						ServiceCell.setCellValue(convertClobToString((Clob)r2list.get("BILL_INSTRUCT_S")));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
						//plan
						ServiceCell = ServiceRow.createCell(11);		
						ServiceCell.setCellValue(convertClobToString((Clob)r2list.get("PLAN_S")));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));   
						//Billing Document
						ServiceCell = ServiceRow.createCell(12);		
						ServiceCell.setCellValue((String)r2list.get("ID_REF"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));   
						//Billing Month
						ServiceCell = ServiceRow.createCell(13);		
						ServiceCell.setCellValue((String)r2list.get("BILL_MONTH"));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));   
						//Amount
						ServiceCell = ServiceRow.createCell(14);
						ServiceCell.setCellValue(Double.parseDouble(r2list.get("ORIGINAL_REV_AMOUNT").toString()));
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));   
						Double sumTemp =0.00;
						for (int j = 0; j < 13; j++) {
							String colNm = "AMOUNT" + j;
							ServiceCell = ServiceRow.createCell(15 + j);
							Double temp=Double.parseDouble(r2list.get(colNm).toString());
							ServiceCell.setCellValue(temp);
							// # RevenueReport TotalFormula calculate start from second column 01112017 CT
							//sumTemp+=temp;
							// # RevenueReport TotalFormula calculate start from second column 01112017 CT
							ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle1"));
						}
						//total
						ServiceCell = ServiceRow.createCell(28);
						String cellFromH = CellReference.convertNumToColString(columnBeforeDate + 1) + (rowStartList + count + 1); 
						String cellToH = CellReference.convertNumToColString(columnBeforeDate + dateColumn) + (rowStartList + count + 1); 
						String formula = "SUM(" + cellFromH + ":" + cellToH + ")";
						ServiceCell.setCellFormula(formula);
						//ServiceCell.setCellValue(sumTemp);
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle1"));
						//totalTemp+=sumTemp;
						count++;
				}
				//set totalRow
				this.SetAmountTotal(targetSheet, count, totalList, cellStyle);
				
				//set summary 
				this.SetSummaryData(targetSheet,summaryList,count,cellStyle);
				
				wb.removeSheetAt(0);
				// set sheet name 
				wb.setSheetName(0, "details");
				
				// output to new Excel file
				String fileName = "Revenue_Report_details_" +svcGrpName+"_"+ reportMonth + ".xlsx";
				
				OutputStream out = new FileOutputStream(
						filePath + File.separator + fileName);
				wb.write(out);
				out.close();
				//Insert File Information Into Table T_REV_R_DOC
				Map<String, Object> fileParam = new HashMap<String, Object>();
				fileParam.put("reportMonth", reportMonth);
			
				fileParam.put("reportBy", "DTL");
				fileParam.put("fileName", fileName);
				fileParam.put("fileLocation", filePath+File.separator);
				fileParam.put("idLogin", "sysadmin");
				queryDAO.executeForMap("R_REV.MERGE_FILE_DETAILS", fileParam);
				fileCount += 1;
			}
		}		
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
	private void SetAmountTotal(XSSFSheet targetSheet,Integer  index,List<Map<String, Object>> totalList ,Map<String, Object> cellStyle)
	{
		XSSFRow totalRow = targetSheet.getRow(4+index);
		for(Map<String, Object> tlist : totalList)
		{
			//AMOUNT0 total
			XSSFCell totalCell0 = totalRow.getCell(15);
			totalCell0.setCellValue(Double.parseDouble(tlist.get("AMOUNT0").toString()));
			//AMOUNT1 total
			XSSFCell totalCell1 = totalRow.getCell(16);
			totalCell1.setCellValue(Double.parseDouble(tlist.get("AMOUNT1").toString()));
			//AMOUNT2 total
			XSSFCell totalCell2 = totalRow.getCell(17);
			totalCell2.setCellValue(Double.parseDouble(tlist.get("AMOUNT2").toString()));
			//AMOUNT3 total
			XSSFCell totalCell3 = totalRow.getCell(18);
			totalCell3.setCellValue(Double.parseDouble(tlist.get("AMOUNT3").toString()));
			//AMOUNT4 total
			XSSFCell totalCell4 = totalRow.getCell(19);
			totalCell4.setCellValue(Double.parseDouble(tlist.get("AMOUNT4").toString()));
			//AMOUNT5 total
			XSSFCell totalCell5 = totalRow.getCell(20);
			totalCell5.setCellValue(Double.parseDouble(tlist.get("AMOUNT5").toString()));
			//AMOUNT6 total
			XSSFCell totalCell6 = totalRow.getCell(21);
			totalCell6.setCellValue(Double.parseDouble(tlist.get("AMOUNT6").toString()));
			//AMOUNT7 total
			XSSFCell totalCell7 = totalRow.getCell(22);
			totalCell7.setCellValue(Double.parseDouble(tlist.get("AMOUNT7").toString()));
			//AMOUNT8 total
			XSSFCell totalCell8 = totalRow.getCell(23);
			totalCell8.setCellValue(Double.parseDouble(tlist.get("AMOUNT8").toString()));
			//AMOUNT9 total
			XSSFCell totalCell9 = totalRow.getCell(24);
			totalCell9.setCellValue(Double.parseDouble(tlist.get("AMOUNT9").toString()));
			//AMOUNT10 total
			XSSFCell totalCell10 = totalRow.getCell(25);
			totalCell10.setCellValue(Double.parseDouble(tlist.get("AMOUNT10").toString()));
			//AMOUNT11 total
			XSSFCell totalCell11 = totalRow.getCell(26);
			totalCell11.setCellValue(Double.parseDouble(tlist.get("AMOUNT11").toString()));
			//AMOUNT12 total
			XSSFCell totalCell12 = totalRow.getCell(27);
			totalCell12.setCellValue(Double.parseDouble(tlist.get("AMOUNT12").toString()));
		}
		//total 
		XSSFCell totalCell13 = totalRow.getCell(28);
		// # RevenueReport TotalFormula calculate start from second column 01112017 CT
		String cellFromForTotal = CellReference.convertNumToColString(columnBeforeDate + dateColumn + 1) + (rowStartList + 1); //D5
		String cellToForTotal = CellReference.convertNumToColString(columnBeforeDate + dateColumn + 1) + (rowStartList + index); //D7
		String formula = "SUM(" + cellFromForTotal + ":" + cellToForTotal + ")";
		totalCell13.setCellFormula(formula);
		// # RevenueReport TotalFormula calculate start from second column 01112017 CT
		//totalCell13.setCellValue(totalTemp);
	}

	//Set Summary Data
	private void SetSummaryData(XSSFSheet targetSheet,List<Map<String, Object>> summaryList,Integer  rowIndex,Map<String, Object> cellStyle)
	{
		 XSSFRow ServiceRow;
         XSSFCell ServiceCell;
         Integer startIndex=5+rowIndex;
         String beforeSummaryType="";
         String  summaryStr="";
		for(Map<String, Object> summlist : summaryList)
		{
			Double sumTemp =0.00;
			String dbSummaryType=(String)summlist.get("SUMMARY_TYPE");
			if(dbSummaryType.equals(beforeSummaryType)==false ){
				  startIndex+=2;
			 	  beforeSummaryType=dbSummaryType;
			  if(dbSummaryType.equals("CO_CATEGORY"))
			    {
				  summaryStr="Company Category";
			    }else if(dbSummaryType.equals("CO_SUB_CATEGORY"))
				{
				  summaryStr="Company Sub Category";
				}else if(dbSummaryType.equals("CO_TYPE"))
				{
				  summaryStr="Company Type";
			    }else if(dbSummaryType.equals("LOCATION"))
				{
				  summaryStr="Location";
				}
			}  
			else 
			{
				startIndex+=1; 
				summaryStr="";
			}
			//<SummaryType>
			ServiceRow = targetSheet.createRow(startIndex);
			ServiceCell = ServiceRow.createCell(12);	
			ServiceCell.setCellValue(summaryStr);
			//<ColDESC>
			ServiceCell = ServiceRow.createCell(13);		
			ServiceCell.setCellValue((String)summlist.get("COL_DESC"));
			
            //<SAmt0>
			ServiceCell = ServiceRow.createCell(15);
			Double temp0=Double.parseDouble(summlist.get("AMOUNT0").toString());
			ServiceCell.setCellValue(temp0);
			sumTemp+=temp0;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt1>
			ServiceCell = ServiceRow.createCell(16);
			Double  temp1=Double.parseDouble(summlist.get("AMOUNT1").toString());
			ServiceCell.setCellValue(temp1);
			sumTemp+=temp1;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt2>
			ServiceCell = ServiceRow.createCell(17);	
			Double temp2=Double.parseDouble(summlist.get("AMOUNT2").toString());
			ServiceCell.setCellValue(temp2);
			sumTemp+=temp2;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt3>
			ServiceCell = ServiceRow.createCell(18);	
			Double temp3=Double.parseDouble(summlist.get("AMOUNT3").toString());
			ServiceCell.setCellValue(temp3);	
			sumTemp+=temp3;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt4>
			ServiceCell = ServiceRow.createCell(19);	
			Double temp4=Double.parseDouble(summlist.get("AMOUNT4").toString());
			ServiceCell.setCellValue(temp4);
			sumTemp+=temp4;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt5>
			ServiceCell = ServiceRow.createCell(20);
			Double temp5=Double.parseDouble(summlist.get("AMOUNT5").toString());
			ServiceCell.setCellValue(temp5);
			sumTemp+=temp5;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt6>
			ServiceCell = ServiceRow.createCell(21);
			Double temp6=Double.parseDouble(summlist.get("AMOUNT6").toString());
			ServiceCell.setCellValue(temp6);
			sumTemp+=temp6;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt7>
			ServiceCell = ServiceRow.createCell(22);
			Double temp7=Double.parseDouble(summlist.get("AMOUNT7").toString());
			ServiceCell.setCellValue(temp7);
			sumTemp+=temp7;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt8>
			ServiceCell = ServiceRow.createCell(23);	
			Double temp8=Double.parseDouble(summlist.get("AMOUNT8").toString());
			ServiceCell.setCellValue(temp8);
			sumTemp+=temp8;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt9>
			ServiceCell = ServiceRow.createCell(24);	
			Double temp9=Double.parseDouble(summlist.get("AMOUNT9").toString());
			ServiceCell.setCellValue(temp9);
			sumTemp+=temp9;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt10>
			ServiceCell = ServiceRow.createCell(25);	
			Double temp10=Double.parseDouble(summlist.get("AMOUNT10").toString());
			ServiceCell.setCellValue(temp10);
			sumTemp+=temp10;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt11>
			ServiceCell = ServiceRow.createCell(26);	
			Double temp11=Double.parseDouble(summlist.get("AMOUNT11").toString());
			ServiceCell.setCellValue(temp11);
			sumTemp+=temp11;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt12>
			ServiceCell = ServiceRow.createCell(27);	
			Double temp12=Double.parseDouble(summlist.get("AMOUNT12").toString());
			ServiceCell.setCellValue(temp12);
			sumTemp+=temp12;
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
            //<SAmt1~12>
			ServiceCell = ServiceRow.createCell(28);
			// # RevenueReport TotalFormula calculate start from second column 01112017 CT
			String cellFromForTotal = CellReference.convertNumToColString(columnBeforeDate + 1) + (startIndex + 1); //D5
			String cellToForTotal = CellReference.convertNumToColString(columnBeforeDate + dateColumn ) + (startIndex + 1); //D7
			String formula = "SUM(" + cellFromForTotal + ":" + cellToForTotal + ")";
			ServiceCell.setCellFormula(formula);
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
			// # RevenueReport TotalFormula calculate start from second column 01112017 CT
			//ServiceCell.setCellValue(sumTemp);
			ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("value0Style"));
		}
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
