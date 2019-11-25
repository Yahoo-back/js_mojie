$(function() {
	 var cust_tb = $.et.commonQuery('#cust_tb',{
		url:st.apiPath+'qryUserListByType',
		 fit:true,
		fitColumns:true,
		singleSelect:false,
		columns:[[
			{field:"user_code",title:"渠道账号 ",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"user_name",title:"渠道名称 ",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			// {field:"url",title:"H5url",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"uvurl",title:"H5Uvurl",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			// {field:"sex",title:"性别",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
			// 	var select = "<select id='sex_cnd' style='width: 100%;' name='loan_from'>";
			// 	select += ("<option value=''>--请选择--</option>");
			// 	select += ("<option value='0'>男</option>");
			// 	select += ("<option value='1'>女</option>");
			// 	return select;
			// },formatter:function(val){
			// 	switch(val){
			// 	case "0":
			// 		return "男";
			// 	case "1":
			// 		return "女";
			// 	default:
			// 		return "未知";
			// 	}
			// }},
			// {field:"identity_card",title:"身份证号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"status",title:"状态",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
					var select = "<select id='status_cnd' style='width: 100%;' name='custstatus'>";
					select += ("<option value=''>--请选择--</option>");
					select += ("<option value='2'>在职</option>");
					select += ("<option value='4'>离职</option>");
					return select;
				},formatter:function(val){
					switch(val){
					case "2":
						return "在职";
					case "4":
						return "离职";
					}
				}
			},
			{field:"BIZ_OPT",title:"操作",width:150,sortable:true,headalign:"center",fuzzy_condition:function(){
				return "";
			},formatter:function(value,row,index){
				var btnLink = "";
				if("0" == row.status || "4" == row.status ){
					value = '<a href="javascript:void(0);" onclick="javascript:views('+row.lgn_id+', '+row.status+', '+row.account_type+');" style="color: #FF4500;">查看</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:getwork('+row.lgn_id+');" style="color: #FF4500;">复职</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:delete_user('+row.lgn_id+');" style="color: #FF4500;">删除</a>';
					return value;
				}else if("2" == row.status){
					value = '<a href="javascript:void(0);" onclick="javascript:views('+row.lgn_id+');" style="color: #FF4500;">查看</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:outwork('+row.lgn_id+');" style="color: #FF4500;">离职</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:edit_user('+row.lgn_id+', '+row.status+');" style="color: #FF4500;">修改</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:allocationRoles('+row.lgn_id+');" style="color: #FF4500;">分配岗位</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:resetPass('+row.lgn_id+');" style="color: #FF4500;">重置密码</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:delete_user('+row.lgn_id+');" style="color: #FF4500;">删除</a>';
					return value;
				}
			}}
		]],
		onDblClickRow:function(index,row){
			views(row.lgn_id);
		}
	 });
	 cust_tb.refresh();
	 window.cust_tb=cust_tb;
});

function queryCust(){
	var par = [
			"user_name",
			"user_code",
			"sex",
			"identity_card",
			"status",
	]
	var cnds = arrTjson(par);
	cust_tb.query(cnds);
}


function views(id){
	var url = ctx+"/qudao/form_qudao?id="+id+"&view=1";
	$("#add_user").dialog({
		title : "查看信息",  href:url,
		fit:true,
		/*
		width : "900", height : "700",
		top:parseInt($(window).height()/2 - 350),*/
		closed : false, cache : false, modal : true,
		onOpen:function(){
			$("input").prop("disabled", "disabled");
		},
		onClose : function() {
			$("input").prop("disabled", false);
			$("select").prop("disabled", false);
		},
		buttons : [  {
			text : "关闭",
			iconCls:"icon-cancel",
			handler : function() {
				$("#add_user").dialog({ closed : true });
			}
		} ]
	});
}



function outwork(id,status){

	$.messager.confirm('提示', '确定该员工已经离职吗？', function(r){
		if (r){
			ajaxRequest("updateUserStatus",{"id":id,"status":"4"}, function(data){
				$.messager.alert('提示',data.resultNode,'info');
				if(data.resultNode == "success"){
					queryCust();
				}
			});
		}
	});
}
function getwork(id,status){
	$.messager.confirm('提示', '确定给该员工复职吗？', function(r){
		if (r){
			ajaxRequest("updateUserStatus",{"id":id,"status":"2"}, function(data){
				$.messager.alert('提示',data.resultNode,'info');
				if(data.resultNode == "success"){
					queryCust();
				}
			});
		}
	});
}
function delete_user(id,status){

	$.messager.confirm('提示', '确定删除该员工吗？', function(r){
		if (r){
			ajaxRequest("updateUserStatus",{"id":id,"status":"5"}, function(data){
				$.messager.alert('提示',data.resultNode,'info');
				if(data.resultNode == "success"){
					queryCust();
				}
			});
		}
	});
}
function goSubmit(id,status,type,user_name){
	if(status == "2"){
		$.messager.alert('提示','非在职状态下才能提交！');
		return false;
	}

	$.messager.confirm('提示', '确定就職吗？', function(r){
		if (r){
			var result = "2";
				ajaxRequest("updateUserStatus",{"id":id,"status":result}, function(data){
					$.messager.alert('提示',data.resultNode,'info');
					if(data.resultNode == "success"){
						queryCust();
					}
				});
			}

		});
}

function resetPass(id){
	$.messager.confirm('提示', '确定重置密码吗？', function(r){
		if (r){
			ajaxRequest("resetPassword",{"id":id,"password":"123456"}, function(data){
				$.messager.alert('提示',data.resultNode,'info');
				if(data.resultNode == "success"){
					queryCust();
				}
			});
		}
	});
}


function add_user(id,status){
	if(id != "" && id != null && id != undefined){
	}

	var url = "";
	var title = "";
		url = ctx+"/qudao/form_qudao?id="+id;
		title = "添加渠道商";

	$("#add_user").dialog({
		title : title,  href: url,
		fit:true,
		/*
		width : "800", height : "700",
		top:parseInt($(window).height()/2 - 350),*/
		closed : false, cache : false, modal : true,
		buttons : [ {
			text : "保存",
			iconCls:"icon-ok",
			handler : function() {
				saveUser(id);
			}
		}, {
			text : "关闭",
			iconCls:"icon-cancel",
			handler : function() {
				$("#add_user").dialog({ closed : true });
			}
		} ]
	});
}

function edit_user(id,status){
	if(id != "" && id != null && id != undefined){
	}

	var url = "";
	var title = "";
	url = ctx+"/qudao/form_qudao?id="+id;
	title = "修改渠道商";

	$("#add_user").dialog({
		title : title,  href: url,
		fit:true,
		/*
		width : "800", height : "700",
		top:parseInt($(window).height()/2 - 350),*/
		closed : false, cache : false, modal : true,
		buttons : [ {
			text : "保存",
			iconCls:"icon-ok",
			handler : function() {
				saveEditUser(id);
			}
		}, {
			text : "关闭",
			iconCls:"icon-cancel",
			handler : function() {
				$("#add_user").dialog({ closed : true });
			}
		} ]
	});
}
function qryUser(id){
	if(id == "" || id == null || id == undefined){
		return;
	}
	ajaxRequest("qryUserByLgnId",{"id":id}, function(data){
		if(data.resultNode == "success"){
			var json = jQuery.parseJSON(data.rows)  ;
			var login = json.login;
			$("#userCode").val(login.userCode);
			$("#id").val(login.lgnId);
			var a = login.lgnId;
			$("#a").val(a);
			var ctm = json.user;
			$.each(ctm,function(i){
					$("#"+i).val(ctm[i]);
			});
			//查询完验证 正确性
			$("#vehicle_add_form").form('validate');
		}
	});
}

function opens(url){
	window.open(url,"_blank");
}

function init(){
	$("#zmwj").html("");
}

function saveUser(id){
	if(!$("#vehicle_add_form").form('validate')){
		return false;
	}
	var jsonData = $("#vehicle_add_form").serializeObject();
	var par = $.parseJSON(JSON.stringify(jsonData));
	var id = $("#a").val();
	par.id = $("#a").val();
	$.messager.confirm('提示', '确定保存吗？', function(r){
        if (r){
        	ajaxRequest("saveUserQudao",par, function(data){
        		$.messager.alert('提示',data.resultNode,'info');
        		if(data.resultNode == "success"){
        			$("#add_user").dialog({ closed : true });
        			$("#cust_tb").datagrid('reload');
        			//queryCust();
        		}
        	});
        }
    });
}

function saveEditUser(id){
	if(!$("#vehicle_add_form").form('validate')){
		return false;
	}
	var jsonData = $("#vehicle_add_form").serializeObject();
	var par = $.parseJSON(JSON.stringify(jsonData));
	par.id = id;
	$.messager.confirm('提示', '确定保存吗？', function(r){
		if (r){
			ajaxRequest("saveUserQudao",par, function(data){
				$.messager.alert('提示',data.resultNode,'info');
				if(data.resultNode == "success"){
					$("#add_user").dialog({ closed : true });
					$("#cust_tb").datagrid('reload');
					//queryCust();
				}
			});
		}
	});
}
function allocationRoles(id){
	$("#showRoles").dialog({
		title : "角色分配",  href: ctx+"/users/role_list?id="+id,
		fit:true,
		/*
		width : "800", height : "400",
		top:parseInt($(window).height()/2 - 250),*/
		closed : false, cache : false, modal : true,
		buttons : [ {
			text : "保存",
			iconCls:"icon-ok",
			handler : function() {
				saveUserRole(id);
			}
		}, {
			text : "关闭",
			iconCls:"icon-cancel",
			handler : function() {
				$("#showRoles").dialog({ closed : true });
			}
		} ]
	});
}

function saveUserRole(id){
	var o1 = document.getElementsByName("roleId");
	if(o1 == null || o1 == undefined){
		$.messager.alert('提示','没有选任何角色，不能保存！');
		return false;
	}
	var arr = "";
	for(var i in o1){
		if(undefined !=  o1[i].value){
			arr += o1[i].value +",";
		}
	}
	$.messager.confirm('提示', '确定保存吗？', function(r){
        if (r){
        	ajaxRequest("allocationRoles",{"roleId":id,"roleId_cnd":arr}, function(data){
        		$.messager.alert('提示',data.resultNode,'info');
        		if(data.resultNode == "success"){
        			$("#showRoles").dialog({ closed : true });
        		}
        	});
        }
    });
}

$(function(){
	$('div[class="datagrid-view et-common-query-datagrid-view"]   input').keyup(function (event) {
        if (event.keyCode == "13") {
        	queryCust();
        }
    });
})
