$(function () {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryDataReportList',
        fitColumns:true,
        singleSelect:false,
        rowStyler: function(index,row){
            if (row.settle_state == "1"){
                return 'background-color:#999999;color:red;';
            }
        },
        columns:[[
            {field:"name",title:"商品名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"company",title:"所属公司",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
                var select = "<select id='company_cnd' style='width: 100%;' name='company_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                ajaxRequest("qryProductCompanyAll",null, function(data){
                    if(data.resultNode == "success"){
                        var row = data.rows;
                        for(var i=0; i<row.length; i++) {
                            $("#company_cnd").append("<option value='"+row[i].company+"'>"+row[i].company+"</option>")
                        }
                    }
                });
                return select;
            }},
            {field:"visit_time",title:"访问时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"visit_count",title:"点击次数",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"reg_count",title:"注册次数",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"change_rate",title:"转化率",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"loan_count",title:"放款个数",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"settle_money",title:"结算金额",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"settle_way",title:"合作方式",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"settle_state",title:"结算状态",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='settle_state_cnd' style='width: 100%;' name='settle_state_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='0'>未结算</option>");
                select += ("<option value='1'>已结算</option>");
                return select;
            },formatter:function(val){
                switch(val){
                    case "0":
                        return "未结算";
                    case "1":
                        return "已结算";
                    default:
                        return "未知";
                }
            }},
            {field:"remark",title:"备注",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(value, row, index){
                    value = "";
                    if((row.reg_count == null && row.loan_count == null && row.settle_money == null) || (row.reg_count == "" && row.loan_count == "" && row.settle_money == "")) {
                        value += "<a href=\"javascript:void(0);\" onclick=\"javascript:countProduct('"+row.id+"');\" style=\"color: #FF4500;\">统计</a>";
                    } else {
                        value += "<a href=\"javascript:void(0);\" onclick=\"javascript:editCountProduct('"+row.id+"');\" style=\"color: #FF4500;\">统计录入</a>";
                    }
                    if(row.settle_state == "0") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:settleProduct('"+row.id+"');\" style=\"color: #FF4500;\">结算</a>";
                    }
                    return value;
                }}
        ]],
        onDblClickRow:function(index,row){
            views(row.id);
        },onLoadSuccess : function(data) {
            $(".grid_edit").linkbutton({
                text : '查询',
                plain : true,
                iconCls : "icon-search"
            });
            initWithDrawTotal();
        }
    });
    cust_tb.refresh();
    window.cust_tb=cust_tb;
});

function queryCust(){
    var par = [
        "name",
        "company",
        "settle_state",
        "visit_time_FROM",
        "visit_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function countProduct(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    var url = ctx + "/data/count_product?id=" + id;
    $("#count_product").dialog({
        title: "统计数据", href: url,
        width: "600", height: "1000",
        top: parseInt($(window).height() / 1.4 - 350),
        closed: false, cache: false, modal: true,fitColumns:true,
        buttons: [ {
            text: "保存",
            iconCls: "icon-ok",
            handler: function () {
                count1();
            }
        }, {
            text: "取消",
            iconCls: "icon-cancel",
            handler: function () {
                $("#count_product").dialog("close");
            }
        }]
    });
}

function count1() {
    if(!$("#vehicle_add_form").form('validate')){
        $.messager.alert('提示', '你还有必填项没有填写!', 'info');
        return false;
    }
    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));

    $.messager.confirm('提示', '确定提交统计吗？请仔细核对填写数据！', function(r){
        if (r){
            ajaxRequest("countProductInfo",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#count_product").dialog("close");
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function count2() {
    if(!$("#vehicle_add_form2").form('validate')){
        $.messager.alert('提示', '你还有必填项没有填写!', 'info');
        return false;
    }
    var jsonData = $("#vehicle_add_form2").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));

    $.messager.confirm('提示', '确定提交统计吗？请仔细核对填写数据！', function(r){
        if (r){
            ajaxRequest("settleProductInfo",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#settle_product_tb").dialog("close");
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}



function settleProduct(id) {
    if(id != "" && id != null && id != undefined){
    }
    var url = "";
    var title = "";
    url = ctx+"/data/settle_product_tb?id="+id;
    title = "结算";

    $("#settle_product_tb").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                count2();
            }
        }, {
            text : "取消",
            iconCls:"icon-cancel",
            handler : function() {
                $("#settle_product_tb").dialog("close");
            }
        } ]
    });
}

function qrySettleProductInfo(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qrySettleProductInfo",{"id":id}, function(data){

        var ifmUser = data.rows.user;
        var dcProductReport = data.rows.dcProductReport;

        $("#remark_form").val(dcProductReport.remark);
        $("#serial_number_form").val(dcProductReport.serialNumber);


        $("#settle_time_form").val(dcProductReport.settleTime);
        if(ifmUser == null || ifmUser == undefined){
            $("#user_name_form").text("");
        }else{
            $("#user_name_form").text(ifmUser.userName);
        }
    });

}


function editCountProduct(id) {
    if(id != "" && id != null && id != undefined){
    }
    var url = "";
    var title = "";
    url = ctx+"/data/count_product?id="+id;
    title = "统计录入";

    $("#count_product").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                count1();
            }
        }, {
            text : "取消",
            iconCls:"icon-cancel",
            handler : function() {
                $("#count_product").dialog("close");
            }
        } ]
    });
}

function qryCountProductInfo(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    ajaxRequest("qryCountProductInfo",{"id":id}, function(data){
        var dcProduct = data.rows.dcProduct
        var dcProductReport = data.rows.dcProductReport;
        $("#product_name_form").text(dcProduct.name);

        $("#settleWayCpa").val(dcProduct.settleWayCpa);
        $("#settleWayCps").val(dcProduct.settleWayCps);

        var temp_status = "";
        if(dcProduct.status == 0){
            temp_status = "下架";
        }else if(dcProduct.status == 1){
            temp_status = "上架";
        }else if(dcProduct.status == 2){
            temp_status = "删除";
        }
        $("#product_status_form").text(temp_status);
        $("#product_contact_form").text(dcProduct.contact);
        $("#product_contact_info_form").text(dcProduct.contactInfo);
        $("#product_company_form").text(dcProduct.company);
        $("#product_manager_url_form").text(dcProduct.managerUrl);
        $("#product_manager_url_form").attr("href",dcProduct.managerUrl);
        $("#product_manager_user_form").text(dcProduct.managerUser);
        $("#product_manager_password_form").text(dcProduct.managerPassword);
        var temp_settle = "";
        if(dcProductReport.settleState == 0){
            temp_settle = "未结算";
        }else if(dcProductReport.settleState == 1){
            temp_settle = "已结算"
        }
        $("#product_settle_state_form").text(temp_settle);
        /*if(dcProduct.settleWayCpa == null && dcProduct.settleWayCps != null){
            $("#product_settle_way_form").text(dcProduct.settleCycle+dcProduct.settleWayCps);
        }else if(dcProduct.settleWayCpa != null && dcProduct.settleWayCps == null){
            $("#product_settle_way_form").text(dcProduct.settleCycle+dcProduct.settleWayCpa);
        }else if(dcProduct.settleWayCpa != null && dcProduct.settleWayCps != null){
            $("#product_settle_way_form").text(dcProduct.settleCycle+dcProduct.settleWayCpa+dcProduct.settleWayCps);
        }else {
            $("#product_settle_way_form").text(dcProduct.settleCycle);
        }*/
        $("#product_settle_way_form").text(dcProductReport.settleWay);
        $("#product_settle_money_form").text(dcProductReport.settleMoney);
        if(dcProductReport.regCount == 0 || dcProductReport.regCount == null){
            $("#reg_count_form").val();
        }else {
            $("#reg_count_form").val(dcProductReport.regCount);
        }

        if(dcProduct.settleWayCpa != undefined && dcProduct.settleWayCpa != null && dcProduct.settleWayCpa != ''){
            $("#reg_count_form").validatebox({
                required : true,
                validType : ['integer','length[0,20]']
            })
        }else {
            $("#reg_count_form").validatebox({
                required : false
            })
        }
        // if(dcProductReport.loanCount == 0 || dcProductReport == null){
        //     $("#loan_count_form").val();
        // }else {
        //     $("#loan_count_form").val(dcProductReport.loanCount);
        // }
        // if(dcProduct.settleWayCps != undefined && dcProduct.settleWayCps != '' && dcProduct.settleWayCps != null){
        //     $("#loan_count_form").validatebox({
        //         required : true,
        //         validType : ['integer','length[0,20]']
        //     })
        // }else {
        //     $("#loan_count_form").validatebox({
        //         required : false
        //     })
        // }

        if(dcProductReport.visitCount == 0 || dcProductReport == null){
            $("#visit_count_form").val();
        }else {
            $("#visit_count_form").val(dcProductReport.visitCount);
        }
        if(dcProduct.settleWayCps != undefined && dcProduct.settleWayCps != '' && dcProduct.settleWayCps != null){
            $("#visit_count_form").validatebox({
                required : true,
                validType : ['integer','length[0,20]']
            })
        }else {
            $("#visit_count_form").validatebox({
                required : false
            })
        }
        $("#remark_form").val(dcProductReport.remark);

    });
}

function initWithDrawTotal(){
    var arr = [
        "name",
        "company",
        "settle_state",
        "visit_time_FROM",
        "visit_time_TO"
    ]
    var condition = {};
    for(var s in arr){
        var id = arr[s]+"_cnd";
        condition[id] = $("#"+id).val();
    }
    ajaxRequest("queryDataProductReportTotal",condition, function(data){
        if(data.resultNode == "success"){
            data = data.rows;
            var html = '<table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center"><tr class="tdCenterClass" align=center ><td width=16.6%>点击次数</td><td width=16.6%>注册个数</td><td width=16.6%>待结算金额</td><td width=16.6%>总结算金额</td></tr>';
            html += "<tr>";
            html += "<td align='center'>";
            html += data.clickcount;
            html += "</td>";

            html += "<td align='center'>";
            html += data.regcount;
            html += "</td>";

            html += "<td align='center'>";
            html += data.waitmoney;
            html += "</td>";

            html += "<td align='center'>";
            html += data.totalmoney;
            html += "</td>";
            html += "<tr>";
            html += "</table>";
            $("#capital").html(html);
        }
    });
}

function exportExcel() {
    var arr = [
        "name",
        "company",
        "settle_state",
        "visit_time_FROM",
        "visit_time_TO"
    ]
    var condition = {};
    for(var s in arr){
        var id = arr[s]+"_cnd";
        condition[id] = $("#"+id).val();
    }
    initexp();
    $.messager.confirm('提示', '确定导出吗？', function(r){
        if (r){
            $(".load1").show();
            ajaxRequest("exportDataProductReportList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'商品数据报表信息','详情',data.rows);
                }else{
                    $.messager.alert('提示',data.resultNode,'info');
                }
                $(".load1").hide();
            });
        }
    });
}

function initexp(){
    var ex_cust_tb = $.et.commonQuery('#ex_cust_tb',{
        fitColumns:true,
        columns:[[
            {field:"name",title:"商品名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"visit_time",title:"访问时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"visit_count",title:"点击次数",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"reg_count",title:"注册次数",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"change_rate",title:"转化率",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"loan_count",title:"放款个数",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"settle_way",title:"合作方式",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"settle_state",title:"结算状态",width:100,sortable:true,headalign:"center",formatter:function(val){
                    switch(val){
                        case "0":
                            return "未结算";
                        case "1":
                            return "已结算";
                        default:
                            return "未知";
                    }
                }},
            {field:"remark",title:"备注",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
        ]]
    });
    ex_cust_tb.refresh();
    window.ex_cust_tb=ex_cust_tb;
}