<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
	<input type="hidden" name="id" id="id"/>
		<table border="1" bordercolor="#a0c6e5" class="tb-customer" style="border-collapse:collapse;width: 100%" align="center">
			<tr>
				<td style="width: 100px;" align="right"><font color="red">*</font>名称：</td>
				<td><input class="easyui-validatebox" data-options="required:true" type="text" name="name" id="name" style="width: 300px"></input></td>
				<td style="width: 100px;" align="right"><font color="red">*</font>位置：</td>
				<td>
					<select class="easyui-validatebox" data-options="required:true" name="position_dd" id="position_dd">
						<option value="">--请选择--</option>
						<option value="0">启动页</option>
						<option value="1">首页banner</option>
						<option value="2">首页滚动小喇叭</option>
						<option value="3">新闻资讯banner</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width: 100px;" align="right">推广类型：</td>
				<td>
					<select class="easyui-validatebox" id="associate_type" style="width: 300px">
						<option value="">--请选择--</option>
						<option value="0">商品</option>
						<option value="1">资讯</option>
					</select>
				</td>
				<td style="width: 100px;" align="right">推广内容：</td>
				<td>
					<select class="easyui-validatebox" name="associate_id" id="associate_id" style="width: 300px">
						<option value="">--请选择--</option>
					</select>
					<script type="text/javascript">
						$(function () {
                            $("#associate_type").change(function () {
                                $("#associate_id").html("<option value=''>--请选择--</option>");
                                if($(this).val() == "0") {
                                    //商品
                                    ajaxRequest("qryProductListAll",null, function(data){
                                        if(data.resultNode == "success"){
                                            var rows = data.rows;
                                            for(var i=0; i<rows.length; i++) {
                                                $("#associate_id").append("<option value='" + rows[i].id +"'>"+ rows[i].name +"</option>");
                                            }
                                        }
                                    });
                                } else if($(this).val() == "1") {
                                    //资讯
                                    ajaxRequest("qryNewsListAll",null, function(data){
                                        if(data.resultNode == "success"){
                                            var rows = data.rows;
                                            for(var i=0; i<rows.length; i++) {
                                                $("#associate_id").append("<option value='" + rows[i].id +"'>"+ rows[i].title +"</option>");
                                            }
                                        }
                                    });
                                }
                            })
                        });
					</script>
				</td>
			<tr>
			<td style="width: 100px;" align="right"><font color="red">*</font>图片：</td>
			<td colspan="3">
				<button type="button" class="layui-btn" id="test1">上传图片</button>
				<table border="1" bordercolor="#ccc" style="border-collapse:collapse; padding: 4px; width: 100%" align="center">
					<tr id="zmwj" style="margin: 3px; display: block;">
					</tr>
				</table>
				<input id="sessionId" type="hidden" value="${pageContext.session.id}"/>
				<font color="red">最多上传1张logo</font>
			</td>
			<tr>
				<td style="width: 100px;" align="right">链接：</td>
				<td colspan="3"><input class="easyui-validatebox" type="text" name="url" id="url" style="width: 500px"></input></td>
			</tr>
			<tr>
				<td style="width: 100px;" align="right">开始时间：</td>
				<td><input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" readonly="readonly" type="text" name="start_time" id="start_time" style="width: 300px"></input></td>
				<td style="width: 202px;" align="right">结束时间(不填写则代表无截止时间)：</td>
				<td><input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" readonly="readonly" type="text" name="end_time" id="end_time" style="width: 300px"></input></td>
			</tr>
			<tr>
				<td style="width: 100px;" align="right"><font color="red">*</font>排序：</td>
				<td><input class="easyui-validatebox" data-options="required:true, validType : 'integer'" type="text" name="sort" id="sort" style="width: 300px"></input></td>
				<td style="width: 100px;" align="right"><font color="red">*</font>状态：</td>
				<td>
					<select class="easyui-validatebox" data-options="required:true" name="status" id="status" style="width: 300px">
						<option value="">--请选择--</option>
						<option value="0">上架</option>
						<option value="1">下架</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width: 100px;" align="right">备注：</td>
				<td colspan="3">
					<textarea rows="5" cols="80" name="remark" id="remark"></textarea>
				</td>
			</tr>
		</table>
	<script type="text/javascript">
		$(function () {
		    setTimeout(function () {

				initPlugn();
                var view = '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>';
                if("1" == view) {
                    $("input").prop("disabled", "disabled");
                    $("select").prop("disabled", "disabled");
                    $("textarea").prop("disabled", "disabled");
                }

                qrySpreadConfig('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
			}, 200);

        });

        //上传
        function initPlugn() {
			layui.use('upload', function(){
				var $ = layui.jquery
						,upload = layui.upload;
				//普通图片上传
				var uploadInst = upload.render({
					elem: '#test1',
					url: ctx + '/upload;jsessionid=' + $("#sessionId").val(),
					before: function(obj){
						//预读本地文件示例，不支持ie8
						obj.preview(function(index, file, result){
							$('#demo1').attr('src', result); //图片链接（base64）

						});
					},
					done: function (data) {
						if ("success" == data.resultNode) {
							callBackFunction(data);
						} else {
							alert(data.resultNode);
						}
					},

					error: function(){
						//演示失败状态，并实现重传
						var demoText = $('#demoText');
						demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
						demoText.find('.demo-reload').on('click', function(){
							uploadInst.upload();
						});
					}
				});
			});
        }
	</script>
</form>
