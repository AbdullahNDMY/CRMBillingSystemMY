/*
 * @(#)B_BIFS03_04BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS03Input;
import nttdm.bsys.bif.dto.B_BIF_Input;
import nttdm.bsys.bif.dto.B_BIF_Output;

import nttdm.bsys.bif.blogic.AbstractB_BIFS03_04BLogic;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_ALT_P03;
import nttdm.bsys.common.util.G_OBT_P01;
import nttdm.bsys.common.util.G_OBT_P02;
import nttdm.bsys.common.util.G_PAG_P01;
import nttdm.bsys.common.util.MessageUtil;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS03_04BLogic extends AbstractB_BIFS03_04BLogic {
	private static final String SELECT_BIF_INFO = "SELECT.B_BIF.S03_01.SQL001";
	
	private static final String CN_TYPE = "BIFCN";
	private static final String DN_TYPE = "BIFDN";
	
	private static final String DELETE_BIF = "DELETE.B_BIF.S03.SQL001";
    private static final String DELETE_DOC = "DELETE.B_BIF.S03.SQL002";
    private static final String DELETE_PLAN_CUST = "DELETE.B_BIF.S03.SQL003";
    private static final String DELETE_PLAN_CUST_H = "DELETE.B_BIF.S03.SQL004";
    private static final String REJECT_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL002";
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(B_BIFS03Input input) {
		BLogicResult result = new BLogicResult();

		BLogicMessages messages = new BLogicMessages();
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		try {
			//check idRef and action
			if (input.getIdRefR() != null) {
				input.setIdRef(input.getIdRefR().toString());
			}
			String idRef = input.getIdRef();
			String action = input.getAction();
			String idUser = input.getUvo().getId_user();
			String idScreen = "";
			String bifType = "";
			String type = "";
			B_BIF_Output out = new B_BIF_Output();
			out.setIdRef(idRef);
			if (action != null) {
				//user click on edit button
				if (action.equals(B_BIFS03Input.EDIT_ACTION)) {
					result.setResultString(B_BIFS03Input.EDIT_ACTION);
				}
				//user click on approval button
				if (action.equals(B_BIFS03Input.APPROVAL_ACTION)) {
					result.setResultString(B_BIFS03Input.APPROVAL_ACTION);
					
					Map<String, String> param = new HashMap<String, String>();
					param.put("ID_REF", idRef);
					param.put("ID_USER", idUser);
					//get BIF Information
					Map<String, Object> bifObject = queryDAO.executeForMap(SELECT_BIF_INFO, param);
					//check bif type
					bifType = bifObject.get("BIF_TYPE").toString();
					if (bifType != null) {
						if (bifType.equals("CN")) {
							idScreen = CN_TYPE;
						} else {
							idScreen = DN_TYPE;
						}
					} else {
						//record is deleted by another, return to main screen
						result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
						return result;
					}
					/*if (bifObject.get("ID_STATUS").equals("DS3") || 
							bifObject.get("ID_STATUS").equals("DS4") ||
							bifObject.get("ID_STATUS").equals("DS5")) {
						//display screen view or close
						String status = bifObject.get("IS_CLOSED").toString();
						if (status != null) {
							if(status.equals("0")) {
								type = "v";
							} else {
								type = "x";
							}
						}
					} else {*/
						G_PAG_P01 gPagP01 = new G_PAG_P01("", idUser, idRef, idScreen, queryDAO, "B-BIF");
						type = gPagP01.getPage();
						out.setPagAction(gPagP01.getMoreAction());
					/*}*/
				}
				
				if (B_BIFS03Input.DELETE_ACTION.equals(action)) {
	                /**
	                 * Audit Trail
	                 */
	                Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
	                        input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_DELETED);
	                
	                this.deleteBIF(input, idAudit);
	                CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
	                //messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC005"));
	                out.setMessage("info.ERR2SC005");
	                result.setResultString("list");
	            }
	            
	            Map<String, String> param = new HashMap<String, String>();
	            param.put("ID_REF", input.getIdRef());
	            Map<String, Object> bifObject = queryDAO.executeForMap(SELECT_BIF_INFO, param);
	            
	            if (B_BIFS03Input.OBTAIN_APPROVAL_ACTION.equals(action)) {
	                /**
                     * Audit Trail
                     */
                    Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
                            input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_EDITED);
                    
                    Map<String, Object> sqlParam = new HashMap<String, Object>();
                    sqlParam.put("idRef", input.getIdRef());
                    sqlParam.put("idLogin", input.getUvo().getId_user());
                    sqlParam.put("idAudit", idAudit);
                    updateDAO.execute("UPDATE.B_BIF.S02_03_01.SQL002", sqlParam);
                    
	                if (bifObject.get("ID_STATUS").equals("DS0")) {
	                    G_OBT_P01 gObtP01 = new G_OBT_P01(this.queryDAO, this.updateDAO);
	                    gObtP01.exucute(input.getIdRef(), "BIF-CN", "C000", B_BIFS03Input.ID_SCREEN, input.getUvo().getId_user());
	                } else {
	                    Map<String, String> params = new HashMap<String, String>();
	                    params.put("ID_REF", input.getIdRef());
	                    params.put("APPR_STATUS", "AS3");
	                    List<Map<String, Object>> rejectList = queryDAO.executeForMapList(REJECT_STATUS_INFO, params);
	                    //get reject workflow information
	                    for (Map<String, Object> map : rejectList) {
	                        String action_user = map.get("ID_APPROVER").toString();
	                        String section_no = map.get("SECTION_NO").toString();
	                        G_OBT_P02 gObtP02 = new G_OBT_P02(this.queryDAO, this.updateDAO);
	                        WF_TABLEBean bean = new WF_TABLEBean();
	                        bean.setId_ref(input.getIdRef());
	                        bean.setAction_user(action_user);
	                        bean.setSection_no(section_no);
	                        bean.setId_screen(B_BIFS03Input.ID_SCREEN);
	                        bean.setId_user(input.getUvo().getId_user());
	                        bean.setId_login3(input.getUvo().getId_user());
	                        bean.setResponse_expire(map.get("RESPONSE_EXPIRE").toString());
	                        gObtP02.exucute(bean);
	                    }
	                }
	                
	                //alert popup
	                out.setMessage(null);
	                type = "v";
	                messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("POPALT_BIF", input.getIdRef()));
	                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
	                CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
	            }   
	            
	            if (B_BIFS03Input.OBTAIN_VERIFICATION_ACTION.equals(action)) {
	                /**
                     * Audit Trail
                     */
                    Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
                            input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_EDITED);
                    
                    Map<String, Object> sqlParam = new HashMap<String, Object>();
                    sqlParam.put("obtainVerification", "1");
                    sqlParam.put("idRef", input.getIdRef());
                    sqlParam.put("idLogin", input.getUvo().getId_user());
                    sqlParam.put("idAudit", idAudit);
                    updateDAO.execute("UPDATE.B_BIF.S02_03_01.SQL001", sqlParam);
                    
	                G_ALT_P03 gAltP03 = new G_ALT_P03(this.queryDAO, this.updateDAO);
	                WF_TABLEBean wf = new WF_TABLEBean();
	                wf.setId_ref(input.getIdRef());
	                wf.setAction_user(bifObject.get("ID_CONSLT").toString());
	                wf.setType(bifObject.get("BIF_TYPE").toString());
	                wf.setId_screen(B_BIFS03Input.ID_SCREEN);
	                String message = MessageUtil.get("info.ERR2SC026", input.getIdRef());
	                wf.setSubject(message);
	                message = MessageUtil.get("info.ERR2SC027", input.getIdRef());
	                wf.setMsg(message);
	                wf.setId_login3(input.getUvo().getId_user());
	                gAltP03.execute(wf);
	                out.setMessage(null);
	                type = "v";
	                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
	                messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("POPALT_BIF_Verification", input.getIdRef()));
	                CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
	            }
			}
	
			if (type.equals("v")) {
				action = B_BIF_Input.VIEW_ACTION;
			} else if(type.equals("x")){
				action = B_BIF_Input.VIEW_ACTION;
			} else if(type.equals("a")){
				action = B_BIF_Input.APPROVAL_ACTION;
			} else if(type.equals("s")){
				action = B_BIF_Input.SECTION_ACTION;
			} else if(type.equals("e")){
				action = B_BIF_Input.EDIT_ACTION;
			} else {
				//display error
			}
			
			out.setIdRef(idRef);
			out.setAction(action);
			result.setResultObject(out);
			result.setMessages(messages);
		} catch (Exception e) {
			// TODO: handle exception
			result.setResultString("failure");
		}
		
		return result;
	}
	
	/**
     * 
     * @param input
     * @return
     */
    private boolean deleteBIF(B_BIFS03Input input, Integer idAudit) {
        Map<String , Object> params = new HashMap<String, Object>();
        params.put("ID_REF", input.getIdRef());
        params.put("IS_DELETED", "1");
        params.put("ID_STATUS", "DS5");
        params.put("ID_LOGIN", input.getUvo().getId_user());
        params.put("PLAN_STATUS", "PS1");
        params.put("ID_AUDIT", idAudit);
        updateDAO.execute(DELETE_BIF, params);
        updateDAO.execute(DELETE_DOC, params);
        updateDAO.execute(DELETE_PLAN_CUST, params);
        updateDAO.execute(DELETE_PLAN_CUST_H, params);
        return true;
    }
}