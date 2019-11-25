<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
	<input type="hidden" name="id" id="id"/>
	<div class="easyui-accordion">
		<div title="基本信息" class="easyui-panel">
			<table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center">
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>商品名称：</td>
					<td colspan="3"><input class="easyui-validatebox layui-input" data-options="required:true" type="text" name="name" id="name" style="width: 500px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>logo：</td>
					<td colspan="3">
						<button type="button" class="layui-btn" id="test1">上传图片</button>
						<table border="1" bordercolor="#ccc" style="border-collapse:collapse; padding: 4px; width: 100%" align="center">
							<tr id="zmwj" style="margin: 3px; display: block;">
							</tr>
						</table>
                        <input id="sessionId" type="hidden" value="${pageContext.session.id}"/>
						<font color="red">最多上传1张logo</font>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>分类：</td>
					<td colspan="3">
						<select style="width: 300px" class="easyui-validatebox layui-select" data-options="required:true" name="classify_id" id="classify_id">
							<option value="">--请选择--</option>
						</select>
						<script type="text/javascript">
							$(function () {
								ajaxRequest("qryProductClassifyAll",null, function(data){
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
					<td style="width: 100px;" align="right"><font color="red">*</font>利息：</td>
					<td><input class="easyui-validatebox layui-input" data-options="validType:'length[0,8]',required:true" type="text" placeholder="请输入小数或者整数" name="interest" id="interest" style="width: 200px"></input><input class="easyui-validatebox layui-input" value="‰" type="text" style="width: 20px"></input></td>
					<td style="width: 100px;" align="right"><font color="red">*</font>可授信额度：</td>
					<td>
						<select style="width: 300px" class="easyui-validatebox layui-select" data-options="required:true" name="money" id="money">
							<option value="">--请选择--</option>
						</select>
						<script type="text/javascript">
							$(function () {
								ajaxRequest("qryDictByDataType",{"data_type" : "PRICE_INTERVAL"}, function(data){
									if(data.resultNode == "success"){
										var rows = data.rows;
										for(var i=0; i<rows.length; i++) {
											$("#money").append("<option value='" + rows[i].id + "'>" + rows[i].itemValue + "</option>")
										}
									}
								});
							})
						</script>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>分期方式：</td>
					<td>
						<input class="easyui-validatebox" data-options="required:true" type="radio" name="perio_way" id="perio_way_0" style="width: 50px" value="0">按天</input>
						<input class="easyui-validatebox" data-options="required:true" type="radio" name="perio_way" id="perio_way_1" style="width: 50px" value="1">按月</input>
					</td>
					<td style="width: 100px;" align="right"><font color="red">*</font>分期期数：</td>
					<td>
						<input class="easyui-validatebox layui-input" type="text" name="periodization" id="periodization" style="width: 300px"></input> 天/月(多个请用逗号隔开)
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>链接：</td>
					<td colspan="3"><input class="easyui-validatebox layui-input" data-options="required:true" type="text" name="link" id="link" style="width: 99%"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>结算方式：</td>
					<td colspan="3">
						<input class="easyui-validatebox" data-options="required:true" type="checkbox" style="width: 50px;margin-right:-15px;margin-left: -15px" name="settle_cpa" id="settle_cpa" value="0">CPA</input>&nbsp;
						<input  class="easyui-validatebox layui-input" data-options="validType:['intOrFloat','length[0,10]'],prompt:'整数或小数'" type="number"  style="width: 90px" name="settle_way_cpa" id="settle_way_cpa"></input>
<%--						<input class="easyui-validatebox" data-options="required:true" type="checkbox"  style="width: 50px;margin-right:-15px" name="settle_cps" id="settle_cps" value="1">CPS</input>&nbsp;--%>
<%--						<input class="easyui-validatebox layui-input" data-options="validType:['intOrFloat','length[0,10]'],prompt:'整数或小数'" type="number"  style="width: 90px" name="settle_way_cps" id="settle_way_cps"></input>--%>
					</td>

					<script type="text/javascript">
						$(function () {

							/*if($('input[name="settle_cpa"]').data('checked',false)){
                                $('input[name="settle_way_cpa"]').validatebox({
                                    disabled: true
                                });
                            }

                            if($('input[name="settle_cps"]').data("checked", false)){
                                $('input[name="settle_way_cps"]').validatebox({
                                    disabled : true
                                })
                            }*/

							$('input[name="settle_cpa"]').click(function () {
								if($('input[name="settle_cpa"]:checked').val() == '0') {
									$("#settle_way_cpa").validatebox({
										required: true,
										validType : ['intOrFloat','length[0,10]']
									});
									/*$('input[name="settle_way_cpa"]').validatebox({
                                        disabled: false
                                    });*/
								} else {
									$("#settle_way_cpa").validatebox({
										required: false
									});
									/*$('input[name="settle_way_cpa"]').validatebox({
                                        disabled: true
                                    });*/
								}
							});

							$('input[name="settle_cps"]').click(function () {
								if($('input[name="settle_cps"]:checked').val() == '1') {
									$("#settle_way_cps").validatebox({
										required: true,
										validType : ['intOrFloat','length[0,10]']
									});
									/*$('input[name="settle_way_cps"]').validatebox({
                                        disabled: false
                                    });*/
								} else {
									$("#settle_way_cps").validatebox({
										required: false
									});
									/*$('input[name="settle_way_cps"]').validatebox({
                                        disabled: true
                                    });*/
								}
							});
						})
					</script>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>结算周期：</td>
					<td colspan="3"><input class="easyui-validatebox layui-input" placeholder="'填写结算周期，“如日结”，“周结”，“每周五结算”等'" data-options="required:true,validType:'length[0,30]'" type="text" name="settle_cycle" id="settle_cycle" style="width: 400px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>申请条件：</td>
					<td colspan="3" id="apply_require_td">
						<script type="text/javascript">
							$(function () {
								ajaxRequest("qryDictByDataType",{"data_type" : "APPLY_REQUIRE"}, function(data){
									if(data.resultNode == "success"){
										var rows = data.rows;
										var temp = "";
										for(var i=0; i<rows.length; i++) {
											temp += "<input type='checkbox' name='apply_require' id='apply_require_" + rows[i].id + "' value='" + rows[i].id + "'>"+rows[i].itemValue+"</input>&nbsp;&nbsp;";
										}
										$("#apply_require_td").html(temp);
									}
								});
							})
						</script>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>申请资料：</td>
					<td colspan="3" id="apply_data_td">
						<script type="text/javascript">
							$(function () {
								ajaxRequest("qryDictByDataType",{"data_type" : "APPLY_DATA"}, function(data){
									if(data.resultNode == "success"){
										var rows = data.rows;
										var temp = "";
										for(var i=0; i<rows.length; i++) {
											temp += "<input type='checkbox' name='apply_data' id='apply_data_" + rows[i].id + "' value='" + rows[i].id + "'>"+rows[i].itemValue+"</input>&nbsp;&nbsp;";
										}
										$("#apply_data_td").html(temp);
									}
								});
							})
						</script>
					</td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">费用说明：</td>
					<td colspan="3"><input class="easyui-validatebox  layui-input" type="text" name="ktx_desc" id="ktx_desc" style="width: 400px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>状态：</td>
					<td>
						<select style="width: 300px" class="easyui-validatebox layui-select" data-options="required:true" name="status" id="status">
							<option value="">--请选择--</option>
							<option value="0">下架</option>
							<option value="1">上架</option>
						</select>
					</td>
					<td style="width: 100px;" align="right"><font color="red">*</font>排序：</td>
					<td><input class="easyui-validatebox  layui-input" data-options="required:true, validType : 'integer'" type="text" name="sort" id="sort" style="width: 400px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>首页热门：</td>
					<td>
						<select style="width: 300px" class="easyui-validatebox layui-select" data-options="required:true" name="is_hot" id="is_hot">
							<option value="">--请选择--</option>
							<option value="0">否</option>
							<option value="1">是</option>
						</select>
					</td>
					<script type="text/javascript">
						$(function () {
							$("#is_hot").change(function () {
								if($(this).val() == "0") {
									$("#hot_sort").validatebox({
										required: false,
										validType : 'integer'
									});
								} else if($(this).val() == "1") {
									$("#hot_sort").validatebox({
										required: true,
										validType : 'integer'
									});
								} else {
									$("#hot_sort").validatebox({
										required: false,
										validType : 'integer'
									});
								}
							})
						})
					</script>
					<td style="width: 100px;" align="right">热门排序：</td>
					<td><input class="easyui-validatebox layui-input" type="text" name="hot_sort" id="hot_sort" style="width: 400px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">后台链接：</td>
					<td colspan="3"><input class="easyui-validatebox layui-input" type="text" name="manager_url" id="manager_url" style="width: 99%"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">后台登陆用户名：</td>
					<td><input class="easyui-validatebox layui-input" type="text" name="manager_user" id="manager_user" style="width: 300px"></input></td>
					<td style="width: 100px;" align="right">后台登陆用户密码：</td>
					<td><input class="easyui-validatebox layui-input" type="text" name="manager_password" id="manager_password" style="width: 300px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right"><font color="red">*</font>所属公司：</td>
					<td colspan="3"><input class="easyui-validatebox layui-input" data-options="required:true" type="text" name="company" id="company" style="width: 500px"></input></td>
				</tr>
				<tr>
					<td style="width: 100px;" align="right">产品联系人：</td>
					<td><input class="easyui-validatebox layui-input" type="text" name="contact" id="contact" style="width: 300px"></input></td>
					<td style="width: 124px;" align="right">产品联系人联系方式：</td>
					<td><input class="easyui-validatebox layui-input" type="text" name="contact_info" id="contact_info" style="width: 300px"></input></td>
				</tr>
				</table>
			</div>
			<div title="商品描述(申请攻略)" class="easyui-panel" style="width: 100%; height: 540px">
				<div id="product_detail_toolbar" class="toolbar" style="border: 1px solid #ccc;">
			</div>
			<div id="product_detail" class="text" style="border: 1px solid #ccc; height: 480px;"> <!--可使用 min-height 实现编辑区域自动增加高度-->
			</div>

			<script type="text/javascript">
				var E = window.wangEditor
				var editor = new E('#product_detail_toolbar', '#product_detail');
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
				qryProduct('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
			}, 200);

		});

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







