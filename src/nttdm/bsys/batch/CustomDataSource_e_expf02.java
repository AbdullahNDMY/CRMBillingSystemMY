/**
 * @(#)detail_info_e_exp_f02.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.batch;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Data Source for Statement Report
 */
public class CustomDataSource_e_expf02 implements JRDataSource {
    private int index = -1;
    List<detail_info_e_exp_f02> data;

    public List<detail_info_e_exp_f02> getData() {
        return data;
    }

    public void setData(List<detail_info_e_exp_f02> data) {
        this.data = data;
    }

    public CustomDataSource_e_expf02() {
        data = new ArrayList<detail_info_e_exp_f02>();
    }
 
    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;
        String sName = field.getName();

        detail_info_e_exp_f02 currdetail = data.get(index);
        if (currdetail == null)
            return null;

        if (sName.equals("invoicedate")) {
            o = currdetail.getInVoiceDate();
        } else if (sName.equals("invoicenumber")) {
            o = currdetail.getInVoiceNumber();
        } else if (sName.equals("entry")) {
            o = currdetail.getEntryType();
        } else if (sName.equals("amount")) {
            o = currdetail.getAmount_due();
            if(currdetail.getDoc_type().equalsIgnoreCase("CN")||currdetail.getDoc_type().equalsIgnoreCase("CB")){
            	if(currdetail.getAmount_due()==0){
            		o=null;
            	}
            }
        } else if (sName.equals("itemactivity")) {
            if(currdetail.getDoc_type().equalsIgnoreCase("IN")||currdetail.getDoc_type().equalsIgnoreCase("DN")){
            	if(currdetail.getAmount_due()==currdetail.getItem_activity()){
            		o = null;
            	}else{
            		o = currdetail.getItem_activity();  
            	}
            }else{
            	if(currdetail.getItem_activity()==0){
            		o = null;
            	}else{
            		o = currdetail.getItem_activity(); 
            	}
            }
        } else if (sName.equals("po")) {
            o = currdetail.getPo();
        } else if (sName.equals("payment")) {
            o = currdetail.getPaymentid();
        }
        return o;
    }

    public boolean next() throws JRException {
        index += 1;
        return (index < data.size());
    }
}
