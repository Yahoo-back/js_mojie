$(function() {
    var cust_tb = $.et.commonQuery('#cust_tb',{
        url:st.apiPath+'qryProductList',
        fit:true,
        fitColumns:true,
        singleSelect:false,
        columns:[[
            {field:"name",title:"商品名称",width:100,sortable:true,headalign:"center",fuzzy_condition:"text"},
            {field:"classify",title:"分类",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    var select = "<select id='classify_cnd' style='width: 100%;' name='classify_cnd'>";
                    select += ("<option value=''>--请选择--</option>");
                    ajaxRequest("qryProductClassifyAll",null, function(data){
                        if(data.resultNode == "success"){
                            var rows = data.rows;
                            for(var i=0; i<rows.length; i++) {
                                $("#classify_cnd").append("<option value='" + rows[i].id + "'>" + rows[i].name + "</option>")
                            }
                        }
                    });
                    return select;
                }},
            {field:"link",title:"链接",width:100,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"status",title:"状态",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    var select = "<select id='status_cnd' style='width: 100%;' name='status_cnd'>";
                    select += ("<option value=''>--请选择--</option>");
                    select += ("<option value='0'>下架</option>");
                    select += ("<option value='1'>上架</option>");
                    return select;
                },formatter:function(val){
                    switch(val){
                        case "0":
                            return "下架";
                        case "1":
                            return "上架";
                        default:
                            return "未知";
                    }
                }},
            {field:"sort",title:"排序",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"is_hot",title:"是否首页热门",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    var select = "<select id='is_hot_cnd' style='width: 100%;' name='is_hot_cnd'>";
                    select += ("<option value=''>--请选择--</option>");
                    select += ("<option value='0'>否</option>");
                    select += ("<option value='1'>是</option>");
                    return select;
                },formatter:function(val){
                    switch(val){
                        case "0":
                            return "否";
                        case "1":
                            return "是";
                        default:
                            return "未知";
                    }
                }},
            {field:"hot_sort",title:"热门排序",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                }},
            {field:"position",title:"位置",width:60,sortable:true,headalign:"center",fuzzy_condition:function(){
                    var select = "<select id='position_cnd' style='width: 100%;' name='position_cnd'>";
                    select += ("<option value=''>--请选择--</option>");
                    select += ("<option value='1'>置顶</option>");
                    select += ("<option value='2'>置尾</option>");
                    select += ("<option value='0'>默认</option>");
                    return select;
                },formatter:function(val){
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
            {field:"create_time",title:"创建时间",width:80,sortable:true,headalign:"center",fuzzy_condition:"date_inteval"},
            {field:"BIZ_OPT",title:"操作",width:130,sortable:true,headalign:"center",fuzzy_condition:function(){
                    return "";
                },formatter:function(value, row, index){
                    value = "<a href=\"javascript:void(0);\" onclick=\"javascript:views('"+row.id+"');\" style=\"color: #FF4500;\">查看</a>";
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:editProduct('"+row.id+"');\" style=\"color: #FF4500;\">编辑</a>";
                    if(row.status == "1") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:underProduct('"+row.id+"');\" style=\"color: #FF4500;\">下架</a>";
                    } else {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:putProduct('"+row.id+"');\" style=\"color: #FF4500;\">上架</a>";
                    }

                    if(row.position == "0") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:topPosition('"+row.id+"');\" style=\"color: #FF4500;\">置顶</a>";
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:basePosition('"+row.id+"');\" style=\"color: #FF4500;\">置尾</a>";
                    } else if(row.position == "1") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:cancelPosition('"+row.id+"');\" style=\"color: #FF4500;\">取消置顶</a>";
                    } else if(row.position == "2") {
                        value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:cancelPosition('"+row.id+"');\" style=\"color: #FF4500;\">取消置尾</a>";
                    }
                    value += "&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:deleteProduct('"+row.id+"');\" style=\"color: #FF4500;\">删除</a>";
                    return value;
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

function views(id){
    var url = ctx+"/product/form_product?id="+id+"&view=1";
    $("#add_product").dialog({
        title : "商品详情",  href:url,
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
                $("#add_product").dialog("close");
            }
        } ]
    });
}

function editProduct(id) {
    if(id != "" && id != null && id != undefined){
    }

    var url = "";
    var title = "";
    url = ctx+"/product/form_product?id="+id;
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
    url = ctx+"/product/form_product?id="+id;
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
    if(!$("#vehicle_add_form").form('validate')){
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

    if($("input[name='perio_way']:checked").length == 0) {
        $.messager.alert('提示', '请至少选择一个分期方式!', 'info');
        return false;
    }
    if($("input[name='apply_require']:checked").length == 0) {
        $.messager.alert('提示', '请至少选择一个申请条件!', 'info');
        return false;
    }
    if($("input[name='apply_data']:checked").length == 0) {
        $.messager.alert('提示', '请至少选择一个申请资料!', 'info');
        return false;
    }

    var s=$("#interest").val();
    var patt = new RegExp(/^(\d{0,7})(\.){0,1}(\d{0,7})$/);

    var result = patt.test(s);
    if (result) {

    } else {
        $.messager.alert('提示', '利息请输入0至8位之间的小数或者整数!', 'info');
        return false;
    }

    if($('input[name^="settle_cp"]:checked').size() == 0){
        $.messager.alert('提示', '请至少选择一种结算方式!', 'info');
        return false;
    }
    var jsonData = $("#vehicle_add_form").serializeObject1();
    var par = $.parseJSON(JSON.stringify(jsonData));

    //处理申请条件、申请资料成字符串形式
    var apply_require = $("input[name='apply_require']:checked");
    var apply_data = $("input[name='apply_data']:checked");

    par.id = $("#id").val();
    par.description = editor.txt.html();
    par.apply_require = arrayToString(apply_require);
    par.apply_data = arrayToString(apply_data);
    var a = par.link;
   var aLink =  a.replace(/&amp;/g,"&");
    par.link = aLink;
    $.messager.confirm('提示', '确定保存该商品吗？', function(r){
        if (r){
            ajaxRequest("saveProduct",par, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#add_product").dialog({ closed : true });
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function qryProduct(id, view) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    ajaxRequest("qryProductById",{"id":id}, function(data){
        var product = data.rows;
        $("#id").val(product.id);
        $("#name").val(product.name);
        $("#interest").val(product.interest);
        $("#money").val(product.money);
        $("#link").val(product.link);
        /*if($('input[name="settle_cpa"]').prop("checked", false)){
            $('input[name="settle_way_cpa"]').validatebox({
                readonly : true
            })
        }
        alert("1111")
        if($('input[name="settle_cps"]').prop("checked", false)){
            $('input[name="settle_way_cps"]').validatebox({
                readonly : true
            })
        }*/

        if(product.settleWayCpa != undefined && product.settleWayCpa != null && product.settleWayCpa != '') {
            $('input[name="settle_cpa"]').prop("checked", true);
            $("#settle_way_cpa").validatebox({
                required: true,
                validType : ['intOrFloat','length[0,10]']
            });
        }else{
            $("#settle_way_cpa").validatebox({
                required: false
            });
        }
        if(product.settleWayCps != undefined && product.settleWayCps != null && product.settleWayCps != ''){
            $("input[name='settle_cps']").prop("checked",true);
            $("#settle_way_cps").validatebox({
                required: true,
                validType : ['intOrFloat','length[0,10]']
            })

        }else{
            $("#settle_way_cps").validatebox({
                required: false
            })
        }
        $("#settle_way_cpa").val(product.settleWayCpa);
        $("#settle_way_cps").val(product.settleWayCps);
        $("#settle_cycle").val(product.settleCycle);
        $("#sort").val(product.sort);
        $("#manager_url").val(product.managerUrl);
        $("#manager_user").val(product.managerUser);
        $("#manager_password").val(product.managerPassword);
        $("#contact").val(product.contact);
        $("#contact_info").val(product.contactInfo);
        $("#classify_id").val(product.classifyId);
        $("#perio_way_"+product.perioWay).prop("checked", "checked");
        $("#periodization").val(product.periodization);
        $("#is_hot").val(product.isHot);
        $("#hot_sort").val(product.hotSort);
        $("#status").val(product.status);
        $("#ktx_desc").val(product.ktxDesc);
        $("#company").val(product.company);
        if(product.conditionList != null) {
            var conditionList = product.conditionList.split(",");
            for(var i=0; i<conditionList.length; i++) {
                $("#apply_require_"+conditionList[i]).prop("checked", "checked");
            }
        }

        if(product.dataList != null) {
            var dataList = product.dataList.split(",");
            for(var i=0; i<dataList.length; i++) {
                $("#apply_data_"+dataList[i]).prop("checked", "checked");
            }
        }
        picSH(product.logo);


        editor.txt.html(product.description);

        if("1" == view) {
            editor.$textElem.attr('contenteditable', false)
        }
    });
}

function topPosition(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    $.messager.confirm('提示', '确定要置顶该商品吗？', function(r){
        if (r){
            ajaxRequest("topPosition",{"id":id}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function basePosition(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }
    $.messager.confirm('提示', '确定要置尾该商品吗？', function(r){
        if (r){
            ajaxRequest("basePosition",{"id":id}, function(data){
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
    $.messager.confirm('提示', '确定要取消置顶或置尾该商品吗？', function(r){
        if (r){
            ajaxRequest("cancelPosition",{"id":id}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function underProduct(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要下架该商品吗？', function(r){
        if (r){
            ajaxRequest("updateProduct",{"id":id, "status" : "0"}, function(data){
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

    $.messager.confirm('提示', '确定要上架该商品吗？', function(r){
        if (r){
            ajaxRequest("updateProduct",{"id":id, "status" : "1"}, function(data){
                $.messager.alert('提示',data.resultNode,'info');
                if(data.resultNode == "success"){
                    $("#cust_tb").datagrid('reload');
                }
            });
        }
    });
}

function deleteProduct(id) {
    if(id == "" || id == null || id == undefined){
        return;
    }

    $.messager.confirm('提示', '确定要删除该商品吗？', function(r){
        if (r){
            ajaxRequest("updateProduct",{"id":id, "status" : "2"}, function(data){
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
    var select = "<td id='TD"+obj.substring(29, 41)+"'>";
    select += "<img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj.substring(29, 41)+"')><br>";
    html += " "+select+" <input name=file_uri type=hidden id=file_uri value='"+obj+"'>";
    //html += "<a href=### onclick=opens('"+(st.webSite+obj)+"') ><img src='"+(st.webSite+obj)+"' style='width:150px;height:100px'  id='demo1' /></a><br></td>";
    html += "<a href=### onclick=opens('"+(st.webSite+obj)+"') ><img src='"+(st.webSite+obj)+"' style='width:150px;height:100px' /></a><br></td>";
    var old = $("#zmwj").html();
    $("#zmwj").html(old+html);
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


