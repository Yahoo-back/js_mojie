$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryProductVisitManageList',
        fit:true,
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"name",title:"商品名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"money",title:"预付款",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"count",title:"访问次数",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(val){
                    switch(val){
                        case "1":
                            return "关闭";
                        case "2":
                            return "失效";
                        case "0":
                            return "开启";
                        default:
                            return "未知";
                    }
                }},
            {field:"create_time",title:"创建时间",width:80,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"BIZ_OPT",title:"操作",width:130,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(value, row, index){
                    if(row.status == '1'){
                        value = "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:putProduct('"+row.id+"');\" style=\"color: #FF4500;\">开启</a>";
                        return value;
                    }else if(row.status == "0"){
                        value = "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:underProduct('"+row.id+"');\" style=\"color: #FF4500;\">关闭</a>";
                        return value;
                    }

                }}
        ]],

        onDblClickRow:function(index,row){
            views(row.id);
        },
    });
    cust_tb.refresh();
    window.cust_tb=cust_tb;
});




function queryCust(){
    var par = [
        "name",
        "classify",
        "status",
        "position",
        "is_hot",
        "create_time_FROM",
        "create_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function editProduct(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/product/form_visit_product?id="+id+"&view=1";
    title = "修改商品";

    $("#add_product").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveProduct();
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

function addProduct(id) {
    if(id != "" && id != null && id != undefined){
    }
    var url = "";
    var title = "";
    url = ctx+"/product/form_visit_product?id="+id;
    title = "添加商品";

    $("#add_product").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveProduct();
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

function saveProduct() {
    var pd=$("#b1").val();
    var pm=$("#b2").val();
    if(pd == ""){
        $.messager.alert('提示', '请选择一个商品！', 'info');
        return false;
    }
    var s=$("#count").val();
    if(s != ""){
        var patt = new RegExp(/^\+?[0-9][0-9]*$/);
        var result = patt.test(s);
        if (result) {
        } else {
            $.messager.alert('提示', '访问次数请输入1至9位之间的整数!', 'info');
            return false;
        }
        if(parseInt(s) < parseInt(pd)){
            $.messager.alert('提示', '访问数不得小于已访问数', 'info');
            return false;
        }
    }

    var m=$("#money").val();
    if(m != ""){
        var patt = new RegExp(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/);
        var result = patt.test(m);
        if (result) {
        } else {
            $.messager.alert('提示', '预付款请输入0以上的数字!', 'info');
            return false;
        }
    }

    if(m == "" && s == ""){
        $.messager.alert('提示', '预付款或访问次数必须输一个', 'info');
        return false;
    }

    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));
    par.id = $("#id").val();
    par.classify_id = $("#classify_id").val();
    $.messager.confirm('提示', '确定保存该商品吗？', function(r){
        if (r){
            ajaxRequest("saveProductVisitManage",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_product").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function qryProduct(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryProductVisitManageById",{"id":id}, function(data){
        var product = data.rows;
        $("#id").val(product.id);
        $("#count").val(product.count);
        $("#money").val(product.money);
        $("#classify_id").val(product.productId);
        var a=$("#classify_id").val();
        visitCount(a);
    });
}

function underProduct(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要关闭吗？', function(r){
        if (r){
            ajaxRequest("updateProductVisitManage",{"id":id, "status" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function putProduct(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要开启吗？', function(r){
        if (r){
            ajaxRequest("updateProductVisitManage",{"id":id, "status" : "0"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function exportExcel() {
    var arr = [
        "name",
        "classify",
        "status",
        "position",
        "is_hot",
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
            ajaxRequest("exportProductList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'商品信息','详情',data.rows);
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
            {field:"classify",title:"分类",width:100,sortable:true,headalign:"center",formatter:function(val){
                    switch(val){
                        case "0":
                            return "贷款超市";
                        case "1":
                            return "借条";
                        default:
                            return "未知";
                    }
                }},
            {field:"link",title:"链接",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",formatter:function(val){
                    switch(val){
                        case "0":
                            return "下架";
                        case "1":
                            return "上架";
                        default:
                            return "未知";
                    }
                }},
            {field:"sort",title:"排序",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"is_hot",title:"是否首页热门",width:100,sortable:true,headalign:"center",formatter:function(val){
                    switch(val){
                        case "0":
                            return "否";
                        case "1":
                            return "是";
                        default:
                            return "未知";
                    }
                }},
            {field:"hot_sort",title:"热门排序",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"position",title:"位置",width:100,sortable:true,headalign:"center",formatter:function(val){
                    switch(val){
                        case "1":
                            return "置顶";
                        case "2":
                            return "置尾";
                        case "0":
                            return "默认";
                        default:
                            return "未知";
                    }
                }},
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""}
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


