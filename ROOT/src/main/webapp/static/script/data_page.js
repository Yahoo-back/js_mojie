$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryDataPageList',
        fit:true,
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"classify",title:"类型",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='classify_cnd' style='width: 100%;' name='classify_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='0'>首页</option>");
                select += ("<option value='1'>注册页</option>");
                select += ("<option value='2'>注册量</option>");
                return select;
            },formatter:function(val){
                switch(val){
                    case "0":
                        return "首页";
                    case "1":
                        return "注册页";
                    case "2":
                        return "注册量";
                    default:
                        return "未知";
                }
            }},
            {field:"visit_time",title:"访问时间",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
                var inputs = "<input id='visit_time_FROM_cnd' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH'})\" class='Wdate' readonly='readonly' type='text' name='visit_time_FROM' style='width: 200px'>&nbsp;&nbsp;-&nbsp;&nbsp;";
                inputs += "<input id='visit_time_TO_cnd' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH'})\" class='Wdate' readonly='readonly' type='text' name='visit_time_TO' style='width: 200px'>";
                return inputs;
            }},
            {field:"count",title:"访问次数",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
        ]],

        onDblClickRow:function(index,row){
            views(row.id);
        }
    });
    cust_tb.refresh();
    window.cust_tb=cust_tb;
});

function queryCust(){
    var par = [
        "classify",
        "visit_time_FROM",
        "visit_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function exportExcel() {
    var arr = [
        "classify",
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
            ajaxRequest("exportDataPageList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'页面访问数据统计','详情',data.rows);
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
            {field:"classify",title:"类型",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "首页";
                    case "1":
                        return "注册页";
                    case "2":
                        return "注册量";
                    default:
                        return "未知";
                }
            }},
            {field:"visit_time",title:"访问时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"count",title:"访问次数",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
        ]]
    });
    ex_cust_tb.refresh();
    window.ex_cust_tb=ex_cust_tb;
}