/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : Updating BLogic
 * FILE NAME      : M_SVGS01_03BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.blogic;

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_svg.bean.ServiceGroupBean;
import nttdm.bsys.m_svg.dto.M_SVGS01_03Input;
import nttdm.bsys.m_svg.dto.M_SVGS01_03Output;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

/**
 * M_SVGS01_03BLogic<br>
 * <ul>
 * <li>A BLogic class to process updating Groups of services
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public class M_SVGS01_03BLogic extends AbstractM_SVGS01_03BLogic {

    private BLogicMessages errors;
    
	/**
	 * 
	 * @param param
	 * @return ビジネスロジチE��の実行結果、BLogicResultインスタンス、E
	 */
	public BLogicResult execute(M_SVGS01_03Input param) {
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();
        
        M_SVGS01_03Output outputDTO = new M_SVGS01_03Output();
        
        if(!inputIsMulCharCheck(param)) {
            outputDTO.setServiceGrpItem(param.getServiceGrpItem());
            result.setResultObject(outputDTO);
            result.setErrors(errors);
            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            return result;
        }
		if(param.getAction().equals(""))
		{
			for(int i=0;i<param.getServiceGrpItem().size();i++)
			{
				param.getServiceGrpItem().get(i).setId_login(param.getUvo().getId_user());
				if(param.getServiceGrpItem().get(i).getSvc_grp().equals("")){
					/**
					 * Audit Trail
					 */
					Integer id_audit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), 
							BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_SVG, 
							null, null, BillingSystemConstants.AUDIT_TRAIL_CREATED);
					param.getServiceGrpItem().get(i).setId_audit(id_audit);
					
					int svcGrp = this.updateDAONuked.insert("INSERT.M_SVG.SQL001", param.getServiceGrpItem().get(i));
					AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
					auditHeader.setIdAudit(id_audit);
					auditHeader.setReference(svcGrp+":"+param.getServiceGrpItem().get(i).getSvc_grp_name());
					CommonUtils.auditTrailUpdate(auditHeader);
					CommonUtils.auditTrailEnd(id_audit);//end Audit Trail
					// success
					BLogicMessages msgs = new BLogicMessages();
					BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[]{});
					msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
					result.setMessages(msgs);
				}else{
					/**
					 * Audit Trail
					 */
					Integer id_audit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), 
							BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_SVG, 
							param.getServiceGrpItem().get(i).getSvc_grp()+":"+param.getServiceGrpItem().get(i).getSvc_grp_name(), 
							null, BillingSystemConstants.AUDIT_TRAIL_EDITED);
					param.getServiceGrpItem().get(i).setId_audit(id_audit);
					
					this.updateDAO.execute("UPDATE.M_SVG.SQL001", param.getServiceGrpItem().get(i));
					CommonUtils.auditTrailEnd(id_audit, true);//end Audit Trail
					// success
					BLogicMessages msgs = new BLogicMessages();
					BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[]{});
					msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
					result.setMessages(msgs);
				}
			}
		}
		
		List<ServiceGroupBean> lst=this.queryDAO.executeForObjectList("SELECT.M_SVG.SQL001",null);
        
        if(param.getAction().equals("add"))
        {
            lst.add(new ServiceGroupBean());
        }        
		
        outputDTO.setServiceGrpItem(lst);
        outputDTO.setAction(null);
        
        //wcbeh #231 - Category Maintenance Add Journal No
		List<Map<String, String>> journal_list = this.queryDAO.executeForObjectList("SELECT.M_SVG.SQL003", null);
		outputDTO.setJournal_list(journal_list);
        
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
	private boolean inputIsMulCharCheck(M_SVGS01_03Input input) {
        boolean isMulCharFlg = true;
        
        String isCheckMulCharFlg = getIsCheckMulCharFlg(queryDAO);
        if ("1".equals(isCheckMulCharFlg)) {
            List<ServiceGroupBean> serviceGrpItemList = input.getServiceGrpItem();
            if(serviceGrpItemList!=null && 0<serviceGrpItemList.size()) {
                for(ServiceGroupBean sgb : serviceGrpItemList) {
                     String svc_grp_name = CommonUtils.toString(sgb.getSvc_grp_name());
                     if (CommonUtils.isMulChar(svc_grp_name)) {
                         isMulCharFlg = false;
                         errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{"Category Name", CommonUtils.MUL_CHAR_STR}));
                         break;
                     }
                }
            }
        }
        return isMulCharFlg;
	}
	
	private static String getIsCheckMulCharFlg(QueryDAO queryDAO) {
        String isCheckMulCharFlg = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_SVG.SQL002", null, String.class)).trim();
        
        return isCheckMulCharFlg;
    }
}