$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryProductClassifyList',
        fit:true,
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"name",title:"类型名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"status",title:"是否使用",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='status_cnd' style='width: 100%;' name='status_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='0'>启用</option>");
                select += ("<option value='1'>不启用</option>");
                return select;
            },formatter:function(val){
                switch(val){
                    case "0":
                        return "启用";
                    case "1":
                        return "不启用";
                    default:
                        return "未知";
                }
            }},
            {field:"sort",title:"排序",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
                return  "";
            }},
            {field:"is_home",title:"是否首页展示",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    var select = "<select id='is_home_cnd' style='width: 100%;' name='is_home_cnd'>";
                    select += ("<option value=''>--请选择--</option>");
                    select += ("<option value='0'>是</option>");
                    select += ("<option value='1'>否</option>");
                    return select;
                },formatter:function(val){
                    switch(val){
                        case "0":
                            return "是";
                        case "1":
                            return "否";
                        default:
                            return "未知";
                    }
                }},
            {field:"home_sort",title:"首页排序",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
                return  "";
            }},
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                return "";
            },formatter:function(value, row, index){
                value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
                value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editProductClassify('"+row.id+"');\" style=\"color: #FF4500;\">编辑</a>";
                if(row.status == "0") {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:underProductClassify('"+row.id+"');\" style=\"color: #FF4500;\">下架</a>";
                } else {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:putProductClassify('"+row.id+"');\" style=\"color: #FF4500;\">上架</a>";
                }
                value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:deleteProductClassify('"+row.id+"');\" style=\"color: #FF4500;\">删除</a>";
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
        "name",
        "status",
        "is_home",
        "create_time_FROM",
        "create_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function views(id){
    var url = ctx+"/product/form_type_product?id="+id+"&view=1";
    $("#add_product_classify").dialog({
        title : "商品类型详情",  href:url,
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
                $("#add_product_classify").dialog("close");
            }
        } ]
    });
}

function addProductClassify(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/product/form_type_product?id="+id;
    title = "增加商品类型";

    $("#add_product_classify").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveProductClassify();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_product_classify").dialog("close");
            }
        } ]
    });
}

function saveProductClassify() {
    if (!$("#vehicle_add_form").form('validate')) {
        $.messager.alert('提示', '你还有必填项没有填写!', 'info');
        return false;
    }

    var file = $("input[name='file_uri']").length;
    if(file == 0) {
        $.messager.alert('提示', '请至少上传1张logo!', 'info');
        return false;
    } else if(file > 1) {
        $.messager.alert('提示', '最多只能上传1张logo!', 'info');
        return false;
    }

    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));
    par.id = $("#id").val();

    $.messager.confirm('提示', '确定保存该商品类型吗？', function(r){
        if (r){
            ajaxRequest("saveProductClassify",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_product_classify").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function editProductClassify(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/product/form_type_product?id="+id;
    title = "修改商品类型";

    $("#add_product_classify").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveProductClassify();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_product_classify").dialog("close");
            }
        } ]
    });
}

function qryProductClassify(id, view) {
    if (id == "" || id == null || id == undefined) {
        return;
    }
    ajaxRequest("qryProductClassifyById",{"id":id}, function(data) {
        var classify = data.rows;
        $("#id").val(classify.id);
        $("#name").val(classify.name);
        $("#url").val(classify.url);
        $("#sort").val(classify.sort);
        $("#status").val(classify.status);
        $("#is_home").val(classify.isHome);
        $("#home_sort").val(classify.homeSort);

        picSH(classify.logo);
    });
}

function picSH(obj){
    var html = '';
    var select = "<td id='TD"+obj.substring(29, 41)+"'>";
    select += "<img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj.substring(29, 41)+"')><br>";
    html += " "+select+" <input name=file_uri type=hidden id=file_uri value='"+obj+"'>";
    html += "<a href=### onclick=opens('"+(st.webSite+obj)+"') ><img src='"+(st.webSite+obj)+"' style='width:150px;height:100px' /></a><br></td>";
    var old = $("#zmwj").html();
    $("#zmwj").html(old+html);
}


function underProductClassify(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要下架该商品类型吗？', function(r){
        if (r){
            ajaxRequest("updateProductClassify",{"id":id, "status" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function putProductClassify(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要上架该商品类型吗？', function(r){
        if (r){
            ajaxRequest("updateProductClassify",{"id":id, "status" : "0"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function deleteProductClassify(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要删除该商品类型吗？', function(r){
        if (r){
            ajaxRequest("updateProductClassify",{"id":id, "status" : "2"}, function(data){
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
        "status",
        "is_home",
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
            ajaxRequest("exportProductClassifyList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'商品类型信息','详情',data.rows);
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
            {field:"name",title:"类型名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"status",title:"是否使用",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "启用";
                    case "1":
                        return "不启用";
                    default:
                        return "未知";
                }
            }},
            {field:"sort",title:"排序",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"is_home",title:"是否首页展示",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "是";
                    case "1":
                        return "否";
                    default:
                        return "未知";
                }
            }},
            {field:"home_sort",title:"首页排序",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"}
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

