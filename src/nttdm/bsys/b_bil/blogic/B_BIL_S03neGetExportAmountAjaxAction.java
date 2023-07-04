/**
 * @(#)B_BIL_S03neGetExportAmountAjaxAction.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/01/17
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CUR_P01;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author gplai
 *
 */
public class B_BIL_S03neGetExportAmountAjaxAction extends ActionEx {

    private QueryDAO queryDAO;
    
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        BigDecimal exportAmtValue = new BigDecimal("0");
        String billCur = CommonUtils.toString(req.getParameter("billingCurrency")).trim();
        String expCur = CommonUtils.toString(req.getParameter("exportCurrency")).trim();
        //String fixedForex = CommonUtils.toString(req.getParameter("fixedForex")).trim();
        String curRate = CommonUtils.toString(req.getParameter("curRate")).trim();
        String itemAmtString = CommonUtils.toString(req.getParameter("itemAmtString"));
        String itemGstString = CommonUtils.toString(req.getParameter("itemApplyGstString"));
        String[] itemAmts = itemAmtString.split(",");
        String[] itemGsts = itemGstString.split(",");
        if(itemAmtString!=null&&!"".equals(itemAmtString)&&itemGstString!=null&&!"".equals(itemGstString)){
        	if(!CommonUtils.isEmpty(billCur)
                    &&!CommonUtils.isEmpty(expCur)
                    &&!"-".equals(expCur)
                    &&!billCur.equals(expCur)) {
            	double itemAmtDouble;
            	G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
            	String getAmtTemp;
            	String itemAmtTemp;
            	for(int i=0;i<itemAmts.length;++i){
                    double gstP = Double.valueOf(itemGsts[i]);
                    gstP = gstP/100;
            		itemAmtDouble = Double.valueOf(itemAmts[i]);
           			getAmtTemp = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble*gstP, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
           			exportAmtValue = exportAmtValue.add(new BigDecimal(getAmtTemp));
            		itemAmtTemp = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
            		exportAmtValue = exportAmtValue.add(new BigDecimal(itemAmtTemp));
            	
            	}
            	exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
            }
            out.write(formatter.format(exportAmtValue));
        }
        else{
        	out.write("0");
        }
        return null;
    }

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}
