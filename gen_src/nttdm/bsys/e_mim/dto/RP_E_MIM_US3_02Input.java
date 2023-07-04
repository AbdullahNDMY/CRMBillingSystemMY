/*
 * @(#)RP_E_MIM_US3_02Input.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US3_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 325295660745875061L;

	/**
     * 
     */
	private String activeStatus;

	/**
     * 
     */
	private String selDay;

	/**
     * 
     */
	private String selHour;

	/**
     * 
     */
	private String selMinute;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
	 * activeStatus を取得する
	 * 
	 * @return activeStatus
	 */
	public String getActiveStatus() {
		return activeStatus;
	}

	/**
	 * activeStatus を設定する
	 * 
	 * @param activeStatus
	 */
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * selDay を取得する
	 * 
	 * @return selDay
	 */
	public String getSelDay() {
		return selDay;
	}

	/**
	 * selDay を設定する
	 * 
	 * @param selDay
	 */
	public void setSelDay(String selDay) {
		this.selDay = selDay;
	}

	/**
	 * selHour を取得する
	 * 
	 * @return selHour
	 */
	public String getSelHour() {
		return selHour;
	}

	/**
	 * selHour を設定する
	 * 
	 * @param selHour
	 */
	public void setSelHour(String selHour) {
		this.selHour = selHour;
	}

	/**
	 * selMinute を取得する
	 * 
	 * @return selMinute
	 */
	public String getSelMinute() {
		return selMinute;
	}

	/**
	 * selMinute を設定する
	 * 
	 * @param selMinute
	 */
	public void setSelMinute(String selMinute) {
		this.selMinute = selMinute;
	}

	/**
	 * uvo を取得する
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo を設定する
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

}