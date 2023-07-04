var FORWARD_EXIT = "forward_exit";
var FORWARD_EDIT = "forward_edit";
var FORWARD_DELETE = "forward_delete";
var FORWARD_UPDATE = "forward_update";
var FORWARD_SAVE = "forward_save";
var FORWARD_VIEW = "forward_view";
var MODE_EDIT = "edit";
var MODE_VIEW = "view";
var MODE_NEW = "new";
$(function(){
	//for view mode
	$('#btnEdit').click(function(){
		var queryString = '&event=' + FORWARD_EDIT + '&mode=' + MODE_EDIT + '&idUser=' + $('#hiddenIdUser').val();
		redirect(queryString);
	});
	
	//for new mode & edit mode
	$('#btnSave').click(function(){
		var idPeopleSoftAcc=$("#idPeopleSoftAcc").val();
		var idPplSoftDept=$("#idPplSoftDept").val();
		var regexp=/[&]/;
		var message = $("#ERR4SC107").val();
		if(regexp.test(idPeopleSoftAcc))
		{
			message=message.replace('{0}', "PeopleSoft Acc. ID").replace('{1}', "PeopleSoft Acc. ID");
			var MsgBox = new messageBox();
			MsgBox.POPALT(message);
			return false;
		}
		if(regexp.test(idPplSoftDept))
		{
			message=message.replace('{0}', "Dept ID").replace('{1}', "Dept ID");
			var MsgBox = new messageBox();
			MsgBox.POPALT(message);
			return false;
		}
		var event = "";
		//if mode is NEW then forward to Action save
		if($('#hiddenMode').val() == MODE_NEW){
			event = FORWARD_SAVE;
		}
		//if mode is EDIT then forward to Action update
		else {
			event = FORWARD_UPDATE;
		}
		submitForm(event);
	});
	

	//for view mode & edit mode
	$('#btnDelete').click(function(){
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM($('#hiddenMsgDeleteConfirmation').val()) == MsgBox.YES_CONFIRM) {
		//if(confirm($('#hiddenMsgDeleteConfirmation').val())){
			//set mode = view beacause, if action delete has error, the destination is view mode again.
			var queryString = '&event=' + FORWARD_DELETE + '&mode=' + MODE_VIEW + '&idUser=' + $('#hiddenIdUser').val();
			redirect(queryString);
		}

	});
	
	//for new,view,edit mode
	$('#btnExit').click(function(){
		var queryString = "";
		//in view mode, exit lead to SEARCH and no need to show popup confirm
		if($('#hiddenMode').val() == MODE_VIEW){
			queryString = '&event=' + FORWARD_EXIT;
			redirect(queryString);
			return;
		}
		var MsgBox = new messageBox();
		if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
		//if(confirm($('#hiddenMsgExitConfirmation').val())){
			//if being in EDIT, then exit will lead to VIEW
			if($('#hiddenMode').val() == MODE_EDIT){
				queryString = '&event=' + FORWARD_VIEW + '&mode=' + MODE_VIEW + '&idUser=' + $('#hiddenIdUser').val();
			} 
			//if being in VIEW, then exit will lead to SEARCH
			else if($('#hiddenMode').val() == MODE_NEW){
				queryString = '&event=' + FORWARD_EXIT;
			}
			redirect(queryString);
		}
	});
	/**
	 * upload
	 */
	var totalSize = 1;
	if($('#hiddenMode').val() == MODE_NEW || $('#hiddenMode').val() == MODE_EDIT){
		$('#btnUpload').uploadify({
		    'uploader'  : $('#hiddenContextPath').val() + '/javascript/uploadify/uploadify.swf',
		    'script'    : $('#hiddenContextPath').val() + '/A_USER_UploadPhoto?sessionDirectory='+$('#sessionDirectory').val(),
		    'cancelImg' : $('#hiddenContextPath').val() + '/javascript/uploadify/cancel.png',
		    'auto'      : true,
		    'hideButton': true,
		    'multi'		: false,
		    'fileExt'	: '*.jpg;*.gif;*.png',
		    'wmode'		: 'transparent',
		    'width'		: 80,
		    'height'	: 25,
		    'scriptData': {
		    },
		    'onSelect'	: function(event,ID,fileObj) {
		    	$('#btnSave').attr('disabled', 'disabled');
		    	totalSize = fileObj.size;
		    },
		    'onComplete': function(event, ID, fileObj, response, data){
				$('#photoPath').val(response);
				$('#btnSave').attr('disabled', '');
			},
			'onProgress'  : function(event,ID,fileObj,data) {
		          var progress = data.bytesLoaded / totalSize;
		          //Set progress bar to progress...
		        }
			,
			'onError'	: function (event,ID,fileObj,errorObj){
				alert("Error occur when upload photo.");
			}
		  });
	}
});

function submitForm(event) {
	var hiddenEvent = '<input type="hidden" name="event" value="' + event + '"/>';
	$('form').append(hiddenEvent);
	$('form').submit();
}
function redirect(queryString) {
	var location = $('form').attr('action');
	window.location = location + queryString;
}
function cls(){
    with(event.srcElement)
    if(value ==defaultValue)value=""
}
function res(){
    with(event.srcElement)
    if(value =="")value=defaultValue
}

function changeAccessType(){
    var selRadio = $("[class='accessRight']:checked").val();
    if("R"==selRadio){
        $("input[type='radio'][name^='userAccess']").attr("disabled", true);
        $("#idRole").attr("disabled", false);
    }else if("C"==selRadio){
        $("input[type='radio'][name^='userAccess']").attr("disabled", false);
        $("#idRole").attr("disabled", true);
        if ($("#idRole").val() == "") {
            $("#idRoleHidden").val("-");
        }
        
    }
}
function retriveAccess(obj){
    var role = $(obj).val();
    $("#idRoleHidden").val(role);
    if(role){
        $.ajax({
        type: "POST",
        url: $('#hiddenContextPath').val()+"/A_USR/A_USR_ChangeAccessAjax.do",
        data:{ "idRole":role },
        success: function(html) {
           var access = $(html).find("#userAccessInformation");
           $("#userAccessInformation").empty();
           $("#userAccessInformation").html(access.html());
           changeAccessType();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            alert("system error!");
        }
    });
    }
}