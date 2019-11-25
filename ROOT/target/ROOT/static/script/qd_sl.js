Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth()+1,                 //月份
		"d+" : this.getDate(),                    //日
		"h+" : this.getHours(),                   //小时
		"m+" : this.getMinutes(),                 //分
		"s+" : this.getSeconds(),                 //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt)) {
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	for(var k in o) {
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		}
	}
	return fmt;
}

var now = new Date();
var timeStart = now.format('yyyy-MM-dd hh:mm:ss');
// var tomorrow = new Date(now.getTime() + 1 * 24 * 60 * 60 * 1000);
// var timeEnd = tomorrow.format("yyyy-MM-dd hh:mm:ss");
$('#update_time').val(timeStart);
// $('#visit_time_TO_cnd').val(timeEnd);
$(function() {
	 var cust_tb = $.et.commonQuery('#cust_tb',{
		url:st.apiPath+'qryQuDaoSl',
		 fit:true,
		fitColumns:true,
		singleSelect:false,
		columns:[[
			{field:"source",title:"渠道名称",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
					var select = "<select id='source_cnd' style='width: 100%;' name='source_cnd'>";
					select += ("<option value=''>--请选择--</option>");
					ajaxRequest("qryMySourceAll",null, function(data){
						if(data.resultNode == "success"){
							var row = data.rows;
							for(var i=0; i<row.length; i++) {
								$("#source_cnd").append("<option value='"+row[i].source+"'>"+row[i].source+"</option>");
							}
						}
					});
					return select;
				}},
			// {field:"user_name",title:"渠道名称 ",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			// {field:"uvCount",title:"实时点击量",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			// {field:"count",title:"实时注册量",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"sl",title:"缩量百分比",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			// {field:"slUv",title:"缩量点击量",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			// {field:"slCount",title:"缩量注册量",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"update_time",title:"缩量操作时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},

			{field:"BIZ_OPT",title:"操作",width:150,sortable:true,headalign:"center",fuzzy_condition:function(){
				return "";
			},formatter:function(value,row,index){
				var btnLink = "";
					value = '<a href="javascript:void(0);" onclick="javascript:views('+row.id+');" style="color: #FF4500;">查看</a>';
					value += '&nbsp;<a href="javascript:void(0);" onclick="javascript:add_user('+row.id+', '+row.status+');" style="color: #FF4500;">修改</a>';
					return value;
			}}
		]],
		onDblClickRow:function(index,row){
			views(row.id);
			add_user(row.id);
		}
	 });
	 cust_tb.refresh();
	 window.cust_tb=cust_tb;
});

function queryCust(){
	var par = [
			"sl",
			"source",
			"update_time"
	]
	var cnds = arrTjson(par);
	cust_tb.query(cnds);
}


function views(id){
	var url = ctx+"/qudao/form_qdsl?id="+id;
	$("#add_user").dialog({
		title : "查看渠道缩量详情",  href:url,
		fit:true,
		/*
		width : "900", height : "700",
		top:parseInt($(window).height()/2 - 350),*/
		closed : false, cache : false, modal : true,
		buttons : [  {
			text : "关闭",
			iconCls:"icon-cancel",
			handler : function() {
				$("#add_user").dialog({ closed : true });
			}
		} ]
	});
}



function add_user(id){
	if(id != "" && id != null && id != undefined){
	}
	var url = "";
	var title = "";
		url = ctx+"/qudao/form_qdsl?id="+id;
		title = "修改渠道缩量";
	
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

function qryUser(id){
	if(id == "" || id == null || id == undefined){
		return;
	}
	ajaxRequest("qrySourceByLgnId",{"id":id}, function(data){
		if(data.resultNode == "success"){
			var json = jQuery.parseJSON(data.rows)  ;
			var login = json.login;
			var user = json.user;
			$("#userCode").val(user.source);
			$("#sl").val(user.sl);
			$("#userName").val(login.userName);
			$("#update_time").val(user.updateTime);
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

	par.id = id;
	par.user_name = $("#userName").val();
	par.user_code = $("#userCode").val();
	par.sl = $("#sl").val();
	par.updateTime = $("#update_time").val();
	$.messager.confirm('提示', '确定保存吗？', function(r){
        if (r){
        	ajaxRequest("saveQdaoSlList",par, function(data){
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
