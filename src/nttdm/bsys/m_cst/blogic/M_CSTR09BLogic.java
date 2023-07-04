/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS09
 * FUNCTION       : Set new value
 * FILE NAME      : M_CSTR09BLogic.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;
import nttdm.bsys.m_cst.bean.CustomerBean;
import nttdm.bsys.m_cst.bean.M_CSTFormBean;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.CustomerDto;
import nttdm.bsys.m_cst.dto.M_CSTR04Output;
import nttdm.bsys.m_cst.dto.M_CSTR09BLogicInput;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;

/******
 * Class M_CSTR08BLogicBLogic<br>
 * <ul>
 * <li>Class save BankInformation
 * <li>
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1
 * ******/
public class M_CSTR09BLogic extends AbstractM_CSTR09BLogic {

    private BLogicMessages errors;
    
    private BLogicMessages messages;
    
	public BLogicResult execute(M_CSTR09BLogicInput param) {
		//declare blogic result
		BLogicResult result = new BLogicResult();
		//declare error message
		errors = new BLogicMessages();
		//declare message
		messages = new BLogicMessages();
		try{
			//set login_id
			param.setUvo(param.getUvoObject().getId_user());
			if(!inputIsMulCharCheck(param)) {
			    //set return error
                result.setErrors(errors);
                //set return string
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			} else {
		        boolean validateOK = true;
		        // check duplicated C.CUST_ACC_NO
	            if (!"1".equals(param.getPopupClickYesFlg())){
                    // check duplicated C.CUST_ACC_NO(PeopleSoft Acc. No)
                    List<CustomerBean> custAccNoList = Util.checkDuplicatedCustAccNo(param.getCust_acc_no(), param.getId_cust(), queryDAO);
                    if (custAccNoList.size() > 0){
                        validateOK =  false;
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
                        
                        //set return error
                        result.setErrors(errors);
                        //set return string
                        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);   
                    }
	            }

	            if ( validateOK ) {
	                CustomerDto custDto = param.getCustomerDto();
	                Integer idAudit = null;
	                //new mode          
	                if((param.getMode()!=null && String.valueOf(param.getMode()).equals(M_CSTFormBean.NEWMODE)) || param.getMode() == null || param.getMode().equals("")){
	                    //get max id_cust
	                    /*
	                     * added, common for bug 2943, by longhb
	                     * from line 69 to 71
	                     */
	                    //String id_cust = queryDAO.executeForObject(Util.GET_SEQ_ID, null, String.class);
	                    G_CDM_P01 gcdm = new G_CDM_P01(this.getQueryDAO(),this.getUpdateDAO(),param.getUvoObject().getId_user());
	                    String id_cust = gcdm.getGenerateCode("CSTID", param.getUvoObject().getId_user());
	                    //set id_cust
	                    param.setId_cust(id_cust);
	                    custDto.setId_cust(id_cust);
	                    //set customer_type
	                    param.setCustomer_type(Util.CUSTOMER_TYPE_CONS);
	                    custDto.setCustomer_type(Util.CUSTOMER_TYPE_CONS);
	                    /**
	                     * Audit Trail
	                     */
	                    idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_CST, 
	                            id_cust + ":" + CommonUtils.toString(param.getCust_name()), null, BillingSystemConstants.AUDIT_TRAIL_CREATED);
	                    custDto.setIdAudit(idAudit);
	                    
	                    //update by Jing Start
	                    /*custDto.setCompany_category(" ");
	                    custDto.setCompany_sub_category(" ");
	                    custDto.setCompany_bank_info(" ");*/
	                    
	                    custDto.setCompany_category(param.getCompany_category());
	                    custDto.setCompany_sub_category(param.getCompany_sub_category());
	                    custDto.setCompany_bank_info(param.getCompany_bank_info());
	                    custDto.setEnd_user(param.getEnd_user());
	                    custDto.setCompany_type(param.getCompany_type());
	                    //updaye by Jing End
	                    
	                    //insert m_cust
	                    updateDAO.execute("INSERT.M_CST.SQL004.BL09", custDto);
	                    CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
	                } else if (param.getMode()!=null && String.valueOf(param.getMode()).equals(M_CSTFormBean.EDITMODE)){
	                    /**
	                     * Audit Trail
	                     */
	                    idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_CST, 
	                            CommonUtils.toString(param.getId_cust())
	                            + ":" + CommonUtils.toString(param.getCust_name()), null, BillingSystemConstants.AUDIT_TRAIL_EDITED);
	                    custDto.setIdAudit(idAudit);
	                    
	                    //update by Jing Start
	                    /*custDto.setCompany_category(" ");
	                    custDto.setCompany_sub_category(" ");
	                    custDto.setCompany_bank_info(" ");*/
	                    
	                    custDto.setCompany_category(param.getCompany_category());
	                    custDto.setCompany_sub_category(param.getCompany_sub_category());
	                    custDto.setCompany_bank_info(param.getCompany_bank_info());
	                    custDto.setEnd_user(param.getEnd_user());
	                    custDto.setCompany_type(param.getCompany_type());
	                    //updaye by Jing End
	                    
	                    //update m_cust
	                    updateDAO.execute("UPDATE.M_CST.SQL005.BL09", custDto);
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
	                //set return message
	                messages.add(Globals.MESSAGE_KEY, new BLogicMessage(Util.ERRORS_ERR2SC003));
	                M_CSTR04Output output = new M_CSTR04Output();
	                output.setMode(M_CSTFormBean.READONLY);
	                output.setId_cust(param.getId_cust());
	                output.setIdAudit(idAudit);
	                output.setPeopleSoftPopupInfo("");
	                result.setResultObject(output);
	                result.setMessages(messages);   
	                //set return string
	                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);   
	            }
			}
		}catch(Exception ex){
			//add error inserting/updating failure
			errors.add(Globals.ERROR_KEY, new BLogicMessage(Util.ERRORS_ERR2SC004));
			//set return error
			result.setErrors(errors);
			//set return string
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			ex.printStackTrace();
		}
		return result;
	}
	
	private boolean inputIsMulCharCheck(M_CSTR09BLogicInput input) {
        boolean isMulCharFlg = true;
        
        String isCheckMulCharFlg = Util.getIsCheckMulCharFlg(queryDAO);
        
        //Salutation
        String salutation = CommonUtils.toString(input.getSalutation());
        //Customer Name
        String cust_name = CommonUtils.toString(input.getCust_name());
        //NRIC/Passport No
        String cust_id_no = CommonUtils.toString(input.getCust_id_no());
        //Email Address
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
        if (CommonUtils.isEmpty(salutation.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Salutation"));
        }
        if (CommonUtils.isEmpty(cust_name.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Customer Name"));
        }
        if (CommonUtils.isEmpty(cust_id_no.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "NRIC/Passport No"));
        } 
        if (!CommonUtils.isEmpty(co_email.trim())) {
            if (!GenericValidator.isEmail(co_email.trim())) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC010",  "Email Address"));
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
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Line 1 Of Billing Address"));
        }
        if (CommonUtils.isEmpty(ba_zip_code.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Zip Code Of Billing Address"));
        }
        if (CommonUtils.isEmpty(ba_country_id.trim())) {
            isMulCharFlg = false;
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005",  "Address Country Of Billing Address"));
        }
        if ("1".equals(isCheckMulCharFlg)) {
            //Home Phone Number
//            String home_tel_no = CommonUtils.toString(input.getHome_tel_no());
//            //Mobile Number
//            String mobile_no = CommonUtils.toString(input.getMobile_no());
//            //Home Fax Number
//            String home_fax_no = CommonUtils.toString(input.getHome_fax_no());
//            //Office Phone Number
//            String co_tel_no = CommonUtils.toString(input.getCo_tel_no());
//            //Office Fax Number
//            String co_fax_no = CommonUtils.toString(input.getCo_fax_no());
//            //PeopleSoft Acc. No.
//            String cust_acc_no = CommonUtils.toString(input.getCust_acc_no());
//            //GBOC Acc No.
//            String gboc_acc_no = CommonUtils.toString(input.getGboc_acc_no());
//            //Reference Customer ID
//            String others_ref_no = CommonUtils.toString(input.getOthers_ref_no());
//            //Salesforce.com Acc. ID
//            String sales_force_acc_id = CommonUtils.toString(input.getSales_force_acc_id());

            if (CommonUtils.isMulChar(cust_name)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Customer Name", CommonUtils.MUL_CHAR_STR}));
            }
            
            if (CommonUtils.isMulChar(cust_id_no)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"NRIC/Passport No", CommonUtils.MUL_CHAR_STR}));
            }

//            if (CommonUtils.isMulChar(home_tel_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Home Phone Number", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(mobile_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Mobile Number", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(home_fax_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Home Fax Number", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(co_tel_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Office Phone Number", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(co_fax_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Office Fax Number", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(cust_acc_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"PeopleSoft Acc. No.", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(gboc_acc_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"GBOC Acc No.", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(others_ref_no)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Reference Customer ID", CommonUtils.MUL_CHAR_STR}));
//            }
//            if (CommonUtils.isMulChar(sales_force_acc_id)) {
//                isMulCharFlg = false;
//                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Salesforce.com Acc. ID", CommonUtils.MUL_CHAR_STR}));
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
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 1 Of Billing Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr_line2)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 2 Of Billing Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_adr_line3)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Line 3 Of Billing Address", CommonUtils.MUL_CHAR_STR}));
            }
            if (CommonUtils.isMulChar(ba_zip_code)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104",  new Object[]{"Address Zip Code Of Billing Address", CommonUtils.MUL_CHAR_STR}));
            }
        }
        return isMulCharFlg;
    }
}