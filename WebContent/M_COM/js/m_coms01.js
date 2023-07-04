
function contact_click(contact_name){
	/*
	document.getElementById("sale_contact").Class ="hide";
	document.getElementById("technical_contact").Class ="hide";
	document.getElementById("other_contact").Class ="hide";
	if(contact_name=="sale_contact"){
		document.getElementById("sale_contact").Class ="show";		
	}else if(contact_name=="technical_contact"){		
		document.getElementById("technical_contact").Class="show";		
	}else{		
		document.getElementById("other_contact").Class="show";
	} */
	$('#sale_contact').removeClass("show");
	$('#sale_contact').addClass("hide");
	$('#technical_contact').removeClass("show");
	$('#technical_contact').addClass("hide");
	$('#other_contact').removeClass("show");
	$('#other_contact').addClass("hide");
	if(contact_name=="sale_contact"){
		$('#sale_contact').removeClass("hide");		
	}else if(contact_name=="technical_contact"){		
		$('#technical_contact').removeClass("hide");
				
	}else{		
		$('#other_contact').removeClass("hide");		
	} 
}


function do_save(){
	getDataToUpdate();
	var bt = document.getElementById("forward_submitData");
	bt.click();
}


function do_exit(){
	window.close();
}

function do_browse(){
	var button = document.getElementById("browse_button");	
	button.click();
	
}

function do_add_row(){
	//get default and make a clone
	var clonedRow = $('table tr#hideRow').clone().removeClass("hide");
	
	//get row num
	var rowNum  = $('table#giroInfo tbody tr').size() - 1;
	//set name for cloned row
	var rowid = "newRow_" + rowNum;
	clonedRow.attr("id", rowid);
	
	clonedRow.find('td:nth-child(2)').html(rowNum); //write row index
	//clonedRow.find('td:nth-child(1)').attr("onclick",'do_remove(' + rowNum + ')'); // add onclick
	clonedRow.find('td:nth-child(1)').click(function(){do_remove( rowNum );})
	clonedRow.find('td:nth-child(4)').click(function(){display_dropdown_value( rowNum );}) //dropdown list
	$('table#giroInfo tbody').append(clonedRow );
	//$('table#giroInfo tbody').append('<tr>'+clonedRow.html()+'</tr>');
	//get id_com_bank
	
}

function display_dropdown_value(rowNum){
	
	var selectedBankID = $('#newRow_'+rowNum + ' td:nth-child(4) :selected').val();
	var i=0;	        
	for(i=0;i<bankData.split(";").length;++i){  
		if(bankArray[i][0]==selectedBankID) {
			$('#newRow_'+rowNum +' td:nth-child(5)').html(bankArray[i][1]); //bank code
			$('#newRow_'+rowNum +' td:nth-child(6)').html(bankArray[i][2]); //branch code
		}
	}

}

function do_remove(row){
	var id_com_bank = $('#newRow_'+row).find('td:nth-child(3)').html(); //write row index
    $('#newRow_'+row).remove(); //remove row
    //child = document.getElementById("newRow_"+row);
    //document.getElementById("giroBody").removeChild(child);
    
    
    //re index
    var counter=1;
    $('table#giroInfo tbody tr[id^=newRow]').each(function(){
        //    alert($(this).html());
        $(this).find('td:nth-child(2)').html(counter);
        counter =counter+1;
    })
    //delete in database
	delete_db(id_com_bank);

	
}

function getDataToUpdate(){
	var all="";
	$('table#giroInfo tbody tr[id^=newRow]').each(function(){
	        //    alert($(this).html());
	        //    build data to save
	        var bankID=$(this).find('td:nth-child(4) :selected').val();
	        var idComBank=$(this).find('td:nth-child(3)').html();
	        var accNo=$(this).find('td:nth-child(7) :input').val();
	        var accType=$(this).find('td:nth-child(8) :selected').val();
	        var accName=$(this).find('td:nth-child(9) :input').val();
	        var swift=$(this).find('td:nth-child(10) :input').val();
	        var tmp = bankID+"," + idComBank+"," +accNo+"," +accType+"," +accName+"," +swift;
	        all=all+tmp +";";
	})
	
	//set value to update_data	
	$('#update_data').attr("value",all);
}

function do_split_bank(row){
	row = bankData.split(";");
	var i=0; 
	bankArray = new Array(row.length) ;
	for (i=0; i<row.length;++i){
		bankArray[i] = row[i].split(",");    
	}	
}

