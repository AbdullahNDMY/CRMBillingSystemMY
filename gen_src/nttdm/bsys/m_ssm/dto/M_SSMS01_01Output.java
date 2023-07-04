/*
 * @(#)M_SSMS01_01Output.java
 *
 *
 */
package nttdm.bsys.m_ssm.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.m_ssm.bean.ServiceObjectBean;

/**
 * OutputDTO class.
 * 
 * @author khaln
 */
public class M_SSMS01_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 654529168236402512L;

	/**
     * 
     */
	private List listServiceGroup;

	/**
     * 
     */
	private List listResult;

	/**
     * 
     */
	private List listServiceType;
	
	/**
     * 
     */
	private List errorMessages;

	/**
     * 
     */
	private List successMessages;

	private List listSsmRows;
	
	private List listServiceBasicInfo;

	public List getListServiceBasicInfo() {
		return listServiceBasicInfo;
	}

	public void setListServiceBasicInfo(List listServiceBasicInfo) {
		this.listServiceBasicInfo = listServiceBasicInfo;
	}

	public List getListSsmRows() {
		return listSsmRows;
	}

	public void setListSsmRows(List listSsmRows) {
		this.listSsmRows = listSsmRows;
	}
	
	/**
	 * listServiceGroup を取得する
	 * 
	 * @return listServiceGroup
	 */
	public List getListServiceGroup() {
		return listServiceGroup;
	}

	/**
	 * listServiceGroup を設定する
	 * 
	 * @param listServiceGroup
	 */
	public void setListServiceGroup(List listServiceGroup) {
		this.listServiceGroup = listServiceGroup;
	}

	/**
	 * listResult を取得する
	 * 
	 * @return listResult
	 */
	public List getListResult() {
		return listResult;
	}

	/**
	 * listResult を設定する
	 * 
	 * @param listResult
	 */
	public void setListResult(List listResult) {
		this.listResult = listResult;
	}

	/**
	 * listServiceType を取得する
	 * 
	 * @return listServiceType
	 */
	public List getListServiceType() {
		return listServiceType;
	}

	/**
	 * listServiceType を設定する
	 * 
	 * @param listServiceType
	 */
	public void setListServiceType(List listServiceType) {
		this.listServiceType = listServiceType;
	}


	/**
	 * errorMessages を取得する
	 * 
	 * @return errorMessages
	 */
	public List getErrorMessages() {
		return errorMessages;
	}

	/**
	 * errorMessages を設定する
	 * 
	 * @param errorMessages
	 */
	public void setErrorMessages(List errorMessages) {
		this.errorMessages = errorMessages;
	}

	/**
	 * successMessages を取得する
	 * 
	 * @return successMessages
	 */
	public List getSuccessMessages() {
		return successMessages;
	}

	/**
	 * successMessages を設定する
	 * 
	 * @param successMessages
	 */
	public void setSuccessMessages(List successMessages) {
		this.successMessages = successMessages;
	}

}