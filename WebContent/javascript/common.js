var isLoaded = false;

var hiddenObj = document.createElement("input");
hiddenObj.setAttribute("type","hidden");
var eventObj;

function initMain(){
	isLoaded = true;
	try {
		if (window.parent && window.parent.frame_top) {
			if (parent.frame_top.isLoaded) parent.frame_top.initPage();
				
			if (parent.frame_menu.isLoaded) 
				parent.frame_menu.showHideMenu();
			
	    if(document.forms[0]){
	      eventObj = hiddenObj.cloneNode();
	      eventObj.setAttribute("name","event");
	    	document.forms[0].appendChild(eventObj);
	    }
			
			document.onunload = function() {
		  	isLoaded = false;
		  };
		}
	} catch(e) {}
}


function mainSubmit(param) {
    eventObj.value = "forward_" + param;
    document.forms[0].submit();
}


function getScreenId(){
    if(isLoaded){
        return document.body.id.toLowerCase();
    }else{
        return "";
    }
}

function popup(url, msg) {
	var newwindow = window.open(url, 'name', 'height=400,width=800,scrollbars=1');
	if (window.focus) { newwindow.focus(); }
}

if (typeof jQuery != "undefined")
/* #readonly([true|false]) */
(function($) {

	$.fn.extend({
		readonly: function(isReadonly) {
			isReadonly = (isReadonly != false ? true: false);
			if (isReadonly) {
				this.each(function(i) {
					$(this).attr("readonly", true).addClass("readonly");
				});
			} else {
				this.each(function(i) {
					$(this).removeAttr("readonly").removeClass("readonly");
				});
			}
		},
		
		resettable: function() {
			this.each(function(i) {
				var obj = $(this);
				obj.attr("rel", obj.val());
			});
		},
		
		resetValue: function() {
			this.each(function() {
				var obj = $(this);
				if (obj.attr("rel") != null) {
					obj.val(obj.attr("rel"));
				}
			});
		}
	});
	
})(jQuery);

function IsNumeric(input)
{
   return (input - 0) == input && input.length > 0;
}

function initTextarea(obj) {
	if(/^[0-9]+$/.test(obj.getAttribute("maxlength"))) {
		var func = function() {
			var len = parseInt(this.getAttribute("maxlength"), 10);
			if(this.value.length > len) {
				this.value = this.value.substr(0, len);
				event.returnValue=false; 
				return false;
			}        
		}
		obj.onkeypress = func;
		obj.onkeyup = func;
		obj.onblur = func;
		obj.onmouseover = func;
		obj.paste = function() {
			var len = parseInt(this.getAttribute("maxlength"), 10);
			if (this.value.length + window.clipboardData.getData("Text").length > len) {
				this.value = this.value.substr(0, len);
				event.returnValue=false;
				return false;
			}
		}
	} //check object neek support maxlength
}
function specialCharReplace(str){
	str = str.replace(/\&/g,'&#38;');
	return str;
}
/**
 * Show loading window
 */
function showLoadingTipWindow() {
    $('body').append('<div id="loadingTipWindow" class="LoadingTipWindow"></div>');

    var loadingTipWindow = document.getElementById("loadingTipWindow");
    loadingTipWindow.style.display = "block";
    loadingTipWindow.style.visibility = "visible";

    loadingTipWindow.style.maxHeight = "4000px";
    loadingTipWindow.style.width = ieCompatibility().scrollWidth + "px";
    loadingTipWindow.style.height = ieCompatibility().scrollHeight + "px";

    var boat = $('<table id="loadingtable" style="position:absolute;width:480px;height:96px;color:blue;z-index: 101;">'+
    '<tr><td align="center"><img id="loadingImg" src="../image/loading.gif" style="visibility: visible;" alt="Loading..."/></td></tr>'+
    '<tr><td align="center" style="font-weight: bold;width:100%">Your request is currently being processed.</td></tr>'+
    '<tr><td align="center" style="font-weight: bold;width:100%">Please do not click on the menu/tool bar or click the back button.</td></tr></table>');

    boat.css("left",(ieCompatibility().clientWidth)/2 - 240 + "px");
    boat.css("top",(ieCompatibility().clientHeight)/2 - 48 + "px");
    $('body').append(boat);

    setTimeout('refeshLoadingImage()', 200);
    
    setInterval('closeWindowResponse()',300);
}

/**
 * Remove laoding tip when response to client finished. 
 */
function closeWindowResponse() {

    var state = document.readyState;

    // readyState contains 5 values
    // 0: (Uninitialized) the send( ) method has not yet been invoked. 
    // 1: (Loading) the send( ) method has been invoked, request in progress. 
    // 2: (Loaded) the send( ) method has completed, entire response received. 
    // 3: (Interactive) the response is being parsed. 
    // 4: (Completed) the response has been parsed, is ready for harvesting
    if (state == 'complete' || state == 'interactive') {
        $("#loadingtable").remove();
        $("#loadingTipWindow").remove();
    }
}

/**
 * Refesh loading image
 */
function refeshLoadingImage() {
    var loadingImg = document.getElementById("loadingImg");
    if (loadingImg) {
        loadingImg.src = "../image/loading.gif";
    }
}

/**
 * IE compatibility
 */
function ieCompatibility() {
    return (!window.opera && document.compatMode && document.compatMode!="BackCompat")? 
           document.documentElement : 
           document.body;
}