/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : object using for define tab info
 * FILE NAME      : B_CPM_Tab.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/08/05 [Duong Nguyen] Created
 * 2011/10/03 [Duong Nguyen] Fix bug #2688
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

import java.util.List;

/**
 * B_CPM_Tab.class<br>
 * <ul>
 * <li>define tab display in B-CPM</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_Tab {
	private String id;
	private String name;
	private String key;
	private int startIndex;
	private int totalCount;
	private Object[] status;
	private List<CustomerPlan> customerPlanList;
	private String idCust;
	
	public B_CPM_Tab() {
		this.startIndex = 0;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Object[] status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public Object[] getStatus() {
		return status;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param customerPlanList the customerPlanList to set
	 */
	public void setCustomerPlanList(List<CustomerPlan> customerPlanList) {
		this.customerPlanList = customerPlanList;
	}

	/**
	 * @return the customerPlanList
	 */
	public List<CustomerPlan> getCustomerPlanList() {
		return customerPlanList;
	}
	
	/**
	 * 
	 */
	public void synchronizeTabinfo() {
		this.totalCount = customerPlanList.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentCustomerPlanId() {
		if (this.startIndex >= customerPlanList.size()) {
			this.startIndex = customerPlanList.size() - 1;
		} else if (this.startIndex == -1) {
			this.startIndex = 0;
		}
		
		if (this.startIndex == -1) {
			return "";
		}
		return customerPlanList.get(this.startIndex).getIdCustPlan();
	}
	
	/**
	 * 
	 * @param idCustPlan
	 */
	public void setCurrentCustomerPlanId(String idCustPlan) {
		for (int i = 0; i < this.customerPlanList.size(); i++) {
			CustomerPlan customerPlan = this.customerPlanList.get(i);
			if (customerPlan.getIdCustPlan().equals(idCustPlan)) {
				this.startIndex = i;
				break;
			} else {
				this.startIndex = -1;
			}
		}
	}

	/**
	 * @param idCust the idCust to set
	 */
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	/**
	 * @return the idCust
	 */
	public String getIdCust() {
		return idCust;
	}	
}
