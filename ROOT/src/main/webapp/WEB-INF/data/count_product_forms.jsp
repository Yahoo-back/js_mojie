<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<form id="vehicle_add_form" method="post" >
	<input type="hidden" name="report_id" id="report_id"/>
    <table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center">
        <tr style="left: 18px" ><td colspan="4">产品信息</td></tr>
        <tr>
            <td style="width: 100px" align="right">产品名称：</td>
            <td id="product_name_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_name_form" id="product_name_form"/>--%>
            </td>
            <td style="width: 100px;" align="right">产品状态：</td>
            <td id="product_status_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_status_form" id="product_status_form"/>--%>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">产品联系人：</td>
            <td id="product_contact_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_contact_form" id="product_contact_form"/>--%>
            </td>
            <td style="width: 100px;" align="right">联系方式：</td>
            <td id="product_contact_info_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_contact_info_form" id="product_contact_info_form"/>--%>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">所属公司：</td>
            <td id="product_company_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_company_form" id="product_company_form"/>--%>
            </td>
        </tr>
        <tr style="left: 18px" ><td colspan="4">后台信息</td></tr>
        <tr>
            <td style="width: 100px" align="right">后台链接：</td>
            <td><a  id="product_manager_url_form" style="color: #0000FF" target="_blank"></a>
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_manager_url_form" id="product_manager_url_form"/>--%>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">登录用户名：</td>
            <td id="product_manager_user_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_manager_user_form" id="product_manager_user_form"/>--%>
            </td>
            <td style="width: 100px;" align="right">登录用户密码：</td>
            <td id="product_manager_password_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_manager_password_form" id="product_manager_password_form"/>--%>
            </td>
        </tr>
        <tr style="left: 18px" ><td colspan="4">统计数据录入</td></tr>
        <tr>
            <td style="width: 100px;" align="right">结算状态：</td>
            <td id="product_settle_state_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_settle_state_form" id="product_settle_state_form"/>--%>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">结算方式：</td>
            <td id="product_settle_way_form">
                <%--<input style="width: 200px" class="easyui-validatebox" type="text" name="product_settle_way_form" id="product_settle_way_form"/>--%>
            </td>
            <td style="width: 100px;" align="right">应结算金额：</td>
            <td id="product_settle_money_form">

            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right"><font color="red">*</font>注册个数：</td>
            <td>
                <input style="width: 200px" class="easyui-validatebox" data-options="validType:['integer','length[0,20]']" type="number" name="reg_count_form" id="reg_count_form"/>
            </td>
            <td style="width: 100px;" align="right">uv个数：</td>
            <td>
                <input style="width: 200px" class="easyui-validatebox" data-options="validType:['integer','length[0,20]']" type="number" name="loan_count_form" id="loan_count_form"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;" align="right">备注：</td>
            <td>
                <input style="width: 200px" class="easyui-validatebox" data-options="validType:'length[0,50]'"  type="text" name="remark_form" id="remark_form"/>
            </td>
        </tr>

        <input type="hidden" name="settleWayCpa" id="settleWayCpa"/>
        <input type="hidden" name="settleWayCps" id="settleWayCps"/>
        <input type="hidden" name="settle_money" id="settle_money"/>

    </table>

	<script type="text/javascript">
		$(function () {
		    $("#report_id").val('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>');
            qryCountProductInfo('<%=(null == request.getParameter("id") ? "" : request.getParameter("id"))%>', '<%=(null == request.getParameter("view") ? "" : request.getParameter("view"))%>');

            $('#reg_count_form').blur(function(){
                if($('#settleWayCpa').val() != null && $('#settleWayCpa').val() != '' && $('#reg_count_form').val() != null && $('#reg_count_form').val() != '') {
                    var temp = parseInt($('#reg_count_form').val()) * $('#settleWayCpa').val();
                    $("#product_settle_money_form").text(parseInt($("#product_settle_money_form").text()) + temp);
                }
            });
            $('#loan_count_form').blur(function(){
                if($('#settleWayCps').val() != null && $('#settleWayCps').val() != '' && $('#loan_count_form').val() != null && $('#loan_count_form').val() != '') {
                    var temp = parseInt($('#loan_count_form').val()) * $('#settleWayCps').val()
                    $("#product_settle_money_form").text(parseInt($("#product_settle_money_form").text()) + temp);
                }
            });

        });

	</script>
</form>