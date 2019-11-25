<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
	<input type="hidden" name="id" id="id"/>
	<div class="easyui-accordion">
		<div title="基本信息" class="easyui-panel">
			<table border="1" bordercolor="#a0c6e5" class="tb-customer"  style="border-collapse:collapse;width: 100%" align="center">
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>资讯标题：</td>
					<td colspan="3"><input class="easyui-validatebox" data-options="required:true" type="text" name="title" id="title" style="width: 500px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>资讯图片：</td>
					<td colspan="3">
						<button type="button" class="layui-btn" id="test1">上传图片</button>
						<table border="1" bordercolor="#ccc" style="border-collapse:collapse; padding: 4px; width: 100%" align="center">
							<tr id="zmwj" style="margin: 3px; display: block;">
							</tr>
						</table>
						<input id="sessionId" type="hidden" value="${pageContext.session.id}"/>
						<font color="red">最多上传1张图片</font>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>分类：</td>
					<td id="classify_td">
						<script type="text/javascript">
                            $(function () {
                                ajaxRequest("qryNewsClassifyAll",{"type" : "0"}, function(data){
                                    if(data.resultNode == "success"){
                                        var rows = data.rows;
                                        var temp = "";
                                        for(var i=0; i<rows.length; i++) {
                                            temp += "<input class='easyui-validatebox' type='checkbox' name='classify' id='classify_"+rows[i].id+"' value='"+rows[i].id+"'>" + rows[i].name + "&nbsp;&nbsp;";
                                        }
                                        $("#classify_td").html(temp);
                                    }
                                });
                            })
						</script>
					</td>
					<td style="width: 100px;" align="right"><font color="red">*</font>标签：</td>
					<td id="label_td">
						<script type="text/javascript">
                            $(function () {
                                ajaxRequest("qryNewsClassifyAll",{"type" : "1"}, function(data){
                                    if(data.resultNode == "success"){
                                        var rows = data.rows;
                                        var temp = "";
                                        for(var i=0; i<rows.length; i++) {
                                            temp += "<input class='easyui-validatebox' type='checkbox' name='label' id='label_"+rows[i].id+"' value='"+rows[i].id+"'>" + rows[i].name + "&nbsp;&nbsp;";
                                        }
                                        $("#label_td").html(temp);
                                    }
                                });
                            })
						</script>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">关联商品：</td>
					<td colspan="3" id="product_td">
						<script type="text/javascript">
                            $(function () {
                                ajaxRequest("qryProductListAll",null, function(data){
                                    if(data.resultNode == "success"){
                                        var rows = data.rows;
                                        var temp = "";
                                        for(var i=0; i<rows.length; i++) {
                                            temp += "<input class='easyui-validatebox' type='checkbox' name='product_id' id='product_id_"+rows[i].id+"' value='"+rows[i].id+"'>" + rows[i].name + "&nbsp;&nbsp;";
                                        }
                                        $("#product_td").html(temp);
                                    }
                                });
                            })
						</script>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">开始时间：</td>
					<td><input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" readonly="readonly" type="text" name="start_time" id="start_time" style="width: 40%"></input></td>
					<td style="width: 202px;" align="right">结束时间(不填写则代表无截止时间)：</td>
					<td><input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" readonly="readonly" type="text" name="end_time" id="end_time" style="width: 300px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">链接：</td>
					<td><input class="easyui-validatebox" type="text" name="url" id="url" style="width: 97%"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>状态：</td>
					<td colspan="3">
						<select style="width: 300px" class="easyui-validatebox" data-options="required:true" name="status" id="status">
							<option value="">--请选择--</option>
							<option value="0">发布</option>
							<option value="1">下线</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>排序：</td>
					<td colspan="3"><input class="easyui-validatebox" data-options="required:true, validType : 'integer'" type="text" name="sort" id="sort" style="width: 400px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>置顶：</td>
					<td colspan="3">
						<select style="width: 300px" class="easyui-validatebox" data-options="required:true" name="position_dd" id="position_dd">
							<option value="">--请选择--</option>
							<option value="0">默认</option>
							<option value="1">置顶</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div title="新闻资讯内容" class="easyui-panel" style="width: 100%; height: 540px">
			<div id="news_content_toolbar" class="toolbar" style="border: 1px solid #ccc;">
			</div>
			<div id="news_content" class="text" style="border: 1px solid #ccc; height: 480px;"> <!--可使用 min-height 实现编辑区域自动增加高度-->
			</div>

			<script type="text/javascript">
                var E = window.wangEditor
                var editor = new E('#news_content_toolbar', '#news_content');
                editor.customConfig.uploadImgServer = ctx  + '/editorUpload';
                editor.create()
			</script>
		</div>
	</div>
	<script type="text/javascript">
		$(function () {
		    setTimeout(function () {
				initPlugn();
                var view = '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>';
                if("1" == view) {
                    $("input").prop("disabled", "disabled");
                    $("select").prop("disabled", "disabled");
                }

                qryNews('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
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
