$(function() {
    $("#data_type_cnd").val("APPLY_REQUIRE");
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryDictByShow',
        fit:true,
       // queryParams:{"cnds":"{'data_type' : 'APPLY_REQUIRE'}"},
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"dictDesc",title:"条件名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"itemValue",title:"值1",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"itemKey",title:"值2",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            // {field:"is_use",title:"是否使用",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
            //     var select = "<select id='is_use_cnd' style='width: 100%;' name='is_use_cnd'>";
            //     select += ("<option value=''>--请选择--</option>");
            //     select += ("<option value='0'>不启用</option>");
            //     select += ("<option value='1'>启用</option>");
            //     return select;
            // },formatter:function(val){
            //     switch(val){
            //         case "0":
            //             return "不启用";
            //         case "1":
            //             return "启用";
            //         default:
            //             return "未知";
            //     }
            // }},
            {field:"xh",title:"排序",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
                return  "";
            }},
            {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                return "";
            },formatter:function(value, row, index){
                value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
                value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editApplyRequire('"+row.id+"');\" style=\"color: #FF4500;\">编辑</a>";
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
        "dict_desc",
        "is_use",
        "data_type",
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function views(id){
    var url = ctx+"/config/form_dictes?id="+id+"&view=1";
    $("#add_config").dialog({
        title : "配置详情",  href:url,
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
                $("#add_config").dialog("close");
            }
        } ]
    });
}

function addApplyRequire(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/config/form_dict?id="+id;
    title = "增加申请条件";

    $("#add_config").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveApplyRequire();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_config").dialog("close");
            }
        } ]
    });
}

function saveApplyRequire() {
    if (!$("#vehicle_add_form").form('validate')) {
        $.messager.alert('提示', '你还有必填项没有填写!', 'info');
        return false;
    }

    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));
    par.id = $("#id").val();
    //par.data_type = "APPLY_REQUIRE";

    $.messager.confirm('提示', '确定保存吗？', function(r){
        if (r){
            ajaxRequest("saveConfig",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_config").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function editApplyRequire(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/config/form_dictes?id="+id;
    title = "修改配置参数";

    $("#add_config").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveApplyRequire();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_config").dialog("close");
            }
        } ]
    });
}

function qryDict(id, view) {
    if (id == "" || id == null || id == undefined) {
        return;
    }
    ajaxRequest("qryDictById",{"id":id}, function(data) {
        var config = data.rows;
        $("#id").val(config.id);
        $("#item_value").val(config.itemValue);
        $("#item_key").val(config.itemKey);
        $("#xh").val(config.xh);
        $("#is_use").val(config.isUse);
    });
}


function underApplyRequire(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要下架该申请条件吗？', function(r){
        if (r){
            ajaxRequest("updateConfigDict",{"id":id, "status" : "0"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function putApplyRequire(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要上架该申请条件吗？', function(r){
        if (r){
            ajaxRequest("updateConfigDict",{"id":id, "status" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function deleteApplyRequire(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要删除该申请条件吗？', function(r){
        if (r){
            ajaxRequest("updateConfigDict",{"id":id, "status" : "2"}, function(data){
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
        "item_value",
        "is_use",
        "data_type",
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
            ajaxRequest("exportDictConfigList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'申请条件信息','详情',data.rows);
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
            {field:"itemValue",title:"条件名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"isUse",title:"是否使用",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "不启用";
                    case "1":
                        return "启用";
                    default:
                        return "未知";
                }
            }},
            {field:"xh",title:"排序",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
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

