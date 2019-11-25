 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<div class="easyui-layout" fit=true>
    <div data-options="region:'east',split:true,iconCls:'icon-reload'"" style="width:20%;">
    	<ul id="userd_role">
    		
    	</ul>
    </div>
    <div data-options="region:'center',iconCls:'icon-ok'" style="width:80%;">
    	<div id="p" class="easyui-panel" fit="true" title='角色列表' >
			<div class="list_title1">
				<a href="#" class="easyui-linkbutton color-orange" style="margin-right: 10px;" onclick="queryRole()">查询</a>
			</div> 
	    	<div id="role_list" height="width: 100%"></div>
    	</div>
    </div>
</div>
<script>
$(function(){
	var role_list = $.et.commonQuery('#role_list',{
		url:st.apiPath+'qryRoles',
		fitColumns:true,
		columns:[[
			{field:"roleId",title:"角色ID",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"roleName",title:"角色名称 ",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
			{field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
				return "";
			},formatter:function(value,row,index){
				return "<a href='javascript:void(0)'  onclick=allocation('"+row.roleId+"','"+row.roleName+"') style='color:#FF4500;text-decoration:none' >分配 </a>";
			}}
		]]
	 });
	role_list.refresh();
	window.role_list=role_list;
	//加载该用户已经选中的角色
	init();
})
function queryRole(){
	var par = [
				"roleId",
				"roleName"
		]
	var cnds = arrTjson(par);
	role_list.query(cnds);
}
function allocation(id,name){
	if($("#li"+id).html() != null){
		return;
	}
	var html = $("#userd_role").html();
	html+="<li id='li"+id+"'>"+name+"<input type=hidden name=roleId value='"+id+"'/><a href=# onclick=remove('li"+id+"')><img src=${base}/static/images/del.png></a></li>";
	$("#userd_role").html(html);
}

function remove(id){
	$("#"+id).remove();
}

function init(){
	var id = '<%=request.getParameter("id")%>';
	ajaxRequest("qryUserRole",{"userCode":id}, function(data){
		if(data.resultNode == "success"){
			var roles = data.rows;
			$.each(roles,function(i){
				allocation(roles[i].roleId,roles[i].roleName);
			});
		}
	}); 
}

</script>