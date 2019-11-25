var sessInfo = "";
var st = {
	'apiPath': ctx+"/main/api?cmd=",
	'webSite': 'http://47.99.71.244:7001',

};
var timestamp=new Date().getTime();
document.write('<title>魔借后台管理系统</title>');
document.write('<meta name="keywords" content="CUM" />');
document.write('<meta name="description" content="CUM" />');
/*document.write('<script type="text/javascript" src="js/lib/jquery.cookie.js"></script>');*/
document.write('<script type="text/javascript" src="'+ctx+'/static/js/jquery-1.7.2.min.js"></script>         ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/jplayer/jquery.jplayer.min.js"></script>         ');
document.write('<link rel="stylesheet" type="text/css" href="'+ctx+'/static/css/style.css" />                 ');
document.write('<link rel="stylesheet" type="text/css" href="'+ctx+'/static/themes/metro/easyui.css" />       ');
document.write('<link rel="stylesheet" type="text/css" href="'+ctx+'/static/themes/icon.css" />               ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/jquery.form.js"></script>               ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/jquery.easyui.min.js"></script>         ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/template-simple.js"></script>         ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/locale/easyui-lang-zh_CN.js"></script>  ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/My97DatePicker/WdatePicker.js"></script>');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/lib/underscore.js"></script>            ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/lib/easytool.js"></script>              ');
document.write('<link rel="stylesheet" type="text/css" href="'+ctx+'/static/js/lib/easytool.css" />           ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/validate/validate.js"></script>    ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/validate/new_validate.js"></script>    ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/common/commonUtil.js"></script>         ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/ExportExcelDlg.js"></script>         ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/registworkflow.js"></script>         ');
document.write('<script type="text/javascript" src="'+ctx+'/static/js/common/jquery.number.min.js"></script>');
document.write('<script type="text/javascript" src="'+ctx+'/static/layui/layui.js"></script>');
/*document.write('<link rel="stylesheet" type="text/css" href="'+ctx+'/static/layui/css/layui.css" />');
document.write('<script type="text/javascript" src="'+ctx+'/static/kindeditor/kindeditor-all-min.js"></script>');
document.write('<script type="text/javascript" src="'+ctx+'/static/kindeditor/kindeditor-all.js"></script>');
 */

function ExporterExcel(headlist,fileName,sheetName,datalist) {
	var obj = $.ExportExcelDlg({
        HeadInfo: headlist,
		//HeadInfo: $("#"+dataId).datagrid("options").columns,
        RowInfo: datalist,
        //FooterInfo: $("#"+dataId).datagrid("getFooterRows"),
        RowStart: 1,
        ColumStart: 1,
        SheetName: sheetName,
        MainTitle: { Displayname: fileName, Alignment: 'Center' },
        SaveName: fileName,
        Swf: ctx+"/static/js/ExportExcel.swf"
    });
	obj.ExportExcelDlg('open');
}


function chkvalue(txt) {
	   if(txt.value.indexOf(" ")>=0)
		   txt.value = txt.value.replace(/\s/g, ""); // 这句话可以强制删除所有空格
}

//对html代码进行编码解码
function htmlEncodeJQ ( str ) {
    return $('<span/>').text( str ).html();
}

function htmlDecodeJQ ( str ) {
    return $('<span/>').html( str ).text();
}

//数组转成1,2,3格式
function arrayToString(temp) {
    var tt = "";
    for(var i =0;i<temp.length; i++) {
        tt += temp[i].value;
        if(i!=temp.length-1) {
            tt += ",";
        }
    }
    return tt;
}

