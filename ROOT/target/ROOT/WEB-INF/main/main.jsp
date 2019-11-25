<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hyy.root.pojo.Logins,com.hyy.root.pojo.Muens" %>
<%@ page import="java.text.DecimalFormat, com.hyy.root.pojo.Logins, com.hyy.root.util.sharekey.SysShareKey, com.visionalsun.util.handler.UtilHandler"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>魔借后台管理系统</title>
<link rel="icon" type="image/png" href="${base }/static/login/favicon.png">
<link title="" href="${base }/css/console.css" rel="stylesheet" type="text/css"/>
<link title="" href="${base }/plugin/fontawesome/css/font-awesome.css" rel="stylesheet">
<link title="" href="${base }/plugin/layer/skin/default/layer.css" rel="stylesheet">
<link title="yellow" href="${base }/css/yellow.css" rel="stylesheet" type="text/css" disabled/>
<link title="blue" href="${base }/css/blue.css" rel="stylesheet" type="text/css" disabled/>
<link title="red" href="${base }/css/red.css" rel="stylesheet" type="text/css" disabled/>
<link title="green" href="${base }/css/green.css" rel="stylesheet" type="text/css"/>
<link title="black" href="${base }/css/black.css" rel="stylesheet" type="text/css" disabled/>
<script src="${base }/plugin/jquery/jquery.min.js"></script>
<script src="${base }/plugin/layer/layer.js"></script>
<script type="text/javascript" src="${base }/static/js/template-simple.js"></script>
<script type="text/javascript">
var muens = ${meunsJson};
var ctx = '${base}';
</script>
<script type="text/javascript" src="${base }/static/js/muen.js"></script>
<script type="text/javascript" src="${base }/static/js/jquery.yc.js"></script>
</head>
<body>
<%
Muens  m = (Muens)request.getAttribute("mainmeuns");
Logins.Login _login = (Logins.Login) session.getAttribute(SysShareKey.LoginSession);
%>
<div class="console-header">
	<div class="logo logo-full" >魔借管理系统</div>
    <div id="header-tab" >
		<%int count=0; for(Muens.Muen user : m.getRows()){if("0".equals(user.getParent_muen_id())){ count++;%>
			<li <%=(count == 1 ? "class=active" : "") %>  onclick="getchil('<%=user.getMuenid() %>')"><i class="fa fa-<%=user.getIcon() %>"></i><span><%=user.getMuen_name() %></span></li>
			<% if(count == 1){ %>
				<script type="text/javascript">
					$(document).ready(function() {
						getchil("<%=user.getMuenid() %>");
					});
				</script>
			<%}%>
		<%}}%>
    </div>
    <div class="nav drop">
      <!-- <li class="n"><a href="#"><i class="fa fa-envelope"></i>消息</a><em>5</em></li> -->
      <li><a href="#" class="b"><i class="fa fa-user"></i><%=_login.getUserName() %></a>
          <div class="sitem">
              <a href="javascript:void(0)" onclick="enterChangePass()">修改密码</a>
              <a href="${base }/loginOut">退出登录</a>
          </div>
      </li>
<%--      <li><a href="#" class="b"><i class="fa fa-dashboard"></i>换肤</a>--%>
<%--          <div class="sitem">--%>
<%--          	  <a href="javascript:" class="changestyle" id="yellow"><span style="background:#ffcf12"></span></a>--%>
<%--              <a href="javascript:" class="changestyle" id="blue"><span style="background:#428bca"></span></a> --%>
<%--              <a href="javascript:" class="changestyle" id="green"><span style="background:#2bb784"></span></a>                --%>
<%--              <a href="javascript:" class="changestyle" id="red"><span style="background:#ca4257"></span></a>--%>
<%--              <a href="javascript:" class="changestyle" id="black"><span style="background:#293038"></span></a>  --%>
<%--          </div>--%>
<%--      </li>--%>
    </div>
</div>

<div class="console-body" >
  <div class="console-left left-full">
    <div class="console-fold"><i class="fa fa-arrows-h"></i></div>

    <div class="pre" id="pre">
    </div>

    <script id="dataListScoreName" type="text/html">
		{{if rows.length > 0}}
			{{each rows}}
			 	<div class="left-nav">
					<div class="chil down" data-toggle="tooltip" data-placement="left" >
						{{if $value.list.length == 0 }}
							<a href="javascript:" class="tab-add" data-id="{{$value.muenid}}" data-url="{{$value.uri}}"><i class="chil-icon fa fa-{{$value.icon}}"></i><span class="title">{{$value.muen_name}}</span></a>
						{{else}}
							<a href="javascript:" class="sub-a"><i class="chil-icon fa fa-{{$value.icon}}"></i><span class="title">{{$value.muen_name}}</span></a>
							<i class="arr-icon fa fa-angle-down"></i>
						{{/if}}
						<span class="tip"><em></em>{{$value.muen_name}}</span>
					</div>
					{{if  $value.list.length > 0}}
						<ul class="nav-content">
						{{each $value.list as value1 }}
							<li>
								<span class="tip"><em></em>{{value1.muen_name}}</span>
								<a href="javascript:" class="tab-add" data-id="{{value1.muenid}}" data-url="{{value1.uri}}"><i class="chil-icon fa fa-{{value1.icon}}"></i><span class="title">{{value1.muen_name}}</span></a>
							</li>
						{{/each}}
						</ul>
					{{/if}}
				</div>
			{{/each}}
		{{/if}}
	</script>
  </div>
  <div class="console-right right-full">
    	<div id="iframe-tab">
        	<%--<li class="active" id="ftab_0"><a href="javascript:"><i class="chil-icon fa fa-home"></i>首页</a></li>--%>
        </div>
        <div id="iframe-con">
        	<%--<iframe class="fcon"  id="fcon_0" width="100%" height="100%" frameborder="0"  ></iframe>--%>
        </div>
  </div>
  <div id="changePass"></div>
</div>
<script type="text/javascript">
$(function(){
/*换肤*/
$(".changestyle").click(function(){
	var style = $(this).attr("id");
    $("link[title!='']").attr("disabled","disabled");
	$("link[title='"+style+"']").removeAttr("disabled");

	localStorage.mystyle=style;
})
if(localStorage.mystyle){
    $("link[title!='']").attr("disabled","disabled");
	$("link[title='"+localStorage.mystyle+"']").removeAttr("disabled");
}

/*左侧导航栏显示隐藏功能*/
$(document).on("click",".chil",function(){
		/*显示*/
		if($(this).find(".arr-icon").hasClass("fa-angle-down"))
		{
			$(this).find(".arr-icon").removeClass("fa-angle-down");
		    $(this).find(".arr-icon").addClass("fa-angle-right");
		    $(this).removeClass("down");
			$(this).addClass("up");
		}
		/*隐藏*/
		else
		{
			$(this).find(".arr-icon").removeClass("fa-angle-right");
			$(this).find(".arr-icon").addClass("fa-angle-down");
			$(this).removeClass("up");
			$(this).addClass("down");
		}
	//
    $(this).next(".nav-content").slideToggle(300).siblings(".nav-content").slideUp(300);
})



/*左侧导航栏缩进功能*/
$(".console-fold").click(function(){
	fold_nav(1);
	change_tab();
})

  /*左侧鼠标移入提示功能*/
  	$(document).on("mouseenter",".left-nav ul li",function() {
		if ($(this).find("span:last-child").css("display") == "none") {
			$(this).find(".tip").show();
		}
	}).on("mouseleave",".left-nav ul li",function() {
		$(this).find(".tip").hide();
	})


	$(document).on("mouseenter",".chil",function() {
		if ($(this).find("span:last-child").css("display") == "none") {
			$(this).find(".tip").show();
		}
	}).on("mouseleave",".chil",function() {
		$(this).find(".tip").hide();
	})

})


//tabframe方法
function open_frame(id,url,title){
	//fold_nav(0);

	if($("#ftab_"+id).length>0){
		$("#iframe-tab li").removeClass("active");
		$("#ftab_"+id).addClass("active");
		$("#iframe-con iframe").hide();
		$("#fcon_"+id).show();
		$("#ftab_"+id).css('width','130px');
		// 重新刷新
		$("#fcon_"+id).attr('src', $("#fcon_"+id).attr('src'));
	}else{
		if($("#iframe-tab li").length>8){
			$("#iframe-tab li:eq(1)").remove();
			$(".fcon:eq(1)").remove();
		}
		$("#iframe-tab li").removeClass("active");
		$("#ftab_"+id).addClass("active");
		$("#iframe-tab").append('<li class="active" id="ftab_'+id+'"><a href="javascript:">'+title+'</a><i class="close fa fa-times-circle"></i></li>');//添加一个tab
		$("#iframe-con iframe").hide();
		$("#iframe-con").append('<iframe id="fcon_'+id+'" class="fcon" name="fcon_'+id+'" width="100%" height="100%" frameborder="0" src="'+url+'"></iframe>');//添加一个iframe

	}
}

$(document).on('click','.tab-add', function(){
	open_frame($(this).data("id"),$(this).data("url"),$(this).html(),true);
	change_tab();
});

function fold_nav(mod){
	if($(".console-left").attr('class')=="console-left left-full")
	 {
	  $(".console-left").removeClass("left-full");
	  $(".console-left").addClass("left-off");

	  $(".console-left").parent().find(".console-right").removeClass("right-full");
	  $(".console-left").parent().find(".console-right").addClass("right-off");

	  $(".console-fold").children().removeClass("fa-arrows-h");
	  $(".console-fold").children().addClass("fa-arrows-v");

	  $(".logo").removeClass("logo-full");
	  $(".logo").addClass("logo-off");

	  }
	 else
	 {
	  if(mod==1){
		  $(".console-left").removeClass("left-off");
		  $(".console-left").addClass("left-full");

		  $(".console-left").parent().find(".console-right").removeClass("right-off");
		  $(".console-left").parent().find(".console-right").addClass("right-full");

		  $(".console-fold").children().removeClass("fa-arrows-v");
		  $(".console-fold").children().addClass("fa-arrows-h");

		  $(".logo").removeClass("logo-off");
		  $(".logo").addClass("logo-full");
	  }

	  }
}

$('#iframe-tab').on('click','li .close', function(){
	var fi=$(this).parent("li").index();

	$("#iframe-con iframe:eq("+ fi +")").remove();
    $(this).parent("li").remove();//删除当前选项卡
	//删除当前iframe
    if($(this).parent().hasClass("active")){
        $("#iframe-tab li:last").addClass("active");//定位最后一个tab
        $("#iframe-con iframe:last").show();//显示最后一个iframe
    }


    change_tab();
});

$('#iframe-tab').on('click','li a', function(){
	$(this).parent().addClass("active");
	$(this).parent().siblings("li").removeClass("active");
	$("#iframe-con iframe").hide();
	$("#iframe-con iframe:eq("+$(this).parent().index()+")").show();

	$(this).parent().css('width','130px');
    change_tab();
});

$('#header-tab').on('click','li', function(){
	$(this).addClass("active");
	$(this).siblings("li").removeClass("active");
});

$(".drop li").hover(
		function(){
			$(this).find(".sitem").show();
			$(this).find(".sitem").css("width",$(this).width());
			$(this).addClass("hover");
			},function(){
			$(this).find(".sitem").hide();
			$(this).removeClass("hover");
		}
);
function change_tab(){

    if($("#iframe-tab li").length*136 > $("#iframe-tab").width()){
        newtab=($("#iframe-tab").width()-136)/($("#iframe-tab li").length-1) - 8;
        $("#iframe-tab li").not('.active').css('width',newtab+'px');
    }else{

    }
}

$(window).on('resize',function(){
	change_tab();
})
function refresh_iframe(){
	$(".fcon").each(function(){
		if($(this).css("display")!="none"){
			 relo($(this).attr("id"));
        }
	})
}

function r_fcon(){
	var new_r_fcon;
	$(".fcon").each(function(){
		if($(this).css("display")!="none"){
			 new_r_fcon=$(this).attr("id")+"";
        }
	})
	return $(window.parent.$("#"+new_r_fcon)).contents();
}

function relo(id){
	document.getElementById(id).contentWindow.location.reload(true);
}
function r_fcon_m(){
	 var new_r_fcon_m;
	 $(".fcon").each(function(){
	  if($(this).css("display")!="none"){
	    new_r_fcon_m=$(this).attr("id")+"";
	        }
	 })
	 return $(window.parent.document).contents().find("#"+new_r_fcon_m)[0].contentWindow;
	}
</script>
</body>
