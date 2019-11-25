var echartsA = echarts.init(document.getElementById('main'));

function tableProductEcharts() {
    var names = [];
    var series = [];
    var datas = [];
    var tempNum = 0;

    ajaxRequest("qryPageListAll", null, function (data) {
        if (data.resultNode == "success") {
            var classify = data.rows;
            var temp = "";
            for(var i=1; i<=classify.length; i++) {
                temp += "<input type='checkbox' name='page_chose' value='"+ classify[i-1].classify +"'>";
                if(classify[i-1].classify == "0") {
                    temp += "<span>首页</span>";
                    names.push("首页");
                } else if(classify[i-1].classify == "1") {
                    temp += "<span>注册页</span>";
                    names.push("注册页");
                } else if(classify[i-1].classify == "2") {
                    temp += "<span>注册量</span>";
                    names.push("注册量");
                }

                ajaxRequest("qryDataPageCharts", {daysFrom : $("#daysFrom").val(), daysTo : $("#daysTo").val(), type : classify[i-1].classify}, function (data) {
                    if (data.resultNode == "success") {
                        tempNum++;
                        var nums = [];
                        var rows = data.rows;

                        for (var j = 0; j < rows.length; j++) {
                            nums.push(rows[j].count);
                            if(tempNum == 1) {
                                datas.push(rows[j].click_date);
                            }
                            if(j == rows.length - 1) {
                                series.push({
                                    name: rows[j].classifyname,
                                    type:'line',
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'insideRight'
                                        }
                                    },
                                    stack: rows[j].classifyname,
                                    data: nums,
                                });
                            }
                        }
                    }
                });

                if(i%4 == 0) {
                    temp += "<br>";
                }

            }
            $("#page_chose_div").html(temp);

            setTimeout( function () {
                echartsA.setOption({
                    title : {
                        text : '页面访问统计图',
                    },
                    tooltip: {
                        trigger: 'axis',
                    },
                    legend: {
                        data: names
                    },
                    grid: {
                        top: '10%',
                        left: '3%',
                        right: '4%',
                        bottom: '1%',
                        containLabel: true
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: true,lang:['','关闭'],
                                optionToContent: function (opt) {
                                    let axisData = opt.xAxis[0].data; //坐标数据
                                    let series = opt.series; //折线图数据
                                    let tdHeads = '<td  style="padding: 0 10px">时间</td>'; //表头
                                    let tdBodys = ''; //数据
                                    series.forEach(function (item) {
                                        //组装表头
                                        tdHeads += `<td style="padding: 0 10px">${item.name}</td>`;
                                    });
                                    let table = `<table border="1" class="table-echarts" style="width: 98%;margin-left:20px;border-collapse:collapse;font-size:14px;text-align:center"><tbody><tr>${tdHeads} </tr>`;
                                    for (let i = 0, l = axisData.length; i < l; i++) {
                                        for (let j = 0; j < series.length; j++) {
                                            //组装表数据
                                            tdBodys += `<td>${ series[j].data[i]}</td>`;
                                        }
                                        table += `<tr><td style="padding: 0 10px">${axisData[i]}</td>${tdBodys}</tr>`;
                                        tdBodys = '';
                                    }
                                    table += '</tbody></table>';
                                    return table;
                                }
                            },
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: datas
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: series
                }, true);
            }, 500);
        }
    });
}

$(function() {
    tableProductEcharts();
});

function queryCust(){
    if(($("#daysFrom").val()=="" && $("#daysTo").val()!="") || ($("#daysFrom").val()!="" && $("#daysTo").val()=="")) {
        $.messager.alert('提示','请选择正确的时间并确保某一个时间节点不为空','info');
        return;
    }

    var temp = $("input[name='page_chose']:checked");
    var tempNum = 0;
    var names1 = [];
    var series1 = [];
    var dates1 = [];

    if(temp.length == 0) {
        temp = $("input[name='page_chose']");
    }

    for(var i=0; i<temp.length; i++) {
        ajaxRequest("qryDataPageCharts", {daysFrom : $("#daysFrom").val(), daysTo : $("#daysTo").val(), type : temp[i].value}, function (data) {
            if (data.resultNode == "success") {
                tempNum++;
                var nums1 = [];
                var rows = data.rows;

                for (var j = 0; j < rows.length; j++) {
                    nums1.push(rows[j].count);

                    if(tempNum == 1) {
                        dates1.push(rows[j].click_date);
                    }
                    if(j == rows.length - 1) {
                        names1.push(rows[j].classifyname);
                        series1.push({
                            name: rows[j].classifyname,
                            type:'line',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'insideRight'
                                }
                            },
                            stack: rows[j].classifyname,
                            data: nums1,
                        });
                    }
                }

                echartsA.setOption({
                    title : {
                        text : '页面访问统计图',
                    },
                    tooltip: {
                        trigger: 'axis',
                    },
                    legend: {
                        data: names1
                    },
                    grid: {
                        top: '10%',
                        left: '3%',
                        right: '4%',
                        bottom: '1%',
                        containLabel: true
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: true,lang:['','关闭'],
                                optionToContent: function (opt) {
                                    let axisData = opt.xAxis[0].data; //坐标数据
                                    let series = opt.series; //折线图数据
                                    let tdHeads = '<td  style="padding: 0 10px">时间</td>'; //表头
                                    let tdBodys = ''; //数据
                                    series.forEach(function (item) {
                                        //组装表头
                                        tdHeads += `<td style="padding: 0 10px">${item.name}</td>`;
                                    });
                                    let table = `<table border="1"  class="table-echarts" style="width: 98%;margin-left:20px;border-collapse:collapse;font-size:14px;text-align:center"><tbody><tr>${tdHeads} </tr>`;
                                    for (let i = 0, l = axisData.length; i < l; i++) {
                                        for (let j = 0; j < series.length; j++) {
                                            //组装表数据
                                            tdBodys += `<td>${ series[j].data[i]}</td>`;
                                        }
                                        table += `<tr><td style="padding: 0 10px">${axisData[i]}</td>${tdBodys}</tr>`;
                                        tdBodys = '';
                                    }
                                    table += '</tbody></table>';
                                    return table;
                                }
                            },
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: dates1
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: series1
                }, true);
            }
        });
    }
}

function exportExcel() {
    initexp();
    $.messager.confirm('提示', '确定导出吗？', function(r){
        if (r){
            $(".load1").show();
            ajaxRequest("exportDataPageCharts",{daysFrom : $("#daysFrom").val(), daysTo : $("#daysTo").val()}, function(data){
                if(data.resultNode == "success"){
                    ExporterExcel($("#ex_cust_tb").datagrid("options").columns,'注册统计','详情',data.rows);
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
            {field:"click_date",title:"注册时间",width:100,sortable:true,headalign:"center",fuzzy_condition:""},
            {field:"count",title:"产品列表访问",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"count2",title:"注册页访问",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
            {field:"count3",title:"注册量",width:100,sortable:true,headalign:"center",fuzzy_condition: ""},
        ]]
    });
    ex_cust_tb.refresh();
    window.ex_cust_tb=ex_cust_tb;
}