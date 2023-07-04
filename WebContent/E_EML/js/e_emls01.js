$(document).ready(function(){
		$("#idRefsAll").click(function(){
			var checked_status = this.checked;
			$('input[name="idRefs"]').each(function(){
				if(checked_status){
					$(this).attr('checked', true);
				}else{
					$(this).attr('checked', false);
				}
			});
		});
	});
function searchData() {
	var checkAlls = document.getElementsByName("idRefs");
	for (var i=0;i<checkAlls.length;i++){
		if(checkAlls[i].checked){
			checkAlls[i].checked=false;
		}
	}
	var startIndex = document.getElementById("startIndex");
	if(startIndex!=null && startIndex!=undefined) {
		startIndex.value="0";
	}
}

function linkClick(param,context) {
	var url = context +'/'+ 'E_EML/E_EMLS03BL.do' + '?'+ param;
	/*var url = context +'/'+ 'E_EML/E_EMLS03.do' + '?code=' + param ;*/
	var width = window.screen.width * 80 / 100;
	var height = window.screen.height * 80 / 100;
	var left = Number((screen.availWidth / 2) - (width / 2));
	var top = Number((screen.availHeight / 2) - (height / 2));
	var offsetFeatures = "width=" + width + ",height=" + height + ",left="
			+ left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
	var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
			+ "," + offsetFeatures;
	var newwindow = window.open(url, null, strFeatures);
	if (window.focus) {
		newwindow.focus();
	}
}
function linkBillDocumentClick(param,context) {
	var url = context +'/'+ 'B_BIL/RP_B_BIL_S02_01BL.do' + '?'+ 'idRef='+param+'&mode=view&fromPageFlag=EML';
	/*var url = context +'/'+ 'E_EML/E_EMLS03.do' + '?code=' + param ;*/
	var width = window.screen.width * 85 / 100;
	var height = window.screen.height * 80 / 100;
	var left = Number((screen.availWidth / 2) - (width / 2));
	var top = Number((screen.availHeight / 2) - (height / 2));
	var offsetFeatures = "width=" + width + ",height=" + height + ",left="
			+ left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
	var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
			+ "," + offsetFeatures;
	var newwindow = window.open(url, null, strFeatures);
	if (window.focus) {
		newwindow.focus();
	}
}
function linkBathHistoryClick(url){
	
	var width = window.screen.width * 80 / 100;
	var height = window.screen.height * 80 / 100;
	var left = Number((screen.availWidth / 2) - (width / 2));
	var top = Number((screen.availHeight / 2) - (height / 2));
	var offsetFeatures = "width=" + width + ",height=" + height + ",left="
			+ left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
	var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
			+ "," + offsetFeatures;
	var newwindow = window.open(url, null, strFeatures);
	if (window.focus) {
		newwindow.focus();
	}
	
}