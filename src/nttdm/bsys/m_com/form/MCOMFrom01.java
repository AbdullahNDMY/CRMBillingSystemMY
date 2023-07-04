/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COM
 * FUNCTION       : Define Form MCOM
 * FILE NAME      : MCOMFrom01.java
 *
 * Copyright (C) 2014 NTTDATA Corporation
 * 
 **********************************************************/
package nttdm.bsys.m_com.form;

import java.util.List;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.common.util.dto.AutoScaleList;
import nttdm.bsys.m_com.dto.CompanyBank;

/**
 * M_COM_01_01 form
 * 
 * @author loamanma
 * 
 */
public class MCOMFrom01 extends ValidatorActionFormEx {

    /**
     * 
     */
    private static final long serialVersionUID = 8920016291619619802L;

    private String idCompany;

    private List<CompanyBank> listCompanyBank = new AutoScaleList<CompanyBank>(new CompanyBank());

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public List<CompanyBank> getListCompanyBank() {
        return listCompanyBank;
    }

    public void setListCompanyBank(List<CompanyBank> listCompanyBank) {
        this.listCompanyBank = listCompanyBank;
    }

}
