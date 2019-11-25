$(function() {
    $("#type_cnd").val("0");
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryNewsTypeList',
        fit:true,
        queryParams:{"cnds":"{'type' : '0'}"},
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"name",title:"分类名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
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
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                return "";
            },formatter:function(value, row, index){
                value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
                value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editClassify('"+row.id+"');\" style=\"color: #FF4500;\">编辑</a>";
                if(row.status == "0") {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:underClassify('"+row.id+"');\" style=\"color: #FF4500;\">关闭</a>";
                } else {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:putClassify('"+row.id+"');\" style=\"color: #FF4500;\">启用</a>";
                }
                value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:deleteClassify('"+row.id+"');\" style=\"color: #FF4500;\">删除</a>";
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
        "type",
        "create_time_FROM",
        "create_time_TO",
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function views(id){
    var url = ctx+"/news/form_news_classify?id="+id+"&view=1";
    $("#add_classify").dialog({
        title : "增加资讯分类",  href:url,
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
                $("#add_classify").dialog("close");
            }
        } ]
    });
}

function editClassify(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/news/form_news_classify?id="+id;
    title = "修改资讯分类";

    $("#add_classify").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveClassify();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_classify").dialog("close");
            }
        } ]
    });
}

function addClassify(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/news/form_news_classify?id="+id;
    title = "增加资讯分类";

    $("#add_classify").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveClassify();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_classify").dialog("close");
            }
        } ]
    });
}

function saveClassify() {
    if (!$("#vehicle_add_form").form('validate')) {
        $.messager.alert('提示', '你还有必填项没有填写!', 'info');
        return false;
    }

    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));
    par.id = $("#id").val();
    par.type = "0";

    $.messager.confirm('提示', '确定保存该资讯分类吗？', function(r){
        if (r){
            ajaxRequest("saveClassify",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_classify").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function qryClassify(id, view) {
    if (id == "" || id == null || id == undefined) {
        return;
    }
    ajaxRequest("qryClassifyById",{"id":id}, function(data) {
        var classify = data.rows;
        $("#id").val(classify.id);
        $("#name").val(classify.name);
        $("#sort").val(classify.sort);
        $("#status").val(classify.status);
    });
}


function underClassify(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要关闭该分类吗？关闭后文章不能关联此分类，并且前端不展示', function(r){
        if (r){
            ajaxRequest("updateClassify",{"id":id, "status" : "1", "type" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function putClassify(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要开启该分类吗？', function(r){
        if (r){
            ajaxRequest("updateClassify",{"id":id, "status" : "0", "type" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function deleteClassify(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要删除该标签吗？删除后文章不能关联此分类，并且前端不展示', function(r){
        if (r){
            ajaxRequest("updateClassify",{"id":id, "status" : "2", "type" : "1"}, function(data){
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
        "type",
        "create_time_FROM",
        "create_time_TO",
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
            ajaxRequest("exportNewsTypeList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'资讯分类信息','详情',data.rows);
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
            {field:"name",title:"分类名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
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
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
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

