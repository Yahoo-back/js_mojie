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
$('#pay_time_FROM_cnd').val(timeStart);
$('#pay_time_TO_cnd').val(timeEnd);
$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryCustomerList',
        fit:true,
        singleSelect:false,
        columns:[[
            {field:"mobile",title:"手机号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"create_time",title:"注册时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
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
            {field:"user_name",title:"姓名",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"id_card",title:"身份证号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"bank_card",title:"银行卡号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"user_auth",title:"认证状态",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
                    var select = "<select id='user_auth_cnd' style='width: 100%;' name='user_auth_cnd'>";
                    select += ("<option value=''>--请选择--</option>");
                    select += ("<option value='0'>未认证</option>");
                    select += ("<option value='1'>身份证</option>");
                    select += ("<option value='2'>个人</option>");
                    select += ("<option value='3'>银行卡</option>");
                    select += ("<option value='4'>联系人</option>");
                    select += ("<option value='5'>运营商</option>");
                    return select;
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
            {field:"pay_amt",title:"已支付金额",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
                return "";
            }},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='status_cnd' style='width: 100%;' name='status_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='0'>正常</option>");
                select += ("<option value='1'>禁用</option>");
                return select;
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
            {field:"remark",title:"备注",width:40,sortable:true,headalign:"center",fuzzy_condition:function () {
                return "";
            }},
            {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                return "";
            },formatter:function(value, row, index){
                value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:update('"+row.id+"');\" style=\"color: #FF4500;\">修改</a>";
                    // value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editReback('"+row.id+"');\" style=\"color: #FF4500;\">退款</a>";
                // value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:reback('"+row.id+"');\" style=\"color: #FF4500;\">退款</a>";

                if(row.status == "0") {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:forbidCustomer('"+row.id+"');\" style=\"color: #FF4500;\">禁用</a>";
                } else {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:recoverCustomer('"+row.id+"');\" style=\"color: #FF4500;\">恢复</a>";
                }

                return value;
            }}
        ]],

        onDblClickRow:function(index,row){
            views(row.id);
        }
    });

    var mon_tb = $.et.commonQuery('#mon_tb',{
        url:st.apiPath+'qryCustomerMoneyAll',
        fit:true,
        singleSelect:false,
        columns:[[
            {field:"pay_time",title:"今日支付金额",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"pay_amt",title:"已总支付金额",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
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
        "mobile",
        "status",
        "user_name",
        "id_card",
        "bank_card",
        "user_auth",
        "name",
        "source",
        "pay_time_FROM",
        "pay_time_TO",
        "create_time_FROM",
        "create_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
    mon_tb.query(cnds);
}

function views(id){
    var url = ctx+"/customer/form_customer?id="+id+"&view=1";
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
function update(id){
    var url = ctx+"/customer/update_customer?id="+id;
    $("#customer_info").dialog({
        title : "修改客户",  href:url,
        fit:true,
        closed : false, cache : false, modal : true,
        onOpen:function(){
            $("input").prop("disabled", "disabled");
        },
        onClose : function() {
            $("input").prop("disabled", false);
            $("select").prop("disabled", false);
        },
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveEditUser(id);
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#customer_info").dialog({ closed : true });
            }
        } ]
    });
}function saveEditUser(id){
    if(!$("#vehicle_add_form").form('validate')){
        return false;
    }
    var jsonData = $("#vehicle_add_form").serializeObject();
    var par = $.parseJSON(JSON.stringify(jsonData));
    console.log(par)
    par.id = id;
    $.messager.confirm('提示', '确定保存吗？', function(r){
        if (r){
            ajaxRequest("updateCustomerById",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#customer_info").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                    //queryCust();
                }
            });
        }
    });
}

function qryCustomer(id, view) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryCustomerById",{"id":id}, function(data){
        var customer = data.rows;
        console.log(customer);
        var att = customer.attach;
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
        $("#user_name").val(customer.user_name );
        $("#id_card ").val(customer.id_card );
        /*$("#source_cnd").val(customer.source);*/
        picSH(att);
    });
}

function forbidCustomer(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要禁用该用户吗？', function(r){
        if (r){
            ajaxRequest("updateCustomer",{"id":id, "status" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}
function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}
//退款
function reback(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    // $.messager.prompt({
    //     width: 380,
    //     height: 200,
    //     title: "退款",
    //     msg: "请输入退款金额",
    //     fn: function (r) {
    //         if (r) {
    //             ajaxRequest("thirdPay",{"id":id,"money": r,"status" : "1"}, function(data){
    //                 $.messager.alert('提示',data.resultNode,'info');
    //                 if(data.resultNode == "success"){
    //                     $("#cust_tb").datagrid('reload');
    //                 }
    //             });
    //                 }
    //     }
    // });
    $.messager.prompt({
        width: 380,
        height: 200,
        title: "退款",
        msg: "请输入退款金额",
        fn: function (r) {
            if (r) {
                ajaxLoading();
                ajaxRequest("thirdPay",{"id":id,"money": r,"status" : "1"},
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
//驳回
function refuse(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    $.messager.prompt({
        width: 450,
        height: 500,
        title: "驳回",
        msg: "<button type=\"button\" class=\"layui-btn\" id=\"test1\" onclick=\"javascript:initPlugn()\">请上传图片</button> 888<table border=\"1\" bordercolor=\"#ccc\" style=\"border-collapse:collapse; padding: 4px; width: 100%\" align=\"center\">\n" +
            "\t\t\t\t\t\t\t<tr id=\"zmwj\" style=\"margin: 3px; display: block;\">\n" +
            "\t\t\t\t\t\t\t</tr>\n" +
            "\t\t\t\t\t\t</table>",
        fn: function (r) {
            if (r) {
                ajaxLoading();
                ajaxRequest("saveRefund",{"id":id,"picUrl": r,"status" : "1"},
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
// qryReback();
//驳回跳转
function editReback(id) {
    if(id != "" && id != null && id != undefined){
    }
// console.log(id);
    var url = "";
    var title = "";
    url = ctx+"/complaint/reback?id="+id;
    title = "驳回";

    $("#add_product").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveReback();
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
function saveReback(id) {
    if(id != "" && id != null && id != undefined){
    }
    console.log(id)
    if(!$("#vehicle_add_form").form('validate')){
        $.messager.alert('提示', '你还有必填项没有填写!', 'info');
        return false;
    }

    var file = $("input[name='file_uri']").length;
    console.log(file)
    if(file == 0) {
        $.messager.alert('提示', '请至少上传1张logo!', 'info');
        return false;
    } else if(file > 1) {
        $.messager.alert('提示', '最多只能上传1张logo!', 'info');
        return false;
    }

    // if($("input[name='perio_way']:checked").length == 0) {
    //     $.messager.alert('提示', '请至少选择一个分期方式!', 'info');
    //     return false;
    // }
    // if($("input[name='apply_require']:checked").length == 0) {
    //     $.messager.alert('提示', '请至少选择一个申请条件!', 'info');
    //     return false;
    // }
    // if($("input[name='apply_data']:checked").length == 0) {
    //     $.messager.alert('提示', '请至少选择一个申请资料!', 'info');
    //     return false;
    // }

    // var s=$("#interest").val();
    // var patt = new RegExp(/^(\d{0,7})(\.){0,1}(\d{0,7})$/);

    // var result = patt.test(s);
    // if (result) {
    //
    // } else {
    //     $.messager.alert('提示', '利息请输入0至8位之间的小数或者整数!', 'info');
    //     return false;
    // }

    // if($('input[name^="settle_cp"]:checked').size() == 0){
    //     $.messager.alert('提示', '请至少选择一种结算方式!', 'info');
    //     return false;
    // }
    var jsonData = $("#vehicle_add_form").serializeObject1();
    console.log(jsonData)
    var par = $.parseJSON(JSON.stringify(jsonData));

    //处理申请条件、申请资料成字符串形式
    // var apply_require = $("input[name='apply_require']:checked");
    // var apply_data = $("input[name='apply_data']:checked");

    par.id = $("#id").val();
    // par.description = editor.txt.html();
    // par.apply_require = arrayToString(apply_require);
    // par.apply_data = arrayToString(apply_data);

    $.messager.confirm('提示', '确定保存该驳回吗？', function(r){
        if (r){
            ajaxRequest("saveReback",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_product").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}
//上传图片
function initPlugn() {
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1',
            url: ctx + '/upload;jsessionid=' + $("#sessionId").val(),
            before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）

                });
            },
            done: function (data) {
                if ("success" == data.resultNode) {
                    callBackFunction(data);
                } else {
                    alert(data.resultNode);
                }
            },
            error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });
    picSH(product.logo);

}
function recoverCustomer(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要恢复该用户吗？', function(r){
        if (r){
            ajaxRequest("updateCustomer",{"id":id, "status" : "0"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
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
        select += "<input type='text' style='w' disabled='disabled' value='"+temp+"'/><br/>"
        html += " "+select+"<input name=file_uri type=hidden id=file_uri value='"+obj[i].path+"'>";
        html += "<a href=### onclick=opens('"+(st.webSite+obj[i].path)+"') ><img src='"+(st.webSite+obj[i].path)+"' style='width:150px;height:100px' /></a><br></td>";
    });
    var old = $("#zmwj").html();
    $("#zmwj").html(old+html);
}


function exportExcel() {
    var arr = [
        "mobile",
        "status",
        "user_name",
        "id_card",
        "bank_card",
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
            ajaxRequest("exportCustomerList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'客户信息','详情',data.rows);
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
            {field:"mobile",title:"手机号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"create_time",title:"注册时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"user_name",title:"姓名",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"id_card",title:"身份证号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"bank_card",title:"银行卡号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"zhima_score",title:"芝麻分",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "正常";
                    case "1":
                        return "禁用";
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

function opens(url){
    window.open(url,"_blank");
}

function init(){
    $("#zmwj").html("");
}
