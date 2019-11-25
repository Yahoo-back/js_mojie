var configButton={
	onload:function(idcard,buttons0,buttons1){
		var isEdit = false ;
		$('#buttonArea td').each(function(){
				var td_id = $(this).attr("id");
				if("list_button" == td_id){
					$("#"+td_id).html(buttons0);
				}else{
					if(td_id.indexOf("form_left_button") >= 0){
						$("#"+td_id).html(buttons0);
						if(td_id.indexOf("_edit") >= 0){
							isEdit = true;
						}
					}else if(td_id.indexOf("form_right_button") >= 0){
						$("#"+td_id).html(buttons1);
					}
				}
		});
		if(isEdit){
			opt.optText(idcard);
		}
	},
	getButtons:function(resouces){
		return $.validate.optTab("",resouces);
	}
};
