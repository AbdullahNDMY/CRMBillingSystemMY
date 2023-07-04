(function ($) {
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
    	  var idNum = 1;
    	  if (this.element.attr("class") == "searchService") {
    		  idNum = 2;
    	  }else if (this.element.attr("class") == "searchPlan") {
    		  idNum = 3;
		  }else {
			  idNum = 4;
		  }
    	  var selected = this.element.children( ":selected" ),        
          value =selected.text();
          this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .attr( "id", "combInput" + idNum )
          .val( value )
          .width(this.element.outerWidth()-19)
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
           this.input.focus(function() {
        	   if (input1.val() == "- Please Select One -") { 
          			input1.val(""); 
          		} 
           });
           this.input.blur(function() {
        	   if (input1.val() == "") { 
          			input1.val("- Please Select One -"); 
          		} 
           });
          
        this._on( this.input, {
            autocompleteselect: function( event, ui ) {
            ui.item.option.selected = true;
            
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
           if ( (( !request.term || matcher.test(text) ) ) )
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
        this.element.val( "" );
        this._delay(function() {
          this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.data( "ui-autocomplete" ).term = "";        
      },
 
      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
  })( jQuery );

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