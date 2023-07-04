package nttdm.bsys.common.bean;

import java.util.Date;

public class E_EXP_F01_SQL2_Bean {
	private String ID_REF;
	private String BILL_SVC_GRP ;
	private String ITEM_EXPORT_AMT;
	private String origin_code;
	private String ID_DEPT;
	private String ID_SERVICE;
	private String ACC_CODE;
	private String PRODUCT_CODE;
	private String ITEM_AMT;
	private String JOB_NO;
	private Date BILL_FROM;
	private Date BILL_TO;
	private String ID_CUST_PLAN_LINK;
	private String APPLY_GST;
	private String TAX_CODE;
	private String ITEM_GST;
	private String ITEM_EXPORT_GST;
	private String ITEM_SUBTOTAL;
	private String ACC_CODE1;
	// Add #146 E-EXP-F01_NTTS_CORP Start
	private String CUST_PO;
	private String REFERENCE1;
	private String REFERENCE2;
	// Add #146 E-EXP-F01_NTTS_CORP End
	public String getACC_CODE1() {
		return ACC_CODE1;
	}
	public void setACC_CODE1(String aCC_CODE1) {
		ACC_CODE1 = aCC_CODE1;
	}
	public String getITEM_SUBTOTAL() {
		return ITEM_SUBTOTAL;
	}
	public void setITEM_SUBTOTAL(String iTEM_SUBTOTAL) {
		ITEM_SUBTOTAL = iTEM_SUBTOTAL;
	}
	public String getAPPLY_GST() {
		return APPLY_GST;
	}
	public void setAPPLY_GST(String aPPLY_GST) {
		APPLY_GST = aPPLY_GST;
	}
	public String getTAX_CODE() {
		return TAX_CODE;
	}
	public void setTAX_CODE(String tAX_CODE) {
		TAX_CODE = tAX_CODE;
	}
	public String getITEM_GST() {
		return ITEM_GST;
	}
	public void setITEM_GST(String iTEM_GST) {
		ITEM_GST = iTEM_GST;
	}
	public String getITEM_EXPORT_GST() {
		return ITEM_EXPORT_GST;
	}
	public void setITEM_EXPORT_GST(String iTEM_EXPORT_GST) {
		ITEM_EXPORT_GST = iTEM_EXPORT_GST;
	}
	public String getID_REF() {
		return ID_REF;
	}
	public void setID_REF(String id_ref) {
		ID_REF = id_ref;
	}
	public String getBILL_SVC_GRP() {
		return BILL_SVC_GRP;
	}
	public void setBILL_SVC_GRP(String bill_svc_grp) {
		BILL_SVC_GRP = bill_svc_grp;
	}
    public String getITEM_EXPORT_AMT() {
        return ITEM_EXPORT_AMT;
    }
    public void setITEM_EXPORT_AMT(String iTEM_EXPORT_AMT) {
        ITEM_EXPORT_AMT = iTEM_EXPORT_AMT;
    }
    public String getOrigin_code() {
		return origin_code;
	}
	public void setOrigin_code(String origin_code) {
		this.origin_code = origin_code;
	}
	public String getID_DEPT() {
		return ID_DEPT;
	}
	public void setID_DEPT(String id_dept) {
		ID_DEPT = id_dept;
	}
	public String getID_SERVICE() {
		return ID_SERVICE;
	}
	public void setID_SERVICE(String id_service) {
		ID_SERVICE = id_service;
	}
	public String getACC_CODE() {
		return ACC_CODE;
	}
	public void setACC_CODE(String acc_code) {
		ACC_CODE = acc_code;
	}
	public String getPRODUCT_CODE() {
		return PRODUCT_CODE;
	}
	public void setPRODUCT_CODE(String product_code) {
		PRODUCT_CODE = product_code;
	}
	public void setITEM_AMT(String iTEM_AMT) {
		ITEM_AMT = iTEM_AMT;
	}
	public String getITEM_AMT() {
		return ITEM_AMT;
	}
	public void setJOB_NO(String jOB_NO) {
		JOB_NO = jOB_NO;
	}
	public String getJOB_NO() {
		return JOB_NO;
	}
	public void setBILL_FROM(Date bILL_FROM) {
		BILL_FROM = bILL_FROM;
	}
	public Date getBILL_FROM() {
		return BILL_FROM;
	}
	public void setBILL_TO(Date bILL_TO) {
		BILL_TO = bILL_TO;
	}
	public Date getBILL_TO() {
		return BILL_TO;
	}
	public void setID_CUST_PLAN_LINK(String iD_CUST_PLAN_LINK) {
		ID_CUST_PLAN_LINK = iD_CUST_PLAN_LINK;
	}
	public String getID_CUST_PLAN_LINK() {
		return ID_CUST_PLAN_LINK;
	}
	// Add #146 E-EXP-F01_NTTS_CORP Start
	public String getCUST_PO() {
		return CUST_PO;
	}
	public void setCUST_PO(String cUST_PO) {
		CUST_PO = cUST_PO;
	}
	public String getREFERENCE1() {
		return REFERENCE1;
	}
	public void setREFERENCE1(String rEFERENCE1) {
		REFERENCE1 = rEFERENCE1;
	}
	public String getREFERENCE2() {
		return REFERENCE2;
	}
	public void setREFERENCE2(String rEFERENCE2) {
		REFERENCE2 = rEFERENCE2;
	}
	// Add #146 E-EXP-F01_NTTS_CORP End

}
