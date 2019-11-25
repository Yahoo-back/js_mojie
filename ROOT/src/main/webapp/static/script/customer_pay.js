$(function() {
    // var cust_tb = $.et.commonQuery('#cust_tb',{
    //     url:st.apiPath+'qryCustomerList',
    //     fit:true,
    //     singleSelect:false,
    //     columns:[[
    //         {field:"mobile",title:"手机号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
    //         {field:"create_time",title:"注册时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
    //         {field:"user_name",title:"姓名",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
    //         {field:"id_card",title:"身份证号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
    //         {field:"bank_card",title:"银行卡号",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
    //         {field:"user_auth",title:"认证状态",width:80,sortable:true,headalign:"center",fuzzy_condition:"text"},
    //         {field:"pay_amt",title:"已支付金额",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
    //             return "";
    //         }},
    //         {field:"status",title:"状态",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
    //             var select = "<select id='status_cnd' style='width: 100%;' name='status_cnd'>";
    //             select += ("<option value=''>--请选择--</option>");
    //             select += ("<option value='0'>正常</option>");
    //             select += ("<option value='1'>禁用</option>");
    //             return select;
    //         },formatter:function(val){
    //             switch(val){
    //                 case "0":
    //                     return "正常";
    //                 case "1":
    //                     return "禁用";
    //                 default:
    //                     return "未知";
    //             }
    //         }},
    //         {field:"remark",title:"备注",width:40,sortable:true,headalign:"center",fuzzy_condition:function () {
    //             return "";
    //         }},
    //         {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
    //             return "";
    //         },formatter:function(value, row, index){
    //             value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
    //             if(row.status == "0") {
    //                 value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:forbidCustomer('"+row.id+"');\" style=\"color: #FF4500;\">禁用</a>";
    //             } else {
    //                 value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:recoverCustomer('"+row.id+"');\" style=\"color: #FF4500;\">恢复</a>";
    //             }
    //             return value;
    //         }}
    //     ]],
    //
    //     onDblClickRow:function(index,row){
    //         views(row.id);
    //     }
    // });

    var mon_tb = $.et.commonQuery('#mon_tb',{
        url:st.apiPath+'qryCustomerMoneyAllSys',
        fit:true,
        singleSelect:false,
        columns:[[

            {field:"name",title:"渠道名",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},

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

    // cust_tb.refresh();
    // window.cust_tb=cust_tb;
});



function queryCust(){
    var par = [
        "name",

    ]
    var cnds = arrTjson(par);
    //cust_tb.query(cnds);
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

function qryCustomer(id, view) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryCustomerById",{"id":id}, function(data){
        var customer = data.rows;
        var att = customer.attach;
        $("#id").val(customer.id);
        $("#mobile").val(customer.mobile);
        $("#create_time").val(customer.create_time);
        $("#sex").val(customer.sex);
        $("#job").val(customer.job);
        $("#zhima_score").val(customer.pay_amt);
        $("#mobile_auth").val(customer.mobile_auth);
        $("#bank_card").val(customer.bank_card);
        $("#bank_open").val(customer.bank_open);
        $("#status").val(customer.status);
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
        select += "<input type='text' disabled='disabled' value='"+temp+"'/><br/>"
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
