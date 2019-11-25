<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<form id="vehicle_add_form2" method="post" >
    <input type="hidden" name="report_id" id="report_id"/>

    <table border="1" bordercolor="#a0c6e5" class="tb-customer" style="border-collapse:collapse;width: 100%" align="center">

        <tr>
            <td style="width: 100px;" align="right"><font color="red">*</font>结算金额：</td>
            <td>
                <input style="width: 200px" class="easyui-validatebox" data-options="required : true,validType : ['integer','length[0,20]']" type="number" name="settle_money_form" id="settle_money_form"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">备注：</td>
            <td>
                <input style="width: 200px" class="easyui-validatebox" data-options="validType:'length[0,50]'"  type="text" name="remark_form" id="remark_form"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">流水号：</td>
            <td>
                <input style="width: 200px" class="easyui-validatebox" type="text" name="serial_number_form" id="serial_number_form"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">结算凭证：</td>
            <td colspan="3">
                <button type="button" class="layui-btn" id="test1">上传图片</button>
                <table border="1" bordercolor="#ccc" style="border-collapse:collapse; padding: 4px; width: 100%" align="center">
                    <tr id="zmwj" style="margin: 3px; display: block;">
                    </tr>
                </table>
                <input id="sessionId" type="hidden" value="${pageContext.session.id}"/>
                <font color="red">最多上传1张凭证图片</font>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">结算时间：</td>
            <td>
                <input style="width: 200px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" <%--readonly="readonly"--%> type="text" name="settle_time_form" id="settle_time_form"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">结算人：</td>
            <td id="user_name_form">
                <%--<input style="width: 200px" class="easyui-validatebox" data-options="validType:'length[0,50]'"  type="text" name="user_name_form" id="user_name_form"/>--%>
            </td>
        </tr>
    </table>

    <script type="text/javascript">
        $(function () {
            setTimeout(function () {
                initPlugn();
            $("#report_id").val('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>');
            qrySettleProductInfo('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
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