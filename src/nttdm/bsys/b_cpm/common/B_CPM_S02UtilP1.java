package nttdm.bsys.b_cpm.common;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts.Globals;

import jp.terasoluna.fw.dao.*;
import jp.terasoluna.fw.service.thin.*;
import jp.terasoluna.fw.util.PropertyUtil;

import nttdm.bsys.b_cpm.dto.*;


public class B_CPM_S02UtilP1 {

	/**
	 * Follow process CPM_S02 New/Edit - Exp. Grand Total
	 * in B_CPM_r6.0.xls
	 * @return
	 */
	public List<BillAccNo> loadGrandTotals(QueryDAO query, String billCurrency){
		List<BillAccNo> list = new ArrayList<BillAccNo>();
		// get defCurrency
		String defCurrency = query.executeForObject("SELECT.B_CPM.DEF_CURRENCY", null, String.class);
		if (defCurrency != null && defCurrency.equals(billCurrency)){
			list = query.executeForObjectList("SELECT.B_CPM.EXP_GRAND_TOTAL", defCurrency);
		}else{
			BillAccNo accNo = new BillAccNo();
			accNo.setCurCode(defCurrency);
			list.add(accNo);
		}
		return list;
	}
	/**
	 * Process Flow: B-CPM-S02: Billing Account No.
	 * @param obj: outputDTO
	 * @param query
	 * @param billCurrency
	 * @param idCustPlan
	 * @param isStandard
	 */
	public void loadBillAccNo(Object outputDTO, QueryDAO queryDAO, String idCustPlan, Boolean isStandard){
		Plan plan = new Plan();
		// displayItems: 0: Bill Account No
		// meaning: 0: non-display; 1: display
		Integer[] displayItems = new Integer[30];
		try {
			// get plan from outputDTO
			Method getPlan = outputDTO.getClass().getMethod("getPlan");
			plan = (Plan) getPlan.invoke(outputDTO);
			// get displayItems from outputDTO
			Method getDisplayItems = outputDTO.getClass().getMethod("getDisplayItems");
			displayItems = (Integer[]) getDisplayItems.invoke(outputDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// check if existed ID_CUST_PLAN in T_BAC_D
		T_BAC_D accNo;
		if (idCustPlan == null) accNo = null;
		accNo= queryDAO.executeForObject("SELECT.B_CPM.EXISTED_ID_CUST_PLAN", idCustPlan, String.class);
		if (accNo != null && !accNo.getIdBillAccount().equals("")){			 
			plan.setBillAccNo(accNo.getIdBillAccount());
			displayItems[3] = 0;
		}else{
			// load Billing No List
			List<BillAccNo> billAccList;
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("idCust", plan.getIdCust());
			m.put("billCurrency", plan.getBillCurrency());
			if (isStandard){
				billAccList = queryDAO.executeForObjectList("SELECT.B_CPM.BILL_ACC_NO_STANDARD", m);
			}else{
				billAccList = queryDAO.executeForObjectList("SELECT.B_CPM.BILL_ACC_NO_NONSTANDARD", m);
			}
			if (billAccList == null || billAccList.size()==0){
				// newACC : Readonly
				displayItems[3] = 2;
				plan.setNewAcc("1");
				
			}else{
				try {
					// newAcc = 1: newAcc unchecked
					plan.setNewAcc("0");
					// get 1st BillingAccNo
					BillAccNo billAccNo = billAccList.get(0);
					// update PaymentMethod = O.Payment_method, export currency, fixed forxed, bill currency
					plan.setPaymentMethod(billAccNo.getPaymentMethod());
					displayItems[13] = 2;
					plan.setExpGrandTotal(billAccNo.getExportCurrency());
					displayItems[15] = 2;
					plan.setFixedForex(billAccNo.getFixedForex());
					displayItems[16] = 2;
					if (!isStandard) {
						plan.setBillCurrency(billAccNo.getBillCurrency());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public List<Service> arrangeSubPlan(List<Service> list){
		List<Service> planDetails = new ArrayList<Service>();
		List<Service> arrangeList = new ArrayList<Service>();
		Service curPlan = new Service();
		Service prevPlan = new Service();
		int index = 0;
		if (list != null && list.size() > 0){
			for(int i=0; i<list.size(); i++){
				curPlan = list.get(i);
				if (i>0){
					prevPlan = list.get(i-1);
					if (prevPlan.getIdPlanGroup().equals(curPlan.getIdPlanGroup())){
						planDetails.add(curPlan);
						if (i== (list.size() -1)){
							prevPlan.setSubPlanDetails(planDetails);
							arrangeList.add(prevPlan);
						}
					}else{
						prevPlan.setSubPlanDetails(planDetails);
						arrangeList.add(prevPlan);
						planDetails = new ArrayList<Service>();
						planDetails.add(curPlan);
						if (i== (list.size() -1)){
							curPlan.setSubPlanDetails(planDetails);
							arrangeList.add(curPlan);
						}
					}
				}else{
					planDetails.add(curPlan);
					if (list.size() == 1){
						curPlan.setSubPlanDetails(planDetails);
						arrangeList.add(curPlan);
					}
					index = index +1;
				}
			}
		}else{
//			curPlan.setSubPlanDetails(list);
//			arrangeList.add(curPlan);
			return null;
		}
		for (int i = 0; i < arrangeList.size(); i++) {
			curPlan = arrangeList.get(i);
			if (curPlan.getSubPlanDetails() != null && curPlan.getSubPlanDetails().size() > 0)
				curPlan.setSubPlanDetailCount(curPlan.getSubPlanDetails().size());
			else
				curPlan.setSubPlanDetailCount(0);
				
		}
		return arrangeList;
	}
	
	public String getToday(){
		Calendar car = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String today = format.format(car.getTime());
		return today;
	}
	public String getStatus(String status){
		/* M.PLAN_STATUS as below:
			PS1: Draft
			PS2: In-Approval
			PS3: Active
			PS4: Suspended to first payment
			PS5: Suspended for late payment
			PS6: Suspended manually
			PS7: Closed
			PS8: Rejected
			(If no entry in db, then default is Draft)
		*/
		if (status!= null){
			if (status.equals("PS1")) return "Draft";
			else if (status.equals("PS1")) return "Draft";
			else if (status.equals("PS2")) return "In-Approval";
			else if (status.equals("PS3")) return "Active";
			else if (status.equals("PS4")) return "Suspended to first payment";
			else if (status.equals("PS5")) return "Suspended for late payment";
			else if (status.equals("PS6")) return "Suspended manually";
			else if (status.equals("PS7")) return "Closed";
			else if (status.equals("PS8")) return "Rejected";
		}
		return "Draft";
	}
	
	public String[] processCheckBoxArray(String[] standard, String[] needCheck){
		
		String[] result = new String[standard.length];
		for(int j=0;j<result.length;j++)
		{
			result[j]="0";
		}		
		
		for (int i = 0; i < result.length; i++) {
			
			if ((needCheck != null) && (i < needCheck.length)){
				result[Integer.valueOf(needCheck[i])-1] = "1"; 
			} else result[i] = "0";
		}
		return result;
	}
	
	public boolean isRightPwdPolicy(String pwd){
		if (pwd.length() <6) return false;
		boolean isDigit = false;
		boolean isChar = false;
		for (int i = 0; i < pwd.length(); i++) {
            //if pwd contain number?
            if (!isDigit && Character.isDigit(pwd.charAt(i))){
            	isDigit = true;
            }
            //if pwd contain character
            if (!isChar && Character.isLetter(pwd.charAt(i))){
            	isChar = true;
            }
            // pwd contain both digit and character
            if (isChar && isDigit) {
            	return true;
            }
        }		
		return false;
	}	
	
	public boolean planBatchMappingChecking(QueryDAO queryDao, String idPlan, String[] idPlanGroups, BLogicMessages errMsgs){
		// input param is idPlan and list of applied sub plan and option service
		//select all from M_PLAN_BATCH_MAPPING
		boolean checkingFail = false;
		B_CPMSqlInputDto dto = new B_CPMSqlInputDto();
		dto.setIdPlan(idPlan);
		dto.setIdPlanGroups(idPlanGroups);
		List<PlanBatchMapping> planBatchList = queryDao.executeForObjectList("SELECT.CPM_S02.M_PLAN_BATCH_MAPPING", dto);
		// count idPlanGrp where itemType = S
		dto.setItemType("S");
		Integer sCount = queryDao.executeForObject("SELECT.CPM_S02.M_PLAN_BATCH_MAPPING_COUNT", dto, Integer.class);
		// count idPlanGrp where itemType = O		
		dto.setItemType("O");
		Integer oCount = queryDao.executeForObject("SELECT.CPM_S02.M_PLAN_BATCH_MAPPING_COUNT", dto, Integer.class);
		// count idPlanGrp where itemType = O and codeValue = 1		
		dto.setCodeValue("1");
		Integer oCount1 = queryDao.executeForObject("SELECT.CPM_S02.M_PLAN_BATCH_MAPPING_COUNT", dto, Integer.class);		
		// count idPlanGrp where itemType = O and codeValue = 2
		dto.setCodeValue("2");
		Integer oCount2 = queryDao.executeForObject("SELECT.CPM_S02.M_PLAN_BATCH_MAPPING_COUNT", dto, Integer.class);		
		// count idPlanGrp where itemType = O and codeValue = 3
		dto.setCodeValue("3");
		Integer oCount3 = queryDao.executeForObject("SELECT.CPM_S02.M_PLAN_BATCH_MAPPING_COUNT", dto, Integer.class);		
		String item = "";
		if (planBatchList != null){
			for (int i = 0; i < planBatchList.size(); i++) {
				PlanBatchMapping mapping = (PlanBatchMapping) planBatchList.get(i);
				if (mapping.getBatchId().equals("CC") ){
					item = PropertyUtil.getProperty("errors.ERR1SC043.item1");
				} else if (mapping.getBatchId().equals("DU") ){
					item = PropertyUtil.getProperty("errors.ERR1SC043.item2");
				} else if (mapping.getBatchId().equals("AD") ){
					item = PropertyUtil.getProperty("errors.ERR1SC043.item3");
				}else{
					item = PropertyUtil.getProperty("errors.ERR1SC043.item4");
				}
				if (sCount >1){
					errMsgs.add(Globals.ERROR_KEY,new BLogicMessage("errors.ERR1SC043", item));
					checkingFail = true;
				}else {
					if (mapping.getBatchId().equals("DU") || mapping.getBatchId().equals("AD")){
						if (oCount > 1){
							errMsgs.add(Globals.ERROR_KEY,new BLogicMessage("errors.ERR1SC044", item));
							checkingFail = true;
						}
					}else{
						if (mapping.getBatchId().equals("IP")){
							if (oCount1 > 1 || oCount2 > 1 || oCount3 > 1){
								errMsgs.add(Globals.ERROR_KEY,new BLogicMessage("errors.ERR1SC045", item));
								checkingFail = true;
							}
						}
					}
				}
			}
				
		}
		return checkingFail;
		
	}
	
	public Integer[] displaySuspendButtons(QueryDAO queryDAO, String mode, String userId, String status){
		// displayButtons: 0: suspend; 1: Unsuspend; 2: Terminate; 3: Delete
		// 
		String accessType = queryDAO.executeForObject("SELECT.B_CPM.GET_ACCESS_TYPE", userId, String.class);
		Integer[] displayButtons = {0,0,0,0};
		if (mode == null || mode.equals("NEWMODE")){
			return displayButtons;
		}else{
			if (accessType.equals("2")){
				//  suspend button: Only display in Edit Mode with condition: M.PLAN_STATUS = "PS3" and B.ACCESS_TYPE = 2
				if (status.equals("PS3")  ){
					displayButtons[0] = 1;
				}
				// Unsuspend button: Only display and enable with condition:M.PLAN_STATUS = "PS6" and B.ACCESS_TYPE = 2
				//(Location of button is replace Suspend button)
				if (status.equals("PS6")){
					displayButtons[1] = 1;
				}
				// terminate button: Do not display in New Mode. Only display in Edit Mode with condition:
//				M.PLAN_STATUS = "PS3" or "PS6"  and B.ACCESS_TYPE = 2
				if ((status.equals("PS6") || status.equals("PS6"))){
					displayButtons[2] = 1;
				}
				// Delete button: Display only in Edit Mode and B.ACCESS_TYPE = 2 and when M.PLAN_STATUS = "PS1"
				if ((status.equals("PS1"))){
					displayButtons[3] = 1;
				}			
			}
			return displayButtons;
		}

	}
	
	public void loadReferenceList(Object output, QueryDAO queryDAO){
		List<BillAccNo> referencePlans = queryDAO.executeForObjectList("SELECT.B_CPM.REFERENCE_PLAN", null);
		if (referencePlans != null && referencePlans.size() == 0) referencePlans = null;
		try {
			// get plan from outputDTO
			Method setReferencePlans = output.getClass().getMethod("setReferencePlans",
					List.class);
			setReferencePlans.invoke(output, new Object[] { referencePlans });
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadSubPlan(){
		
	}
	
	/**
	 * 
	 * @param idCustomer
	 * @param queryDAO
	 * @param output
	 */
	public void getCustomerInfo(String idCustomer, QueryDAO queryDAO, Object output){
		Customer cust = new Customer();
		cust = queryDAO.executeForObject("SELECT.B_CPM.CUSTOMER_INFO", idCustomer, Customer.class);
		try {
			// set Customer to outputDTO
			Method setCustomer = output.getClass().getMethod("setCustomer",
					Customer.class);
			setCustomer.invoke(output, cust);
			// set idCustomer to outputDTO
			Method setIdCustomer = output.getClass().getMethod("setIdCustomer",
					String.class);
			setIdCustomer.invoke(output,idCustomer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * get BillCurrency, PlanName and PlanDesc from M_Plan_H C
	 * use for newPlan
	 * @param idPlan
	 * @param queryDAO
	 * @param output
	 */
	public void getPlanInfo(String idPlan, QueryDAO queryDAO, Object output ){
		Map m = new HashMap<String, Object>();
		m = new HashMap<String, Object>();
		m.put("planId", idPlan);
		Plan plan = new Plan();
		plan = queryDAO.executeForObject("SELECT.B_CPM.PLAN_INFO_BY_IDPLAN",m , Plan.class);
		// set Plan to outputDTO
		try {
			Method setPlan = output.getClass().getMethod("setPlan",
					Plan.class);
			setPlan.invoke(output, plan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void getExistedPlan(String idCustPlan,  QueryDAO queryDAO, Object output ){
		Plan plan = new Plan();
		plan = queryDAO.executeForObject("SELECT.B_CPM.T_CUST_PLAN_D",idCustPlan , Plan.class);
		if(plan != null)
			plan.setBillSrvGrpName((String) queryDAO.executeForObject("SELECT.B_CPM.GET_BILL_SVC_GRP", plan.getBillSrvGrp(), String.class)); 
		// set Plan to outputDTO
		try {
			Method setPlan = output.getClass().getMethod("setPlan",
					Plan.class);
			setPlan.invoke(output, plan);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void setOutput(Object output, Object input, QueryDAO queryDAO, HashMap parameters ){
		// set customer
		// set Plan
		// set displayButtons
		// set displayItems
		// set subPlan
		// set optionService
	}
	
	/**
	 * Init data to display Items and buttons on screen
	 * @param outputDTO
	 */
	public void initData(Object outputDTO){
		// meaning
//		0: non-display
//		1: display with enable
//		2: display with disable
		// items
//		0	Transaction Type
//		1	Subscription ID
//		2	Billing Acc. No.
//		3	New Acc.
//		4	Application Date
//		5	Bill. Service Grp
//		6	Subscription Information
//		7	Service Start From
//		8	Service Start To
//		9	Auto Renewal
//		10	Billing Instruction
//		11	Pro rate based on
//		12	Billing Currency
//		13	Payment Method
//		14	Export SingPost
//		15	Exp. Grand Total
//		16	Fixed Forex
//		17	<Auto Display Status>
//		18	Reference Plan
//		19	Plan Name
//		20	Lum Sum Amount and Descriptions
//		21	(LumpSumTextBox)
//		22	(LumpSumCheckBox)
//		23	Quantity
//		24	Unit Price 
//		25	Total Amount
		Integer[] displayItems = new Integer[30];
		for (int i = 0; i < 30; i++) {
			displayItems[i] = 1;
		}
		
//		0	Suspend
//		1	Unsuspend
//		2	Terminate
//		3	Edit
//		4	Add Sub-Plan/ Option Service
//		5	Save
//		6	Exit
//		7	Delete

		Integer[] displayButtons = new Integer[10];
		for (int i = 0; i < 10; i++) {
			displayButtons[i] = 1;
		}
		
		try {
			Method setDisplayItems = outputDTO.getClass().getMethod("setDisplayItems",
					Integer[].class);
			setDisplayItems.invoke(outputDTO, new Object[] { displayItems });
			
			Method setDisplayButtons = outputDTO.getClass().getMethod("setDisplayButtons",
					Integer[].class);
			setDisplayButtons.invoke(outputDTO, new Object[] { displayButtons });
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void loadExistedSubPlan(Object outputDTO, Object inputDTO,
			QueryDAO queryDAO, String idCustPlan, String[] idPlanGroup) {
		List<Service> subPlans = new ArrayList<Service>();
		List<Service> optionServices = new ArrayList<Service>();
		Map m = new HashMap<String, Object>();
		String[] idPlanGroups = new String[100];
		String[] inputIdPlanGroups = null;
		if (idCustPlan != null) {
			// get idPlanGroups of existed subPlan
			idPlanGroups = queryDAO.executeForObjectArray(
					"SELECT.CPM.GET_ID_PLAN_GROUP", idCustPlan, String.class);
			if (idPlanGroups != null && idPlanGroup != null
					&& idPlanGroup.length > 0) {
				// concat existed idPlanGroup and new idPlanGroup
				inputIdPlanGroups = new String[idPlanGroups.length
						+ idPlanGroup.length];
				for (int i = 0; i < idPlanGroups.length; i++) {
					inputIdPlanGroups[i] = idPlanGroups[i];
				}
				int index = 0;
				for (int i = idPlanGroups.length; i < inputIdPlanGroups.length; i++) {
					inputIdPlanGroups[i] = idPlanGroup[index];
					index++;
				}
			} else {
				if (idPlanGroup != null)
					inputIdPlanGroups = idPlanGroup;
				if (idPlanGroups != null)
					inputIdPlanGroups = idPlanGroups;
			}
		} else {
			inputIdPlanGroups = idPlanGroup;
		}
		// get subPlan by idPlanGroup
		if (inputIdPlanGroups != null && inputIdPlanGroups.length>0) {
			B_CPMSqlInputDto input = new B_CPMSqlInputDto();
			input.setIdPlanGroups(inputIdPlanGroups);
			input.setItemType("S");
			subPlans = queryDAO.executeForObjectList(
					"SELECT.CPM_COMMON.GET_SUB_PLANS", input);
			// get quantity, price and total amount for sub plan
			if (subPlans != null && idCustPlan != null) {
				for (int i = 0; i < subPlans.size(); i++) {
					Service price = new Service();
					Service subPlan = subPlans.get(i);
					m = new HashMap<String, Object>();
					m.put("idCustPlan", idCustPlan);
					m.put("idPlanGrp", subPlan.getIdPlanGroup());
					price = queryDAO
							.executeForObject(
									"SELECT.B_CPM_S02.SUB_PLAN_PRICE", m,
									Service.class);
					if (price != null)
						subPlan.setPriceQuantity(price);
				}
			}
			// get option services
			input.setItemType("O");
			optionServices = queryDAO.executeForObjectList(
					"SELECT.CPM_COMMON.GET_SUB_PLANS", input);
			// get quantity, price and total amount for option service
			if (optionServices != null && idCustPlan != null) {
				for (int i = 0; i < optionServices.size(); i++) {
					Service price = new Service();
					Service subPlan = optionServices.get(i);
					m = new HashMap<String, Object>();
					m.put("idCustPlan", idCustPlan);
					m.put("idPlanGrp", subPlan.getIdPlanGroup());
					price = queryDAO
							.executeForObject(
									"SELECT.B_CPM_S02.SUB_PLAN_PRICE", m,
									Service.class);
					if (price != null)
						subPlan.setPriceQuantity(price);
				}
			}
			// set to output
			if (subPlans != null && subPlans.size()==0) subPlans = null;
			if (optionServices != null && optionServices.size()==0) optionServices = null;
			
			try {
				Method setSubPlans = outputDTO.getClass().getMethod(
						"setSubPlans", List.class);
				setSubPlans.invoke(outputDTO,
						new Object[] { arrangeSubPlan(subPlans) });

				Method setOptionServices = outputDTO.getClass().getMethod(
						"setOptionServices", List.class);
				setOptionServices.invoke(outputDTO,
						new Object[] { optionServices });
				Method getPlan = outputDTO.getClass().getMethod("getPlan");
				Plan plan = (Plan) getPlan.invoke(outputDTO);
				plan.setSubPlans(subPlans);
				plan.setOptionServices(optionServices);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void loadExistedSubPlan(Object outputDTO,
			QueryDAO queryDAO, String idCustPlan, String[] idPlanGroup, String type) {
		List<Service> subPlans = new ArrayList<Service>();
		List<Service> optionServices = new ArrayList<Service>();
		Map m = new HashMap<String, Object>();
		String[] idPlanGroups = new String[100];
		String[] inputIdPlanGroups = null;
		if (idCustPlan != null) {
			// get idPlanGroups of existed subPlan
			idPlanGroups = queryDAO.executeForObjectArray(
					"SELECT.CPM.GET_ID_PLAN_GROUP", idCustPlan, String.class);
			if (idPlanGroups != null && idPlanGroup != null
					&& idPlanGroup.length > 0) {
				// concat existed idPlanGroup and new idPlanGroup
				inputIdPlanGroups = new String[idPlanGroups.length
						+ idPlanGroup.length];
				for (int i = 0; i < idPlanGroups.length; i++) {
					inputIdPlanGroups[i] = idPlanGroups[i];
				}
				int index = 0;
				for (int i = idPlanGroups.length; i < inputIdPlanGroups.length; i++) {
					inputIdPlanGroups[i] = idPlanGroup[index];
					index++;
				}
			} else {
				if (idPlanGroup != null)
					inputIdPlanGroups = idPlanGroup;
				if (idPlanGroups != null)
					inputIdPlanGroups = idPlanGroups;
			}
		} else {
			inputIdPlanGroups = idPlanGroup;
		}
		// get subPlan by idPlanGroup
		if (inputIdPlanGroups != null && inputIdPlanGroups.length>0) {
			B_CPMSqlInputDto input = new B_CPMSqlInputDto();
			input.setIdPlanGroups(inputIdPlanGroups);
			input.setItemType("S");
			subPlans = queryDAO.executeForObjectList(
					"SELECT.CPM_COMMON.GET_SUB_PLANS", input);
			// get quantity, price and total amount for sub plan
			if (subPlans != null && idCustPlan != null) {
				for (int i = 0; i < subPlans.size(); i++) {
					Service price = new Service();
					Service subPlan = subPlans.get(i);
					m = new HashMap<String, Object>();
					m.put("idCustPlan", idCustPlan);
					m.put("idPlanGrp", subPlan.getIdPlanGroup());
					price = queryDAO
							.executeForObject(
									"SELECT.B_CPM_S02.SUB_PLAN_PRICE", m,
									Service.class);
					if (price != null)
						subPlan.setPriceQuantity(price);
				}
			}
			// get option services
			input.setItemType("O");
			optionServices = queryDAO.executeForObjectList(
					"SELECT.CPM_COMMON.GET_SUB_PLANS", input);
			// get quantity, price and total amount for option service
			if (optionServices != null && idCustPlan != null) {
				for (int i = 0; i < optionServices.size(); i++) {
					Service price = new Service();
					Service subPlan = optionServices.get(i);
					m = new HashMap<String, Object>();
					m.put("idCustPlan", idCustPlan);
					m.put("idPlanGrp", subPlan.getIdPlanGroup());
					price = queryDAO
							.executeForObject(
									"SELECT.B_CPM_S02.SUB_PLAN_PRICE", m,
									Service.class);
					if (price != null)
						subPlan.setPriceQuantity(price);
				}
			}
			// set to output
			if (subPlans != null && subPlans.size()==0) subPlans = null;
			if (optionServices != null && optionServices.size()==0) optionServices = null;
			
			try {
				if (type.equals("new")){
					Method setNewSubPlans = outputDTO.getClass().getMethod(
							"setNewSubPlans", List.class);
					setNewSubPlans.invoke(outputDTO, 
							new Object[] { arrangeSubPlan(subPlans) });
	
					Method setNewOptionServices = outputDTO.getClass().getMethod(
							"setNewOptionServices", List.class);
					setNewOptionServices.invoke(outputDTO,
							new Object[] { optionServices });
				}
				if (type.equals("active")){
					Method setActiveSubPlans = outputDTO.getClass().getMethod(
							"setActiveSubPlans", List.class);
					setActiveSubPlans.invoke(outputDTO, 
							new Object[] { arrangeSubPlan(subPlans) });
	
					Method setActiveOptionServices = outputDTO.getClass().getMethod(
							"setActiveOptionServices", List.class);
					setActiveOptionServices.invoke(outputDTO,
							new Object[] { optionServices });
				}
				if (type.equals("inactive")){
					Method setInactiveNewSubPlans = outputDTO.getClass().getMethod(
							"setInactiveSubPlans", List.class);
					setInactiveNewSubPlans.invoke(outputDTO, 
							new Object[] { arrangeSubPlan(subPlans) });
	
					Method setInactiveOptionServices = outputDTO.getClass().getMethod(
							"setInactiveOptionServices", List.class);
					setInactiveOptionServices.invoke(outputDTO,
							new Object[] { optionServices });
				}
				if (type.equals("terminated")){
					Method setTerminatedSubPlans = outputDTO.getClass().getMethod(
							"setTerminatedSubPlans", List.class);
					setTerminatedSubPlans.invoke(outputDTO, 
							new Object[] { arrangeSubPlan(subPlans) });
	
					Method setTerminatedOptionServices = outputDTO.getClass().getMethod(
							"setTerminatedOptionServices", List.class);
					setTerminatedOptionServices.invoke(outputDTO,
							new Object[] { optionServices });
				}
				if (type.equals("rejected")){
					Method setRejectedSubPlans = outputDTO.getClass().getMethod(
							"setRejectedSubPlans", List.class);
					setRejectedSubPlans.invoke(outputDTO, 
							new Object[] { arrangeSubPlan(subPlans) });
	
					Method setRejectedOptionServices = outputDTO.getClass().getMethod(
							"setRejectedOptionServices", List.class);
					setRejectedOptionServices.invoke(outputDTO,
							new Object[] { optionServices });
				}
				if (type.equals("one")){
					Method setOneSubPlans = outputDTO.getClass().getMethod(
							"setOneSubPlans", List.class);
					setOneSubPlans.invoke(outputDTO, 
							new Object[] { arrangeSubPlan(subPlans) });
	
					Method setOneOptionServices = outputDTO.getClass().getMethod(
							"setOneOptionServices", List.class);
					setOneOptionServices.invoke(outputDTO,
							new Object[] { optionServices });
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}			
		}

	}	
	public void displayButtonViewMode(Object outputDTO, Object inputDTO, QueryDAO queryDAO, String planStatus, String idUser, Plan activePlan){
		// set display for button: 0: Generate BIF, 1: Approve, 2: Delete, 3: Suspend, 4: Unsuspend, 5: Terminate
		// there are 3 status: 0: non-display, 1: display with disable, 2: display with enable
		// get display status for BIF button: Display only when (G.SET_VALUE = "MY" and G.SET_SEQ = 1 and G.ID_SET = "SYSTEMBASE")
		Integer[] displayStatus = {0,0, 0,0,0,0,0,0,0,0};
		String setValue = queryDAO.executeForObject("SELECT.B_CPM_S04.SET_VALUE", null, String.class);
		HashMap<String, Object> m = new HashMap<String, Object>();
		Plan plan = null;
		try {
			Method getPlan = outputDTO.getClass().getMethod("getPlan");
			plan = (Plan) getPlan.invoke(outputDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		String accessType = queryDAO.executeForObject("SELECT.B_CPM.GET_ACCESS_TYPE", idUser, String.class);
		if (setValue.equals("MY")){
			displayStatus[0] = 1;
			displayStatus[1] = 1;
			// Generate bif: Only enable when there is any M.PLAN_STATUS = "PS1" and M.IS_DELETED = 0.
			if (plan != null){
				m = new HashMap<String, Object>();
				m.put("status1", "PS1");
				m.put("condition", 1);
				List<Plan> checkPlans= queryDAO.executeForObjectList("SELECT.B_CPM.NEW_PLAN_LIST", m );
				if (checkPlans != null && checkPlans.size()>0){
					displayStatus[0] = 2;
				}
			}
			// Approve: Only enable when M.PLAN_STATUS = "PS1" and M.IS_DELETED = 0.
			// Delete: Display only when B.ACCESS_TYPE = 2 and when M.PLAN_STATUS = "PS1" (Draft)
			
			if (plan != null && (planStatus.equals("Draft") || planStatus.equals("PS1"))){
				displayStatus[1] = 2;
				if (accessType.equals("2")){
					displayStatus[2] = 2;
				}
			}
		}
		// get accessType of activePlan (which status = "PS3" or "PS6"
		// Suspend : Only display with condition: M.PLAN_STATUS = "PS3" and B.ACCESS_TYPE = 2 ==> display in Active tab ('PS3' or 'PS6')
		// Unsuspend: Only display and enable with condition:M.PLAN_STATUS = "PS6" and B.ACCESS_TYPE = 2 (Location of button is replace Suspend button)
		// Terminate: Only display with condition: M.PLAN_STATUS = "PS3" or "PS6"  and B.ACCESS_TYPE = 2
		if (accessType.equals("2")){
			if (activePlan != null && activePlan.getPlanStatus().equals("PS3")) {
				displayStatus[3] = 2;
				displayStatus[5] = 2;
			}
			if (activePlan != null && activePlan.getPlanStatus().equals("PS6")) {
				displayStatus[4] = 2;
				displayStatus[5] = 2;
			}
			
		}		
		try {
			Method setDisplayButtons = outputDTO.getClass().getMethod("setDisplayButtons",
					Integer[].class);
			setDisplayButtons.invoke(outputDTO, new Object[] {displayStatus});
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void getExportSingPort(Object outputDTO, QueryDAO queryDAO){
		String setValue = queryDAO.executeForObject("SELECT.B_CPM.GET_SYSTEMBASE", null, String.class);
		Integer[] displayItems = new Integer[30];
		try {
			Method getDisplayItems = outputDTO.getClass().getMethod("getDisplayItems");
			displayItems = (Integer[]) getDisplayItems.invoke(outputDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		if (setValue.equals("SG")){
			displayItems[14] =  1 ;
		}else{
			displayItems[14] =  0;
		}
	}
	
	public void processDisplayItems(Object outputDTO){
//		0: non-display
//		1: display with enable
//		2: display with disable (readOnly)
		Plan plan;
		Integer[] displayItems = new Integer[30];
		try {
			Method getPlan = outputDTO.getClass().getMethod("getPlan");
			plan =  (Plan) getPlan.invoke(outputDTO);
			Method getDisplayItems = outputDTO.getClass().getMethod("getDisplayItems");
			displayItems =   (Integer[]) getDisplayItems.invoke(outputDTO);
			
			// process Transaction Type: ReadOnly when M.Plan_status = "PS2"
			if (plan != null && plan.getPlanStatus() != null){
				if (plan.getPlanStatus().equals("PS2") ){
					displayItems[0] = 2;
				}
			}
			// process Reference Plan: Disable when TransactionType = "IN"
			if (plan.getTransactionType().equals("IN")){
				displayItems[18] = 2;
			}
			// process serviceStartTo: Readonly when Auto Renewal's checked (=1)
			if (plan.getAutoRenewal() != null && plan.getAutoRenewal().equals("1")){
				displayItems[8] = 2;
			}
			// process Fixed Forex: Disable by default || Exp Grand Total <> -- Please select One --
			if (plan.getExpGrandTotal() != null && !plan.getExpGrandTotal().equals("")){
				displayItems[16] = 2;
			}
			// process Quantity and Price: ReadOnly when m. is_display_desc is Uncheck 
			if (plan.getIsDisplayDesc() == null || plan.getIsDisplayDesc().equals("0")){
				displayItems[23] = 2;
				displayItems[24] = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public String suspendTerminate(UpdateDAO updateDAO, QueryDAO queryDAO, String idCustPlan, String type){
		String message = null;
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("idCustPlan", idCustPlan);
		// suspend: update plan_status = 'PS6'
		if (type.equals("suspend")){
			inputMap.put("planStatus", "PS6");
		}
		// unSuspend: update plan_status = 'PS6'
		if (type.equals("unSuspend")){
			inputMap.put("planStatus", "PS3");
		}
		// terminate: update plan_status = 'PS7'
		if (type.equals("terminate")){
			// validate srv_end
			String today = this.getToday();
			String svcEnd = queryDAO.executeForObject("SELECT.CPM_S02.GET_SVC_END", idCustPlan, String.class);
			if (svcEnd == null || svcEnd.equals(today)){
				// get billFrom and billTo
				HashMap<String, Object> resultMap = (HashMap<String, Object>) queryDAO.executeForMap("SELECT.CPM_S02.GET_BILL", idCustPlan);
				if (resultMap != null){
			        DateFormat formatter ; 
			        Date billToDate = null, billFromDate = null, nowDate = null ; 
			        Calendar billTo =Calendar.getInstance();
			        Calendar billFrom =Calendar.getInstance();
			        Calendar now=Calendar.getInstance(); ;
			        formatter = new SimpleDateFormat("dd/MM/yyyy");
			        try {
						billToDate = (Date)formatter.parse((String) resultMap.get("billTo"));
						billFromDate = (Date)formatter.parse((String) resultMap.get("billFrom"));
						nowDate = (Date)formatter.parse(today);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					billTo.setTime(billToDate);
					billFrom.setTime(billFromDate);
					now.setTime(nowDate);
					// update planStatus = PS7
					inputMap.put("planStatus", "PS7");
					// check if billFrom <= sysdate <= billTo
					if ((billFrom.before(now) || billFrom.equals(now)) && (billTo.after(now) || billTo.equals(now))){
						message = PropertyUtil.getProperty("errors.ERR1SC006");
					}else{
						// update svcEnd also
						updateDAO.execute("UPDATE.CPM_S02.UPDATE_PLAN_STATUS_SVC_END", inputMap);
					}
				}
			}else{
				inputMap.put("planStatus", "PS7");
			}
			inputMap.put("planStatus", "PS7");
		}
		updateDAO.execute("UPDATE.CPM_S02.UPDATE_PLAN_STATUS", inputMap);
		return message;
	}
	
	public String getBillType(String id){
		if (id.equals("IN")) return "Invoice";
		if (id.equals("DN")) return "Debit Note";
		if (id.equals("CN")) return "Credit Note";
		return "";
	}
	
	public void loadBillCurrencyList(Object outputDTO, QueryDAO queryDAO){
		List<BillCurrency> billCurList = new ArrayList<BillCurrency>();
		billCurList = queryDAO.executeForObjectList("SELECT.B_CPM.LOAD_BILL_CUR_LIST", null);
		if (billCurList != null && billCurList.size() == 0) billCurList = null;
		try {
			Method setBillCurrencyList = outputDTO.getClass().getMethod("setBillCurrencyList",
					List.class);
			setBillCurrencyList.invoke(outputDTO, new Object[] {billCurList});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadService(Object outputDTO, QueryDAO queryDAO, String serviceGrp){
		List<BillCurrency> serviceTypeList = new ArrayList<BillCurrency>();
		List<BillCurrency> serviceItemList = new ArrayList<BillCurrency>();
		List<Supplier> supplierList = new ArrayList<Supplier>();
		List<BillCurrency> billServiceGroupList = new ArrayList<BillCurrency>();
		List<ServiceGroup> serviceGroupList = new ArrayList<ServiceGroup>();
		HashMap< String, Object> m = new HashMap<String, Object>();
		// load serviceGroupList
		serviceGroupList = queryDAO.executeForObjectList("SELECT.B_CPM.SERVICE_GROUP_LIST", null);
		// load Service Item
		m.put("svcGrp", serviceGrp);
		m.put("type", "TYP");
		serviceTypeList = queryDAO.executeForObjectList("SELECT.B_CPM.LOAD_SERVICE", m);
		m.put("type", "ITM");
		serviceItemList = queryDAO.executeForObjectList("SELECT.B_CPM.LOAD_SERVICE", m);
		supplierList = queryDAO.executeForObjectList("SELECT.B_CPM.CARRIER_LIST", null);
		billServiceGroupList = queryDAO.executeForObjectList("SELECT.B_CPM.BILL_SERVICE_GROUP", null);
		try {
			Method setServiceTypeList = outputDTO.getClass().getMethod("setServiceTypeList",
					List.class);
			setServiceTypeList.invoke(outputDTO, new Object[] {serviceTypeList});
			Method setServiceItemList = outputDTO.getClass().getMethod("setServiceItemList",
					List.class);
			setServiceItemList.invoke(outputDTO, new Object[] {serviceItemList});
			Method setVendorList = outputDTO.getClass().getMethod("setVendorList",
					List.class);
			setVendorList.invoke(outputDTO, new Object[] {supplierList});
			Method setBillServiceGroupList = outputDTO.getClass().getMethod("setBillServiceGroupList",
					List.class);
			setBillServiceGroupList.invoke(outputDTO, new Object[] {billServiceGroupList});
			Method setServiceGroupList = outputDTO.getClass().getMethod("setServiceGroupList",
					List.class);
			setServiceGroupList.invoke(outputDTO, new Object[] {serviceGroupList});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void setDefaultValue(Object outputDTO ){
		B_CPM_S02UtilP1 util = new B_CPM_S02UtilP1();
		Plan plan = null;
		try {
			Method getPlan = outputDTO.getClass().getMethod("getPlan");
			plan = (Plan) getPlan.invoke(outputDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		plan.setApplicationDate(util.getToday());// set applicationDate = today	
		plan.setPlanStatus("PS1");
		plan.setTransactionType("IN");// default is Invoice
		plan.setBillDesc(plan.getPlanDesc());
		if (plan.getQuantity() == null || plan.getQuantity().equals("0")) plan.setQuantity("1");
		
	}
	public static boolean checkDecimalPlaces(String number, int decimalPlaces,
			double maxValue) {
		double d = 0;
		try {
			d = Double.parseDouble(number);
		} catch (Exception e) {
			return false;
		}
		if ((maxValue != 0) && (d > maxValue))
			return false;
		if (d == 0)
			return true;

		final double epsilon = Math.pow(10.0, ((decimalPlaces + 1) * -1));

		double multiplier = Math.pow(10, decimalPlaces);
		double check = d * multiplier;
		long checkLong = (long) Math.abs(check);
		check = checkLong / multiplier;

		double e = Math.abs(d - check);
		return e < epsilon;
	}

	public boolean checkDecimalString(String fixedFores) {
		if ((fixedFores != null) && (!fixedFores.equals(""))) {
			String[] splits = fixedFores.split("\\.");
			if ((splits.length > 3) || (splits[0].length() > 8)
					|| (splits[1].length() > 4)) {
				return false;
			}

		}
		return true;
	}
	
	public boolean validate(Object input,
			Object output, BLogicMessages errMsgs) {
		B_CPM_S02UtilP1 util = new B_CPM_S02UtilP1();
		BLogicMessage err;
		boolean fail = false;
		Plan plan = null;
		try {
			Method getPlan = input.getClass().getMethod("getPlan");
			plan = (Plan) getPlan.invoke(input);
		} catch (Exception e) {
			e.printStackTrace();
		}		// tbxProratebased OnSave: Check if value is <= 31. If no, prompt error
		// message: ERR1SC018
		String proBaseOn = plan.getProRateBaseOn();
		if ((proBaseOn == null) || (proBaseOn.equals(""))
				|| (Integer.parseInt(proBaseOn) > 31)) {
			Object[] items = {
					PropertyUtil.getProperty("errors.ERR1SC018.item1"),
					PropertyUtil.getProperty("errors.ERR1SC018.item2") };
			err = new BLogicMessage("errors.ERR1SC018", items);
			errMsgs.add(Globals.ERROR_KEY, err);
			fail = true;
		}
		// tbxFixedForex OnSave: Check if value is decimal value and is format
		// (8,4).
		String fixedFores = plan.getFixedForex();
		// check in case tbxFixedForex enable
		if (plan.getExpGrandTotal() != null
				|| !plan.getExpGrandTotal().equals("")) {
			if ((fixedFores != null) && (!fixedFores.equals(""))) {
				// Check if value is decimal value and is format (8,4).
				 if (!util.checkDecimalPlaces(fixedFores, 4, 99999999.9999)){
					err = new BLogicMessage("errors.ERR1SC012", PropertyUtil
							.getProperty("errors.ERR1SC012.item1"));
					errMsgs.add(Globals.ERROR_KEY, err);
					fail = true;
				 }
			} 
		}
		// tbxQuantity is mandatory fields
		String tbxQuantity = plan.getQuantity();
		if (tbxQuantity == null || tbxQuantity.equals("")) {
			err = new BLogicMessage("errors.ERR1SC005", PropertyUtil
					.getProperty("errors.ERR1SC005.item1"));
			errMsgs.add(Globals.ERROR_KEY, err);
			fail = true;
		}
		// Service Start From: mandatory field.
		String tbxServiceStartFrom = plan.getServiceStartFrom();
		if (tbxServiceStartFrom == null || tbxServiceStartFrom.equals("")) {
			err = new BLogicMessage("errors.ERR1SC005", PropertyUtil
					.getProperty("errors.ERR1SC005.item6"));
			errMsgs.add(Globals.ERROR_KEY, err);
			fail = true;
		}
		// Service Start From: mandatory field.
		String cboBillingInstr = plan.getBillingInstruction();
		if (cboBillingInstr == null || cboBillingInstr.equals("")) {
			err = new BLogicMessage("errors.ERR1SC005", PropertyUtil
					.getProperty("errors.ERR1SC005.item7"));
			errMsgs.add(Globals.ERROR_KEY, err);
			fail = true;
		}

		// 2. If Service Date To is enabled and not null, Service Date To must
		// be greater than Service Date From.
		String tbxServiceStartTo = plan.getServiceStartTo();
		if ((tbxServiceStartTo != null) && (!tbxServiceStartTo.equals(""))) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				if (sdf.parse(tbxServiceStartTo).before(
						sdf.parse(tbxServiceStartFrom))) {
					Object[] items = {
							PropertyUtil.getProperty("errors.ERR1SC037.item1"),
							PropertyUtil.getProperty("errors.ERR1SC037.item2"),
							PropertyUtil.getProperty("errors.ERR1SC037.item3") };
					err = new BLogicMessage("errors.ERR1SC037", items);
					errMsgs.add(Globals.ERROR_KEY, err);
					fail = true;
				}
			} catch (ParseException e) {
				err = new BLogicMessage("errors.date", PropertyUtil
						.getProperty("errors.date.item1"));
				errMsgs.add(Globals.ERROR_KEY, err);
				fail = true;
			}
		}
		// tbxUnitPrice Accepts integer and decimal values only.
		String planPrice = plan.getUnitPrice();
		if ((planPrice != null) && (!planPrice.equals(""))) {
			// Check if value is decimal value and is format (8,4).
			 if (!util.checkDecimalPlaces(planPrice, 2, 99999999.99)){
				err = new BLogicMessage("errors.ERR1SC012", PropertyUtil
						.getProperty("errors.ERR1SC012.item3"));
				errMsgs.add(Globals.ERROR_KEY, err);
				fail = true;
			 }
		} 
		return fail;
	}
	
	public boolean isExistedId(String[] array, String id){
		boolean existed = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(id)) return true;
		}
		return existed;
	}
	public void replaceBlankByZero(String[] sts){
		for (int i = 0; i < sts.length; i++) {
			if (sts[i].equals("")) sts[i] = "0";
		}
	}
	public String getModeName(String idMode){
		if (idMode.equals("1")) return "Yearly";
		if (idMode.equals("2")) return "Bi Annually";
		if (idMode.equals("3")) return "Quarterly";
		if (idMode.equals("4")) return "Bi-Monthly";
		if (idMode.equals("5")) return "Monthly";
		if (idMode.equals("6")) return "Once";
		if (idMode.equals("7")) return "Hourly";
		if (idMode.equals("8")) return "Minute";
		return "";
	
	}
	
}
