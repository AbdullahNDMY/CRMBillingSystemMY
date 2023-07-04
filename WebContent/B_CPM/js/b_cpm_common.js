//display type
var NON_DISPLAY = '0';
var DISPLAY = '1';
var ENABLE = '2';
var DISABLE = '3';
var LABEL = '4';
//Service Type
var STANDARD = "SP";
var NON_STANDARD = "NP";
//plan status
var ACTIVE = "PS3";
var SUSPEND = "PS6";
var TERMINATE = "PS7";

var POPUP_FEATURE_STD = "height=600,width=800,resizable=1,scrollbars=1";
var POPUP_FEATURE = "height=#height#,width=#width#,resizable=1,scrollbars=1";

function popup(queryString) {
	var location = $('form').attr('action');
	var url = location + queryString;
	newwindow=window.open(url,'name','height=600,width=800, scrollbars=yes');
	if (window.focus) {
		newwindow.focus();
	}
}

function getUrl(queryString) {
	var location = $('form').attr('action');
	var url = location + queryString;
	return url;
}

function showJQuery(subPlan){
	var imgExpend = '<img src="' + $('#hiddenContextPath').val()  + '/image/expend.PNG"/>';
	//hide the togglePlace
	subPlan.find('.spanTogglePlace').css('display','');
	//insert image to clickPlace
	$(imgExpend).prependTo(subPlan.find('.spanToggleClick'));
}
function hideJQuery(subPlan){
	var imgColapse = '<img src="' + $('#hiddenContextPath').val()  + '/image/colapse.PNG"/>';
	//hide the togglePlace
	subPlan.find('.spanTogglePlace').css('display','none');
	//insert image to clickPlace
	$(imgColapse).prependTo(subPlan.find('.spanToggleClick'));
}
function toggleJQuery(subPlan){
	//Remove the existing image which will be changed after toggle
	
	subPlan.find('.spanToggleClick img').remove();
	if(subPlan.find('.spanTogglePlace').css('display') == 'none') { //if togglePlace is being hiden then show it
		showJQuery(subPlan);
	} else { 												//if togglePlace is being showed then hide it
		hideJQuery(subPlan);
	}
}

/////////////////////////////////////////////////////////////
//Show a region when clicking on the place
//clickPlaceId : the element's id, clicking in it causing toggle
//togglePlaceId : the element's id, which will be show or hide depends the arg 'state'
function show(clickPlaceId,togglePlaceId){
	var imgExpend = '<img src="' + $('#hiddenContextPath').val()  + '/image/expend.PNG"/>';
	//hide the togglePlace
	$('#'+ togglePlaceId).css('display','');
	//insert image to clickPlace
	$(imgExpend).prependTo('#' + clickPlaceId);
}
/////////////////////////////////////////////////////////////
//Show a region when clicking on the place
//clickPlaceId : the element's id, clicking in it causing toggle
//togglePlaceId : the element's id, which will be show or hide depends the arg 'state'
function hide(clickPlaceId,togglePlaceId){
	var imgColapse = '<img src="' + $('#hiddenContextPath').val()  + '/image/colapse.PNG"/>';
	//hide the togglePlace
	$('#'+ togglePlaceId).css('display','none');
	//insert image to clickPlace
	$(imgColapse).prependTo('#' + clickPlaceId);
}
/////////////////////////////////////////////////////////////
//Execute show, hide when click the toggle place
//clickPlaceId : the element's id, clicking in it causing toggle
//togglePlaceId : the element's id, which will be show or hide depends the arg 'state'

function toggle(clickPlaceId,togglePlaceId){
	//Remove the existing image which will be changed after toggle
	
	$('#' + clickPlaceId +' img').remove();
	
	if($('#' + togglePlaceId).css('display') == 'none') //if togglePlace is being hiden then show it
		show(clickPlaceId,togglePlaceId);
	else 												//if togglePlace is being showed then hide it
		hide(clickPlaceId,togglePlaceId);

}



function inputDecimal(evt)
{
    var e = event || evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;
    if ( charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

    return true;
}

function inputInteger(evt)
{
    var e = event || evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;
    if ( charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

    return true;
}

///Get the index which is end in ID
function getIndex(id){

	var index = id.substr(id.lastIndexOf('_') + 1);
	return index;
}
//Function use submit form
function submitForm(event) {
	$("input[name='event']").remove();
	var event = '<input type="hidden" name="event" value="' + event + '"/>';
	$('form').append(event);
	/*
	var action = $('form').attr("action");
	var index = action.indexOf("&event=");
	if (index > -1) {
		action = action.substring(0, index);
	}
	action += "&event=" + event;
	$('form').attr("action", action);
	*/
	$('form').submit();
}

function IsNumeric(input)
{
   return (input - 0) == input && input.length > 0;
}

function numberFormat(value, decimalValue, splitChar) {
	var power = 1;
	for ( var i = 0; i < decimalValue; i++) {
		power *= 10;
	}
	var newValue = parseFloat(Math.round(value * power)) / power;
	newValue = addCommas(newValue, decimalValue, ",");
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

/**
 * 
 * @param idClass
 * @param forward
 * @return
 */
function initPageLinks(idClass, forward) {
	var tablePageLinks = $(idClass);
	for(var i = 0;i < tablePageLinks.length;i++) {
		//page link 1,2,3,4. ..,next,previous
		var pageLinks = tablePageLinks.eq(i).find('a');
		for(var j = 0; j < pageLinks.length;j++) {
			var link = pageLinks.eq(j);
			initLink(link,tablePageLinks.eq(i), forward);
		}
	}
}

/**
 * 
 * @param link
 * @param tablePageLink
 * @param forward
 * @return
 */
function initLink(link,tablePageLink, forward) {
	//save startindex
	var startIndex = getStartIndexFromHRef(link.attr('href'));
	link.append('<input type="hidden" value="' + startIndex + '"/>');
	//remove hereft from link
	link.attr('href','JavaScript:void(0);');
	link.click(function(){
		startIndex = link.find('input[type="hidden"]').val();
		//tablePageLink.find('.hiddenStartIndex').val(startIndex);
		var activeTab = $('#activeTab').val();
		var tabref = $('a[name="aTab"][rel="' + activeTab + '"]').closest("li");
		tabref.find(".startIndex").val(startIndex);
		submitForm(forward);
	});
}

/**
 * 
 * @param href
 * @return
 */
function getStartIndexFromHRef(href) {
	var last = parseInt(href.lastIndexOf('=')) + 1;
	var length = href.length;
	var startIndex = href.substr(last,length - last);
	startIndex = jQuery.trim(startIndex);
	return startIndex;
}
 

/**
 * 
 * @param obj
 * @return
 */
function inactiveCheckbox(obj) {
	obj.checked = !obj.checked;
}

/**
 * 
 * @param className
 * @return
 */
function inactiveRadio(className, container) {
	var transType = null;
 	if (container == undefined) {
 		transType = $("." + className);
} else {
	transType = container.find("." + className);
}
for ( var i = 0; i < transType.length; i++) {
	if(transType.eq(i).is(":checked")) {
		transType.eq(i).attr("disabled", false);
	} else {
		transType.eq(i).attr("disabled", true);
 		}
 	}
}
/**
 * 
 * @param className
 * @return
 */
function inactiveRadioForObj(controlObj) {
	for ( var i = 0; i < controlObj.length; i++) {
		if(controlObj.eq(i).is(":checked")) {
			controlObj.eq(i).attr("disabled", false);
		} else {
			controlObj.eq(i).attr("disabled", true);
	 	}
 	}
}