var nn6=document.getElementById&&!document.all;
var isdrag=false;
var y,x;
var oDragObj;

function moveMouse(e) {
	if (isdrag) {
		oDragObj.style.top  =  (nTY + e.clientY - y)+"px";
		oDragObj.style.left  =  (nTX + e.clientX - x)+"px";
		return false;
	}
}

function initDrag(e) {
	var oDragHandle = e.target || e.srcElement;
	var topElement = "HTML";
	while (oDragHandle.tagName != topElement && oDragHandle.className != "rebox-contents") {
		oDragHandle =  oDragHandle.parentNode || oDragHandle.parentElement;
	}
	if (oDragHandle.className=="rebox-contents") {
		isdrag = true;
		oDragObj = oDragHandle;
		oDragObjStyle=window.getComputedStyle(oDragObj);
		var oDragObjTop=oDragObjStyle.getPropertyValue("top");
		var oDragObjLeft=oDragObjStyle.getPropertyValue("left");
		nTY = parseInt(oDragObjTop+0);
		y=e.clientY;
		nTX = parseInt(oDragObjLeft+0);
		x=e.clientX;
		oDragObj.onmousemove=moveMouse;
		return false;
	}
}

function cancelDrag(){
	isdrag=false;
}
