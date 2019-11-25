<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>配置管理</title>
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
		<script defer type="text/javascript" src="${base}/static/script/product_type.js"></script>
		<script type="text/javascript" src="${base}/static/js/wangEditor.min.js"></script>
		<style type="text/css">
			.tdLeftClass span {margin: 4px;color: red;}
		</style>
	</head>
	<body  style="height: 100%; overflow: hidden">
	<div class="list_title1">
		<a href="#" class="easyui-linkbutton color-orange" style="margin-right: 10px;" onclick="addProductClassify('')">新增类型</a>
		<a href="#" class="easyui-linkbutton color-orange" onclick="queryCust()">查询</a>
		<a href="#" class="easyui-linkbutton color-orange" onclick="exportExcel()">导出</a>
	</div>
	<div class="h" style="min-height: 85%;">
		<div id="p" class="easyui-panel" fit="true">

			<div id="cust_tb" height="width: 100%"></div>
			<div id="add_product_classify"></div>
			<div style="display: none">
				<div id="ex_cust_tb" height="width: 100%"></div>
			</div>
		</div>
	</div>
	</body>
</html>