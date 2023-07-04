var FORWARD_SEARCH = "forward_search";
var FORWARD_NEW = "forward_new";
var FORWARD_VIEWCUSTOMER = "forward_view";
var SEARCH_BY_BUTTON = "button";
var SEARCH_BY_PAGELINK = "link";
var FORWARD_ROLEACCESS = "forward_roleAccess";
var FORWARD_ORGANIZATION = "forward_organization";

var MODE_VIEW = "view";
var MODE_NEW = "new";
$(function(){
	$('#btnSearch').click(function(){
	    showLoadingTipWindow();
		$('#hiddenSearchBy').val(SEARCH_BY_BUTTON);
		$('#hiddenStartIndex').val('0');		
		submitForm(FORWARD_SEARCH);
	});
	$('#btnReset').click(function(){
		var contextPath = $("#contextpath").val();
		window.location = contextPath+"/A_USR/A_USR_Gate.do";
	});
	$('#btnNew').click(function(){
		queryString = "&event=" + FORWARD_NEW + "&mode=" + MODE_NEW;
		redirect(queryString);
	});
	$('#btnOrganization').click(function(){
        var queryString = '&event='+FORWARD_ORGANIZATION+'&mode=' + MODE_VIEW + '&choosed=';        
        redirect(queryString);
    });
    $('#btnRoleAccess').click(function(){
        var queryString = '&event='+FORWARD_ROLEACCESS+'&mode=' + MODE_VIEW + '&choosed=';
        redirect(queryString);
    });
	//pageLink
	for(var i = 0; i <  $('.pageLink a').length;i++) {
		initLink($('.pageLink a').eq(i));
	}
	//hyper link user id
	for(var i = 0; i < $('.result .hlViewUser').length;i++) {
		var hlViewUser = $('.result .hlViewUser').eq(i);
		hlViewUser.click(function(){
			var queryString = '&event=' + FORWARD_VIEWCUSTOMER + '&mode=' + MODE_VIEW + '&idUser=' + $(this).html();
			redirect(queryString);
		});
	}
	
	//hyper link role id
    for(var i = 0; i < $('.result .hlViewRole').length;i++) {
        var hlViewRole = $('.result .hlViewRole').eq(i);
        hlViewRole.click(function(){
            var queryString = '&event='+FORWARD_ROLEACCESS+'&mode=' + MODE_VIEW + '&choosed='+$(this).find("input[name='idRole']").val();
            redirect(queryString);
        });
    }
});
function initLink(link) {
	//save startindex
	var startIndex = getStartIndexFromHRef(link.attr('href'));
	link.append('<input type="hidden" value="' + startIndex + '"/>');
	//remove hereft from link
	link.attr('href','JavaScript:void(0);');
	link.click(function(){
		var startIndex = link.find('input[type="hidden"]').val();
		$('#hiddenStartIndex').val(startIndex);
		$('#hiddenSearchBy').val(SEARCH_BY_PAGELINK);
		submitForm(FORWARD_SEARCH);
	});
}
function getStartIndexFromHRef(href) {
	var last = parseInt(href.lastIndexOf('=')) + 1;
	var length = href.length;
	var startIndex = href.substr(last,length - last);
	startIndex = jQuery.trim(startIndex);
	return startIndex;
}
function submitForm(event) {
	var hiddenEvent = '<input type="hidden" name="event" value="' + event + '"/>';
	$('form').append(hiddenEvent);
	$('form').submit();
}
function redirect(queryString) {
	var location = $('form').attr('action');
	window.location = location + queryString;
}
