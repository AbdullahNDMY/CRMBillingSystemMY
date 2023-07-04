/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (Export)
 * SERVICE NAME   : B_CPM_E01
 * FUNCTION       : Export search result to CSV file
 * FILE NAME      : B_CPM_S01DownloadBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.CodeListLoader;

import nttdm.bsys.b_cpm.common.ByteArrayStreamInfo;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_CPM_S01DownloadBLogic extends DownloadAction {
	
	private QueryDAO queryDAO;
	
	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String fileNameTemp = this.queryDAO.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_EXPORT_FILENAME", "PLN", String.class);
		String content = "";
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyMMddhhmmss");
		
		//generate system date
		fileNameTemp = fileNameTemp.replace("yymmddhhmmss", fmt.format(new Date()));
		content += this.generateHeader() + "\n";
		content += this.generateContent(request);
		
		String fileName = new String(fileNameTemp.getBytes(), "UTF-8");
		
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);	
		return new ByteArrayStreamInfo("html/text", content.getBytes());
	}
	
	/**
	 * 
	 * @return
	 */
	private String generateHeader() {
		List<String> header = this.queryDAO.executeForObjectList(B_CPM_CONSTANT.NAMESPACE + "GET_EXPORT_HEADER", "PLN");
		String strHeader = "";
		for (int i = 0; i < header.size(); i++) {
			if (i == header.size() - 1) {
				strHeader += "\"" + header.get(i) + "\"";
			} else {
				strHeader += "\"" + header.get(i) + "\",";
			}
		}
		return strHeader;
	}
	
	@SuppressWarnings("unchecked")
	private String generateContent(HttpServletRequest request) {
		// String content = "";
		StringBuilder content = new StringBuilder();
		List<Map<String, Object>> result = (List<Map<String, Object>>)request.getAttribute("searchResult");
		int index = 1;
		/** This processing for displaying full text follow:
		 * Service Status.
		 * Category (SVC_LEVEL1).
		 * Service Name (SVC_LEVEL2).
		 * Plan (SVC_LEVEL3).
		 * */
		// Service Status
		CodeListLoader codeList= (CodeListLoader) ApplicationContextProvider.getApplicationContext().getBean("LIST_PLANSTATUS");
		CodeBean[] results = codeList != null ? codeList.getCodeBeans() : null;
//		for(Map<String, Object> record : result){
//			for(CodeBean cBean : results){
//				if(record.get("SERVICES_STATUS").equals(cBean.getId())){
//					record.put("SERVICES_STATUS",cBean.getName());
//					break;
//				}	
//			}
//		}
		//Category
		List<Map<String,Object>> groupName = this.queryDAO.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_SVC_GROUP",null);
		//Service Name, Plan
		List<Map<String,Object>> serviceName = this.queryDAO.executeForMapList(B_CPM_CONSTANT.NAMESPACE + "GET_SVC_NAME",null);
		
//		for (Map<String, Object> record : result) {
//			// Category
//			for(Map<String, Object> group : groupName){
//				if(record.get("SVC_LEVEL1") != null){
//					if(record.get("SVC_LEVEL1").equals(group.get("SVC_GRP"))){
//						record.put("SVC_LEVEL1", group.get("SVC_GRP_NAME"));
//					}
//				}
//			}
//			//Service Name, Plan
//			for(Map<String, Object> svc : serviceName){
//				if(record.get("SVC_LEVEL2") != null){
//					if(record.get("SVC_LEVEL2").equals(svc.get("ID_SERVICE"))){
//						record.put("SVC_LEVEL2", svc.get("SVC_DESC"));
//					}
//				}
//				if(record.get("SVC_LEVEL3") != null){
//					if(record.get("SVC_LEVEL3").equals(svc.get("ID_SERVICE"))){
//						record.put("SVC_LEVEL3", svc.get("SVC_DESC"));
//					}
//				}
//			}
//			
//		}
		
		//Rate Type
		CodeListLoader listRateType = (CodeListLoader) ApplicationContextProvider.getApplicationContext().getBean("LIST_RATETYPE");
		CodeBean[] rateTypeResults = listRateType != null ? listRateType.getCodeBeans() : null;
//		for(Map<String, Object> record : result){
//			for(CodeBean cBean : rateTypeResults){
//				if(record.get("RATE_TYPE").equals(cBean.getId())){
//					record.put("RATE_TYPE",cBean.getName());
//					break;
//				}	
//			}
//		}
		
		//Rate Mode
		CodeListLoader listRateMode = (CodeListLoader) ApplicationContextProvider.getApplicationContext().getBean("LIST_RATEMODE");
		CodeBean[] rateModeResults = listRateMode != null ? listRateMode.getCodeBeans() : null;
//		for(Map<String, Object> record : result){
//			for(CodeBean cBean : rateModeResults){
//				if(record.get("RATE_MODE").equals(cBean.getId())){
//					record.put("RATE_MODE",cBean.getName());
//					break;
//				}	
//			}
//		}
		
		/**
		 * Processing for Print document
		 * */
		for (Map<String, Object> record : result) {
			content.append("\"" + index + "\",");
			content.append("\"" + this.emptySetting(record.get("ID_CUST")) + "\",");
			content.append("\"" + this.emptySetting(record.get("CUST_NAME")) + "\",");
			content.append("\"" + this.emptySetting(record.get("ID_SUB_INFO")) + "\",");
			//String bill_desc = B_CPM_Common.convertClobToString((Clob)record.get("BILL_DESC"));
			
			//content += "\"" + this.emptySetting(record.get("TRANSACTION_TYPE")) + "\",";
			content.append("\"" + this.emptySetting(record.get("ID_BILL_ACCOUNT")) + "\",");
			content.append("\"" + this.emptySetting(record.get("BILL_INSTRUCT")) + "\",");
			content.append("\"" + this.emptySetting(record.get("BILL_CURRENCY")) + "\",");
			//content += "\"" + this.emptySetting(record.get("SUB_AMOUNT")) + "\",";
			//content += "\"" + this.emptySetting(record.get("GRAND_AMOUNT")) + "\",";
			content.append("\"" + this.emptySetting(record.get("SVC_START")) + "\",");
			content.append("\"" + this.emptySetting(record.get("SVC_END")) + "\",");
			// Service Status
			String serviceStatus = "";
			for(CodeBean cBean : results){
				if(record.get("SERVICES_STATUS").equals(cBean.getId())){
					serviceStatus = cBean.getName();
					break;
				}	
			}
			content.append("\"" + this.emptySetting(serviceStatus) + "\",");
			// #202 Start
			//Billing Status
			String billingStatus = "-";
			if ("BS1".equals(this.emptySetting(record.get("BILLING_STATUS")))) {
				billingStatus = "Not Started";
			} else if ("BS2".equals(this.emptySetting(record.get("BILLING_STATUS")))) {
				billingStatus = "In Progress";
			} else if ("BS3".equals(this.emptySetting(record.get("BILLING_STATUS")))) {
				billingStatus = "Completed";
			}
			content.append("\"" + this.emptySetting(billingStatus) + "\",");
			//Billing Option
			String billOption = "Prepaid";
			if ("2".equals(this.emptySetting(record.get("BILL_OPTION")))) {
				billOption = "Postpaid";
			} 
			content.append("\"" + this.emptySetting(billOption) + "\",");
			// #202 End
			String svcStart = "";
			String svcend = "";
			String contractTerm = CommonUtils.toString(record.get("CONTRACT_TERM"));
			String svcPeriod = CommonUtils.toString(record.get("MIN_SVC_PERIOD"));
			String contractTermNo = "";
			String contractTermName = "";
			if ("1".equals(svcPeriod)) {
			    if ("Y".equals(contractTerm)) {
	                contractTermName = "Year(s)";
	            } else if ("M".equals(contractTerm)) {
	                contractTermName += "Month(s)";
	            }
			    contractTermNo = CommonUtils.toString(record.get("CONTRACT_TERM_NO"));
			    svcStart = CommonUtils.toString(record.get("MIN_SVC_START"));
			    svcend = CommonUtils.toString(record.get("MIN_SVC_END"));
			}
			content.append("\"" + svcStart + "\",");
			content.append("\"" + svcend + "\",");
			content.append("\"" + contractTermNo + " " +contractTermName + "\",");
			content.append("\"" + this.emptySetting(record.get("JOB_NO")) + "\",");
			// #165 Start
			content.append("\"" + this.emptySetting(record.get("CUST_PO")) + "\",");
			// #165 End

			//wcbeh@20161221 #216 CPM Export Result Alignment Issue
			String bill_desc = "";
			if(!CommonUtils.isEmpty(record.get("BILL_DESC")))
				bill_desc = CommonUtils.toString(record.get("BILL_DESC")).replace("\"", "\"\"");
			content.append("\"" + this.emptySetting(bill_desc) + "\",");
			String item_desc = "";
			if(!CommonUtils.isEmpty(record.get("ITEM_DESC")))
				item_desc = CommonUtils.toString(record.get("ITEM_DESC")).replace("\"", "\"\"");
			content.append("\"" + this.emptySetting(item_desc) + "\",");
			//content += "\"" + this.emptySetting(record.get("PLAN_DESC") + "" + this.emptySetting(record.get("ITEM_GRP_NAME"))) + "\",";
			// Category
			Object categoryStr = null;
			for (Map<String, Object> group : groupName) {
				if (record.get("SVC_LEVEL1") != null) {
					if (record.get("SVC_LEVEL1").equals(group.get("SVC_GRP"))) {
						categoryStr = group.get("SVC_GRP_NAME");
						break;
					}
				}
			}
			content.append("\"" + this.emptySetting(categoryStr) + "\",");
			//Service Name, Plan
			Object serviceNameObj = null;
			Object planObj = null;
			for (Map<String, Object> svc : serviceName) {
				if (record.get("SVC_LEVEL2") != null) {
					if (record.get("SVC_LEVEL2").equals(svc.get("ID_SERVICE"))) {
						serviceNameObj = svc.get("SVC_DESC");
					}
				}
				if (record.get("SVC_LEVEL3") != null) {
					if (record.get("SVC_LEVEL3").equals(svc.get("ID_SERVICE"))) {
						planObj = svc.get("SVC_DESC");
					}
				}
			}
			content.append("\"" + this.emptySetting(serviceNameObj) + "\",");
			content.append("\"" + this.emptySetting(planObj) + "\",");
			// Rate Type
			String rateType = "";
			for (CodeBean cBean : rateTypeResults) {
				if (record.get("RATE_TYPE").equals(cBean.getId())) {
					rateType = cBean.getName();
					break;
				}
			}
			content.append("\"" + this.emptySetting(rateType) + "\",");
			// Rate Mode
			String rateMode = "";
			for (CodeBean cBean : rateModeResults) {
				if (record.get("RATE_MODE").equals(cBean.getId())) {
					rateMode = cBean.getName();
					break;
				}
			}
			content.append("\"" + this.emptySetting(rateMode) + "\",");
			content.append("\"" + this.emptySetting(record.get("QUANTITY")) + "\",");
			content.append("\"" + this.emptySetting(record.get("UNIT_PRICE")) + "\",");
			content.append("\"" + this.emptySetting(record.get("TOTAL_AMOUNT")) + "\",");
			//wcbeh@20161215 #216 CPM Export Result Add Location
			content.append("\"" + this.emptySetting(record.get("LOCATION")) + "\"");
			content.append("\n");
			index++;
		}
		return content.toString();
	}

	private Object emptySetting(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj;
		}
	}
	
	/**
	 * @param queryDAO the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
}