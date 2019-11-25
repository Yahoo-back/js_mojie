<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>魔借后台管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/x-icon" href="${base }/static/login/favicon.ico">
<script src="${base }/static/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="${base }/assets/css/reset.css">
<%--<link rel="stylesheet" href="${base }/assets/css/supersized.css">--%>
<link rel="stylesheet" href="${base }/assets/css/style.css">
</head>
<body>
<%--	 <div class="n_head">--%>
<%--         <div class="n_head-con">--%>
<%--             <i class="n_icon-logo"></i><span>魔借后台管理系统</span>--%>
<%--         </div>--%>
<%--     </div>--%>
    <div id="particles-js" class="n_page-container">
        <div class="n_login-box">
<%--            <h1>用户登录</h1>--%>
    <span>魔借后台管理系统</span>
            <form action="" method="post">

                <input type="text" name="Username" id="Username" class="n_username" placeholder="请输入用户名">

                <input type="password" name="Password" id="Password" class="n_password" placeholder="请输入密码">

                <button type="button" class="n_sub"  id="loginBtn">登录</button>

                <div class="n_error"><span>+</span></div>
            </form>
            <div class="connect">
                <p>${msg }</p>
           </div>
        </div>
    </div>
	<div class="n_connect">
        <p>
			<%--<a style="color: #fff;font-size: 14px; text-decoration:none;" href="http://files.xiaoyuehui-core.com/fileUpload/plugin/flashplayer_ppapi_23.0.0.207.exe" target="_blank">Google浏览器无法正常显示上传，请点击下载！</a>--%>
            <%--<br> Copyright  © 2013 - 2018 南京数据服务有限公司版权所有--%>
        </p>
    </div>

	<!-- Javascript -->

	<script type="text/javascript">
	var ctx='${base }';
	//打开字滑入效果
	$(function() {
		//打开字滑入效果
		var _topWin = window;
		while (_topWin != _topWin.parent.window) {
			_topWin = _topWin.parent.window;
		}
		if (window != _topWin)
			_topWin.document.location.href = '${base}/index';
	});
   </script>
<%--	<script src="${base }/assets/js/supersized.3.2.7.min.js"></script>--%>
<%--	<script src="${base }/assets/js/supersized-init.js"></script>--%>
	<script src="${base }/assets/js/scripts.js"></script>
     <script src="${base }/assets/js/particles.js"></script>
     <script src="${base }/assets/js/app.js"></script>

</body>
</html>

