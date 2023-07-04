/**
 * jsptabcontrol-states.js to manage State of TabPage
 * whith Javscript.
 *
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */

var HTML_TABPAGESTATE_HIDDEN_KEY = "tabPageStateInputHiddenName";

/* ----------- STATE MANAGEMENT ---------*/
var /* Array */ states = null;
function State(name) {
	/* String */ this.name = name;
	/* Array */ this.insertJavascriptList = null;
	/* Array */ this.insertStyleClassList = null;
	this.addInsertJavascript = function(insertJavascript) {
									if (this.insertJavascriptList == null)
										this.insertJavascriptList = new Array();
									this.insertJavascriptList.push(insertJavascript);
							   };
	this.addInsertStyleClass = function(insertStyleClass) {
									if (this.insertStyleClassList == null)
										this.insertStyleClassList = new Array();
									this.insertStyleClassList.push(insertStyleClass);
							   };							   
}

function addState(state) {
	if (states == null) {
		states = new Array();
	}
	states.push(state);
}

function InsertJavascript(event, where, javascriptContent) {
	/* String */ this.event = event;
	/* String */  this.where = where;
	/* String */  this.javascriptContent = javascriptContent;
}

function InsertStyleClass(where, styleClass) {
	/* String */ this.where = where;
	/* String */ this.styleClass = styleClass;
}

/**
 * Get state to Tab Page <b>tabPageName</b> of Tab Control with name <b>tabControlName</b>.
 * @param tabControlName
 * @param tabPageName
 * @return
 */
function getTabPageState(/* String */ tabControlName, /* String */ tabPageName) {
	var inputHiddenName  = HTML_TABPAGESTATE_HIDDEN_KEY + "_" + tabControlName + "_" + tabPageName;
	var inputHidden =  document.getElementById(inputHiddenName);
	if (inputHidden != null)
		return inputHidden.value;
	return null;
}
	
/**
 * Set state to Tab Page <b>tabPageName</b> of Tab Control with name <b>tabControlName</b>.
 * @param tabControlName
 * @param tabPageName
 * @param stateName
 */
function setTabPageState(/* String */ tabControlName, /* String */ tabPageName, /* String */ stateName) {
	// Test if LI Element exist
	var liElement = getLITabPageHeader(tabControlName, tabPageName);
	if (liElement != null) {
		// Get the state configuration 
		var /* State */  state = null;
		if (states != null) {
			for (var i=0; i<states.length ; i++) {
				var currentState = states[i];
				if (currentState.name == stateName) {
					state = currentState;
					break;
				}
			}	
		}
		if (state != null) {
			// Update event Javascript
			var hrefElement = liElement.childNodes[0];
	  		if (hrefElement != null) {
	  			// Rebuild A HREF element
  				var tabPageTitle = hrefElement.innerHTML;
  				var tabPageWidth = hrefElement.style;
  				if (tabPageWidth != null) {
  					tabPageWidth = tabPageWidth.width;
  				}
  				
				var insertJavascriptList = state.insertJavascriptList;
				if (insertJavascriptList != null) {
	  				
	  				var /* String */ eventsJavascript = "";
					var /* String */ eventOnClickBeforeJavascript = "";
					var /* String */ eventOnClickAfterJavascript = "";
					for (var i=0; i<insertJavascriptList.length ; i++) {
						var insertJavascript = insertJavascriptList[i];
						var event = insertJavascript.event;
						event = event.replace("on", "")
						event = event.toLowerCase();
						var where = insertJavascript.where;
						var javascriptContent = insertJavascript.javascriptContent;
						if (event == "click") {
							if (javascriptContent.charAt(javascriptContent.length -1) != ';')
								javascriptContent = javascriptContent + ";";
							if (where == "before") {
								eventOnClickBeforeJavascript = javascriptContent;
							}
							else {
								eventOnClickAfterJavascript = javascriptContent;
							}
						}
						else {
							eventsJavascript = eventsJavascript + getJavascript(insertJavascript);
						}
					}
					
					var newHrefElement = "<a href=\"#\"";
					newHrefElement += eventsJavascript;
					// event onclick
					newHrefElement += " onclick=\""
					newHrefElement += eventOnClickBeforeJavascript;
					newHrefElement += "new TabToggle('" + tabControlName + "','" + tabPageName + "'); return false;";
					newHrefElement += eventOnClickAfterJavascript;
					newHrefElement += "\"";
					if (tabPageWidth != null) {
						newHrefElement += "style=\"width:" + tabPageWidth + "\" ";
					}
					newHrefElement += ">";
					newHrefElement += tabPageTitle;
					newHrefElement += "</a>";
									
	  				liElement.innerHTML = newHrefElement;
				}
				else {
					var newHrefElement = "<a href=\"#\"";
					// event onclick
					newHrefElement += " onclick=\""
					newHrefElement += "new TabToggle('" + tabControlName + "','" + tabPageName + "'); return false;";
					newHrefElement += "\"";
					if (tabPageWidth != null) {
						newHrefElement += "style=\"width:" + tabPageWidth + "\" ";
					}
					newHrefElement += ">";
					newHrefElement += tabPageTitle;
					newHrefElement += "</a>";
									
	  				liElement.innerHTML = newHrefElement;
				}
			}
			
			// Update Style class	
			var insertStyleClassList = state.insertStyleClassList;
			if (insertStyleClassList != null) {
				for (var i=0; i<insertStyleClassList.length ; i++) {
					var insertStyleClass = insertStyleClassList[i];
					var where = insertStyleClass.where;
					var styleClass = insertStyleClass.styleClass;
					if (where == "tabPageHeader") {
						var tabPageHeader = getLITabPageHeader(tabControlName, tabPageName);
						if (tabPageHeader != null) {
							tabPageHeader.className = styleClass;
						}
					}
					else {
						if (where == "tabPageBody") {
							var tabPageBodyId = getHtmlId(HTML_TABPAGEBODY_KEY , tabControlName, tabPageName);
							var tabPageBody = document.getElementById(tabPageBodyId);
							if (tabPageBody != null) {
								tabPageBody.className = styleClass;
							}
						}
					}
				}
			}
			// Update State input Hidden of tabPage with statName
			var inputHiddenName  = HTML_TABPAGESTATE_HIDDEN_KEY + "_" + tabControlName + "_" + tabPageName;
			var inputHidden =  document.getElementById(inputHiddenName);
			if (inputHidden != null)
				inputHidden.value = stateName;
		}
	}
}

function getJavascript(insertJavascript) {
	var /* String */ eventJavascript = "";
	eventJavascript = " ";
	eventJavascript = eventJavascript + insertJavascript.event;
	eventJavascript = eventJavascript +  "=\"";
	eventJavascript = eventJavascript +  insertJavascript.javascriptContent;
	eventJavascript = eventJavascript + "\";";
	return eventJavascript;
}


