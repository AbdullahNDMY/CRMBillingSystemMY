package nttdm.bsys.b_csb.dto;

import java.io.Serializable;

public class PaymentRefDetail implements Serializable {

	private static final long serialVersionUID = 1890547213748918746L;

	private String PAYMENT_METHOD;
	
	private String REF_DETAIL;

	public String getPAYMENT_METHOD() {
		return PAYMENT_METHOD;
	}

	public void setPAYMENT_METHOD(String pAYMENT_METHOD) {
		PAYMENT_METHOD = pAYMENT_METHOD;
	}

	public String getREF_DETAIL() {
		return REF_DETAIL;
	}

	public void setREF_DETAIL(String rEF_DETAIL) {
		REF_DETAIL = rEF_DETAIL;
	}
	
}
