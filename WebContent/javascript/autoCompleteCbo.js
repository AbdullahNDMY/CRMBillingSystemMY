(function( $ ) {
    $.widget( "custom.combobox", {
      _create: function() {
        this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );
 
        this.element.hide();
        this._createAutocomplete();
        this._createShowAllButton();
      },
 
      _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
          value =selected.text();
          this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .attr( "id", "combInput" )
          .val( value )
          .width(this.element.outerWidth()-13)
          //.width('auto')
          .attr( "title", "" )
          .addClass( "custom-combobox-input" )
          .autocomplete({
            delay: 0,
            minLength: 0,
            source: $.proxy( this, "_source" )
          })
          .tooltip({
              tooltipClass: "ui-state-highlight"
            });

 		   var input1 = this.input;
 		   
           this.input.click(function() {
            input1.focus();
 
            var wasOpen = input1.autocomplete( "widget" ).is( ":visible" );
            if ( wasOpen ) {
              input1.autocomplete("close", "");
            } else {
 	           input1.autocomplete( "search", "" );
            }
          });
          
    	 
	    
        this._on( this.input, {
          autocompleteselect: function( event, ui ) {
          
           if (ui.item.option.value=="add") {
              var classname=ui.item.option.parentNode.className;
              onAddClick(this.input,this.element,classname);
              return false;
            }
            ui.item.option.selected = true;
            
            //Change this Combobox Event
            if (ui.item.option.value!="add"||ui.item.option.value=="") {
            	svcLevel3ChangeEvt(this.input,ui.item.option.value);
            }
            //JobNoAllCheck
            var jboNoAllName=ui.item.option.parentNode.className;
            if(jboNoAllName=="jobNoAllJob"){
            	JobNoAllChkEvt('JobNoAllJboChange',this.input);
            }
            
            this._trigger( "select", event, {
              item: ui.item.option
            });
          },
 
          autocompletechange: "_removeIfInvalid"
        });
      },
 
      _createShowAllButton: function() {
        var input = this.input,
          wasOpen = false;

        $( "<a>" )
          .attr( "tabIndex", -1 )
          .appendTo( this.wrapper )
          .height(input.outerHeight())
          .button({
            icons: {
              primary: "ui-icon-triangle-1-s"
            },
            text: false
          })
          .removeClass( "ui-corner-all" )
          .addClass( "custom-combobox-toggle ui-corner-right" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();
 
           if ( wasOpen ) {
              return;
            }
 
            input.autocomplete( "search", "" );
          });
      },
 
      _source: function( request, response ) {
        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
        this.input.autocomplete( "widget" ).hide();
        response( this.element.children( "option" ).map(function() {
          var text = $( this ).text();
           if ( (( !request.term || matcher.test(text) ) ) || this.value == "add" )
            return {
              label: text,
              value: text,
              option: this
            };
        }) );
      },
 
      _removeIfInvalid: function( event, ui ) {
        if ( ui.item ) {
          return;
        }
 
        var value = this.input.val(),
          valueLowerCase = value.toLowerCase(),
          valid = false;
        this.element.children( "option" ).each(function() {
          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
            this.selected = valid = true;
            return false;
          }
        });
 
        if ( valid ) {
          return;
        }
 
        this.input
          .val( "-Please select one-" )
          .attr( "title", value + " didn't match any item" )
          .tooltip( "open" );
        this.element.val( "" );
        this._delay(function() {
          this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.data( "ui-autocomplete" ).term = "";
 
       	svcLevel3ChangeEvt(this.input,"");
        
      },
 
      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
  })( jQuery );

  //set JobNoCombobox
  function resetComb(){
	  $(".jobNo").each(function(i){
		  var subplan = $(this).closest("div.subPlan");
		  if(subplan.css("display")!="none"){
			  $(this).combobox();
			  $(this).append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
		  }
	  }); 
	 
  }
  
  //set PlanDetailCombobox
  function resetPlanDetailComb(){
	  $(".svcLevel3").each(function(j){
		  var subplan = $(this).closest("div.subPlan");
		  if(subplan.css("display")!="none"){
			  $(this).combobox();
			  $(this).append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
		  }
	  }); 
	  
  }

  function onAddClick(obj,selobj,classname){
	  var url;
	  if(classname=="svcLevel3"){
		  var subPlan = $(obj).closest("div.subPlan");
		  var svcLevel1Val=subPlan.find('.svcLevel1 option:selected').val();
		  var svcLevelName=subPlan.find('.svcLevel1 option:selected').html();
	  
		  if(svcLevel1Val==undefined||svcLevel1Val==null||svcLevel1Val==""){
			  svcLevel1Val=subPlan.find(".svcLevel1").val();
			  svcLevelName="";
		  }
	  
		  url=$("#hiddenContextPath").val()+"/M_SVT/M_SVTS02_AddPlanDetailOptionBL.do"+"?svcLevel1Val="+svcLevel1Val+"&lblCategory="+svcLevelName;
	  }
	  if(classname=="jobNoAllJob"||classname=="jobNo"){
		  var idCust = $("#idCustomer").val();
		  var actionFrom="add";
		  url=$("#hiddenContextPath").val()+"/M_JNM/M_JNMR02BLogic.do"+"?id_cust="+idCust+"&actionFrom="+actionFrom;
	  }
	  openDefineWindow(url);
	  globalInput=$(obj);
	  globalSel=$(selobj);
	 }

  function openDefineWindow(url) {
	  var width = window.screen.width*60/100;
	  var height = window.screen.height*40/100;
	  var left = Number((screen.availWidth/2) - (width/2));
	  var top = Number((screen.availHeight/2) - (height/2));
	  var offsetFeatures = "width=" + width + ",height=" + height +
	                        ",left=" + left + ",top=" + top +
	                        "screenX=" + left + ",screenY=" + top;
	  var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;   
	  var newwindow = window.open(url, null, strFeatures);   
	  if (window.focus) { newwindow.focus(); }
	  	return false;
	 }

  function seaclosetip(iobj){

		var b = iobj.parentNode.children;
	    for(var i=0;i<b.length;i++){
	        if(b[i] !== iobj) {
		        var node = b[i].childNodes[0];
		        if (node.className.indexOf("ui-state-focus") !=-1) {
	       			 node.className = ""; 
	       			 break;
	            }
	        }
	    }
	 
	}
  function seashowtip(iobj,iwidth){  

		var my_tips=document.getElementById("mytips");  
	    var w = iwidth - iobj.offsetWidth;
	    var ileft = getAbsoluteLeft(iobj)+2;
	    var itop = getAbsoluteTop(iobj);
	    var iheight = iobj.offsetHeight;
	   // var tips = "loading.........";
	    
	   // if (tips !="") {
		   // my_tips.innerHTML=tips;  
		    my_tips.style.width=iwidth+"px";  
		    my_tips.style.left=ileft+"px";  
		    my_tips.style.top=(itop+iheight)+"px";
		    my_tips.style.display="";
	    //}
	 
	 
	}  

	function getAbsoluteLeft(o) {

	    oLeft = o.offsetLeft;         
	    while(o.offsetParent!=null) {
	    oParent = o.offsetParent;   
	    oLeft += oParent.offsetLeft;
	    o = oParent;
	    }
	    return oLeft;
	}

	function getAbsoluteTop(o) {

	    oTop = o.offsetTop;
	    while(o.offsetParent!=null)
	    { 
	    oParent = o.offsetParent;
	    oTop += oParent.offsetTop;
	    o = oParent;
	    }
	    return oTop;
	}	 
