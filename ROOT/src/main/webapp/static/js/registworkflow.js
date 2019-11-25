function registCall(tp_id,bizid,taskId,status,auditComment){
	var par = {
		"id":bizid,	
		"taskId":taskId,	
		"status":status,
		"cause":auditComment
	}
	switch(tp_id){
	case "1":
		ajaxRequest("fallbackCustomers",par, function(data){
			console.info(data.resultNode);
		}); 
		break;
	}
	
}