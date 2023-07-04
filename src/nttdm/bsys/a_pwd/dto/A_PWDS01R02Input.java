/*
 * @(#)A_PWDS01R02Input.java
 *
 *
 */
package nttdm.bsys.a_pwd.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.*;

/**
 * 入力DTOクラス。
 * 
 * @author ss042
 */
public class A_PWDS01R02Input implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4275578010694072867L;
	BillingSystemUserValueObject uvo = null;

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	
}