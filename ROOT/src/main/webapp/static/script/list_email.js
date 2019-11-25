$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'mailRefundList',
        fit:true,
        queryParams:{"cnds": ''},
        // queryParams:{"cnds":'{"visit_time_FROM_cnd":"'+ timeStart +'","visit_time_TO_cnd":"'+ timeEnd+'"}'},
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"request_no",title:"订单号",width:140,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"mobile",title:"手机号",width:90,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"id_card",title:"身份证号",width:120,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"create_time",title:"注册时间",width:120,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"source",title:"来源",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"user_name",title:"姓名",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"bank_card",title:"银行卡号",width:120,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"user_auth",title:"认证状态",width:70,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(val){
                    switch(val){
                        case "活体":
                            return "身份证";
                        case "个人信息":
                            return "个人信息";
                        case "银行卡":
                            return "银行卡";
                        case "联系人":
                            return "联系人";
                        case "运营商":
                            return "运营商";
                        default:
                            return "未认证";
                    }
                }},
            {field:"pay_amt",title:"已支付金额",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"amt",title:"迅付金额",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"status",title:"状态",width:70,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(val){
                    switch(val){
                        case "0":
                            return "正常";
                        case "1":
                            return "禁用";
                        default:
                            return "未知";
                    }
                }},
            {field:"remark",title:"备注",width:40,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"BIZ_OPT",title:"操作",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(value, row, index){
                    if (row.ua == "2" || row.ua == "3") {
                        value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\"  style=\"color: #FF4500;\">查看</a>";
                    }
                    else {
                        value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\"  style=\"color: #FF4500;\">查看</a>";
                        value += "&nbsp;<a onclick=\"javascript:reback('" + row.id + "');\"  style=\"color: #FF4500;\">退款</a>";
                    }
                    return value;
                }}
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
        "mobile",
        "id_card",
        "bank_card",
        "request_no"
    ];
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
    // var phone = document.getElementById("phone_cnd").value;
    // var card = document.getElementById("card_cnd").value;


}
function views(id){
    var url = ctx+"/complaint/form_email?id="+id+"&view=1";
    $("#customer_info").dialog({
        title : "客户详情",  href:url,
        fit:true,
        closed : false, cache : false, modal : true,
        onOpen:function(){
            $("input").prop("disabled", "disabled");
        },
        onClose : function() {
            $("input").prop("disabled", false);
            $("select").prop("disabled", false);
        },
        buttons : [  {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#customer_info").dialog("close");
            }
        } ]
    });
}
function qryEmailList(id, view) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryRefundAttachById",{"id":id}, function(data){
        var customer = data.rows;
        var att = customer.attach;
        var refund = customer.refund;
        $("#id").val(customer.id);
        $("#mobile").val(customer.mobile);
        $("#create_time").val(customer.create_time);
        $("#sex").val(customer.sex);
        $("#job").val(customer.job);
        $("#zhima_score").val(customer.zhima_score);
        $("#mobile_auth").val(customer.mobile_auth);
        $("#bank_card").val(customer.bank_card);
        $("#bank_open").val(customer.bank_open);
        $("#status").val(customer.status);
        picSH(att);
        picSHS(refund);
        picSHR(refund);
    });
}
function qryRefuse() {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryRefundAttachById",{"id":id}, function(data){
        var customer = data.rows;
        $("#id").val(customer.id);
    });
}
function qryReback() {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryRefundAttachById",{"id":id}, function(data){
        var customer = data.rows;
        $("#id").val(customer.id);
    });
}
//退款
function reback(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    $.messager.prompt({
        width: 380,
        height: 200,
        title: "退款",
        msg: "请输入退款金额",
        fn: function (r) {
            if (r) {
                ajaxLoading();
                ajaxRequest("thirdPay",{"id":id,"money": r,"status" : "1","type": "1"},
                    function(data){
                        $.messager.alert('提示',data.resultNode,'info');
                        if(data.resultNode == "success"){
                            $("#cust_tb").datagrid('reload');
                        }
                        ajaxLoadEnd();
                    });

            }
        }
    });
}

function opens(url){
    // var url = "st.webSite+obj[i].path";
    window.open(url,"_blank");
}
function picSH(obj){
    var html = '';
    $.each(obj,function(i){
        var select = "<td id='TD"+obj[i].id+"'>";
        //select += "<img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
        var temp = "";
        if(obj[i].type == "0") {
            temp = "身份证人像";
        } else if(obj[i].type == "1") {
            temp = "身份证国徽面";
        } else if(obj[i].type == "2") {
            temp = "手持身份证";
        }
        if(obj[i].type == "0" || obj[i].type == "1" || obj[i].type == "2") {
            select += "<input type='text' disabled='disabled' value='"+temp+"'/><br/>"
            html += " "+select+"<input name=file_uri type=hidden id=file_uri value='"+obj[i].path+"'>";
            html += "<a href=### onclick=opens('"+(st.webSite+obj[i].path)+"') ><img src='"+(st.webSite+obj[i].path)+"' style='width:150px;height:100px' /></a><br></td>";
        }
    });
    var old = $("#zmwj").html();
    $("#zmwj").html(old+html);
}
function picSHS(obj){
    var html = '';
    $.each(obj,function(i){
        var select = "<td id='TD"+obj[i].id+"'>";
        //select += "<img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
        var temp = "";
        if(obj[i].type == "0") {
            temp = "身份证人像";
        } else if(obj[i].type == "1") {
            temp = "身份证国徽面";
        } else if(obj[i].type == "2") {
            temp = "手持身份证";
        }
        else if(obj[i].type == "3") {
            temp = "银行卡正面";
        } else if(obj[i].type == "4") {
            temp = "银行卡反面";
        } else if(obj[i].type == "5") {
            temp = "app失败";
        }else if(obj[i].type == "6") {
            temp = "app失败";
        } else if(obj[i].type == "7") {
            temp = "app失败";
        }
        if(obj[i].type == "0" || obj[i].type == "1" || obj[i].type == "2" || obj[i].type == "3" || obj[i].type == "4" || obj[i].type == "5" || obj[i].type == "6" || obj[i].type == "7") {
            select += "<input type='text' disabled='disabled' value='" + temp + "'/><br/>"
            html += " " + select + "<input name=file_uri type=hidden id=file_uri value='" + obj[i].path + "'>";
            html += "<a href=### onclick=opens('" + (st.webSite + obj[i].path) + "') ><img src='" + (st.webSite + obj[i].path) + "' style='width:150px;height:100px' /></a><br></td>";
        }
    });
    var olds = $("#pz").html();
    $("#pz").html(olds+html);
}
function picSHR(obj){
    var html = '';
    $.each(obj,function(i){
        var select = "<td id='TD"+obj[i].id+"'>";
        //select += "<img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
        var temp = "";
        if(obj[i].type == "8") {
            temp = "退款";
        } else if(obj[i].type == "9") {
            temp = "驳回截图";
        }
        if(obj[i].type == "8" || obj[i].type == "9") {
            select += "<input type='text' disabled='disabled' value='" + temp + "'/><br/>"
            html += " " + select + "<input name=file_uri type=hidden id=file_uri value='" + obj[i].path + "'>";
            html += "<a href=### onclick=opens('" + (st.webSite + obj[i].path) + "') ><img src='" + (st.webSite + obj[i].path) + "' style='width:150px;height:100px' /></a><br></td>";
        }
    });
    var olds = $("#reback").html();
    $("#reback").html(olds+html);
}
function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
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

function init(){
    $("#zmwj").html("");
    $("#pz").html("");
    $("#reback").html("");
}