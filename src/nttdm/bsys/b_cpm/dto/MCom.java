package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class MCom implements Serializable {
	private String lblPrimaryDomai;
	private String lblSecondaryDom;
	private String tbxRackNo;
	private String tbxMaxPower;
	private String lblProxyServerN;
	private String lblPortNumber;
	private String idLogin;

	
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getLblProxyServerN() {
		return lblProxyServerN;
	}
	public void setLblProxyServerN(String lblProxyServerN) {
		this.lblProxyServerN = lblProxyServerN;
	}
	public String getLblPortNumber() {
		return lblPortNumber;
	}
	public void setLblPortNumber(String lblPortNumber) {
		this.lblPortNumber = lblPortNumber;
	}
	public String getLblPrimaryDomai() {
		return lblPrimaryDomai;
	}
	public void setLblPrimaryDomai(String lblPrimaryDomai) {
		this.lblPrimaryDomai = lblPrimaryDomai;
	}
	public String getLblSecondaryDom() {
		return lblSecondaryDom;
	}
	public void setLblSecondaryDom(String lblSecondaryDom) {
		this.lblSecondaryDom = lblSecondaryDom;
	}
	public String getTbxRackNo() {
		return tbxRackNo;
	}
	public void setTbxRackNo(String tbxRackNo) {
		this.tbxRackNo = tbxRackNo;
	}
	public String getTbxMaxPower() {
		return tbxMaxPower;
	}
	public void setTbxMaxPower(String tbxMaxPower) {
		this.tbxMaxPower = tbxMaxPower;
	}

	
}
