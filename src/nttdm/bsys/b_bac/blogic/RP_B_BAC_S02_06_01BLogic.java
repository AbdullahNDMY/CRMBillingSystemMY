/*
 * @(#)RP_B_BAC_S02_06_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_06_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_06_01Output;
import nttdm.bsys.common.bean.T_BAC_D;
import nttdm.bsys.common.util.CommonUtils;

/*
**
* BusinessLogic class of B-BAC Edit init.
* 
* @author khungl0ng
*/
public class RP_B_BAC_S02_06_01BLogic extends AbstractRP_B_BAC_S02_06_01BLogic {
    /**
     * B-BAC Billing Reference Edit init.
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S02_06_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S02_06_01Output outputDTO = new RP_B_BAC_S02_06_01Output();
        
        // get idBillAccount
        String idBillAccount = param.getIdBillAccount();
        //get idCustPlan
        String idCustPlan = param.getIdCustPlan();
        //get frompage
        String frompage = param.getFromPage();
        // setup parameters
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("idBillAccount", idBillAccount);
        // get bill ref info
        List<HashMap<String, Object>> billRefInfo = this.queryDAO.executeForObjectList("B_BAC.getDistBillRef", m);
        //get DropdownList AC_Manager
        List<LabelValueBean> billRefACManagerList = this.queryDAO.executeForObjectList("B_BAC.getBillRefACManager", null);
        //New inputBillReferenceInfo
        T_BAC_D  inputBillReferenceInfo=new T_BAC_D();
        
        //ID_BIF
        List<String> idBif=new ArrayList<String>();
        //ID_QCS
        List<String> idQcs=new ArrayList<String>();
        //ID_QUO
        List<String> idQuo=new ArrayList<String>();
        //CUST_PO
        List<String> custPo=new ArrayList<String>();
        //AC_MANAGER
        List<String> acManager=new ArrayList<String>();
        //TERM
        List<String> term=new ArrayList<String>();
      //TERM
        List<String> termDay=new ArrayList<String>();
        for (int i = 0; i < billRefInfo.size(); i++) {
            //ID_BIF
            String getidBif=CommonUtils.toString(billRefInfo.get(i).get("ID_BIF")==null?"":billRefInfo.get(i).get("ID_BIF").toString()).trim();
            //ID_QCS
            String getidQcs=CommonUtils.toString(billRefInfo.get(i).get("ID_QCS")==null?"":billRefInfo.get(i).get("ID_QCS").toString()).trim();
            //ID_QUO 
            String getidQuo=CommonUtils.toString(billRefInfo.get(i).get("ID_QUO")==null?"":billRefInfo.get(i).get("ID_QUO").toString()).trim();
            //CUST_PO
            String getcustPo=CommonUtils.toString(billRefInfo.get(i).get("CUST_PO")==null?"":billRefInfo.get(i).get("CUST_PO").toString()).trim();
            //AC_MANAGER
            String geracManager=CommonUtils.toString(billRefInfo.get(i).get("AC_MANAGER")==null?"":billRefInfo.get(i).get("AC_MANAGER").toString()).trim();
            //TERM
            String getterm=CommonUtils.toString(billRefInfo.get(i).get("TERM")==null?"":billRefInfo.get(i).get("TERM").toString()).trim();
            //TERM_DAY
            String gettermDay=CommonUtils.toString(billRefInfo.get(i).get("TERM_DAY")==null?"":billRefInfo.get(i).get("TERM_DAY").toString()).trim();
            
            if(!CommonUtils.isEmpty(getidBif)&&!idBif.contains(getidBif)){
                idBif.add(getidBif);
            }
            if(!CommonUtils.isEmpty(getidQcs)&&!idQcs.contains(getidQcs)){
                idQcs.add(getidQcs);
            }
            if(!CommonUtils.isEmpty(getidQuo)&&!idQuo.contains(getidQuo)){
                idQuo.add(getidQuo);
            }
            if(!CommonUtils.isEmpty(getcustPo)&&!custPo.contains(getcustPo)){
                custPo.add(getcustPo);
            }
            if(!CommonUtils.isEmpty(geracManager)&&!acManager.contains(geracManager)){
                acManager.add(geracManager);
            }
            if(!CommonUtils.isEmpty(getterm)&&!term.contains(getterm)){
                term.add(getterm);
            }
            if(!CommonUtils.isEmpty(gettermDay)&&!termDay.contains(gettermDay)){
            	termDay.add(gettermDay);
            }
        }
        
        //set ID_BIF for display in screen
        if(idBif.size()==0||idBif.size()>1){
            inputBillReferenceInfo.setIdBif("");
        }else{
            inputBillReferenceInfo.setIdBif(idBif.get(0));
        }
        //set ID_QCS for display in screen
        if(idQcs.size()==0||idQcs.size()>1){
            inputBillReferenceInfo.setIdQcs("");
        }else{
            inputBillReferenceInfo.setIdQcs(idQcs.get(0));
        }
        //set ID_QUO for display in screen
        if(idQuo.size()==0||idQuo.size()>1){
            inputBillReferenceInfo.setIdQuo("");
        }else{
            inputBillReferenceInfo.setIdQuo(idQuo.get(0));
        }
        //set CUST_PO for display in screen    
        if(custPo.size()==0||custPo.size()>1){
            inputBillReferenceInfo.setCustPo("");
        }else{
            inputBillReferenceInfo.setCustPo(custPo.get(0));
        }
        //set AC_MANAGER for display in screen
        if(acManager.size()==0||acManager.size()>1){
            inputBillReferenceInfo.setAcManager("");
        }else{
            inputBillReferenceInfo.setAcManager(acManager.get(0));
        }
        //set TERM for display in screen
        if(term.size()==0||term.size()>1){
            inputBillReferenceInfo.setTerm("");
        }else{
            inputBillReferenceInfo.setTerm(term.get(0));
        }
      //set TERM_DAY for display in screen
        if(termDay.size()==0||termDay.size()>1){
            inputBillReferenceInfo.setTermDay("0");
        }else{
            inputBillReferenceInfo.setTermDay(termDay.get(0));
        }
        
        //Add by Jing For #142 Start
        String custPoDisp = this.queryDAO.executeForObject("B_BAC.SELECT_SET_VALUE", null, String.class);
        inputBillReferenceInfo.setCustPoDisp(custPoDisp);
        //Add by Jing For #142 End
        
        outputDTO.setInputBillReferenceInfo(inputBillReferenceInfo);
        outputDTO.setIdBillAccount(idBillAccount);
        outputDTO.setFromPage(frompage);
        outputDTO.setIdCustPlan(idCustPlan);
        outputDTO.setBillRefACManagerList(billRefACManagerList);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}
