$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryBusinessCustomerList',
        fit:true,
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"name",title:"联系人名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"mobile",title:"联系人手机号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"reason",title:"联系事由",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"remark",title:"备注",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
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
        "name",
        "mobile",
        "create_time_FROM",
        "create_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function exportExcel() {
    var arr = [
        "name",
        "mobile",
        "create_time_FROM",
        "create_time_TO"
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
            ajaxRequest("exportBusinessCustomerList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'商务合作信息','详情',data.rows);
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
            {field:"name",title:"联系人名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"mobile",title:"联系人手机号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"reason",title:"联系事由",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"remark",title:"备注",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
        ]]
    });
    ex_cust_tb.refresh();
    window.ex_cust_tb=ex_cust_tb;
}