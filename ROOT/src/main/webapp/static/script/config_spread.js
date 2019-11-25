$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qrySpreadConfigList',
        fit:true,
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"name",title:"名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"position",title:"位置",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='position_cnd' style='width: 100%;' name='position_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='0'>启动页</option>");
                select += ("<option value='1'>首页banner</option>");
                select += ("<option value='2'>首页滚动小喇叭</option>");
                select += ("<option value='3'>新闻资讯banner</option>");
                return select;
            },formatter:function(val){
                switch(val){
                    case "0":
                        return "启动页";
                    case "1":
                        return "首页banner";
                    case "2":
                        return "首页滚动小喇叭";
                    case "3":
                        return "新闻资讯banner";
                    default:
                        return "未知";
                }
            }},
            {field:"associate_name",title:"推广产品(资讯)",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
                return "";
            }},
            {field:"url",title:"推广链接",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"start_time",title:"开始时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"end_time",title:"结束时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='status_cnd' style='width: 100%;' name='status_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='0'>上架</option>");
                select += ("<option value='1'>下架</option>");
                return select;
            },formatter:function(val){
                switch(val){
                    case "0":
                        return "上架";
                    case "1":
                        return "下架";
                    default:
                        return "未知";
                }
            }},
            {field:"remark",title:"备注",width:100,sortable:true,headalign:"center",fuzzy_condition:function () {
                return "";
            }},
            {field:"BIZ_OPT",title:"操作",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                return "";
            },formatter:function(value, row, index){
                value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
                value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editSpreadConfig('"+row.id+"');\" style=\"color: #FF4500;\">编辑</a>";
                if(row.status == "0") {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:underSpreadConfig('"+row.id+"');\" style=\"color: #FF4500;\">下架</a>";
                } else {
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:putSpreadConfig('"+row.id+"');\" style=\"color: #FF4500;\">上架</a>";
                }
                value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:deleteSpreadConfig('"+row.id+"');\" style=\"color: #FF4500;\">删除</a>";
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
        "position",
        "status",
        "start_time_FROM",
        "start_time_TO",
        "end_time_FROM",
        "end_time_TO",
        "url"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function views(id){
    var url = ctx+"/config/form_spread_config?id="+id+"&view=1";
    $("#add_config").dialog({
        title : "推广配置详情",  href:url,
        fit:true,
        /*
        width : parseInt($(window).height() + 350), height : parseInt($(window).height() - 100),
        top:parseInt($(window).height()/2 - 350),*/
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

function addSpreadConfig(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/config/form_spread_config?id="+id;
    title = "添加推广配置";

    $("#add_config").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveSpreadConfig();
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

function saveSpreadConfig() {
    if (!$("#vehicle_add_form").form('validate')) {
        $.messager.alert('提示', '你还有必填项没有填写!', 'info');
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

    if($("#start_time").val() == "") {
        $.messager.alert('提示', '请选择开始时间!', 'info');
        return false;
    }

    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));
    par.id = $("#id").val();

    $.messager.confirm('提示', '确定保存该推广配置吗？', function(r){
        if (r){
            ajaxRequest("saveSpreadConfig",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_config").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function editSpreadConfig(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/config/form_spread_config?id="+id;
    title = "修改推广配置";

    $("#add_config").dialog({
        title : title,  href: url,
        fit:true,
        /*
        width : parseInt($(window).height() + 350), height : parseInt($(window).height() - 100),
        top:parseInt($(window).height()/2 - 350),*/
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveSpreadConfig();
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

function qrySpreadConfig(id, view) {
    if (id == "" || id == null || id == undefined) {
        return;
    }
    ajaxRequest("qrySpreadConfigById",{"id":id}, function(data) {
        var config = data.rows;
        $("#id").val(config.id);
        $("#name").val(config.name);
        $("#position_dd").val(config.position);
        $("#associate_type").val(config.associateType);
        if(config.associateType == "0") {
            ajaxRequest("qryProductListAll",null, function(data){
                if(data.resultNode == "success"){
                    var rows = data.rows;
                    for(var i=0; i<rows.length; i++) {
                        $("#associate_id").append("<option value='" + rows[i].id +"'>"+ rows[i].name +"</option>");
                    }
                    $("#associate_id").val(config.associateId);
                }
            });
        } else if(config.associateType == "1") {
            ajaxRequest("qryNewsListAll",null, function(data){
                if(data.resultNode == "success"){
                    var rows = data.rows;
                    for(var i=0; i<rows.length; i++) {
                        $("#associate_id").append("<option value='" + rows[i].id +"'>"+ rows[i].title +"</option>");
                    }
                    $("#associate_id").val(config.associateId);
                }
            });
        }
        $("#url").val(config.url);
        $("#start_time").val(config.startTime);
        $("#end_time").val(config.endTime);
        $("#sort").val(config.sort);
        $("#status").val(config.status);
        $("#remark").val(config.remark);

        picSH(config.picPath);
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


function underSpreadConfig(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要下架该配置吗？', function(r){
        if (r){
            ajaxRequest("updateSpreadConfig",{"id":id, "status" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function putSpreadConfig(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要上架该配置吗？', function(r){
        if (r){
            ajaxRequest("updateSpreadConfig",{"id":id, "status" : "0"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function deleteSpreadConfig(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要删除该配置吗？', function(r){
        if (r){
            ajaxRequest("updateSpreadConfig",{"id":id, "status" : "2"}, function(data){
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
        "position",
        "status",
        "start_time_FROM",
        "start_time_TO",
        "end_time_FROM",
        "end_time_TO"
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
            ajaxRequest("exportSpreadConfigList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'推广配置信息','详情',data.rows);
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
            {field:"name",title:"名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"position",title:"位置",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "启动页";
                    case "1":
                        return "首页banner";
                    case "2":
                        return "首页滚动小喇叭";
                    case "3":
                        return "新闻资讯banner";
                    default:
                        return "未知";
                }
            }},
            {field:"associate_name",title:"推广产品(资讯)",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"url",title:"推广链接",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"start_time",title:"开始时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"end_time",title:"结束时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "上架";
                    case "1":
                        return "下架";
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

