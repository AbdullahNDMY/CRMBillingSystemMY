
//Constants
var S01_GENERAL_TAB_POS = 0;
var S01_ISP_TAB_POS = 1;
var S01_COLOCATION_TAB_POS = 2;
var S01_EMAIL_TAB_POS = 3;
var S01_ADDRESS_TAB_POS = 4;

/**
 * Init B_SSM_S01 page
 */
function initB_SSM_S01_Page(tabID, tabPosition) {
	initB_SSM_S01_Page_TabSet(tabID, tabPosition);
	//clear condition value
	initClearCondition();
};

/**
 * clear condition value
 */
function initClearCondition(){
	var processModeField = document.getElementById("processModeField").value;
	if(processModeField=="4"){
		document.getElementById("customerIDText").value="";
		document.getElementById("customerNameID").value="";
		document.getElementById("subscriptionIDText").value="";
		// Router No
		document.getElementById('routerNo').value = '';
		// Circuit ID
		document.getElementById('circuitIDText').value = '';
		// Carrier
		document.getElementById('carrierID').selectedIndex = 0;
		// Modem No
		document.getElementById('modemNoID').value = '';
		// ADSL_DelNo
		document.getElementById('aDSL_DelNoID').value = '';
	}
}

/**
 * Set value to textfield with specific id
 * @param txtID
 * @param text
 */
function setTextField(txtID,  text) { 	
	document.getElementById(txtID).value = text;
	// #180 Start
	//general
	setPropertyValue("generalCategoryID", "#hiddenGeneralService", "#hiddenGeneralPlan", "#hiddenGeneralPlanDetail");
	//ISP
	setPropertyValue("categoryID", "#hiddenService", "#hiddenPlan", "#hiddenPlanDetail");
	//coLocation
	setPropertyValue("coLocationCategoryID", "#hiddenCoLocationService", "#hiddenCoLocationPlan", "#hiddenCoLocationPlanDetail");
	//email
	setPropertyValue("emailCategoryID", "#hiddenEmailService", "#hiddenEmailPlan", "#hiddenEmailPlanDetail");
	//address
	setPropertyValue("addressCategoryID", "#hiddenAddressService", "#hiddenAddressPlan", "#hiddenAddressPlanDetail");	
	// #180 End
};

/**
 * Init tab set of B_SSM_S01 page
 * @param tabID
 * @param tabPosition
 */
function initB_SSM_S01_Page_TabSet(tabID, tabPosition) {
	// Init tab content
	var tabs =new ddtabcontent(tabID);	
	tabs.setpersist(true);
	tabs.setselectedClassTarget("link"); 
	tabs.init();	
	tabs.expandit(tabPosition);
};

/**
 * Activate selectBoxs with specific condition
 */
function activateSelectBoxsWithCondition(isActive,
										 conditionSelectBoxID,
										 conditionValue,
										 processedSelectBoxIDs) {
	var element =  document.getElementById(conditionSelectBoxID);
	if (isActive && element) {
		var conditionSelectBoxValue = document.getElementById(conditionSelectBoxID).value;		
		if (conditionSelectBoxValue && conditionSelectBoxValue != conditionValue) {
			for ( var i = 0; i < processedSelectBoxIDs.length; i++) {
				document.getElementById(processedSelectBoxIDs[i]).disabled = false;
			}
		} else {
			for ( var j = 0; j < processedSelectBoxIDs.length; j++) {
				document.getElementById(processedSelectBoxIDs[j]).disabled = true;
			}
		}
	}
}

/**
 * Set values of specific select box
 */
function setSelectBoxValuesWithID(selectBoxID, valueArray, valueID, textID) {
	var selectBox = document.getElementById(selectBoxID);
	if (selectBox) {
		clearSelectBox(selectBox);
		setSelectBoxValues(selectBox, valueArray, valueID, textID);
	}
}

/**
 * Activate General select boxs
 */ 
function activateB_SSM_S01_GeneralSelectBox() {
	    var selectedCategoryValue = document.getElementById('generalCategoryID').value;
	    setSelectBoxValuesWithID('generalServiceID', serviceValues[selectedCategoryValue], 'value', 'text');
	    setSelectBoxValuesWithID('generalPlanID', planValues[selectedCategoryValue], 'value', 'text');
	    setSelectBoxValuesWithID('generalPlanDetailID', planDetailValues[selectedCategoryValue], 'value', 'text'); 
	    document.getElementById('generalServiceID').selectedIndex = 0;
	    document.getElementById('generalPlanID').selectedIndex = 0;
	    document.getElementById('generalPlanDetailID').selectedIndex = 0;
	    if (initialValues) {
	    	document.getElementById('generalServiceID').value = initialValues['generalService'];
	    	document.getElementById('generalPlanID').value = initialValues['generalPlan'];
	    	document.getElementById('generalPlanDetailID').value = initialValues['generalPlanDetail'];
	    }
	    activateSelectBoxsWithCondition(true,
									    'generalCategoryID',
									    BLANK_STRING,
										    new Array('generalServiceID',
						 						  'generalPlanID',
												  'generalPlanDetailID'));
		// #180 Start
	    inputValue('#generalCategoryID');
	    // #180 End										  
}

/**
 * Activate isp select boxs
 */ 
function activateB_SSM_S01_ISPSelectBox() {
	    var selectedCategoryValue = document.getElementById('categoryID').value;
	    setSelectBoxValuesWithID('serviceID', serviceValues[selectedCategoryValue], 'value', 'text');
	    setSelectBoxValuesWithID('planID', planValues[selectedCategoryValue], 'value', 'text');
	    setSelectBoxValuesWithID('planDetailID', planDetailValues[selectedCategoryValue], 'value', 'text'); 
	    document.getElementById('serviceID').selectedIndex = 0;
	    document.getElementById('planID').selectedIndex = 0;
	    document.getElementById('planDetailID').selectedIndex = 0;
	    if (initialValues) {
	    	document.getElementById('serviceID').value = initialValues['service'];
	    	document.getElementById('planID').value = initialValues['plan'];
	    	document.getElementById('planDetailID').value = initialValues['planDetail'];
	    }
	    activateSelectBoxsWithCondition(true,
									    'categoryID',
									    BLANK_STRING,
										    new Array('serviceID',
						 						  'planID',
												  'planDetailID'));
		// #180 Start
	    inputValue('#categoryID');
	    // #180 End										  
}


/**
 * Activate colocation select boxs
 */ 
function activateB_SSM_S01_CoLocationSelectBox() {
	    activateSelectBoxsWithCondition(true,
									    'coLocationCategoryID',
									    BLANK_STRING,
			 						    new Array('coLocationServiceID',
						 						  'coLocationPlanID',
												  'coLocationPlanDetailID'));
	    var selectedCategoryValue = document.getElementById('coLocationCategoryID').value;		
	    setSelectBoxValuesWithID('coLocationServiceID', serviceValues[selectedCategoryValue], 'value', 'text');
	    setSelectBoxValuesWithID('coLocationPlanID', planValues[selectedCategoryValue], 'value', 'text'); 
	    setSelectBoxValuesWithID('coLocationPlanDetailID', planDetailValues[selectedCategoryValue], 'value', 'text'); 
	    document.getElementById('coLocationServiceID').selectedIndex = 0;
	    document.getElementById('coLocationPlanID').selectedIndex = 0;
	    document.getElementById('coLocationPlanDetailID').selectedIndex = 0;
	    if (initialValues) {
	    	document.getElementById('coLocationServiceID').value = initialValues['coLocationService'];
	    	document.getElementById('coLocationPlanID').value = initialValues['coLocationPlan'];
	    	document.getElementById('coLocationPlanDetailID').value = initialValues['coLocationPlanDetail'];
	    }
	    // #180 Start
	    inputValue('#coLocationCategoryID');
	    // #180 End
}

/**
 * Activate address select boxs
 */ 
function activateB_SSM_S01_AddressSelectBox() {
	    activateSelectBoxsWithCondition(true,
									    'addressCategoryID',
									    BLANK_STRING,
										new Array('addressServiceID',
										           'addressPlanID',
									               'addressPlanDetailID'));
	    var selectedCategoryValue = document.getElementById('addressCategoryID').value;	
	    setSelectBoxValuesWithID('addressServiceID', serviceValues[selectedCategoryValue], 'value', 'text');
	    setSelectBoxValuesWithID('addressPlanID', planValues[selectedCategoryValue], 'value', 'text'); 
	    setSelectBoxValuesWithID('addressPlanDetailID', planDetailValues[selectedCategoryValue], 'value', 'text'); 
	    document.getElementById('addressServiceID').selectedIndex = 0;
	    document.getElementById('addressPlanID').selectedIndex = 0;
    	document.getElementById('addressPlanDetailID').selectedIndex = 0;
	    if (initialValues) {
	    	document.getElementById('addressServiceID').value = initialValues['addressService'];
	    	document.getElementById('addressPlanID').value = initialValues['addressPlan'];
	    	document.getElementById('addressPlanDetailID').value = initialValues['addressPlanDetail'];
	    }
	    // #180 Start
	    inputValue('#addressCategoryID');
	    // #180 End
}

/**
 * Activate email select boxs
 */ 
function activateB_SSM_S01_EmailSelectBox() {	
	    activateSelectBoxsWithCondition(true,
									    'emailCategoryID',
									    BLANK_STRING,
										new Array('emailServiceID',
										           'emailPlanID',
									               'emailPlanDetailID'));
	    var selectedCategoryValue = document.getElementById('emailCategoryID').value;	
	    setSelectBoxValuesWithID('emailServiceID', serviceValues[selectedCategoryValue], 'value', 'text');
	    setSelectBoxValuesWithID('emailPlanID', planValues[selectedCategoryValue], 'value', 'text'); 
	    setSelectBoxValuesWithID('emailPlanDetailID', planDetailValues[selectedCategoryValue], 'value', 'text'); 
	    document.getElementById('emailServiceID').selectedIndex = 0;
	    document.getElementById('emailPlanID').selectedIndex = 0;
    	document.getElementById('emailPlanDetailID').selectedIndex = 0;
	    if (initialValues) {
	    	document.getElementById('emailServiceID').value = initialValues['emailService'];
	    	document.getElementById('emailPlanID').value = initialValues['emailPlan'];
	    	document.getElementById('emailPlanDetailID').value = initialValues['emailPlanDetail'];
	    }
	    // #180 Start
	    inputValue('#emailCategoryID');
	    // #180 end
}

/**
 * Check ISP month value
 */ 
function checkISPMonthValue() {
	 return isValidNumberOnKeyUp('timeContractTermID', event, 1, 9999);
}

/**
* Check ISP year value
*/
function checkISPYearValue() {
	return isValidNumberOnKeyUp('timeContractTermID', event, 1, 9999);
}

/**
 * Check colocation month value
 */ 
function checkCoLocationMonthValue() {			
	return isValidNumberOnKeyUp('coLocationTimeContractTermID', event, 1, 9999);
}

/**
 * Check colocation year value
 */
function checkCoLocationYearValue() {
	return isValidNumberOnKeyUp('coLocationTimeContractTermID', event, 1, 9999);
}

/**
 * Check General month value
 */ 
function checkGeneralMonthValue() {			
	return isValidNumberOnKeyUp('generalTimeContractTermID', event, 1, 9999);
}

/**
 * Check General year value
 */
function checkGeneralYearValue() {
	return isValidNumberOnKeyUp('generalTimeContractTermID', event, 1, 9999);
}

/**
 * Check General time contract term
 */
function checkGeneralTimeContractTerm(element) {
	var isMonthChecked = document.getElementById('generalContractTermMode_MID').checked;
	var isYearChecked = document.getElementById('generalContractTermMode_YID').checked;
	var isValid = false;
	if (isMonthChecked) {
		isValid = checkGeneralMonthValue();
	}
	if (isYearChecked) {
		isValid = checkGeneralYearValue();
	}
	return isValid;
}

/**
 * Do action on change General time contract term
 */
function doChangeGeneralTimeContractTerm(element) {
	var isMonthChecked = document.getElementById('generalContractTermMode_MID').checked;
	var isYearChecked = document.getElementById('generalContractTermMode_YID').checked;
	var min = null;
	var max = null;
	if (isMonthChecked) {
		min = 1;
		max = 9999;
	}
	if (isYearChecked) {
		min = 1;
		max = 9999;
	}
	if (!isNumber(element.value) || !isNumberBetween(element.value, min, max)) {
		element.value = '';
	}
}

/**
 * Check timer contract term
 */
function checkTimeContractTerm(element) {
	var isMonthChecked = document.getElementById('contractTermMode_MID').checked;
	var isYearChecked = document.getElementById('contractTermMode_YID').checked;
	var isValid = false;
	if (isMonthChecked) {
		isValid = checkISPMonthValue();
	}
	if (isYearChecked) {
		isValid = checkISPYearValue();
	}
	return isValid;
}
 
/**
 * Do action on change time contract term
 */
function doChangeTimeContractTerm(element) {
	var isMonthChecked = document.getElementById('contractTermMode_MID').checked;
	var isYearChecked = document.getElementById('contractTermMode_YID').checked;
	var min = null;
	var max = null;
	if (isMonthChecked) {
		min = 1;
		max = 9999;
	}
	if (isYearChecked) {
		min = 1;
		max = 9999;
	}
	if (!isNumber(element.value) || !isNumberBetween(element.value, min, max)) {
		element.value = '';
	}
}

/**
 * Check colocation time contract term
 */
function checkCoLocationTimeContractTerm(element) {
	var isMonthChecked = document.getElementById('coLocationContractTermMode_MID').checked;
	var isYearChecked = document.getElementById('coLocationContractTermMode_YID').checked;
	var isValid = false;
	if (isMonthChecked) {
		isValid = checkCoLocationMonthValue();
	}
	if (isYearChecked) {
		isValid = checkCoLocationYearValue();
	}
	return isValid;
}

/**
 * Do action on change colocation time contract term
 */
function doChangeCoLocationTimeContractTerm(element) {
	var isMonthChecked = document.getElementById('coLocationContractTermMode_MID').checked;
	var isYearChecked = document.getElementById('coLocationContractTermMode_YID').checked;
	var min = null;
	var max = null;
	if (isMonthChecked) {
		min = 1;
		max = 9999;
	}
	if (isYearChecked) {
		min = 1;
		max = 9999;
	}
	if (!isNumber(element.value) || !isNumberBetween(element.value, min, max)) {
		element.value = '';
	}
}

/**
 * Add validator to textbox
 */		
function addValidatorToTextBox(txtID, checkID, tabPosition) {
	var txt = document.getElementById(txtID);
	// General
	if (tabPosition == S01_GENERAL_TAB_POS) {				
		if (checkID == 'M') {	
			txt.removeAttribute('readOnly');			
			//txt.detachEvent("onkeyup", checkISPYearValue);			
			//txt.attachEvent("onkeyup", checkISPMonthValue);
			txt.value = '';										    							
		}
		if (checkID == 'Y') {	
			txt.removeAttribute('readOnly');
			//txt.detachEvent("onkeyup", checkISPMonthValue);			
			//txt.attachEvent("onkeyup", checkISPYearValue);
			txt.value = '';												    							
		}
	}
	// ISP
	if (tabPosition == S01_ISP_TAB_POS) {				
		if (checkID == 'M') {	
			txt.removeAttribute('readOnly');			
			//txt.detachEvent("onkeyup", checkISPYearValue);			
			//txt.attachEvent("onkeyup", checkISPMonthValue);
			txt.value = '';										    							
		}
		if (checkID == 'Y') {	
			txt.removeAttribute('readOnly');
			//txt.detachEvent("onkeyup", checkISPMonthValue);			
			//txt.attachEvent("onkeyup", checkISPYearValue);
			txt.value = '';												    							
		}
	}
	// Colocation
	if (tabPosition == S01_COLOCATION_TAB_POS) {				
		if (checkID == 'M') {	
			txt.removeAttribute('readOnly');			
			//txt.detachEvent("onkeyup", checkCoLocationYearValue);				
			//txt.attachEvent("onkeyup", checkCoLocationMonthValue);	
			txt.value = '';										    							
		}
		if (checkID == 'Y') {	
			txt.removeAttribute('readOnly');
			//txt.detachEvent("onkeyup", checkCoLocationMonthValue);					
			//txt.attachEvent("onkeyup", checkCoLocationYearValue);	
			txt.value = '';															    						
		}
	}
}
 
/**
 * Add validator to textbox with previous state
 */		
function addValidatorToTextBoxWithPreviousState(txtID, checkID, tabPosition) {
 	var txt = document.getElementById(txtID);
 	// General
 	if (tabPosition == S01_GENERAL_TAB_POS) {				
 		if (checkID == 'M') {	
 			txt.removeAttribute('readOnly');			
 			//txt.detachEvent("onkeyup", checkISPYearValue);			
 			//txt.attachEvent("onkeyup", checkISPMonthValue);
 			//txt.value = '';										    							
 		}
 		if (checkID == 'Y') {	
 			txt.removeAttribute('readOnly');
 			//txt.detachEvent("onkeyup", checkISPMonthValue);			
 			//txt.attachEvent("onkeyup", checkISPYearValue);
 			//txt.value = '';												    							
 		}
 	}
 	// ISP
 	if (tabPosition == S01_ISP_TAB_POS) {				
 		if (checkID == 'M') {	
 			txt.removeAttribute('readOnly');			
 			//txt.detachEvent("onkeyup", checkISPYearValue);			
 			//txt.attachEvent("onkeyup", checkISPMonthValue);
 			//txt.value = '';										    							
 		}
 		if (checkID == 'Y') {	
 			txt.removeAttribute('readOnly');
 			//txt.detachEvent("onkeyup", checkISPMonthValue);			
 			//txt.attachEvent("onkeyup", checkISPYearValue);
 			//txt.value = '';												    							
 		}
 	}
 	// Colocation
 	if (tabPosition == S01_COLOCATION_TAB_POS) {				
 		if (checkID == 'M') {	
 			txt.removeAttribute('readOnly');			
 			//txt.detachEvent("onkeyup", checkCoLocationYearValue);				
 			//txt.attachEvent("onkeyup", checkCoLocationMonthValue);	
 			//txt.value = '';										    							
 		}
 		if (checkID == 'Y') {	
 			txt.removeAttribute('readOnly');
 			//txt.detachEvent("onkeyup", checkCoLocationMonthValue);					
 			//txt.attachEvent("onkeyup", checkCoLocationYearValue);	
 			//txt.value = '';															    						
 		}
 	}
}

/**
 * Activate Email Subscribed Mode Fields
 */
function activateEmailSubscribedModeFields() {			
	activateElementWith('emailSubscribedAddressTextID', true);
	activateElementWith('emailDomainNameTextID', true);
	activateElementWith('detetedEmailID', true);
	activateElementWith('emailTeamworkUrlTextID', false);
	activateElementWith('emailTeamworkAdminIDTextID', false);
	activateElementWith('emailTeamworkAdressTextID', false);
}

/**
 * Activate Teamwork Mode Fields
 */
function activateTeamworkModeFields() {
	activateElementWith('emailSubscribedAddressTextID', false);
	activateElementWith('emailDomainNameTextID', false);
	activateElementWith('detetedEmailID', false);
	document.getElementById('detetedEmailID').checked = false;
	activateElementWith('emailTeamworkUrlTextID', true);
	activateElementWith('emailTeamworkAdminIDTextID', true);
	activateElementWith('emailTeamworkAdressTextID', true);
}

/**
 * Activate Email Subscribed Mode Fields
 */
//function activateEmailITContactModeFields() {
//	activateElementWith('emailSubscribedAddressTextID', false);
//	activateElementWith('emailDomainNameTextID', false);
//	activateElementWith('emailTeamworkUrlTextID', true);
//	activateElementWith('emailTeamworkAdminIDTextID', true);
//}
		
/**
 * Get tab position
 */	
function getTabPosition() {			
	return document.getElementById('tabPositionField').value;
}			

/**
 * Activate fields in isp cust plan info
 */	
function activateB_SSM_S01_ISP_CustPlanInfoFields() {
	// Contract term
	var iSP_CustPlanInfo_MSelectBox = document.getElementById("contractTermMode_MID");
	if (iSP_CustPlanInfo_MSelectBox && iSP_CustPlanInfo_MSelectBox.checked) {
		//addValidatorToTextBox('timeContractTermID', 'M', 0);
	}
	var iSP_CustPlanInfo_YSelectBox = document.getElementById("contractTermMode_YID");
	if (iSP_CustPlanInfo_YSelectBox && iSP_CustPlanInfo_YSelectBox.checked) {
		//addValidatorToTextBox('timeContractTermID', 'Y', 0);
	}
}

/**
 * Activate fields in colocation cust plan info
 */	
function activateB_SSM_S01_CoLocation_CustPlanInfoFields() {
	// Contract term
	var iSP_CustPlanInfo_MSelectBox = document.getElementById("coLocationContractTermMode_MID");
	if (iSP_CustPlanInfo_MSelectBox && iSP_CustPlanInfo_MSelectBox.checked) {
		addValidatorToTextBoxWithPreviousState('coLocationTimeContractTermID', 'M', 1);
	}
	var iSP_CustPlanInfo_YSelectBox = document.getElementById("coLocationContractTermMode_YID");
	if (iSP_CustPlanInfo_YSelectBox && iSP_CustPlanInfo_YSelectBox.checked) {
		addValidatorToTextBoxWithPreviousState('coLocationTimeContractTermID', 'Y', 1);
	}
}

/**
 * Activate fields in email info
 */
function activateB_SSM_S01_EmailInfoFields() {
	// Contract term
	var emailSubscribedInfoMode = document.getElementById("emailSubscribedInfoModeID");
	if (emailSubscribedInfoMode && emailSubscribedInfoMode.checked) {
		activateEmailSubscribedModeFields();
	}
	var emailTeamworkInfoMode = document.getElementById("emailTeamworkInfoModeID");
	if (emailTeamworkInfoMode && emailTeamworkInfoMode.checked) {
		activateTeamworkModeFields();
	}
//	var emailITContactInfoMode = document.getElementById("emailITContactInfoModeID");
//	if (emailITContactInfoMode && emailITContactInfoMode.checked) {
//		activateEmailITContactModeFields();
//	}
}

/**
 * Activate fields in result panel
 */
function activateB_SSM_S01_ResultPanelFields() {	    	
	var tabPosition = getTabPosition();
	// General
	if (tabPosition == S01_GENERAL_TAB_POS) {
		// General
		activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultPageLinks');
		activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultInfo');	  					         		  					         
  	 	activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralDisplayResultPanel');
		// ISP
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPDisplayResultPanel');
  		// CoLocation			         
  	  	activateHiddenPanels(false, 
  					       	 'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultPageLinks');			       			       								 					  					       
	   	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultInfo');
	   	activateHiddenPanels(false, 
	         				 'B_SSM_S01_Page_Form_ResultPanel_CoLocationDisplayResultPanel');
      	// Email
      	activateHiddenPanels(false, 
			       			 'B_SSM_S01_Page_Form_ResultPanel_EmailResultPageLinks');
 	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_EmailResultInfo');
 	 	activateHiddenPanels(false, 
	   					   	 'B_SSM_S01_Page_Form_ResultPanel_EmailDisplayResultPanel');
	  	// Address
	  	activateHiddenPanels(false, 
   							 'B_SSM_S01_Page_Form_ResultPanel_AddressResultPageLinks');
	  	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_AddressResultInfo');
	  	activateHiddenPanels(false, 
				   			 'B_SSM_S01_Page_Form_ResultPanel_AddressDisplayResultPanel');
	}
	// ISP
	if (tabPosition == S01_ISP_TAB_POS) {
		// General
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralDisplayResultPanel');
		// ISP
		activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultPageLinks');
		activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultInfo');	  					         		  					         
  	 	activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPDisplayResultPanel');
  		// CoLocation			         
  	  	activateHiddenPanels(false, 
  					       	 'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultPageLinks');			       			       								 					  					       
	   	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultInfo');
	   	activateHiddenPanels(false, 
	         				 'B_SSM_S01_Page_Form_ResultPanel_CoLocationDisplayResultPanel');
      	// Email
      	activateHiddenPanels(false, 
			       			 'B_SSM_S01_Page_Form_ResultPanel_EmailResultPageLinks');
 	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_EmailResultInfo');
 	 	activateHiddenPanels(false, 
	   					   	 'B_SSM_S01_Page_Form_ResultPanel_EmailDisplayResultPanel');
	  	// Address
	  	activateHiddenPanels(false, 
   							 'B_SSM_S01_Page_Form_ResultPanel_AddressResultPageLinks');
	  	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_AddressResultInfo');
	  	activateHiddenPanels(false, 
				   			 'B_SSM_S01_Page_Form_ResultPanel_AddressDisplayResultPanel');
	}
	// Colocation
	if (tabPosition == S01_COLOCATION_TAB_POS) {
		// General
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralDisplayResultPanel');
		// ISP
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPDisplayResultPanel');
  		// CoLocation			         
  	  	activateHiddenPanels(true, 
  					       	 'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultPageLinks');			       			       								 					  					       
	   	activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultInfo');
	   	activateHiddenPanels(true, 
	         				 'B_SSM_S01_Page_Form_ResultPanel_CoLocationDisplayResultPanel');
      	// Email
      	activateHiddenPanels(false, 
			       			 'B_SSM_S01_Page_Form_ResultPanel_EmailResultPageLinks');
 	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_EmailResultInfo');
 	 	activateHiddenPanels(false, 
	   					   	 'B_SSM_S01_Page_Form_ResultPanel_EmailDisplayResultPanel');
	  	// Address
	  	activateHiddenPanels(false, 
   							 'B_SSM_S01_Page_Form_ResultPanel_AddressResultPageLinks');
	  	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_AddressResultInfo');
	  	activateHiddenPanels(false, 
				   			 'B_SSM_S01_Page_Form_ResultPanel_AddressDisplayResultPanel');
	}
	// Email
	if (tabPosition == S01_EMAIL_TAB_POS) {
		// General
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralDisplayResultPanel');
		// ISP
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPDisplayResultPanel');
  		// CoLocation			         
  	  	activateHiddenPanels(false, 
  					       	 'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultPageLinks');			       			       								 					  					       
	   	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultInfo');
	   	activateHiddenPanels(false, 
	         				 'B_SSM_S01_Page_Form_ResultPanel_CoLocationDisplayResultPanel');
      	// Email
      	activateHiddenPanels(true, 
			       			 'B_SSM_S01_Page_Form_ResultPanel_EmailResultPageLinks');
 	 	activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_EmailResultInfo');
 	 	activateHiddenPanels(true, 
	   					   	 'B_SSM_S01_Page_Form_ResultPanel_EmailDisplayResultPanel');
	  	// Address
	  	activateHiddenPanels(false, 
   							 'B_SSM_S01_Page_Form_ResultPanel_AddressResultPageLinks');
	  	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_AddressResultInfo');
	  	activateHiddenPanels(false, 
				   			 'B_SSM_S01_Page_Form_ResultPanel_AddressDisplayResultPanel');
	}
	// Address
	if (tabPosition == S01_ADDRESS_TAB_POS) {
		// General
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_GeneralDisplayResultPanel');
		// ISP
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultPageLinks');
		activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPResultInfo');	  					         		  					         
  	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_ISPDisplayResultPanel');
  		// CoLocation			         
  	  	activateHiddenPanels(false, 
  					       	 'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultPageLinks');			       			       								 					  					       
	   	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_CoLocationResultInfo');
	   	activateHiddenPanels(false, 
	         				 'B_SSM_S01_Page_Form_ResultPanel_CoLocationDisplayResultPanel');
      	// Email
      	activateHiddenPanels(false, 
			       			 'B_SSM_S01_Page_Form_ResultPanel_EmailResultPageLinks');
 	 	activateHiddenPanels(false, 
  					         'B_SSM_S01_Page_Form_ResultPanel_EmailResultInfo');
 	 	activateHiddenPanels(false, 
	   					   	 'B_SSM_S01_Page_Form_ResultPanel_EmailDisplayResultPanel');
	  	// Address
	  	activateHiddenPanels(true, 
   							 'B_SSM_S01_Page_Form_ResultPanel_AddressResultPageLinks');
	  	activateHiddenPanels(true, 
  					         'B_SSM_S01_Page_Form_ResultPanel_AddressResultInfo');
	  	activateHiddenPanels(true, 
				   			 'B_SSM_S01_Page_Form_ResultPanel_AddressDisplayResultPanel');
	}			
}

function resetClick(url,tabPosition){
	resetFieldValues(tabPosition);
	$('#searchForm').attr("action", url+"/B_SSM/B_SSM_Action.do?processMode=-1&hasSearchStarted=-1&tabPosition=0");
	$('#searchForm').submit();
	$('#searchForm').attr('action', 'B_SSM_S01_Request_Process_Action.do');
}

/**
 * Reset fields basing on tab position
 */	
function resetFieldValues(tabPosition) {
	// General
	if (tabPosition == S01_GENERAL_TAB_POS) {
		// CustomerID
		document.getElementById('generalCustomerIDText').value = '';
		// Customer Name
		document.getElementById('generalCustomerNameID').value = '';
		// Customer Type
		document.getElementById('generalCustomerTypeID').selectedIndex = 0;
		// Category
		document.getElementById('generalCategoryID').selectedIndex = 0;
		activateB_SSM_S01_GeneralSelectBox();
		// Service
		document.getElementById('generalServiceID').selectedIndex = 0;
		// Plan
		document.getElementById('generalPlanID').selectedIndex = 0;
		// Plan Detail
		document.getElementById('generalPlanDetailID').selectedIndex = 0;
		// Subscription ID
		document.getElementById('generalSubscriptionIDText').value = '';
		// Billing Account 
		//document.getElementById('generalBillingAccountText').value = '';
		// Billing Instruction
		//document.getElementById('generalBillingInstructionID').selectedIndex = 0;
		// From Service Date
		//document.getElementById('generalFromServiceDateID').value = '';
		// To Service Date
		//document.getElementById('generalToServiceDateID').value = '';
		// end From Service Date
		//document.getElementById('generalEndFromServiceDateID').value = '';
		// end To Service Date
		//document.getElementById('generalEndToServiceDateID').value = '';
		// Month Contract Term Mode
		//document.getElementById('generalContractTermMode_MID').checked = false;
		// Year Contract Term Mode
		//document.getElementById('generalContractTermMode_YID').checked = false;
		// Time Contract Term 
		//document.getElementById('generalTimeContractTermID').value = '';
		// Draft Service Status
		document.getElementById('generalDraftServiceStatusID').checked = false;
		// Pending Approval Service Status
		if (document.getElementById('generalPendingApprovalServiceStatusID') != null) {
			document.getElementById('generalPendingApprovalServiceStatusID').checked = false;
		}
		// Active Service Status
		document.getElementById('generalActiveServiceStatusID').checked = true;
		// Cancelled Service Status
		document.getElementById('generalCancelledServiceStatusID').checked = false;
		// Terminate Service Status
		document.getElementById('generalTerminatedServiceStatusID').checked = false;
		// Rejected Service Status
		if (document.getElementById('generalRejectedServiceStatusID') != null) {
			document.getElementById('generalRejectedServiceStatusID').checked = false;
		}
		// Suspended Service Status
		document.getElementById('generalSuspendedServiceStatusID').checked = false;
	}
	// ISP
	if (tabPosition == S01_ISP_TAB_POS) {
		// CustomerID
		document.getElementById('customerIDText').value = '';
		// Customer Name
		document.getElementById('customerNameID').value = '';
		// Customer Type
		document.getElementById('customerTypeID').selectedIndex = 0;
		// Installation Address
		document.getElementById('installationAddressID').value = '';
		// Postal Code
		document.getElementById('postalCodeID').value = '';
		// Router No
		document.getElementById('routerNo').value = '';
		// Circuit ID
		document.getElementById('circuitIDText').value = '';
		// Carrier
		document.getElementById('carrierID').selectedIndex = 0;
		// Modem No
		document.getElementById('modemNoID').value = '';
		// Access Account
		document.getElementById('txtAccessAccountID').value = '';
		// ADSL_DelNo
		document.getElementById('aDSL_DelNoID').value = '';
		// Category
		document.getElementById('categoryID').selectedIndex = 0;
		activateB_SSM_S01_ISPSelectBox();
		// Service
		document.getElementById('serviceID').selectedIndex = 0;
		// Plan
		document.getElementById('planID').selectedIndex = 0;
		// Plan Detail
		document.getElementById('planDetailID').selectedIndex = 0;
		// Subscription ID
		document.getElementById('subscriptionIDText').value = '';
		// Draft Service Status
		document.getElementById('draftServiceStatusID').checked = false;
		// Pending Approval Service Status
		if (document.getElementById('pendingApprovalServiceStatusID') != null) {
			document.getElementById('pendingApprovalServiceStatusID').checked = false;
		}
		// Active Service Status
		document.getElementById('activeServiceStatusID').checked = true;
		// Onetime Service Status
		document.getElementById('cancelledServiceStatusID').checked = false;
		// Terminate Service Status
		document.getElementById('terminatedServiceStatusID').checked = false;
		// Rejected Service Status
		if (document.getElementById('rejectedServiceStatusID') != null) {
			document.getElementById('rejectedServiceStatusID').checked = false;
		}
		// Suspended Service Status
		document.getElementById('suspendedServiceStatusID').checked = false;
		// Billing Instruction
		//document.getElementById('billingInstructionID').selectedIndex = 0;
		// From Service Date
		//document.getElementById('fromServiceDateID').value = '';
		// To Service Date
		//document.getElementById('toServiceDateID').value = '';
		// end From Service Date
		//document.getElementById('endFromServiceDateID').value = '';
		// end To Service Date
		//document.getElementById('endToServiceDateID').value = '';
		// Month Contract Term Mode
		//document.getElementById('contractTermMode_MID').checked = false;
		// Year Contract Term Mode
		//document.getElementById('contractTermMode_YID').checked = false;
		// Time Contract Term 
		//document.getElementById('timeContractTermID').value = '';
	}
	// Colocation
	if (tabPosition == S01_COLOCATION_TAB_POS) {
		// Colocation CustomerID
		document.getElementById('coLocationCustomerIDText').value = '';
		// CoLocation Customer Name
		document.getElementById('coLocationCustomerNameID').value = '';
		// CoLocation Customer Type
		document.getElementById('coLocationCustomerTypeID').selectedIndex = 0;
		// CoLocation Search By Details
		document.getElementById('coLocationSearchByDetailsID').checked = false;
		// CoLocation RackNo
		document.getElementById('coLocationRackNoID').value = '';
		// CoLocation Power Committed
		//document.getElementById('coLocationPowerCommittedID').value = '';
		// CoLocation Equipment Location
		document.getElementById('coLocationEquipmentLocationID').selectedIndex = 0;
		// CoLocation Equipment Suite
		document.getElementById('coLocationEquipmentSuiteID').value = '';				
		// CoLocation Category
		document.getElementById('coLocationCategoryID').selectedIndex = 0;
		activateB_SSM_S01_CoLocationSelectBox();
		// CoLocation Service
		document.getElementById('coLocationServiceID').selectedIndex = 0;
		// CoLocation Plan
		document.getElementById('coLocationPlanID').selectedIndex = 0;
		// CoLocation Plan Detail
		document.getElementById('coLocationPlanDetailID').selectedIndex = 0;
		// CoLocation Subscription ID
		document.getElementById('coLocationSubscriptionIDText').value = '';
		// CoLocation Draft Service Status
		document.getElementById('coLocationDraftServiceStatusID').checked = false;
		// CoLocation Pending Approval Service Status
		if (document.getElementById('coLocationPendingApprovalServiceStatusID') != null) {
			document.getElementById('coLocationPendingApprovalServiceStatusID').checked = false;
		}
		// CoLocation Active Service Status
		document.getElementById('coLocationActiveServiceStatusID').checked = true;
		// CoLocation Onetime Service Status
		document.getElementById('coLocationCancelledServiceStatusID').checked = false;
		// CoLocation Terminate Service Status
		document.getElementById('coLocationTerminatedServiceStatusID').checked = false;
		// CoLocationRejected Service Status
		if (document.getElementById('coLocationRejectedServiceStatusID') != null) {
			document.getElementById('coLocationRejectedServiceStatusID').checked = false;
		}
		// CoLocation Suspended Service Status
		document.getElementById('coLocationSuspendedServiceStatusID').checked = false;
		// CoLocation Billing Instruction
		//document.getElementById('coLocationBillingInstructionID').selectedIndex = 0;
		// CoLocation From Service Date
		//document.getElementById('coLocationFromServiceDateID').value = '';
		// CoLocation To Service Date
		//document.getElementById('coLocationToServiceDateID').value = '';
		// CoLocation From Service Date
		//document.getElementById('coLocationEndFromServiceDateID').value = '';
		// CoLocation To Service Date
		//document.getElementById('coLocationEndToServiceDateID').value = '';
		// CoLocation Month Contract Term Mode
		//document.getElementById('coLocationContractTermMode_MID').checked = false;
		// CoLocation Year Contract Term Mode
		//document.getElementById('coLocationContractTermMode_YID').checked = false;
		// CoLocation Time Contract Term 
		//document.getElementById('coLocationTimeContractTermID').value = '';
	}
	// Email
	if (tabPosition == S01_EMAIL_TAB_POS) {
		// Email CustomerID
		document.getElementById('emailCustomerIDText').value = '';
		// Email Customer Name
		document.getElementById('emailCustomerNameID').value = '';
		// Email Customer Type
		document.getElementById('emailCustomerTypeID').selectedIndex = 0;
		// Email Customer Telephone
		//document.getElementById('emailCustomerTelephoneID').value = '';
		// Email Subscribed Email Address Mode
		document.getElementById('emailSubscribedInfoModeID').checked = true;
		// Email Domain Name Mode
		document.getElementById('emailTeamworkInfoModeID').checked = false;
		// Email IT Contact Mode
		//document.getElementById('emailITContactInfoModeID').checked = false;
		// Email Subscribed Email Address Text
		document.getElementById('emailSubscribedAddressTextID').value = '';	
		// Email Domain Name Text
		document.getElementById('emailDomainNameTextID').value = '';	
		// Email Teamwork Admin ID
		document.getElementById('emailTeamworkAdminIDTextID').value = '';
		// Email Teamwork URL
		document.getElementById('emailTeamworkUrlTextID').value = '';
		// Email Teamwork Domain Address
		document.getElementById('emailTeamworkAdressTextID').value = '';
		// Email Category
		document.getElementById('emailCategoryID').selectedIndex = 0;
		activateB_SSM_S01_EmailSelectBox();
		// Email Service
		document.getElementById('emailServiceID').selectedIndex = 0;
		// Email Plan
		document.getElementById('emailPlanID').selectedIndex = 0;
		// Email Plan Detail
		document.getElementById('emailPlanDetailID').selectedIndex = 0;
		// Email Subscription ID
		document.getElementById('emailSubscriptionIDText').value = '';
		// Email Draft Service Status
		document.getElementById('emailDraftServiceStatusID').checked = false;
		// Email Pending Approval Service Status
		if (document.getElementById('emailPendingApprovalServiceStatusID') != null) {
			document.getElementById('emailPendingApprovalServiceStatusID').checked = false;
		}
		// Email Active Service Status
		document.getElementById('emailActiveServiceStatusID').checked = true;
		// Email Onetime Service Status
		document.getElementById('emailCancelledServiceStatusID').checked = false;
		// Email Terminate Service Status
		document.getElementById('emailTerminatedServiceStatusID').checked = false;
		// EmailRejected Service Status
		if (document.getElementById('emailRejectedServiceStatusID') != null) {
			document.getElementById('emailRejectedServiceStatusID').checked = false;
		}
		// Email Suspended Service Status
		document.getElementById('emailSuspendedServiceStatusID').checked = false;
		// Email Deleted Email 
		document.getElementById('detetedEmailID').checked = false;
	}
	// Address
	if (tabPosition == S01_ADDRESS_TAB_POS) {
		// Address CustomerID
		document.getElementById('addressCustomerIDText').value = '';
		// Address Customer Name
		document.getElementById('addressCustomerNameID').value = '';
		// Address Customer Type
		document.getElementById('addressCustomerTypeID').selectedIndex = 0;
		// Address Customer Telephone
		//document.getElementById('addressCustomerTelephoneID').value = '';
		// Address Fax No
		document.getElementById('addressCustomerFaxNoID').value = '';
		// Address Registered Type
		document.getElementById('addressRegisteredTypeID').checked = true;
		// Address Billing Type
		document.getElementById('addressBillingTypeID').checked = true;
		// Address Correspondence Type
		document.getElementById('addressCorrespondenceTypeID').checked = false;
		// Address Technical Type
		document.getElementById('addressTechnicalTypeID').checked = false;
		//#188 Start
		// Contact General Type
		document.getElementById('addressContactGeneralTypeID').checked = true;
		// Contact Billing Contact 1/2/3/4 Type
		document.getElementById('addressContactBillingTypeID').checked = true;
		// Primary Contact
		document.getElementById('addressContactPrimaryTypeID').checked = false;
		// Technical Contact
		document.getElementById('addressContactTechnicalTypeID').checked = false;
		// Ohter Contact
		document.getElementById('addressContactOtherTypeID').checked = false;
		// Contact IT Contact 1/2/3 Type
		document.getElementById('addressContactITTypeID').checked = false;
		// Consumer's NRIC / Passport No
		document.getElementById('addressICNoID').value = '';
		//#188 End
		// Address Text
		document.getElementById('addressTextID').value = '';	
		// Address Postal Code
		document.getElementById('addressPostalCodeID').value = '';					
		// Address Category
		document.getElementById('addressCategoryID').selectedIndex = 0;
		activateB_SSM_S01_AddressSelectBox();
		// Address Service
		document.getElementById('addressServiceID').selectedIndex = 0;
		// Address Plan
		document.getElementById('addressPlanID').selectedIndex = 0;
		// Address Plan Detail
		document.getElementById('addressPlanDetailID').selectedIndex = 0;
		// Address Subscription ID
		document.getElementById('addressSubscriptionIDText').value = '';
		// Address Draft Service Status
		document.getElementById('addressDraftServiceStatusID').checked = false;
		// Address Pending Approval Service Status
		if (document.getElementById('addressPendingApprovalServiceStatusID') != null) {
			document.getElementById('addressPendingApprovalServiceStatusID').checked = false;
		}
		// Address Active Service Status
		document.getElementById('addressActiveServiceStatusID').checked = true;
		// Address Onetime Service Status
		document.getElementById('addressCancelledServiceStatusID').checked = false;
		// Address Terminate Service Status
		document.getElementById('addressTerminatedServiceStatusID').checked = false;
		// AddressRejected Service Status
		if (document.getElementById('addressRejectedServiceStatusID') != null) {
			document.getElementById('addressRejectedServiceStatusID').checked = false;
		}
		// Address Suspended Service Status
		document.getElementById('addressSuspendedServiceStatusID').checked = false;
		// Address IT Contact - Name
		document.getElementById('addressITContactNoNameID').value = '';
		// Address IT Contact - Email
		document.getElementById('addressITContactNoEmailID').value = '';
		// Address IT Contact - Tel No.
		document.getElementById('addressITContactNoTelNoID').value = '';
		// Address Country
		document.getElementById('addressCountryID').selectedIndex = 0;
	}
}

/**
 * Activate B_SSM_S01 fields	
 */
function activateB_SSM_S01Fields() {			
	// #180 Start
	$(".searchService").combobox();
    $(".searchPlan").combobox();
    $(".searchPlanDetail").combobox();
    // #180 End
	// Activate selectboxes
	activateB_SSM_S01_GeneralSelectBox();
	activateB_SSM_S01_ISPSelectBox();
  	activateB_SSM_S01_CoLocationSelectBox();
    activateB_SSM_S01_EmailSelectBox();
  	activateB_SSM_S01_AddressSelectBox();
    
    // Activate cust plan fields
  	activateB_SSM_S01_ISP_CustPlanInfoFields();
  	activateB_SSM_S01_CoLocation_CustPlanInfoFields();
  	
  	// Activate email info
  	activateB_SSM_S01_EmailInfoFields();
  	
  	// Activate result panel fields
  	activateB_SSM_S01_ResultPanelFields();
  	
  	// Empty initial values
  	initialValues = null;
	// #180 Start
	//general
  	setCombInputValue("generalCategoryID", "#hiddenGeneralService", "#hiddenGeneralPlan", "#hiddenGeneralPlanDetail");
	//ISP
  	setCombInputValue("categoryID", "#hiddenService", "#hiddenPlan", "#hiddenPlanDetail");
	//coLocation
  	setCombInputValue("coLocationCategoryID", "#hiddenCoLocationService", "#hiddenCoLocationPlan", "#hiddenCoLocationPlanDetail");
	//email
  	setCombInputValue("emailCategoryID", "#hiddenEmailService", "#hiddenEmailPlan", "#hiddenEmailPlanDetail");
	//address
  	setCombInputValue("addressCategoryID", "#hiddenAddressService", "#hiddenAddressPlan", "#hiddenAddressPlanDetail");	
	// #180 End
}
 
/**
 * Activate export button
 */
function activateExportBtn(isNullResultSet, isAllowExport) {
	var exportBtn = document.getElementById('exportBtnID');
	if (exportBtn) {
		if (isNullResultSet || !isAllowExport) {
			exportBtn.disabled = true;
		} else {
			if (!isNullResultSet) {
				exportBtn.disabled = false;
			}
		}
	}
}
// #180 Start
function inputValue(CategoryID) {
	$(CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent")
	 			 .find(".custom-combobox-input").val( "- Please Select One -" );	
    if ($(CategoryID).val() != "") {
    	$(CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent")
    				 .find(".custom-combobox-input").attr("disabled", false);
		$(CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent")
					 .find(".ui-button").attr("disabled", false);
		
	}else {
		$(CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent")
    				 .find(".custom-combobox-input").attr("disabled", true);
		$(CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent")
					 .find(".ui-button").attr("disabled", true);
	}
    $(".custom-combobox-input").css("width","400px");
	$(".ui-button").css("height","20px");
}

function setPropertyValue(CategoryID,hiddenService,hiddenPlan,hiddenPlanDetail) {
	var categoryVal = $("#"+CategoryID).val();
	var input2 = $("#"+CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent").find("#combInput2").val();
	var input3 = $("#"+CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent").find("#combInput3").val();
	var input4 = $("#"+CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent").find("#combInput4").val();
	if (categoryVal != "") {
		var inputBlank = "- Please Select One -";
		if (input2 != inputBlank) {
			$(hiddenService).val(input2);
		}else {
			$(hiddenService).val("");
		}
		if (input3 != inputBlank) {
			$(hiddenPlan).val(input3);
		}else {
			$(hiddenPlan).val("");
		}
		if (input4 != inputBlank) {
			$(hiddenPlanDetail).val(input4);
		}else {
			$(hiddenPlanDetail).val("");
		}
	}
} 
function setCombInputValue(CategoryID,hiddenService,hiddenPlan,hiddenPlanDetail) {
	var categoryVal = $("#"+CategoryID).val();
	var input2 = $("#"+CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent").find("#combInput2");
	var input3 = $("#"+CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent").find("#combInput3");
	var input4 = $("#"+CategoryID).closest(".B_SSM_S01_Page_Form_FieldSet_PanelContent").find("#combInput4");
	if (categoryVal != "") {
		if ($(hiddenService).val() != "") {
			input2.val($(hiddenService).val());
		}else {
			input2.val("- Please Select One -");
		}
		if ($(hiddenPlan).val() != "") {
			input3.val($(hiddenPlan).val());
		}else {
			input3.val("- Please Select One -");
		}
		if ($(hiddenPlanDetail).val() != "") {
			input4.val($(hiddenPlanDetail).val());
		}else {
			input4.val("- Please Select One -");
		}
	}
} 
// #180 End 
