<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>产品财务报表</title>
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
    <script defer type="text/javascript" src="${base}/static/script/data_finance_report.js"></script>
    <style type="text/css">
        .tdLeftClass span {margin: 4px;color: red;}
    </style>
</head>
<body>
<div id="p" class="easyui-panel" fit="true" title='<span class="searchbox-text" id="position"><font color="#FF4040">当前位置：</font>财务管理&gt;&gt;引流入账管理</span>' >
    <div style="width: 100%;height: 26px">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:120px;float:right" onclick="queryCust()">查询</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" style="width:120px;float:right" onclick="exportExcel()">导出</a>
    </div>
    <div id="count_product" height="width: 100%"></div>
    <div id="cust_tb" height="width: 100%"></div>

    <div id="settle_product_tb" height="width: 100%"></div>
    <div style="display: none">
        <div id="ex_cust_tb" height="width: 100%"></div>
    </div>
    <br>
    <div id="capital" class="easyui-panel" title="财务统计" style="width:500px;height:100px;padding:10px;"
         data-options="iconCls:'icon-search',closable:false,tools:'#tt'">
    </div>
</div>
</body>
</html>