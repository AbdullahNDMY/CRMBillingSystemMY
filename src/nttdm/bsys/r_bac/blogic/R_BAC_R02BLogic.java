/**
 * @(#)R_BAC_R02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.blogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_bac.dto.R_BAC_R02Input;
import nttdm.bsys.r_bac.dto.R_BAC_R02Output;

/**
 * R_BAC_R02BLogic class.
 * 
 * @author NTT DATA
 */
public class R_BAC_R02BLogic extends AbstractR_BAC_R02BLogic {

	/**
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(R_BAC_R02Input input) {
		BLogicResult result = new BLogicResult();
		R_BAC_R02Output output = new R_BAC_R02Output();

		BillingSystemSettings systemSetting = new BillingSystemSettings(
				queryDAO);
		Integer row = systemSetting.getResultRow();
		Integer startIndex = 0;
		try {
			startIndex = Integer.parseInt(CommonUtils.toString(input
					.getStartIndex()));
		} catch (Exception e) {
			startIndex = 0;
		}
		String doSearch = input.getDoSearch();
		if ("Y".equals(doSearch)) {
			startIndex = 0;
		}

		Integer totalRow = queryDAO.executeForObject("SELECT.R_BAC.SQL002",input, Integer.class);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		if (totalRow != null && totalRow.compareTo(new Integer(0)) > 0) {
			output.setTotalRow(totalRow);
			
			List<Map<String, Object>> searchResults = new ArrayList<Map<String, Object>>();
			
			searchResults = queryDAO.executeForMapList("SELECT.R_BAC.SQL001",input,startIndex,row);
			/*
			Map<String, Object> item = new HashMap<String, Object>();
			String id_bill_account = "";
			BigDecimal amountA = BigDecimal.ZERO;
			BigDecimal amountC = BigDecimal.ZERO;
			BigDecimal amountD = BigDecimal.ZERO;
			BigDecimal totalAmountDue=BigDecimal.ZERO;
            DecimalFormat formater = new DecimalFormat("0.00");
            formater.setRoundingMode(RoundingMode.HALF_UP);
            
			for (int i = 0; i < searchResults.size(); i++) {
				item = searchResults.get(i);
				id_bill_account = item.get("ID_BILL_ACCOUNT").toString();
				amountA = queryDAO.executeForObject("SELECT.R_BAC.SQL003", id_bill_account, BigDecimal.class);
				amountC = queryDAO.executeForObject("SELECT.R_BAC.SQL004", id_bill_account, BigDecimal.class);
				amountD = queryDAO.executeForObject("SELECT.R_BAC.SQL005", id_bill_account, BigDecimal.class);
				BigDecimal amountB = amountA.subtract(amountC).subtract(amountD);
				item.put("ACTUAL_AMOUNT", formater.format(amountB));
				totalAmountDue = new BigDecimal(CommonUtils.toString(item.get("TOTAL_AMT_DUE")));
				item.put("VARIANCE_AMOUNT", formater.format(totalAmountDue.subtract(amountB)));
				resultList.add(item);
			}
			*/
			resultList.addAll(searchResults);
		} else {
			output.setTotalRow(0);
			// info.ERR2SC002
			BLogicMessages msgs = new BLogicMessages();
			BLogicMessage msg = new BLogicMessage("info.ERR2SC002",
					new Object());
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			result.setMessages(msgs);
		}

		
		output.setCustId(input.getCustId());
		output.setCustName(input.getCustName());
		output.setBillAcc(input.getBillAcc());
		output.setBillCurrency(input.getBillCurrency());
		output.setPaymentMethod(input.getPaymentMethod());
		output.setTotalamount(input.getTotalamount());
		output.setAccessType(input.getAccessType());
		
		output.setResultList(resultList);
		output.setStartIndex(startIndex);
		output.setRow(row);
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}

}
