/**
 * 
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author duongnld
 *
 */
public class B_BIF_Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6516731011706739046L;
	private String idCust;
	private String idCustPlan;
	private String bifType;
	private String idRef;
	private String exportCurrency;
	private String action;
	private String message;
	private String messageParameter;
	private Map<String, Object> screenObject;
	private Map<String, Object> wfInfo = null;
	private Map<String, Object> pagAction;
	private String errorFlg;
	private String fileName;
	private String maxFileSize;

	/**
	 * @return the idCust
	 */
	public String getIdCust() {
		return idCust;
	}
	/**
	 * @param idCust the idCust to set
	 */
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}
	/**
	 * @return the idCustPlan
	 */
	public String getIdCustPlan() {
		return idCustPlan;
	}
	/**
	 * @param idCustPlan the idCustPlan to set
	 */
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	/**
	 * @return the bifType
	 */
	public String getBifType() {
		return bifType;
	}
	/**
	 * @param bifType the bifType to set
	 */
	public void setBifType(String bifType) {
		this.bifType = bifType;
	}
	/**
	 * @return the idRef
	 */
	public String getIdRef() {
		return idRef;
	}
	/**
	 * @param idRef the idRef to set
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}
	/**
	 * @return the exportCurrency
	 */
	public String getExportCurrency() {
		return exportCurrency;
	}
	/**
	 * @param exportCurrency the exportCurrency to set
	 */
	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}
	/**
	 * @return the rateCurrency
	 */
	public String getRateCurrency() {
		return rateCurrency;
	}
	/**
	 * @param rateCurrency the rateCurrency to set
	 */
	public void setRateCurrency(String rateCurrency) {
		this.rateCurrency = rateCurrency;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param screenObject the screenObject to set
	 */
	public void setScreenObject(Map<String, Object> screenObject) {
		this.screenObject = screenObject;
	}
	/**
	 * @return the screenObject
	 */
	public Map<String, Object> getScreenObject() {
		return screenObject;
	}
	/**
	 * @param wfInfo the wfInfo to set
	 */
	public void setWfInfo(Map<String, Object> wfInfo) {
		this.wfInfo = wfInfo;
	}
	/**
	 * @return the wfInfo
	 */
	public Map<String, Object> getWfInfo() {
		return wfInfo;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param messageParameter the messageParameter to set
	 */
	public void setMessageParameter(String messageParameter) {
		this.messageParameter = messageParameter;
	}
	/**
	 * @return the messageParameter
	 */
	public String getMessageParameter() {
		return messageParameter;
	}
	String rateCurrency;
	public Map<String, Object> getPagAction() {
		return pagAction;
	}
	public void setPagAction(Map<String, Object> pagAction) {
		this.pagAction = pagAction;
	}
	/**
	 * @return the errorFlg
	 */
	public String getErrorFlg() {
		return errorFlg;
	}
	/**
	 * @param errorFlg the errorFlg to set
	 */
	public void setErrorFlg(String errorFlg) {
		this.errorFlg = errorFlg;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the maxFileSize
	 */
	public String getMaxFileSize() {
		return maxFileSize;
	}
	/**
	 * @param maxFileSize the maxFileSize to set
	 */
	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}
}
