/**
 * @(#)B_BIL_S03neAddBillingItemBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/30
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.RoundEnvironment;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.bean.T_BIL_DBean;
import nttdm.bsys.b_bil.bean.T_BIL_DetailInfo;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.b_bil.dto.B_BIL_S03neAddBillingItemInput;
import nttdm.bsys.b_bil.dto.B_BIL_S03neAddBillingItemOutput;
import nttdm.bsys.b_cpm.dto.BillCurrency;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gplai
 *
 */
public class B_BIL_S03neAddBillingItemBLogic extends
        AbstractB_BIL_S03neAddBillingItemBLogic {

    private double pGST=0;
    
    /**
     * @param B_BIL_S03neAddBillingItemInput
     */
    public BLogicResult execute(B_BIL_S03neAddBillingItemInput params) {
        BLogicResult result = new BLogicResult();
        B_BIL_S03neAddBillingItemOutput output = new B_BIL_S03neAddBillingItemOutput();
        
        String idCustPlanLinks = params.getIdCustPlanLinks();
        //the idCustPlanGrpArr length=idCustPlanGrpArr
        //if one service has more than subPlan the data will like this
        //idCustPlanGrpArr data:['service1','service1','service1','service2','service2'],
        //idCustPlanLinkArr data:['service1 SubPlan','service1 SubPlan','service1 SubPlan','service2 SubPlan','service2 SubPlan'],
        //String[] idCustPlanGrpArr = idCustPlanGrps.split(",");
        String[] idCustPlanLinkArr = idCustPlanLinks.split(",");
        
        T_BIL_HeaderInfo bilHeaderInfo = new T_BIL_HeaderInfo();
        List<T_BIL_DetailInfo> bilDetail = new ArrayList<T_BIL_DetailInfo>();
        
        if(idCustPlanLinkArr!=null && 0 < idCustPlanLinkArr.length) {
            //GST Value
            Map<String, Object> mapPGst = this.queryDAO.executeForMap("B_BIL.getGstAmount", null);
            pGST = CommonUtils.toDouble(mapPGst.get("SET_VALUE"));
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("idCustPlanLink", idCustPlanLinkArr);
            List<Map<String, Object>> listServiceAndSubPlanInfo = queryDAO.executeForMapList("B_BIL.selectServiceAndSubPlan", map);
          //Fix Item_desc length over 4000 character 20180404 CT
            for(Map<String, Object> temp : listServiceAndSubPlanInfo) {
            	String itemDesc = clobToString((Clob)temp.get("ITEM_DESC_TEXT"));
            	temp.put("ITEM_DESC_TEXT", itemDesc);
            }
          //Fix Item_desc length over 4000 character 20180404 CT
            if (listServiceAndSubPlanInfo!=null && 0<listServiceAndSubPlanInfo.size()) {
                String preIdCustPlanGrp = "";
                List<Map<String, Object>> sameServiceSubPlanInfo = new ArrayList<Map<String, Object>>();
                //loop record,one record include service and sub_plan info
                for (int i=0;i<listServiceAndSubPlanInfo.size();i++) {
                    Map<String, Object> serviceAndSubPlanInfo = listServiceAndSubPlanInfo.get(i);
                    String idCustPlanGrp = CommonUtils.toString(serviceAndSubPlanInfo.get("ID_CUST_PLAN_GRP"));
                    if(i==0) {
                        sameServiceSubPlanInfo.add(serviceAndSubPlanInfo);
                    } else {
                        if(preIdCustPlanGrp.equals(idCustPlanGrp)) {
                            sameServiceSubPlanInfo.add(serviceAndSubPlanInfo);
                        } else {
                            initServiceAndSubPlanInfo(sameServiceSubPlanInfo, bilDetail);
                            sameServiceSubPlanInfo = new ArrayList<Map<String, Object>>();
                            sameServiceSubPlanInfo.add(serviceAndSubPlanInfo);
                        }
                    }
                    preIdCustPlanGrp = idCustPlanGrp;
                }
                initServiceAndSubPlanInfo(sameServiceSubPlanInfo, bilDetail);
            }
        }
        
        bilHeaderInfo.setBilDetail(bilDetail);
        
        //Job Modules is used flag
        B_BIL_CommonUtil util = new B_BIL_CommonUtil(queryDAO, updateDAO);
        String jobModulesDisplayFlg = util.getIsJNMModulesDisplayFlg();
        bilHeaderInfo.setJobModulesDisplayFlg(jobModulesDisplayFlg);
        
        bilHeaderInfo.setBillType(params.getBillType());
        
        String gstCheck = CommonUtils.toString(queryDAO.executeForObject("B_BIL.getM_GSET_D_SetValue", null, String.class));
        output.setGstCheck(gstCheck);
        
        output.setBilHeaderInfo(bilHeaderInfo);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
    
    /**
     * set service and subplan to output bean
     * @param serviceSubPlanInfo
     * @param bilHeaderInfo
     */
    private void initServiceAndSubPlanInfo(List<Map<String, Object>> serviceSubPlanInfo, List<T_BIL_DetailInfo> bilDetail) {
        if (serviceSubPlanInfo!=null && 0<serviceSubPlanInfo.size()) {
            int index = 1;
            //add Service
            T_BIL_DBean serviceDetail = mapInfoToBean(serviceSubPlanInfo.get(0), index, true);
            T_BIL_DetailInfo serviceBean = new T_BIL_DetailInfo();
            double totalAmt = 0;
            double totalDiscountAmt = 0;
            double totalSubtotal = 0;
            double totalGST = 0;
            // #164 start
            String hTaxRate = "0";
            String hApplyGST = "0";
            // #164 end
            List<T_BIL_DBean> subPlanList = new ArrayList<T_BIL_DBean>();
            for(int i=0;i<serviceSubPlanInfo.size();i++) {
                index = index + 1;
                //Add Sub Plan
                T_BIL_DBean subPlanDetail = mapInfoToBean(serviceSubPlanInfo.get(i), index, false);
                subPlanList.add(subPlanDetail);
                totalAmt = totalAmt + Double.parseDouble(subPlanDetail.getItemAmt());
                totalDiscountAmt = totalDiscountAmt + Double.parseDouble(subPlanDetail.getItemDisc());
                totalSubtotal = totalSubtotal + Double.parseDouble(subPlanDetail.getItemSubTotal());
                totalGST = totalGST + Double.parseDouble(subPlanDetail.getItemGst());
                // #164 start
                hTaxRate = subPlanDetail.getTaxRate();
                hApplyGST = subPlanDetail.getApplyGst();
                // #164 end
            }
            //add subplan
            serviceBean.setSubPlanBean(subPlanList);
            serviceDetail.setItemUprice(CommonUtils.toString(totalAmt));
            serviceDetail.setItemAmt(CommonUtils.toString(totalAmt));
            serviceDetail.setItemDisc(CommonUtils.toString(totalDiscountAmt));
            serviceDetail.setItemSubTotal(CommonUtils.toString(totalSubtotal));
            serviceDetail.setItemGst(CommonUtils.toString(totalGST));
            // #164 start
            serviceDetail.setTaxRate(hTaxRate);
            serviceDetail.setApplyGst(hApplyGST);
            // #164 end
            try {
                BeanUtils.copyProperties(serviceBean, serviceDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //add service
            bilDetail.add(serviceBean);
        }
    }
    
    /**
     * copy map value to T_BIL_DBean
     * @param map 
     * @param index
     * @param isServiceFlg true:is service,false:not service
     * @return
     */
    private T_BIL_DBean mapInfoToBean(Map<String, Object> map, int index, boolean isServiceFlg) {
        T_BIL_DBean tbilDBean = new T_BIL_DBean();
        
        tbilDBean.setItemCat("1");
        tbilDBean.setItemNo(CommonUtils.toString(index));
        //service
        if (isServiceFlg) {
            tbilDBean.setItemLevel("0");
            tbilDBean.setItemDesc(CommonUtils.toString(map.get("BILL_DESC_TEXT")));
            tbilDBean.setItemQty("1");
            tbilDBean.setItemGst("0");
            tbilDBean.setItemExportAmt("0");
            tbilDBean.setApplyGst("0");
            //isDisplay
            String isLumpSum = CommonUtils.toString(map.get("IS_LUMP_SUM"));
            String isDisplay = "";
            if("0".equals(isLumpSum)) {
                isDisplay="0";
            } else {
                isDisplay="1";
            }
            tbilDBean.setIsDisplay(isDisplay);
            tbilDBean.setItemType("N");
            
            //#154 start
            //displayDisCount
            String discLumpSum = CommonUtils.toString(map.get("DISC_LUMP_SUM"));
            String displayDisCount = "";
            if("1".equals(discLumpSum)) {
            	displayDisCount="1";
            } else {
            	displayDisCount="0";
            }
            tbilDBean.setDisplayDiscount(displayDisCount);
            tbilDBean.setTaxCode("");
            tbilDBean.setTaxRate("0");
            tbilDBean.setItemExportGST("0");
            //#154 end
        } else {
            //sub plan
            tbilDBean.setItemLevel("1");
            tbilDBean.setItemDesc(CommonUtils.toString(map.get("ITEM_DESC_TEXT")));
            tbilDBean.setItemQty(CommonUtils.toString(map.get("QUANTITY")));
            tbilDBean.setItemUprice(CommonUtils.toString(map.get("UNIT_PRICE")));
            String totalAmount = CommonUtils.toString(map.get("TOTAL_AMOUNT"));
            tbilDBean.setItemAmt(totalAmount);

            
            String applyGst = CommonUtils.toString(map.get("APPLY_GST"));
            tbilDBean.setApplyGst(applyGst);
            
            //#154 Start
            String itemDisc = CommonUtils.toString(map.get("DISC_AMOUNT"));
            tbilDBean.setItemDisc(itemDisc);
            Double itemDiscTmp = Math.abs(Double.parseDouble(itemDisc));  
            String itemSubTotal = CommonUtils.toString(Double.parseDouble(totalAmount) - itemDiscTmp);
            tbilDBean.setItemSubTotal(itemSubTotal);
            
            Map<String, Object> taxInfo = this.queryDAO.executeForMap("B_BIL.getM_TAXInfo", applyGst);
            Integer taxRate = Integer.parseInt(taxInfo.get("TAX_RATE").toString());
            tbilDBean.setTaxCode(CommonUtils.toString(taxInfo.get("TAX_CODE")));
            tbilDBean.setTaxRate(CommonUtils.toString(taxRate));
            String billCurrency = CommonUtils.toString(map.get("BILL_CURRENCY"));
            /*double tmp = Double.parseDouble(itemSubTotal) * taxRate;
            BigDecimal b = new BigDecimal(tmp/100);*/
            BigDecimal tmp = new BigDecimal(Double.parseDouble(itemSubTotal) * taxRate/100);
            double f1 = tmp.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();
            double f2 = tmp.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
            if("JPY".equals(billCurrency)){
            	tbilDBean.setItemGst(Double.toString(f1));
            }else {
            	tbilDBean.setItemGst(Double.toString(f2));
			}
            tbilDBean.setItemExportAmt(tbilDBean.getItemGst());
            /*String pGSTAmount = "";
            if("1".equals(applyGst)) {
                pGSTAmount = CommonUtils.toString((Double.parseDouble(totalAmount) * pGST) / 100);
            } else {
                pGSTAmount = "0";
            }
            tbilDBean.setItemGst(pGSTAmount);
            tbilDBean.setItemExportAmt(totalAmount);*/                        
            //#154 end
            
            //isDisplay
            String isLumpSum = CommonUtils.toString(map.get("IS_LUMP_SUM"));
            String isDisplay = "";
            if("0".equals(isLumpSum)) {
                isDisplay="1";
            } else {
                isDisplay="0";
            }
            tbilDBean.setIsDisplay(isDisplay);
            
            //displayDisCount
            String discLumpSum = CommonUtils.toString(map.get("DISC_LUMP_SUM"));
            String displayDisCount = "";
            if("0".equals(discLumpSum)) {
            	displayDisCount="1";
            } else {
            	displayDisCount="0";
            }
            tbilDBean.setDisplayDiscount(displayDisCount);
            
            tbilDBean.setItemType(CommonUtils.toString(map.get("ITEM_TYPE")));
            tbilDBean.setSvcLevel1(CommonUtils.toString(map.get("SUB_SVC_LEVEL1")));
            tbilDBean.setSvcLevel2(CommonUtils.toString(map.get("SUB_SVC_LEVEL2")));
            tbilDBean.setJobNo(CommonUtils.toString(map.get("JOB_NO")));
            tbilDBean.setIsDisplayJobNo(CommonUtils.toString(map.get("IS_DISPLAY_JOBNO")));
        }
        tbilDBean.setIdCustPlan(CommonUtils.toString(map.get("ID_CUST_PLAN")));
        tbilDBean.setIdCustPlanGrp(CommonUtils.toString(map.get("ID_CUST_PLAN_GRP")));
        tbilDBean.setIdCustPlanLink(CommonUtils.toString(map.get("ID_CUST_PLAN_LINK")));
        tbilDBean.setIsDisplayMinSvc(CommonUtils.toString(map.get("MIN_SVC_PERIOD")));
        tbilDBean.setMinSvcFrom(CommonUtils.formatDate(map.get("MIN_SVC_START"), "dd/MM/yyyy"));
        tbilDBean.setMinSvcTo(CommonUtils.formatDate(map.get("MIN_SVC_END"), "dd/MM/yyyy"));
        tbilDBean.setMinSvcFromDisplay(CommonUtils.toString(map.get("MIN_SVC_START_TEXT")));
        tbilDBean.setMinSvcToDisplay(CommonUtils.toString(map.get("MIN_SVC_END_TEXT")));
        //#154 start
        tbilDBean.setPoNo(CommonUtils.toString(map.get("CUST_PO")));
        //#154 end
        return tbilDBean;
    }
  //Fix Item_desc length over 4000 character 20180404 CT
  	private String clobToString(Clob data) {
          StringBuilder sb = new StringBuilder();
          try {
              Reader reader = data.getCharacterStream();
              BufferedReader br = new BufferedReader(reader);

              String line;
              while(null != (line = br.readLine())) {
                  sb.append(line);
                  sb.append("\n");
              }
              br.close();
          } catch (SQLException e) {
              // handle this exception
          } catch (IOException e) {
              // handle this exception
          }
          return sb.toString();
      }
  	//Fix Item_desc length over 4000 character 20180404 CT
}
