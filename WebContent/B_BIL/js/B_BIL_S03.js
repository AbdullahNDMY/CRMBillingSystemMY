var msg = new messageBox("");

$(document).ready(function() {
	//onload function
	var screenWidth = window.screen.width;
	var contentDivWidth = screenWidth-270;
	// set page width
	$('#contentDiv').css("width",contentDivWidth);
	
	//M_GSET_D is BAC the Currency combo Box is disabled
	//var currencyEnabledFlg = $('#currencyEnabledFlg').val();
	//if (currencyEnabledFlg=="0") {
		$('#cboCurrency').attr("disabled", true);
	//}
	
	//Cust Name Combo Box not selected then Add Description button and Add Item from Customer Plan button disabled
	var cboCustomerName = $('#cboCustomerName').val();
	if(cboCustomerName==""||cboCustomerName==null) {
		$('#btnAddDescription').attr("disabled", true);
		$('#btnAddCustPlan').attr("disabled", true);
	} else {
		$('#btnAddDescription').attr("disabled", false);
		$('#btnAddCustPlan').attr("disabled", false);
	}
	//init Contract Period display
	initContractPeriodDisplay();
	
	//change GST Percent value
	changeGstPercentValue();
});

/**
 * init Contract Period display
 */
function initContractPeriodDisplay(){
	var bilDetailInfos = $("#bilDetail_group").find(".itemBillDetail");
	for ( var i = 0; i < bilDetailInfos.length; i++) {
		var itemBillDetail = bilDetailInfos.eq(i);
		var serviceItemCat = itemBillDetail.find(".serviceItemCat").val();
		//is Contract Period display flag
		var serviceIsDisplayMinSvc = itemBillDetail.find(".serviceIsDisplayMinSvc").val();
		if(serviceIsDisplayMinSvc=="1" && serviceItemCat=="1") {
			itemBillDetail.find(".trMinSvcFormAndTo").attr("style", "display:inline");
		} else {
			itemBillDetail.find(".trMinSvcFormAndTo").attr("style", "display:none");
		}
		
		//#154 start
		if (serviceItemCat=="1") {
			//serviceGst
			var serviceIsDisplay = itemBillDetail.find(".serviceIsDisplay").val();
			var serviceTaxRate = itemBillDetail.find(".serviceTaxRate").val();
			var serviceTaxCode = itemBillDetail.find(".serviceTaxCode").val();
			
			if (serviceIsDisplay=="1") {
				if($(".gstCheck").val() == "TAX_RATE"){
					itemBillDetail.find(".divServiceGST").text(serviceTaxRate+"%");
				}else {
					itemBillDetail.find(".divServiceGST").text(serviceTaxCode);
				}
			} else {
				itemBillDetail.find(".divServiceGST").text("");
			}
			//serviceDisplayDiscount
			var serviceItemDisc = itemBillDetail.find(".serviceItemDisc").val();
			var serviceDisplayDiscount = itemBillDetail.find(".serviceDisplayDiscount").val();
			var serviceItemLevel = itemBillDetail.find(".serviceItemLevel").val();
			
			if(serviceDisplayDiscount == "1" && serviceItemLevel == "0" && serviceItemDisc != "0"){
				itemBillDetail.find("#trServiceDisCount").attr("style", "display:inline");
				itemBillDetail.find(".divServiceItemDisc").text(negativeNumberFormat(serviceItemDisc, 2, ","));
				if(serviceIsDisplay == "1"){
					if($(".gstCheck").val() == "TAX_RATE"){
						itemBillDetail.find(".divServiceDisCountGST").text(serviceTaxRate+"%");
					}else {
						itemBillDetail.find(".divServiceDisCountGST").text(serviceTaxCode);
					}
				}else {
					itemBillDetail.find(".divServiceDisCountGST").text("");
				}
			}else {
				itemBillDetail.find(".divServiceItemDisc").text("");
				itemBillDetail.find(".divServiceDisCountGST").text("");
				itemBillDetail.find("#trServiceDisCount").attr("style", "display:none");
			}
			
			//setting sub plan
			var subItemBillDetails = itemBillDetail.find(".subPlan");
			for ( var j = 0; j < subItemBillDetails.length; j++) {
				var subItemBillDetail = subItemBillDetails.eq(j);
				var subPlanIsDisplay = subItemBillDetail.find(".subPlanIsDisplay").val();
				var subPlanTaxRate = subItemBillDetail.find(".subPlanTaxRate").val();
				var subPlanTaxCode = subItemBillDetail.find(".subPlanTaxCode").val();
				if (subPlanIsDisplay=="1") {
					if($(".gstCheck").val() == "TAX_RATE"){
						subItemBillDetail.find(".divGst").text(subPlanTaxRate+"%");
					}else {
						subItemBillDetail.find(".divGst").text(subPlanTaxCode);
					}
				} else {
					subItemBillDetail.find(".divGst").text("");
				}
				var subPlanItemDisc = subItemBillDetail.find(".subPlanItemDisc").val();
				var subPlanDisplayDiscount = subItemBillDetail.find(".subPlanDisplayDiscount").val();
				var subPlanItemLevel = subItemBillDetail.find(".subPlanItemLevel").val();
				if(subPlanDisplayDiscount == "1" && subPlanItemLevel == "1" && subPlanItemDisc != "0"){
					subItemBillDetail.find("#trSubPlanDisCount").attr("style", "display:inline");
					subItemBillDetail.find(".divSubPlanItemDisc").text(negativeNumberFormat(subPlanItemDisc, 2, ","));
					if(subPlanIsDisplay == "1"){
						if($(".gstCheck").val() == "TAX_RATE"){
							subItemBillDetail.find(".divSubPlanDisCountGST").text(subPlanTaxRate+"%");
						}else {
							subItemBillDetail.find(".divSubPlanDisCountGST").text(subPlanTaxCode);
						}
					}else {
						subItemBillDetail.find(".divSubPlanDisCountGST").text("");
					}
				}else {
					subItemBillDetail.find(".divSubPlanItemDisc").text("");
					subItemBillDetail.find(".divSubPlanDisCountGST").text("");
					subItemBillDetail.find("#trSubPlanDisCount").attr("style", "display:none");
				}
			}
		}
		//#154 end
	}
}

/**
 * address type combo box change event
 */
function cboAdrTypeChange(){
	$('#changeTypeFlag').val("");
	comboBoxChangeEvent();
}

/**
 * Attn combo box change event
 */
function cboAttnChange(){
	$('#changeTypeFlag').val("");
	comboBoxChangeEvent();
}

/**
 * Billing Account No. combo box change event
 */
function cboBillAccChange(){
	$('#changeTypeFlag').val("changeBillAcc");
	comboBoxChangeEvent();
}

/**
 * Currency combo box change event
 */
function cboCurrencyChange(){
	$('#changeTypeFlag').val("");
	comboBoxChangeEvent();
}

function comboBoxChangeEvent(){
	var path = $("#rootPath").val();
	var changeTypeFlag = $("#changeTypeFlag").val();
	var idCust = $("#cboCustomerName").val();
	var adrType = $("#cboAdrType").val();
	var contactType = $("#cboAttn").val();
	var billAcc = $("#cboBillAcc").val();
	var billCurrency = $("#cboCurrency").val();
	var invoiceDate = $("#dateReq").val();
	var preCustomerTypeFlag = $("#customerTypeFlag").val();
	var billType = $("#billType").val();
	//M_GSET_D is BAC the Currency combo Box is disabled
	var currencyEnabledFlg = $('#currencyEnabledFlg').val();
	// Add #156 Start
	var billCnAmtNegative = $("#billCnAmtNegative").val();
	var negativeDispalyFlg = "0";
	if ("CN"==billType && "1"==billCnAmtNegative) {
		negativeDispalyFlg = "1";
	}
	// Add #156 End
	
	var urlPath = path+"/B_BIL/B_BIL_S03neCboChangeAjaxBL.do";
	$.ajax({
		type: "POST",
		url: urlPath,
		data: {
			"bilHeaderInfo.changeTypeFlag": changeTypeFlag,
			"bilHeaderInfo.idCust": idCust,
			"bilHeaderInfo.adrType":adrType,
			"bilHeaderInfo.contactType": contactType,
			"bilHeaderInfo.billAcc": billAcc,
			"bilHeaderInfo.billType": billType,
			"bilHeaderInfo.billCurrency": billCurrency,
			"bilHeaderInfo.invoiceDate": invoiceDate
		},
		success: function(html) { 
			var content = document.createElement("div");
			content.innerHTML = html;
			var result = $(content);
			
			$("#tdAdrType").html(result.find("#divAdrType").html());
			$("#tdAddress1").html(result.find("#divAddress1").html());
			$("#tdAddress2").html(result.find("#divAddress2").html());
			$("#tdAddress3").html(result.find("#divAddress3").html());
			$("#tdAddress4").html(result.find("#divAddress4").html());
			$("#tdTelNo").html(result.find("#divTelNo").html());
			$("#tdFaxNo").html(result.find("#divFaxNo").html());
			$("#tdCboAttn").html(result.find("#divCboAttn").html());
			$("#tdContactEmail").html(result.find("#divContactEmail").html());
			$("#tdContactEmailCC").html(result.find("#divContactEmailCC").html());
			// #206 Start
			//$("#tdDelivery").html(result.find("#divDelivery").html());
			// #206 End
			//Customer Name is cust name combo box change
			if ("changeCustName"==changeTypeFlag) {
				$("#tdBillAcc").html(result.find("#divBillAcc").html());
				
				$("#gstAmount").val("0");
				$("#billAmount").val("0");
				$("#subTotal").text("0.00");
				$("#subTotalAmt").val("0.00");
				$("#gstAmountDisplay").text("0.00");
				$("#billAmountDisplay").text("0.00");
				$("#bilDetail_group").html("");
				// Add #156 Start
				if ("1"==negativeDispalyFlg) {
					$("#subTotal").text("-0.00");
					$("#gstAmountDisplay").text("-0.00");
					$("#billAmountDisplay").text("-0.00");
				}
				// Add #156 End
				
				//Billing Account No. combo box change
				$("#tdPayMethod").html(result.find("#divPayMethod").html());
				$("#tdCboBillCurrency").html(result.find("#divCboBillCurrency").html());
				$("#spanSubTotalBillCurrency").html(result.find("#divBillCurrency").html());
				$("#spanSubTotalBillCurrency2").html(result.find("#divBillCurrency").html());
				$("#spanGrandTotalUBillCurrency").html(result.find("#divBillCurrency").html());
				$("#spanBillAmountBillCurrency").html(result.find("#divBillCurrencyName").html());
				$("#spanGrandTotal").html(result.find("#divGrandTotal").html());
				$("#tdTermDays").html(result.find("#divTermDays").html());
				$("#tdContactDueDate").html(result.find("#divContactDueDate").html());
				$("#tdDelivery").html(result.find("#divDelivery").html());
				
				var salutation = result.find("#divTelNo").find("#salutation").val();
				var salutationValue = "";
				var custName = result.find("#divTelNo").find("#custName").val();
				if ("MR"==salutation) {
					salutationValue = "Mr";
				} else if ("MS"==salutation) {
					salutationValue = "Ms";
				} else if ("MRS"==salutation) {
					salutationValue = "Mrs";
				}
				$("#spanCustomerName").text(salutationValue+" "+custName);
				
				//change GST Percent value
				changeGstPercentValue();
			}
			var currentBillAcc = $("#cboBillAcc").val();
			//Billing Account No. combo box change
			if ("changeBillAcc"==changeTypeFlag){
				$("#tdPayMethod").html(result.find("#divPayMethod").html());
				$("#tdCboBillCurrency").html(result.find("#divCboBillCurrency").html());
				$("#spanSubTotalBillCurrency").html(result.find("#divBillCurrency").html());
				$("#spanSubTotalBillCurrency2").html(result.find("#divBillCurrency").html());
				$("#spanGrandTotalUBillCurrency").html(result.find("#divBillCurrency").html());
				$("#spanBillAmountBillCurrency").html(result.find("#divBillCurrencyName").html());
				$("#spanGrandTotal").html(result.find("#divGrandTotal").html());
				$("#tdTermDays").html(result.find("#divTermDays").html());
				$("#tdContactDueDate").html(result.find("#divContactDueDate").html());
				$("#tdDelivery").html(result.find("#divDelivery").html());
				
				getExportAmount();
			}
			if(currentBillAcc==""||currentBillAcc==null) {
				$("#tdPayMethod").html(result.find("#divPayMethod").html());
				$("#tdCboBillCurrency").html(result.find("#divCboBillCurrency").html());
				$("#spanSubTotalBillCurrency").html(result.find("#divBillCurrency").html());
				$("#spanSubTotalBillCurrency2").html(result.find("#divBillCurrency").html());
				$("#spanGrandTotalUBillCurrency").html(result.find("#divBillCurrency").html());
				$("#spanBillAmountBillCurrency").html(result.find("#divBillCurrencyName").html());
				$("#tdTermDays").html(result.find("#divTermDays").html());
				$("#tdContactDueDate").html(result.find("#divContactDueDate").html());
				$("#tdDelivery").html(result.find("#divDelivery").html());
			}
			//if (currencyEnabledFlg=="0") {
				$('#cboCurrency').attr("disabled", true);
			//}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//error handler
		}
	});
}

/**
 * open B_BAC_S03 page
 */
function openB_BAC_S03() {
	var path = $("#rootPath").val();
	var url = path+"/B_BAC/RP_B_BAC_S03SearchBL.do";
	var width = window.screen.width*80/100;
    var height = window.screen.height*80/100;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;	 	
	var newwindow = window.open(url, null, strFeatures);	
	if (window.focus) { newwindow.focus(); }
	return false;
}

/**
 * B_BAC_S03 page return call
 */
function setCustomerAndBillAccInfo(idCust,custName,idBillAcc){
	$('#changeTypeFlag').val("changeCustName");
	$("#cboCustomerName").val(idCust);
	$("#spanCustomerId").text(idCust);
	$('#cboBillAcc').append("<option value='"+idBillAcc+"'>"+idBillAcc+"</option>");
	$('#cboBillAcc').val(idBillAcc);
	if(idCust==""||idCust==null) {
		$('#btnAddDescription').attr("disabled", true);
		$('#btnAddCustPlan').attr("disabled", true);
	} else {
		$('#btnAddDescription').attr("disabled", false);
		$('#btnAddCustPlan').attr("disabled", false);
	}
	comboBoxChangeEvent();
}

/**
 * get Export Amount value
 */
function getExportAmount(){
	var billCur = $("#billCurrency").val();
	var expCur = $("#exportCur").val();
	if(expCur!=null && expCur !=undefined) {
		if(billCur != "" && billCur != null
			&& expCur != "" && expCur != ""
			&& expCur != billCur) {
			var fixedForex = $("#fixedForex").val();
			var gstAmount = $("#gstAmount").val();
			var subTotalAmt = $("#subTotalAmt").val();
			var curRate = $("#curRate").val();
			var path = $("#rootPath").val();
			if (parseFloat(curRate)==0.0) {
				$("#curRate").val("1");
				curRate = "1";
			}
			var gstPercent = $("#gstPercent").val();
			var itemAmtString = "";
			var itemApplyGstString = "";
			$(".subPlanItemAmt").each(
				function(i,e){
					itemAmtString = itemAmtString +e.value+",";
				}
			);
			$(".subPlanApplyGst").each(
					function(i,e){
						itemApplyGstString = itemApplyGstString +e.value+",";
					}
				);
			itemAmtString = itemAmtString.substring(0, itemAmtString.length-1);
			itemApplyGstString = itemApplyGstString.substring(0, itemApplyGstString.length-1);
			var url = path + "/B_BIL/GetExpAmtAjax.do";
			$.ajax({
		        type: 'POST',
		        url: url,
		        data: {billingCurrency : billCur,
		        	   exportCurrency  : expCur,
		        	   gstAmount       : gstAmount,
		        	   subTotalAmt     : subTotalAmt,
		        	   fixedForex      : fixedForex,
		        	   curRate         : curRate,
		        	   gstPercent      : gstPercent,//need divide 100 in logic
		        	   itemAmtString   : itemAmtString,
		        	   itemApplyGstString : itemApplyGstString
		        },
		        success: function(data){
		        	data = parseFloat(data).toFixed(2);
		            $("#spanExportAmount").text(numberFormat(data,2,","));
		            $("#exportAmount").val(data);
		        }
		    });
		}
	}
}

/**
 * Add Description button
 */
function addDesc(){
	//open B_BIL_S05 page
	openB_BIL_S05("", "", "");
}

/**
 * Add Item from Customer Plan
 */
function addCustPlan(){
	//open B_BIL_S05 page
	var idCust = $('#cboCustomerName').val();
	var cboBillAcc = $('#cboBillAcc').val();
	//M_GSET_D is BAC 
	var currencyEnabledFlg = $('#currencyEnabledFlg').val();
	if (currencyEnabledFlg=="0") {
		if(cboBillAcc==null || cboBillAcc=="" || cboBillAcc=="-"){
			msg.POPALT($("#message_group").find(".messageSelectBillAcc").text());
		} else {
			openB_BIL_S04(idCust, cboBillAcc);
		}
	} else {
		openB_BIL_S04(idCust, "");
	}
}

/**
 * edit Billing item
 * @param obj
 */
function editBillItem(obj) {
	var itemBillDetail = $(obj).closest("div.itemBillDetail");
	//0:Not Billing Item,1:Billing Item
	var serviceItemCat = itemBillDetail.find(".serviceItemCat").val();
	var serviceItemId = itemBillDetail.find(".serviceItemId").val();
	if("0"==serviceItemCat) {
		var serviceItemDesc = itemBillDetail.find(".serviceItemDesc").val();
		//open B_BIL_S05 page
		openB_BIL_S05("", serviceItemId, serviceItemDesc);
	} else {
		//open B_BIL_S06 page
		openB_BIL_S06(itemBillDetail);
	}
}

/**
 * open B_BIL_S04 page
 * @param idCust
 * @param cboBillAcc
 */
function openB_BIL_S04(idCust, cboBillAcc) {
	var path = $("#rootPath").val();
	var url = path+"/B_BIL/B_BIL_S04CustPlanSearch.do?idCust="+idCust+"&billAcc="+cboBillAcc;
	var width = window.screen.width*80/100;;
    var height = window.screen.height*80/100;;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=no" + "," + offsetFeatures;	 	
	var newwindow = window.open(url, null, strFeatures);
	if (window.focus) { newwindow.focus(); }
}

/**
 * B_BIL_S04 page return call
 * @param idCustPlanGrps service id
 * @param idCustPlanLink sub plan id
 */
function B_BIL_S04ReturnCalled(idCustPlanGrps, idCustPlanLinks){
	var path = $("#rootPath").val();
	var billType = $("#billType").val();
	var urlPath = path+"/B_BIL/B_BIL_S03neAddBillingItemBL.do?idCustPlanGrps="+idCustPlanGrps
									+"&idCustPlanLinks="+idCustPlanLinks+"&billType="+billType;
	$.ajax({
		type: "POST",
		url: urlPath,
		success: function(html) { 
			var content = document.createElement("div");
			content.innerHTML = html;
			var result = $(content);
			//add new object to sub plan group
			var billDetail = result.find(".itemBillDetailList").clone();
			$("#bilDetail_group").append(billDetail);
			//Refresh Biling Item info
			refreshBillItem();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//error handler
		}
	});
}

/**
 * open B_BIL_S05 page
 * @param idRef
 * @param itemId
 * @param itemDesc
 */
function openB_BIL_S05(idRef, itemId, itemDesc) {
	var path = $("#rootPath").val();
	var url = path+"/B_BIL/RP_B_BIL_S05BL.do?idRef="+idRef+"&itemId="+itemId+"&itemDesc="+escape(itemDesc);
	var width = 750;
    var height = 300;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=no" + "," + offsetFeatures;	 	
	var newwindow = window.open(url, null, strFeatures);	
	if (window.focus) { newwindow.focus(); }
}

/**
 * B_BIL_S05 page return call
 * @param idRef
 * @param itemId
 * @param description
 */
function setNotItemDesc(idRef, serviceItemId, description) {
	//new Billing Item
	if(serviceItemId==null || serviceItemId=="") {
		var itemBillDetail = $("#idItemBillDetail").clone();
		itemBillDetail.attr("id", "");
		itemBillDetail.css('display','');
		itemBillDetail.find(".notItemBillDesc").html("<pre>"+description+"</pre>");
		itemBillDetail.find(".serviceItemDesc").val(description);
		itemBillDetail.find(".serviceItemCat").val("0");
		$("#bilDetail_group").append(itemBillDetail);
		
		//change the control id
		var bilDetailInfos = $("#bilDetail_group").find(".itemBillDetail");
		var allBillItemAccount =  0;
		var serviceItemAccount = 0;
		for ( var i = 0; i < bilDetailInfos.length; i++) {
			var level0 = bilDetailInfos.eq(i);
			allBillItemAccount = allBillItemAccount + 1;
			serviceItemAccount = serviceItemAccount + 1;
			//setting sub plan
			var level1s = level0.find(".subPlan");
			for ( var j = 0; j < level1s.length; j++) {
				allBillItemAccount = allBillItemAccount + 1;
			}
		}
		
		itemBillDetail.find(".serviceItemId").val(allBillItemAccount);
		itemBillDetail.find(".serviceIsDisplay").val("0");
		//Refresh is not Billing Item Level 0 name
		notBillItemRef(itemBillDetail, (serviceItemAccount-1));
	} else {
		//edit Biling Item
		var itemIdObj = null;
		var bilDetailInfos = $("#bilDetail_group").find(".itemBillDetail");
		for ( var i = 0; i < bilDetailInfos.length; i++) {
			var itemBillDetail = bilDetailInfos.eq(i);
			var itemIdLoop = itemBillDetail.find(".serviceItemId").val();
			if(itemIdLoop==serviceItemId) {
				itemBillDetail.find(".notItemBillDesc").html("<pre>"+description+"</pre>");
				itemBillDetail.find(".serviceItemDesc").val(description);
				break;
			}
		}
	}
}

/**
 * open B_BIL_S06 page
 * @param itemBillDetail
 */
function openB_BIL_S06(itemBillDetail) {
	var itemId = itemBillDetail.find(".serviceItemId").val();
	var billFrom = itemBillDetail.find(".serviceBillFrom").val();
	var billTo = itemBillDetail.find(".serviceBillTo").val();
	var isDisplay = itemBillDetail.find(".serviceIsDisplay").val();
	var isDisplayMinSvc = itemBillDetail.find(".serviceIsDisplayMinSvc").val();
	var minSvcFrom = itemBillDetail.find(".serviceMinSvcFrom").val();
	var minSvcTo = itemBillDetail.find(".serviceMinSvcTo").val();
	var itemQty = itemBillDetail.find(".serviceItemQty").val();
	var itemUprice = itemBillDetail.find(".serviceItemUprice").val();
	var itemAmt = itemBillDetail.find(".serviceItemAmt").val();
	var minSvcFromDisplay = itemBillDetail.find(".serviceMinSvcFromDisplay").val();
	var minSvcToDisplay = itemBillDetail.find(".serviceMinSvcToDisplay").val();
	//setting sub plan
	var subItemBillDetails = itemBillDetail.find(".subPlan");
	var subPlanLength = subItemBillDetails.length;
	//var gstPercent = $("#hidGstPercent").val();
	
	//#154 Start
	var displayDiscount = itemBillDetail.find(".serviceDisplayDiscount").val();
	var itemDisc = itemBillDetail.find(".serviceItemDisc").val();
	var poNo = itemBillDetail.find(".servicePoNo").val();
	//#154 End
	
	var params = "bilS06Bean.itemId="+itemId+
				 "&bilS06Bean.billFrom="+billFrom+
				 "&bilS06Bean.billTo="+billTo+
				 "&bilS06Bean.isDisplay="+isDisplay+
				 "&bilS06Bean.isDisplayMinSvc="+isDisplayMinSvc+
				 "&bilS06Bean.minSvcFrom="+minSvcFrom+
				 "&bilS06Bean.minSvcTo="+minSvcTo+
				 "&bilS06Bean.itemQty="+itemQty+
				 "&bilS06Bean.itemUprice="+itemUprice+
				 "&bilS06Bean.itemAmt="+itemAmt+
				 "&bilS06Bean.minSvcFromDisplay="+minSvcFromDisplay+
				 "&bilS06Bean.minSvcToDisplay="+minSvcToDisplay+
				 "&bilS06Bean.subPlanLength="+subPlanLength+
				 //"&bilS06Bean.gstPercent="+gstPercent+
				 "&bilS06Bean.displayDiscount="+displayDiscount+
				 "&bilS06Bean.itemDisc="+itemDisc+
				 "&bilS06Bean.poNo="+poNo;
	var path = $("#rootPath").val();
	var url = path+"/B_BIL/RP_B_BIL_S06BL.do?"+params;
	var width = 1126;
    var height = 700;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=no" + "," + offsetFeatures;	 	
	var newwindow = window.open(url, null, strFeatures);	
	if (window.focus) { newwindow.focus(); }
}

/**
 * B_BIL_S06 page after save called
 */
function B_BIL_S06ReturnCalled(itemId){
	$("#trBillPeriod").attr("style", "display:online");
	var billType = $("#billType").val();
	var strNegative = "";
//	if (billType=="CN") {
//		strNegative = "-";
//	}
	var totalAmt = 0;
	var totalGst = 0;
	//var gstPercent = parseFloat($("#hidGstPercent").val());
	var jobModulesDisplayFlg = $("#jobModulesDisplayFlg").val();
	
	var bilDetailInfos = $("#bilDetail_group").find(".itemBillDetail");
	var itemNoIndex = 0;
	// Add #156 Start
	var billCnAmtNegative = $("#billCnAmtNegative").val();
	var negativeDispalyFlg = "0";
	if ("CN"==billType && "1"==billCnAmtNegative) {
		// display credit note in negative amount when billCnAmtNegative equals 1
		negativeDispalyFlg = "1";
	}
	var negativeSign = "-";
	// Add #156 End
	
	// #174 Start
	var billingPeriod = $("#billingPeriod").val();
	// #174 End
	
	for ( var i = 0; i < bilDetailInfos.length; i++) {
		var itemBillDetail = bilDetailInfos.eq(i);
		var itemCat = itemBillDetail.find(".serviceItemCat").val();
		if (itemCat=="1") {
			var loopItemId = itemBillDetail.find(".serviceItemId").val();
			//setting sub plan
			var subItemBillDetails = itemBillDetail.find(".subPlan");
			var serviceIsDisplay = itemBillDetail.find(".serviceIsDisplay").val();
			//var itemAmt = itemBillDetail.find(".serviceItemAmt").val();
			var serviceTotalGst = 0;
			var itemSubTotal = itemBillDetail.find(".serviceItemSubTotal").val();
			
			if(serviceIsDisplay=="1"){
				itemNoIndex = itemNoIndex + 1;
				itemBillDetail.find(".serviceItemIndex").text(itemNoIndex);
				//calculate Total Amount
				//totalAmt = accAdd(totalAmt , itemAmt);
				totalAmt = accAdd(totalAmt , itemSubTotal);
			} else {
				itemBillDetail.find(".serviceItemIndex").text("");
			}
			
			var serviceItemCat = itemBillDetail.find(".serviceItemCat").val();
			//is Contract Period display flag
			var serviceIsDisplayMinSvc = itemBillDetail.find(".serviceIsDisplayMinSvc").val();
			if(serviceIsDisplayMinSvc=="1") {
				itemBillDetail.find(".trMinSvcFormAndTo").attr("style", "display:inline");
			} else {
				itemBillDetail.find(".trMinSvcFormAndTo").attr("style", "display:none");
			}
			
			//calculate GST Amount
			/*var serviceApplyGst = itemBillDetail.find(".serviceApplyGst").val();
			if(serviceApplyGst=="1") {
				totalGst = accAdd(totalGst , calcGst(gstPercent,itemAmt));
			}*/
			
			//update selected item's display
			if(loopItemId==itemId) {
				var serviceIsDisplayMinSvc = itemBillDetail.find(".serviceIsDisplayMinSvc").val();
				var serviceBillFromDisplay = itemBillDetail.find(".serviceBillFromDisplay").val();
				var serviceBillToDisplay = itemBillDetail.find(".serviceBillToDisplay").val();
				var serviceItemDesc = itemBillDetail.find(".serviceItemDesc").val();
				var serviceItemQty = numberFormat(itemBillDetail.find(".serviceItemQty").val(), 0, ",");
				var serviceItemUprice = numberFormat(itemBillDetail.find(".serviceItemUprice").val(), 2, ",");
				var serviceItemAmt = numberFormat(itemBillDetail.find(".serviceItemAmt").val(), 2, ",");
				// #164 start
				var hTaxRate = subItemBillDetails.eq(subItemBillDetails.length - 1).find(".subPlanTaxRate").val();
				itemBillDetail.find(".serviceTaxRate").val(hTaxRate);
				var hApplyGST = subItemBillDetails.eq(subItemBillDetails.length - 1).find(".subPlanApplyGst").val();
				itemBillDetail.find(".serviceApplyGst").val(hApplyGST);
				// #164 end
				var serviceTaxRate = itemBillDetail.find(".serviceTaxRate").val();
				var serviceTaxCode = itemBillDetail.find(".serviceTaxCode").val();
				
				itemBillDetail.find(".divServiceDesc").html("<pre>"+serviceItemDesc+"</pre>");
				if(serviceBillFromDisplay!=serviceBillToDisplay || billingPeriod == "1") {
					itemBillDetail.find("#trBillPeriod").css({"display":"inline"});
				} else {
					itemBillDetail.find("#trBillPeriod").css({"display":"none"});
				}
				itemBillDetail.find(".spanBillFromDisplay").text(serviceBillFromDisplay);
				itemBillDetail.find(".spanBillToDisplay").text(serviceBillToDisplay);
				
				if (serviceIsDisplay=="1") {
					itemBillDetail.find(".divServiceQty").text(serviceItemQty);
					itemBillDetail.find(".divServiceUprice").text(strNegative+serviceItemUprice);
					itemBillDetail.find(".divServiceAmt").text(strNegative+serviceItemAmt);
					// Add #156 Start
					if ("1"==negativeDispalyFlg) {
						itemBillDetail.find(".divServiceUprice").text(negativeSign+serviceItemUprice);
						itemBillDetail.find(".divServiceAmt").text(negativeSign+serviceItemAmt);
					}
					// Add #156 End
										
					//#154 start
					if($(".gstCheck").val() == "TAX_RATE"){
						itemBillDetail.find(".divServiceGST").text(serviceTaxRate+"%");
					}else {
						itemBillDetail.find(".divServiceGST").text(serviceTaxCode);
					}
					//#154 end
				} else {
					itemBillDetail.find(".divServiceQty").text("");
					itemBillDetail.find(".divServiceUprice").text("");
					itemBillDetail.find(".divServiceAmt").text("");
					itemBillDetail.find(".divServiceGST").text("");
				}
				//#154 start
				var serviceItemDisc = itemBillDetail.find(".serviceItemDisc").val();
				var serviceDisplayDiscount = itemBillDetail.find(".serviceDisplayDiscount").val();
				var serviceItemLevel = itemBillDetail.find(".serviceItemLevel").val();
				if(serviceItemDisc == "0.0" || serviceItemDisc == "0.00"){
					serviceItemDisc = "0";
				}
				if(serviceDisplayDiscount == "1" && serviceItemLevel == "0" && serviceItemDisc != "0"){
					itemBillDetail.find("#trServiceDisCount").attr("style", "display:inline");
					itemBillDetail.find(".divServiceItemDisc").text(negativeNumberFormat(serviceItemDisc,2,","));
					if(serviceIsDisplay == "1"){
						if($(".gstCheck").val() == "TAX_RATE"){
							itemBillDetail.find(".divServiceDisCountGST").text(serviceTaxRate+"%");
						}else {
							itemBillDetail.find(".divServiceDisCountGST").text(serviceTaxCode);
						}
					}else {
						itemBillDetail.find(".divServiceDisCountGST").text("");
					}
				}else {
					itemBillDetail.find(".divServiceItemDisc").text("");
					itemBillDetail.find(".divServiceDisCountGST").text("");
					itemBillDetail.find("#trServiceDisCount").attr("style", "display:none");
				}
				//#154 end
				
				for ( var j = 0; j < subItemBillDetails.length; j++) {
					var subItemBillDetail = subItemBillDetails.eq(j);
					var subPlanIsDisplay = subItemBillDetail.find(".subPlanIsDisplay").val();
					var subPlanItemDesc = subItemBillDetail.find(".subPlanItemDesc").val();
					var subPlanItemQty = numberFormat(subItemBillDetail.find(".subPlanItemQty").val(), 0, ",");
					var subPlanItemUprice = numberFormat(subItemBillDetail.find(".subPlanItemUprice").val(), 2, ",");
					var subPlanItemAmt = subItemBillDetail.find(".subPlanItemAmt").val();
					var subPlanItemAmtDisplay = numberFormat(subItemBillDetail.find(".subPlanItemAmt").val(), 2, ",");
					var subPlanJobNo = subItemBillDetail.find(".subPlanJobNo").val();
					var subPlanIsDisplayJobNo = subItemBillDetail.find(".subPlanIsDisplayJobNo").val();
					subItemBillDetail.find(".divSubPlanDesc").html("<pre>"+subPlanItemDesc+"</pre>");
					//#154 start
					var subPlanTaxRate = subItemBillDetail.find(".subPlanTaxRate").val();
					var subPlanTaxCode = subItemBillDetail.find(".subPlanTaxCode").val();
					var subPlanItemSubTotal = subItemBillDetail.find(".subPlanItemSubTotal").val();
					//#154 end
					if (subPlanIsDisplay=="1") {
						itemNoIndex = itemNoIndex + 1;
						subItemBillDetail.find(".subPlanItemIndex").text(itemNoIndex);
						subItemBillDetail.find(".divSubPlanQty").text(subPlanItemQty);
						subItemBillDetail.find(".divSubPlanUprice").text(strNegative+subPlanItemUprice);
						subItemBillDetail.find(".divSubPlanAmt").text(strNegative+subPlanItemAmtDisplay);
						// Add #156 Start
						if ("1"==negativeDispalyFlg) {
							subItemBillDetail.find(".divSubPlanUprice").text(negativeSign+subPlanItemUprice);
							subItemBillDetail.find(".divSubPlanAmt").text(negativeSign+subPlanItemAmtDisplay);
						}
						// Add #156 End
						
						//#154 start						
						//totalAmt = accAdd(totalAmt , subPlanItemAmt);
						totalAmt = accAdd(totalAmt , subPlanItemSubTotal);
						if($(".gstCheck").val() == "TAX_RATE"){
							subItemBillDetail.find(".divGst").text(subPlanTaxRate+"%");
						}else {
							subItemBillDetail.find(".divGst").text(subPlanTaxCode);
						}
						//#154 end
					} else {
						subItemBillDetail.find(".subPlanItemIndex").text("");
						subItemBillDetail.find(".divSubPlanQty").text("");
						subItemBillDetail.find(".divSubPlanUprice").text("");
						subItemBillDetail.find(".divSubPlanAmt").text("");
						subItemBillDetail.find(".divGst").text("");
					}
					//#154 start
					var subPlanItemDisc = subItemBillDetail.find(".subPlanItemDisc").val();
					var subPlanDisplayDiscount = subItemBillDetail.find(".subPlanDisplayDiscount").val();
					var subPlanItemLevel = subItemBillDetail.find(".subPlanItemLevel").val();
					if(subPlanItemDisc == "0.0" || subPlanItemDisc == "0.00"){
						subPlanItemDisc = "0";
					}
					if(subPlanDisplayDiscount == "1" && subPlanItemLevel == "1" && subPlanItemDisc != "0"){
						subItemBillDetail.find("#trSubPlanDisCount").attr("style", "display:inline");
						subItemBillDetail.find(".divSubPlanItemDisc").text(negativeNumberFormat(subPlanItemDisc,2,","));
						if(subPlanIsDisplay == "1"){
							if($(".gstCheck").val() == "TAX_RATE"){
								subItemBillDetail.find(".divSubPlanDisCountGST").text(subPlanTaxRate+"%");
							}else {
								subItemBillDetail.find(".divSubPlanDisCountGST").text(subPlanTaxCode);
							}
						}else {
							subItemBillDetail.find(".divSubPlanDisCountGST").text("");
						}
					}else {
						subItemBillDetail.find(".divSubPlanItemDisc").text("");
						subItemBillDetail.find(".divSubPlanDisCountGST").text("");
						subItemBillDetail.find("#trSubPlanDisCount").attr("style", "display:none");
					}
					//#154 end
					
					if("1"==subPlanIsDisplayJobNo && "1"==jobModulesDisplayFlg) {
						subItemBillDetail.find(".divJob").attr("style", "display:inline;color:#CD853F;");
						subItemBillDetail.find(".divJob").html($("#jobTitleName").val()+"&nbsp;"+subPlanJobNo);
						
						subItemBillDetail.find(".divSubPlanDisplayJobNo").html("&nbsp;");
						subItemBillDetail.find(".divSubPlanDisplayJobNo").attr("style", "display:inline");
					} else {
						subItemBillDetail.find(".divJob").html("");
						subItemBillDetail.find(".divJob").attr("style", "display:none");
						
						subItemBillDetail.find(".divSubPlanDisplayJobNo").html("");
						subItemBillDetail.find(".divSubPlanDisplayJobNo").attr("style", "display:none");
					}
					//calculate GST Amount
					var subPlanApplyGst = subItemBillDetail.find(".subPlanApplyGst").val();
					var subPlanItemGst = subItemBillDetail.find(".subPlanItemGst").val();
					if(subPlanApplyGst=="1") {
						//totalGst = accAdd(totalGst , calcGst(gstPercent,subPlanItemAmt));
						totalGst = accAdd(totalGst , subPlanItemGst);
						serviceTotalGst = accAdd(serviceTotalGst, subPlanItemGst);
					}
				}
			} else {
				// not selected item only update Item No 
				for ( var j = 0; j < subItemBillDetails.length; j++) {
					var subItemBillDetail = subItemBillDetails.eq(j);
					var subPlanIsDisplay = subItemBillDetail.find(".subPlanIsDisplay").val();
					//var subPlanItemAmt = subItemBillDetail.find(".subPlanItemAmt").val();
					var subPlanItemSubTotal = subItemBillDetail.find(".subPlanItemSubTotal").val();
					var subPlanItemGst = subItemBillDetail.find(".subPlanItemGst").val();
					if(subPlanIsDisplay == "1") {
						itemNoIndex = itemNoIndex + 1;
						subItemBillDetail.find(".subPlanItemIndex").text(itemNoIndex);
						
						//totalAmt = accAdd(totalAmt , subPlanItemAmt);
						totalAmt = accAdd(totalAmt , subPlanItemSubTotal);
					} else {
						subItemBillDetail.find(".subPlanItemIndex").text("");
					}
					//calculate GST Amount
					var subPlanApplyGst = subItemBillDetail.find(".subPlanApplyGst").val();
					if(subPlanApplyGst=="1") {
						//totalGst = accAdd(totalGst , calcGst(gstPercent,subPlanItemAmt));
						totalGst = accAdd(totalGst , subPlanItemGst);
						serviceTotalGst = accAdd(serviceTotalGst, subPlanItemGst);
					}
				}
			}
			itemBillDetail.find(".serviceItemGst").val(serviceTotalGst);
			itemBillDetail.find(".serviceItemExportGST").val(serviceTotalGst);
		}
		
	}
	
	//SUB TOTAL 
	totalAmt = totalAmt.toFixed(2);
	var subTotalDisplay = numberFormat(totalAmt,2,",");
	$("#subTotal").text(subTotalDisplay);
	$("#subTotalAmt").val(totalAmt);
	
	//SUB TOTAL 
	totalGst = totalGst.toFixed(2);
	var gstAmtDisplay = numberFormat(totalGst,2,",");
	$("#gstAmountDisplay").text(gstAmtDisplay);
	$("#gstAmount").val(totalGst);
	
	//change GST Percent value
	changeGstPercentValue();
	
	//GRAND TOTAL
	var billAmount = accAdd(totalAmt,totalGst);
	$("#billAmountDisplay").text(numberFormat(billAmount,2,","));
	$("#billAmount").val(billAmount);
	var billAmountToWord = nbrToEngWord($("#billAmountDisplay").text(),",",".","");
	$('#billAmountToStr').text(billAmountToWord);
	// Add #156 Start
	if ("1"==negativeDispalyFlg) {
		$("#subTotal").text(negativeSign+subTotalDisplay);
		$("#gstAmountDisplay").text(negativeSign+gstAmtDisplay);
		$("#billAmountDisplay").text(negativeSign+numberFormat(billAmount,2,","));
	}
	// Add #156 End
	
	getExportAmount();
}

//B_BIL_S06 page selected Service object
var bilS06selectedItemService = null;

/**
 * B_BIL_S06 page called set service value
 * @param serviceObj
 */
function setServiceValue(serviceObj){
	var bilDetailInfos = $("#bilDetail_group").find(".itemBillDetail");
	var itemNoIndex = 0;
	for ( var i = 0; i < bilDetailInfos.length; i++) {
		var loopItemBillDetail = bilDetailInfos.eq(i);
		var loopItemId = loopItemBillDetail.find(".serviceItemId").val();
		if(loopItemId==serviceObj.itemId) {
			bilS06selectedItemService = loopItemBillDetail;
			break;
		}
	}
	bilS06selectedItemService.find(".serviceBillFrom").val(serviceObj.serviceBillFrom);
	bilS06selectedItemService.find(".serviceBillTo").val(serviceObj.serviceBillTo);
	bilS06selectedItemService.find(".serviceBillFromDisplay").val(serviceObj.enServiceBillFrom);
	bilS06selectedItemService.find(".serviceBillToDisplay").val(serviceObj.enServiceBillTo);
	bilS06selectedItemService.find(".serviceIsDisplay").val(serviceObj.serviceIsDisplay);
	bilS06selectedItemService.find(".serviceItemDesc").val(serviceObj.serviceItemDesc);
	bilS06selectedItemService.find(".serviceIsDisplayMinSvc").val(serviceObj.serviceIsDisplayMinSvc);
	bilS06selectedItemService.find(".serviceItemUprice").val(serviceObj.serviceUprice);
	bilS06selectedItemService.find(".serviceItemAmt").val(serviceObj.serviceAmt);
	//#154 start
	bilS06selectedItemService.find(".serviceItemDisc").val(serviceObj.serviceItemDisc);
	var serviceAmt = parseFloat(serviceObj.serviceAmt.replace(/,/g, ""));
	var serviceDisc = parseFloat(serviceObj.serviceItemDisc.replace(/,/g, ""));
	bilS06selectedItemService.find(".serviceItemSubTotal").val(accAdd(serviceAmt,serviceDisc));
	bilS06selectedItemService.find(".serviceItemExportAmt").val(accAdd(serviceAmt,serviceDisc));
	bilS06selectedItemService.find(".servicePoNo").val(serviceObj.servicePoNo);
	bilS06selectedItemService.find(".serviceDisplayDiscount").val(serviceObj.serviceDisplayDiscount);
	//#154 end
}

/**
 * B_BIL_S06 page Called set sub plan value
 * @param index
 * @param subPlanObj
 */
function setSubPlanValue(index,subPlanObj){
	var subItemBillDetail = bilS06selectedItemService.find(".subPlan").eq(index);
	subItemBillDetail.find(".subPlanBillFrom").val(subPlanObj.serviceBillFrom);
	subItemBillDetail.find(".subPlanBillTo").val(subPlanObj.serviceBillTo);
	if ("1"==subPlanObj.serviceIsDisplay) {
		subItemBillDetail.find(".subPlanIsDisplay").val("0");
	} else {
		subItemBillDetail.find(".subPlanIsDisplay").val("1");
	}
	subItemBillDetail.find(".subPlanBillFromDisplay").val(subPlanObj.enServiceBillFrom);
	subItemBillDetail.find(".subPlanBillToDisplay").val(subPlanObj.enServiceBillTo);
	
	//set parent sub plan value to current page
	subItemBillDetail.find(".subPlanItemDesc").val(subPlanObj.subPlanItemDesc);
	subItemBillDetail.find(".subPlanItemQty").val(subPlanObj.subPlanItemQty.replace(/,/g, ""));
	subItemBillDetail.find(".subPlanItemUprice").val(subPlanObj.subPlanItemUprice.replace(/,/g, ""));
	subItemBillDetail.find(".subPlanItemAmt").val(subPlanObj.subPlanItemAmt.replace(/,/g, ""));
	//subItemBillDetail.find(".subPlanItemGst").val(subPlanObj.subPlanItemGst);
	if(subPlanObj.jobModulesDisplayFlg=="1") {
		subItemBillDetail.find(".subPlanJobNo").val(subPlanObj.subPlanJobNo);
		subItemBillDetail.find(".subPlanIsDisplayJobNo").val(subPlanObj.subPlanIsDisplayJobNo);
	}
	//#154 start
	var subPlanAmt = parseFloat(subPlanObj.subPlanItemAmt.replace(/,/g, ""));
	var subPlanDisc = parseFloat(subPlanObj.subPlanItemDisc.replace(/,/g, ""));
	subItemBillDetail.find(".subPlanTaxRate").val(subPlanObj.subPlanTaxRate);
	subItemBillDetail.find(".subPlanTaxCode").val(subPlanObj.subPlanTaxCode);
	subItemBillDetail.find(".subPlanApplyGst").val(subPlanObj.subPlanApplyGst);
	subItemBillDetail.find(".subPlanItemDisc").val(subPlanDisc);
	subItemBillDetail.find(".subPlanItemSubTotal").val(accAdd(subPlanAmt,subPlanDisc));
	subItemBillDetail.find(".subPlanItemExportAmt").val(accAdd(subPlanAmt,subPlanDisc));
	var subPlanItemSubTotal = subItemBillDetail.find(".subPlanItemSubTotal").val();
	if($("#billCurrency").val() == "JPY"){
		subItemBillDetail.find(".subPlanItemGst").val(calcGst(subPlanObj.subPlanTaxRate,subPlanItemSubTotal));
	}else {
		subItemBillDetail.find(".subPlanItemGst").val(calcGst(subPlanObj.subPlanTaxRate,subPlanItemSubTotal));
	}
	subItemBillDetail.find(".subPlanItemExportGST").val(subItemBillDetail.find(".subPlanItemGst").val());
	subItemBillDetail.find(".subPlanDisplayDiscount").val(subPlanObj.subPlanDisplayDiscount);
	subItemBillDetail.find(".subPlanPoNo").val(subPlanObj.servicePoNo);
	
	$(".gstCheck").val(subPlanObj.gstCheck);
	//#154 end
}

var initSelectedService = null;
/**
 * B_BIL_S06 page init service value call
 * @param currentItemId
 * @returns
 */
function bilS06InitServiceCall(currentItemId){
	var bilDetailInfos = $("#bilDetail_group").find(".itemBillDetail");
	if(bilDetailInfos==null || bilDetailInfos==undefined || bilDetailInfos.length==0) {
		return null;
	} else {
		for ( var i = 0; i < bilDetailInfos.length; i++) {
			var itemBillDetailInfo = bilDetailInfos.eq(i);
			var itemId = itemBillDetailInfo.find(".serviceItemId").val();
			if(currentItemId == itemId) {
				initSelectedService = itemBillDetailInfo;
				break;
			}
		}
		if (initSelectedService==null) {
			return null;
		} else {
			//parent page Service Item Desc
			var serviceItemDesc = initSelectedService.find(".serviceItemDesc").val();
			var serviceObj = new Object();
			serviceObj.serviceItemDesc = serviceItemDesc;
			serviceObj.gstCheck = $(".gstCheck").val();
			return serviceObj;
		}
	}
}

/**
 * B_BIL_S06 page init sub plan value call
 * @param index
 */
function bilS06InitSubPlanCall(index){
	//get parent page sub plan
	var subItemBillDetail = initSelectedService.find(".subPlan").eq(index);
	//show job modules flag 0:not display,1:display
	var jobModulesDisplayFlg = $(".jobModulesDisplayFlg").val();

	if(subItemBillDetail==null || subItemBillDetail==undefined) {
		return null;
	} else {
		//get Parent sub plan value
		var subPlanItemDesc = subItemBillDetail.find(".subPlanItemDesc").val();
		var subPlanItemQty = subItemBillDetail.find(".subPlanItemQty").val();
		var subPlanItemUprice = subItemBillDetail.find(".subPlanItemUprice").val();
		var subPlanItemAmt = subItemBillDetail.find(".subPlanItemAmt").val();
		var subPlanApplyGst = subItemBillDetail.find(".subPlanApplyGst").val();
		var subPlanJobNo = subItemBillDetail.find(".subPlanJobNo").val();
		var subPlanIsDisplayJobNo = subItemBillDetail.find(".subPlanIsDisplayJobNo").val();
		var subPlanItemGst = subItemBillDetail.find(".subPlanItemGst").val();
		var subPlanItemDisc = subItemBillDetail.find(".subPlanItemDisc").val();
		
		var subPlanObj = new Object();
		subPlanObj.subPlanItemDesc = subPlanItemDesc;
		subPlanObj.subPlanItemQty = subPlanItemQty;
		subPlanObj.subPlanItemUprice = subPlanItemUprice;
		subPlanObj.subPlanItemAmt = subPlanItemAmt;
		subPlanObj.subPlanItemGst = subPlanItemGst;
		subPlanObj.subPlanApplyGst = subPlanApplyGst;
		subPlanObj.subPlanJobNo = subPlanJobNo;
		subPlanObj.subPlanIsDisplayJobNo = subPlanIsDisplayJobNo;
		subPlanObj.subPlanItemDisc = subPlanItemDisc;

		return subPlanObj;
	}
}

/**
 * click delete Billing item
 * @param obj
 */
function removeBillItem(obj){
	var isConfirm = (msg.POPCFM($("#message_group").find(".messageRemoveBillItem").text()) == msg.YES_CONFIRM);
	if (isConfirm) {
		var link = $(obj);
		var itemBillDetail = link.closest("div.itemBillDetail");
		itemBillDetail.remove();
		refreshBillItem();
	}
}

/**
 * click save button
 */
function save(){
	//the last selected is Cust Name combox,then clear data
	if($('#changeTypeFlag').val()=="changeCustName"){
		$('#changeTypeFlag').val("");
	}
}

function curRateChange(obj){
	var curRate = $(obj).val();
	validateNumber(obj);
	
	getExportAmount();
}

/**
 * refresh Biling Item info
 */
function refreshBillItem() {
	var bilDetailInfos = $("#bilDetail_group").find(".itemBillDetail");
	var itemIdIndex = 0;
	var itemNoIndex = 0;
	
	//var totalAmt = 0;
	var totalGst = 0;
	//var gstPercent = parseFloat($("#hidGstPercent").val());
	var totalSubTotal = 0;
	// Add #156 Start
	var billType = $("#billType").val();
	var billCnAmtNegative = $("#billCnAmtNegative").val();
	var negativeDispalyFlg = "0";
	if ("CN"==billType && "1"==billCnAmtNegative) {
		// display credit note in negative amount when billCnAmtNegative equals 1
		negativeDispalyFlg = "1";
	}
	var negativeSign = "-";
	// Add #156 End
	for ( var i = 0; i < bilDetailInfos.length; i++) {
		itemIdIndex = itemIdIndex + 1;
		var itemBillDetail = bilDetailInfos.eq(i);
		var itemCat = itemBillDetail.find(".serviceItemCat").val();
		itemBillDetail.find(".serviceItemId").val(itemIdIndex);
		if (itemCat=="0") {
			//Refresh is not Billing Item Level 0 name
			billItemRefLevel0Name(itemBillDetail, i);
		} else {
			//Refresh is Billing Item Level 0 name
			billItemRefLevel0Name(itemBillDetail, i);
			var serviceIsDisplay = itemBillDetail.find(".serviceIsDisplay").val();
			var serviceTaxRate = itemBillDetail.find(".serviceTaxRate").val();
			var serviceTaxCode = itemBillDetail.find(".serviceTaxCode").val();
			//is Contract Period display flag
			var serviceIsDisplayMinSvc = itemBillDetail.find(".serviceIsDisplayMinSvc").val();
			if(serviceIsDisplayMinSvc=="1") {
				itemBillDetail.find(".trMinSvcFormAndTo").attr("style", "display:inline");
			} else {
				itemBillDetail.find(".trMinSvcFormAndTo").attr("style", "display:none");
			}
			
			var itemSubTotal = itemBillDetail.find(".serviceItemSubTotal").val();
			if(serviceIsDisplay == "1") {
				itemNoIndex = itemNoIndex + 1;
				itemBillDetail.find(".serviceItemIndex").html(itemNoIndex);
				
				//calculate Total Amount
				//var itemAmt = itemBillDetail.find(".serviceItemAmt").val();
				//totalAmt = accAdd(totalAmt , itemAmt);
				// Add #156 Start
				if ("1"==negativeDispalyFlg) {
					var serviceItemUprice = itemBillDetail.find(".divServiceUprice").text();
					var serviceItemAmt = itemBillDetail.find(".divServiceAmt").text();
					if (serviceItemUprice.indexOf(negativeSign) < 0 ) {
						itemBillDetail.find(".divServiceUprice").text(negativeSign+serviceItemUprice);
						itemBillDetail.find(".divServiceAmt").text(negativeSign+serviceItemAmt);
					}
				}
				// Add #156 End
				totalSubTotal = accAdd(totalSubTotal,itemSubTotal);
				if($(".gstCheck").val() == "TAX_RATE"){
					itemBillDetail.find(".divServiceGST").text(serviceTaxRate+"%");
				}else {
					itemBillDetail.find(".divServiceGST").text(serviceTaxCode);
				}
			}else {
				itemBillDetail.find(".divServiceGST").text("");
			}
			//#181 Start
			/*//calculate GST Amount
			var applyGst = itemBillDetail.find(".serviceApplyGst").val();
			if(applyGst=="1") {
				//var itemAmt = itemBillDetail.find(".serviceItemAmt").val();
				//totalGst = accAdd(totalGst , calcGst(serviceTaxRate,itemAmt));
				var itemGst = itemBillDetail.find(".serviceItemGst").val();
				totalGst = accAdd(totalGst , itemGst);
			}*/
			//#181 End
			//#154 start
			var serviceItemDisc = itemBillDetail.find(".serviceItemDisc").val();
			var serviceDisplayDiscount = itemBillDetail.find(".serviceDisplayDiscount").val();
			var serviceItemLevel = itemBillDetail.find(".serviceItemLevel").val();
			
			if(serviceItemDisc == "0.0" || serviceItemDisc == "0.00"){
				serviceItemDisc = "0";
			}
			if(serviceDisplayDiscount == "1" && serviceItemLevel == "0" && serviceItemDisc != "0"){
				itemBillDetail.find("#trServiceDisCount").attr("style", "display:inline");
				itemBillDetail.find(".divServiceItemDisc").text(negativeNumberFormat(serviceItemDisc,2,","));
				if(serviceIsDisplay == "1"){
					if($(".gstCheck").val() == "TAX_RATE"){
						itemBillDetail.find(".divServiceDisCountGST").text(serviceTaxRate+"%");
					}else {
						itemBillDetail.find(".divServiceDisCountGST").text(serviceTaxCode);
					}
				}else {
					itemBillDetail.find(".divServiceDisCountGST").text("");
				}
			}else {
				itemBillDetail.find(".divServiceItemDisc").text("");
				itemBillDetail.find(".divServiceDisCountGST").text("");
				itemBillDetail.find("#trServiceDisCount").attr("style", "display:none");
			}
			//#154 end
			//setting sub plan
			var subItemBillDetails = itemBillDetail.find(".subPlan");
			for ( var j = 0; j < subItemBillDetails.length; j++) {
				var subItemBillDetail = subItemBillDetails.eq(j);
				itemIdIndex = itemIdIndex + 1;
				subItemBillDetail.find(".subPlanItemId").val(itemIdIndex);
				//Refresh is Billing Item Level 1 name
				billItemRefLevel1Name(subItemBillDetail, i, j);
				
				// #154 start
				var subPlanItemDisc = subItemBillDetail.find(".subPlanItemDisc").val();
				var subPlanDisplayDiscount = subItemBillDetail.find(".subPlanDisplayDiscount").val();
				var subPlanItemLevel = subItemBillDetail.find(".subPlanItemLevel").val();
				var subPlanTaxRate = itemBillDetail.find(".subPlanTaxRate").val();
				var subPlanTaxCode = itemBillDetail.find(".subPlanTaxCode").val();
				var subPlanIsDisplay = itemBillDetail.find(".subPlanIsDisplay").val();
				if(subPlanItemDisc == "0.0" || subPlanItemDisc == "0.00"){
					subPlanItemDisc = "0";
				}
				if(subPlanDisplayDiscount == "1" && subPlanItemLevel == "1" && subPlanItemDisc != "0"){
					subItemBillDetail.find("#trSubPlanDisCount").attr("style", "display:inline");
					subItemBillDetail.find(".divSubPlanItemDisc").text(negativeNumberFormat(subPlanItemDisc,2,","));
					if(subPlanIsDisplay == "1"){
						if($(".gstCheck").val() == "TAX_RATE"){
							subItemBillDetail.find(".divSubPlanDisCountGST").text(subPlanTaxRate+"%");
						}else {
							subItemBillDetail.find(".divSubPlanDisCountGST").text(subPlanTaxCode);
						}
					}else {
						subItemBillDetail.find(".divSubPlanDisCountGST").text("");
					}
				}else {
					subItemBillDetail.find(".divSubPlanItemDisc").text("");
					subItemBillDetail.find(".divSubPlanDisCountGST").text("");
					subItemBillDetail.find("#trSubPlanDisCount").attr("style", "display:none");
				}
				//#154 end
				
				//calculate Total Amount
				//var itemAmt = subItemBillDetail.find(".subPlanItemAmt").val();
				//subItemBillDetail.find(".subPlanItemGst").val(calcGst(subPlanTaxRate, itemSubTotal));
				var itemSubTotal = subItemBillDetail.find(".subPlanItemSubTotal").val();
				
				var subPlanIsDisplay = subItemBillDetail.find(".subPlanIsDisplay").val();
				if(subPlanIsDisplay == "1") {
					itemNoIndex = itemNoIndex + 1;
					subItemBillDetail.find(".subPlanItemIndex").html(itemNoIndex);
					// Add #156 Start
					if ("1"==negativeDispalyFlg) {
						var subPlanItemUprice = subItemBillDetail.find(".divSubPlanUprice").text();
						var subPlanItemAmtDisplay = subItemBillDetail.find(".divSubPlanAmt").text();
						if (subPlanItemUprice.indexOf(negativeSign) < 0 ) {
							subItemBillDetail.find(".divSubPlanUprice").text(negativeSign+subPlanItemUprice);
							subItemBillDetail.find(".divSubPlanAmt").text(negativeSign+subPlanItemAmtDisplay);
						}
					}
					// Add #156 End
					
					//totalAmt = accAdd(totalAmt , itemAmt);
					totalSubTotal = accAdd(totalSubTotal,itemSubTotal);
				}
				//calculate GST Amount
				var applyGst = subItemBillDetail.find(".subPlanApplyGst").val();
				var itemGst = subItemBillDetail.find(".subPlanItemGst").val();
				if(applyGst=="1") {
					//totalGst = accAdd(totalGst , calcGst(gstPercent,itemAmt));
					totalGst = accAdd(totalGst , itemGst);
				}
				
			}
		}
	}
	
	//SUB TOTAL 
	//totalAmt = totalAmt.toFixed(2);
	// subTotalDisplay = numberFormat(totalAmt,2,",");
	//$("#subTotal").text(subTotalDisplay);
	//$("#subTotalAmt").val(totalAmt);
	totalSubTotal = totalSubTotal.toFixed(2);
	var totalSubTotalDisplay = numberFormat(totalSubTotal,2,",");
	$("#subTotal").text(totalSubTotalDisplay);
	$("#subTotalAmt").val(totalSubTotal);
	
	//SUB TOTAL 
	//totalGst = totalGst.toFixed(2);
	var gstAmtDisplay = numberFormat(totalGst,2,",");
	$("#gstAmountDisplay").text(gstAmtDisplay);
	$("#gstAmount").val(totalGst);
	
	//change GST Percent value
	changeGstPercentValue();
	
	//GRAND TOTAL
	var billAmount = accAdd(totalSubTotal,totalGst);
	$("#billAmountDisplay").text(numberFormat(billAmount,2,","));
	$("#billAmount").val(billAmount);
	var billAmountToWord = nbrToEngWord($("#billAmountDisplay").text(),",",".","");
	$('#billAmountToStr').text(billAmountToWord);
	// Add #156 Start
	if ("1"==negativeDispalyFlg) {
		$("#subTotal").text(negativeSign+totalSubTotalDisplay);
		$("#gstAmountDisplay").text(negativeSign+gstAmtDisplay);
		$("#billAmountDisplay").text(negativeSign+numberFormat(billAmount,2,","));
	}
	// Add #156 End
	
	getExportAmount();
}

/**
 * change GST Percent value
 */
function changeGstPercentValue(){
	var gstAmountDisplay = $("#gstAmountDisplay").text();
	if (gstAmountDisplay==0.00) {
		$("#spanGstPercent").text("0");
		$("#gstPercent").val("0");
	} else {
		var hidGstPercent = $("#hidGstPercent").val();
		$("#spanGstPercent").text(hidGstPercent);
		$("#gstPercent").val(hidGstPercent);
	}
}

function openB_CPM_View(url) {
	var width = window.screen.width*90/100;
    var height = window.screen.height*80/100;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;	 	
	var newwindow = window.open(url, null, strFeatures);
	if (window.focus) { newwindow.focus(); }
}

/**
 * Refresh is not Billing Item Level 0 name
 * @param itemBillDetail
 * @param i
 */
function notBillItemRef(itemBillDetail, i) {
	itemBillDetail.find(".serviceItemDesc").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemDesc");
	itemBillDetail.find(".serviceItemCat").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemCat");
	itemBillDetail.find(".serviceItemId").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemId");
	itemBillDetail.find(".serviceIsDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].isDisplay");
}

/**
 * Refresh is Billing Item Level 0 name
 * @param itemBillDetail
 * @param i
 */
function billItemRefLevel0Name(itemBillDetail, i) {
	itemBillDetail.find(".serviceItemId").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemId");
	itemBillDetail.find(".serviceItemCat").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemCat");
	itemBillDetail.find(".serviceItemLevel").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemLevel");
	itemBillDetail.find(".serviceItemNo").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemNo");
	itemBillDetail.find(".serviceItemDesc").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemDesc");
	itemBillDetail.find(".serviceItemQty").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemQty");
	itemBillDetail.find(".serviceItemUprice").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemUprice");
	itemBillDetail.find(".serviceItemAmt").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemAmt");
	itemBillDetail.find(".serviceItemGst").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemGst");
	itemBillDetail.find(".serviceItemExportAmt").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemExportAmt");
	itemBillDetail.find(".serviceApplyGst").attr("name","bilHeaderInfo.bilDetail[" + i + "].applyGst");
	itemBillDetail.find(".serviceIsDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].isDisplay");
	itemBillDetail.find(".serviceIdCustPlan").attr("name","bilHeaderInfo.bilDetail[" + i + "].idCustPlan");
	itemBillDetail.find(".serviceIdCustPlanGrp").attr("name","bilHeaderInfo.bilDetail[" + i + "].idCustPlanGrp");
	itemBillDetail.find(".serviceIdCustPlanLink").attr("name","bilHeaderInfo.bilDetail[" + i + "].idCustPlanLink");
	itemBillDetail.find(".serviceItemType").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemType");
	itemBillDetail.find(".serviceSvcLevel1").attr("name","bilHeaderInfo.bilDetail[" + i + "].svcLevel1");
	itemBillDetail.find(".serviceSvcLevel2").attr("name","bilHeaderInfo.bilDetail[" + i + "].svcLevel2");
	itemBillDetail.find(".serviceBillFrom").attr("name","bilHeaderInfo.bilDetail[" + i + "].billFrom");
	itemBillDetail.find(".serviceBillTo").attr("name","bilHeaderInfo.bilDetail[" + i + "].billTo");
	itemBillDetail.find(".serviceJobNo").attr("name","bilHeaderInfo.bilDetail[" + i + "].jobNo");
	itemBillDetail.find(".serviceIsDisplayMinSvc").attr("name","bilHeaderInfo.bilDetail[" + i + "].isDisplayMinSvc");
	itemBillDetail.find(".serviceMinSvcFrom").attr("name","bilHeaderInfo.bilDetail[" + i + "].minSvcFrom");
	itemBillDetail.find(".serviceMinSvcTo").attr("name","bilHeaderInfo.bilDetail[" + i + "].minSvcTo");
	itemBillDetail.find(".serviceBillFromDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].billFromDisplay");
	itemBillDetail.find(".serviceBillToDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].billToDisplay");
	itemBillDetail.find(".serviceMinSvcFromDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].minSvcFromDisplay");
	itemBillDetail.find(".serviceMinSvcToDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].minSvcToDisplay");
	
	//#154 start
	itemBillDetail.find(".serviceItemDisc").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemDisc");
	itemBillDetail.find(".serviceItemSubTotal").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemSubTotal");
	itemBillDetail.find(".serviceTaxCode").attr("name","bilHeaderInfo.bilDetail[" + i + "].taxCode");
	itemBillDetail.find(".serviceTaxRate").attr("name","bilHeaderInfo.bilDetail[" + i + "].taxRate");
	itemBillDetail.find(".serviceItemExportGST").attr("name","bilHeaderInfo.bilDetail[" + i + "].itemExportGST");
	itemBillDetail.find(".servicePoNo").attr("name","bilHeaderInfo.bilDetail[" + i + "].poNo");
	itemBillDetail.find(".serviceDisplayDiscount").attr("name","bilHeaderInfo.bilDetail[" + i + "].displayDiscount");
	//#154 end
}

/**
 * Refresh is Billing Item Level 1 name
 * @param subItemBillDetail
 * @param i
 * @param j
 */
function billItemRefLevel1Name(subItemBillDetail, i, j) {
	subItemBillDetail.find(".subPlanItemId").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemId");
	subItemBillDetail.find(".subPlanItemCat").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemCat");
	subItemBillDetail.find(".subPlanItemLevel").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemLevel");
	subItemBillDetail.find(".subPlanItemNo").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemNo");
	subItemBillDetail.find(".subPlanItemDesc").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemDesc");
	subItemBillDetail.find(".subPlanItemQty").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemQty");
	subItemBillDetail.find(".subPlanItemUprice").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemUprice");
	subItemBillDetail.find(".subPlanItemAmt").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemAmt");
	subItemBillDetail.find(".subPlanItemGst").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemGst");
	subItemBillDetail.find(".subPlanItemExportAmt").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemExportAmt");
	subItemBillDetail.find(".subPlanApplyGst").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].applyGst");
	subItemBillDetail.find(".subPlanIsDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].isDisplay");
	subItemBillDetail.find(".subPlanIdCustPlan").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].idCustPlan");
	subItemBillDetail.find(".subPlanIdCustPlanGrp").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].idCustPlanGrp");
	subItemBillDetail.find(".subPlanIdCustPlanLink").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].idCustPlanLink");
	subItemBillDetail.find(".subPlanItemType").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemType");
	subItemBillDetail.find(".subPlanSvcLevel1").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].svcLevel1");
	subItemBillDetail.find(".subPlanSvcLevel2").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].svcLevel2");
	subItemBillDetail.find(".subPlanBillFrom").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].billFrom");
	subItemBillDetail.find(".subPlanBillTo").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].billTo");
	subItemBillDetail.find(".subPlanJobNo").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].jobNo");
	subItemBillDetail.find(".subPlanIsDisplayJobNo").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].isDisplayJobNo");
	subItemBillDetail.find(".subPlanIsDisplayMinSvc").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].isDisplayMinSvc");
	subItemBillDetail.find(".subPlanMinSvcFrom").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].minSvcFrom");
	subItemBillDetail.find(".subPlanMinSvcTo").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].minSvcTo");
	subItemBillDetail.find(".subPlanBillFromDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].billFromDisplay");
	subItemBillDetail.find(".subPlanBillToDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].billToDisplay");
	subItemBillDetail.find(".subPlanMinSvcFromDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].minSvcFromDisplay");
	subItemBillDetail.find(".subPlanMinSvcToDisplay").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].minSvcToDisplay");
	
	//#154 start
	subItemBillDetail.find(".subPlanItemDisc").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemDisc");
	subItemBillDetail.find(".subPlanItemSubTotal").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemSubTotal");
	subItemBillDetail.find(".subPlanTaxCode").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].taxCode");
	subItemBillDetail.find(".subPlanTaxRate").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].taxRate");
	subItemBillDetail.find(".subPlanItemExportGST").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].itemExportGST");
	subItemBillDetail.find(".subPlanPoNo").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].poNo");
	subItemBillDetail.find(".subPlanDisplayDiscount").attr("name","bilHeaderInfo.bilDetail[" + i + "].subPlanBean["+j+"].displayDiscount");
	//#154 end
}

/**
 * Multiplication
 * @param arg1
 * @param arg2
 * @returns {Number}
 */
function accMul(arg1,arg2){ 
	var m=0;
	var s1=arg1.toString();
	var s2=arg2.toString(); 
	try{m+=s1.split(".")[1].length;}catch(e){};
	try{m+=s2.split(".")[1].length;}catch(e){} ;
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}

/**
 * Addition
 * @param arg1
 * @param arg2
 * @returns {Number}
 */
function accAdd(arg1,arg2){ 
	var r1,r2,m; 
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;};
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;};
	m=Math.pow(10,Math.max(r1,r2));
	return (arg1*m+arg2*m)/m;
}

/**
 * Auto Calculation of GST amount
 * @param arg1
 * @param arg2
 * @returns
 */
function calcGst(arg1,arg2){ 
	var gstCalcAmt;
	gstCalcAmt = accMul((arg1/100),arg2);
	if($("#billCurrency").val() == "JPY"){
		return numberFormat(gstCalcAmt,0,",").replace(/,/g, "");
	}else {
		return numberFormat(gstCalcAmt,2,",").replace(/,/g, "");
	}
}

/**
 * number formate(000000.00-->000,000.00)
 * @param value
 * @param decimalValue
 * @param splitChar
 * @returns {Number}
 */
function numberFormat(value, decimalValue, splitChar) {
	var power = 1;
	for ( var i = 0; i < decimalValue; i++) {
		power *= 10;
	}
	var newValue = parseFloat(Math.round(value * power)) / power;
	newValue = addCommas(newValue, decimalValue, ",");
	return newValue;
}

/**
 * negativeNumber formate(000000.00-->000,000.00)
 * @param value
 * @param decimalValue
 * @param splitChar
 * @returns {Number}
 */
function negativeNumberFormat(value, decimalValue, splitChar) {
	var nv = value.toString();
	var isFlg = false;
	if(nv.indexOf("-") >= 0){
		nv = nv.split('-')[1];
		isFlg = true;
	}
	
	var power = 1;
	for ( var i = 0; i < decimalValue; i++) {
		power *= 10;
	}
	var newValue = parseFloat(Math.round(nv * power)) / power;
	newValue = addCommas(newValue, decimalValue, ",");
	// Add #156 comment7 Start
	var billType = $("#billType").val();
	var billCnAmtNegative = $("#billCnAmtNegative").val();
	if(isFlg == true && "CN" != billType && "1" != billCnAmtNegative){
		newValue="-"+newValue;
	}
	// Add #156 comment7 end
	return newValue;
}

function addCommas(nStr, length, splitChar)
{
	nStr += '';
	x = nStr.split('.');
	x1 = x[0] + "";
	//calculate decimal number
	x[1] = paddingDecimal(x[1], length);
	x2 = x[1].length > 1 ? '.' + x[1] : '';
	/*
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, splitChar);
	}
	*/
	//format number
	var commaNo = parseInt((x1.length - 1)/ 3);
	var x1Temp = "";
	var count = 0;
	for ( var i = x1.length - 1; i > -1; i--) {
		if (commaNo > 0) {
			x1Temp = x1.charAt(i) + x1Temp;
			count++;
			if (count != 0 && count % 3 == 0) {
				x1Temp = splitChar + x1Temp;
				commaNo--;
			}
		} else {
			x1Temp = x1.charAt(i) + x1Temp;
		}
	}
	x1 = x1Temp;
	return (x1 + "" + x2);
}

/**
 * 
 * @param decimalNumber
 * @param length
 * @return
 */
function paddingDecimal(decimalNumber, length) {
	if (decimalNumber == undefined || length == 0) {
		decimalNumber = "";
	}
	decimalNumber = "" + decimalNumber;
	
	while(decimalNumber.length != length) {
		if (decimalNumber.length > length) {
			//remove end of number
			decimalNumber = decimalNumber.substring(0, decimalNumber.length - 1);
		} else {
			//add 0 after number
			decimalNumber += "0";
		}	
	}
	return decimalNumber;
}

function validateNumber(obj){
	if (/^\.?$/.test($(obj).val()) || !/^-?\d*\.?\d*$/.test($(obj).val())) {
		new messageBox().POPALT('This field requires a Number'); 
		$(obj).val("0");
		return false;
	};
	return true;
}

/**
 * only input Dec Number
 * @param evt
 * @returns {Boolean}
 */
function onlyDecNumbers(obj,evt) {
	var e = evt;
	if(window.event){ // IE
		var charCode = e.keyCode;
	} else if (e.which) { // Safari 4, Firefox 3.0.4
		var charCode = e.which;
	}
	if (charCode > 31 && (charCode < 48 || charCode > 57)){
		if(charCode == 46){// available for . 
			return true;
		}else{
			return false;
		}
	}else{
		if(48<=charCode&&57>=charCode){
			return decbit(obj);
		}
		else{
			return true;
		}
	}
}

function decbit(obj){
	var cursurPosition = -1;  
	if (obj.selectionStart) {//!IE
		cursurPosition = obj.selectionStart;
	}  
	else {//IE   
		var range = document.selection.createRange();  
		range.moveStart("character", -obj.value.length);  
		cursurPosition = range.text.length;  
	}
	var value = $.trim(obj.value);
	var arr = value.split(".");
	if(arr.length>1){
		if(arr[0].length>=4&&cursurPosition<=arr[0].length){
			return false;
		}
		if(arr[1].length>=8&&cursurPosition>arr[0].length){
			return false;
		}
	}
	else{
		if(arr[0].length>=4){
			return false;
		}
	}
	return true;
}
function ClickExit(){	
	var MsgBox = new messageBox();
	if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
		var url=$("#rootPath").val()+"";
		if($('#newExit').val()!=undefined){
			url+="/B_BIL/RP_B_BIL_S01_01BL.do";
		}else if($('#editExit').val()!=undefined){
			url+="/B_BIL/RP_B_BIL_S02_01BL.do?idRef="+$("#hidIdRef").val()+"&mode=view&fromPageFlag=BIL";
		}		
		window.location.href=url;
	}
}