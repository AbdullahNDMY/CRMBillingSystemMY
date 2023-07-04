/*
 * @(#)B_CLSR01BLogic.java
 *
 *
 */
package nttdm.bsys.b_cls.blogic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.DBCodeListLoader;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.a_usr.bean.UserAccessBean;
import nttdm.bsys.b_cls.dto.B_CLSR01Input;
import nttdm.bsys.b_cls.dto.B_CLSR01Output;
import nttdm.bsys.b_cls.dto.T_CLOSING;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionMessages;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/** 
 * B_CLSR01BLogic Class. 
 * <p> 
 * BLogic
 * </p> 
 * @author DungLQ
 * @version 1.0 May 24,2010
 */ 
public class B_CLSR01BLogic extends AbstractB_CLSR01BLogic {
	private static final String SQL_SEARCH_QCS = "SELECT.B_CLS.SQL001";
	private static final String SQL_COUNT_QCS = "SELECT.B_CLS.SQL002";
	private static final String SQL_GET_ACCESSTYPE = "SELECT.BSYS.SQL016";
	private static final String ID_MODULE = "B";
	private static final String ID_SUB_MODULE = "B-CLS";
	
	@SuppressWarnings("unchecked")
	public BLogicResult execute(B_CLSR01Input param) {
		BLogicResult result = new BLogicResult();
		// BLogicMessages messages = new BLogicMessages();
		B_CLSR01Output output = new B_CLSR01Output();
		BillingSystemUserValueObject bsUser = param.getUvo();
		// Get user_id and get access_type
		if(bsUser != null){			
			String userId = param.getUvo().getId_user();
			UserAccessBean userAccess = new UserAccessBean();
			userAccess.setId_user(userId);
			userAccess.setId_module(ID_MODULE);
			userAccess.setId_sub_module(ID_SUB_MODULE);
			String access_type = queryDAO.executeForObject(SQL_GET_ACCESSTYPE, userAccess, String.class);
			output.setAccessType(access_type);
		}
		
		// Update data status
		if(!CommonUtils.isEmpty(param.getClickEvent())){
			String clickEvent = param.getClickEvent();
			String[] clickEvents = clickEvent.split(",");
			String idYearMonth = clickEvents[1];
			Map m = new HashMap();
			m.put("idYearMonth", StringUtils.replace(idYearMonth, "/", ""));
			m.put("moduleId", clickEvents[2]);
			m.put("updatedDate", new Date());
			/**
        	 * Audit Trail
        	 */
        	Integer idAudit = CommonUtils.auditTrailBegin(bsUser.getId_user(),
        			BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CLS, idYearMonth, null,
				BillingSystemConstants.AUDIT_TRAIL_EDITED);
        	m.put("idAudit", idAudit);
        	
			if(bsUser != null){
				m.put("idLogin", bsUser.getId_user());
			}
			
			if(clickEvents[0].equals("close")){//mode
				m.put("isClosedUpt", 1);
				m.put("isDeleted", 0);
				m.put("isClosed", 0);
			}else{
				m.put("isClosedUpt", 0);
				m.put("isDeleted", 0);
				m.put("isClosed", 1);
			}
			if(clickEvents[2].equals("QCS")){
				// more parameter
				if(clickEvents[0].equals("close")){
					m.put("idStatus", "1");
				}
				updateDAO.execute("B_CLS.updateT_QCS_H", m);
			}else if(clickEvents[2].equals("BIF")){
				// more parameter
				if(clickEvents[0].equals("close")){
					m.put("idStatus", "1");
				}
				updateDAO.execute("B_CLS.updateT_BIF_H", m);
			}else if(clickEvents[2].equals("BIL")){
				updateDAO.execute("B_CLS.updateT_BIL_H", m);
			}else if(clickEvents[2].equals("CSB")){
				updateDAO.execute("B_CLS.updateT_CSB_H", m);
			}
			// final update T_Closing
			updateDAO.execute("B_CLS.updateTClosing", m);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		}
		
		// Set data to t_closing bean
        T_CLOSING input = new T_CLOSING();
        input.setModuleId(param.getModuleId());
        input.setYear(param.getYear());
        input.setMonth(param.getMonth());
        input.setIdYearMonth(param.getMonth()+param.getYear()); // Format idYearMonth Str "mmyyyy"
        input.setIsClosed(param.getStatus());
        
        // Paging
        String strIndex = param.getStartIndex();
		int startIndex = 0;
		//get the number of row for paging
		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
		int row = bss.getResultRow();
        if (strIndex != null ) {
            startIndex = Integer.parseInt(strIndex);          
        }
        
        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        row = systemSetting.getResultRow();
        output.setRow(String.valueOf(row));
        
        // Get CLOSEDOCTYPE list
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        /*MappedCodeListLoader closeDocType = (MappedCodeListLoader) context.getBean("CLOSEDOCTYPE");
        Map closeDTMap = closeDocType.getCodeListMap();*/
        DBCodeListLoader closeDocType = (DBCodeListLoader) context.getBean("CLOSEDOCTYPE");
        CodeBean[] temp = closeDocType.getCodeBeans();
        Map closeDTMap = new HashMap();
        for(int i = 0 ; i < temp.length ; i++){
        	CodeBean test = temp[i];
        	closeDTMap.put(test.getId(), test.getName());
        }
        
        // Is date param exist in DB
        String monthYear = "";
        // get current time
        Calendar cal = Calendar.getInstance();
        //if(param.getYear() != null && param.getMonth() != null){
        	monthYear = (cal.get(Calendar.MONTH) < 9 ? ("0" + (cal.get(Calendar.MONTH) + 1)) : (cal.get(Calendar.MONTH) + 1)) + "" + cal.get(Calendar.YEAR) + "";
        //}
        List<HashMap> existResult = queryDAO.executeForObjectList("B_CLS.getExistResult", monthYear);
        if(existResult.size() > 0){
        	// do nothing
        }else{
        	//if(!CommonUtils.isEmpty(param.getYear()) && !CommonUtils.isEmpty(param.getMonth())){
	        	// Insert into T_Closing with new date
	        	Iterator iter = closeDTMap.keySet().iterator();
	        	java.sql.Date currDate = CommonUtils.parseToDate("01" + monthYear, "ddMMyyyy");
	        	/**
	        	 * Audit Trail
	        	 */
	        	String idMonthYear = (cal.get(Calendar.MONTH) < 9 ? ("0" + (cal.get(Calendar.MONTH) + 1)) : (cal.get(Calendar.MONTH) + 1)) + "/" + cal.get(Calendar.YEAR) + "";
	        	Integer idAudit = CommonUtils.auditTrailBegin(bsUser.getId_user(),
	        			BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CLS, 
	        			idMonthYear, null, BillingSystemConstants.AUDIT_TRAIL_CREATED);
	        	
	        	while(iter.hasNext()){
	        		Map tClosing = new HashMap();
	            	tClosing.put("idYearMonth", currDate);
	            	tClosing.put("moduleId", iter.next());
	            	tClosing.put("isClosed", param.getStatus() != "" ? param.getStatus() : "0");
	            	tClosing.put("createdDate", new Date());
	            	tClosing.put("updatedDate", new Date());
	            	tClosing.put("idLogin", bsUser.getId_user());
	            	tClosing.put("idAudit", idAudit);
	            	updateDAO.execute("B_CLS.insertTClosing", tClosing);
	        	}
	        	CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
        	//}
        }
        // Get total 
        Integer totalCount = queryDAO.executeForObject(SQL_COUNT_QCS, input, Integer.class);
        // Get list closing inquiry
        if(totalCount > 0){
//        	List<T_CLOSING> clsInfos = queryDAO.executeForObjectList(SQL_SEARCH_QCS, input, startIndex, row);
//        	output.setClsInfos(clsInfos);
//        	output.setTotalCount(totalCount.toString());
        	List<T_CLOSING> clsInfos = queryDAO.executeForObjectList(SQL_SEARCH_QCS, input);
        	output.setClsInfos(clsInfos);
        	output.setTotalCount(totalCount.toString());
        	//
        	int index = 0;
        	int start = 0, end = 0;
        	List<T_CLOSING> acc = new java.util.ArrayList<T_CLOSING>();
        	for(T_CLOSING c : clsInfos) {
        		if(contains(acc, c)) {
        			c.setIdYearMonthDisplay("");
        			c.setIndex("");
        		}
        		else {
        			index++;
        			c.setIdYearMonthDisplay(c.getIdYearMonth());
        			c.setIndex(index+"");
        			if(index - 1 == startIndex) {
        				start = acc.size();
        			}
        			if(index - 1 == startIndex + row) {
        				end = acc.size();
        			}
        		}
    			acc.add(c);
        	}
        	if(end == 0)
        		end = acc.size();
        	output.setClsInfos(acc.subList(start, end));
        	output.setTotalCount(index+"");
        }else{
        	output.setTotalCount("0");
        	//info.ERR2SC002
        	BLogicMessages msgs = new BLogicMessages();
        	BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
        	msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
        	result.setMessages(msgs);
        }
        // Reset property
		result.setResultObject(output);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
	
	private boolean contains(List<T_CLOSING> con, T_CLOSING e) {
		for(T_CLOSING c : con) {
			if(c.getIdYearMonth().equals(e.getIdYearMonth()))
				return true;
		}
		return false;
	}
}