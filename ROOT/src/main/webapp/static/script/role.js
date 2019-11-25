$(function(){
	 var cust_tb = $.et.commonQuery('#cust_tb',{
		url:st.apiPath+'qryRoles',
		 fit:true,
		fitColumns:true,
		columns:[[
			{field:"roleId",title:"角色ID",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"roleCode",title:"角色代码 ",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"roleName",title:"角色名称 ",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
				return "";
			},formatter:function(value,row,index){
				return "<a href='javascript:void(0)'  onclick=add_user('"+row.roleId+"','"+row.roleName+"','"+row.roleCode+"') style='color:#FF4500;text-decoration:none' >修改 </a> <a href='javascript:void(0)'  onclick=del('"+row.roleId+"')  style='color:#FF4500;text-decoration:none' >删除</a> <a href='javascript:void(0)'  onclick=allocationMeuns('"+row.roleId+"','"+row.roleName+"')  style='color:#FF4500;text-decoration:none' >分配菜单</a> ";
			}}
		]]
	 });
	 cust_tb.refresh();
	 window.cust_tb=cust_tb;
});

function queryCust(){
	var par = [
			"roleId",
			"roleCode",
			"roleName"
	]
	var cnds = arrTjson(par);
	cust_tb.query(cnds);
}

function del(id){
	$.messager.confirm('提示', '系统将会删除该角色下拥有的菜单项，确定删除吗？', function(r){
        if (r){
        	ajaxRequest("deleteRoles",{"roleId":id}, function(data){
        		$.messager.alert('提示',data.resultNode,'info');
        		if(data.resultNode == "success"){
        			$("#add_user").dialog({ closed : true });
        			queryCust();
        		}
        	}); 
        }
    });
}

function add_user(id,roleName,roleCode){
	if(id != "" && id != null && id != undefined){
		$("#roleId").val(id);
		$("#roleName").val(roleName);
		$("#roleCode").val(roleCode);
	}else{
		$("#roleId").val("");
		$("#roleName").val("");
		$("#roleCode").val("");
	}
	
	$("#add_user").dialog({
		title : "添加角色",
		fit:true,
		closed : false, cache : false, modal : true,
		buttons : [ {
			text : "保存",
			iconCls:"icon-ok",
			handler : function() {
				if($("#roleName").val() == ""){
					$.messager.alert('提示','请输入角色！');
					return false;
				}
				var jsonData = $("#ff").serializeObject();
				var par = $.parseJSON(JSON.stringify(jsonData)); 
				$.messager.confirm('提示', '确定保存吗？', function(r){
			        if (r){
			        	ajaxRequest("saveRoles",par, function(data){
			        		$.messager.alert('提示',data.resultNode,'info');
			        		if(data.resultNode == "success"){
			        			$("#add_user").dialog({ closed : true });
			        			queryCust();
			        		}
			        	}); 
			        }
			    });
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

var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "ps", "N": "ps" }
	},
	callback: {
		beforeClick: beforeClick,
		onClick: onClick
	}
};

var log, className = "dark";
function beforeClick(treeId, treeNode, clickFlag) {
	className = (className === "dark" ? "":"dark");
	return (treeNode.click != false);
}
function onClick(event, treeId, treeNode, clickFlag) {
	// nothing to do but can't delete
}	
function allocationMeuns(id,roleName){
	$("#roleId_cnds").val(id);
	$("#roleName_cnds").val(roleName);
	$("#muen").css("display","block");
	ajaxRequest("qryMeunsByRole",{"roleId":id}, function(data){
		if(data.resultNode == "success"){
			$.fn.zTree.init($("#treeDemo"), setting, data.rows);
		}
	}); 
}

function saveRoleMeun(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var meuns = "";
	var role = $("#roleId_cnds").val();
	if(role == ""){
		$.messager.alert('提示','请到左侧选择要分配的角色！');
		return false;
	}
	if(null != nodes){
		$.each(nodes,function(i){
			meuns += nodes[i].id+",";
		});
		var par = {'roleId':role,'meunId':meuns};
		$.messager.confirm('提示', '确定保存吗？', function(r){
	        if (r){
	        	ajaxRequest("allocationMeuns",par, function(data){
	    			$.messager.alert('提示',data.resultNode,'info');
	    			if(data.resultNode == "success"){
	    				$("#muen").css("display","none");
	    			}
	    		}); 
	        }
	    });
	}
}

function reset(){
	$("#parentOrgId").val("0");
	$("#parentOrgName").val("");
	$("#orgId").val("");
	$("#orgName").val("");
	$("#status").val("1");
	$("#menuBtn").show();
}

$(function(){
	$('div[class="datagrid-view et-common-query-datagrid-view"]   input').keyup(function (event) {
        if (event.keyCode == "13") {
        	queryCust();
        }
    });
})

