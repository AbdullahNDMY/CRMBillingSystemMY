/**
 * Billing System
 * 
 * SUBSYSTEM NAME : RP_B_BTH_P01 
 * SERVICE NAME :  RP_B_BTH
 * FUNCTION : Print Billing Document
 * FILE NAME : RP_B_BTH_P01_02BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */

package nttdm.bsys.b_bth.blogic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_02Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_02Output;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/** 
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Action display billing document
 * 
 */

public class RP_B_BTH_P01_02BLogic extends AbstractRP_B_BTH_S01_02BLogic {

	public static String ID_MODULE="B";
	public static String ID_SUB_MODULE="B-BTH";
	public static String ACTION_AUTHENTICATION_FAILED="failed";
	public static String ACTION_EDIT="edit";
	public static String ACTION_VIEW="view";
	
	public static final String ID_SET_NOPRINT = "NOPRINTDOC";
	public static final int ACTIVE_SET_SEQ = 1;
	public static final char NOT_DELETED = '0';
	
	private BLogicResult result;
	private BLogicMessages errors;
	private BLogicMessages messages;
	
	public BLogicResult execute(RP_B_BTH_P01_02Input param) {
		result = new BLogicResult();
		errors = new BLogicMessages();
		messages = new BLogicMessages();
		RP_B_BTH_P01_02Output outputDTO = new RP_B_BTH_P01_02Output();
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017
		String tbxCustomerId = param.getTbxCustomerId();
		String cboCustomerType = param.getCboCustomerType();
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017
		String customerName = param.getCustomerName();
		String documentNoFrom = param.getDocumentNoFrom();
		String documentNoTo = param.getDocumentNoTo();
		String billingType = param.getBillingType();
		String billingAccount = param.getBillingAccount();
		Date fromDate = CommonUtils.parseToDate(param.getFromDate(), "dd/MM/yyyy");
		Date toDate = CommonUtils.parseToDate(param.getToDate(), "dd/MM/yyyy");
		// mapping param
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("customerName", CommonUtils.escapeSQL(customerName == null?customerName:customerName.trim()));
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017
		m.put("tbxCustomerId", CommonUtils.escapeSQL(tbxCustomerId == null?tbxCustomerId:tbxCustomerId.trim()));
		m.put("cboCustomerType", CommonUtils.escapeSQL(cboCustomerType == null?cboCustomerType:cboCustomerType.trim()));
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017
		m.put("documentNoFrom", CommonUtils.escapeSQL(documentNoFrom == null?documentNoFrom:documentNoFrom.trim()));
		m.put("documentNoTo", CommonUtils.escapeSQL(documentNoTo == null?documentNoTo:documentNoTo.trim()));
		m.put("billingType", billingType);
		m.put("billingAccount", CommonUtils.escapeSQL(billingAccount == null?billingAccount:billingAccount.trim()));
		m.put("fromDate", fromDate);
		m.put("toDate", toDate);
		m.put("idSet", ID_SET_NOPRINT);
        m.put("cboDeletedStatus", param.getCboDeletedStatus());
        m.put("cboBillingCurrency", param.getCboBillingCurrency());
        m.put("documentNo", param.getDocumentNo());
        m.put("deliveryEmail", param.getDeliveryEmail());
        if (param.getDelivery().length == 3) {
			m.put("delivery", new String[] { "1", "2", "3", "4" });
		} else {
			 m.put("delivery", param.getDelivery());
		}
        
        String issueTypeAllNotChecked = "";
        String issueTypeSingpost = CommonUtils.toString(param.getIssueTypeSingpost());
        String issueTypeAuto = CommonUtils.toString(param.getIssueTypeAuto());
        String issueTypeManual = CommonUtils.toString(param.getIssueTypeManual());
        
        if (CommonUtils.isEmpty(issueTypeSingpost)
                &&CommonUtils.isEmpty(issueTypeAuto)
                &&CommonUtils.isEmpty(issueTypeManual)) {
            issueTypeAllNotChecked = "0";
        }
        m.put("issueTypeSingpost", issueTypeSingpost);
        m.put("issueTypeAuto", issueTypeAuto);
        m.put("issueTypeManual", issueTypeManual);
        m.put("issueTypeAllNotChecked", issueTypeAllNotChecked);
		
		//validate data
		boolean allowSearch = true;
		if((CommonUtils.isEmpty(documentNoFrom) && CommonUtils.isEmpty(documentNoTo)) ||
				(!CommonUtils.isEmpty(documentNoFrom) && !CommonUtils.isEmpty(documentNoTo))) {
			allowSearch = true;
		}
		else {
			allowSearch = false;
			errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC046", new Object[] {"Billing Document No.", "Billing Document No. (From)", "Billing Document No. (To)"}));
		}
		if(CommonUtils.isEmpty(param.getFromDate()) && !CommonUtils.isEmpty(param.getToDate())) {
			allowSearch = false;
			errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC005", new Object[] {"Billing Date From"}));
		}
		if(!CommonUtils.isEmpty(param.getFromDate()) && CommonUtils.isEmpty(param.getToDate())) {
			allowSearch = false;
			errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC005", new Object[] {"Billing Date To"}));
		}
		if(fromDate != null && toDate != null )
			if(toDate.before(fromDate)) {
				allowSearch = false;
				errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC037", new Object[] {"Billing Date", "Billing Date From", "Billing Date To"}));
			}
		
		Integer totalHistories = 0;
		int row = 0;
		List<HashMap> listPrintHistories = new ArrayList<HashMap>();
		if(allowSearch) {
			BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
			row = bss.getResultRow();
	        Integer startIndex = param.getStartIndex();		
			totalHistories = queryDAO.executeForObject("B_BTH.countPrintingHistories", m, Integer.class);
			if (startIndex == null || startIndex < 0 || startIndex > totalHistories)
				startIndex = 0;
			listPrintHistories = this.queryDAO.executeForObjectList("B_BTH.getPrintingHistories", m, startIndex, row);

			if(totalHistories==0){
			    // info.ERR2SC002
	            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
	            messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
		}

		try {
			BeanUtils.copyProperties(outputDTO, param);
		} catch (Exception e) {}

		String[] idRefs = param.getIdRefs();
		//prompt confirmation for authorised signature printing,when G.VALUE=MY02
        Map<String,Object> mSystemBase=this.queryDAO.executeForMap("B_BTH.getBILDOCPDF", null);
        String systembase=CommonUtils.toString(mSystemBase.get("VALUE"));
        outputDTO.setSysBaseVal(systembase);
        
		outputDTO.setInitFlg("0");
		outputDTO.setListPrintHistories(listPrintHistories);
		outputDTO.setTotalRow(totalHistories);
		outputDTO.setRow(row);
		outputDTO.setIdRefs(idRefs);
		result.setResultObject(outputDTO);
		result.setErrors(errors);
		result.setMessages(messages);
		result.setResultString("success");
		return result;
	}
}