<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
var ctx = '${base}';
</script>
<script type="text/javascript" src="${base }/static/js/main.js"></script>
<script type="text/javascript" src="${base }/static/js/pic.js"></script>
<link rel="stylesheet" type="text/css" href="${base }/static/js/ztree/css/demo.css" />
<link rel="stylesheet" type="text/css" href="${base }/static/js/ztree/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${base }/static/js/ztree/ztree.js"></script>
<script type="text/javascript" src="${base }/static/js/ztree/ztree.excheck.js"></script>
<script type="text/javascript" src="${base }/static/js/ztree/ztree.edit.js"></script>
<link rel="stylesheet" type="text/css" href="${base }/static/css/css/css.css" />
<SCRIPT type="text/javascript">
	    var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		var log, className = "dark";
		function beforeClick(treeId, treeNode, clickFlag) {
			className = (className === "dark" ? "":"dark");
			return (treeNode.click != false);
		}
		function onClick(event, treeId, treeNode, clickFlag) {
			if("treeDemo1" == treeId){
				$("#parentMuenId").val(treeNode.id);
				if(treeNode.uri==""){
					$("#parentMuenName").val(treeNode.name);
				}else{
					alert("请选择父菜单！");
				}
				$("#treeDemo1").css("display","none");
			}else{
				ajaxRequest("qryMeun",{"meunId":treeNode.id}, function(data){
					if(data.resultNode == "success"){
						reset();
						$("#parentMuenId").val(data.rows.pid);
						$("#parentMuenName").val(data.rows.pname);
						$("#muenId").val(treeNode.id);
						$("#muenName").val(treeNode.name);
						$("#url").val(data.rows.uri);
						$("#xuhao").val(treeNode.xh);
						$("#status").val(data.rows.status);
						
						$(".menuBtn").hide();
					}
				}); 
			}
		}	
		$(document).ready(function(){
			init();
			$("#save").on("click",function(){
				if($("#parentMuenName").val() == ""){
					$.messager.alert('提示',"请选择父菜单",'info');
					return;
				}
				if($("#xuhao").val() == ""){
					$.messager.alert('提示',"请输入序号",'info');
					return;
				}
				if($("#muenName").val() == ""){
					$.messager.alert('提示',"请输入子菜单名称",'info');
					return;
				}
				
				if($("#url").val() == ""){
					$.messager.alert('提示',"请输入路径",'info');
					return;
				}
				var jsonData = $("#ff").serializeObject();
				var par = $.parseJSON(JSON.stringify(jsonData)); 
				
				$.messager.confirm('提示', '确定保存吗？', function(r){
			        if (r){
			        	ajaxRequest("saveMuen",par, function(data){
							$.messager.alert('提示',data.resultNode,'info');
							if(data.resultNode == "success"){
								init();
								reset();
							}
						}); 
			        }
			    });
			});
			$("#add").on("click",function(){
				reset();
			});
			
			$("#del").on("click",function(){
				if($("#parentMuenName").val()==""){
					$.messager.alert('提示',"不能删除父菜单",'info');
					return;
				}
				if($("#muenId").val() == ""){
					$.messager.alert('提示',"请点击左边的菜单项选择要删除子菜单",'info');
					return;
				}$.messager.confirm('提示', '确定删除吗？', function(r){
			        if (r){
			        	ajaxRequest("deleteMuen",{"meunId":$("#muenId").val()}, function(data){
							$.messager.alert('提示',data.resultNode,'info');
							if(data.resultNode == "success"){
								init();
								reset();
							}
						}); 
			        }
			    });
			});
		});
		
		function init(){
			ajaxRequest("qryAllMeuns",null, function(data){
				if(data.resultNode == "success"){
					znodes = data.rows
					$.fn.zTree.init($("#treeDemo"), setting, data.rows);
					$.fn.zTree.init($("#treeDemo1"), setting, data.rows);
				}
			}); 
		}
		
		function reset(){
			$("#parentMuenId").val("");
			$("#parentMuenName").val("");
			$("#muenId").val("");
			$("#muenName").val("");
			$("#url").val("");
			$("#xuhao").val("");
			$("#status").val("0");
			$(".menuBtn").show();
		}
		
		function showMenu() {
			$("#treeDemo1").css("display","block");
			var cityObj = $("#parentMuenName");
			var cityOffset = $("#parentMuenName").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		}
	</SCRIPT>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}

</style>

</head>
<body>
<div id="p" class="easyui-panel" fit="true" >
     <div class="easyui-layout" fit=true>
        <div data-options="region:'west',split:true,iconCls:'icon-reload'" title="菜单项" style="width:500px;" >
        	<div id="treeDemo" class="ztree"></div>
        </div>
        <div data-options="region:'center',title:'操作',iconCls:'icon-ok'">
        	<form id="ff">
	        	<div class="form-new" style="float: center">
					<ul>
						<li><label><font color="red">*</font>上级菜单名称：</label>


							<input name="parentMuenName" type="text" class="input-coom menuBtn" id="parentMuenName" style="width: 216px; height: 30px; background: url(/static/js/ztree/css/zTreeStyle/img/diy/custom-i11.png) right center no-repeat;" readonly onclick="showMenu(); return false;">
							<b color=red>*</b>

							<input name="parentMuenId" type="hidden" class="input-coom" id="parentMuenId" style="width: 210px; height: 30px;" >
							<input name="muenId" type="hidden" class="input-coom" id="muenId" style="width: 210px; height: 30px;" >
						</li>
						<li><label><font color="red">*</font>序号：</label>
							<input name="xuhao" type="text" class="input-coom" id="xuhao" style="width: 216px; height: 30px;"><b color=red>*</b>
						</li>
						<li><label><font color="red">*</font>菜单名称：</label>
							<input name="muenName" type="text" class="input-coom" id="muenName" style="width: 216px; height: 30px;"><b color=red>*</b>
						</li>
						<li><label><font color="red">*</font>URL：</label>
							<input name="url" type="text" class="input-coom" id="url" style="width: 216px; height: 30px;"><b color=red>*</b>
						</li>
						<li><label>状态：</label>
							<select  name="status" id="status" style="width: 222px; height: 30px;">
								<option value="0">即时启用</option>	
								<option value="1">禁用</option>	
							</select>
						</li>
					</ul>
				</div>
			</form>
			<div>
				<ul style="list-style-type: none;" id="wfmbtns">
					<li style="display: inline;line-height: 10px"><a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-small" id="add"  style="width:120px;background:#faa732; color:#fff;">新增</a></li> &nbsp;&nbsp;&nbsp;
					<li style="display: inline;line-height: 10px"><a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-small" id="save"  style="width:120px;background:#faa732; color:#fff;">保存</a></li> &nbsp;&nbsp;&nbsp;
					<li style="display: inline;line-height: 10px"><a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-small" id="del"  style="width:120px;background:#faa732; color:#fff;">删除</a></li> &nbsp;&nbsp;&nbsp;
				</ul>	
				<font color="red">*编辑时，请点左边的菜单！</font>
			</div>
        </div>
    </div>
    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo1" class="ztree" style="margin-top:0; width:210px;"></ul>
	</div>
</div>
</body>
</html>
