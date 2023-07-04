/**
 * @(#)R_REV_R02BLogic.java
 * Billing System
 * Version 3.03
 * Created 2016/09/30
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rev.blogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class.
 * 
 * @author binz
 */
public class R_REV_R02BLogic extends AbstractR_REV_R02BLogic {
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


	/**
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(R_REV_R02Input input) {

		BLogicResult result = new BLogicResult();		

		BLogicMessages msgs = new BLogicMessages();
        BLogicMessage msg = new BLogicMessage("info.ERR2SC060", new Object());
		
		// 2.1
		Integer fileCount = 0;
		String moduledID = "E";
		String reportMonth = this.getReportMonth(input.getGenerateReportYear(),
				input.getGenerateReportMonth());
		String userId = input.getUvo().getId_user();
		
		// 3.1
		T_SET_BATCH t_batch = new T_SET_BATCH();
		t_batch.setSTATUS("1");
		t_batch.setBATCH_TYPE("RR");
		t_batch.setFILENAME("Final Report " + reportMonth);
		
		// 3.2
		G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
		G_SET_ReturnValue returnValue = gsetp01.G_SET_P01_GetIdBatch(t_batch);
		
		// 3.3
		int batchId = returnValue.getBatch_id();
		
		// batch_id is -1
		if (batchId == -1) {
			//BLogicMessages msgs = new BLogicMessages();
			msg = new BLogicMessage("errors.ERR1SC112",
					new Object[] { returnValue.getRET_MSG() });
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			result.setErrors(msgs);
		}
		// batch_id is not -1
		else {
			// 4.1 Process Data
			try {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("REPORTMONTH", reportMonth.replace("/", ""));
				param.put("USERID", userId);
				param.put("RSLT_FLG", null);
				
				//Validation Checking
				String currentOpeningMonth = queryDAO.executeForObject("R_REV.GET_REPORT_MONTH_CHECKING", null, String.class);
				if(CommonUtils.isEmpty(currentOpeningMonth) || !(currentOpeningMonth.equals(reportMonth.replace("/", "")))){
		            msg = new BLogicMessage("info.ERR2SC059", new Object());
					throw new Exception("Invalid Report Month");
				}
				
				// Call Procedure
				this.executeFinalReport("R_REV.finalReportProcedure1", param);
				this.executeFinalReport("R_REV.finalReportProcedure2", param);
				this.executeFinalReport("R_REV.finalReportProcedure3", param);
				this.executeFinalReport("R_REV.finalReportProcedure4", param);
				this.executeFinalReport("R_REV.finalReportProcedure5", param);
				this.executeFinalReport("R_REV.finalReportProcedure6", param);
				
				Integer Ex_rsltFlg = 0;				
				Ex_rsltFlg = (Integer)this.doExcel(param.get("REPORTMONTH").toString());
				
				if(Ex_rsltFlg.equals(0))
					throw new Exception("Some error has been occured while generating excel file");
				else
					fileCount = (CommonUtils.isEmpty(Ex_rsltFlg)? 0 : Ex_rsltFlg);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				 t_batch.setSTATUS("3");
				 t_batch.setBATCH_TYPE("RR");
				 GlobalMessageResources msgResource = GlobalMessageResources
							.getInstance();
				 t_batch.setMessage(e.getMessage());
				 t_batch.setMessage(msgResource.getMessage("info.ERR2SC058",
							new Object[] {}).replace("{0}", (CommonUtils.isEmpty(e.getMessage())? "Kindly refer logs" : e.getMessage())));
				 t_batch.setID_BATCH(String.valueOf(batchId));
				 gsetp01.G_SET_P01_GetIdBatch(t_batch);
				 

	             msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
	             result.setMessages(msgs);
				 result.setResultString("success");
         		 return result;
			}
			
			// 4.2 ~ 4.3
			t_batch.setSTATUS("2");
			GlobalMessageResources msgResource = GlobalMessageResources
					.getInstance();
			t_batch.setMessage(msgResource.getMessage("info.ERR2SC057",
					new Object[] {}).replace("{0}", fileCount.toString()));
			t_batch.setID_BATCH(String.valueOf(batchId));
			t_batch.setBATCH_TYPE("RR");
			// 5.2
			gsetp01.G_SET_P01_GetIdBatch(t_batch);
		}
        
		msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
        result.setMessages(msgs);
		result.setResultString("success");
		return result;
	}
	
	private void executeFinalReport(String spName, Map<String, Object> param) throws Exception{
		param.put("RSLT_FLG", null);
		queryDAO.executeForMap(spName, param);
		Integer rsltFlg = Integer.parseInt(param.get("RSLT_FLG").toString());
		
		if(rsltFlg <= 0)
			throw new Exception("Some error has been occured during processing " + spName + ", step" + rsltFlg.toString());
	}
	
	
	private String getReportMonth(String year, String month) {
		String rslt = "";
		SimpleDateFormat inSDF = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyy/MM");
		try {
			rslt = outSDF.format(inSDF.parse(year + month));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rslt;
	}
	
	private Integer doExcel(String reportMonth){
		Integer fileGenerated = 0;
		
		try {
			
			Map<String, Object> fiscalResult = new HashMap<String, Object>();
			fiscalResult = queryDAO.executeForMap("R_REV.GET_PERIOD_START_END", reportMonth);
			
			String periodFrom = (String)fiscalResult.get("FISCAL_START");//"2015/04";
			String periodTo = (String)fiscalResult.get("FISCAL_END");//"2015/09";
			
			//Category Report
			//R_REV_R02BLogic rev02 = new R_REV_R02BLogic();
			//rev02.setQueryDAO(this.queryDAO);
			//rev02.setUpdateDAO(this.updateDAO);			
			//fileGenerated = (Integer)rev02.doSummaryExcel(periodFrom, periodTo, reportMonth);
			fileGenerated = this.doSummaryExcel(periodFrom, periodTo, reportMonth);

			//Consolidation Report
			R_REV_R04BLogic rev04 = new R_REV_R04BLogic();
			rev04.setQueryDAO(this.queryDAO);
			rev04.setUpdateDAO(this.updateDAO);
			Map<String, Object> m_rev04 = this.queryDAO.executeForMap("R_REV.GET_REGEN_FISCAL_YEAR_REV04BL", reportMonth);
			fileGenerated += (Integer)rev04.doSummaryExcel(m_rev04.get("FISCAL_START").toString(), m_rev04.get("FISCAL_END").toString(), reportMonth);
			
			//Details Report
			R_REV_R05BLogic rev05 = new R_REV_R05BLogic();
			rev05.setQueryDAO(this.queryDAO);
			rev05.setUpdateDAO(this.updateDAO);
			Map<String, Object> m_rev05 = this.queryDAO.executeForMap("R_REV.GET_REGEN_FISCAL_YEAR_REV05BL", reportMonth);
			fileGenerated += (Integer)rev05.doSummaryExcel(m_rev05.get("FISCAL_START").toString(), m_rev05.get("FISCAL_END").toString(), reportMonth);
			
			//Advance Revenue Report
			R_REV_R06BLogic rev06 = new R_REV_R06BLogic();
			rev06.setQueryDAO(this.queryDAO);
			rev06.setUpdateDAO(this.updateDAO);
			Map<String, Object> m_rev06 = this.queryDAO.executeForMap("R_REV.GET_REGEN_FISCAL_YEAR_REV05BL", reportMonth);
			fileGenerated += (Integer)rev06.doSummaryExcel(m_rev06.get("FISCAL_START").toString(), m_rev06.get("FISCAL_END").toString(), reportMonth);
			
			//Advance Revenue Upload File
			R_REV_R07BLogic rev07 = new R_REV_R07BLogic();
			rev07.setQueryDAO(this.queryDAO);
			rev07.setUpdateDAO(this.updateDAO);
			fileGenerated += (Integer)rev07.doAdvanceTxT(reportMonth);

		} catch (Exception e) {
			e.printStackTrace();
			return fileGenerated;
		}
		
		return fileGenerated;
	}
	
	protected Integer doSummaryExcel(String periodFrom, String periodTo, String reportMonth) throws Exception{
		
		String filePath = queryDAO.executeForObject("R_REV.GET_FILE_PATH", null, String.class);
		
		Integer fileCount = 0;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fiscalStart", periodFrom);
		param.put("fiscalEnd", periodTo);
		
		String todayDate = this.getPrintDate();
		String cellFromH = "";
		String cellToH = "";
		String cellFromV = "";
		String cellToV = "";
		List<String> monthlyColums = this.getMonthSpace(periodFrom, periodTo);
		
		List<Map<String, Object>> productList = queryDAO.executeForMapList("R_REV.GET_SUMMARY_PRODUCT", param);
		
		//Loop Through Product Svc Grp Name
		for(Map<String, Object> prodList : productList){			

			List<String> summProductCode = new ArrayList<String>();
			List<String> summSvcLevel = new ArrayList<String>();
			
			String prodName = (String)prodList.get("SVC_GRP_NAME");
			String svcLevel1 = prodList.get("SVC_LEVEL1").toString();
			Map<String, Object> pramTemp = new HashMap<String, Object>();
			pramTemp.put("fiscalStart", periodFrom);
			pramTemp.put("fiscalEnd", periodTo);
			pramTemp.put("svcLevel1", svcLevel1);
			List<Map<String, Object>> listChecking = queryDAO.executeForMapList("R_REV.GET_PROD_SVC_AMOUNT", pramTemp);
			if(listChecking.size() > 0){
				//InputStream is = new FileInputStream(filePath + File.separator + templateFileName);
				InputStream is = this.getClass().getResourceAsStream("/Template_Summary-Final.xlsx");
				XSSFWorkbook wb = new XSSFWorkbook(is);
				is.close();
				
				//--- Get Template Start ---
				XSSFSheet cellTemplateSheet = wb.getSheetAt(2);
				Map<String, Object> cellStyle = new HashMap<String, Object>(); //= exportTemplate(cellTemplateSheet);
				
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
				
				wb.removeSheetAt(2);
				//--- Get Template End ---
				
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
				XSSFCell categoryCell = categoryRow.getCell(0);
				String categoryText = categoryCell.getStringCellValue();
				categoryText = categoryText.replace("{category}", (String)prodName);
				categoryCell.setCellValue(categoryText);
				XSSFCell printDateCell = categoryRow.getCell(16);
				String printDateText = printDateCell.getStringCellValue();
				printDateText = printDateText.replace("{printDate}", (String)todayDate);
				printDateCell.setCellValue(printDateText);
				
				// Fourth Row - Print Monthly Date Value
				XSSFRow columnDateRow = targetSheet.getRow(3);
				XSSFCell columnDateCell;
				for (int j = 0; j < monthlyColums.size(); j++) {
					columnDateCell = columnDateRow.getCell(3 + j);
					columnDateCell.setCellValue(formatDate(monthlyColums.get(j)));				
				}
				
				// Product Services Monthly Amount (Horizontal)
				Map<String, Object> svcParam = new HashMap<String, Object>();
				svcParam.put("fiscalStart", periodFrom);
				svcParam.put("fiscalEnd", periodTo);
				svcParam.put("prodName", prodName);
				svcParam.put("svcLevel1", svcLevel1);
				Integer nextRowNum = 4;
				Integer i = 0;
				XSSFRow ServiceRow;
				XSSFCell ServiceCell;
				List<Map<String, Object>> serviceAmounttList = queryDAO.executeForMapList("R_REV.GET_PROD_SVC_AMOUNT", svcParam);
				for(Map<String, Object> svcAmtList : serviceAmounttList){
					
					//For Product Service Summary Detail Parameter Usage
					summProductCode.add(svcAmtList.get("PRODUCT_CODE").toString());
					summSvcLevel.add(svcAmtList.get("SVC_LEVEL1").toString());
					
					//Shift row by 1
					targetSheet.shiftRows(4+i, targetSheet.getLastRowNum(), 1, true, true);
					
					ServiceRow = targetSheet.createRow(4 + i);
					ServiceCell = ServiceRow.createCell(0);				
					ServiceCell.setCellValue((String)svcAmtList.get("PRODUCT_CODE"));
					ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
					
					ServiceCell = ServiceRow.createCell(1);				
					ServiceCell.setCellValue((String)svcAmtList.get("PRODUCT_DESC"));
					ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
	
					//ServiceCell = ServiceRow.createCell(1);		
					//ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
					ServiceCell = ServiceRow.createCell(2);
					ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
					
					cellFromH = "";
					cellToH = "";
					
					for (int j = 0; j < monthlyColums.size(); j++) {
						String colNm = "AMOUNT" + j;
						ServiceCell = ServiceRow.createCell(3 + j);
						if(svcAmtList.get(colNm) != null){
							ServiceCell.setCellValue(Double.parseDouble(svcAmtList.get(colNm).toString()));
							ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle1"));
						}
						else{
							ServiceCell.setCellValue(0);
							ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle2"));
						}
						
						if(j == 0){ //Row First Amount Column
							// # RevenueReport TotalFormula calculate start from second column 01112017 CT
							cellFromH = CellReference.convertNumToColString(3+j+1) + (4+i+1); //D3
							// # RevenueReport TotalFormula calculate start from second column 01112017 CT
						}
						
						if(j+1 == monthlyColums.size()){ //Row Last Amount Column
							cellToH = CellReference.convertNumToColString(3 + monthlyColums.size() - 1) + (4+i+1); //P3
							ServiceCell = ServiceRow.createCell(3 + j+1);
							String formula = "SUM(" + cellFromH + ":" + cellToH + ")";
							ServiceCell.setCellFormula(formula);
							ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle3"));
						}						
					}
					
					//Do Services Row
					Map<String, Object> subSvcParamTmp = new HashMap<String, Object>();
					subSvcParamTmp.put("fiscalStart", periodFrom);
					subSvcParamTmp.put("fiscalEnd", periodTo);
					subSvcParamTmp.put("prodName", prodName);
					subSvcParamTmp.put("prodCode", (String)svcAmtList.get("PRODUCT_CODE"));
					subSvcParamTmp.put("svcLevel1", svcAmtList.get("SVC_LEVEL1").toString());
					List<Map<String, Object>> subSvcDetailList = queryDAO.executeForMapList("R_REV.GET_SUMMARY_SERVICE_DETAILS", subSvcParamTmp);
					if(subSvcDetailList.size() > 0){
					XSSFSheet targetSheetSub = wb.cloneSheet(1);
						this.doExportService(periodFrom, periodTo, todayDate, prodName, (String)svcAmtList.get("PRODUCT_CODE"), (String)svcAmtList.get("PRODUCT_DESC"), svcAmtList.get("SVC_LEVEL1").toString(), monthlyColums, targetSheetSub, cellStyle, subSvcDetailList);				
						
						String svcSheetName = (!CommonUtils.isEmpty(svcAmtList.get("PRODUCT_DESC"))? svcAmtList.get("PRODUCT_DESC").toString().trim() : svcAmtList.get("PRODUCT_CODE").toString());
						svcSheetName = svcSheetName.replaceAll("[\\\\/:*?\'\\[\\]]", "_");
						if(svcSheetName.length() > 25) svcSheetName = svcSheetName.substring(0,25);
						
						wb.setSheetName(wb.getSheetIndex(targetSheetSub), svcSheetName);
						//wb.setSheetName(3+i, (String)svcAmtList.get("PRODUCT_CODE"));
					}
					
					i += 1;
				}
				wb.removeSheetAt(1);
				
				// Product Services Monthly Sum Up Amount (Vertical)
				nextRowNum = nextRowNum + i + 1;
				cellFromH = "";
				cellToH = "";
				cellFromV = "";
				cellToV = "";
				for (int j = 0; j < monthlyColums.size(); j++) {
					ServiceRow = targetSheet.getRow(nextRowNum);
					ServiceCell = ServiceRow.getCell(3+j);
					cellFromV = CellReference.convertNumToColString(3+j) + (4+1); //D5
					cellToV = CellReference.convertNumToColString(3+j) + (nextRowNum-1); //D7
					String formula = "SUM(" + cellFromV + ":" + cellToV + ")";
					ServiceCell.setCellFormula(formula);
					ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("overallTotalStyle"));
					
					if(j == 0){ //Row First Amount Column
						// # RevenueReport TotalFormula calculate start from second column 01112017 CT
						cellFromH = CellReference.convertNumToColString(3+j+1) + (nextRowNum+1); //D9
						// # RevenueReport TotalFormula calculate start from second column 01112017 CT
					}
					
					if(j+1 == monthlyColums.size()){ //Row Last Amount Column
						cellToH = CellReference.convertNumToColString(3 + monthlyColums.size() - 1) + (nextRowNum+1); //Q9
						ServiceCell = ServiceRow.getCell(3+j+1);
						String vFormula = "SUM(" + cellFromH + ":" + cellToH + ")";
						ServiceCell.setCellFormula(vFormula);
						ServiceCell.setCellStyle((XSSFCellStyle)cellStyle.get("overallTotalStyle"));
					}
				}
				
				// Product Service Summary Detail
				nextRowNum = nextRowNum + 1;
				List<Map<String, Object>> summaryList = queryDAO.executeForMapList("R_REV.GET_SUMMARY_TYPE", param);
				for(Map<String, Object> summList : summaryList){
					Map<String, Object> summaryParam = new HashMap<String, Object>();
					summaryParam.put("fiscalStart", periodFrom);
					summaryParam.put("fiscalEnd", periodTo);
					summaryParam.put("summaryType", (String)summList.get("SUMMARY_TYPE"));
					summaryParam.put("prodCode", summProductCode);
					summaryParam.put("svcLevel", summSvcLevel);
					
					nextRowNum = nextRowNum + 1;
					Integer w = 0;
					XSSFRow locationRow;
					XSSFCell locationCell;
					
					if(!CommonUtils.isEmpty(summSvcLevel)){				
						List<Map<String, Object>> summDetailList = queryDAO.executeForMapList("R_REV.GET_MAIN_SUMMARY_DETAIL_BY_TYPE", summaryParam);
						for(Map<String, Object> summDetList : summDetailList){
							if(summDetList.get("SUMMARY_TYPE") != null){
								cellFromH = "";
								cellToH = "";
								locationRow = targetSheet.createRow(nextRowNum+w);
								
								if(w == 0){ //First Row Title
									locationCell = locationRow.createCell(0);
									locationCell.setCellValue(summDetList.get("SUMMARY_TYPE2").toString());
								}
								
								locationCell = locationRow.createCell(1);				
								locationCell.setCellValue((!CommonUtils.isEmpty(summDetList.get("COL_DESC")))? summDetList.get("COL_DESC").toString() : "-");
								
								for (int j = 0; j < monthlyColums.size(); j++) {
									String colNm = "AMOUNT" + j;
									locationCell = locationRow.createCell(3+j);
									if(summDetList.get(colNm) != null){
										locationCell.setCellValue(Double.parseDouble(summDetList.get(colNm).toString()));
										locationCell.setCellStyle((XSSFCellStyle)cellStyle.get("detailStyle1"));
		
									}
									else{
										locationCell.setCellValue(0);
										locationCell.setCellStyle((XSSFCellStyle)cellStyle.get("detailStyle2"));
									}
									
									if(j == 0){ //First Row For Sum
										// # RevenueReport TotalFormula calculate start from second column 01112017 CT
										cellFromH = CellReference.convertNumToColString(3 + j + 1) + (nextRowNum+w+1); //D14
										// # RevenueReport TotalFormula calculate start from second column 01112017 CT
									}
									
									if(j+1 == monthlyColums.size()){ //Row Last Amount Column
										cellToH = CellReference.convertNumToColString(3 + monthlyColums.size() - 1) + (nextRowNum+w+1); //P14
										locationCell = locationRow.createCell(3+j+1);
										String vFormula = "SUM(" + cellFromH + ":" + cellToH + ")";
										locationCell.setCellFormula(vFormula);
										locationCell.setCellStyle((XSSFCellStyle)cellStyle.get("detailStyle3"));
									}
								}		
								w += 1;
							}
						}
						nextRowNum = nextRowNum + w;
					}				
				}			
				
				// delete the Template sheet
				wb.removeSheetAt(0);
				// set sheet name
				wb.setSheetName(0, "Summary - " + prodName);
				
				// output to new Excel file
				String fileName = "Revenue_Report_" + prodName + "_" + reportMonth + ".xlsx";
				OutputStream out = new FileOutputStream(
						filePath + File.separator + fileName);
				wb.write(out);
				out.close();
				
				//Insert File Information Into Table T_REV_R_DOC
				Map<String, Object> fileParam = new HashMap<String, Object>();
				fileParam.put("reportMonth", reportMonth);
				fileParam.put("reportBy", "CAT");
				fileParam.put("fileName", fileName);
				fileParam.put("fileLocation", filePath+File.separator);
				fileParam.put("idLogin", "sysadmin");
				queryDAO.executeForMap("R_REV.MERGE_FILE_DETAILS", fileParam);
				
				fileCount += 1;
			}
		}
		return fileCount;		
	}
	
	private void doExportService(String periodFrom, String periodTo, String todayDate, String prodName, String product_code, String product_desc, String svc_level, List<String> monthlyColums, XSSFSheet targetSheetSub, Map<String, Object> cellStyle, List<Map<String, Object>> subSvcDetailList) throws Exception {
		
		Map<String, Object> subSvcParam = new HashMap<String, Object>();
		subSvcParam.put("fiscalStart", periodFrom);
		subSvcParam.put("fiscalEnd", periodTo);
		subSvcParam.put("prodName", prodName);
		subSvcParam.put("prodCode", product_code);		

		//For Product Sub Service Parameter Usage
		List<String> summProductCode = new ArrayList<String>();
		summProductCode.add(product_code);
		List<String> summSvcLevel = new ArrayList<String>();
		summSvcLevel.add(svc_level);
		
		String cellFromH = "";
		String cellToH = "";
		String cellFromV = "";
		String cellToV = "";
		
		List<Map<String, Object>> serviceDetailList = subSvcDetailList;
		//List<Map<String, Object>> serviceDetailList = queryDAO.executeForMapList("R_REV.GET_SUMMARY_SERVICE_DETAILS", subSvcParam);
		
		if(serviceDetailList.size() > 0 && serviceDetailList != null)
		{		
			//First Row Report Period Date
			XSSFRow rptPeriodRow = targetSheetSub.getRow(0);
			XSSFCell rptPeriodCell = rptPeriodRow.getCell(0);
			String rptPeriodTxt = rptPeriodCell.getStringCellValue();
			rptPeriodTxt = rptPeriodTxt.replace("{dateFrom}", this.formatDate(periodFrom));
			rptPeriodTxt = rptPeriodTxt.replace("{dateTo}",this.formatDate(periodTo));
			rptPeriodCell.setCellValue(rptPeriodTxt);
			
			//Second Row Category / Service
			XSSFRow categoryRow = targetSheetSub.getRow(1);
			XSSFCell categoryCell = categoryRow.getCell(0);
			String catSvcTxt = categoryCell.getStringCellValue();
			catSvcTxt = catSvcTxt.replace("{category}", prodName);
			catSvcTxt = catSvcTxt.replace("{productDesc}", (!CommonUtils.isEmpty(product_desc)? product_desc : product_code));
			categoryCell.setCellValue(catSvcTxt);
			XSSFCell dtTodayCell = categoryRow.getCell(22);
			String dtTodayTxt = dtTodayCell.getStringCellValue();
			dtTodayTxt = dtTodayTxt.replace("{todayDate}", todayDate);
			dtTodayCell.setCellValue(dtTodayTxt);
			
			//Third Row - Product Code Value
			XSSFRow prodCodeRow = targetSheetSub.getRow(2);
			XSSFCell prodCodeCell = prodCodeRow.getCell(0);
			String prodCodeTxt = prodCodeCell.getStringCellValue();
			prodCodeTxt = prodCodeTxt.replace("{productCode}", product_code);
			prodCodeCell.setCellValue(prodCodeTxt);
			
			//Fourth Row - Print Monthly Date Value
			XSSFRow colDateRow = targetSheetSub.getRow(3);
			XSSFCell colDateCell;
			for (int j = 0; j < monthlyColums.size(); j++) {
				colDateCell = colDateRow.getCell(9 + j);
				colDateCell.setCellValue(formatDate(monthlyColums.get(j)));				
			}
			
			//Service Detail Row
			Integer i = 0;
			Integer nextRowNum = 4;		
			
			for(Map<String, Object> svcDetailList : serviceDetailList){
				
				// Shift Down 1 Row
				targetSheetSub.shiftRows(4+i, targetSheetSub.getLastRowNum(), 1, true, true);
				
				XSSFRow svcItemRow = targetSheetSub.createRow(4 + i);
				
				String projectNm = (svcDetailList.get("PROJECT_NAME")!= null) ? svcDetailList.get("PROJECT_NAME").toString() : "";
				String custNm = (svcDetailList.get("CUST_NAME")!= null) ? svcDetailList.get("CUST_NAME").toString() : "";
				String location = (svcDetailList.get("LOCATION")!= null) ? svcDetailList.get("LOCATION").toString() : "";
				String coCategory = (svcDetailList.get("CO_CATEGORY")!= null) ? svcDetailList.get("CO_CATEGORY").toString() : "";
				String coSubCategory = (svcDetailList.get("CO_SUB_CATEGORY")!= null) ? svcDetailList.get("CO_SUB_CATEGORY").toString() : "";
				String coType = (svcDetailList.get("CO_TYPE")!= null) ? svcDetailList.get("CO_TYPE").toString() : "";
				String serviceType = (svcDetailList.get("SERVICE_DURATION")!= null) ? convertClobToString((Clob)svcDetailList.get("SERVICE_DURATION")) : "";
				String billInstruction = (svcDetailList.get("BILL_INSTRUCT")!= null) ? convertClobToString((Clob)svcDetailList.get("BILL_INSTRUCT")) : "";
				String plan = (svcDetailList.get("PLAN")!= null) ? convertClobToString((Clob)svcDetailList.get("PLAN")) : "";
				
				//Project Code
				XSSFCell svcItemCell = svcItemRow.createCell(0);				
				svcItemCell.setCellValue(projectNm);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
				
				//Customer Name
				svcItemCell = svcItemRow.createCell(1);				
				svcItemCell.setCellValue(custNm);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
				
				//Location
				svcItemCell = svcItemRow.createCell(2);				
				svcItemCell.setCellValue(location);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
	
				//Co. Categroy
				svcItemCell = svcItemRow.createCell(3);				
				svcItemCell.setCellValue(coCategory);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
	
				//Co. Sub Category
				svcItemCell = svcItemRow.createCell(4);				
				svcItemCell.setCellValue(coSubCategory);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
	
				//Company Type
				svcItemCell = svcItemRow.createCell(5);				
				svcItemCell.setCellValue(coType);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
	
				//Service Type
				svcItemCell = svcItemRow.createCell(6);				
				svcItemCell.setCellValue(serviceType);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
	
				//Bill Instruction
				svcItemCell = svcItemRow.createCell(7);				
				svcItemCell.setCellValue(billInstruction);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
	
				//Plan
				svcItemCell = svcItemRow.createCell(8);				
				svcItemCell.setCellValue(plan);
				svcItemCell.setCellStyle((XSSFCellStyle)cellStyle.get("borderCell"));
				
				for (int j = 0; j < monthlyColums.size(); j++) {
					String colNm = "AMOUNT" + j;
					XSSFCell itemAmtCell = svcItemRow.createCell(9+j);
					if(svcDetailList.get(colNm) != null){
						itemAmtCell.setCellValue(Double.parseDouble(svcDetailList.get(colNm).toString()));
						itemAmtCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle1"));
					}
					else{
						itemAmtCell.setCellValue(0);
						itemAmtCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle2"));
					}
					
					if(j == 0){ //First Row For Sum
						// # RevenueReport TotalFormula calculate start from second column 01112017 CT
						cellFromH = CellReference.convertNumToColString(9 + j + 1) + (4+i+1); //D9
						// # RevenueReport TotalFormula calculate start from second column 01112017 CT
					}
					
					if(j+1 == monthlyColums.size()){ //Row Last Amount Column
						cellToH = CellReference.convertNumToColString(9 + monthlyColums.size() - 1) + (4+i+1); //V9
						itemAmtCell = svcItemRow.createCell(9+j+1);
						String vFormula = "SUM(" + cellFromH + ":" + cellToH + ")";
						itemAmtCell.setCellFormula(vFormula);
						itemAmtCell.setCellStyle((XSSFCellStyle)cellStyle.get("tableStyle3"));
					}
				}
				i = i + 1;
			}
			
			//Service Detail Sum Total(Vertical)
			nextRowNum = nextRowNum + i + 1;
			cellFromH = "";
			cellToH = "";
			cellFromV = "";
			cellToV = "";		
			XSSFRow svcItemAmtRow = targetSheetSub.getRow(nextRowNum);;
			XSSFCell svcItemAmtCell;
			for (int j = 0; j < monthlyColums.size(); j++) {
				svcItemAmtCell = svcItemAmtRow.getCell(9+j);
				cellFromV = CellReference.convertNumToColString(9+j) + (4+1); //D5
				cellToV = CellReference.convertNumToColString(9+j) + (nextRowNum-1); //D7
				String formula = "SUM(" + cellFromV + ":" + cellToV + ")";
				svcItemAmtCell.setCellFormula(formula);
				svcItemAmtCell.setCellStyle((XSSFCellStyle)cellStyle.get("overallTotalStyle"));
		
				if(j == 0){ //Row First Amount Column
					// # RevenueReport TotalFormula calculate start from second column 01112017 CT
					cellFromH = CellReference.convertNumToColString(9+j+1) + (nextRowNum+1); //D9
					// # RevenueReport TotalFormula calculate start from second column 01112017 CT
				}
				
				if(j+1 == monthlyColums.size()){ //Row Last Amount Column
					cellToH = CellReference.convertNumToColString(9 + monthlyColums.size() - 1) + (nextRowNum+1); //Q9
					svcItemAmtCell = svcItemAmtRow.getCell(9+j+1);
					String vFormula = "SUM(" + cellFromH + ":" + cellToH + ")";
					svcItemAmtCell.setCellFormula(vFormula);
					svcItemAmtCell.setCellStyle((XSSFCellStyle)cellStyle.get("overallTotalStyle"));
				}
			}
			
			// Product Sub Service Summary Detail
			nextRowNum = nextRowNum + 1;
			List<Map<String, Object>> subSummaryList = queryDAO.executeForMapList("R_REV.GET_SUMMARY_TYPE", subSvcParam);
			for(Map<String, Object> subSummList : subSummaryList){
				Map<String, Object> subSummaryParam = new HashMap<String, Object>();
				subSummaryParam.put("fiscalStart", periodFrom);
				subSummaryParam.put("fiscalEnd", periodTo);
				subSummaryParam.put("summaryType", (String)subSummList.get("SUMMARY_TYPE"));
				subSummaryParam.put("prodCode", summProductCode);
				subSummaryParam.put("svcLevel", summSvcLevel);
				
				nextRowNum = nextRowNum + 1;
				Integer w = 0;
				XSSFRow locationRow;
				XSSFCell locationCell;
				
				List<Map<String, Object>> subSummDetailList = queryDAO.executeForMapList("R_REV.GET_SUMMARY_DETAIL_BY_TYPE", subSummaryParam);
				for(Map<String, Object> subSummDetList : subSummDetailList){
					if(subSummDetList.get("SUMMARY_TYPE") != null){
						cellFromH = "";
						cellToH = "";
						locationRow = targetSheetSub.createRow(nextRowNum+w);
						
						if(w == 0){ //First Row Title
							locationCell = locationRow.createCell(6);
							locationCell.setCellValue(subSummDetList.get("SUMMARY_TYPE2").toString());
						}
						
						locationCell = locationRow.createCell(7);				
						locationCell.setCellValue((!CommonUtils.isEmpty(subSummDetList.get("COL_DESC")))? subSummDetList.get("COL_DESC").toString() : "-");
						
						for (int j = 0; j < monthlyColums.size(); j++) {
							String colNm = "AMOUNT" + j;
							locationCell = locationRow.createCell(9+j);
							if(subSummDetList.get(colNm) != null){
								locationCell.setCellValue(Double.parseDouble(subSummDetList.get(colNm).toString()));
								locationCell.setCellStyle((XSSFCellStyle)cellStyle.get("detailStyle1"));
							}
							else{
								locationCell.setCellValue(0);
								locationCell.setCellStyle((XSSFCellStyle)cellStyle.get("detailStyle2"));
							}
							
							if(j == 0){ //First Row For Sum
								// # RevenueReport TotalFormula calculate start from second column 01112017 CT
								cellFromH = CellReference.convertNumToColString(9 + j + 1) + (nextRowNum+w+1);
								// # RevenueReport TotalFormula calculate start from second column 01112017 CT//D14
							}
							
							if(j+1 == monthlyColums.size()){ //Row Last Amount Column
								cellToH = CellReference.convertNumToColString(9 + monthlyColums.size() - 1) + (nextRowNum+w+1); //P14
								locationCell = locationRow.createCell(9+j+1);
								String vFormula = "SUM(" + cellFromH + ":" + cellToH + ")";
								locationCell.setCellFormula(vFormula);
								locationCell.setCellStyle((XSSFCellStyle)cellStyle.get("detailStyle3"));
							}
						}		
						w += 1;
					}
				}
				nextRowNum = nextRowNum + w;
			}
		} //END IF
	}
	
	private List<String> getMonthSpace(String periodFrom, String periodTo) throws Exception {

		List<String> monthlyColums = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(sdf.parse(periodFrom));
		c2.setTime(sdf.parse(periodTo));
		
		c1.add(Calendar.MONTH, -1);
		
		while (!c1.equals(c2)) {
			monthlyColums.add(sdf.format(c1.getTime()));
			c1.add(Calendar.MONTH, 1);
		}
		monthlyColums.add(sdf.format(c2.getTime()));
		
		return monthlyColums;
		
	}
	
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