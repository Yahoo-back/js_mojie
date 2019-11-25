<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
	
<form id="vehicle_add_form" method="post" >
<input type=hidden id="parentId" />
<div title="基本资料" class="easyui-panel">
<table border="1" bordercolor="#a0c6e5" class="tb-customer"  style="border-collapse:collapse;width: 100%" align="center" >
	<tr>
		<td style="width: 100px;" align="right">姓名：<font color="red">*</font></td> 
		<td><input  class="easyui-validatebox" data-options="required:true, validType:'name'" type="text" onblur="chkvalue(this)" name="userName" id="userName"  style="width: 200px"></input></td>
		<td for="email" align="right">登录账号:<font color="red">*</font></td> 
		<td><input class="easyui-validatebox" data-options="required:true, validType:'username'" type="text" onblur="chkvalue(this)" name="userCode" id="userCode"  style="width: 200px"></input></td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">性别:</td>
		<td>
			<select style="width: 200px" id="sex" name="sex">
				<option value="0">男</option>
				<option value="1">女</option>
			</select>
			<input type="hidden" name="accountType" id="accountType"  style="width: 200px" value="0"></input>
			<input type="hidden" name="id" id="id"  style="width: 200px"></input>
		</td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">身份证:<font color="red">*</font></td>
		<td><input class="easyui-validatebox"  class="easyui-validatebox" data-options="required:true" type="text" onblur="chkvalue(this)" name="identityCard" id="identityCard" style="width: 200px"></input></td>
		<td style="width: 100px;" align="right">工号:</td> 
		<td><input class="easyui-validatebox" type="text" name="jobNum" id="jobNum" onblur="chkvalue(this)" style="width: 200px"></input></td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">学历:</td>
		<td>
			<select style="width: 200px" id="degree" name="degree">
				<option value="0">本科以上</option>
				<option value="1">本科</option>
				<option value="2">大专</option>
				<option value="3">高中</option>
				<option value="4">初中</option>
			</select>
		</td>
		<td style="width: 100px;" align="right">电话:<font color="red">*</font></td>
		<td><input  class="easyui-validatebox" data-options="required:true,validType:'mobile'" type="text" name="phone" id="phone" style="width: 200px"></input></td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">公司邮箱:</td>
		<td><input class="easyui-validatebox" type="text" onblur="chkvalue(this)" name="companyEmail" id="companyEmail" style="width: 200px"></input></td>
		<td style="width: 100px;" align="right">个人邮箱:</td>
		<td><input class="easyui-validatebox" type="text" onblur="chkvalue(this)" name="personalEmail" id="personalEmail" style="width: 200px"></input></td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">QQ:</td>
		<td><input class="easyui-validatebox" type="text" onblur="chkvalue(this)" name="qq" id="qq" style="width: 200px"></input></td>
		<td style="width: 100px;" align="right">微信:</td>
		<td><input class="easyui-validatebox" type="text" onblur="chkvalue(this)" name="weixin" id="weixin" style="width: 200px"></input></td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">入职时间:</td>
		<td><input id="onjobDate"  onclick="WdatePicker()" class="Wdate" readonly="readonly" type="text" name="onjobDate" style="width: 200px"></td>
		<td style="width: 100px;" align="right">所属部门:</td>
		<td><input class="easyui-validatebox" type="text" name="department" id="department" style="width: 200px"></input></td>
	</tr>
		
	<tr>
		<td style="width: 100px; border-bottom:2px solid #ccc;" align="right">家庭住址:</td>
		<td colspan="3" style=" border-bottom:2px solid #ccc;"><input class="easyui-validatebox" type="text" name="liveAddr" id="liveAddr" style="width: 85%"></input></td>
	</tr>
	<tr>
		<div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color: white;height:215px; overflow:auto;">
			<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px;"></ul>
		</div>
	</tr>
	</table>
	</div>
	<script type="text/javascript">
		qryUser('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>');
	</script>
</form>