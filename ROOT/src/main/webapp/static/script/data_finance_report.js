$(function () {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryDataFinanceReportList',
        fit:true,
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
            {field:"settle_way",title:"合作方式",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    var select = "<select id='settle_way_cnd' style='width: 100%;' name='settle_way_cnd'>";
                    select += ("<option value=''>--请选择--</option>");
                    ajaxRequest("qryProductSettleWayAll",null, function(data){
                        if(data.resultNode == "success"){
                            var row = data.rows;
                            for(var i=0; i<row.length; i++) {
                                $("#settle_way_cnd").append("<option value='"+row[i].settle_way+"'>"+row[i].settle_way+"</option>")
                            }
                        }
                    });
                    return select;
                }},
            {field:"visit_time",title:"统计时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"settle_money",title:"结算金额",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"remark",title:"备注",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
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
            {field:"user_code",title:"结算人",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"serial_number",title:"流水号",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},

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
        "visit_time_TO",
        "settle_way"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function initWithDrawTotal(){
    var arr = [
        "name",
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
            var html = '<table border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;width: 100%" align="center"><tr class="tdCenterClass" align=center ><td width=16.6%>已结算金额</td><td width=16.6%>待结算金额</td><td width=16.6%>总结算金额</td></tr>';
            html += "<tr>";
            html += "<td align='center'>";
            html += data.settlemoney;
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
            ajaxRequest("exportDataFinanceReportList",condition, function(data){
                console.info(data.rows);
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'财务报表信息','详情',data.rows);
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
            {field:"name",title:"商品名称",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"company",title:"所属公司",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"settle_way",title:"合作方式",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"visit_time",title:"统计时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"settle_money",title:"结算金额",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"remark",title:"备注",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
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
            {field:"user_code",title:"结算人",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"serial_number",title:"流水号",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
        ]]
    });
    ex_cust_tb.refresh();
    window.ex_cust_tb=ex_cust_tb;
}