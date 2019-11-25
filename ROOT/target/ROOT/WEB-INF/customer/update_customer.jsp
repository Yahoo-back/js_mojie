<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
    <input type="hidden" name="id" id="id"/>
    <table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" class="tb-customer" align="center">
        <tr>
            <td style="width: 100px;" align="right">姓名：</td>
            <td><input class="easyui-validatebox" type="text" name="user_name" id="user_name" style="width: 300px"></input></td>
            <td style="width: 100px;" align="right">身份证号：</td>
            <td><input class="easyui-validatebox" type="text" name="id_card" id="id_card" style="width: 300px"></input></td>
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
                qryCustomer('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');
            }, 200);
        });
    </script>
</form>
