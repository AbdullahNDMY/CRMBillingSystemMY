/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management B-CPM-S02v (Update status)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Update status when terminate in B-CPM-S02v
 * FILE NAME      : COMMONTerminateBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/10/12 [Duong Nguyen] Update DD change #2761
 * 
**********************************************************/
package nttdm.bsys.common.blogic;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

import java.text.ParseException;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.struts.Globals;

import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.common.blogic.AbstractCOMMONTerminateBLogic;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingCalendar;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CPM_P03;
import nttdm.bsys.common.util.G_CPM_P04;
import nttdm.bsys.common.util.dto.G_CPM_P03Input;
import nttdm.bsys.common.util.RadUserTUtil;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class COMMONTerminateBLogic extends AbstractCOMMONTerminateBLogic {

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(HashMap<String, Object> param) {
        BLogicResult result = new BLogicResult();
        BLogicMessages errors = new BLogicMessages();

        if (param.get("terminateDate") != null && !param.get("terminateDate").equals("")) {
            if(param.get("svc_start")!=null && !param.get("svc_start").equals("")){
                //get idLogin
                BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)param.get("uvo");
                param.put("idLogin", uvo.getId_user());

                //get svcStart
                Date svc_Start = new Date();
                Date terminate_Date = new Date();
                SimpleDateFormat date_Format;
                boolean isGreater = false;
                try {
                    date_Format = new SimpleDateFormat("dd/MM/yyyy");
                    svc_Start =  date_Format.parse(param.get("svc_start").toString());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(svc_Start);

                    date_Format = new SimpleDateFormat("dd/MM/yyyy");
                    terminate_Date = date_Format.parse(param.get("terminateDate").toString());
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(terminate_Date);
                    //compare
                    if(calendar.before(calendar2) || calendar.equals(calendar2)){
                        isGreater = true;
                    }
                }
                catch(ParseException e){
                    e.printStackTrace();
                    param.put("actionStatus", "failure");
                    //set error message
                    errors.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC004"));
                    result.setErrors(errors);
                }
                if(isGreater) {
                    // $terminateDate < $min_svc_end ?
                    //String valid = this.queryDAO.executeForObject("COMMON.VALID_TERMINATE_DATE", param, String.class);
                    try {

                        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date dateTerminate = format.parse(param.get("terminateDate").toString());
                        Date sysdate= new Date();
                        String idCustPlan1 = param.get("idCustPlan").toString();

                        /*at the first, actionStatus = validate*/
                        if (param.get("actionStatus").equals("validate")||param.get("actionStatus").equals("generate1")) {
                            if(!param.get("actionStatus").equals("generate1")){
                                // $termiantedate < sysdate?
                                if(BillingCalendar.compare(dateTerminate, sysdate) < 0){ // $termiantedate < sysdate -> yes
                                    //get idAudit
                                    Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CPM, idCustPlan1, "PS7", BillingSystemConstants.AUDIT_TRAIL_EDITED);
                                    param.put("idAudit", idAudit);
                                    param.put("servicesStatus", "PS7");
                                    //update service status and service end
                                    this.updateDAO.execute("COMMON.TERMINATE_SERVICE", param);
                                    //End Audit Trail
                                    CommonUtils.auditTrailEnd(idAudit);
                                    
                                    // DELETE RADUIS RECORD IF EXIST
                                    B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
                                    String userName = CommonUtils.toString(util.getAccessAccount(idCustPlan1).get("ACCESS_ACCOUNT"));
                                    if (!userName.equals("") && util.isExistPlanGrp(idCustPlan1)) {
                                        RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                                        if (radUserTUtil.isExistUserName(userName)) {
                                            radUserTUtil.deleteByUserName(userName);
                                        }
                                    }
                                } else { // $termiantedate < sysdate -> No
                                    //get idAudit
                                    Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CPM, idCustPlan1, "PS3", BillingSystemConstants.AUDIT_TRAIL_EDITED);
                                    param.put("idAudit", idAudit);
                                    //update service status and service end
                                    this.updateDAO.execute("COMMON.TERMINATE_SERVICE", param);
                                    //End Audit Trail
                                    CommonUtils.auditTrailEnd(idAudit);
                                }
                            }

//                                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
//                                B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
//                                String userName = String.valueOf(util.getAccessAccount(idCustPlan1).get("ACCESS_ACCOUNT"));
//                                if (util.isExistPlanGrp(idCustPlan1)) {
//
//                                    if (radUserTUtil.isExistUserName(userName)) {
//                                        radUserTUtil.deleteByUserName(userName);
//                                    }
//                                    else {
//                                        param.put("actionStatus", "confirm1");
//                                        result.setResultObject(param);
//                                        result.setResultString("success");
//                                        return result;
//                                    }
//                                } // end  $termiantedate > sysdate -> yes
//                            }
                            //check billing account
//                                String recordNo = this.queryDAO.executeForObject("COMMON.CHECK_BILLING_ACOUNT_TERMINATE", param, String.class);
//                                if (recordNo != null && !recordNo.equals("0")) {
//                                    /*set actionStatus = 'confirm' to show confirm popup*/
//                                    param.put("actionStatus", "confirm");
//                                } else {
//                                    
//                                }
                            
                            G_CPM_P04 gCpmP04 = new G_CPM_P04(this.queryDAO, this.updateDAO);
                            gCpmP04.execute(param.get("idCustPlan").toString());
                            //set to success
                            param.put("actionStatus", "success");

                        } else if (param.get("actionStatus").equals("generate")) { // click OK on msgBox
                            //TODO call G-CPM-P03 
                            G_CPM_P03 gCpmP03 = new G_CPM_P03(this.queryDAO, this.updateDAOiBatisNuked);
                            G_CPM_P03Input input = new G_CPM_P03Input();

                            /*get*/
                            Date svcStart = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                svcStart =  dateFormat.parse(param.get("terminateDate").toString());
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(svcStart);
                                calendar.add(Calendar.DATE, 1);                    
                                svcStart.setTime(calendar.getTimeInMillis());

                            } catch(ParseException e){
                                e.printStackTrace();
                                param.put("actionStatus", "failure");
                                errors.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC004"));
                                result.setErrors(errors);
                            }
                            
                            String svcEnd = this.queryDAO.executeForObject("COMMON.GET_BILL_TO", param, String.class);
                            String idCustPlan = param.get("idCustPlan").toString();
                            String idCustPlanGrp = param.get("idService").toString();
                            String idLogin= param.get("idLogin").toString();    
                            
                            /*set*/
                            input.setSvcStart(dateFormat.format(svcStart));
                            input.setSvcEnd(svcEnd);
                            input.setIdCustPlan(idCustPlan);
                            input.setIdCustPlanGrp(idCustPlanGrp);
                            input.setIdLogin(idLogin);
                            
                            /*pass input for gCpmP03*/                
                            gCpmP03.execute(input);

                            /*set "success" to call process G-CPM-P04*/
                            param.put("actionStatus", "success");

                            // $termiantedate < sysdate?
                            if(dateTerminate.compareTo(sysdate) < 0){ // $termiantedate < sysdate
                                if (param.get("actionStatus").equals("success")) {
                                    //call process G-CPM-P04
                                    /** Update DD change #2761 start **/
                                    //String idCustPlan = param.get("idCustPlan").toString();
                                    G_CPM_P04 gCpmP04 = new G_CPM_P04(this.queryDAO, this.updateDAO);
                                    gCpmP04.execute(idCustPlan);
                                    /*set "success" to close windows*/
                                    param.put("actionStatus", "success");
                                    /** Update DD change #2761 end **/
                                }
                            } else {
                                /*set "success" to close windows*/
                                param.put("actionStatus", "success");
                            }
                        } else if (param.get("actionStatus").equals("complete")){ // click NO on msgBox
                            /*call process G-CPM-P04*/
                            String idCustPlan = param.get("idCustPlan").toString();
                            G_CPM_P04 gCpmP04 = new G_CPM_P04(this.queryDAO, this.updateDAO);
                            gCpmP04.execute(idCustPlan);
                            /*set "success" to close windows*/
                            param.put("actionStatus", "success");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        param.put("actionStatus", "failure");
                        //set error message
                        errors.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC004"));
                        result.setErrors(errors);
                    }
                } else {// end isGreater
                    param.put("actionStatus", "failure");
                    errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC063" , "Service End Date"));
                    result.setErrors(errors);
                }
            } // end if ("svc_start")!=null
        } else { // end if ("terminateDate") != null
            param.put("actionStatus", "failure");
            //set error message
            errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC063" , "Service End Date"));
            result.setErrors(errors);
        }

        result.setResultObject(param);
        result.setResultString("success");
        return result;
    }
}
