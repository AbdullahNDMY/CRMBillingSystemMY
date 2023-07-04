package nttdm.bsys.b_cpm.dto;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;
/**
 * @author hungtm
 *
 */
public class B_CPM_FormBean2 extends DynaValidatorActionFormEx {

	private static final long serialVersionUID = 1L;
	//These methods 
	public List<Service> getSubPlans(){
		return (List<Service>)this.get("subPlans");
	}
	public void setSubPlans(List<Service> subPlans){
		this.set("subPlans",subPlans);
	}
	public List<Service> getOptionServices(){
		return (List<Service>)this.get("optionServices");
	}
	public void setOptionsServices(List<Service> optionServices){
		this.set("optionServices",optionServices);
	}
	public void setCustomerList(List<Customer> customerList){
		this.set("customerList", customerList);
	}
	public Plan getPlan() {
		return (Plan)this.get("plan");
	}
	public void setPlan(Plan plan) {
		this.set("plan",plan);
	}
	//1 : user has edited | 0 : user hasn't edited
	public void setIsEdited(String isEdited){
		this.set("isEdited", isEdited);
	}
	public void setDisplayItems(Integer[] displayItems){
		this.set("displayItems", displayItems);
	}
	
	//overriding reset,get,set
	public void reset(ActionMapping mapping, HttpServletRequest request){
		if(request.getAttribute("idCustPlan") != null){
			this.set("idCustPlan", request.getAttribute("idCustPlan"));
		}
		
		setSubPlans(new ArrayList<Service>());
		setOptionsServices(new ArrayList<Service>());
		setCustomerList(new ArrayList<Customer>());
		//plan
		Plan plan = new Plan();
		plan.setExportSingPost("0");
		plan.setIsDisplayDesc("0");
		plan.setAutoRenewal("0");
		plan.setNewAcc("0");
		plan.setPaymentMethod("0");
		plan.setBillingInstruction("0");
		plan.setReferencePlan("");
		setPlan(plan);
		//Is user edited
		setIsEdited("0");
		//display items
		Integer[] displayItems = new Integer[Item.MAX];
		for(int i = 0; i < Item.MAX; i ++)
			displayItems[i] = Item.NON_DISPLAY;
		setDisplayItems(displayItems);
		
		
	}
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		if (errors == null) {
			errors = new ActionErrors();
		}

		return errors;
	}
	

	public Object get(String name,int index){
		if(name.equals("subPlans") || name.equals("optionServices")) {
			List<Object> list = (List<Object>)this.get(name);
			while(list.size() <= index) {
				Service subplan = new Service();
				subplan.setIsApply("0");
				subplan.setQuantity("1");
				list.add(subplan);
			}
			return list.get(index);
		}
		return null;
	}
	
	public void set(String name,int index,Object value){
		if(name.equals("subPlans") || name.equals("optionServices")) {
			List<Object> list = (List<Object>)this.get(name);
			list.set(index, value);
		}
		
	}
}
