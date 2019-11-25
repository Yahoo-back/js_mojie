<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
    <input type="hidden" name="id" id="id"/>
    <div class="easyui-accordion">
        <div class="easyui-panel">
            <table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center">
                <tr>
                    <td style="width: 100px;" align="right"><font color="red">*</font>退款金额：</td>
                    <td colspan="3"><input class="easyui-validatebox layui-input" data-options="required:true" type="text" name="money" id="money" style="width: 500px"></input></td>
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
            </table>
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
                qryReback('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
                <%--editReback('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>');--%>
                <%--qryReback('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');--%>
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







