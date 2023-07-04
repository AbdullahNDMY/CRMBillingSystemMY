/*
 * @(#)M_COMS01IO.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;
import java.util.*;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author Tran Manh Hung
 */
public class M_COMS01IO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4612938216379629845L;

    // input

    private String idCompany;
    private BillingSystemUserValueObject uvo;

    // input & output
    private List<CompanyBank> listCompanyBank;

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

    public String getIdLogin() {
        String idLogin = "";
        if (uvo != null) {
            idLogin = uvo.getId_user();
        }
        return idLogin;
    }

    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

}