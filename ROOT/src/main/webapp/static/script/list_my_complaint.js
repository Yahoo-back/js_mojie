$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryMyRefund',
        fit:true,
        queryParams:{"cnds": ''},
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"mobile",title:"手机号",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"id_card",title:"身份证号",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"create_time",title:"注册时间",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"source",title:"来源",width:40,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"user_name",title:"姓名",width:50,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"bank_card",title:"银行卡号",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"user_auth",title:"认证状态",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
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
            {field:"status",title:"状态",width:40,sortable:true,headalign:"center",fuzzy_condition:function(){
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
            {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(value, row, index) {
                    value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('" + row.id + "');\"  style=\"color: #FF4500;\">查看</a>";
                    if (row.refundstatus == "5"){
                        value += "&nbsp;<a onclick=\"javascript:cancleOrder('" + row.id + "');\"  style=\"color: #FF4500;\">取消跟单</a>";
                        value += '&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editReback('+row.id+', '+row.pay_amt+');\" style=\"color: #FF4500;\">退款</a>';
                        // value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:views('" + row.id + "');\" style=\"color: #FF4500;\">退款</a>";
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:refuse('" + row.id + "');\"  style=\"color: #FF4500;\">驳回</a>";
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
    ];
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function views(id){
    //查看客户详情接口
    var url = ctx+"/complaint/form_my_complaint?id="+id+"&view=1";
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
//驳回
function refuse(id){
    //查看客户详情接口
    var title = "";
    title = "我的客户列表";
    var url = ctx+"/complaint/refuse?id="+id;
    $("#add_product").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveRefuse(id);
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_product").dialog("close");
            }
        } ]
    });
}
function saveRefuse(id) {
    if(id != "" && id != null && id != undefined){
    }
    // console.log(id)
    // if(!$("#vehicle_add_form").form('validate')){
    //     $.messager.alert('提示', '你还有必填项没有填写!', 'info');
    //     return false;
    // }
    var file = $("input[name='file_uri']").length;
    if(file == 0) {
        $.messager.alert('提示', '请至少上传1张图片!', 'info');
        return false;
    } else if(file > 1) {
        $.messager.alert('提示', '最多只能上传1张图片!', 'info');
        return false;
    }
    var jsonData = $("#vehicle_add_form").serializeObject1();
    // console.log(jsonData)
    var par = $.parseJSON(JSON.stringify(jsonData));
    var file_uri = par.file_uri;
    var money = par.money;

    $.messager.confirm('提示', '确定保存该驳回吗？', function(r){
        if (r){
            ajaxRequest("saveRefund",{"id": id, "file_uri" : file_uri} ,  function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_product").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}
//取消跟单
function cancleOrder(id){
    if(id == "" || id == null || id == undefined){
        return;
    }
    $.messager.confirm('提示', '确定要取消跟单吗？', function(r){
        if (r){
            ajaxRequest("updateOrderAuditStatus",{"id":id,"status":1}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}
//驳回跳转
//退款跳转
function editReback(id,payAmt) {
    if(id != "" && id != null && id != undefined){
    }
    var url = "";
    var title = "";
    url = ctx+"/complaint/reback?id="+id;
    title = "我的客户列表";

    $("#add_product").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveReback(id,payAmt);
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_product").dialog("close");
            }
        } ]
    });
}
function saveReback(id,payAmt) {
    if(id != "" && id != null && id != undefined){
    }
    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));
    var m = payAmt;
    var money = par.money;
    var file_uri = par.file_uri;
    var exp = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if(!exp.test(money)) {
        $.messager.alert('提示', "请输入退款金额!<br>退款金额应小于" + payAmt, 'info');
        return;
    }
    if(money > m) {
        $.messager.alert('提示', '退款金额应小于已支付金额!', 'info');
        return false;
    }
    if(!$("#vehicle_add_form").form('validate')){
        $.messager.alert('提示', '你还有必填项没有填写!退款金额应小于已支付金额', 'info');
        return false;
    }
    var file = $("input[name='file_uri']").length;
    if(file == 0) {
        $.messager.alert('提示', '请至少上传1张图片!', 'info');
        return false;
    } else if(file > 1) {
        $.messager.alert('提示', '最多只能上传1张图片!', 'info');
        return false;
    }

    $.messager.confirm('提示', '确定保存该退款吗？', function(r){
        if (r){
            ajaxRequest("saveRefund",{"id": id, "money": money, "file_uri" : file_uri} ,  function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_product").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
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
function qryRefundMyCustomer(id, view) {
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
        /*$("#source_cnd").val(customer.source);*/
        picSH(att);
        picSHS(refund);
        picSHR(refund);
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
        select += "<input type='text' disabled='disabled' value='"+temp+"'/><br/>"
        html += " "+select+"<input name=file_uri type=hidden id=file_uri value='"+obj[i].path+"'>";
        html += "<a href=### onclick=opens('"+(st.webSite+obj[i].path)+"') ><img src='"+(st.webSite+obj[i].path)+"' style='width:150px;height:100px' /></a><br></td>";
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
        } else if(obj[i].type == "3") {
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

