<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
	
<form id="vehicle_add_form" method="post" >
<input type=hidden id="parentId" />
<div title="缩量详情" class="easyui-panel">
<table border="1" bordercolor="#a0c6e5" class="tb-customer"  style="border-collapse:collapse;width: 100%" align="center" >
	<tr>
		<td style="width: 100px;" align="right">渠道名：<font color="red">*</font></td>
		<td><input  class="easyui-validatebox" data-options="required:true, validType:'name'" type="text" onblur="chkvalue(this)" name="userName" id="userName"  style="width: 200px"></input></td>
		<td for="email" align="right">渠道账号:<font color="red">*</font></td>
		<td><input class="easyui-validatebox" data-options="required:true, validType:'username'" type="text" onblur="chkvalue(this)" name="userCode" id="userCode"  style="width: 200px"></input></td>
	</tr>

	<tr>
		<td style="width: 100px;" align="right">渠道缩量百分比：<font color="red">*</font></td>
		<td><input  class="easyui-validatebox" data-options="required:false, " type="text" onblur="chkvalue(this)" name="sl" id="sl"  style="width: 200px"></input></td>
		<td  style="width: 100px;" for="email" align="right">渠道缩量需改时间:<font color="red">*</font></td>

		<td>
			<input id='update_time' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class='Wdate' readonly='readonly' type='text' name='update_time' style="width: 150px;float:left">
<%--			<input id='update_time' onclick="WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss'})" class='Wdate' readonly='readonly' type='text' name='update_time' style="width: 150px;float:left">--%>
<%--			<input class="easyui-validatebox" data-options="required:false, " type="" onblur="chkvalue(this)" name="update_time" id="update_time"  style="width: 200px">--%>

<%--			</input>--%>
		</td>
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