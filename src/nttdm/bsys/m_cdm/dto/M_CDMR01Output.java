package nttdm.bsys.m_cdm.dto;

import java.io.Serializable;

import nttdm.bsys.m_cdm.bean.MCodeBean;

/**
 * 
 * @author Duyen.Vo
 *
 */
public class M_CDMR01Output implements Serializable {

    /**
     * <div>serial version</div>
     */
    private static final long serialVersionUID = 7571812219381056163L;

    /**
     * <div>store the properties of qcs no</div>
     */
    private MCodeBean[] qcsno = new MCodeBean[4];

    /**
     * <div>store generated code value of qcs no</div>
     */
    private String qcsCodeValue = null;

    /**
     * <div>store the properties of quono</div>
     */
    private MCodeBean[] quono = new MCodeBean[4];

    /**
     * <div>store generated code value of quono</div>
     */
    private String quoCodeValue = null;

    /**
     * <div>store the properties of bifno</div>
     */
    private MCodeBean[] bifno = new MCodeBean[4];
    
    /**
     * <div>store the properties of bifdn</div>
     */
    private MCodeBean[] bifdn = new MCodeBean[4];
    
    /**
     * <div>store the properties of bifcn</div>
     */
    private MCodeBean[] bifcn = new MCodeBean[4];

    /**
     * <div>store generated code value of bifno</div>
     */
    private String bifCodeValue = null;
    
    /**
     * <div>store generated code value of bifdn</div>
     */
    private String bifdnCodeValue = null;
    
    /**
     * <div>store generated code value of bifcn</div>
     */
    private String bifcnCodeValue = null;

    /**
     * <div>store the properties of invno</div>
     */
    private MCodeBean[] invno = new MCodeBean[4];

    /**
     * <div>store generated code value of invno</div>
     */
    private String invCodeValue = null;

    /**
     * <div>store the properties of dbtno</div>
     */
    private MCodeBean[] dbtno = new MCodeBean[4];

    /**
     * <div>store generated code value of dbtno</div>
     */
    private String dbtCodeValue = null;

    /**
     * <div>store the properties of cdtno</div>
     */
    private MCodeBean[] cdtno = new MCodeBean[4];

    /**
     * <div>store generated code value of cdtno</div>
     */
    private String cdtCodeValue = null;
    
    /**
     * <div>store the properties of ntinv</div>
     */
    private MCodeBean[] ntinv = new MCodeBean[4];

    /**
     * <div>store generated code value of cdtno</div>
     */
    private String ntinvCodeValue = null;

    /**
     * <div>store the properties of rcpno</div>
     */
    private MCodeBean[] rcpno = new MCodeBean[4];

    /**
     * <div>store generated code value of rcpno</div>
     */
    private String rcpCodeValue = null;

    /**
     * <div>store the properties of scpid</div>
     */
    private MCodeBean[] scpid = new MCodeBean[4];

    /**
     * <div>store generated code value of scpid</div>
     */
    private String scpCodeValue = null;

    /**
     * <div>store the properties of ctmid</div>
     */
    private MCodeBean[] ctmid = new MCodeBean[4];

    /**
     * <div>store generated code value of ctmid</div>
     */
    private String ctmCodeValue = null;
    
    /**
     * <div>store the properties of bac no</div>
     */
    private MCodeBean[] bacno = new MCodeBean[4];

    /**
     * <div>store generated code value of bac no</div>
     */
    private String bacCodeValue = null;
    
    /**
     * <div>store the properties of soa no</div>
     */
    private MCodeBean[] soano = new MCodeBean[4];

    /**
     * <div>store generated code value of soa no</div>
     */
    private String soaCodeValue = null;
    
    /**
     * user access mode
     */
    private String mode = null;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public MCodeBean[] getQcsno() {
		return qcsno;
	}
	
	public void setQcsno(MCodeBean[] qcsno) {
		this.qcsno = qcsno;
	}
	
	public String getQcsCodeValue() {
		return qcsCodeValue;
	}
	
	public void setQcsCodeValue(String qcsCodeValue) {
		this.qcsCodeValue = qcsCodeValue;
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
	
	public String getBifCodeValue() {
		return bifCodeValue;
	}
	
	public void setBifCodeValue(String bifCodeValue) {
		this.bifCodeValue = bifCodeValue;
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
     * cdtCodeValue get method
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
     * rcpno get
     * 
     * @return rcpno
     */
    public MCodeBean[] getRcpno() {
        return rcpno;
    }

    /**
     * rcpno set method
     * 
     * @param rcpno
     * MCodeBean[]
     */
    public void setRcpno(MCodeBean[] rcpno) {
        this.rcpno = rcpno;
    }

    /**
     * rcpCodeValue get method
     * 
     * @return rcpCodeValue
     */
    public String getRcpCodeValue() {
        return rcpCodeValue;
    }

    /**
     * rcpCodeValue set method
     * 
     * @param rcpCodeValue
     * String
     */
    public void setRcpCodeValue(String rcpCodeValue) {
        this.rcpCodeValue = rcpCodeValue;
    }

    /**
     * scpid get method
     * 
     * @return scpid
     */
    public MCodeBean[] getScpid() {
        return scpid;
    }

    /**
     * scpid set method
     * 
     * @param scpid
     * MCodeBean[]
     */
    public void setScpid(MCodeBean[] scpid) {
        this.scpid = scpid;
    }

    /**
     * getScpCodeValue get method
     * 
     * @return getScpCodeValue
     */
    public String getScpCodeValue() {
        return scpCodeValue;
    }

    /**
     * scpCodeValue set method
     * 
     * @param scpCodeValue
     * String
     */
    public void setScpCodeValue(String scpCodeValue) {
        this.scpCodeValue = scpCodeValue;
    }

    /**
     * ctmid set method
     * 
     * @param ctmid
     * MCodeBean[]
     */
    public void setCtmid(MCodeBean[] ctmid) {
        this.ctmid = ctmid;
    }

    /**
     * ctmCodeValue get method
     * 
     * @return ctmCodeValue
     */
    public String getCtmCodeValue() {
        return ctmCodeValue;
    }

    /**
     * ctmCodeValue set method
     * 
     * @param ctmCodeValue
     * String
     */
    public void setCtmCodeValue(String ctmCodeValue) {
        this.ctmCodeValue = ctmCodeValue;
    }

    /**
     * ctmid get method
     * 
     * @return ctmid
     */
    public MCodeBean[] getCtmid() {
        return ctmid;
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
     * bacCodeValue get method
     * @return bacCodeValue
     */
    public String getBacCodeValue() {
        return bacCodeValue;
    }

    /**
     * bacCodeValue set method
     * @param bacCodeValue String
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
}
