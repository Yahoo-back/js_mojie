<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<form  id="changePassform" method="post" >
<table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center" >
	<tr>
		<td style="width: 100px;" align="right">旧密码：<font color="red">*</font></td> 
		<td><input class="easyui-validatebox" type="password" name="oldpassword" id="oldpassword"  style="width: 200px"></input></td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">新密码:<font color="red">*</font></td>
		<td><input class="easyui-validatebox" type="password" name="newpassword" id="newpassword" style="width: 200px"></input></td>
	</tr>
	<tr>
		<td style="width: 100px;" align="right">确认新密码:<font color="red">*</font></td>
		<td><input class="easyui-validatebox" type="password" name="password" id="password" style="width: 200px"></input></td>
	</tr>
	</table>
	<script type="text/javascript">
		
	</script>
</form>
