package nttdm.bsys.m_cdm.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.m_cdm.bean.MCodeBean;

/**
 * 
 * @author Duyen.Vo
 *
 */
public class M_CDMR02Input implements Serializable{

	/**
	 * <div>serial version</div>
	 */
	private static final long serialVersionUID = 3798543771149719860L;
	
	/**
	 * <div>store the properties of qcs no</div>
	 */
	private MCodeBean[] qcsno = new MCodeBean[4];
	
	/**
	 * <div>store the properties of quono</div>
	 */
	private MCodeBean[] quono = new MCodeBean[4];
	
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
	 * <div>store the properties of invno</div>
	 */
	private MCodeBean[] invno = new MCodeBean[4];
	
	/**
	 * <div>store the properties of dbtno</div>
	 */
	private MCodeBean[] dbtno = new MCodeBean[4];
	
    /**
     * <div>store the properties of cdtno</div>
     */
    private MCodeBean[] cdtno = new MCodeBean[4];
    
    /**
     * <div>store the properties of ntinv</div>
     */
    private MCodeBean[] ntinv = new MCodeBean[4];

    /**
     * <div>store the properties of rcpno</div>
     */
    private MCodeBean[] rcpno = new MCodeBean[4];

    /**
     * <div>store the properties of scpid</div>
     */
    private MCodeBean[] scpid = new MCodeBean[4];

    /**
     * <div>store the properties of ctmid</div>
     */
    private MCodeBean[] ctmid = new MCodeBean[4];
	
	/**
	 * <div>store the properties of bac no</div>
	 */
	private MCodeBean[] bacno = new MCodeBean[4];
	
	/**
     * <div>store the properties of soa no</div>
     */
    private MCodeBean[] soano = new MCodeBean[4];
	
	/**
	 * <div>uvoObject</div>
	 */
	private BillingSystemUserValueObject uvoObject = null;
	
	public void setQcsno(MCodeBean[] qcsno) {
		this.qcsno = qcsno;
	}

	public MCodeBean[] getQcsno() {
		return qcsno;
	}

	public MCodeBean[] getQuono() {
		return quono;
	}

	public void setQuono(MCodeBean[] quono) {
		this.quono = quono;
	}

	public MCodeBean[] getBifno() {
		return bifno;
	}

	public void setBifno(MCodeBean[] bifno) {
		this.bifno = bifno;
	}

	public MCodeBean[] getInvno() {
		return invno;
	}

	public void setInvno(MCodeBean[] invno) {
		this.invno = invno;
	}

	public MCodeBean[] getDbtno() {
		return dbtno;
	}

	public void setDbtno(MCodeBean[] dbtno) {
		this.dbtno = dbtno;
	}

    /**
     * cdtno get method
     * 
     * @return cdtno
     */
    public MCodeBean[] getCdtno() {
        return cdtno;
    }

    /**
     * cdtno set method
     * 
     * @param cdtno MCodeBean[]
     */
    public void setCdtno(MCodeBean[] cdtno) {
        this.cdtno = cdtno;
    }

    /**
     * rcpCodeValue get method
     * 
     * @return rcpCodeValue
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
     * ctmid get method
     * 
     * @return ctmid
     */
    public MCodeBean[] getCtmid() {
        return ctmid;
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
     * bacno get method
     * 
     * @return bacno
     */
    public MCodeBean[] getBacno() {
        return bacno;
    }

    /**
     * bacno set method
     * 
     * @param bacno MCodeBean[]
     */
    public void setBacno(MCodeBean[] bacno) {
        this.bacno = bacno;
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

    public void setUvoObject(BillingSystemUserValueObject uvoObject) {
        this.uvoObject = uvoObject;
    }

    public BillingSystemUserValueObject getUvoObject() {
        return uvoObject;
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

	public MCodeBean[] getNtinv() {
		return ntinv;
	}

	public void setNtinv(MCodeBean[] ntinv) {
		this.ntinv = ntinv;
	}    
}
