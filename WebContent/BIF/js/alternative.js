function ALT() {
	this.page;
	this.object;
	this.displayTime = 5;
	this.OBJECT_NAME = "popup0215632598452141554";
	
	this.status = false;
	
	//setting timer to display message
	this.secs = 5;
	this.timerID = null;
	this.timerRunning = false;
	this.delay = 1000;
	
	this.init = function(page) {
		this.page = page;
		if(document.getElementById(this.OBJECT_NAME) == undefined) {
			this.createPOPUP();
		} else { 
			this.object = document.getElementById(this.OBJECT_NAME);
		}
	};
	
	this.createPOPUP = function () {
		var alt = document.createElement('div');
		alt.className = 'popupAlternative';
		this.object = alt; 
		alt.id = this.OBJECT_NAME;
		alt.style.display = 'none';
		this.page.appendChild(alt);
	};
	
	this.display = function (text) {
		//display content
		this.object.style.display = '';
		this.object.innerText = text;
		//start clock
		//this.InitializeTimer();
		
	};
	
	this.hidden = function () {
	    this.status = false;
	    //hidden object
	    this.object.style.display = 'none';
		//stop current clock
	    //this.StopTheClock();
	};
	
	//for timer
	this.InitializeTimer = function () {
		if (this.status) {
			return;
		} else {
			this.status = true;
		}
	    // Set the length of the timer, in seconds
	    this.secs = this.displayTime;
	    //stop current clock
	    this.StopTheClock();
	    //start new clock
	    this.StartTheTimer();
	}
	
	this.StopTheClock = function ()
	{
	    if(this.timerRunning)
	        clearTimeout(this.timerID);
	    this.timerRunning = false;
	}

	this.StartTheTimer = function ()
	{
	    if (this.secs == 0) {
	    	this.hidden();
	    } else {
	        this.secs -= 1;
	        this.timerRunning = true;
	        this.timerID = setTimeout("StartTheTimer()", this.delay);
	    }
	}
	
	this.changePosition = function (e) {
		if (this.object != undefined && e != undefined) {
			var cur = this.getPosition(e);
			//include menu length
			var maxLength = parseFloat(300 + cur.x);  
			if(maxLength > screen.width) {
				this.object.style.left = parseFloat(cur.x - 300);
				this.object.style.top = cur.y;
			} else {
				this.object.style.left = cur.x;
				this.object.style.top = cur.y;
			}
		}
	}
	
	this.getPosition = function (e) {
	    e = e || window.event;
	    var cursor = {x:0, y:0};
	    if (e.pageX || e.pageY) {
	        cursor.x = e.pageX;
	        cursor.y = e.pageY;
	    } 
	    else {
	    	
	        var de = document.documentElement;
	        var b = document.body;
	        cursor.x = e.clientX + 
	            (de.scrollLeft || b.scrollLeft) - (de.clientLeft || 0) + 5;
	        cursor.y = e.clientY + 
	            (de.scrollTop || b.scrollTop) - (de.clientTop || 0) + 5;
	    }
	    return cursor;
	}
 

}