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
        url:st.apiPath+'qryDataSourceList',
        fit:true,
        queryParams:{"cnds":'{"visit_time_FROM_cnd":"'+ timeStart +'","visit_time_TO_cnd":"'+ timeEnd+'"}'},
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"source",title:"来源",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='source_cnd' style='width: 100%;' name='source_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                ajaxRequest("qrySourceAll",null, function(data){
                    if(data.resultNode == "success"){
                        var row = data.rows;
                        for(var i=0; i<row.length; i++) {
                            $("#source_cnd").append("<option value='"+row[i].source+"'>"+row[i].source+"</option>");
                        }
                    }
                });
                return select;
            }},
        /*    {field:"visit_time",title:"访问时间",width:320,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},*/
            {field:"uv",title:"点击",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                return "";
            }},
            // {field:"count_uv",title:"注册",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
            //         return "";
            //     }},
            {field:"count",title:"验证码注册",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"none",title:"未认证",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"face",title:"身份证",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"per",title:"个人信息",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"bank",title:"银行卡",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"concat",title:"联系人",width:40,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"yun",title:"运营商",width:40,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"pay",title:"支付",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
        ]],

        onDblClickRow:function(index,row){
            views(row.id);
        }
    });

    var mon_tb = $.et.commonQuery('#mon_tb',{
        url:st.apiPath+'qryDataSourceSum',
        fit:true,
        queryParams:{"cnds":'{"visit_time_FROM_cnd":"'+ timeStart +'","visit_time_TO_cnd":"'+ timeEnd+'"}'},
        fitColumns:true,
        singleSelect:false,
        columns:[[

            {field:"uvCount",title:"点击总数",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            // {field:"countUvCount",title:"注册总数",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
            //         return "";
            //     }},
            {field:"countCount",title:"验证码注册总数",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"noneCount",title:"未认证",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"faceCount",title:"身份证",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"perCount",title:"个人信息",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"bankCount",title:"银行卡",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"concatCount",title:"联系人",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"yunCount",title:"运营商",width:100,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
            {field:"pay",title:"支付",width:80,sortable:true,headalign:"center",fuzzy_condition: function () {
                    return "";
                }},
        ]],
        onDblClickRow:function(index,row){
            views(row.id);
        }
    });
    mon_tb.refresh();
    window.mon_tb=mon_tb;

    cust_tb.refresh();
    window.cust_tb=cust_tb;
});

function queryCust(){
    var par = [
        "source",
        "visit_time_FROM",
        "visit_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
    mon_tb.query(cnds);
}

function exportExcel() {
    var arr = [
        "source",
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