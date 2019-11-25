<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
	<input type="hidden" name="id" id="id"/>
		<table border="1" bordercolor="#a0c6e5" class="tb-customer" style="border-collapse:collapse;width: 100%" align="center">
			<tr>
				<td style="width: 100px;" align="right"><font color="red">*</font>名称(区间)：</td>
				<td colspan="3"><input class="easyui-validatebox" data-options="required:true" type="text" name="item_value" id="item_value" style="width: 500px"></input></td>
			</tr>
			<tr>
				<td style="width: 100px;" align="right"><font color="red">*</font>是否使用：</td>
				<td>
					<select class="easyui-validatebox" data-options="required:true" name="is_use" id="is_use" style="width: 300px">
						<option value="">--请选择--</option>
						<option value="0">不启用</option>
						<option value="1">启用</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width: 100px;" align="right"><font color="red">*</font>排序：</td>
				<td>
					<input class="easyui-validatebox" type="text" data-options="required:true, validType : 'integer'" name="xh" id="xh" style="width: 300px"></input>
				</td>
			</tr>
		</table>
	<script type="text/javascript">
		$(function () {
		    setTimeout(function () {
                var view = '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>';
                if("1" == view) {
                    $("input").prop("disabled", "disabled");
                    $("select").prop("disabled", "disabled");
                }

                qryDict('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
			}, 200);
        });
	</script>
</form>
