/*
 * @(#)M_JNMR01BLogic.java
 *
 *
 */
package nttdm.bsys.m_jnm.blogic;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_jnm.dto.M_JNMR01Input;
import nttdm.bsys.m_jnm.dto.M_JNMR01Output;
import nttdm.bsys.m_jnm.dto.T_JOB_LIST;

/**
 * BusinessLogic class.
 * 
 * @author ss051
 */
public class M_JNMR01BLogic extends AbstractM_JNMR01BLogic {

	private static final String SEL_MOD = "1";
	private static final String DEL_MOD = "2";

	public BLogicResult execute(M_JNMR01Input param) {
		BLogicResult result = new BLogicResult();
		M_JNMR01Output output = new M_JNMR01Output();
		BLogicMessages msgs = new BLogicMessages();

		String refreshFlg = param.getRefreshFlg();
		// Initialize startIndex and row
		int startIndex = param.getStartIndex();
		if (refreshFlg != null && refreshFlg.equals("1")) {
			startIndex = 0;
		}
		// get clickEvent
		String clickEvent = param.getClickEvent();

		if (clickEvent != null && !clickEvent.equals("")) {
			String nameCust = param.getName_cust();
			String idCust = param.getId_cust();
			// T_JOB_LIST input = this.setData(param);
			T_JOB_LIST input = this.setData(param);
			List<T_JOB_LIST> jobObjr = new ArrayList<T_JOB_LIST>();

			if (DEL_MOD.equals(clickEvent)) {
				/**
				 * Audit Trail
				 */
				Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo()
						.getId_user(), BillingSystemConstants.MODULE_M,
						BillingSystemConstants.SUB_MODULE_M_JNM, input
								.getId_jobno(), null,
						BillingSystemConstants.AUDIT_TRAIL_DELETED);
				updateDAO.execute("DELETE.M_JNM.SQL004", input);
				idCust = param.getHid_id_cust();
				CommonUtils.auditTrailEnd(idAudit);// end Audit Trail
				BLogicMessage msg = new BLogicMessage("info.ERR2SC005",
						new Object());
				msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
				clickEvent = SEL_MOD;
			}

			BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
			int rowsPerPage = bss.getResultRow();

			// get totalCount
			Integer totalCount = queryDAO.executeForObject(
					"SELECT.M_JNM.SEARCH_COUNT", param, Integer.class);

			if (startIndex >= totalCount && startIndex != 0) {
				startIndex -= rowsPerPage;
			}
			// reget list job
			// jobObjr = queryDAO.executeForObjectList("SELECT.M_JNM.SQL001",
			// param, startIndex, row);
			jobObjr = queryDAO.executeForObjectList("SELECT.M_JNM.SQL001",
					param, startIndex, rowsPerPage);
			// output.setListCustomer(listCustomer);
			output.setTotalCount(String.valueOf(totalCount));
			output.setHid_id_cust(idCust);
			// #159 Start
			if(refreshFlg != null && refreshFlg.equals("1")){
				output.setId_cust("");
				output.setName_cust("");
				output.setJob_no("");
				BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object());
				msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}else{
				output.setId_cust(param.getId_cust());
				output.setName_cust(nameCust);
				output.setJob_no(param.getJob_no());
			}
			// #159 End
			output.setRowsPerPage(rowsPerPage);
			output.setStartIndex(startIndex);
			output.setClickEvent(clickEvent);
			output.setJnmInfos(jobObjr);

			if (totalCount == 0) {
				// info.ERR2SC002
				BLogicMessage msg = new BLogicMessage("info.ERR2SC002",
						new Object());
				msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);

			}
		}
		result.setMessages(msgs);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		result.setResultObject(output);
		return result;
	}

	// set Data to perform with Database
	private T_JOB_LIST setData(M_JNMR01Input param) {
		T_JOB_LIST input = new T_JOB_LIST();

		if (DEL_MOD.equals(param.getClickEvent())) {
			input.setId_jobno(param.getHid_id_jobno());
		} else {
			input.setId_jobno(param.getId_jobno());
		}
		input.setId_cust(param.getHid_id_cust());
		input.setIs_deleted("0");

		// get Login User
		BillingSystemUserValueObject uvoObj = param.getUvo();
		if (uvoObj != null) {
			input.setId_login(uvoObj.getId_user());
		} else {
			input.setId_login(" ");
		}
		return input;
	}
}