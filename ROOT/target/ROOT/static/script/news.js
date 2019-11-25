$(function () {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryNewsList',
        fit:true,
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"title",title:"资讯标题",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"clickall",title:"总点击量",width:80,sortable:true,headalign:"center",fuzzy_condition:function () {
                return "";
            }},
            {field:"clickseven",title:"近7天点击量",width:80,sortable:true,headalign:"center",fuzzy_condition:function () {
                return "";
            }},
            {field:"sort",title:"排序",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
                return "";
            }},
            {field:"position",title:"位置",width:80,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='position_cnd' style='width: 100%;' name='position_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='1'>置顶</option>");
                select += ("<option value='0'>默认</option>");
                return select;
            },formatter:function(val){
                switch(val){
                    case "1":
                        return "置顶";
                    case "0":
                        return "默认";
                    default:
                        return "未知";
                }
            }},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                var select = "<select id='status_cnd' style='width: 100%;' name='status_cnd'>";
                select += ("<option value=''>--请选择--</option>");
                select += ("<option value='0'>已发布</option>");
                select += ("<option value='1'>未发布</option>");
                return select;
            },formatter:function(val){
                switch(val){
                    case "0":
                        return "已发布";
                    case "1":
                        return "未发布";
                    default:
                        return "未知";
                }
            }},
            {field:"start_time",title:"发布时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"create_time",title:"创建时间",width:100,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"BIZ_OPT",title:"操作",width:110,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(value, row, index){
                    value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editNews('"+row.id+"');\" style=\"color: #FF4500;\">编辑</a>";
                    if(row.status == "0") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:underNews('"+row.id+"');\" style=\"color: #FF4500;\">下线</a>";
                    } else {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:putNews('"+row.id+"');\" style=\"color: #FF4500;\">发布</a>";
                    }

                    if(row.position == "0") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:topPosition('"+row.id+"');\" style=\"color: #FF4500;\">置顶</a>";
                    } else if(row.position == "1") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:cancelPosition('"+row.id+"');\" style=\"color: #FF4500;\">取消置顶</a>";
                    }
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:deleteNews('"+row.id+"');\" style=\"color: #FF4500;\">删除</a>";
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
        "title",
        "status",
        "position",
        "start_time_FROM",
        "start_time_TO",
        "create_time_FROM",
        "create_time_TO"
    ]
    var cnds = arrTjson(par);
    cust_tb.query(cnds);
}

function views(id){
    var url = ctx+"/news/form_news?id="+id+"&view=1";
    $("#add_news").dialog({
        title : "资讯详情",  href:url,
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
                $("#add_news").dialog("close");
            }
        } ]
    });
}

function addNews(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/news/form_news?id="+id;
    title = "添加资讯";

    $("#add_news").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveNews();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_news").dialog("close");
            }
        } ]
    });
}

function editNews(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/news/form_news?id="+id;
    title = "修改资讯";

    $("#add_news").dialog({
        title : title,  href: url,
        fit:true,
        closed : false, cache : false, modal : true,
        buttons : [ {
            text : "保存",
            iconCls:"icon-ok",
            handler : function() {
                saveNews();
            }
        }, {
            text : "关闭",
            iconCls:"icon-cancel",
            handler : function() {
                $("#add_news").dialog("close");
            }
        } ]
    });
}

function saveNews() {
    if(!$("#vehicle_add_form").form('validate')){
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

    if($("input[name='classify']:checked").length == 0) {
        $.messager.alert('提示', '请至少选择一个分类!', 'info');
        return false;
    }
    if($("input[name='label']:checked").length == 0) {
        $.messager.alert('提示', '请至少选择一个标签!', 'info');
        return false;
    }

    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));

    //处理申请条件、申请资料成字符串形式
    var classify = $("input[name='classify']:checked");
    var label = $("input[name='label']:checked");
    var product_id = $("input[name='product_id']:checked");

    par.id = $("#id").val();
    par.content = editor.txt.html();
    par.classify = arrayToString(classify);
    par.label = arrayToString(label);
    par.product_id = arrayToString(product_id);

    $.messager.confirm('提示', '确定保存该资讯吗？', function(r){
        if (r){
            ajaxRequest("saveNews",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_news").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function qryNews(id, view) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryNewsById",{"id":id}, function(data){
        var news = data.rows;
        var dcNewsAssociate = news.dcNewsAssociates;
        $("#id").val(news.id);
        $("#title").val(news.title);
        $("#start_time").val(news.startTime);
        $("#end_time").val(news.endTime);
        $("#url").val(news.url);
        $("#status").val(news.status);
        $("#sort").val(news.sort);
        $("#position_dd").val(news.position);
        for(var i=0; i<dcNewsAssociate.length; i++) {
            if(dcNewsAssociate[i].classify == "0") {
                $("#product_id_"+dcNewsAssociate[i].associateId).prop("checked", "checked");
            } else if(dcNewsAssociate[i].classify == "1") {
                $("#classify_"+dcNewsAssociate[i].associateId).prop("checked", "checked");
            } else if(dcNewsAssociate[i].classify == "2") {
                $("#label_"+dcNewsAssociate[i].associateId).prop("checked", "checked");
            }
        }



        picSH(news.icon);


        editor.txt.html(news.content);

        if("1" == view) {
            editor.$textElem.attr('contenteditable', false)
        }
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

function topPosition(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    $.messager.confirm('提示', '确定要置顶该资讯吗？', function(r){
        if (r){
            ajaxRequest("topNewsPosition",{"id":id}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function cancelPosition(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    $.messager.confirm('提示', '确定要取消置顶该资讯吗？', function(r){
        if (r){
            ajaxRequest("cancelNewsPosition",{"id":id}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function underNews(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要下线该资讯吗？', function(r){
        if (r){
            ajaxRequest("updateNews",{"id":id, "status" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function putNews(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要发布该资讯吗？', function(r){
        if (r){
            ajaxRequest("updateNews",{"id":id, "status" : "0"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function deleteNews(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要删除该资讯吗？', function(r){
        if (r){
            ajaxRequest("updateNews",{"id":id, "status" : "2"}, function(data){
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
        "title",
        "status",
        "position",
        "start_time_FROM",
        "start_time_TO",
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
            ajaxRequest("exportNewsList",condition, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'资讯信息','详情',data.rows);
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
            {field:"title",title:"资讯标题",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"clickall",title:"总点击量",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"clickseven",title:"近7天点击量",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"sort",title:"排序",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"position",title:"位置",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "1":
                        return "置顶";
                    case "0":
                        return "默认";
                    default:
                        return "未知";
                }
            }},
            {field:"status",title:"状态",width:100,sortable:true,headalign:"center",formatter:function(val){
                switch(val){
                    case "0":
                        return "已发布";
                    case "1":
                        return "未发布";
                    default:
                        return "未知";
                }
            }},
            {field:"start_time",title:"发布时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
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