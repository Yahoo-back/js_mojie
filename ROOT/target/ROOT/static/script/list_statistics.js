Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

var now = new Date();
var timeStart = now.format('yyyy-MM-dd 00:00:00');
var tomorrow = new Date(now.getTime() + 1 * 24 * 60 * 60 * 1000);
var timeEnd = tomorrow.format("yyyy-MM-dd 00:00:00");
$('#visit_time_FROM_cnd').val(timeStart);
$('#visit_time_TO_cnd').val(timeEnd);
$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'listStatistics',
        fit:true,
        queryParams:{"cnds":'{"visit_time_FROM_cnd":"'+ timeStart +'","visit_time_TO_cnd":"'+ timeEnd+'"}'},
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"user_name",title:"人员",sortable:true,width:100,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"audit_new",title:"跟单量",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"new_pass_cnt",title:"退款量",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"new_pass_rate",title:"退款率（%）",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(val){
                    return val*100;
                }},
            {field:"new_reject_cnt",title:"驳回量",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"new_reject_rate",title:"驳回率（%）",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(val){
                    return val*100;
                }},
        ]],
    });

    cust_tb.refresh();
    window.cust_tb=cust_tb;
});

function queryCust(){
    var par = [
        "user_name",
        "visit_time_FROM",
        "visit_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function exportExcel() {
    var arr = [
        "user_name",
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
            ajaxRequest("exportDataSourceList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'点击来源数据统计','详情',data.rows);
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
            {field:"source",title:"来源",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"visit_time",title:"访问时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"count",title:"访问次数",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"countUv",title:"UV访问次数",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
        ]]
    });
    ex_cust_tb.refresh();
    window.ex_cust_tb=ex_cust_tb;
}

function init(){
    $("#zmwj").html("");
    $("#pz").html("");
    $("#reback").html("");
}