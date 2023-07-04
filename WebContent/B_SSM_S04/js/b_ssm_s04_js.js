function clickSave(){
	var services = document.getElementById("service_object");
	if(1<services.rows.length) {
		var serviceIDs = "";
		var completionDates = "";
		for ( var i = 1; i < services.rows.length; i++) {
			var row = services.rows[i];
			if ("trDataHeader"==$(row).attr("id")) {
				var serviceID = $(row).find("#serviceGroupID").val();
				var completionDate = $(row).find("#completionDate").val();
				serviceIDs = serviceIDs + serviceID+" ,";
				completionDates = completionDates + completionDate+" ,";
			}
		}
		$("#serviceIds").val(serviceIDs);
		$("#completionDates").val(completionDates);
	}
}