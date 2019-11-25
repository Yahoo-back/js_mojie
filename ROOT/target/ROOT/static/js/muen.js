//打开左侧菜单方法
function getchil(id){
	//重新封装list
	var s = [];
	for(var i in muens){
		if(muens[i].parent_muen_id == id ){
			var m = muens[i];
			var s2 = [];
			for(var j in muens){
				if(muens[j].parent_muen_id == m.muenid ){
					s2.push(muens[j]);
				}
			}
			m.list=s2;
			s.push(m);
		}
	}
	var data = {"rows":s};
	var html = template.render('dataListScoreName', data);
	$("#pre").html(html);
    $("#pre").find('div').eq(0).find('a').click();
}  

function enterChangePass(){
	$.openframe4Btn("修改密码","400px","350px",ctx+"/main/changePass",function(index, layero){
		if($("#oldpassword").iframe(index).val() == ""){
			$.layMsg("旧密码不能为空！","2");
			return false;
		}
		if($("#newpassword").iframe(index).val() == ""){
			$.layMsg("新密码不能为空！","2");
			return false;
		}
		if($("#password").iframe(index).val() == ""){
			$.layMsg("确认密码不能为空！","2");
			return false;
		}
		if($("#newpassword").iframe(index).val()!=$("#password").iframe(index).val()){	
			$.layMsg("两次密码不一样!","2");
			return false;
		}
		var par = {
			"oldpassword":$("#oldpassword").iframe(index).val(),
			"password":$("#password").iframe(index).val()
		}
		$.layConfirm('确认修改密码？', '1', function(r){
			if (r){
				var loading = $.layLoad(1);
				var jsonString = JSON.stringify(par);
				var enjson = encodeURIComponent(jsonString);
				$.ajax({
					url: ctx + "/main/api?cmd=changePassword",
					timeout: 30000,
					type: "POST",
					dataType: "json",
					data: {
						"cnds" : enjson
					},
					success:  function(data){
						$.layClose(loading);
						$.layMsg(data.resultNode);
						if(data.resultNode == "success"){
							window.location.href= ctx + "/loginOut";
						}
					}
				});
			}
		});
	});
}