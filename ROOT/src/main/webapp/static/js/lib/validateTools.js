/**
 * Created with Eclipse. User: amao Date: 14-3-29 Time: 下午4:34
 */
(function($) {
	$.validate={
		optTab:function(url,dd){
			
			var rbdate = [];
			
			if(( undefined == dd || null == dd || "" == dd) && ( undefined == url || null == url || "" == url)){
				return ;
			}
			
			if(undefined != dd && null != dd && "" != dd)
			{
				rbdate = checkPop(eval("(" + dd + ")"));
			}
			else
			{
				$.ajax({
					type : "post",
					url : url,
					dataType : "json",
					error : function() {
					},
					success : function(n_date){
						rbdate = checkPop(n_date);
					},
					async: true
				});
			}
			return rbdate;
		}
	};
	
	
	var checkPop = function (data) {
		var rbdate = [];
		$.each(data,function(i){
			var s_id = data[i].TP_ID+"_"+data[i].SOURCE_NAME;
			var s_type = data[i].SOURCE_TYPE;
			var s_bt = data[i].IS_BT;
			var IS_BUTTON = data[i].IS_BUTTON;
			switch(s_type){
			case "1": // 不能看了
				// 先看到input框
				/*if(IS_BUTTON != "Y"){
					$("#"+s_id).attr("type","hidden");
					var hts = $("#"+s_id+"_INPUT").html();
					$("#"+s_id+"_INPUT").html(hts+"*********");
					
					$("#"+s_id+"_DIV").html("*********");
				}*/
				break;
			case "2":
				$("#"+s_id).hide();
				break;
			default:
				$("#"+s_id).removeAttr("disabled");
				$("#"+s_id).css("visibility","visible");
				// 只有 可以 的迎奥设置为必填
				if("Y" == s_bt){
					$("#"+s_id).attr({ isNull: 'true' });
				}else{
					$("#"+s_id).attr({ isNull: 'false' });
				}
				break;
			}
			rbdate.push(s_id);
		});
		
		return rbdate;
		
	};
})(jQuery);
