<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
	<input type="hidden" name="id" id="id"/>
	<div class="easyui-accordion">
		<div title="基本信息" class="easyui-panel">
			<table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center">
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>商品：</td>
					<td colspan="3">
						<select style="width: 300px" class="easyui-validatebox layui-select" data-options="required:true" name="classify_id" id="classify_id">
							<option value="">--请选择--</option>
						</select>
						<script type="text/javascript">
							$(function () {
								ajaxRequest("qryVisitProductListAll",null, function(data){
									if(data.resultNode == "success"){
										var rows = data.rows;
										for(var i=0; i<rows.length; i++) {
											$("#classify_id").append("<option value='" + rows[i].id + "'>" + rows[i].name + "</option>")
										}
									}
								});
							})
						</script>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">预付款：</td>
					<td colspan="3"><input class="easyui-validatebox layui-input" type="text" name="money" id="money" style="width: 18.8%"></input>
						<b id="b2" color="red"></b>
					</td>
				</tr>
                <tr>
                    <td style="width: 100px;" align="right">访问次数：</td>
                    <td colspan="3"><input class="easyui-validatebox layui-input" type="text" name="count" id="count" style="width: 18.8%"></input>
                        <b id="b1" color="red"></b>
                    </td>
                </tr>
				</table>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(function () {
			setTimeout(function () {
				var view = '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>';
				if("1" == view) {
					$("select").prop("disabled", "disabled");
					var money = $("#b2").val();
					if(money == ""){
						$("#money").val(0);
					}
				}
				qryProduct('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>');
			}, 200);
		});

		$("#classify_id").change(function(){
			var id=$("#classify_id").val();
			visitCount(id);
		});

		function visitCount(id){
			if(id != "" && id != null && id != undefined){
				ajaxRequest("qryProductVisitCount",{"id":id}, function(data){
					if(data.resultNode == "success"){
						$("#b1").text("该商品今日已访问：" + data.rows.count + "次");
						$("#b1").val(data.rows.count);
					}
				});
			}else{
				$("#b1").text("");
				$("#b1").val("");
			}
		}

	</script>



</form>







