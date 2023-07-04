/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (view/edit)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : object using for define tab control
 * FILE NAME      : TabController.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/08/05 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * InputSearchPlan.class<br>
 * <ul>
 * <li>define tab controller object</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class TabController {
	public static final String DRAFT_PLAN = "draftplan";
	public static final String CANCELLED_PLAN = "cancelledplan";
	public static final String IN_APPROVAL_PLAN = "inappvovalplan";
	public static final String ACTIVE_PLAN = "activeplan";
	public static final String TERMINATED_PLAN = "terminatedplan";
	public static final String SUSPENDED_PLAN = "suspendedplan";
	public static final String REJECTED_PLAN = "rejectedplan";
	
	private String activeTab;
	private int row = 1;
	private int totalCount;
	private int startIndex;
	private List<B_CPM_Tab> tabs;
	private List<String> listStatus;
	private ResourceBundle resources;
	
	private String pageEvent;
	public TabController() {
	    this.initTabs(true);
        this.initListStatus();
        resources = ResourceBundle.getBundle("B_CPM-messages");
	}
	
	public TabController(boolean isBifUsedFlg) {
		this.initTabs(isBifUsedFlg);
		this.initListStatus();
		resources = ResourceBundle.getBundle("B_CPM-messages");
	}

	public TabController(boolean isEmpty, boolean isBifUsedFlg) {
		if (!isEmpty) {
			this.initTabs(isBifUsedFlg);
			this.initListStatus();
		} else {
			this.tabs = new AutoScaleList<B_CPM_Tab>(new B_CPM_Tab());
		}
		resources = ResourceBundle.getBundle("B_CPM-messages");
	}
	
	public static TabController getEmptyTab(boolean isBifUsedFlg) {
		return new TabController(true, isBifUsedFlg);
	}
	
	/**
	 * @return the activeTab
	 */
	public String getActiveTab() {
		return activeTab;
	}

	/**
	 * @param activeTab the activeTab to set
	 */
	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return this.startIndex;
	}

	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the tabs
	 */
	public List<B_CPM_Tab> getTabs() {
		return tabs;
	}

	/**
	 * @param tabs the tabs to set
	 */
	public void setTabs(List<B_CPM_Tab> tabs) {
		this.tabs = tabs;
	}
	
	public void setListStatus(List<String> listStatus) {
		this.listStatus = listStatus;
	}

	public List<String> getListStatus() {
		return listStatus;
	}

	/**
	 * 
	 */
	private void initTabs(boolean isBifUsedFlg) {
		this.tabs = new AutoScaleList<B_CPM_Tab>(new B_CPM_Tab());
		ResourceBundle resources = ResourceBundle.getBundle("B_CPM-messages");
		B_CPM_Tab tab = new B_CPM_Tab();
		tab.setId(DRAFT_PLAN);
		tab.setName(resources.getString("screen.b_cpm.tab.draftPlan"));
		tab.setKey(DRAFT_PLAN);
		this.tabs.add(tab);
		
		tab = new B_CPM_Tab();
        tab.setId(CANCELLED_PLAN);
        tab.setName(resources.getString("screen.b_cpm.tab.cancelledPlan"));
        tab.setKey(CANCELLED_PLAN);
        this.tabs.add(tab);
        
        if (isBifUsedFlg) {
            tab = new B_CPM_Tab();
            tab.setId(IN_APPROVAL_PLAN);
            tab.setName(resources.getString("screen.b_cpm.tab.inApprovalPlan"));
            tab.setKey(IN_APPROVAL_PLAN);
            this.tabs.add(tab);
        }
        
		tab = new B_CPM_Tab();
		tab.setId(ACTIVE_PLAN);
		tab.setName(resources.getString("screen.b_cpm.tab.activedPlan"));
		tab.setKey(ACTIVE_PLAN);
		this.tabs.add(tab);
		
		tab = new B_CPM_Tab();
		tab.setId(TERMINATED_PLAN);
		tab.setName(resources.getString("screen.b_cpm.tab.terminatedPlan"));
		tab.setKey(TERMINATED_PLAN);
		this.tabs.add(tab);
		
		tab = new B_CPM_Tab();
        tab.setId(SUSPENDED_PLAN);
        tab.setName(resources.getString("screen.b_cpm.tab.suspendedPlan"));
        tab.setKey(SUSPENDED_PLAN);
        this.tabs.add(tab);
        
		if (isBifUsedFlg) {
		    tab = new B_CPM_Tab();
	        tab.setId(REJECTED_PLAN);
	        tab.setName(resources.getString("screen.b_cpm.tab.rejectedPlan"));
	        tab.setKey(REJECTED_PLAN);
	        this.tabs.add(tab);
		}
		
//		tab = new B_CPM_Tab();
//		tab.setId(ONE_TIME);
//		tab.setName(resources.getString("screen.b_cpm.tab.oneTimePlan"));
//		tab.setKey(ONE_TIME);
//		this.tabs.add(tab);
	}
	
	/**
	 * 
	 */
	private void initListStatus() {
		this.listStatus = new AutoScaleList<String>(new String());
		if (this.activeTab != null) {
			if(this.activeTab.equals(DRAFT_PLAN)){
				listStatus.add("PS1");
			} else if(this.activeTab.equals(CANCELLED_PLAN)){
			    listStatus.add("PS0");
            } else if(this.activeTab.equals(IN_APPROVAL_PLAN)){
				listStatus.add("PS2");
			} else if(this.activeTab.equals(ACTIVE_PLAN)){
				listStatus.add("PS3");	
			} else if(this.activeTab.equals(TERMINATED_PLAN)){
				listStatus.add("PS7");	
			} else if(this.activeTab.equals(SUSPENDED_PLAN)){
                listStatus.add("PS6");  
			} else if(this.activeTab.equals(REJECTED_PLAN)){
				listStatus.add("PS8");
			}
			this.setListStatus(listStatus);
		}
		
	}
	
	/**
	 * 
	 * @param status
	 */
	public void addTabByStatus(String status) {
		B_CPM_Tab tab = new B_CPM_Tab();
		if (status.equals("PS1")) {
			tab.setId(DRAFT_PLAN);
			tab.setName(resources.getString("screen.b_cpm.tab.draftPlan"));
			tab.setKey(DRAFT_PLAN);
			this.tabs.add(tab);
			this.activeTab = DRAFT_PLAN; 
		} else if (status.equals("PS0")) {
            tab = new B_CPM_Tab();
            tab.setId(CANCELLED_PLAN);
            tab.setName(resources.getString("screen.b_cpm.tab.cancelledPlan"));
            tab.setKey(CANCELLED_PLAN);
            this.tabs.add(tab);
            this.activeTab = CANCELLED_PLAN;
        } else if (status.equals("PS2")) {
            tab = new B_CPM_Tab();
            tab.setId(IN_APPROVAL_PLAN);
            tab.setName(resources.getString("screen.b_cpm.tab.inApprovalPlan"));
            tab.setKey(IN_APPROVAL_PLAN);
			this.tabs.add(tab);
            this.activeTab = IN_APPROVAL_PLAN;
        } else if (status.equals("PS3")) {
			tab = new B_CPM_Tab();
			tab.setId(ACTIVE_PLAN);
			tab.setName(resources.getString("screen.b_cpm.tab.activedPlan"));
			tab.setKey(ACTIVE_PLAN);
			this.tabs.add(tab);
			this.activeTab = ACTIVE_PLAN;
		} else if (status.equals("PS7")) {
			tab = new B_CPM_Tab();
			tab.setId(TERMINATED_PLAN);
			tab.setName(resources.getString("screen.b_cpm.tab.terminatedPlan"));
			tab.setKey(TERMINATED_PLAN);
			this.tabs.add(tab);
			this.activeTab = TERMINATED_PLAN;
		} else if (status.equals("PS6")) {
            tab = new B_CPM_Tab();
            tab.setId(SUSPENDED_PLAN);
            tab.setName(resources.getString("screen.b_cpm.tab.suspendedPlan"));
            tab.setKey(SUSPENDED_PLAN);
            this.tabs.add(tab);
            this.activeTab = SUSPENDED_PLAN;
		} else if (status.equals("PS8")) {
			tab = new B_CPM_Tab();
			tab.setId(REJECTED_PLAN);
			tab.setName(resources.getString("screen.b_cpm.tab.rejectedPlan"));
			tab.setKey(REJECTED_PLAN);
			this.tabs.add(tab);
			this.activeTab = REJECTED_PLAN;
		}
	}
	
	/**
	 * 
	 * @param planStatus
	 * @return
	 */
	public static String getActiveTab(String planStatus) {
		String tabId = "";
		if (planStatus.equals("PS1")) {
			tabId = DRAFT_PLAN;
		} else if (planStatus.equals("PS0")) {
            tabId = CANCELLED_PLAN;
        } else if (planStatus.equals("PS2")) {
            tabId = IN_APPROVAL_PLAN;
        } else if (planStatus.equals("PS3")) {
			tabId = ACTIVE_PLAN;
		} else if (planStatus.equals("PS7")) {
			tabId = TERMINATED_PLAN;
		} else if (planStatus.equals("PS6")) {
            tabId = SUSPENDED_PLAN;
		} else if (planStatus.equals("PS8")) {
			tabId = REJECTED_PLAN;
		}
		return tabId;
	}
	
	/**
	 * 
	 * @param tabId
	 * @return
	 */
	public static Object[] getStatusByTabId(String tabId) {
		List<String> list = new ArrayList<String>();
		list = new AutoScaleList<String>(new String());
		if(tabId.equals(DRAFT_PLAN)){
			list.add("PS1");
		} else if(tabId.equals(CANCELLED_PLAN)){
		    list.add("PS0");
        } else if(tabId.equals(IN_APPROVAL_PLAN)){
			list.add("PS2");
		} else if(tabId.equals(ACTIVE_PLAN)){
			list.add("PS3");		
		} else if(tabId.equals(TERMINATED_PLAN)){
			list.add("PS7");	
		} else if(tabId.equals(SUSPENDED_PLAN)){
            list.add("PS6");
		} else if(tabId.equals(REJECTED_PLAN)){
			list.add("PS8");
		}
		return list.toArray();
	}
	/**
	 * 
	 * @return
	 */
	public B_CPM_Tab seletedTab() {
		for (B_CPM_Tab tab : this.tabs) {
			if (tab.getId().equals(this.getActiveTab())) {
				return tab;
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	public void synchronizeTabinfo() {
		//synchronize tab
		for (B_CPM_Tab tab : this.tabs) {
			tab.synchronizeTabinfo();
		}
		
		//synchronize tab controller
		if (this.seletedTab() != null) {
			this.totalCount = this.seletedTab().getTotalCount();
			this.startIndex = this.seletedTab().getStartIndex();
		} 
		this.initListStatus();
	}

	/**
	 * 
	 * @return
	 */
	public String getSelectedCustomerPlan() {
		String customerPlanId = "";
		for (B_CPM_Tab tab : this.tabs) {
			customerPlanId = tab.getCurrentCustomerPlanId();
		}
		return customerPlanId;
	}

	/**
	 * @param pageEvent the pageEvent to set
	 */
	public void setPageEvent(String pageEvent) {
		this.pageEvent = pageEvent;
	}

	/**
	 * @return the pageEvent
	 */
	public String getPageEvent() {
		return pageEvent;
	}
}
