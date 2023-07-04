/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Set Logo
 * FILE NAME      : M_COMS01Bean.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.bean;
/**
 * BusinessLogic class. 
 * @author NTT Data Vietnam	
 * 
 */
public class M_COMS01Bean {
	private byte[] LOGO;

	public byte[] getLOGO() {
		return LOGO;
	}

	public void setLOGO(byte[] logo) {
		LOGO = logo;
	}


}
