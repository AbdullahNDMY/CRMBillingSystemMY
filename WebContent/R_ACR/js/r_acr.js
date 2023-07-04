$(function(){

	//setting check box
	settingCheckbox($(".searchServiceStatus"), $(".hidServStatus"));
	
	function settingCheckbox(controlList, valueList) {
		for ( var i = 0; i < controlList.length; i++) {
			var control = controlList.eq(i);
			for ( var j = 0; j < valueList.length; j++) {
				if (control.val() == valueList.eq(j).val()) {
					control.attr("checked", true);
					break;
				}
			}
		}
	}
});
function searchClick() {
	var startIndex = document.getElementById("startIndex");
	if(startIndex!=null && startIndex!=undefined) {
		startIndex.value="0";
	}
}