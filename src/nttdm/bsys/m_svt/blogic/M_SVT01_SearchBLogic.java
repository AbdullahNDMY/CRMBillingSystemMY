/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVT
 * SERVICE NAME   : M_SVT_S01
 * FUNCTION       : Showing BLogic
 * FILE NAME      : M_SVT01_SearchBLogic.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.m_svt.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchInput;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchOutput;
import nttdm.bsys.m_svt.dto.PlanService;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

/**
 * M_SVT01_SearchBLogic<br>
 * <ul>
 * <li>A BLogic class to process showing services and plans
 * </ul>
 * <br>
 * 
 * @author hungtm
 * @version 1.0
 */
public class M_SVT01_SearchBLogic extends AbstractM_SVT01_SearchBLogic {

	public BLogicResult execute(M_SVT01_SearchInput input) {

		BLogicResult result = new BLogicResult();
		BLogicMessages messages = new BLogicMessages();
		Integer startIndex = input.getStartIndex();
		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
		int row = bss.getResultRow();
		M_SVT01_SearchOutput output = new M_SVT01_SearchOutput();
		// param
		if (input.getParameters() != null && !"".equals(input.getParameters())) {
			String param[] = input.getParameters().split(",");
			if (param.length != 0 && param.length == 8) {
				// String category[] = param[0].split("=");
				if (param[0].split("=").length > 1) {
					input.setCategory(param[0].split("=")[1].trim());
				}
				if (param[1].split("=").length > 1) {
					input.setAccountCode(param[1].split("=")[1].trim());
				}
				if (param[2].split("=").length > 1) {
					input.setType(param[2].split("=")[1].trim());
				}
				if (param[3].split("=").length > 1) {
					input.setProductCode(param[3].split("=")[1].trim());
				}
				if (param[4].split("=").length > 1) {
					input.setDescription(param[4].split("=")[1].trim());
				}
				if (param[5].split("=").length > 1) {
					input.setReference1(param[5].split("=")[1].trim());
				}
				if (param[6].split("=").length > 1) {
					input.setAbbreviation(param[6].split("=")[1].trim());
				}
				if (param[7].split("=").length > 1) {
					input.setReference2(param[7].split("=")[1].trim());
				}
				//#157 start
				/*if (param[8].split("=").length > 1) {
					input.setMessage(param[8].split("=")[1].trim());
				}*/
				//#157 end
			}
		} else {
			if (input.getCategory() != null && !"".equals(input.getCategory())) {
				input.setCategory(input.getCategory().trim());
			}
			if (input.getAbbreviation() != null
					&& !"".equals(input.getAbbreviation())) {
				input.setAbbreviation(input.getAbbreviation().trim());
			}
			if (input.getDescription() != null
					&& !"".equals(input.getDescription())) {
				input.setDescription(input.getDescription().trim());
			}
			if (input.getProductCode() != null
					&& !"".equals(input.getProductCode())) {
				input.setProductCode(input.getProductCode().trim());
			}
			if (input.getType() != null && !"".equals(input.getType())) {
				input.setType(input.getType().trim());
			}
			if (input.getAccountCode() != null
					&& !"".equals(input.getAccountCode())) {
				input.setAccountCode(input.getAccountCode().trim());
			}
			if (input.getReference1() != null
					&& !"".equals(input.getReference1())) {
				input.setReference1(input.getReference1().trim());
			}
			if (input.getReference2() != null
					&& !"".equals(input.getReference2())) {
				input.setReference2(input.getReference2().trim());
			}
		}
		// get mode
		String accessType = input.getUvo().getUserAccessInfo("M", "M-SVT-E2")
				.getAccess_type();
		output.setMode(accessType);
		List<PlanService> listPlanService = null;
		//#157 start
		List<String> idServiceList = new ArrayList<String>();
		if (input.getIdServiceListStr() != null && !"".equals(input.getIdServiceListStr())) {
			String idStr[] = input.getIdServiceListStr().split(",");
			for (int i = 0; i < idStr.length; i++) {
				idServiceList.add(idStr[i]);
			}
			input.setIdServiceList(idServiceList);
		}
		//#157 end
		// COUNT
		int count = 0;
		count = queryDAO.executeForObject("SELECT.M_SVT.GET_LIST_COUNT", input,
				Integer.class);
		if (count > 0) {
			// SERVICE LIST
			listPlanService = queryDAO
					.executeForObjectList("SELECT.M_SVT.GET_LIST_PLANSERVICE",
							input, startIndex, row);
		} else {
			// info.ERR2SC002
			BLogicMessage msg = new BLogicMessage("info.ERR2SC002",
					new Object());
			messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
			result.setMessages(messages);
		}

		output.setListPlanService(listPlanService);
		output.setTotalCount(count);
		result.setResultString("success");
		if (input.getMessage() != null && !"".equals(input.getMessage())) {
			if (!input.getMessage().equals("")) {
				messages.add(Globals.MESSAGE_KEY,
						new BLogicMessage(input.getMessage()));
			}

			result.setMessages(messages);
		}
		output.setCategory(input.getCategory());
		output.setAbbreviation(input.getAbbreviation());
		output.setDescription(input.getDescription());
		output.setProductCode(input.getProductCode());
		output.setType(input.getType());
		output.setAccountCode(input.getAccountCode());
		output.setReference1(input.getReference1());
		output.setReference2(input.getReference2());
		output.setStartIndex(startIndex);
		output.setRow(row);
		result.setResultObject(output);

		return result;
	}
}
