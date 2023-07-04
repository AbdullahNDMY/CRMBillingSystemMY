/*
 * @(#)Q_QCSR02BLogicBLogic.java
 *
 *
 */
package nttdm.bsys.q_qcs.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.G_PAG_P01;
import nttdm.bsys.q_qcs.dto.Q_QCSR02Input;
import nttdm.bsys.q_qcs.dto.Q_QCSR02Output;
import nttdm.bsys.q_qcs.bean.QCSHeader;
import nttdm.bsys.q_qcs.blogic.AbstractQ_QCSR02BLogic;

/**
 * BusinessLogic class.
 * 
 * @author ss051
 */
public class Q_QCSR02BLogic extends AbstractQ_QCSR02BLogic {

	private static final String SQL_GET_STATUS = "SELECT.Q_QCS.SQL003";
	private static final String DS3_STATUS = "DS3";
	private static final String DS4_STATUS = "DS4";
	private static final String DS5_STATUS = "DS5";
	private static final String IS_CLOSED_0 = "0";
	private static final String PAGE2 = "T_QCS_H";
	private static final String PAGE2e = "T_QCS_He";
	private static final String PAGE2a = "T_QCS_Ha";
	private static final String PAGE2s = "T_QCS_Hs";
	private static final String PAGE2x = "T_QCS_Hx";
	private static final String PAGE2v = "T_QCS_Hv";
	private static final String ERR1SC019_FLG = "ERR1SC019";
	private static final String ERR1SC019 = "errors.ERR1SC019";
	private static final String RESULT_ERROR = "error";
	
	public BLogicResult execute(Q_QCSR02Input param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages messages = new BLogicMessages();
		Q_QCSR02Output output = new Q_QCSR02Output();
		String id_ref = param.getId_ref_s01();
		String id_login = param.getUvo().getId_user();
		try{
			//Get id_sattus, is_closed
			QCSHeader header = queryDAO.executeForObject(SQL_GET_STATUS, id_ref, QCSHeader.class);
			String id_status = header.getId_status();
			String is_closed = header.getIs_closed();
			String id_wf_approval = "";
			String section_no = "";
			String section_group = "";
			if(id_status != null && (id_status.equals(DS3_STATUS) || id_status.equals(DS4_STATUS) || id_status.equals(DS5_STATUS))){
				if(is_closed.equals(IS_CLOSED_0)){
					output.setClickEvent("6");//View mode
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
				}else{
					output.setClickEvent("14");//X mode
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
				}
			}else{
				//Run G_TBL_P01 process
				//ArrayList<String> tbl = G_TBL_P01.execute(BillingSystemConstants.ID_SCREEN_QCS);
				//Run G_PAG_P01 process
				G_PAG_P01 g_pag_p01 = new G_PAG_P01(PAGE2, id_login, id_ref, BillingSystemConstants.ID_SCREEN_QCS,queryDAO,BillingSystemConstants.ID_SUB_MODULE);
				String page = g_pag_p01.getPage();
				if( page.equals(PAGE2e)){
					output.setClickEvent("7");
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
				}else if (page.equals(PAGE2a)){
					output.setClickEvent("8");
					WF_TABLEBean bean = g_pag_p01.getT_wf_app();
					if(bean != null){
						section_group = bean.getSection_group();
						id_wf_approval = bean.getId_wf_approval();
						section_no = bean.getSection_no();
					}
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
				}else if (page.equals(PAGE2s)){
					output.setClickEvent("11");
					WF_TABLEBean bean = g_pag_p01.getT_wf_app();
					if(bean != null){
						section_group = bean.getSection_group();
						id_wf_approval = bean.getId_wf_approval();
						section_no = bean.getSection_no();
					}
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
				}else if (page.equals(PAGE2x)){
					output.setClickEvent("14");
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
				}else if (page.equals(PAGE2v)){
					output.setClickEvent("6");
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
				}else if(page.equals(ERR1SC019_FLG)){
					id_ref="";
					result.setResultString(RESULT_ERROR);
					messages.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR1SC019,id_ref));
				}
				
				output.setId_ref(id_ref);
			}
			output.setSection_group(section_group);
			output.setSection_no(section_no);
			output.setId_wf_approval(id_wf_approval);
			output.setShowPopalt("");
			result.setResultObject(output);
			result.setMessages(messages);
			return result;
		}catch(Exception ex){
			result.setResultObject(output);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
}