package nttdm.bsys.m_ppm.bean;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

import org.apache.struts.action.ActionMapping;

/**
 * M_PPM05 form bean
 * 
 * @author NTTData
 * 
 */
public class M_PPMS05FormBean extends ValidatorActionFormEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1886034392484983086L;

	private String row;
	private String startIndex;
	private String totalCount;
	private String[] idPlanGrp;
	private String idPlan;
	private String noDisplayOTC;
	private String billCurrency;
	private String lblServiceName;
	private String lblServiceDescr;
	private String idPlanGrpList;
	private ArrayList<HashMap<String, Object>> searchResult;

	public String getIdPlanGrpList() {
		return idPlanGrpList;
	}

	public void setIdPlanGrpList(String idPlanGrpList) {
		this.idPlanGrpList = idPlanGrpList;
	}

	public String getBillCurrency() {
		return billCurrency;
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	public String getLblServiceName() {
		return lblServiceName;
	}

	public void setLblServiceName(String lblServiceName) {
		this.lblServiceName = lblServiceName;
	}

	public String getLblServiceDescr() {
		return lblServiceDescr;
	}

	public void setLblServiceDescr(String lblServiceDescr) {
		this.lblServiceDescr = lblServiceDescr;
	}

	public ArrayList<HashMap<String, Object>> getSearchResult() {
		return searchResult;
	}

	public String getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	public void setSearchResult(ArrayList<HashMap<String, Object>> searchResult) {
		this.searchResult = searchResult;
	}

	public String[] getIdPlanGrp() {
		return idPlanGrp;
	}

	public void setIdPlanGrp(String[] idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setIdPlanGrp(new String[] {});
		super.reset(mapping, request);
	}

	public String getNoDisplayOTC() {
    	return noDisplayOTC;
    }

	public void setNoDisplayOTC(String noDisplayOTC) {
    	this.noDisplayOTC = noDisplayOTC;
    }

}
