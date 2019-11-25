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
		<script defer type="text/javascript" src="${base}/static/script/data_report.js"></script>
		<style type="text/css">
			.tdLeftClass span {margin: 4px;color: red;}
		</style>
	</head>
	<body  style="height: 100%; overflow: hidden">
	<div class="list_title1">
		<a href="#" class="easyui-linkbutton color-orange" style="margin-right: 10px;" onclick="queryCust()">查询</a>
		<a href="#" class="easyui-linkbutton color-orange" onclick="exportExcel()">导出</a>
	</div>
	<div class="h" style="min-height: 90%;">
		<div id="p" class="easyui-panel" fit="true" >

			<div id="count_product" height="width: 100%"></div>
            <div id="cust_tb" height="width: 100%"></div>

			<div id="settle_product_tb" height="width: 100%"></div>
			<div style="display: none">
				<div id="ex_cust_tb" height="width: 100%"></div>
			</div>
            <br>
            <div id="capital" class="easyui-panel" title="统计" style="width:100%;height:auto !important;padding:10px 6px;"
                 data-options="iconCls:'icon-search',closable:false,tools:'#tt'">
            </div>
		</div>
	</div>
	</body>
</html>