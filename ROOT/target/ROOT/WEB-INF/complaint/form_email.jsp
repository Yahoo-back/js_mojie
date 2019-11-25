<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
    <input type="hidden" name="id" id="id"/>
    <table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" class="tb-customer" align="center">
        <tr>
            <td style="width: 100px;" align="right">手机号：</td>
            <td><input class="easyui-validatebox" type="text" name="mobile" id="mobile" style="width: 300px"></input></td>
            <td style="width: 100px;" align="right">注册时间：</td>
            <td><input class="easyui-validatebox" type="text" name="create_time" id="create_time" style="width: 300px"></input></td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">性别：</td>
            <td><input class="easyui-validatebox" type="text" name="sex" id="sex" style="width: 200px"></input></td>
        <tr>
            <td style="width: 100px;" align="right">职业：</td>
            <td><input class="easyui-validatebox" type="text" name="job" id="job" style="width: 200px"></input></td>
        </tr>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">芝麻分：</td>
            <td><input class="easyui-validatebox" type="text" name="zhima_score" id="zhima_score" style="width: 200px"></input></td>
            <td style="width: 100px;" align="right">认证状态：</td>
            <td><input class="easyui-validatebox" type="text" name="mobile_auth" id="mobile_auth" style="width: 200px"></input></td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">身份证信息：</td>
            <td colspan="3">
                <table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center">
                    <tr id="zmwj">
                    </tr>
                </table>
                <div id="icondis" style="display: none"></div>
                <input id="sessionId" type="hidden" value="${pageContext.session.id}"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">银行卡号：</td>
            <td><input class="easyui-validatebox" type="text" name="bank_card" id="bank_card" style="width: 300px"></input></td>
            <td style="width: 100px;" align="right">银行卡开户行：</td>
            <td><input class="easyui-validatebox" type="text" name="bank_open" id="bank_open" style="width: 300px"></input></td>
        </tr>
        <tr>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">状态：</td>
            <td colspan="3">
                <input class="easyui-validatebox" type="text" name="status" id="status" style="width: 200px"></input>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">凭证：</td>
            <td colspan="3">
                <table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center">
                    <tr id="pz">
                    </tr>
                </table>
                <div id="icon" style="display: none"></div>
                <input id="session" type="hidden" value="${pageContext.session.id}"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">退款/驳回：</td>
            <td colspan="3">
                <table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center">
                    <tr id="reback">
                    </tr>
                </table>
                <%--                <div id="icon" style="display: none"></div>--%>
                <%--                <input id="session" type="hidden" value="${pageContext.session.id}"/>--%>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
        $(function () {
            setTimeout(function () {
                var view = '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>';
                if("1" == view) {
                    $("input").prop("disabled", "disabled");
                    $("select").prop("disabled", "disabled");
                }
                qryEmailList('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
            }, 200);
        });
    </script>
</form>
