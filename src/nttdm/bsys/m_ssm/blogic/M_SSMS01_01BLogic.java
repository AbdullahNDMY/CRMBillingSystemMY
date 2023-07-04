/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : M_SSMS01_01BLogic
 * FILE NAME      : M_SSMS01_01BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_gbs.bean.GSettingBean;
import nttdm.bsys.m_ssm.bean.OutputObject;
import nttdm.bsys.m_ssm.bean.ParameterObject;
import nttdm.bsys.m_ssm.bean.ServiceGroupBean;
import nttdm.bsys.m_ssm.bean.ServiceObjectBean;
import nttdm.bsys.m_ssm.bean.ServiceTypeBean;
import nttdm.bsys.m_ssm.bean.ServiceTypeItemBean;
import nttdm.bsys.m_ssm.dto.M_SSMS01_01Input;
import nttdm.bsys.m_ssm.dto.M_SSMS01_01Output;
import nttdm.bsys.m_ssm.blogic.AbstractM_SSMS01_01BLogic;

/**
 * M_SSMS01_01BLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_SSMS01_01BLogic extends AbstractM_SSMS01_01BLogic {
	private int TOTAL_COLUMN = 0;

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_SSMS01_01Input param) {
		BLogicResult result = new BLogicResult();
		M_SSMS01_01Output output = new M_SSMS01_01Output();
		List<String> errorMessages = new ArrayList<String>();
		List<String> successMessages = new ArrayList<String>();
		
		List<ServiceGroupBean> listBasicInfo = queryDAO.executeForObjectList(
				"M_SSM.selectServiceBasicInfo", null);
		TOTAL_COLUMN = listBasicInfo.size();

		List<ServiceGroupBean> listServiceGroup = queryDAO
				.executeForObjectList("M_SSM.selectServiceGroups", null);

		// List to store all service type with Entry and Report flag of a
		// Service Group
		List<List<ServiceTypeItemBean>> listResult = new ArrayList<List<ServiceTypeItemBean>>();

		// List to store only service type name and id of a Service Group
		List<ServiceTypeBean> listServiceType = new ArrayList<ServiceTypeBean>();

		// Get action type
		String actionType = param.getActionType();

		// get groupId
		String svcGrp = param.getSvc_grp();

		// Do delete if actionType == delete
		if ("delete".equalsIgnoreCase(actionType)) {
			
		}

		// Do update if actionType == update
		if ("update".equalsIgnoreCase(actionType)) {
			// get all idService
			String[] arrIdService = param.getIdService();
			//////// DELETE /////
			String[] deletedIdService = param.getDeletedIdService();
			for (int i=0; i<deletedIdService.length; i++)
			{
				// Check if this idService is already existed in M_PLAN_SVC or not
				ParameterObject checkObject = new ParameterObject();
				checkObject.setIdService(deletedIdService[i]);
				OutputObject outputObj = queryDAO.executeForObject(
						"M_SSM.checkIdExistedInPlanSVC", checkObject,
						OutputObject.class);
	
				if (outputObj.getTotalRecord() > 0) {
					// Throw warning message to user: Could not delete the record because it is in used in other module.
					errorMessages.add("errors.ERR1SC054");
				} else {
					/**
					 * Audit Trail
					 */
				    //Modify Result as ""1:174", it should be M_SSM.SVC_GRP-M_SVG.SVC_GRP_NAME: M_SSM.ID_SERVIC-M_SVC.SVC_DESC
				    String idservice=deletedIdService[i];
	                String svcgrpname="";
	                String svcdesc="";
	                for (ServiceGroupBean groupBean : listServiceGroup)
	                {
	                    if(Integer.toString(groupBean.getSvc_grp()).equals(svcGrp)){
	                        svcgrpname=groupBean.getSvc_grp_name();
	                    }
	                }
	                svcdesc=queryDAO.executeForObject("M_SSM.selectsvcdescOfsvc", idservice,String.class);
	                String referkey=svcGrp+"-"+svcgrpname;
	                String refervalue=idservice+"-"+svcdesc;
					Integer idAudit = auditTrailBegin(param, referkey, refervalue, BillingSystemConstants.AUDIT_TRAIL_DELETED);
					// delete (set IS_DELETED = 1) all item in M_SSM table that was
					// deleted from browser
					ParameterObject delObject = new ParameterObject();
					delObject.setIdService(deletedIdService[i]);
					delObject.setSvcGrp(svcGrp);
					delObject.setIdAudit(idAudit);
					updateDAO.execute("M_SSM.SQL20", delObject);
					auditTrailEnd(idAudit);
				}
			}
			
			//////// UPDATE //////			
			//get number of service info
			String serviceInfoQuantity = param.getServiceInfoQuantity();
			int numServiceInfo = 0;
			if (serviceInfoQuantity!=null)
			{
				try {
					numServiceInfo = Integer.parseInt(serviceInfoQuantity);
				}
				catch (Exception exp)
				{
					exp.printStackTrace();
				}
			}				

			// get list of Entry/Report value to update
			String[] arrE = param.getEntry();
			String[] arrR = param.getReport();
			String[] infoId = param.getInfoId();
			
			// Update M_SSM table for entry/report
			int count = 0;
			boolean hasError = false;
			for (String idService : arrIdService) {
				/**
				 * Audit Trail
				 */
			    //Modify Result as ""1:174", it should be M_SSM.SVC_GRP-M_SVG.SVC_GRP_NAME: M_SSM.ID_SERVIC-M_SVC.SVC_DESC
			    String svcgrpname="";
			    String svcdesc="";
			    for (ServiceGroupBean groupBean : listServiceGroup)
			    {
			        if(Integer.toString(groupBean.getSvc_grp()).equals(svcGrp)){
			            svcgrpname=groupBean.getSvc_grp_name();
			        }
			    }
			    svcdesc=queryDAO.executeForObject("M_SSM.selectsvcdescOfsvc", idService, String.class);
			    String referkey=svcGrp+"-"+svcgrpname;
			    String refervalue=idService+"-"+svcdesc;
				Integer idAudit = auditTrailBegin(param, referkey, refervalue, BillingSystemConstants.AUDIT_TRAIL_EDITED);
				Integer idAudit2 = auditTrailBegin(param, referkey, refervalue, BillingSystemConstants.AUDIT_TRAIL_CREATED);
				OutputObject outputObj1 = null;
				for (int i = 0; i < numServiceInfo; i++) {
					ParameterObject updateObject = new ParameterObject();
					updateObject.setSvcGrp(svcGrp);
					updateObject.setIdService(idService);
					updateObject.setInfoId(infoId[i]);
					updateObject.setEntry(arrE[(numServiceInfo * count) + i]);
					updateObject.setReport(arrR[(numServiceInfo * count) + i]);
					updateObject.setIdLogin(param.getUvo().getId_user());
					updateObject.setIdAudit(idAudit);
					
					OutputObject outputObj = queryDAO.executeForObject(
							"M_SSM.checkIdExistedInSSM", updateObject,
							OutputObject.class);

					try {
						// Check if this idService is already existed in M_SSM
						// or not
						if (outputObj.getTotalRecord() > 0 ) {
							// Check if this idService is deleted in M_SSM or
							// not
							// Only check for the first record
							if (i == 0) {
								outputObj1 = queryDAO.executeForObject(
										"M_SSM.checkIsDeletedInSSM",
										updateObject, OutputObject.class);
							}
							if (outputObj1.getIsDeleted()) {
								// If it is deleted SQL33
								updateDAO.execute("M_SSM.SQL33", updateObject);
							} else {
								// If it is not deleted
								updateDAO.execute("M_SSM.SQL32", updateObject);
							}
						} else {
							// If this idService is not existed then insert a
							// new one
							updateObject.setIdAudit(idAudit2);
							updateDAO.execute("M_SSM.SQL31", updateObject);
						}
					} catch (Exception e) {
						hasError = true;
						// Add save error message
						errorMessages.add("errors.ERR2SC004");
					}
				}
				auditTrailEnd(idAudit);
				auditTrailEnd(idAudit2);
				count++;
			}
			
			if(!hasError) {
				// Add save success message
				successMessages.add("errors.ERR2SC003");
			}
		}
		
		List<ServiceObjectBean> ssmRows = new ArrayList<ServiceObjectBean>();

		// Get value to display
		if (!"".equals(svcGrp)) {
			// Get all service type for selection combobox
			listServiceType = queryDAO.executeForObjectList(
					"M_SSM.selectServiceType", svcGrp, 0, Integer.MAX_VALUE);
			
			//get all SSM row of a service group			
			ssmRows = queryDAO.executeForObjectList("M_SSM.selectServicesOfSSM", svcGrp, 0, Integer.MAX_VALUE);
			
			for (int i=0; i<ssmRows.size(); i++)
			{
				// get all records from M_SSM table for a service group id
				List<ServiceTypeItemBean> listSSM = new ArrayList<ServiceTypeItemBean>();
				listSSM = queryDAO.executeForObjectList(
						"M_SSM.selectSSMFromService", ssmRows.get(i), 0,
						Integer.MAX_VALUE);
				listResult.add(listSSM);
			}
		}

		output.setErrorMessages(errorMessages);
		output.setSuccessMessages(successMessages);
		output.setListServiceGroup(listServiceGroup);
		output.setListServiceType(listServiceType);
		output.setListSsmRows(ssmRows);
		output.setListResult(listResult);
		output.setListServiceBasicInfo(listBasicInfo);
		result.setResultString("success");
		result.setResultObject(output);
		return result;
	}

	/**
	 * Audit Trail
	 */
	private Integer auditTrailBegin(M_SSMS01_01Input param, String svcGroup, String idService, String action) {
		return CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_M, 
				BillingSystemConstants.SUB_MODULE_M_SSM, 
				svcGroup+":"+idService, null, action);
	}
	
	private void auditTrailEnd(Integer idAudit) {
		CommonUtils.auditTrailEnd(idAudit, true);//End Audit Trail
	}
}
