<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>报表管理</title>
		<link rel="stylesheet" type="text/css" href="${base}/static/js/ztree/css/zTreeStyle/zTreeStyle.css" />
		<link rel="stylesheet" type="text/css" href="${base}/static/css/css/css.css" />
		<link rel="stylesheet" type="text/css" href="${base}/static/js/uploadify/uploadify.css" />
		<script type="text/javascript"> var ctx = '${base }'; </script>
		<script type="text/javascript" src="${base}/static/js/main.js"></script>
		<script type="text/javascript" src="${base}/static/js/pic.js"></script>
		<script type="text/javascript" src="${base}/static/js/ztree/ztree.js"></script>
		<script type="text/javascript" src="${base}/static/js/ztree/ztree.excheck.js"></script>
		<script type="text/javascript" src="${base}/static/js/ztree/ztree.edit.js"></script>
		<script type="text/javascript" src="${base}/static/js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${base}/static/js/validate/validate.js"></script>
		<script type="text/javascript" src="${base}/static/js/uploadify/jquery.uploadify.min.js"></script>
		<script defer type="text/javascript" src="${base}/static/script/data_charts_product.js"></script>
		<script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
		<style type="text/css">
			.tdLeftClass span {margin: 4px;color: red;}
		</style>
	</head>
	<body  style="height: 100%; overflow: hidden">

	<div class="list_title1">
		<a href="#" class="easyui-linkbutton color-orange" style="margin-right: 10px;" onclick="exportExcel()">导出</a>
		<a href="#" class="easyui-linkbutton color-orange" onclick="queryCust()">查询</a>
	</div>

		<div id="p" class="easyui-panel" fit="true">
			<div style="width: 100%;height: 26px">
				<font style="float: left; margin-left: 10px;">请选择日期：</font>
				<input type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" name="daysFrom" id="daysFrom" style="width: 150px;float:left"></input>
				<font style="float: left">&nbsp;&nbsp;-&nbsp;&nbsp;</font>
				<input type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" name="daysTo" id="daysTo" style="width: 150px;float:left"></input>
				<font style="float: left">&nbsp;&nbsp;&nbsp;&nbsp;</font>
				<br><br>
				<style>
					#product_chose_div{ width: 100%;
						overflow: hidden;
						margin: 10px 0 0;
					}
					#product_chose_div input{
						margin: 0 4px 0 14px;

					}
				</style>
				<div id="product_chose_div" style="float:left; font-size: 15px">
				</div>
				<font style="float: left">&nbsp;&nbsp;&nbsp;&nbsp;</font>

			</div>
			<div id="cust_tb" height="width: 100%"></div>
			<div style="display: none">
				<div id="ex_cust_tb" height="width: 100%"></div>
			</div>
			<br><br><br><br><br><br><br><br>
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<style>
				.table-echarts td{
					border-color: #ccc;
					line-height: 32px;
				}
				.table-echarts tbody{ border: #999 1px solid;}
				.table-echarts tr:first-child{
					height: 50px;
				}
			</style>
			<div id="main" style="height:400px;width: 100%"></div>

			<!-- 弹出层 -->
			<div>
				<div id="rechargeDia"></div>
			</div>
		</div>

	</body>
</html>