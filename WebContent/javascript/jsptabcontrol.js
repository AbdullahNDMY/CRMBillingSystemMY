/**
 * jsptabcontrol-states.js to manage Selection of TabPage
 * whith Javscript.
 * 
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
 
var HTML_TABPAGEHEADER_KEY = "tabPageheader";
var HTML_TABCONTROLBODY_KEY = "tabControlBody";
var HTML_TABPAGEBODY_KEY = "tabPageBody";
var HTML_TABPAGE_HIDDEN_KEY = "tabPageInputHiddenName";
    
TabToggle = function(tabControlName, tabPageName) {
  
  // Get ul element parent of ul element
  var selectedTabPageHeaderId = getHtmlId(HTML_TABPAGEHEADER_KEY, tabControlName, tabPageName);
  var ulElement = document.getElementById(selectedTabPageHeaderId).parentNode;
  /*
   * 1. Manage tabHeader => Show tab header which is clicked and Hide others.
  */
  // This gives the active tab its look
  var tabHeaderList = ulElement.getElementsByTagName('li');
  for(j = 0; j < tabHeaderList.length; j++) {
  	var /* LI */ tabHeader = tabHeaderList[j];
  	var currentTabPageHeaderId = tabHeader.id;
  	if (currentTabPageHeaderId == selectedTabPageHeaderId) {
  		if (tabHeader.className == null ||
  			tabHeader.className == "") 
  		{
  			tabHeader.className="active";
  		}
  		else {
  			if (tabHeader.className.indexOf("active") == -1) {
  				// tabPage is not selected => set active tabPage  
  				tabHeader.className=tabHeader.className + "_active";
  			}
  		}
  	}
  	else {
  		if (tabHeader.className=="active")
  			tabHeader.className="";
  		else {
  			if (tabHeader.className != null) {
  				tabHeader.className=tabHeader.className.replace("_active", "");
  			}
  		}
  	}
  }
  
  /*
   * 2. Manage tabPage (body of tabControl) => Hide all DIV and Show the tabPageIndex eme DIV.
  */
  // Get root of tab control body 
  var tabControlId = getHtmlId(HTML_TABCONTROLBODY_KEY, tabControlName);
  var rootTabControlBody = document.getElementById(tabControlId);
  if (rootTabControlBody != null) {
  	var selectedTabPageBodyId = getHtmlId(HTML_TABPAGEBODY_KEY, tabControlName, tabPageName);
    // Loop for child nodes => eeach DIV must be updated by display='none' 
	var /* Array */ tabPageList = rootTabControlBody.childNodes;
	var /* Integer */ index = 0;
	for(var i = 0; i < tabPageList.length; i++) {
	  var tabPage = tabPageList[i];
	  if (tabPage.style != null) {
	  	var currentTabPageBodyId = tabPage.id;
	  
	    if (selectedTabPageBodyId == currentTabPageBodyId) {
	      // Show tab page
	  tabPage.style.display = '';
	}
	else {
	  // Hide tab page
	  tabPage.style.display = 'none';
	        }
	      }
	    }
  }

	// update input HIDDEN wihch contains name of tab selected
	var inputHiddenName  = HTML_TABPAGE_HIDDEN_KEY + "_" + tabControlName;
	var inputHidden =  document.getElementById(inputHiddenName);
	if (inputHidden != null) {
		inputHidden.value = tabPageName;
	}
  //Magic Happens
  //new Effect.Appear(ele);
}

function getHtmlId(key, tabControlName, tabPageName) {
	if (tabPageName == null)
		return key + "_" + tabControlName;
 return key + "_" + tabControlName + "_" + tabPageName;
}


/**
 * Select Tab Page <b>tabPageName</b> of Tab Control with name <b>tabControlName</b>.
 * If force=true select tab page otherwise call onclick event of tab 
 * (to execute javascript according state).
 * @param tabControlName tabs name
 * @param tabPageName tab name
 * @param force boolean
 */
function setSelectedTabPageName(/* String */ tabControlName, /* String */ tabPageName, /*boolean */ force) {
	// Test if LI Element exist
	var liElement = getLITabPageHeader(tabControlName, tabPageName);
	if (liElement != null) {
		// Tab exist
		if (force != null && force == true) {
			// Force the selection of tab 
			new TabToggle(tabControlName, tabPageName);
		}
		else {
			// Call onclick event of tab (to execute javascript according state)
  			var hrefElement = liElement.childNodes[0];
	  		if (hrefElement != null) {
				hrefElement.onclick();
			}
		}
	}
}

/**
 * Get name of selected tabPage of tabControl with name <b>tabControlName</b>.
 * Return null, if tabControlName doesn't exist into request.
 * @param tabControlName tabsName
 * @return name of tabPage selected for tabControl tabControlName.
 */
function getSelectedTabPageName(/* String */ tabControlName) {
	var inputHiddenName  = HTML_TABPAGE_HIDDEN_KEY + "_" + tabControlName;
	var inputHidden =  document.getElementById(inputHiddenName);
	if (inputHidden != null)
		return inputHidden.value;
	return null;
}
	
function getLITabPageHeader(/* String */ tabControlName, /* String */ tabPageName) {
	var selectedTabPageHeaderId = getHtmlId(HTML_TABPAGEHEADER_KEY, tabControlName, tabPageName);
	return liElement = document.getElementById(selectedTabPageHeaderId);
}
