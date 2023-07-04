/*
 * @(#)M_CDMFormBean.java
 *
 *
 */
package nttdm.bsys.m_cdm.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * ActionForm class.
 * 
 * @author Duyen.Vo
 */
public class M_CDMFormBean extends ValidatorActionFormEx {

    /**
     * <div>serial version</div>
     */
    private static final long serialVersionUID = -548152372635303392L;

    /**
     * <div>READONLY mode</div>
     */
    public static final String READONLY = "READONLY";

    /**
     * <div>QCSNO number</div>
     */
    public static final String QCSNO = "QCSNO";

    /**
     * <div>QUONO number</div>
     */
    public static final String QUONO = "QUONO";

    /**
     * <div>BIFNO number</div>
     * 
     */
    public static final String BIFNO = "BIFNO";
    
    
    /**
     * <div>BIFDN number</div>
     */
    public static final String BIFDN = "BIFDN";
    
    /**
     * <div>BIFCN number</div>
     */
    public static final String BIFCN = "BIFCN";
    
    /**
     * <div>INVNO number</div>
     */
    public static final String INVNO = "INVNO";

    /**
     * <div>DBTNO number</div>
     */
    public static final String DBTNO = "DBTNO";

    /**
     * <div>CDTNO number</div>
     */
    public static final String CDTNO = "CDTNO";
    
    /**
     * <div>NTINV number</div>
     */
    public static final String NTINV = "NTINV";

    /**
     * <div>RCPNO number</div>
     */
    public static final String RCPNO = "RCPNO";

    /**
     * <div>SCPID number</div>
     */
    public static final String SCPID = "SCPID";

    /**
     * <div>CTMID number</div>
     */
    public static final String CTMID = "CSTID";

    /**
     * <div>BACNO number</div>
     */
    public static final String BACNO = "BACNO";
    
    /**
     * <div>SOANO number</div>
     */
    public static final String SOANO = "SOANO";
    
    /**
     * <div>RUNNING type val</div>
     */
    public static final String RUNNING = "05";

    /**
     * <div>PREFIX type val</div>
     */
    public static final String PREFIX = "06";

    /**
     * <div>YYYYMMDD type val</div>
     */
    public static final String YYYYMMDD = "03";

    /**
     * <div>YYMMDD type val</div>
     */
    public static final String YYMMDD = "04";
    
    /**
     * <div>MAX_CONTROLS maximum combobox A for each number</div>
     */
    public static final int MAX_CONTROLS = 10;

    /**
     * <div>test</div>
     */
    private String test = null;

    /**
     * <div>mode screen</div>
     */
    private String mode = null;

    /**
     * <div>store generated code value of usrid</div>
     */
    private String usrCodeValue = null;

    /**
     * <div>store the properties of qcs no</div>
     */
    private MCodeBean[] qcsno = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of qcs no</div>
     */
    private String qcsCodeValue = null;

    /**
     * <div>store the properties of quono</div>
     */
    private MCodeBean[] quono = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of quono</div>
     */
    private String quoCodeValue = null;

    /**
     * <div>store the properties of bifno</div>
     */
    private MCodeBean[] bifno = new MCodeBean[MAX_CONTROLS];
    
    /**
     * <div>store generated code value of bifno</div>
     */
    private String bifCodeValue = null;
    
    /**
     * <div>store the properties of bifdn</div>
     */
    private MCodeBean[] bifdn = new MCodeBean[MAX_CONTROLS];
    
    /**
     * <div>store generated code value of bifdn</div>
     */
    private String bifdnCodeValue = null;
    
    /**
     * <div>store the properties of bifcn</div>
     */
    private MCodeBean[] bifcn = new MCodeBean[MAX_CONTROLS];
    
    /**
     * <div>store generated code value of bifcn</div>
     */
    private String bifcnCodeValue = null;

    /**
     * <div>store the properties of invno</div>
     */
    private MCodeBean[] invno = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of invno</div>
     */
    private String invCodeValue = null;

    /**
     * <div>store the properties of dbtno</div>
     */
    private MCodeBean[] dbtno = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of dbtno</div>
     */
    private String dbtCodeValue = null;

    /**
     * <div>store the properties of cdtno</div>
     */
    private MCodeBean[] cdtno = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of cdtno</div>
     */
    private String cdtCodeValue = null;
    
    /**
     * <div>store the properties of ntinv</div>
     */
    private MCodeBean[] ntinv = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of ntinv</div>
     */
    private String ntinvCodeValue = null;

    /**
     * <div>store the properties of rcpno</div>
     */
    private MCodeBean[] rcpno = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of rcpno</div>
     */
    private String rcpCodeValue = null;

    /**
     * <div>store the properties of scpid</div>
     */
    private MCodeBean[] scpid = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of scpid</div>
     */
    private String scpCodeValue = null;

    /**
     * <div>store the properties of ctmid</div>
     */
    private MCodeBean[] ctmid = new MCodeBean[MAX_CONTROLS];

    /**
     * <div>store generated code value of ctmid</div>
     */
    private String ctmCodeValue = null;
    
    /**
     * <div>store the properties of bac no</div>
     */
    private MCodeBean[] bacno = new MCodeBean[MAX_CONTROLS];
    
    /**
     * <div>store generated code value of bacno</div>
     */
    private String bacCodeValue = null;
    
    /**
     * <div>store the properties of bac no</div>
     */
    private MCodeBean[] soano = new MCodeBean[MAX_CONTROLS];
    
    /**
     * <div>store generated code value of bacno</div>
     */
    private String soaCodeValue = null;
    
    private String hidResetNoOption;

    public void setQcsno(MCodeBean[] qcsno) {
        this.qcsno = qcsno;
    }

    public MCodeBean[] getQcsno() {
        return qcsno;
    }

    public void setQcsCodeValue(String qcsCodeValue) {
        this.qcsCodeValue = qcsCodeValue;
    }

    public String getQcsCodeValue() {
        return qcsCodeValue;
    }

    public String getUsrCodeValue() {
        return usrCodeValue;
    }

    public void setUsrCodeValue(String usrCodeValue) {
        this.usrCodeValue = usrCodeValue;
    }

    public MCodeBean[] getQuono() {
        return quono;
    }

    public void setQuono(MCodeBean[] quono) {
        this.quono = quono;
    }

    public String getQuoCodeValue() {
        return quoCodeValue;
    }

    public void setQuoCodeValue(String quoCodeValue) {
        this.quoCodeValue = quoCodeValue;
    }

    public MCodeBean[] getBifno() {
        return bifno;
    }

    public void setBifno(MCodeBean[] bifno) {
        this.bifno = bifno;
    }

	public MCodeBean[] getBifdn() {
		return bifdn;
	}

	public void setBifdn(MCodeBean[] bifdn) {
		this.bifdn = bifdn;
	}

	public MCodeBean[] getBifcn() {
		return bifcn;
	}

	public void setBifcn(MCodeBean[] bifcn) {
		this.bifcn = bifcn;
	}

	public String getBifCodeValue() {
        return bifCodeValue;
    }

    public void setBifCodeValue(String bifCodeValue) {
        this.bifCodeValue = bifCodeValue;
    }
    
    

    public String getBifdnCodeValue() {
		return bifdnCodeValue;
	}

	public void setBifdnCodeValue(String bifdnCodeValue) {
		this.bifdnCodeValue = bifdnCodeValue;
	}

	public String getBifcnCodeValue() {
		return bifcnCodeValue;
	}

	public void setBifcnCodeValue(String bifcnCodeValue) {
		this.bifcnCodeValue = bifcnCodeValue;
	}

	public MCodeBean[] getInvno() {
        return invno;
    }

    public void setInvno(MCodeBean[] invno) {
        this.invno = invno;
    }

    public String getInvCodeValue() {
        return invCodeValue;
    }

    public void setInvCodeValue(String invCodeValue) {
        this.invCodeValue = invCodeValue;
    }

    public MCodeBean[] getDbtno() {
        return dbtno;
    }

    public void setDbtno(MCodeBean[] dbtno) {
        this.dbtno = dbtno;
    }

    public String getDbtCodeValue() {
        return dbtCodeValue;
    }

    public void setDbtCodeValue(String dbtCodeValue) {
        this.dbtCodeValue = dbtCodeValue;
    }

    /**
     * cdtno get method
     * @return cdtno
     */
    public MCodeBean[] getCdtno() {
        return cdtno;
    }

    /**
     * cdtno set method
     * @param cdtno MCodeBean[]
     */
    public void setCdtno(MCodeBean[] cdtno) {
        this.cdtno = cdtno;
    }

    /**
     * CdtCodeValue get method
     * @return cdtCodeValue
     */
    public String getCdtCodeValue() {
        return cdtCodeValue;
    }

    /**
     * cdtCodeValue set method
     * @param cdtCodeValue String
     */
    public void setCdtCodeValue(String cdtCodeValue) {
        this.cdtCodeValue = cdtCodeValue;
    }
    
    

    public MCodeBean[] getNtinv() {
		return ntinv;
	}

	public void setNtinv(MCodeBean[] ntinv) {
		this.ntinv = ntinv;
	}

	public String getNtinvCodeValue() {
		return ntinvCodeValue;
	}

	public void setNtinvCodeValue(String ntinvCodeValue) {
		this.ntinvCodeValue = ntinvCodeValue;
	}

	/**
     * rcpCodeValue get method
     * @return rcpCodeValue
     */
    public MCodeBean[] getRcpno() {
        return rcpno;
    }

    /**
     * rcpno set method
     * @param rcpno MCodeBean[]
     */
    public void setRcpno(MCodeBean[] rcpno) {
        this.rcpno = rcpno;
    }

    /**
     * rcpCodeValue get method
     * @return rcpCodeValue
     */
    public String getRcpCodeValue() {
        return rcpCodeValue;
    }

    /**
     * rcpCodeValue set method
     * @param rcpCodeValue String
     */
    public void setRcpCodeValue(String rcpCodeValue) {
        this.rcpCodeValue = rcpCodeValue;
    }

    /**
     * scpid get method
     * @return scpid
     */
    public MCodeBean[] getScpid() {
        return scpid;
    }

    /**
     * scpid set method
     * @param scpid MCodeBean[]
     */
    public void setScpid(MCodeBean[] scpid) {
        this.scpid = scpid;
    }

    /**
     * getScpCodeValue get method
     * @return getScpCodeValue
     */
    public String getScpCodeValue() {
        return scpCodeValue;
    }

    /**
     * scpCodeValue set method
     * @param scpCodeValue
     *            String
     */
    public void setScpCodeValue(String scpCodeValue) {
        this.scpCodeValue = scpCodeValue;
    }

    /**
     * ctmCodeValue get method
     * @return ctmCodeValue
     */
    public MCodeBean[] getCtmid() {
        return ctmid;
    }

    /**
     * ctmid set method
     * @param ctmid MCodeBean[]
     */
    public void setCtmid(MCodeBean[] ctmid) {
        this.ctmid = ctmid;
    }

    /**
     * ctmid get method
     * @return ctmid
     */
    public String getCtmCodeValue() {
        return ctmCodeValue;
    }

    /**
     * ctmCodeValue set method
     * @param ctmCodeValue String
     */
    public void setCtmCodeValue(String ctmCodeValue) {
        this.ctmCodeValue = ctmCodeValue;
    }
    
    /**
     * bacno get method
     * @return bacno
     */
    public MCodeBean[] getBacno() {
        return bacno;
    }

    /**
     * bacno set method
     * @param bacno MCodeBean[]
     */
    public void setBacno(MCodeBean[] bacno) {
        this.bacno = bacno;
    }

    /**
     * getBacCodeValue get method
     * @return getBacCodeValue
     */
    public String getBacCodeValue() {
        return bacCodeValue;
    }

    /**
     * bacCodeValue set method
     * @param bacCodeValue
     *            String
     */
    public void setBacCodeValue(String bacCodeValue) {
        this.bacCodeValue = bacCodeValue;
    }

	/**
     * @return the soano
     */
    public MCodeBean[] getSoano() {
        return soano;
    }

    /**
     * @param soano the soano to set
     */
    public void setSoano(MCodeBean[] soano) {
        this.soano = soano;
    }

    /**
     * @return the soaCodeValue
     */
    public String getSoaCodeValue() {
        return soaCodeValue;
    }

    /**
     * @param soaCodeValue the soaCodeValue to set
     */
    public void setSoaCodeValue(String soaCodeValue) {
        this.soaCodeValue = soaCodeValue;
    }

    /**
     * @return the hidResetNoOption
     */
    public String getHidResetNoOption() {
        return hidResetNoOption;
    }

    /**
     * @param hidResetNoOption the hidResetNoOption to set
     */
    public void setHidResetNoOption(String hidResetNoOption) {
        this.hidResetNoOption = hidResetNoOption;
    }

    public M_CDMFormBean(){
		//setMode(READONLY);
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setTest(String test) {
		this.test = test;
	}
	
	public String getTest() {
		return test;
	}
	
}