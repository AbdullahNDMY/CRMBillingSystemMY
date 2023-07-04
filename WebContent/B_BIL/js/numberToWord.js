var sepStringM=new Array("", "Thousand ", "Million ", "Billion ", "Trillion ", " ", " ");
var nbrStringA=new Array(" ", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ");
var nbrStringB=new Array("Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen ");
var nbrStringC=new Array(" ", " ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety ");

function nbrToEngWord(src, sep, per, mode){
	var int=src.split(per)[0];
	var dec=src.split(per)[1]=="undefined"?"00":src.split(per)[1];
	var str="";
	var tmp=int.split(sep);
	for (var i=0;i<tmp.length;i++){
		if (tmp[i].length==1){
			str=str+nbrStringA[parseInt(tmp[i])]+sepStringM[tmp.length-i-1];
		} else {
			if (tmp[i].length==2){
				if (tmp[i].substring(0,1)=="1"){
					str=str+nbrStringB[parseInt(tmp[i].substring(1,2))]+sepStringM[tmp.length-i-1];
				} else {
					str=str+nbrStringC[parseInt(tmp[i].substring(0,1))]+nbrStringA[parseInt(tmp[i].substring(1,2))]+sepStringM[tmp.length-i-1];
				}
			} else {
				if (tmp[i].substring(0,1)=="0") {
				//
				} else {
					str=str+nbrStringA[parseInt(tmp[i].substring(0,1))]+"Hundred ";
				}
				if (tmp[i].substring(1,2)=="1"){
					str=str+nbrStringB[parseInt(tmp[i].substring(2,3))]+sepStringM[tmp.length-i-1];
				} else {
					if (tmp[i].substring(1,2)=="0"){
						if (!(0<(tmp.length-i-1) && tmp[i]=="000")){
							str=str+nbrStringA[parseInt(tmp[i].substring(2,3))]+sepStringM[tmp.length-i-1];
						}
					} else {
						str=str+nbrStringC[parseInt(tmp[i].substring(1,2))]+nbrStringA[parseInt(tmp[i].substring(2,3))]+sepStringM[tmp.length-i-1];
					}
				}
			}
		}
	}
	dec=(dec.length>2)?dec.substring(0,2):dec;
	if (dec.length==1) {dec=dec+"0";}
	if (dec=="00") {
	} else {
		if (str != "" && str != " "){
			str=str+"And ";
		}
		if (dec.substring(0,1)=="1"){
			str=str+"Cents "+nbrStringB[parseInt(dec.substring(1,2))];
		} else {
			if (dec.substring(0,1)=="0") {
				str=str+"Cents "+nbrStringA[parseInt(dec.substring(1,2))];
			} else {
				str=str+"Cents "+nbrStringC[parseInt(dec.substring(0,1))]+nbrStringA[parseInt(dec.substring(1,2))];
			}
		}
	}
	switch (mode){
	case "U":
	str=str.toUpperCase();
	break;
	case "L":
	str=str.toLowerCase();
	break;
	default:
	break;
	}
	if(" " == str && parseFloat(src) == 0.0){
		str = "Zero ";
	}
	return str;
}