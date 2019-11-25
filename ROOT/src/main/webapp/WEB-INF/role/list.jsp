<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<html style="height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
var ctx = '${base}';
</script>
<script type="text/javascript" src="${base }/static/js/main.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${base }/static/js/ztree/css/demo.css" /> --%>
<link rel="stylesheet" type="text/css" href="${base }/static/js/ztree/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${base }/static/js/ztree/ztree.js"></script>
<script type="text/javascript" src="${base }/static/js/ztree/ztree.excheck.js"></script>
<%-- <script type="text/javascript" src="${base }/static/js/ztree/ztree.edit.js"></script> --%>
<link rel="stylesheet" type="text/css" href="${base }/static/css/css/css.css" />
	<script type="text/javascript" src="${base }/static/script/role.js"></script>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>

</head>
<body  style="height: 100%; overflow: hidden">

		<div class="easyui-layout" fit=true>
			<div data-options="region:'east',split:true,iconCls:'icon-reload'"" title="分配菜单项" style="width:600px;" >
				<div id="muen"  style="display: none" >
					当前分配的角色：<input name="roleName_cnd" type="text" class="input-coom" id="roleName_cnds" style="width: 210px; height: 30px;" disabled="disabled"><b color=red>*</b>
							<input name="roleId_cnd" type="hidden" class="input-coom" id="roleId_cnds" style="width: 210px; height: 30px;" >
					<div id="treeDemo" class="ztree"></div>
					<br>
					<div>
						<ul style="list-style-type: none;" id="wfmbtns">
							<li style="display: inline;line-height: 10px"><a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-small" onclick="saveRoleMeun()"  style="width:120px;background:#faa732; color:#fff;">保存</a></li> &nbsp;&nbsp;&nbsp;
						</ul>
					</div>
				</div>
			</div>
			<div data-options="region:'center',title:'角色列表',iconCls:'icon-ok'">
				<div class="list_title1">
					<a href="#" class="easyui-linkbutton color-orange" onclick="queryCust()">查询</a>
					<a href="#" class="easyui-linkbutton color-orange" onclick="add_user('','')">新增</a>
				</div>

				<div class="h" style="min-height: 85%; ">
					<div id="p" class="easyui-panel" fit="true"  >

				<div id="cust_tb" height="width: 100%"></div>
			</div>




			</div>
	</div>



    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo1" class="ztree" style="margin-top:0; width:210px;"></ul>
	</div>
    <div id="add_user">
		<form id="ff">
        	<div class="form-new" style="float: center">
				<ul>
					<li><label>角色代码：</label>
						<input name="roleCode" type="text" class="input-coom" id="roleCode" style="width: 210px; height: 30px;" ><font color=red>*</font>
					</li>
					<li><label>角色名称：</label>
						<input name="roleName" type="text" class="input-coom" id="roleName" style="width: 210px; height: 30px;" ><font color=red>*</font>
						<input name="roleId" type="hidden" class="input-coom" id="roleId" style="width: 210px; height: 30px;" >
					</li>
				</ul>
			</div>
		</form>
	</div>






</body>

</html>
