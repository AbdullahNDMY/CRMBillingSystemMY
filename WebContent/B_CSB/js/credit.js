
function getCredit(id_cust) {

 var req = newXMLHttpRequest();

 req.onreadystatechange = getReadyStateHandler(req, updateCredit);
 
 req.open("POST", "B_CSBR01A.do", true);
 req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 req.send("id_cust="+id_cust);
}
function updateCredit(creditXML){
	var credit = creditXML.getElementsByTagName("credit")[0];
	var items = credit.getElementsByTagName("item");
	var item = items[0];
	var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
	document.getElementById("remark").value=name;	
}
