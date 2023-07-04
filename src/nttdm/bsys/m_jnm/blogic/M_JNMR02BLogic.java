/*
 * @(#)M_JNMR02BLogic.java
 *
 *
 */
package nttdm.bsys.m_jnm.blogic;

import org.apache.struts.Globals;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_jnm.dto.M_JNMR02Input;
import nttdm.bsys.m_jnm.dto.M_JNMR02Output;
import java.util.List;
import java.util.Map;
/**
 * BusinessLogic class.
 * 
 * @author ss051
 */
public class M_JNMR02BLogic extends AbstractM_JNMR02BLogic {

	public BLogicResult execute(M_JNMR02Input param) {

		BLogicResult result = new BLogicResult();
		BLogicMessages messages = new BLogicMessages();
		BLogicMessages errors = new BLogicMessages();
		M_JNMR02Output output = new M_JNMR02Output();
		M_JNMR02Input input = new M_JNMR02Input();

		String Id_cust = CommonUtils.toString(param.getId_cust());
		String Job_no = CommonUtils.toString(param.getJob_no());
		String saveMode = param.getSaveMode();
		String msgBoxMode = "0";
		String isSaveFlg = "0";
		
		//From Screen 'B-CPM' ,when click the option 'add' in Combobox
		String action = CommonUtils.toString(param.getActionFrom());
		if(action.equals("add")){
		    output.setId_cust(Id_cust);
		    output.setActionFrom(action);
		}
		
		if (saveMode != null && !saveMode.equals("")) {
			input.setJob_no(Job_no);
			input.setId_cust(Id_cust);
			input.setIdLogin(param.getUvo().getId_user());
			updateDAO.execute("SELECT.M_JNM.SAVE", input);
            output.setJob_no(param.getJob_no());
            // #159 Start
			output.setId_cust(Id_cust);
			// #159 End

//			messages.add(Globals.MESSAGE_KEY, new BLogicMessage(
//					"info.ERR2SC003"));
			isSaveFlg = "1";
		} else {
			// get clickEvent
			String clickEvent = param.getClickEvent();

			if (clickEvent != null && !clickEvent.equals("")) {

				boolean isSave = true;

				// check
				if (CommonUtils.isEmpty(Id_cust)) {
					// validate is null
					errors.add(Globals.ERROR_KEY, new BLogicMessage(
							"errors.ERR1SC005", "Customer ID"));
				}
				if (CommonUtils.isEmpty(Job_no)) {
					// validate is null
					errors.add(Globals.ERROR_KEY, new BLogicMessage(
							"errors.ERR1SC005", "Job No"));
				}
				// check Customer ID exist
				if (errors.isEmpty() && !Id_cust.equals("000000")) {
					String custName = queryDAO.executeForObject(
							"SELECT.M_JNM.SQL007", Id_cust, String.class);					
					if (custName == null) {
						errors.add(Globals.ERROR_KEY, new BLogicMessage(
								"errors.ERR1SC105", "Invalid customer ID"));
					} else {
						output.setCust_name(custName);
					}
				}

				if (errors.isEmpty()) {
					input.setJob_no(Job_no);
					int jobNoCount = queryDAO.executeForObject(
							"SELECT.M_JNM.DATACHECK_COUNT", input,
							Integer.class);
					

					input.setId_cust(Id_cust);
					int jobNoAndIdCustCount = queryDAO.executeForObject(
							"SELECT.M_JNM.DATACHECK_COUNT", input,
							Integer.class);

					if (jobNoAndIdCustCount > 0) {
						errors.add(Globals.ERROR_KEY, new BLogicMessage(
								"errors.ERR1SC105",
								" Job No. already exist for this customer ID"));
					} else if (jobNoCount > 0) {
						String msg="";
						List<Map<String, Object>> itemList = this.queryDAO.executeForMapList("SELECT.M_JNM.GETCUSTIDANDNAME", Job_no);
						for(Map<String, Object> item:itemList){		
							String objCustId=CommonUtils.toString(item.get("ID_CUST"));
							String objCustname=CommonUtils.toString(item.get("CUST_NAME"));
							msg+=objCustname + '(' + objCustId + ')'+'/';
						}
						output.setSame_cust_name(msg);
						msgBoxMode = "1";
						isSave = false;
					}

					if (errors.isEmpty() && isSave) {
						input.setIdLogin(param.getUvo().getId_user());
						updateDAO.execute("SELECT.M_JNM.SAVE", input);
						output.setJob_no(Job_no);
						// #159 Start
						output.setId_cust(Id_cust);
						// #159 End
//
//						messages.add(Globals.MESSAGE_KEY, new BLogicMessage(
//								"info.ERR2SC003"));
						isSaveFlg = "1";
					} else {
						output.setId_cust(param.getId_cust());
						output.setJob_no(param.getJob_no());
					}
				}
			}
		}
		output.setIsSaveFlg(isSaveFlg);
		output.setMsgBoxMode(msgBoxMode);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		result.setErrors(errors);
		result.setMessages(messages);
		result.setResultObject(output);
		return result;
	}
}