package nttdm.bsys.m_bnk.bean;

import java.io.Serializable;

public class M_BNK_AdressRA implements Serializable {
	
	private static final long serialVersionUID = -746633336857442359L;
	private String BK_ADR_LINE1;
	private String BK_ADR_LINE2;
	private String BK_ADR_LINE3;
	private String BK_ZIP_CODE;
	private String COUNTRY;
	public String getBK_ZIP_CODE() {
		return BK_ZIP_CODE;
	}
	public void setBK_ZIP_CODE(String bk_zip_code) {
		BK_ZIP_CODE = bk_zip_code;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String country) {
		COUNTRY = country;
	}
	public String getBK_ADR_LINE1() {
		return BK_ADR_LINE1;
	}
	public void setBK_ADR_LINE1(String bk_adr_line1) {
		BK_ADR_LINE1 = bk_adr_line1;
	}
	public String getBK_ADR_LINE2() {
		return BK_ADR_LINE2;
	}
	public void setBK_ADR_LINE2(String bk_adr_line2) {
		BK_ADR_LINE2 = bk_adr_line2;
	}
	public String getBK_ADR_LINE3() {
		return BK_ADR_LINE3;
	}
	public void setBK_ADR_LINE3(String bk_adr_line3) {
		BK_ADR_LINE3 = bk_adr_line3;
	}
	
}
