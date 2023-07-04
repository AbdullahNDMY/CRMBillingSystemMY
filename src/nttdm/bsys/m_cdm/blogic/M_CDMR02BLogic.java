/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Code Maintenance
 * SERVICE NAME   : Code Maintenance
 * FUNCTION       : Code Maintenance BLogic
 * FILE NAME      : M_CDMR02BLogic.java
 *
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/27 Tuyen Fixed for error of checking style
 * 
**********************************************************/
package nttdm.bsys.m_cdm.blogic;

import java.util.ArrayList;
import java.util.Collection;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.util.PropertyUtil;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cdm.bean.MCodeBean;
import nttdm.bsys.m_cdm.bean.M_CDMFormBean;
import nttdm.bsys.m_cdm.dto.M_CDMR02Input;

import org.apache.struts.Globals;
/**
 * Code Maintenance Handling BLogic
 * <ul>
 * <li>Code Maintenance Handling BLogic
 * </ul>
 * @author  NTTData Vietnam
 * @version 1.1 
 */
public class M_CDMR02BLogic extends AbstractM_CDMR02BLogic{
	
	/**
	 * <div>queryDAO class</div> 
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>updateDAO class</div> 
	 */
	private UpdateDAO updateDAO;
	
	/**
	 * <div>GET_KEY sql</div> 
	 */	
	private static final String GET_KEY = "SELECT.M_CDM.SQL003";

	/**
	 * <div>INSERT_CODE sql</div> 
	 */
	private static final String INSERT_CODE = "INSERT.M_CDM.SQL001";
	
	/**
	 * <div>UPDATE_CODE sql</div> 
	 */	
	private static final String UPDATE_CODE = "UPDATE.M_CDM.SQL001";
	
	/**
	 * <div>UPDATE_DELETED_CODE sql</div> 
	 */
	private static final String UPDATE_DELETED_CODE = "UPDATE.M_CDM.SQL002";
	
	/**
	 * <div>DELETE_CODE sql</div> 
	 */
	private static final String DELETE_CODE = "UPDATE.M_CDM.SQL003";
	
	/**
	 * <div>ERR1SC009 error message</div> 
	 */
	private static final String ERR1SC009 = "errors.ERR1SC009";
	
	/**
	 * <div>ERR1SC011 error message</div> 
	 */
	private static final String ERR1SC011 = "errors.ERR1SC011";
	
	/**
	 * <div>ERR1SC011 error message</div> 
	 */
	private static final String ERR1SC014 = "errors.ERR1SC014";
	
	/**
	 * <div>ERR1SC022 error message</div> 
	 */
	private static final String ERR1SC022 = "errors.ERR1SC022";
	
	/**
	 * <div>ERR1SC005 error message</div> 
	 */
	private static final String ERR1SC005 = "errors.ERR1SC005";
	
	/**
	 * <div>ERR1SC031 error message</div> 
	 */
	private static final String ERR1SC031 = "errors.ERR1SC031";
	
	/**
     * <div>ERR1SC031 error message</div> 
     */
    private static final String ERR1SC094 = "errors.ERR1SC094";
	
	/**
	 * <div>ERR2SC003 error message</div> 
	 */
	private static final String ERR2SC003 = "info.ERR2SC003";
	
	/**
	 * <div>ERR2SC004 error message</div> 
	 */
	private static final String ERR2SC004 = "info.ERR2SC004";
	
	/**
	 * <div>ERROR1 error message</div> 
	 */
	private static final String ERROR1 = "screen.m_cdm.error1";
	
	/**
	 * <div>ERROR2 error message</div> 
	 */
	private static final String ERROR2 = "screen.m_cdm.error2";
	
	/**
	 * <div>ERROR3 error message</div> 
	 */
	private static final String ERROR3 = "screen.m_cdm.error3";
	
	/**
	 * <div>ERROR4 error message</div> 
	 */
	private static final String ERROR4 = "screen.m_cdm.error4";
	
	/**
	 * <div>ERROR5 error message</div> 
	 */
	private static final String ERROR5 = "screen.m_cdm.error5";
	
	/**
	 * <div>ERROR6 error message</div> 
	 */
	private static final String ERROR6 = "screen.m_cdm.error6";
	
	/**
	 * <div>errors blogic when catch error</div> 
	 */
	private BLogicMessage errors = null;
	
	/**
	 * <div>
	 * default status is 1
	 * 1 : removed items
	 * 0 : added items
	 * </div>
	 */
	private static final String STATUS_ADD = "0";

	/**
	 * execute blogic
	 */
	public BLogicResult execute(M_CDMR02Input params) {
		BLogicResult result = new BLogicResult();
		BLogicMessages error = new BLogicMessages();
		BLogicMessages message = new BLogicMessages();
		try {
			String idLogin = params.getUvoObject().getId_user();
			// Get qcsno array
            MCodeBean[] qcsno = params.getQcsno();
            //String formatQcsNo = getFormatStr(qcsno);
            // Get quono array
            MCodeBean[] quono = params.getQuono();
            //String formatQuoNo = getFormatStr(quono);
            // Get bifno array
            MCodeBean[] bifno = params.getBifno();
            //String formatBifNo = getFormatStr(bifno);
            
            // Get bifdn array
            MCodeBean[] bifdn = params.getBifdn();
            
            // Get bifcn array
            MCodeBean[] bifcn = params.getBifcn();

            // Get invno array
            MCodeBean[] invno = params.getInvno();
            //String formatInvNo = getFormatStr(invno);
            // Get dbtno array
            MCodeBean[] dbtno = params.getDbtno();
            //String formatDbtNo = getFormatStr(dbtno);
            // Get cdtno array
            MCodeBean[] cdtno = params.getCdtno();
            //String formatCdtNo = getFormatStr(cdtno);
            
            // Get ntinv array
            MCodeBean[] ntinv = params.getNtinv();
            // Get rcpno array
            MCodeBean[] rcpno = params.getRcpno();
            //String formatRcpNo = getFormatStr(rcpno);
            // Get scpid array
            MCodeBean[] scpid = params.getScpid();
            //String formatScpid = getFormatStr(scpid);
            // Get ctmid array
            MCodeBean[] ctmid = params.getCtmid();
            //String formatCtmid = getFormatStr(ctmid);
            // Get bacno array
            MCodeBean[] bacno = params.getBacno();
            // Get bacno array
            MCodeBean[] soano = params.getSoano();
            
            // Validate data
	            if (!this.validate(qcsno))
	            	error.add(Globals.ERROR_KEY, errors);
	            if (!this.validate(quono))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(bifno))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(bifdn))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(bifcn))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(invno))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(dbtno))
	            	error.add(Globals.ERROR_KEY, errors);	
	            if(!this.validate(cdtno))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(ntinv))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(rcpno))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(scpid))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(ctmid))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(bacno))
	            	error.add(Globals.ERROR_KEY, errors);
	            if(!this.validate(soano))
                    error.add(Globals.ERROR_KEY, errors);
	        
            // Validate same format
//            validateDuplicateFormat(formatQcsNo, formatQuoNo, formatBifNo,
//                    formatInvNo, formatDbtNo, formatCdtNo, formatRcpNo,
//                    formatScpid, formatCtmid, error);
            if (!error.isEmpty()) {
                result.setErrors(error);
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                return result;
            }
            // Update data
            executeData(qcsno, idLogin);
            // Update data
            executeData(quono, idLogin);
            // Update data
            executeData(bifno, idLogin);
            // Update data
            executeData(bifdn, idLogin);
            // Update data
            executeData(bifcn, idLogin);
            // Update date
            executeData(invno, idLogin);
            // Update date
            executeData(dbtno, idLogin);
            // Update date
            executeData(cdtno, idLogin);
            // Update date
            executeData(ntinv, idLogin);
            // Update date
            executeData(rcpno, idLogin);
            // Update date
            executeData(scpid, idLogin);
            // Update date
            executeData(ctmid, idLogin);
            // Update date
            executeData(bacno, idLogin);
            // Update date
            executeData(soano, idLogin);
            message.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC003));
            
        } catch (Exception ex) {
            // error saving record
            error.add(Globals.ERROR_KEY, new BLogicMessage(ERR2SC004));
            result.setErrors(error);
            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            //return result;
        }
		//record saved successfully 
		
		result.setMessages(message);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
	
//    private void validateDuplicateFormat(String formatQcsNo ,
//            String formatQuoNo , String formatBifNo , String formatInvNo ,
//            String formatDbtNo , String formatCdtNo , String formatRcpNo ,
//            String formatScpid , String formatCtmid , BLogicMessages error) {
//        boolean isError = false;
//        String[] allLabel = new String[] { "QCS No. ", "Quotation No. ",
//                "BIF No.  ", "Invoice No. ", "Debit Note No. ",
//                "Credit Note No. ", "Receipt No. ", "Subscription ID. ",
//                "Customer ID. " };
//        String[] allCode = new String[] { formatQcsNo, formatQuoNo,
//                formatBifNo, formatInvNo, formatDbtNo, formatCdtNo,
//                formatRcpNo, formatScpid, formatCtmid };
//        int i = 0;
//        do {
//            for (int j = 0; j < allCode.length; j++) {
//                if (i == j) {// bypass self-element
//                    continue;
//                } else {// check duplicate
//                    if (allCode[i].equals(allCode[j])) {
//                        error.add(Globals.ERROR_KEY, new BLogicMessage(
//                                "errors.ERR1SC030", new Object[] { allLabel[i],
//                                        allLabel[j] }));
//                        isError = true;
//                        break;
//                    } else {
//                        continue;
//                    }
//                }
//            }
//            i++;
//        } while (i < 9 && !isError);
//    }

//	private String getFormatStr(MCodeBean[] mcBeans) {
//		String formatStr = "";
//		for(MCodeBean mcBean : mcBeans){
//			String[] nameAndType = mcBean.getInit_val().split(";");
//			if(mcBean.getType_val().equals(M_CDMFormBean.PREFIX) || mcBean.getType_val().equals(M_CDMFormBean.RUNNING)){
//				formatStr += mcBean.getValue();
//			}else if(nameAndType.length > 1){
//				formatStr += nameAndType[1];			
//			}
//		}
//		return formatStr;
//	}

	/**
	 * update database
	 * @param beans
	 * @param idLogin
	 */
	public void executeData(MCodeBean[] beans,String idLogin){
		for(int i=0;i<M_CDMFormBean.MAX_CONTROLS;i++)
		{
			//set id login
			beans[i].setId_login(idLogin);
			Integer idAudit = null;
			String resetNo ="";
			//if combobox A is unhide, insert or update
			if(beans[i].getStatus().equals(STATUS_ADD))
			{
				//if not exist key 
			    Integer flagI = queryDAO.executeForObject(GET_KEY, beans[i], Integer.class);
			    int flag = 0;
			    if (flagI != null) {
			        flag = flagI.intValue();
			    }
				switch(flag){
				case 0:
					//if existed with is_deleted = 0
					//update
					beans[i].setCur_val(beans[i].getValue());
					beans[i].setIs_deleted("0");
					idAudit = beginAuditTrail(beans[i], BillingSystemConstants.AUDIT_TRAIL_EDITED);
					resetNo = CommonUtils.toString(beans[i].getReset_no()).trim();
					if (CommonUtils.isEmpty(resetNo)) {
					    beans[i].setReset_no("0");
					}
					updateDAO.execute(UPDATE_CODE, beans[i]);
					CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
					break;
				case 1:
					//if existed with is_deleted = 1
					//change to suitable values then update
					//beans[i].setInit_val(beans[i].getValue());
					beans[i].setCur_val(beans[i].getValue());
					beans[i].setIs_deleted("1");
					idAudit = beginAuditTrail(beans[i], BillingSystemConstants.AUDIT_TRAIL_DELETED);
					resetNo = CommonUtils.toString(beans[i].getReset_no()).trim();
                    if (CommonUtils.isEmpty(resetNo)) {
                        beans[i].setReset_no("0");
                    }
					updateDAO.execute(UPDATE_DELETED_CODE, beans[i]);
					CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
					break;
				case 2:
					//insert
					//beans[i].setInit_val(beans[i].getValue());
					beans[i].setCur_val(beans[i].getValue());
					idAudit = beginAuditTrail(beans[i], BillingSystemConstants.AUDIT_TRAIL_CREATED);
					resetNo = CommonUtils.toString(beans[i].getReset_no()).trim();
                    if (CommonUtils.isEmpty(resetNo)) {
                        beans[i].setReset_no("0");
                    }
					updateDAO.execute(INSERT_CODE, beans[i]);
					CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
					break;
					default:
						break;
				}
			}else{
				//if combobox A is hide, delete if any
				idAudit = beginAuditTrail(beans[i], BillingSystemConstants.AUDIT_TRAIL_DELETED);
				updateDAO.execute(DELETE_CODE, beans[i]);
				CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			}
		}		
	}
	
	/**
	 * Audit Trail
	 */
	private Integer beginAuditTrail(MCodeBean bean, String action) {
		Integer id_audit = CommonUtils.auditTrailBegin(bean.getId_login(), BillingSystemConstants.MODULE_M, 
				BillingSystemConstants.SUB_MODULE_M_CDM, bean.getId_code(), null, action);
		bean.setId_audit(id_audit);
		return id_audit;
	}
	
	/**
	 * validate inputed values
	 * @param beans
	 * @return
	 */
	private boolean validate(MCodeBean[] beans){
		Object[] elements=null;
		int len = 0;
		int countRunningNo = 0;
		int isNull = 0;
		boolean isNNumberic = false;
		boolean isNNegative = false;
		boolean isRequired = false;
		boolean soaNoFormat = false;
		String labelOfCode = "";
		for(int i=0;i<M_CDMFormBean.MAX_CONTROLS;i++){
			if(i==0){
				//Get label of code
				String[] nameAndType = beans[i].getInit_val().split(";");
				labelOfCode = nameAndType[0];
			}
			//check validate when exist selected combobox 
			if(beans[i].getStatus().equals(STATUS_ADD)){
				//get length of code value
				len = len + this.getCodeValueLen(beans[i]);
				//check mandatory
				isRequired = this.isRequired(beans[i]);
				if(!isRequired){break;}
				//check is null
				isNull = this.isNull(beans[i]);				
				//exit loop when catch error
				if(isNull==1||isNull==2){break;}				
				//check is numeric
				isNNumberic = this.isNotNumberic(beans[i]);
				//exit loop when catch error
				if(isNNumberic){break;}
				//check is numeric
				isNNegative = this.isNotNegative(beans[i]);
				//exit loop when catch error
				if(isNNegative){break;}
				//exist at least 1 running no
				if(beans[i].getType_val().equals(M_CDMFormBean.RUNNING)){
					countRunningNo+=1;
				}
				String idCode = beans[i].getId_code();
				if (M_CDMFormBean.SOANO.equals(idCode)) {
				    soaNoFormat = soaNoFormatCheck(beans[i]);
				    if(soaNoFormat) {
				        break;
				    }
				}
			}
		}
		//check length
		if(len>20){
			elements = new Object[2];
			elements[0] = PropertyUtil.getProperty(ERROR1) + labelOfCode;
			elements[1] = PropertyUtil.getProperty(ERROR2);
			this.getErrorMessage(ERR1SC009,elements);
			return false;
		}
		//check mandatory
		if(!isRequired){
			//catch error at running
			elements = new Object[1];
			elements[0] = PropertyUtil.getProperty(ERROR6) + labelOfCode;
			this.getErrorMessage(ERR1SC005,elements);
			return false;			
		}
		//check null value
		if(isNull==1){
			//catch error at prefix
			elements = new Object[1];
			elements[0] = PropertyUtil.getProperty(ERROR3) + labelOfCode;
		}
		if(isNull==2){
			//catch error at running
			elements = new Object[1];
			elements[0] = PropertyUtil.getProperty(ERROR4) + labelOfCode;
		}
		if(isNull!=0){
			this.getErrorMessage(ERR1SC005,elements);
			return false;
		}
		//check is numberic
		if(isNNumberic){
			//catch error at running
			elements = new Object[1];
			elements[0] = PropertyUtil.getProperty(ERROR4) + labelOfCode;
			this.getErrorMessage(ERR1SC011,elements);
			return false;
		}
		//check is more than 0
		if(isNNegative){
			//catch error at running
			elements = new Object[1];
			elements[0] = PropertyUtil.getProperty(ERROR4) + labelOfCode;
			this.getErrorMessage(ERR1SC014,elements);
			return false;
		}
		//SOANO no allow have selection of YYYYMMDD and YYMMDD
		if (soaNoFormat) {
		    this.getErrorMessage(ERR1SC094,elements);
            return false;
		} else {
		    //check exist at least running no
	        if(countRunningNo==0){
	            //catch error at running
	            elements = new Object[2];
	            elements[0] = PropertyUtil.getProperty(ERROR5);
	            elements[1] = labelOfCode;
	            this.getErrorMessage(ERR1SC022,elements);
	            return false;
	        }       
	        //check eixst more than 1 running no
	        if(countRunningNo>1){
	            //catch error at running
	            elements = new Object[1];
	            elements[0] = labelOfCode;
	            this.getErrorMessage(ERR1SC031,elements);       
	            return false;
	        }
		}
		return true;
	}
	/**
	 * check value is blank
	 * @param mbean
	 * @return
	 */
	private boolean isRequired(MCodeBean mbean){
		//ERR_REQUIRED
		if(mbean.getType_val() == null || mbean.getType_val().equals("")){
			return false;
		}
		return true;
	}
	/**
	 * check value is numeric
	 * @param mbean
	 * @return
	 */
	private boolean isNotNumberic(MCodeBean mbean){
		if(mbean.getType_val().equals(M_CDMFormBean.RUNNING))
		{
			try{
				Integer.parseInt(mbean.getValue(), 10);
			}catch(Exception ex){
				return true;	
			}
		}
		return false;
	}
	private boolean isNotNegative(MCodeBean mbean){
		if(mbean.getType_val().equals(M_CDMFormBean.RUNNING))
		{
			try{
				if(mbean.getValue().trim().equals("-0")){
					return true;
				}else{
						int val = Integer.parseInt(mbean.getValue(), 10);
						if(val>=0){
							return false;
						}else{
							return true;
						}
				}
			}catch(Exception ex){
				return true;	
			}
		}
		return false;
	}
	/**
	 * check value is null
	 * @param mbean
	 * @return
	 */
	private int isNull(MCodeBean mbean){
		//in case type is 05
		if(mbean.getType_val().equals(M_CDMFormBean.PREFIX)){
			//check inputed value is null
			if(mbean.getValue() == null || mbean.getValue().equals("")){
				return 1;
			}
		}
		//in case type is 06
		if(mbean.getType_val().equals(M_CDMFormBean.RUNNING)){
			//check inputed value is null
			if(mbean.getValue() == null || mbean.getValue().equals("")){
				return 2;
			}
		}
		return 0;
	}
	/**
	 * return length of inputed text box
	 * @param mbean
	 * @return
	 */
	private int getCodeValueLen(MCodeBean mbean){		
		//in case type is 05 or 06
		if(mbean.getType_val().equals(M_CDMFormBean.PREFIX) || mbean.getType_val().equals(M_CDMFormBean.RUNNING)){
			//return length of inputed value
			return mbean.getValue().length();
		}
		String[] nameAndType = mbean.getInit_val().split(";");
		if(nameAndType.length > 1){
			//otherwise, return length of type (it refer from init_val object  
			return nameAndType[1].length();			
		}
		return 0;
	}
	
	/**
	 * SOANO Format Check(no allow have selection of YYYYMMDD and YYMMDD)
	 * @param mbean
	 * @return
	 */
	private boolean soaNoFormatCheck(MCodeBean mbean){
        if(M_CDMFormBean.YYYYMMDD.equals(mbean.getType_val()) 
                || M_CDMFormBean.YYMMDD.equals(mbean.getType_val())){
            return true;
        }
        return false;
    }
	/**
	 * return error message content
	 * @param errorNumber
	 * @param elements
	 */
	private void getErrorMessage(String errorNumber,Object[] elements){
		this.errors = new BLogicMessage(errorNumber,elements); 
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

}
