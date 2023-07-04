/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST04
 * FUNCTION       : INSERT_UPDATE_M_CUST
 * FILE NAME      : M_CSTR04BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.bean.UserBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;
import nttdm.bsys.m_cst.bean.CustomerBean;
import nttdm.bsys.m_cst.bean.M_CSTFormBean;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.CustomerDto;
import nttdm.bsys.m_cst.dto.M_CSTR04Input;
import nttdm.bsys.m_cst.dto.M_CSTR04Output;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data VietNam
 */

public class M_CSTR04BLogic extends AbstractM_CSTR04BLogic {
	
	/**
	 * <div>INSERT_UPDATE_M_CUST_CTC_OC sql</div>
	 */
	private static final String INSERT_UPDATE_M_CUST_CTC_OC = "SELECT.M_CST.SQL012";
	
	/**
	 * <div>ERRORS_ERR2SC004 error message</div>
	 */
	private static final String ERRORS_ERR2SC004 = "info.ERR2SC004";
	
	/**
	 * <div>Error message</div>
	 */
	private static final String ERRORS_ERR2SC003 = "info.ERR2SC003";
	
	/**
	 * <div>CONTACT_TYPE_OC contact type</div>
	 */
	private static final String CONTACT_TYPE_OC  = "OC";
	
	/**
	 * <div>updateDAO class</div>
	 */
	private UpdateDAO updateDAO;
	
	/**
	 * <div>queryDAO class</div>
	 */
	private QueryDAO queryDAO;
	
	private BLogicMessages errors;
	
	private BLogicMessages messages;

	public BLogicResult execute(M_CSTR04Input param) {
		//declare blogic result
		BLogicResult result = new BLogicResult();
		//declare error message
		errors = new BLogicMessages();
		//declare message
		messages = new BLogicMessages();
		try{
			//set login_id
			param.setUvo(param.getUvoObject().getId_user());
			// Handn for M_CSTR02_r11
			// validate isValidAffiliate
			boolean validateOK = true;
			if(!inputIsMulCharCheck(param)) {
			    validateOK = false;
			    //set return error
                result.setErrors(errors);
                //set return string
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);   
			} else {
			    if (!isValidAffiliate(param)){
	                validateOK = false;
	                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Affiliate NTT"));
	                //set return error
	                result.setErrors(errors);
	                //set return string
	                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);   
	                
                } else if (!"1".equals(param.getPopupClickYesFlg())) {
                    // check duplicated C.CUST_ACC_NO(PeopleSoft Acc. No)
                    List<CustomerBean> custAccNoList = Util.checkDuplicatedCustAccNo(param.getCust_acc_no(), param.getId_cust(), queryDAO);
                    if (custAccNoList.size() > 0){
                        validateOK = false;
                        // duplicated PeopleSoft Acc. No message
                        StringBuffer popupInfo = new StringBuffer();
                        popupInfo.append("Warning<br/>");
                        popupInfo.append("Peoplesoft Acc ID duplicated with customer:<br/>");
                        for(CustomerBean cust : custAccNoList){
                            popupInfo.append(cust.getCust_name()).append("(").append(cust.getId_cust()).append(")<br/>");
                        }
                        popupInfo.append("Do you want to proceed?<br/>");
                        popupInfo.append("Click Yes to save, No to cancel.");;

                        M_CSTR04Output output = new M_CSTR04Output();
                        output.setPeopleSoftPopupInfo(popupInfo.toString());
                        output.setMode(param.getMode());
                        output.setId_cust(param.getId_cust());
                        result.setResultObject(output);

                        //errors.add(Globals.ERROR_KEY, new BLogicMessage(Util.ERRORS_ERR1SC006,  PropertyUtil.getProperty("screen.common.cust_acc_no")));
                        //set return error
                        result.setErrors(errors);
                        //set return string
                        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);   
                    }
			    }
			}
			if (validateOK) {
				Integer idAudit = null;
				CustomerDto custDto = param.getCustomerDto();
                //check Manager
                custDto = getCustomerDto(custDto);
				//new mode			
				if(param.getMode()!=null && String.valueOf(param.getMode()).equals(M_CSTFormBean.NEWMODE)){
					//get max id_cust
					/*
					 * added, common for bug 2943, by longhb
					 * from line 111 to 113
					 */
					//String id_cust = queryDAO.executeForObject(Util.GET_SEQ_ID, null, String.class);
					G_CDM_P01 gcdm = new G_CDM_P01(this.getQueryDAO(),this.getUpdateDAO(),param.getUvoObject().getId_user());
					String id_cust = gcdm.getGenerateCode("CSTID", param.getUvoObject().getId_user());
					//set id_cust
					param.setId_cust(id_cust);
					custDto.setId_cust(id_cust);
					//set customer_type
					param.setCustomer_type(Util.CUSTOMER_TYPE_CORP);
					custDto.setCustomer_type(Util.CUSTOMER_TYPE_CORP);
					/**
					 * Audit Trail
					 */
					idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), "M", "M-CST", 
							id_cust+":"+CommonUtils.toString(param.getCust_name()), 
							null, BillingSystemConstants.AUDIT_TRAIL_CREATED);
					custDto.setIdAudit(idAudit);
					//insert m_cust
					updateDAO.execute(Util.INSERT_M_CUST, custDto);
					CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				} else if (param.getMode()!=null && String.valueOf(param.getMode()).equals(M_CSTFormBean.EDITMODE)){
					/**
					 * Audit Trail
					 */
					idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), "M", "M-CST", 
							CommonUtils.toString(param.getId_cust())+":"+CommonUtils.toString(param.getCust_name()), 
							null, BillingSystemConstants.AUDIT_TRAIL_EDITED);
					custDto.setIdAudit(idAudit);
					//update m_cust
					updateDAO.execute(Util.UPDATE_M_CUST, custDto);
					CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				}
				//insert m_cust_adr ra
				param.setAdr_type(Util.ADR_TYPE_RA);
				custDto.setAdr_type(Util.ADR_TYPE_RA);
				updateDAO.execute(Util.INSERT_UPDATE_M_CUST_ADR_RA, custDto);
				//insert m_cust_adr ba
				param.setAdr_type(Util.ADR_TYPE_BA);
				custDto.setAdr_type(Util.ADR_TYPE_BA);
				updateDAO.execute(Util.INSERT_UPDATE_M_CUST_ADR_BA, custDto);
				//insert m_cust_adr_ba2
				param.setAdr_type(Util.ADR_TYPE_BA2);
                custDto.setAdr_type(Util.ADR_TYPE_BA2);
                updateDAO.execute(Util.INSERT_UPDATE_M_CUST_ADR_BA2, custDto);
                //insert m_cust_adr_ba3
                param.setAdr_type(Util.ADR_TYPE_BA3);
                custDto.setAdr_type(Util.ADR_TYPE_BA3);
                updateDAO.execute(Util.INSERT_UPDATE_M_CUST_ADR_BA3, custDto);
                //insert m_cust_adr_ba4
                param.setAdr_type(Util.ADR_TYPE_BA4);
                custDto.setAdr_type(Util.ADR_TYPE_BA4);
                updateDAO.execute(Util.INSERT_UPDATE_M_CUST_ADR_BA4, custDto);
				//insert m_cust_adr ca
				param.setAdr_type(Util.ADR_TYPE_CA);
				custDto.setAdr_type(Util.ADR_TYPE_CA);
				updateDAO.execute(Util.INSERT_UPDATE_M_CUST_ADR_CA, custDto);
				// insert m_cust_adr ta
				param.setAdr_type(Util.ADR_TYPE_TA);
				custDto.setAdr_type(Util.ADR_TYPE_TA);
				updateDAO.execute(Util.INSERT_UPDATE_M_CUST_ADR_TA, custDto);
				//insert m_cust_ctc pc
				param.setContact_type(Util.CONTACT_TYPE_PC);
				custDto.setContact_type(Util.CONTACT_TYPE_PC);
				updateDAO.execute(Util.INSERT_UPDATE_M_CUST_CTC_PC, custDto);
				//insert m_cust_ctc bc
				param.setContact_type(Util.CONTACT_TYPE_BC);
				custDto.setContact_type(Util.CONTACT_TYPE_BC);
				updateDAO.execute(Util.INSERT_UPDATE_M_CUST_CTC_BC, custDto);
				//insert m_cust_ctc bc2
				param.setContact_type(Util.CONTACT_TYPE_BC2);
                custDto.setContact_type(Util.CONTACT_TYPE_BC2);
                updateDAO.execute(Util.INSERT_UPDATE_M_CUST_CTC_BC2, custDto);
				//insert m_cust_ctc bc3
                param.setContact_type(Util.CONTACT_TYPE_BC3);
                custDto.setContact_type(Util.CONTACT_TYPE_BC3);
                updateDAO.execute(Util.INSERT_UPDATE_M_CUST_CTC_BC3, custDto);
				//insert m_cust_ctc bc4
                param.setContact_type(Util.CONTACT_TYPE_BC4);
                custDto.setContact_type(Util.CONTACT_TYPE_BC4);
                updateDAO.execute(Util.INSERT_UPDATE_M_CUST_CTC_BC4, custDto);
				//insert m_cust_ctc tc
				param.setContact_type(Util.CONTACT_TYPE_TC);
				custDto.setContact_type(Util.CONTACT_TYPE_TC);
				updateDAO.execute(Util.INSERT_UPDATE_M_CUST_CTC_TC, custDto);
				//insert m_cust_ctc oc
				param.setContact_type(M_CSTR04BLogic.CONTACT_TYPE_OC);
				custDto.setContact_type(M_CSTR04BLogic.CONTACT_TYPE_OC);
				updateDAO.execute(M_CSTR04BLogic.INSERT_UPDATE_M_CUST_CTC_OC, custDto);
				//set return message
				messages.add(Globals.MESSAGE_KEY, new BLogicMessage(M_CSTR04BLogic.ERRORS_ERR2SC003));
				// Handn for M_CSTR02_r11
				M_CSTR04Output output = new M_CSTR04Output();
				output.setMode(M_CSTFormBean.READONLY);
				output.setId_cust(param.getId_cust());
				output.setIdAudit(idAudit);
				output.setPeopleSoftPopupInfo("");
				result.setResultObject(output);
				// end Handn for M_CSTR02_r11	
				result.setMessages(messages);	
				//set return string
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);	
			}
		}catch(Exception ex){
			//add error inserting/updating failure
			errors.add(Globals.ERROR_KEY, new BLogicMessage(M_CSTR04BLogic.ERRORS_ERR2SC004));
			//set return error
			result.setErrors(errors);
			//set return string
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);	
		}
		return result;
	}
	
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	
	private boolean isValidAffiliate(M_CSTR04Input param){
		if (param.getInter_comp() != null && param.getInter_comp().equals("on")){
			if (param.getAffiliate_ntt() == null || param.getAffiliate_ntt().equals("")){
				return false;
			}
		}
		return true;
	}
	
	private boolean inputIsMulCharCheck(M_CSTR04Input input) {
        boolean isMulCharFlg = true;
        
        String isCheckMulCharFlg = Util.getIsCheckMulCharFlg(queryDAO);
        //Customer Name
        String cust_name = CommonUtils.toString(input.getCust_name());
        //Company Email
        String co_email = CommonUtils.toString(input.getCo_email());
        //Registered Address
        String ra_adr_line1 = CommonUtils.toString(input.getRa_adr_line1());
        String ra_adr_line2 = CommonUtils.toString(input.getRa_adr_line2());
        String ra_adr_line3 = CommonUtils.toString(input.getRa_adr_line3());
        String ra_zip_code = CommonUtils.toString(input.getRa_zip_code());
        String ra_country_id = CommonUtils.toString(input.getRa_country_id());
        //Billing Address
        String ba_adr_line1 = CommonUtils.toString(input.getBa_adr_line1());
        String ba_adr_line2 = CommonUtils.toString(input.getBa_adr_line2());
        String ba_adr_line3 = CommonUtils.toString(input.getBa_adr_line3());
        String ba_zip_code = CommonUtils.toString(input.getBa_zip_code());
        String ba_country_id = CommonUtils.toString(input.getBa_country_id());
        //Billing Address2
        String ba_adr2_line1 = CommonUtils.toString(input.getBa_adr2_line1());
        String ba_adr2_line2 = CommonUtils.toString(input.getBa_adr2_line2());
        String ba_adr2_line3 = CommonUtils.toString(input.getBa_adr2_line3());
        String ba_zip_code2 = CommonUtils.toString(input.getBa_zip_code2());
        String ba_country2_id = CommonUtils.toString(input.getBa_country2_id());
        //Billing Address3
        String ba_adr3_line1 = CommonUtils.toString(input.getBa_adr3_line1());
        String ba_adr3_line2 = CommonUtils.toString(input.getBa_adr3_line2());
        String ba_adr3_line3 = CommonUtils.toString(input.getBa_adr3_line3());
        String ba_zip_code3 = CommonUtils.toString(input.getBa_zip_code3());
        String ba_country3_id = CommonUtils.toString(input.getBa_country3_id());
        //Billing Address4
        String ba_adr4_line1 = CommonUtils.toString(input.getBa_adr4_line1());
        String ba_adr4_line2 = CommonUtils.toString(input.getBa_adr4_line2());
        String ba_adr4_line3 = CommonUtils.toString(input.getBa_adr4_line3());
        String ba_zip_code4 = CommonUtils.toString(input.getBa_zip_code4());
        String ba_country4_id = CommonUtils.toString(input.getBa_country4_id());
        //Contact Name Of Billing Contact
        String bc_contact_name = CommonUtils.toString(input.getBc_contact_name());
        //Contact Name Of Billing Contact2
        String bc2_contact_name = CommonUtils.toString(input.getBc2_contact_name());
        //Contact Name Of Billing Contact3
        String bc3_contact_name = CommonUtils.toString(input.getBc3_contact_name());
        //Contact Name Of Billing Contact4
        String bc4_contact_name = CommonUtils.toString(input.getBc4_contact_name());
        //Email Of Billing Contact
        String bc_email = CommonUtils.toString(input.getBc_email());
        //Email(CC) Of Billing Contact
        String bc_email_cc = CommonUtils.toString(input.getBc_email_cc());
        //Email(To) Of Billing Contact2
        String bc2_email_to = CommonUtils.toString(input.getBc2_email_to());
        //Email(CC) Of Billing Contact2
        String bc2_email_cc = CommonUtils.toString(input.getBc2_email_cc());
        //Email(To) Of Billing Contact3
        String bc3_email_to = CommonUtils.toString(input.getBc3_email_to());
        //Email(CC) Of Billing Contact3
        String bc3_email_cc = CommonUtils.toString(input.getBc3_email_cc());
        //Email(To) Of Billing Contact4
        String bc4_email_to = CommonUtils.toString(input.getBc4_email_to());
        //Email(CC) Of Billing Contact4
        String bc4_email_cc = CommonUtils.toString(input.getBc4_email_cc());
        //Email(To) Of Primary Contact
        String pc_email = CommonUtils.toString(input.getPc_email());
        //Email(CC) Of Primary Contact
        String pc_email_cc = CommonUtils.toString(input.getPc_email_cc());
        //Email(To) Of Technical Contact
        String tc_email = CommonUtils.toString(input.getTc_email());
        //Email(CC) Of Technical Contact
        String tc_email_cc = CommonUtils.toString(input.getTc_email_cc());
        //Email(To) Of Other Contact
        String oc_email = CommonUtils.toString(input.getOc_email());
        //Email(CC) Of Other Contact
        String oc_email_cc = CommonUtils.toString(input.getOc_email_cc());
        
        if (CommonUtils.isEmpty(cust_name.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Customer Name"));
        }
        if (!CommonUtils.isEmpty(co_email.trim())) {
            if (!GenericValidator.isEmail(co_email.trim())) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Company Email"));
            }
        }
        if (CommonUtils.isEmpty(ra_adr_line1.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Line 1 Of Registered Address"));
        }
        if (CommonUtils.isEmpty(ra_zip_code.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Zip Code Of Registered Address"));
        }
        if (CommonUtils.isEmpty(ra_country_id.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Country Of Registered Address"));
        }
        if (CommonUtils.isEmpty(ba_adr_line1.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Line 1 Of Billing Address 1"));
        }
        if (CommonUtils.isEmpty(ba_zip_code.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Zip Code Of Billing Address 1"));
        }
        if (CommonUtils.isEmpty(ba_country_id.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Country Of Billing Address 1"));
        }
        /*if (CommonUtils.isEmpty(ba_country2_id.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Country Of Billing Address 1"));
        }
        if (CommonUtils.isEmpty(ba_country3_id.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Country Of Billing Address 1"));
        }
        if (CommonUtils.isEmpty(ba_country4_id.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Country Of Billing Address 1"));
        }*/
        if (CommonUtils.isEmpty(bc_contact_name.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Contact Name Of Billing Contact 1"));
        }
        //billing contact 1 ==> email to
        if (!CommonUtils.isEmpty(bc_email.trim())) {
        	String[] bc=bc_email.split(";");
        	for(String email:bc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(To) Of Billing Contact 1"));
            }
        	}
        }
        //billing contact 1 ==> email cc
        if (!CommonUtils.isEmpty(bc_email_cc.trim())) {
        	String[] bc=bc_email_cc.split(";");
        	for(String email:bc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(Cc) Of Billing Contact 1"));
            }
        	}
        }
        // billing contact 1 email to maxlength=300 
        if (bc_email.trim().length() > 300) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(To) Of Billing Contact 1", 300 }));
        }
        // billing contact 1 email cc maxlength=300 
        if (bc_email_cc.trim().length() > 300) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(Cc) Of Billing Contact 1", 300 }));
        }
        //billing contact 2 ==> email to
        if (!CommonUtils.isEmpty(bc2_email_to.trim())) {
        	String[] bc2=bc2_email_to.split(";");
        	for(String email:bc2){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(To) Of Billing Contact 2"));
            }
        	}
        }
        //billing contact 2 ==> email cc
        if (!CommonUtils.isEmpty(bc2_email_cc.trim())) {
        	String[] bc2=bc2_email_cc.split(";");
        	for(String email:bc2){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(Cc) Of Billing Contact 2"));
            }
        	}
        }
        // billing contact 2 email to maxlength=300 
        if (bc2_email_to.trim().length() > 300) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(To) Of Billing Contact 2", 300 }));
        }
        // billing contact 2 email cc maxlength=300 
        if (bc2_email_cc.trim().length() > 300) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(Cc) Of Billing Contact 2", 300 }));
        }
        //billing contact 3 ==> email to
        if (!CommonUtils.isEmpty(bc3_email_to.trim())) {
        	String[] bc3=bc3_email_to.split(";");
        	for(String email:bc3){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(To) Of Billing Contact 3"));
            }
        	}
        }
        //billing contact 3 ==> email cc
        if (!CommonUtils.isEmpty(bc3_email_cc.trim())) {
        	String[] bc3=bc3_email_cc.split(";");
        	for(String email:bc3){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(Cc) Of Billing Contact 3"));
            }
        	}
        }
        // billing contact 3 email to maxlength=300 
        if (bc3_email_to.trim().length() > 300) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(To) Of Billing Contact 3", 300 }));
        }
        // billing contact 3 email cc maxlength=300 
        if (bc3_email_cc.trim().length() > 300) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(Cc) Of Billing Contact 3", 300 }));
        }
        //billing contact 4 ==> email to
        if (!CommonUtils.isEmpty(bc4_email_to.trim())) {
        	String[] bc4=bc4_email_to.split(";");
        	for(String email:bc4){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(To) Of Billing Contact 4"));
            }
        	}
        }
        //billing contact 4 ==> email cc
        if (!CommonUtils.isEmpty(bc4_email_cc.trim())) {
        	String[] bc4=bc4_email_cc.split(";");
        	for(String email:bc4){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(Cc) Of Billing Contact 4"));
            }
        	}
        }
        // billing contact 4 email to maxlength=300 
        if (bc4_email_to.trim().length() > 300) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(To) Of Billing Contact 4", 300 }));
        }
        // billing contact 4 email cc maxlength=300 
        if (bc4_email_cc.trim().length() > 300) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Email(Cc) Of Billing Contact 4", 300 }));
        }
        if (!CommonUtils.isEmpty(pc_email.trim())) {
        	String[] pc=pc_email.split(";");
        	for(String email:pc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(To) Of Primary Contact"));
            }
        	}
        }
        if (!CommonUtils.isEmpty(pc_email_cc.trim())) {
        	String[] pc=pc_email_cc.split(";");
        	for(String email:pc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(cc) Of Primary Contact"));
            }
        	}
        }
        if (!CommonUtils.isEmpty(tc_email.trim())) {
        	String[] tc=tc_email.split(";");
        	for(String email:tc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(To) Of Technical Contact"));
            }
        	}
        }
        if (!CommonUtils.isEmpty(tc_email_cc.trim())) {
        	String[] tc=tc_email_cc.split(";");
        	for(String email:tc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(cc) Of Primary Contact"));
            }
        	}
        }
        if (!CommonUtils.isEmpty(oc_email.trim())) {
        	String[] oc=oc_email.split(";");
        	for(String email:oc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(To) Of Other Contact"));
            }
        	}
        }
        if (!CommonUtils.isEmpty(oc_email_cc.trim())) {
        	String[] oc=oc_email_cc.split(";");
        	for(String email:oc){
            if (!GenericValidator.isEmail(email)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email(cc) Of Other Contact"));
            }
        	}
        }
        if ("1".equals(isCheckMulCharFlg)) {
            //Company Reg. No.
            String co_regno = CommonUtils.toString(input.getCo_regno());
            //Company Website
//          String co_website = CommonUtils.toString(input.getCo_website());
            //Telephone No.
//          String co_tel_no = CommonUtils.toString(input.getCo_tel_no());
//          //Fax No.
//          String co_fax_no = CommonUtils.toString(input.getCo_fax_no());
//          //PeopleSoft Acc. No. 
//          String cust_acc_no = CommonUtils.toString(input.getCust_acc_no());
//          //GBOC Acc No.
//          String gboc_acc_no = CommonUtils.toString(input.getGboc_acc_no());
//          //Reference Customer ID
//          String others_ref_no = CommonUtils.toString(input.getOthers_ref_no());
//          //Salesforce.com Acc. ID
//          String sales_force_acc_id = CommonUtils.toString(input.getSales_force_acc_id());
//          //Account Manager (Primary) 
//          String managerPrimary = CommonUtils.toString(input.getManagerPrimary());
//          //Account Manager (Secondary)
//          String managerSecondary = CommonUtils.toString(input.getManagerSecondary());
            //Correspondence Address
            String ca_adr_line1 = CommonUtils.toString(input.getCa_adr_line1());
            String ca_adr_line2 = CommonUtils.toString(input.getCa_adr_line2());
            String ca_adr_line3 = CommonUtils.toString(input.getCa_adr_line3());
            String ca_zip_code = CommonUtils.toString(input.getCa_zip_code());
            //Technical Address 
            String ta_adr_line1 = CommonUtils.toString(input.getTa_adr_line1());
            String ta_adr_line2 = CommonUtils.toString(input.getTa_adr_line2());
            String ta_adr_line3 = CommonUtils.toString(input.getTa_adr_line3());
            String ta_zip_code = CommonUtils.toString(input.getTa_zip_code());
            //Designation Of Billing Contact
//          String bc_designation = CommonUtils.toString(input.getBc_designation());
            //Telephone No. Of Billing Contact
//          String bc_tel_no = CommonUtils.toString(input.getBc_tel_no());
//          //DID No. Of Billing Contact
//          String bc_did_no = CommonUtils.toString(input.getBc_did_no());
//          //Fax No. Of Billing Contact
//          String bc_fax_no = CommonUtils.toString(input.getBc_fax_no());
//          //Mobile No. Of Billing Contact
//          String bc_mobile_no = CommonUtils.toString(input.getBc_mobile_no());
            //Contact Name Of Primary Contact
            String pc_contact_name = CommonUtils.toString(input.getPc_contact_name());
            //Designation Of Primary Contact
//          String pc_designation = CommonUtils.toString(input.getPc_designation());
            //Telephone No. Of Primary Contact
//          String pc_tel_no = CommonUtils.toString(input.getPc_tel_no());
//          //DID No. Of Primary Contact
//          String pc_did_no = CommonUtils.toString(input.getPc_did_no());
//          //Fax No. Of Primary Contact
//          String pc_fax_no = CommonUtils.toString(input.getPc_fax_no());
//          //Mobile No. Of Primary Contact
//          String pc_mobile_no = CommonUtils.toString(input.getPc_mobile_no());
            //Contact Name Of Technical Contact
            String tc_contact_name = CommonUtils.toString(input.getTc_contact_name());
            //Designation Of Technical Contact
//          String tc_designation = CommonUtils.toString(input.getTc_designation());
            //Telephone No. Of Technical Contact
//          String tc_tel_no = CommonUtils.toString(input.getTc_tel_no());
//          //DID No. Of Technical Contact
//          String tc_did_no = CommonUtils.toString(input.getTc_did_no());
//          //Fax No. Of Technical Contact
//          String tc_fax_no = CommonUtils.toString(input.getTc_fax_no());
//          //Mobile No. Of Technical Contact
//          String tc_mobile_no = CommonUtils.toString(input.getTc_mobile_no());
            //Contact Name Of Other Contact
            String oc_contact_name = CommonUtils.toString(input.getOc_contact_name());
            //Designation Of Other Contact
//          String oc_designation = CommonUtils.toString(input.getOc_designation());
            //Telephone No. Of Other Contact
//          String oc_tel_no = CommonUtils.toString(input.getOc_tel_no());
//          //DID No. Of Other Contact
//          String oc_did_no = CommonUtils.toString(input.getOc_did_no());
//          //Fax No. Of Other Contact
//          String oc_fax_no = CommonUtils.toString(input.getOc_fax_no());
//          //Mobile No. Of Other Contact
//          String oc_mobile_no = CommonUtils.toString(input.getOc_mobile_no());
            
            if (CommonUtils.isMulChar(cust_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Customer Name", CommonUtils.MUL_CHAR_STR}));
            }
            
            if (CommonUtils.isMulChar(co_regno)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Company Reg. No.", CommonUtils.MUL_CHAR_STR}));
            }
//          if (CommonUtils.isMulChar(co_website)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Company Website", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(co_tel_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Telephone No.", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(co_fax_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Fax No.", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(cust_acc_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"PeopleSoft Acc. No.", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(gboc_acc_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"GBOC Acc No.", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(others_ref_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Reference Customer ID", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(sales_force_acc_id)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Salesforce.com Acc. ID", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(managerPrimary)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Account Manager (Primary)", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(managerSecondary)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Account Manager (Secondary)", CommonUtils.MUL_CHAR_STR}));
//            }
            if (CommonUtils.isMulChar(ra_adr_line1)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Registered Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ra_adr_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Registered Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ra_adr_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Registered Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ra_zip_code)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Registered Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr_line1)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Billing Address 1", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Billing Address 1", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Billing Address 1", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_zip_code)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Billing Address 1", CommonUtils.MUL_CHAR_STR}));
            }
            //billing address 2
            if (CommonUtils.isMulChar(ba_adr2_line1)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Billing Address 2", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr2_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Billing Address 2", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr2_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Billing Address 2", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_zip_code2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Billing Address 2", CommonUtils.MUL_CHAR_STR}));
            }
            //billing address 3
            if (CommonUtils.isMulChar(ba_adr3_line1)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Billing Address 3", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr3_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Billing Address 3", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr3_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Billing Address 3", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_zip_code3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Billing Address 3", CommonUtils.MUL_CHAR_STR}));
            }
            //billing address 4
            if (CommonUtils.isMulChar(ba_adr4_line1)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Billing Address 4", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr4_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Billing Address 4", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr4_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Billing Address 4", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_zip_code4)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Billing Address 4", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ca_adr_line1)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Correspondence Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ca_adr_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Correspondence Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ca_adr_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Correspondence Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ca_zip_code)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Correspondence Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ta_adr_line1)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Technical Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ta_adr_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Technical Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ta_adr_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Technical Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ta_zip_code)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Technical Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(bc_contact_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Contact Name Of Billing Contact 1", CommonUtils.MUL_CHAR_STR}));
            }
            //billing contact 2
            if (CommonUtils.isMulChar(bc2_contact_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Contact Name Of Billing Contact 2", CommonUtils.MUL_CHAR_STR}));
            }
            //billing contact 3
            if (CommonUtils.isMulChar(bc3_contact_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Contact Name Of Billing Contact 3", CommonUtils.MUL_CHAR_STR}));
            }
            //billing contact 4
            if (CommonUtils.isMulChar(bc4_contact_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Contact Name Of Billing Contact 4", CommonUtils.MUL_CHAR_STR}));
            }
//          if (CommonUtils.isMulChar(bc_designation)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Designation Of Billing Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(bc_tel_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Telephone No. Of Billing Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(bc_did_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"DID No. Of Billing Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(bc_fax_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Fax No. Of Billing Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//          if (CommonUtils.isMulChar(bc_mobile_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Mobile No. Of Billing Contact", CommonUtils.MUL_CHAR_STR}));
//            }
            if (CommonUtils.isMulChar(pc_contact_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Contact Name Of Primary Contact", CommonUtils.MUL_CHAR_STR}));
            }
//            if (CommonUtils.isMulChar(pc_designation)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Designation Of Primary Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(pc_tel_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Telephone No. Of Primary Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(pc_did_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"DID No. Of Primary Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(pc_fax_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Fax No. Of Primary Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(pc_mobile_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Mobile No. Of Primary Contact", CommonUtils.MUL_CHAR_STR}));
//            }
            if (CommonUtils.isMulChar(tc_contact_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Contact Name Of Technical Contact", CommonUtils.MUL_CHAR_STR}));
            }
//            if (CommonUtils.isMulChar(tc_designation)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Designation Of Technical Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(tc_tel_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Telephone No. Of Technical Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(tc_did_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"DID No. Of Technical Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(tc_fax_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Fax No. Of Technical Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(tc_mobile_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Mobile No. Of Technical Contact", CommonUtils.MUL_CHAR_STR}));
//            }
            if (CommonUtils.isMulChar(oc_contact_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Contact Name Of Other Contact", CommonUtils.MUL_CHAR_STR}));
            }
//            if (CommonUtils.isMulChar(oc_designation)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Designation Of Other Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(oc_tel_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Telephone No. Of Other Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(oc_did_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"DID No. Of Other Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(oc_fax_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Fax No. Of Other Contact", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(oc_mobile_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Mobile No. Of Other Contact", CommonUtils.MUL_CHAR_STR}));
//            }
        }
        
        return isMulCharFlg;
    }
	   
    /**
     * check ManagerPrimary and ManagerSecondary
     * @param custDto
     * @return custDto
     */
    private CustomerDto getCustomerDto(CustomerDto custDto) {
        List <UserBean> listUser = new ArrayList<UserBean>();
        listUser = queryDAO.executeForObjectList("SELECT.M_CST.USERLIST",null);     
        String managerPrimary = custDto.getManagerPrimary().toLowerCase();
        String managerSecondary = custDto.getManagerSecondary().toLowerCase();
        int count = 0;
        for (UserBean user : listUser) {
            if(managerPrimary.equals(user.getUser_name().toLowerCase())){
                custDto.setManagerPrimary(user.getUser_name());             
                count ++;
            }
            if(managerSecondary.equals(user.getUser_name().toLowerCase())){
                custDto.setManagerSecondary(user.getUser_name());               
                count ++;
            }
            if(count == 2){
                break;
            }
        }       
        return custDto;
    }
}